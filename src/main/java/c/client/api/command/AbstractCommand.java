package c.client.api.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public abstract class AbstractCommand
{
	public abstract void init(CommandDispatcher dispatcher);
	
	protected static void register(CommandDispatcher dispatcher, LiteralArgumentBuilder... arguments)
	{
		for(LiteralArgumentBuilder argument : arguments)
		{
			dispatcher.register(argument);
		}
	}
}
