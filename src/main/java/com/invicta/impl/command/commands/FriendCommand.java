package com.invicta.impl.command.commands;

import com.invicta.api.command.AbstractCommand;
import com.invicta.api.command.utils.EntitySuggestor;
import com.invicta.api.event.Subscriber;
import com.invicta.api.event.entity.SendMessageEvent;
import com.mojang.brigadier.CommandDispatcher;

import static com.mojang.brigadier.arguments.StringArgumentType.*;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class FriendCommand extends AbstractCommand
{
	@Override
	public String getName()
	{
		return "Friend";
	}
	
	@Override
	public void init(CommandDispatcher dispatcher)
	{
		System.out.println("Init friend");
		register(
			dispatcher,
			literal("add")
				.then(
					argument("player", string())
						.suggests(new EntitySuggestor())
						.executes(c -> addFriend(getString(c, "player")))
				),
			literal("remove")
				.then(
					argument("player", string())
						.suggests(new EntitySuggestor())
						.executes(c -> removeFriend(getString(c, "player")))
				)
		);
	}
	
	@Subscriber
	private void onChat(SendMessageEvent.Pre event)
	{
		System.out.println("Sent message pre event " + event.getMessage());
	}
	
	@Subscriber
	private void onChat(SendMessageEvent event)
	{
		System.out.println("Sent message event " + event.getMessage());
	}
	
	@Subscriber
	private void onChat(SendMessageEvent.Post event)
	{
		System.out.println("Sent message post event " + event.getMessage());
	}
	
	private static int removeFriend(String name)
	{
		System.out.println("removed friend  " + name);
		return 1;
	}
	
	private static int addFriend(String name)
	{
		System.out.println("Added friend  " + name);
		return 1;
	}
}
