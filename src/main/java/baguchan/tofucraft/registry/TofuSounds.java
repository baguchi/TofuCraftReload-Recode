package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class TofuSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TofuCraftReload.MODID);

	public static final RegistryObject<SoundEvent> SOFT_BGM = createEvent("music.soft");
	public static final RegistryObject<SoundEvent> MILKY_EARTH_BGM = createEvent("music.milky_earth");
	public static final RegistryObject<SoundEvent> ROUGH_GROUND_BGM = createEvent("music.rough_ground");
	public static final RegistryObject<SoundEvent> TOFU_ROAD_BGM = createEvent("music.tofu_road");
	public static final RegistryObject<SoundEvent> GREEN_BRANCH_BGM = createEvent("music.green_branch");
	public static final RegistryObject<SoundEvent> TOFU_DUNGEON_BGM = createEvent("music.tofu_dungeon");

	public static final RegistryObject<SoundEvent> TOFUBUGLE = createEvent("tofubugle");

	public static final RegistryObject<SoundEvent> TOFUNIAN_YES = createEvent("mob.tofunian.yes");
	public static final RegistryObject<SoundEvent> TOFUNIAN_NO = createEvent("mob.tofunian.no");

	public static final RegistryObject<SoundEvent> TOFUNIAN_AMBIENT = createEvent("mob.tofunian.ambient");
	public static final RegistryObject<SoundEvent> TOFUNIAN_HURT = createEvent("mob.tofunian.hurt");
	public static final RegistryObject<SoundEvent> TOFUNIAN_DEATH = createEvent("mob.tofunian.death");

	public static final RegistryObject<SoundEvent> TOFUSPIDER_AMBIENT = createEvent("mob.tofuspider.ambient");
	public static final RegistryObject<SoundEvent> TOFUSPIDER_HURT = createEvent("mob.tofuspider.hurt");
	public static final RegistryObject<SoundEvent> TOFUSPIDER_DEATH = createEvent("mob.tofuspider.death");
	public static final RegistryObject<SoundEvent> TOFUSPIDER_SPIT = createEvent("mob.tofuspider.spit");

	public static final RegistryObject<SoundEvent> SOUL_BREATH = createEvent("block.soybean_soul.soul_breath");
	public static final RegistryObject<SoundEvent> SOYBEAN_CRACK = createEvent("item.soybean.crack");

	private static RegistryObject<SoundEvent> createEvent(String sound) {
		ResourceLocation name = new ResourceLocation(TofuCraftReload.MODID, sound);
		return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
	}

}
