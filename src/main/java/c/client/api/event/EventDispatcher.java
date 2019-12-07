package c.client.api.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class EventDispatcher
{
	private static final Map<Class<? extends AbstractEvent>, Set<SubscribingMethod>> subscriptions = new HashMap<>();
	
	/**
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

class SubscribingMethod
{
	public SubscribingMethod(Object instance, Class<? extends AbstractEvent> event, Method method)
	{
		this.instance = instance;
		this.event = event;
		this.method = method;
	}
	
	final Object instance;
	final Class<? extends AbstractEvent> event;
	final Method method;
	boolean active = true;
}
