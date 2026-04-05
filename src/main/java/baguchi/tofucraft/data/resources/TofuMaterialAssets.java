package baguchi.tofucraft.data.resources;

import baguchi.tofucraft.data.generator.TofuEquipmentAssets;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;

import java.util.Map;

public class TofuMaterialAssets {

	public static final MaterialAssetGroup TOFU_METAL = MaterialAssetGroup.create("tofu_metal", Map.of(TofuEquipmentAssets.METAL, "tofu_metal_darker"));
	public static final MaterialAssetGroup TOFU_DIAMOND = MaterialAssetGroup.create("tofu_diamond", Map.of(TofuEquipmentAssets.DIAMOND, "tofu_diamond_darker"));
	public static final MaterialAssetGroup ZUNDA = MaterialAssetGroup.create("zunda_ruby");

}
