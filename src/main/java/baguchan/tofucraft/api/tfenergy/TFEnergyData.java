package baguchan.tofucraft.api.tfenergy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;

public record TFEnergyData(int storeTF, int maxTF) {
	public static final Codec<TFEnergyData> CODEC = RecordCodecBuilder.create(
			p_337892_ -> p_337892_.group(
							ExtraCodecs.NON_NEGATIVE_INT.fieldOf("store_tf_energy").forGetter(TFEnergyData::storeTF),
							ExtraCodecs.NON_NEGATIVE_INT.fieldOf("max_tf_energy").forGetter(TFEnergyData::maxTF))
					.apply(p_337892_, TFEnergyData::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, TFEnergyData> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT,
			TFEnergyData::storeTF,
			ByteBufCodecs.VAR_INT,
			TFEnergyData::maxTF,
			TFEnergyData::new
	);

	public TFEnergyData setStoreTF(int storeTF) {
		return new TFEnergyData(storeTF, this.maxTF);
	}

	public TFEnergyData setMaxTF(int maxTF) {
		return new TFEnergyData(this.storeTF, maxTF);
	}

}
