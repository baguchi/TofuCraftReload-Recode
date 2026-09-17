package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.feature.BigLeekFeature;
import baguchi.tofucraft.world.gen.feature.TofuBlobFeature;
import baguchi.tofucraft.world.gen.feature.TofuBuildingFeature;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuFeatures {
	public static final DeferredRegister<MapCodec<? extends Feature>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE_TYPE, TofuCraftReload.MODID);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<? extends Feature>> TOFU_ROCK = FEATURES.register("tofu_rock", () -> TofuBlobFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<? extends Feature>> TOFU_BUILDING = FEATURES.register("tofu_building", () -> TofuBuildingFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<? extends Feature>> BIG_LEEK = FEATURES.register("big_leek", () -> BigLeekFeature.CODEC);

}
