package baguchan.tofucraft.data.generator;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TofuEquipmentModelProvider implements DataProvider {
	private final PackOutput.PathProvider pathProvider;

	public TofuEquipmentModelProvider(PackOutput p_371200_) {
		this.pathProvider = p_371200_.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/equipment");
	}

	@Override
	public CompletableFuture<?> run(CachedOutput p_371294_) {
		Map<ResourceLocation, EquipmentModel> map = new HashMap<>();
		TofuEquipmentModels.bootstrap((p_371303_, p_371237_) -> {
			if (map.putIfAbsent(p_371303_, p_371237_) != null) {
				throw new IllegalStateException("Tried to register equipment model twice for id: " + p_371303_);
			}
		});
		return DataProvider.saveAll(p_371294_, EquipmentModel.CODEC, this.pathProvider, map);
	}

	@Override
	public String getName() {
		return "Equipment Model Definitions";
	}
}
