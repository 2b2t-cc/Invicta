package c.client.api.command;

import c.client.api.event.EventDispatcher;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

/**
 * A command that can be called by the player in chat
 *
 * @author cookiedragon234 07/Dec/2019
 */
public abstract class AbstractCommand
{
	public AbstractCommand()
	{
		EventDispatcher.register(this);
		EventDispatcher.subscribe(this);
	}
	
	/**
	 * @return A user friendly name for this command
	 */
	public abstract String getName();
	
	/**
	 * Initialises this command and allows it to register with the given {@link CommandDispatcher}
	 * @param dispatcher The command dispatcher to register with
	 */
	public abstract void init(CommandDispatcher dispatcher);
	
	protected static void register(CommandDispatcher dispatcher, LiteralArgumentBuilder... arguments)
	{
		for(LiteralArgumentBuilder argument : arguments)
		{
			dispatcher.register(argument);
		}
	}
}
