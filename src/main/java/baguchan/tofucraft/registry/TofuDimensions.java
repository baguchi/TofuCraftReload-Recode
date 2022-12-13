package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;

public class TofuDimensions {
	public static final ResourceKey<Level> tofu_world = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TofuCraftReload.MODID, "tofu_world"));
	public static final ResourceKey<LevelStem> tofu_world_stem = ResourceKey.create(Registries.LEVEL_STEM, new ResourceLocation(TofuCraftReload.MODID, "tofu_world"));

}
