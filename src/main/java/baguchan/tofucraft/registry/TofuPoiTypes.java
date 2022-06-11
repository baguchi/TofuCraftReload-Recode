package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class TofuPoiTypes {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, TofuCraftReload.MODID);


	public static final ResourceKey<PoiType> TOFU_CRAFTSMAN = createKey("tofu_craftsman");
	public static final ResourceKey<PoiType> MORIJIO = createKey("morijio");

	// CREATE TOFU_CRAFTSMAN WORKSTATION AND PROFESSION
	public static final RegistryObject<PoiType> TOFU_CRAFTSMAN_POI = POI_TYPES.register("tofu_craftsman", () -> new PoiType(Set.of(TofuBlocks.SALT_FURNACE.get().defaultBlockState()), 1, 1));

	public static final RegistryObject<PoiType> MORIJIO_POI = POI_TYPES.register("morijio", () -> new PoiType(Set.of(TofuBlocks.MORIJIO.get().defaultBlockState()), 1, 1));

	private static ResourceKey<PoiType> createKey(String p_218091_) {
		return ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, new ResourceLocation(TofuCraftReload.MODID, p_218091_));
	}
}
