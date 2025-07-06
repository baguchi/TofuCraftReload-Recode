package baguchi.tofucraft.api;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public record TofuLearning(Optional<ResourceLocation> learning_advancement) {
	public static final ResourceKey<Registry<TofuLearning>> REGISTRY_KEY = ResourceKey
			.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "learning"));

	public static final Codec<TofuLearning> CODEC = RecordCodecBuilder.create(instance -> instance
			.group(
					ResourceLocation.CODEC.optionalFieldOf("learning_advancement").forGetter(TofuLearning::learning_advancement))
			.apply(instance, TofuLearning::new)
	);


	public static final Codec<Holder<TofuLearning>> REFERENCE_CODEC = RegistryFileCodec.create(TofuLearning.REGISTRY_KEY, CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<TofuLearning>> STREAM_CODEC = ByteBufCodecs.holderRegistry(REGISTRY_KEY);

	public Optional<ResourceLocation> getLearningAdvancement() {
		return learning_advancement;
	}
}