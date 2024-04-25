package baguchan.tofucraft.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;

public record FermentationData(int fermentation) {
	public static final Codec<FermentationData> CODEC = RecordCodecBuilder.create(
			p_337892_ -> p_337892_.group(
							ExtraCodecs.NON_NEGATIVE_INT.fieldOf("fermentation").forGetter(FermentationData::fermentation))
					.apply(p_337892_, FermentationData::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, FermentationData> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT,
			FermentationData::fermentation,
			FermentationData::new
	);

}
