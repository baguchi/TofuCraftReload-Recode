package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

public class TofuStructures {
	public static final ResourceKey<Structure> TOFU_CASTLE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle"));
}
