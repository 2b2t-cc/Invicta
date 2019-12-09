package com.invicta.api.util.minecraft.player;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/**
 * @author cookiedragon234 09/Dec/2019
 */
public class ChatUtils
{
	public static void printMessage(String message)
	{
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
	}
}
