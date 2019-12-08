package com.invicta.impl.launch;

import com.invicta.impl.hack.HackManager;

public class ClientLaunchImpl
{
	public static final String MOD_ID = "cclient";
	public static final String MOD_NAME = "CClient";
	public static final String VERSION = "0.1";
	
	public static void preInit()
	{
	
	}
	
	public static void init()
	{
		HackManager.init();
	}
	
	public static void postInit()
	{
	
	}
}
