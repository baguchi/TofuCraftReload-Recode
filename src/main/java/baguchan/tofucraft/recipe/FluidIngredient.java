package baguchan.tofucraft.recipe;

import com.google.common.collect.Lists;
import com.google.gson.*;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FluidIngredient implements Predicate<FluidStack> {
	//Because Mojang caches things... we need to invalidate them.. so... here we go..
	private static final java.util.Set<FluidIngredient> INSTANCES = java.util.Collections.newSetFromMap(new java.util.WeakHashMap<FluidIngredient, Boolean>());

	public static void invalidateAll() {
		INSTANCES.stream().filter(e -> e != null).forEach(i -> i.invalidate());
	}

	public static final FluidIngredient EMPTY = new FluidIngredient(Stream.empty());
	private final FluidIngredient.Value[] values;
	private FluidStack[] fluidStacks;
	private IntList stackingIds;

	protected FluidIngredient(Stream<? extends FluidIngredient.Value> p_43907_) {
		this.values = p_43907_.toArray((p_43933_) -> {
			return new FluidIngredient.Value[p_43933_];
		});
		FluidIngredient.INSTANCES.add(this);
	}

	public FluidStack[] getFluids() {
		this.dissolve();
		return this.fluidStacks;
	}

	private void dissolve() {
		if (this.fluidStacks == null) {
			this.fluidStacks = Arrays.stream(this.values).flatMap((p_43916_) -> {
				return p_43916_.getFluids().stream();
			}).distinct().toArray((p_43910_) -> {
				return new FluidStack[p_43910_];
			});
		}

	}

	public boolean test(@Nullable FluidStack p_43914_) {
		if (p_43914_ == null) {
			return false;
		} else {
			this.dissolve();
			if (this.fluidStacks.length == 0) {
				return p_43914_.isEmpty();
			} else {
				for (FluidStack fluidstack : this.fluidStacks) {
					if (fluidstack.containsFluid(p_43914_)) {
						return true;
					}
				}

				return false;
			}
		}
	}


	public final void toNetwork(FriendlyByteBuf p_43924_) {
		this.dissolve();

		p_43924_.writeCollection(Arrays.asList(this.fluidStacks), FriendlyByteBuf::writeFluidStack);
	}

	public JsonElement toJson() {
		if (this.values.length == 1) {
			return this.values[0].serialize();
		} else {
			JsonArray jsonarray = new JsonArray();

			for (FluidIngredient.Value Fluidingredient$value : this.values) {
				jsonarray.add(Fluidingredient$value.serialize());
			}

			return jsonarray;
		}
	}

	public boolean isEmpty() {
		return this.values.length == 0 && (this.fluidStacks == null || this.fluidStacks.length == 0) && (this.stackingIds == null || this.stackingIds.isEmpty());
	}

	protected void invalidate() {
		this.fluidStacks = null;
		this.stackingIds = null;
	}

	public boolean isSimple() {
		return this == EMPTY;
	}

	public static FluidIngredient fromValues(Stream<? extends FluidIngredient.Value> p_43939_) {
		FluidIngredient Fluidingredient = new FluidIngredient(p_43939_);
		return Fluidingredient.values.length == 0 ? EMPTY : Fluidingredient;
	}

	public static FluidIngredient of() {
		return EMPTY;
	}

	public static FluidIngredient of(FluidStack... p_43928_) {
		return of(Arrays.stream(p_43928_));
	}

	public static FluidIngredient of(Stream<FluidStack> p_43922_) {
		return fromValues(p_43922_.filter((p_43944_) -> {
			return !p_43944_.isEmpty();
		}).map(FluidIngredient.FluidValue::new));
	}

	public static FluidIngredient of(TagKey<Fluid> p_43912_) {
		return fromValues(Stream.of(new FluidIngredient.TagValue(p_43912_)));
	}

	public static FluidIngredient fromNetwork(FriendlyByteBuf p_43941_) {
		var size = p_43941_.readVarInt();
		return fromValues(Stream.generate(() -> new FluidIngredient.FluidValue(p_43941_.readFluidStack())).limit(size));
	}

	public static FluidIngredient fromJson(@Nullable JsonElement p_43918_) {
		if (p_43918_ != null && !p_43918_.isJsonNull()) {
			if (p_43918_.isJsonObject()) {
				return fromValues(Stream.of(valueFromJson(p_43918_.getAsJsonObject())));
			} else if (p_43918_.isJsonArray()) {
				JsonArray jsonarray = p_43918_.getAsJsonArray();
				if (jsonarray.size() == 0) {
					throw new JsonSyntaxException("Fluid array cannot be empty, at least one fluid must be defined");
				} else {
					return fromValues(StreamSupport.stream(jsonarray.spliterator(), false).map((p_151264_) -> {
						return valueFromJson(GsonHelper.convertToJsonObject(p_151264_, "fluid"));
					}));
				}
			} else {
				throw new JsonSyntaxException("Expected fluid to be object or array of objects");
			}
		} else {
			throw new JsonSyntaxException("Fluid cannot be null");
		}
	}

	public static FluidStack getFluidStack(JsonObject json, boolean readNBT) {
		String fluidName = GsonHelper.getAsString(json, "fluid");

		Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName));

		if (fluid == null)
			throw new JsonSyntaxException("Unknown fluid '" + fluidName + "'");

		return new FluidStack(fluid, 1000);
	}

	public static FluidIngredient.Value valueFromJson(JsonObject p_43920_) {
		if (p_43920_.has("fluid") && p_43920_.has("tag")) {
			throw new JsonParseException("An Fluidingredient entry is either a tag or an fluid, not both");
		} else if (p_43920_.has("fluid")) {
			Fluid fluid = fluidFromJson(p_43920_);
			return new FluidIngredient.FluidValue(fluid);
		} else if (p_43920_.has("tag")) {
			ResourceLocation resourcelocation = new ResourceLocation(GsonHelper.getAsString(p_43920_, "tag"));
			TagKey<Fluid> tagkey = TagKey.create(Registry.FLUID_REGISTRY, resourcelocation);
			return new FluidIngredient.TagValue(tagkey);
		} else {
			throw new JsonParseException("An Fluidingredient entry needs either a tag or an fluid");
		}
	}

	public static Fluid fluidFromJson(JsonObject p_151279_) {
		String s = GsonHelper.getAsString(p_151279_, "fluid");
		Fluid fluid = Registry.FLUID.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
			return new JsonSyntaxException("Unknown fluid '" + s + "'");
		});
		if (fluid == Fluids.EMPTY) {
			throw new JsonSyntaxException("Invalid fluid: " + s);
		} else {
			return fluid;
		}
	}

	//Merges several vanilla FluidIngredients together. As a quirk of how the json is structured, we can't tell if its a single FluidIngredient type or multiple so we split per fluid and re-merge here.
	//Only public for internal use, so we can access a private field in here.
	public static FluidIngredient merge(Collection<FluidIngredient> parts) {
		return fromValues(parts.stream().flatMap(i -> Arrays.stream(i.values)));
	}

	public static class FluidValue implements FluidIngredient.Value {
		private final Fluid fluid;

		public FluidValue(FluidStack p_43953_) {
			this.fluid = p_43953_.getFluid();
		}

		public FluidValue(Fluid p_43953_) {
			this.fluid = p_43953_;
		}

		public Collection<Fluid> getFluids() {
			return Collections.singleton(this.fluid);
		}

		public JsonObject serialize() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("fluid", Registry.FLUID.getKey(this.fluid).toString());
			return jsonobject;
		}
	}

	public static class TagValue implements FluidIngredient.Value {
		private final TagKey<Fluid> tag;

		public TagValue(TagKey<Fluid> p_43961_) {
			this.tag = p_43961_;
		}

		public Collection<Fluid> getFluids() {
			List<Fluid> list = Lists.newArrayList();

			for (Holder<Fluid> holder : Registry.FLUID.getTagOrEmpty(this.tag)) {
				list.add(holder.value());
			}

			return list;
		}

		public JsonObject serialize() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("tag", this.tag.location().toString());
			return jsonobject;
		}
	}

	public interface Value {
		Collection<Fluid> getFluids();

		JsonObject serialize();
	}
}
