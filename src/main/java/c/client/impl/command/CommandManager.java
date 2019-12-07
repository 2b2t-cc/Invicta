package c.client.impl.command;

import c.client.api.command.RootCommandProvider;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class CommandManager
{
	private static CommandDispatcher dispatcher;
	
	public static void init()
	{
		dispatcher = new CommandDispatcher(new RootCommandProvider());
	}
	
	public static void execute(String command) throws CommandSyntaxException
	{
		dispatcher.execute(command, null);
	}
	
	public static String parse()
	{
		ParseResults parse = dispatcher.parse("", null);
		return parse.getReader().getString();
	}
}
