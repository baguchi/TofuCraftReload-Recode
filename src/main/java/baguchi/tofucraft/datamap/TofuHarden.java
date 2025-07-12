package baguchi.tofucraft.datamap;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TofuHarden(int level) {
	public static final Codec<TofuHarden> LEVEL_CODEC = Codec.intRange(0, 15)
			.xmap(TofuHarden::new, TofuHarden::level);
	public static final Codec<TofuHarden> CODEC = Codec.withAlternative(
			RecordCodecBuilder.create(in -> in.group(
					Codec.intRange(0, 15).fieldOf("level").forGetter(TofuHarden::level)).apply(in, TofuHarden::new)),
			LEVEL_CODEC);
}
