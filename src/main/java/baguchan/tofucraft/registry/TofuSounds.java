package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuSounds {
	public static final SoundEvent SOFT_BGM = createEvent("music.soft");

	public static final SoundEvent MILKY_EARTH_BGM = createEvent("music.milky_earth");

	public static final SoundEvent ROUGH_GROUND_BGM = createEvent("music.rough_ground");

	public static final SoundEvent TOFU_ROAD_BGM = createEvent("music.tofu_road");

	public static final SoundEvent GREEN_BRANCH_BGM = createEvent("music.green_branch");

	public static final SoundEvent TOFU_DUNGEON_BGM = createEvent("music.tofu_dungeon");

	public static final SoundEvent TOFUBUGLE = createEvent("tofubugle");

	public static final SoundEvent TOFUNIAN_YES = createEvent("mob.tofunian.yes");

	public static final SoundEvent TOFUNIAN_NO = createEvent("mob.tofunian.no");

	public static final SoundEvent TOFUNIAN_AMBIENT = createEvent("mob.tofunian.ambient");

	public static final SoundEvent TOFUNIAN_DEATH = createEvent("mob.tofunian.death");

	public static final SoundEvent ZOMBIE_TOFUNIAN_IDLE = createEvent("mob.zombie_tofunian.idle");

	public static final SoundEvent ZOMBIE_TOFUNIAN_HURT = createEvent("mob.zombie_tofunian.hurt");

	public static final SoundEvent ZOMBIE_TOFUNIAN_DEATH = createEvent("mob.zombie_tofunian.death");

	public static final SoundEvent SOUL_BREATH = createEvent("block.soybean_soul.soul_breath");

	public static final SoundEvent SOYBEAN_CRACK = createEvent("item.soybean.crack");

	private static SoundEvent createEvent(String sound) {
		ResourceLocation name = TofuCraftReload.prefix(sound);
		return (new SoundEvent(name)).setRegistryName(name);
	}

	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> registry) {
		registry.getRegistry().register(SOFT_BGM);
		registry.getRegistry().register(MILKY_EARTH_BGM);
		registry.getRegistry().register(ROUGH_GROUND_BGM);
		registry.getRegistry().register(TOFU_ROAD_BGM);
		registry.getRegistry().register(GREEN_BRANCH_BGM);
		registry.getRegistry().register(TOFU_DUNGEON_BGM);
		registry.getRegistry().register(TOFUBUGLE);
		registry.getRegistry().register(TOFUNIAN_YES);
		registry.getRegistry().register(TOFUNIAN_NO);
		registry.getRegistry().register(TOFUNIAN_AMBIENT);
		registry.getRegistry().register(TOFUNIAN_DEATH);
		registry.getRegistry().register(ZOMBIE_TOFUNIAN_IDLE);
		registry.getRegistry().register(ZOMBIE_TOFUNIAN_HURT);
		registry.getRegistry().register(ZOMBIE_TOFUNIAN_DEATH);
		registry.getRegistry().register(SOUL_BREATH);
		registry.getRegistry().register(SOYBEAN_CRACK);
	}
}
