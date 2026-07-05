package baguchi.tofucraft.recipe;

import baguchi.tofucraft.item.tool.TofuMetalBucketItem;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuItems;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public class BucketToBottleRecipe extends CustomRecipe {
	public static final MapCodec<BucketToBottleRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
			i -> i.group(ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result),
							BuiltInRegistries.FLUID.holderByNameCodec().fieldOf("fluid").forGetter(o -> o.fluid))
					.apply(i, BucketToBottleRecipe::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, BucketToBottleRecipe> STREAM_CODEC = new StreamCodec<RegistryFriendlyByteBuf, BucketToBottleRecipe>(
	) {
		@Override
		public BucketToBottleRecipe decode(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
			ItemStackTemplate stackTemplate = registryFriendlyByteBuf.readLenientJsonWithCodec(ItemStackTemplate.CODEC);
			Holder<Fluid> fluidHolder = registryFriendlyByteBuf.readLenientJsonWithCodec(BuiltInRegistries.FLUID.holderByNameCodec());


			return new BucketToBottleRecipe(stackTemplate, fluidHolder);
		}

		@Override
		public void encode(RegistryFriendlyByteBuf registryFriendlyByteBuf, BucketToBottleRecipe bucketToBottleRecipe) {
			registryFriendlyByteBuf.writeJsonWithCodec(ItemStackTemplate.CODEC, bucketToBottleRecipe.result);
			registryFriendlyByteBuf.writeJsonWithCodec(BuiltInRegistries.FLUID.holderByNameCodec(), bucketToBottleRecipe.fluid);
		}
	};
	public static final RecipeSerializer<BucketToBottleRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
	private final ItemStackTemplate result;
	private final Holder<Fluid> fluid;

	public BucketToBottleRecipe(ItemStackTemplate result, Holder<Fluid> fluid) {
		this.result = result;
		this.fluid = fluid;
	}

	public ItemStackTemplate getResult() {
		return result;
	}

	public Holder<Fluid> getFluid() {
		return fluid;
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (input.ingredientCount() != 4) {
			return false;
		}

		boolean hasBucket = false;
		int bottleCount = 0;
		for (int slot = 0; slot < input.size(); slot++) {
			ItemStack itemStack = input.getItem(slot);
			if (!itemStack.isEmpty()) {
				if (!itemStack.is(Items.GLASS_BOTTLE) && !(itemStack.getItem() instanceof TofuMetalBucketItem banner)) {
					return false;
				}

				if (itemStack.is(Items.GLASS_BOTTLE)) {
					if (bottleCount >= 3) {
						return false;
					}

					bottleCount++;
				} else if (itemStack.has(TofuDataComponents.STORED_FLUID) && itemStack.get(TofuDataComponents.STORED_FLUID).matches(new FluidStack(this.fluid, 1000))) {
					if (hasBucket) {
						return false;
					}

					hasBucket = true;
				}
			}
		}

		return bottleCount == 3 && hasBucket;
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		return this.result.create();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
		NonNullList<ItemStack> result = NonNullList.withSize(input.size(), ItemStack.EMPTY);

		for (int slot = 0; slot < result.size(); ++slot) {
			ItemStack itemStack = input.getItem(slot);
			if (!itemStack.isEmpty()) {
				if (itemStack.is(TofuItems.TOFU_METAL_BUCKET)) {
					result.set(slot, TofuItems.TOFU_METAL_BUCKET.get().getDefaultInstance());
				}
			}
		}

		return result;
	}

	@Override
	public RecipeSerializer<BucketToBottleRecipe> getSerializer() {
		return SERIALIZER;
	}
}
