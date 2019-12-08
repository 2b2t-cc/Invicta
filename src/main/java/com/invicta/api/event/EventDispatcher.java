package com.invicta.api.event;

import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class EventDispatcher
{
	private static final Map<Class<?>, Set<SubscribingMethod>> subscriptions = new ConcurrentHashMap<>();
	private static final Set<SubscribingMethod> subscribingMethods = new HashSet<>();
	
	/**
	 * This will index the given class, searching it for methods that subscribe to events. This will **NOT**
	 * subscribe the class to receive events. In order to that you need to, well, subscribe it! Just use the
	 * subscribe function and pass the same class.
	 *
	 * @param subscriber An initialised class containing methods to be subscribed
	 */
	public static void register(Object subscriber)
	{
		Class<?> clazz = subscriber.getClass();
		
		for(Method declaredMethod : clazz.getDeclaredMethods())
		{
			if(!declaredMethod.isAnnotationPresent(Subscriber.class))
				continue;
			
			if(declaredMethod.getParameterCount() != 1)
				throw new RuntimeException(String.format("Registered method '%s' needs only 1 parameter", declaredMethod.toString()));
			
			Class<?> eventType = declaredMethod.getParameterTypes()[0];
			
			if(!AbstractEvent.class.isAssignableFrom(eventType))
				throw new RuntimeException(String.format("Registered method '%s' parameter needs to extends AbstractEvent", declaredMethod.toString()));
			
			if(!subscriptions.containsKey(eventType))
				subscriptions.put(eventType, new HashSet<>());
			
			subscriptions.get(eventType).add(new SubscribingMethod(subscriber, declaredMethod));
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
	public static void unsubscribe(Object subscriber)
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
		Class eventClass = event.getClass();
		
		while(eventClass != null)
		{
			if(!subscriptions.containsKey(eventClass))
				subscriptions.put(eventClass, new HashSet<>());
			
			Iterator<SubscribingMethod> iterator = subscriptions.get(eventClass).iterator();
			while(iterator.hasNext() && !event.isCancelled())
			{
				SubscribingMethod subscribingMethod = iterator.next();
				if(subscribingMethod.active)
				{
					try
					{
						subscribingMethod.invoke(event);
					}
					catch(Exception e)
					{
						throw new RuntimeException("Error while invoking event '" + event.getClass().getName(), e);
					}
				}
			}
			
			eventClass = eventClass.getSuperclass();
		}
		return event;
	}
}
