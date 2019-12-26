package com.invicta.impl.hack.hacks.player;

import com.invicta.api.hack.AbstractHack;
import com.invicta.api.hack.HackCategory;

/**
 * @author Darki 25/Dec/2019
 */

@AbstractHack.Hack(name = "Sprint", description = "Makes you sprint!", category = HackCategory.COMBAT)
public class SprintHack extends AbstractHack {

	@Override
	public void onUpdated() {

		if (!this.isEnabled()) { // i guess this wont be needed after implementation of tick listeners, cause it shouldnt be called when the hack is disabled
			mc.player.setSprinting(false);
			return;
		}

		if (!isMoving()) {
			mc.player.setSprinting(false);
			return;
		}

		if (mc.player.collidedHorizontally) {
			mc.player.setSprinting(false);
			return;
		}

		mc.player.setSprinting(true);

	}


	@Override
	protected void onDisabled() {
		mc.player.setSprinting(false);
	}

	private boolean isMoving() {
		return mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown();
	}

	private boolean isMovingLegit() {
		return mc.gameSettings.keyBindForward.isKeyDown();
	}

}
