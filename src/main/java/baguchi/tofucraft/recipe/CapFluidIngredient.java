package baguchi.tofucraft.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.util.NeoForgeExtraCodecs;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;

import java.util.Objects;
import java.util.Optional;

public class CapFluidIngredient {
	public static final Codec<CapFluidIngredient> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					FluidIngredient.CODEC.optionalFieldOf("ingredient").forGetter(CapFluidIngredient::ingredient),
					NeoForgeExtraCodecs.optionalFieldAlwaysWrite(ExtraCodecs.POSITIVE_INT, "amount", FluidType.BUCKET_VOLUME).forGetter(CapFluidIngredient::amount))
			.apply(instance, CapFluidIngredient::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, CapFluidIngredient> STREAM_CODEC = StreamCodec.composite(
			FluidIngredient.OPTIONAL_STREAM_CODEC,
			CapFluidIngredient::ingredient,
			ByteBufCodecs.VAR_INT,
			CapFluidIngredient::amount,
			CapFluidIngredient::new);

	public static CapFluidIngredient of(Fluid fluid, int amount) {
		return new CapFluidIngredient(Optional.of(FluidIngredient.of(fluid)), amount);
	}

	public static CapFluidIngredient ofEmpty() {
		return new CapFluidIngredient(Optional.empty(), 1000);
	}

	private final Optional<FluidIngredient> ingredient;
	private final int amount;

	public CapFluidIngredient(Optional<FluidIngredient> ingredient, int amount) {
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public Optional<FluidIngredient> ingredient() {
		return ingredient;
	}

	public int amount() {
		return amount;
	}

	/**
	 * Performs a size-sensitive test on the given stack.
	 *
	 * @return {@code true} if the stack matches the ingredient and has at least the required amount.
	 */
	public boolean test(FluidStack stack) {
		return ingredient().isEmpty() && stack.isEmpty() || ingredient().isPresent() && ingredient.get().test(stack) && stack.getAmount() >= amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CapFluidIngredient other)) return false;
		return amount == other.amount && ingredient.equals(other.ingredient);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredient, amount);
	}

	@Override
	public String toString() {
		return amount + "x " + ingredient;
	}
}
