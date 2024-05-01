package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class TofuPoiTypes {

	private static final Set<BlockState> CAULDRONS = (Set) ImmutableList.of(Blocks.CAULDRON, Blocks.LAVA_CAULDRON, Blocks.WATER_CAULDRON, Blocks.POWDER_SNOW_CAULDRON).stream().flatMap((p_218093_) -> {
		return p_218093_.getStateDefinition().getPossibleStates().stream();
	}).collect(ImmutableSet.toImmutableSet());
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, TofuCraftReload.MODID);
	public static final ResourceKey<PoiType> TOFU_CRAFTSMAN = createKey("tofu_craftsman");
	public static final ResourceKey<PoiType> MORIJIO = createKey("morijio");
	public static final ResourceKey<PoiType> TOFUNIAN_STATUE = createKey("tofunian_statue");

	// CREATE TOFU_CRAFTSMAN WORKSTATION AND PROFESSION
	public static final Supplier<PoiType> TOFU_CRAFTSMAN_POI = POI_TYPES.register("tofu_craftsman", () -> new PoiType(getBlockStates(TofuBlocks.SALT_FURNACE.get()), 1, 1));

	public static final Supplier<PoiType> MORIJIO_POI = POI_TYPES.register("morijio", () -> new PoiType(getBlockStates(TofuBlocks.MORIJIO.get()), 1, 1));
	public static final Supplier<PoiType> TOFUNIAN_STATUE_POI = POI_TYPES.register("tofunian_statue", () -> new PoiType(getBlockStates(TofuBlocks.TOFUNIAN_STATUE.get()), 32, 6));

	//tofunian's poi
	private static ResourceKey<PoiType> createKey(String p_218091_) {
		return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(TofuCraftReload.MODID, p_218091_));
	}

	private static Set<BlockState> getBlockStates(Block p_218074_) {
		return ImmutableSet.copyOf(p_218074_.getStateDefinition().getPossibleStates());
	}
}
