package baguchan.tofucraft.client.sound;

import baguchan.tofucraft.api.TofuBossMob;
import baguchan.tofucraft.network.GuiHooks;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

/*
 * https://github.com/The-Aether-Team/The-Aether/blob/1.20.4-develop/src/main/java/com/aetherteam/aether/client/AetherMusicManager.java
 * based from aether music handler thanks...!
 *
 */

public class TofuMusicManager {
	private static final int FADE_LIMIT = 50;
	private static final RandomSource random = RandomSource.create();
	private static final Minecraft minecraft = Minecraft.getInstance();
	private static final MusicManager musicManager = Minecraft.getInstance().getMusicManager();
	@Nullable
	private static SoundInstance currentMusic;
	private static int nextSongDelay = 100;
	private static Integer fade = null;

	/**
	 * [CODE COPY] - {@link MusicManager#tick()}.<br><br>
	 * Modified to have a {@link Music} null check.
	 */
	public static void tick() {
		Music music = getSituationalMusic();
		if (currentMusic instanceof MusicSoundInstance musicSoundInstance) {
			if (musicSoundInstance.isBossMusic()) {
				if (music == null || !isTofuBossMusic(music)) {
					if (fade == null) {
						fade = 0;
					}
					musicSoundInstance.setVolume((float) Math.exp(-(fade / (FADE_LIMIT / 3.0))));
					fade++;
					if (fade >= FADE_LIMIT) {
						fade = null;
						minecraft.getSoundManager().stop(currentMusic);
						currentMusic = null;
						nextSongDelay = Math.min(Integer.MAX_VALUE, Mth.nextInt(random, 50, 100 / 2)); // End boss music
					}
				}
			} else {
				if (music != null && isTofuBossMusic(music)) {
					if (fade == null) {
						fade = 0;
					}
					musicSoundInstance.setVolume((float) Math.exp(-(fade / (FADE_LIMIT / 3.0))));
					fade++;
					if (fade >= FADE_LIMIT) {
						fade = null;
						minecraft.getSoundManager().stop(currentMusic);
						currentMusic = null;
						nextSongDelay = FADE_LIMIT / 3; // Start boss music
					}
				}
			}
		}

		if (music != null) {
			if (currentMusic != null) {
				if (fade == null) {
					if (!music.getEvent().value().getLocation().equals(currentMusic.getLocation()) && music.replaceCurrentMusic()) {
						minecraft.getSoundManager().stop(currentMusic); // Non-copy, cancels vanilla music if Tofu music starts
						nextSongDelay = Mth.nextInt(random, 0, music.getMinDelay() / 2);
					}

					if (!minecraft.getSoundManager().isActive(currentMusic)) {
						currentMusic = null;
						nextSongDelay = Math.min(nextSongDelay, Mth.nextInt(random, music.getMinDelay(), music.getMaxDelay()));
					}
				}
			}

			nextSongDelay = Math.min(nextSongDelay, music.getMaxDelay());
			if (currentMusic == null && nextSongDelay-- <= 0) {
				startPlaying(music);
			}
		} else {
			if (currentMusic == null || !minecraft.getSoundManager().isActive(currentMusic)) {
				currentMusic = null;
				if (nextSongDelay-- <= 0) {
					nextSongDelay = Math.min(Integer.MAX_VALUE, Mth.nextInt(random, 50, 100));
				}
			}
		}
	}

	/**
	 * [CODE COPY] - {@link MusicManager#startPlaying(Music)}.
	 */
	public static void startPlaying(Music music) {
		musicManager.stopPlaying(); // Non-copy, cancels vanilla music if Tofu music starts
		if (isTofuBossMusic(music)) {
			currentMusic = MusicSoundInstance.forBossMusic(music.getEvent().value());
		} else {
			currentMusic = MusicSoundInstance.forMusic(music.getEvent().value());
		}
		if (currentMusic.getSound() != SoundManager.EMPTY_SOUND) {
			minecraft.getSoundManager().play(currentMusic);
		}
		nextSongDelay = Integer.MAX_VALUE;
	}

	/**
	 * [CODE COPY] - {@link MusicManager#stopPlaying()}.
	 */
	public static void stopPlaying() {
		if (currentMusic != null) {
			minecraft.getSoundManager().stop(currentMusic); // Non-copy, cancels vanilla music if Tofu music starts
			currentMusic = null;
		}
		nextSongDelay += 100;
	}

	@Nullable
	public static SoundInstance getCurrentMusic() {
		return currentMusic;
	}

	/**
	 * Determines when to play different music.
	 *
	 * @return The {@link Music} to play.
	 */
	@Nullable
	public static <T extends LivingEntity & TofuBossMob<?>> Music getSituationalMusic() {
		if (!(minecraft.screen instanceof WinScreen)) {
			if (minecraft.player != null) { // Otherwise replace creative music with biome music in the Tofu.
				if (isTofuBossMusicActive()) {
					T boss = getBossFromFight();
					if (boss != null && boss.getHealth() > 0) {
						Music bossMusic = boss.getBossMusic();
						if (bossMusic != null) {
							return boss.getBossMusic();
						}
					}
				}
			}
		}
		return null;
	}

	public static boolean isTofuBossMusic(Music music) {
		return music.getEvent().is(TofuTags.SoundEvents.BOSS_MUSIC);
	}

	public static boolean isTofuBossMusicActive() {
		return minecraft.gui.getBossOverlay().shouldPlayMusic();
	}


	public static <T extends LivingEntity & TofuBossMob<?>> T getBossFromFight() {
		for (Map.Entry<UUID, Integer> event : GuiHooks.BOSS_EVENTS.entrySet()) {
			UUID eventUUID = event.getKey();
			Entity entity = minecraft.player.level().getEntity(event.getValue());
			if (entity instanceof LivingEntity && entity instanceof TofuBossMob<?>) {
				return (T) entity;
			}
		}
		return null;
	}
}