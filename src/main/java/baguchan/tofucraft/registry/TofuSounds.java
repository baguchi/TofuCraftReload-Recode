package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, TofuCraftReload.MODID);

	public static final Holder<SoundEvent> SOFT_BGM = createHolderEvent("music.soft");
	public static final Holder<SoundEvent> MILKY_EARTH_BGM = createHolderEvent("music.milky_earth");
	public static final Holder<SoundEvent> ROUGH_GROUND_BGM = createHolderEvent("music.rough_ground");
	public static final Holder<SoundEvent> TOFU_ROAD_BGM = createHolderEvent("music.tofu_road");
	public static final Holder<SoundEvent> GREEN_BRANCH_BGM = createHolderEvent("music.green_branch");
	public static final Holder<SoundEvent> TOFU_DUNGEON_BGM = createHolderEvent("music.tofu_dungeon");

	public static final Supplier<SoundEvent> TOFUBUGLE = createEvent("tofubugle");

	public static final Supplier<SoundEvent> TOFUNIAN_YES = createEvent("mob.tofunian.yes");
	public static final Supplier<SoundEvent> TOFUNIAN_NO = createEvent("mob.tofunian.no");

	public static final Supplier<SoundEvent> TOFUNIAN_AMBIENT = createEvent("mob.tofunian.ambient");
	public static final Supplier<SoundEvent> TOFUNIAN_HURT = createEvent("mob.tofunian.hurt");
	public static final Supplier<SoundEvent> TOFUNIAN_DEATH = createEvent("mob.tofunian.death");

	public static final Supplier<SoundEvent> TOFUSPIDER_AMBIENT = createEvent("mob.tofuspider.ambient");
	public static final Supplier<SoundEvent> TOFUSPIDER_HURT = createEvent("mob.tofuspider.hurt");
	public static final Supplier<SoundEvent> TOFUSPIDER_DEATH = createEvent("mob.tofuspider.death");
	public static final Supplier<SoundEvent> TOFUSPIDER_SPIT = createEvent("mob.tofuspider.spit");

	public static final Supplier<SoundEvent> SOUL_BREATH = createEvent("block.soybean_soul.soul_breath");
	public static final Supplier<SoundEvent> SOYBEAN_CRACK = createEvent("item.soybean.crack");

	private static Supplier<SoundEvent> createEvent(String sound) {
		ResourceLocation name = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, sound);
		return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
	}


	private static Holder<SoundEvent> createHolderEvent(String sound) {
		ResourceLocation name = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, sound);
		return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
	}
}
