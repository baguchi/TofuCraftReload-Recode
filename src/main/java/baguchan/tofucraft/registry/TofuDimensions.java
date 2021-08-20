package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class TofuDimensions {
	public static final ResourceKey<Level> tofu_world = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(TofuCraftReload.MODID, "tofu_world"));
}
