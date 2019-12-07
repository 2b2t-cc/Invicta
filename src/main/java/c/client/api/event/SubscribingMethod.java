package c.client.api.event;

import java.lang.reflect.Method;

/**
 * Utility class for storing information about methods that are subscribed to events
 *
 * @author cookiedragon234 07/Dec/2019
 */
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
