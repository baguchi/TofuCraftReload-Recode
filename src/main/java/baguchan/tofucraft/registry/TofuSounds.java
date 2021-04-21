package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
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
		return (SoundEvent) (new SoundEvent(name)).setRegistryName(name);
	}

	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> registry) {
		registry.getRegistry().register((IForgeRegistryEntry) SOFT_BGM);
		registry.getRegistry().register((IForgeRegistryEntry) MILKY_EARTH_BGM);
		registry.getRegistry().register((IForgeRegistryEntry) ROUGH_GROUND_BGM);
		registry.getRegistry().register((IForgeRegistryEntry) TOFU_ROAD_BGM);
		registry.getRegistry().register((IForgeRegistryEntry) GREEN_BRANCH_BGM);
		registry.getRegistry().register((IForgeRegistryEntry) TOFU_DUNGEON_BGM);
		registry.getRegistry().register((IForgeRegistryEntry) TOFUBUGLE);
		registry.getRegistry().register((IForgeRegistryEntry) TOFUNIAN_YES);
		registry.getRegistry().register((IForgeRegistryEntry) TOFUNIAN_NO);
		registry.getRegistry().register((IForgeRegistryEntry) TOFUNIAN_AMBIENT);
		registry.getRegistry().register((IForgeRegistryEntry) TOFUNIAN_DEATH);
		registry.getRegistry().register((IForgeRegistryEntry) ZOMBIE_TOFUNIAN_IDLE);
		registry.getRegistry().register((IForgeRegistryEntry) ZOMBIE_TOFUNIAN_HURT);
		registry.getRegistry().register((IForgeRegistryEntry) ZOMBIE_TOFUNIAN_DEATH);
		registry.getRegistry().register((IForgeRegistryEntry) SOUL_BREATH);
		registry.getRegistry().register((IForgeRegistryEntry) SOYBEAN_CRACK);
	}
}
