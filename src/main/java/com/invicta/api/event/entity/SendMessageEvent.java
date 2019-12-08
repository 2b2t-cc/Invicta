package com.invicta.api.event.entity;

import com.invicta.api.event.AbstractEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

/**
 * @author cookiedragon234 08/Dec/2019
 */
public class SendMessageEvent extends AbstractEvent
{
	private final String message;
	
	public SendMessageEvent(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	@Cancelable
	public static class Pre extends SendMessageEvent
	{
		public Pre(String message)
		{
			super(message);
		}
	}
	
	public static class Post extends SendMessageEvent
	{
		public Post(String message)
		{
			super(message);
		}
	}
}
