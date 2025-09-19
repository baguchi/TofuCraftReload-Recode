package baguchi.tofucraft.utils;

import baguchi.tofucraft.client.toast.LearningToast;
import baguchi.tofucraft.registry.TofuParticleTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.player.Player;

public class ClientUtils {
	public static final WeightedList<ExplosionParticleInfo> ZUNDA_EXPLOSION_BLOCK_PARTICLES = WeightedList.<ExplosionParticleInfo>builder()
			.add(new ExplosionParticleInfo(TofuParticleTypes.ZUNDA_CLOUD.get(), 1.0F, 1.0F))
			.build();

	public static void playPortalSound(Player localPlayer) {
		Minecraft.getInstance()
				.getSoundManager()
				.play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, localPlayer.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));

	}

	public static void openToast() {
		Minecraft.getInstance().getToastManager().addToast(new LearningToast(Component.translatable("toast.tofucraft.learning").withStyle(ChatFormatting.BLACK)));
	}
}
