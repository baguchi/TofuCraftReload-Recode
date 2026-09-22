package baguchi.tofucraft.datamap;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TofuSignal(int level) {
	public static final Codec<TofuSignal> LEVEL_CODEC = Codec.intRange(0, 15)
			.xmap(TofuSignal::new, TofuSignal::level);
	public static final Codec<TofuSignal> CODEC = Codec.withAlternative(
			RecordCodecBuilder.create(in -> in.group(
					Codec.intRange(0, 15).fieldOf("level").forGetter(TofuSignal::level)).apply(in, TofuSignal::new)),
			LEVEL_CODEC);
}
