package baguchi.tofucraft.recipe;

import baguchi.tofucraft.item.tool.bucket.TofuMetalBucketItem;
import baguchi.tofucraft.registry.TofuDataComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.SimpleFluidContent;

public class FluidBucketRecipe extends CustomRecipe {
	public static final MapCodec<FluidBucketRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
			i -> i.group(Ingredient.CODEC.fieldOf("ingredient").forGetter(o -> o.ingredient)
							, BuiltInRegistries.FLUID.holderByNameCodec().fieldOf("fluid").forGetter(o -> o.fluid))
					.apply(i, (ingredient1, fluidHolder) -> new FluidBucketRecipe(ingredient1, fluidHolder))
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, FluidBucketRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, o -> o.ingredient
			, ByteBufCodecs.holderRegistry(Registries.FLUID)
			, o -> o.fluid, FluidBucketRecipe::new
	);
	public static final RecipeSerializer<FluidBucketRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
	private final Ingredient ingredient;
	private final Holder<Fluid> fluid;

	public FluidBucketRecipe(Ingredient ingredient, Holder<Fluid> fluid) {
		this.ingredient = ingredient;
		this.fluid = fluid;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (input.ingredientCount() != 2) {
			return false;
		}

		DyeColor color = null;
		boolean hasBucket = false;
		boolean hasSource = false;

		for (int slot = 0; slot < input.size(); slot++) {
			ItemStack itemStack = input.getItem(slot);
			if (!itemStack.isEmpty()) {
				if (!this.ingredient.test(itemStack) && !(itemStack.getItem() instanceof TofuMetalBucketItem banner)) {
					return false;
				}

				if (ingredient.test(itemStack)) {
					if (hasSource) {
						return false;
					}

					hasSource = true;
				} else if (itemStack.has(TofuDataComponents.STORED_FLUID) && itemStack.get(TofuDataComponents.STORED_FLUID).isEmpty()) {
					if (hasBucket) {
						return false;
					}

					hasBucket = true;
				}
			}
		}

		return hasSource && hasBucket;
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		for (int slot = 0; slot < input.size(); slot++) {
			ItemStack itemStack = input.getItem(slot);
			ItemStack bucket = ItemStack.EMPTY;
			if (itemStack.has(TofuDataComponents.STORED_FLUID) && itemStack.get(TofuDataComponents.STORED_FLUID).isEmpty()) {
				bucket = itemStack.copy();
			}

			if (!bucket.isEmpty()) {
				bucket.set(TofuDataComponents.STORED_FLUID, SimpleFluidContent.copyOf(new FluidStack(this.fluid.value(), 1000)));
				return bucket;

			}
		}

		return ItemStack.EMPTY;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public Holder<Fluid> getFluid() {
		return fluid;
	}

	@Override
	public RecipeSerializer<FluidBucketRecipe> getSerializer() {
		return SERIALIZER;
	}
}
