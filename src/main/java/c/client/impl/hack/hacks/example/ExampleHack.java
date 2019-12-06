package c.client.impl.hack.hacks.example;

import c.client.api.hack.AbstractHack;
import c.client.api.hack.HackCategory;

/**
 * @author cookiedragon234 06/Dec/2019
 */
@AbstractHack.Hack(name = "Example Hack", description = "This is just an example", category = HackCategory.COMBAT)
public class ExampleHack extends AbstractHack
{
	/**
	 * Constructor isnt required normally, unless you want to add code to run when the client is initialised, in
	 * this case throwing an exception cause wtf are you doing initialising an example hack
	 */
	public ExampleHack()
	{
		throw new RuntimeException("Bro dont initialise this hack lmaoooo");
	}
}
