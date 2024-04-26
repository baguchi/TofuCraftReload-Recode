package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.api.tfenergy.TFEnergyData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class TofuDataComponents {
	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, TofuCraftReload.MODID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<TFEnergyData>> TF_ENERGY_DATA = register(
			"tf_energy_data",
			p_341845_ -> p_341845_.persistent(TFEnergyData.CODEC).networkSynchronized(TFEnergyData.DIRECT_STREAM_CODEC).cacheEncoding()
	);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FERMENTATION_DATA = DATA_COMPONENT_TYPES.register("fermentation_data", () -> DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT.orElse(0)).networkSynchronized(ByteBufCodecs.VAR_INT).cacheEncoding().build());



	private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String p_332092_, UnaryOperator<DataComponentType.Builder<T>> p_331261_) {
		return DATA_COMPONENT_TYPES.register(p_332092_, () -> p_331261_.apply(DataComponentType.builder()).build());
	}
}
