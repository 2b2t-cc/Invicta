package com.invicta.api.event;

import java.util.*;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class EventDispatcher
{
	private static final Map<Class<? extends AbstractEvent>, Set<SubscribingMethod>> subscriptions = new HashMap<>();
	
	/**
	 * This will index the given class, searching it for methods that subscribe to events. This will **NOT**
	 * subscribe the class to receive events. In order to that you need to, well, subscribe it! Just use the
	 * subscribe function and pass the same class.
	 *
	 * @param subscriber An initialised class containing methods to be subscribed
	 */
	public static void register(Object subscriber)
	{
		Class clazz = subscriber.getClass();
		while(clazz != null)
		{
			Arrays.stream(clazz.getDeclaredMethods())
				.filter(method -> method.isAnnotationPresent(Subscriber.class))
				.forEach(method ->
				{
					if(method.getParameterCount() != 1)
						throw new IllegalArgumentException(String.format("Incorrect number of parameters for subscribed method '%s'", method.toString()));
					
					Class<?> paramType = method.getParameterTypes()[0];
					if(!(AbstractEvent.class.isInstance(paramType)))
						throw new IllegalArgumentException(String.format("Arguments did not extend event for subscribed method '%s'", method.toString()));
					
					method.setAccessible(true);
					
					Class<? extends AbstractEvent> eventType = (Class<? extends AbstractEvent>)paramType;
					
					if(!subscriptions.containsKey(paramType))
						subscriptions.put(eventType, new HashSet<>());
					
					subscriptions.get(paramType).add(new SubscribingMethod(subscriber, eventType, method));
				});
		}
	}
	
	/**
	 * Subscribes the given class, meaning that it's methods will be eligible to recieve events.
	 * You **MUST** register the class before subscribing it.
	 *
	 * @param subscriber The class to subscribe
	 */
	public static void subscribe(Object subscriber)
	{
		for(Set<SubscribingMethod> subscribingMethods : subscriptions.values())
		{
			for(SubscribingMethod subscribingMethod : subscribingMethods)
			{
				if(subscribingMethod.instance == subscriber)
				{
					subscribingMethod.active = true;
				}
			}
		}
	}
	
	/**
	 * Unsubscribe the class, therefore making it ineligible to receive events.
	 *
	 * @param subscriber The class to subscribe
	 */
	public static void unsubscribe(Subscriber subscriber)
	{
		for(Set<SubscribingMethod> subscribingMethods : subscriptions.values())
		{
			for(SubscribingMethod subscribingMethod : subscribingMethods)
			{
				if(subscribingMethod.instance == subscriber)
				{
					subscribingMethod.active = false;
				}
			}
		}
	}
	
	/**
	 * Dispatches the given event to all the methods that subscribe to it
	 *
	 * @param event The event to dispatch
	 * @return The event that was dispatched
	 */
	public static AbstractEvent dispatch(AbstractEvent event)
	{
		Iterator<SubscribingMethod> iterator = subscriptions.get(event.getClass()).iterator();
		while(iterator.hasNext() && !event.isCancelled())
		{
			SubscribingMethod subscribingMethod = iterator.next();
			if(subscribingMethod.active)
			{
				try
				{
					subscribingMethod.method.invoke(subscribingMethod.instance);
				}
				catch(Exception e)
				{
					throw new RuntimeException("Error while invoking event '" + event.getClass().getName());
				}
			}
		}
		return event;
	}
}
