package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentModel;

import java.util.function.BiConsumer;

public interface TofuEquipmentModels {
	ResourceLocation KINU = TofuCraftReload.prefix("kinu");
	ResourceLocation MOMEN = TofuCraftReload.prefix("momen");
	ResourceLocation SOLID = TofuCraftReload.prefix("solid");
	ResourceLocation METAL = TofuCraftReload.prefix("metal");
	ResourceLocation DIAMOND = TofuCraftReload.prefix("diamond");

	static void bootstrap(BiConsumer<ResourceLocation, EquipmentModel> p_371586_) {
		p_371586_.accept(KINU, onlyHumanoid("kinu"));
		p_371586_.accept(MOMEN, onlyHumanoid("momen"));
		p_371586_.accept(SOLID, onlyHumanoid("solid"));
		p_371586_.accept(METAL, onlyHumanoid("metal"));
		p_371586_.accept(DIAMOND, onlyHumanoid("diamond"));
	}


	private static EquipmentModel onlyHumanoid(String p_371738_) {
		return EquipmentModel.builder().addHumanoidLayers(TofuCraftReload.prefix(p_371738_)).build();
	}
}
