package com.invicta.impl.command;

import com.invicta.api.command.AbstractCommand;
import com.invicta.api.command.RootCommandProvider;
import com.invicta.api.event.EventDispatcher;
import com.invicta.api.event.Subscriber;
import com.invicta.api.event.entity.SendMessageEvent;
import com.invicta.api.util.SimpleClassLoader;
import com.invicta.api.util.minecraft.player.ChatUtils;
import com.invicta.impl.command.commands.FriendCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class CommandManager
{
	private static final String PREFIX = ".";
	private static CommandDispatcher dispatcher;
	private static Set<AbstractCommand> commands;
	
	public static void init()
	{
		dispatcher = new CommandDispatcher(new RootCommandProvider());
		
		commands = new HashSet<>();
		
		new SimpleClassLoader<AbstractCommand>()
			.build(
				FriendCommand.class
			)
			.initialise(
				cmd -> {commands.add(cmd); cmd.init(dispatcher);},
				cmd -> {},
				(cmd, e) -> new RuntimeException(String.format("Failed to initialise command '%s'", cmd.getName()), e)
			);
		
		EventDispatcher.register(CommandManager.class);
		EventDispatcher.subscribe(CommandManager.class);
		System.out.println("Subscribed");
	}
	
	@Subscriber
	public static void onMessage(SendMessageEvent.Pre event)
	{
		if(event.getMessage().startsWith(PREFIX))
		{
			event.setCancelled(true);
			try
			{
				execute(event.getMessage().trim().substring(PREFIX.length()));
			}
			catch(Exception e)
			{
				ChatUtils.printMessage(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public static void execute(String command) throws CommandSyntaxException
	{
		dispatcher.execute(parse(command));
	}
	
	public static ParseResults parse(String command)
	{
		return dispatcher.parse(command, null);
	}
}
