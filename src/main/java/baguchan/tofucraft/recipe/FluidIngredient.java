package baguchan.tofucraft.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FluidIngredient implements Predicate<FluidStack> {
	private static final java.util.concurrent.atomic.AtomicInteger INVALIDATION_COUNTER = new java.util.concurrent.atomic.AtomicInteger();
	public static void invalidateAll() {
		INVALIDATION_COUNTER.incrementAndGet();
	}

	public static final FluidIngredient EMPTY = new FluidIngredient(Stream.empty());
	private FluidIngredient.Value[] values;
	@Nullable
	private FluidStack[] fluidStack;
	@Nullable
	private IntList stackingIds;
	public static final Codec<FluidIngredient> CODEC = codec(true);
	public static final Codec<FluidIngredient> CODEC_NONEMPTY = codec(false);
	private int invalidationCounter;

	protected FluidIngredient(Stream<? extends FluidIngredient.Value> p_43907_) {
		this.values = p_43907_.toArray((p_43933_) -> {
			return new FluidIngredient.Value[p_43933_];
		});
	}

	private FluidIngredient(FluidIngredient.Value[] p_301101_) {
		this.values = p_301101_;
	}

	public FluidStack[] getFluidStacks() {
		if (this.fluidStack == null) {
			this.fluidStack = Arrays.stream(this.values).flatMap((p_43916_) -> {
				return p_43916_.getFluids().stream();
			}).distinct().toArray((p_43910_) -> {
				return new FluidStack[p_43910_];
			});
		}

		return this.fluidStack;
	}

	public boolean test(@Nullable FluidStack p_43914_) {
		if (p_43914_ == null) {
			return false;
		} else if (this.isEmpty()) {
			return p_43914_.isEmpty();
		} else {
			for (FluidStack fluidStack : this.getFluidStacks()) {
				if (fluidStack.getFluid() == p_43914_.getFluid()) {
					return true;
				}
			}

			return false;
		}
	}

	public IntList getStackingIds() {
		if (this.stackingIds == null || checkInvalidation()) {
			this.markValid();
			FluidStack[] aitemstack = this.getFluidStacks();
			this.stackingIds = new IntArrayList(aitemstack.length);

			for (FluidStack fluid : aitemstack) {
				this.stackingIds.add(getStackingIndex(fluid));
			}

			this.stackingIds.sort(IntComparators.NATURAL_COMPARATOR);
		}

		return this.stackingIds;
	}


	public static int getStackingIndex(FluidStack p_36497_) {
		return BuiltInRegistries.FLUID.getId(p_36497_.getFluid());
	}
	public final void toNetwork(FriendlyByteBuf p_43924_) {
      /*
      if (!this.isVanilla()) {
         net.minecraftforge.common.crafting.CraftingHelper.write(p_43924_, this);
         return;
      }
      */
		p_43924_.writeCollection(Arrays.asList(this.getFluidStacks()), FriendlyByteBuf::writeFluidStack);
	}

	public JsonElement toJson(boolean p_299391_) {
		Codec<FluidIngredient> codec = p_299391_ ? CODEC : CODEC_NONEMPTY;
		return Util.getOrThrow(codec.encodeStart(JsonOps.INSTANCE, this), IllegalStateException::new);
	}

	public boolean isEmpty() {
		return this.values.length == 0;
	}

	public boolean equals(Object p_300457_) {
		if (p_300457_ instanceof FluidIngredient ingredient) {
			return Arrays.equals((Object[]) this.values, (Object[]) ingredient.values);
		} else {
			return false;
		}
	}

	public final boolean checkInvalidation() {
		int currentInvalidationCounter = INVALIDATION_COUNTER.get();
		if (this.invalidationCounter != currentInvalidationCounter) {
			invalidate();
			return true;
		}
		return false;
	}

	protected final void markValid() {
		this.invalidationCounter = INVALIDATION_COUNTER.get();
	}

	protected void invalidate() {
		this.fluidStack = null;
		this.stackingIds = null;
	}

	public boolean isSimple() {
		return true;
	}

	private final boolean isVanilla = this.getClass() == FluidIngredient.class;

	public final boolean isVanilla() {
		return isVanilla;
	}

   /*
   public net.minecraftforge.common.crafting.IIngredientSerializer<? extends Ingredient> getSerializer() {
      if (!isVanilla()) throw new IllegalStateException("Modders must implement Ingredient.getSerializer in their custom Ingredients: " + this);
      return net.minecraftforge.common.crafting.VanillaIngredientSerializer.INSTANCE;
   }
   */

	public static FluidIngredient fromValues(Stream<? extends FluidIngredient.Value> p_43939_) {
		FluidIngredient ingredient = new FluidIngredient(p_43939_);
		return ingredient.isEmpty() ? EMPTY : ingredient;
	}

	public static FluidIngredient of() {
		return EMPTY;
	}

	public static FluidIngredient of(Fluid... p_43930_) {
		return of(Arrays.stream(p_43930_).map(fluid -> {
			return new FluidStack(fluid, 1000);
		}));
	}

	public static FluidIngredient of(FluidStack... p_43928_) {
		return of(Arrays.stream(p_43928_));
	}

	public static FluidIngredient of(Stream<FluidStack> p_43922_) {
		return fromValues(p_43922_.filter((p_43944_) -> {
			return !p_43944_.isEmpty();
		}).map(FluidIngredient.FluidValue::new));
	}

	public static FluidIngredient of(TagKey<Fluid> p_204133_) {
		return fromValues(Stream.of(new FluidIngredient.TagValue(p_204133_)));
	}

	public static FluidIngredient fromNetwork(FriendlyByteBuf p_43941_) {
		var size = p_43941_.readVarInt();
		//if (size == -1) return net.minecraftforge.common.crafting.CraftingHelper.getIngredient(p_43941_.readResourceLocation(), p_43941_);
		return fromValues(Stream.generate(() -> new FluidIngredient.FluidValue(p_43941_.readFluidStack())).limit(size));
	}

	private static Codec<FluidIngredient> codec(boolean p_298496_) {
		Codec<FluidIngredient.Value[]> codec = Codec.list(FluidIngredient.Value.CODEC).comapFlatMap((p_296902_) -> {
			return !p_298496_ && p_296902_.size() < 1 ? DataResult.error(() -> {
				return "Item array cannot be empty, at least one item must be defined";
			}) : DataResult.success(p_296902_.toArray(new FluidIngredient.Value[0]));
		}, List::of);
		return ExtraCodecs.either(codec, FluidIngredient.Value.CODEC).flatComapMap((p_296900_) -> {
			return p_296900_.map(FluidIngredient::new, (p_296903_) -> {
				return new FluidIngredient(new FluidIngredient.Value[]{p_296903_});
			});
		}, (p_296899_) -> {
			if (p_296899_.values.length == 1) {
				return DataResult.success(Either.right(p_296899_.values[0]));
			} else {
				return p_296899_.values.length == 0 && !p_298496_ ? DataResult.error(() -> {
					return "Item array cannot be empty, at least one item must be defined";
				}) : DataResult.success(Either.left(p_296899_.values));
			}
		});
	}

	//Merges several vanilla FluidIngredients together. As a quirk of how the json is structured, we can't tell if its a single FluidIngredient type or multiple so we split per fluid and re-merge here.
	//Only public for internal use, so we can access a private field in here.
	public static FluidIngredient merge(Collection<FluidIngredient> parts) {
		return fromValues(parts.stream().flatMap(i -> Arrays.stream(i.values)));
	}

	public static record FluidValue(FluidStack fluidStack) implements FluidIngredient.Value {

		static final Codec<FluidStack> FLUID_CODEC = ExtraCodecs.validate(BuiltInRegistries.FLUID.byNameCodec(), (p_300046_) -> {
			return p_300046_ == Fluids.EMPTY ? DataResult.error(() -> {
				return "Empty ingredient not allowed here";
			}) : DataResult.success(p_300046_);
		}).xmap(fluid -> {
			return new FluidStack(fluid, 1000);
		}, FluidStack::getFluid);
		static final Codec<FluidIngredient.FluidValue> CODEC = RecordCodecBuilder.create((p_300421_) -> {
			return p_300421_.group(FLUID_CODEC.fieldOf("fluid").forGetter((p_299657_) -> {
				return p_299657_.fluidStack;
			})).apply(p_300421_, FluidIngredient.FluidValue::new);
		});

		public boolean equals(Object p_300135_) {
			if (!(p_300135_ instanceof FluidIngredient.FluidValue ingredient$itemvalue)) {
				return false;
			} else {
				return ingredient$itemvalue.fluidStack.getFluid().equals(this.fluidStack.getFluid()) && ingredient$itemvalue.fluidStack.getAmount() == this.fluidStack.getAmount();
			}
		}

		public Collection<FluidStack> getFluids() {
			return Collections.singleton(this.fluidStack);
		}
	}

	public static record TagValue(TagKey<Fluid> tag) implements FluidIngredient.Value {
		static final Codec<FluidIngredient.TagValue> CODEC = RecordCodecBuilder.create((p_300241_) -> {
			return p_300241_.group(TagKey.codec(Registries.FLUID).fieldOf("tag").forGetter((p_301340_) -> {
				return p_301340_.tag;
			})).apply(p_300241_, FluidIngredient.TagValue::new);
		});

		public boolean equals(Object p_298268_) {
			if (p_298268_ instanceof Ingredient.TagValue ingredient$tagvalue) {
				return ingredient$tagvalue.tag().location().equals(this.tag.location());
			} else {
				return false;
			}
		}

		public Collection<FluidStack> getFluids() {
			List<FluidStack> list = Lists.newArrayList();

			for (Holder<Fluid> holder : BuiltInRegistries.FLUID.getTagOrEmpty(this.tag)) {
				list.add(new FluidStack(holder.value(), 1000));
			}

			if (list.size() == 0) {
				list.add(new FluidStack(Fluids.EMPTY, 0));
			}
			return list;
		}
	}

	public interface Value {
		Codec<FluidIngredient.Value> CODEC = ExtraCodecs.xor(FluidValue.CODEC, FluidIngredient.TagValue.CODEC).xmap((p_300070_) -> {
			return p_300070_.map((p_301348_) -> {
				return p_301348_;
			}, (p_298354_) -> {
				return p_298354_;
			});
		}, (p_299608_) -> {
			if (p_299608_ instanceof FluidIngredient.TagValue ingredient$tagvalue) {
				return Either.right(ingredient$tagvalue);
			} else if (p_299608_ instanceof FluidIngredient.FluidValue ingredient$itemvalue) {
				return Either.left(ingredient$itemvalue);
			} else {
				throw new UnsupportedOperationException("This is neither an item value nor a tag value.");
			}
		});
		Collection<FluidStack> getFluids();
	}
}
