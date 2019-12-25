package com.invicta.impl.hack.hacks.player;

import com.invicta.api.hack.AbstractHack;
import com.invicta.api.hack.HackCategory;

/**
 * @author Darki 25/Dec/2019
 */

@AbstractHack.Hack(name = "Sprint", description = "Makes you sprint!", category = HackCategory.COMBAT)
public class SprintHack extends AbstractHack {
	
	@Override
	public void onEnable() {
		Minecraft.getMinecraft().player.setSprinting() = true;
	}
	
	@Override
	public void onDisable() {
		Minecraft.getMinecraft().player.setSprinting() = false;
		
}