package baguchi.tofucraft.api.entity;

import baguchi.tofucraft.data.resources.registries.TofunianVariants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryFileCodec;

import java.util.Objects;

public class TofunianVariant {
	public static final Codec<TofunianVariant> DIRECT_CODEC = RecordCodecBuilder.create(
			p_332779_ -> p_332779_.group(
							Identifier.CODEC.fieldOf("texture").forGetter(p_335261_ -> p_335261_.texture),
							Identifier.CODEC.fieldOf("texture_baby").forGetter(p_335261_ -> p_335261_.textureBaby))
					.apply(p_332779_, TofunianVariant::new)
	);
	public static final Codec<Holder<TofunianVariant>> CODEC = RegistryFileCodec.create(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<TofunianVariant>> STREAM_CODEC = ByteBufCodecs.holderRegistry(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY);
	private final Identifier texture;
	private final Identifier textureFull;
	private final Identifier textureBaby;
	private final Identifier textureBabyFull;

	public TofunianVariant(Identifier adultTexture, Identifier babyTexture) {
		this.texture = adultTexture;
		this.textureFull = fullTextureId(adultTexture);
		this.textureBaby = babyTexture;
		this.textureBabyFull = fullTextureId(babyTexture);
	}

	private static Identifier fullTextureId(Identifier p_336042_) {
		return p_336042_.withPath(p_335262_ -> "textures/" + p_335262_ + ".png");
	}

	public Identifier texture() {
		return this.textureFull;
	}

	public Identifier textureBaby() {
		return this.textureBabyFull;
	}

	@Override
	public boolean equals(Object p_332811_) {
		if (p_332811_ == this) {
			return true;
		} else {
			return !(p_332811_ instanceof TofunianVariant tofunianClothVariant)
					? false
					: Objects.equals(this.texture, tofunianClothVariant.texture) && Objects.equals(this.textureBaby, tofunianClothVariant.textureBaby);
		}
	}

	@Override
	public int hashCode() {
		int i = 1;
		i = 31 * i + this.texture.hashCode();
		i = 31 * i + this.textureBaby.hashCode();
		return i;
	}
}