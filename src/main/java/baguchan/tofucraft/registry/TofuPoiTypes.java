package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuPoiTypes {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, TofuCraftReload.MODID);


	// CREATE TOFU_CRAFTSMAN WORKSTATION AND PROFESSION
	public static final RegistryObject<PoiType> TOFU_CRAFTSMAN_POI = POI_TYPES.register("tofu_craftsman", () -> new PoiType("tofucraft:tofu_craftsman", PoiType.getBlockStates(TofuBlocks.SALT_FURNACE.get()), 1, 1));

	public static final RegistryObject<PoiType> MORIJIO_POI = POI_TYPES.register("morijio", () -> new PoiType("tofucraft:morijio", PoiType.getBlockStates(TofuBlocks.MORIJIO.get()), 1, 1));
}
