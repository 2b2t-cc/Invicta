package c.client.impl.command;

import c.client.api.command.AbstractCommand;
import c.client.api.command.RootCommandProvider;
import c.client.api.event.EventDispatcher;
import c.client.api.util.SimpleClassLoader;
import c.client.impl.command.commands.FriendCommand;
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
				cmd -> commands.add(cmd),
				cmd -> {},
				(cmd, e) -> new RuntimeException(String.format("Failed to initialise command '%s'", cmd.getName()), e)
			);
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
