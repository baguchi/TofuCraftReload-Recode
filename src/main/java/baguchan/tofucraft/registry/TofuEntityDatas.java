package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.api.TofunianProfession;
import net.minecraft.core.Holder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class TofuEntityDatas {
	public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATAS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, TofuCraftReload.MODID);


	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<FluidStack>> FLUID_STACK = ENTITY_DATAS.register(
			"fluid_stack",
			() -> EntityDataSerializer.forValueType(FluidStack.STREAM_CODEC)
	);
	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<TofunianProfession>>> TOFUNIAN_PROFESSION = ENTITY_DATAS.register("tofunian_profession", () -> EntityDataSerializer.forValueType(ByteBufCodecs.fromCodecWithRegistries(TofunianProfessions.getRegistry().holderByNameCodec())));

}
