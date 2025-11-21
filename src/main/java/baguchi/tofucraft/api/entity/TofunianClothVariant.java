package baguchi.tofucraft.api.entity;

import baguchi.tofucraft.data.resources.registries.TofunianClothVariants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.level.biome.Biome;

import java.util.Objects;

public class TofunianClothVariant {
	public static final Codec<TofunianClothVariant> DIRECT_CODEC = RecordCodecBuilder.create(
			p_332779_ -> p_332779_.group(
							Identifier.CODEC.fieldOf("texture").forGetter(p_335261_ -> p_335261_.texture),
							RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(TofunianClothVariant::biomes)
					)
					.apply(p_332779_, TofunianClothVariant::new)
	);
	public static final Codec<Holder<TofunianClothVariant>> CODEC = RegistryFileCodec.create(TofunianClothVariants.TOFUNIAN_CLOTH_VARIANT_REGISTRY_KEY, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<TofunianClothVariant>> STREAM_CODEC = ByteBufCodecs.holderRegistry(TofunianClothVariants.TOFUNIAN_CLOTH_VARIANT_REGISTRY_KEY);
	private final Identifier texture;
	private final Identifier textureFull;
	private final HolderSet<Biome> biomes;

	public TofunianClothVariant(Identifier p_332712_, HolderSet<Biome> p_332717_) {
		this.texture = p_332712_;
		this.textureFull = fullTextureId(p_332712_);
		this.biomes = p_332717_;
	}

	private static Identifier fullTextureId(Identifier p_336042_) {
		return p_336042_.withPath(p_335262_ -> "textures/" + p_335262_ + ".png");
	}

	public Identifier texture() {
		return this.textureFull;
	}

	public HolderSet<Biome> biomes() {
		return this.biomes;
	}

	@Override
	public boolean equals(Object p_332811_) {
		if (p_332811_ == this) {
			return true;
		} else {
			return !(p_332811_ instanceof TofunianClothVariant tofunianClothVariant)
					? false
					: Objects.equals(this.texture, tofunianClothVariant.texture)
					&& Objects.equals(this.biomes, tofunianClothVariant.biomes);
		}
	}

	@Override
	public int hashCode() {
		int i = 1;
		i = 31 * i + this.texture.hashCode();
		return 31 * i + this.biomes.hashCode();
	}
}