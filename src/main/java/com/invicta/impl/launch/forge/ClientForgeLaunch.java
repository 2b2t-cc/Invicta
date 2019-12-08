package com.invicta.impl.launch.forge;

import com.invicta.impl.launch.ClientLaunchImpl;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author cookiedragon234 06/Dec/2019
 */
@Mod(
	modid = ClientLaunchImpl.MOD_ID,
	name = ClientLaunchImpl.MOD_NAME,
	version = ClientLaunchImpl.VERSION
)
public class ClientForgeLaunch
{
	/**
	 * This is the first initialization event. Register tile entities here.
	 * The registry events below will have fired prior to entry to this method.
	 */
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ClientLaunchImpl.preInit();
	}
	
	/**
	 * This is the second initialization event. Register custom recipes
	 */
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ClientLaunchImpl.init();
	}
	
	/**
	 * This is the final initialization event. Register actions from other mods here
	 */
	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		ClientLaunchImpl.postInit();
	}
}
