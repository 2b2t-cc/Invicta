package com.invicta.api.event;

import java.lang.reflect.Method;

/**
 * Utility class for storing information about methods that are subscribed to events
 *
 * @author cookiedragon234 07/Dec/2019
 */
class SubscribingMethod
{
	public SubscribingMethod(Object instance, Method method)
	{
		method.setAccessible(true);
		
		this.instance = instance;
		this.method = method;
	}
	
	final Object instance;
	final Method method;
	boolean active = true;
	
	public void invoke(Object arg)
	{
		try
		{
			method.invoke(this.instance, arg);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Failed to post event", e);
		}
	}
}
