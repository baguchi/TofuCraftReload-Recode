package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.TofuNoiseBasedChunkGenerator;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuChunkGenerators {
	public static final DeferredRegister<MapCodec<? extends ChunkGenerator>> CHUNK_GENERATOR = DeferredRegister.create(BuiltInRegistries.CHUNK_GENERATOR, TofuCraftReload.MODID);
	public static final DeferredHolder<MapCodec<? extends ChunkGenerator>, MapCodec<? extends ChunkGenerator>> TOFU_CHUNK_GENERATOR = CHUNK_GENERATOR.register("tofu_gen", () -> TofuNoiseBasedChunkGenerator.CODEC);

}
