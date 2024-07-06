package baguchan.tofucraft.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ClientUtils {

	@OnlyIn(Dist.CLIENT)
	public static void playPortalSound(Player localPlayer) {
		Minecraft.getInstance()
				.getSoundManager()
				.play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, localPlayer.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));

	}
}
