package baguchan.tofucraft.recipe;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class FluidIngredient implements Predicate<FluidStack> {

	public static final FluidIngredient EMPTY = NullFluidIngredient.EMPTY;

	public List<FluidStack> matchingFluidStacks;

	public static FluidIngredient fromTag(TagKey<Fluid> tag, int amount) {
		FluidTagIngredient ingredient = new FluidTagIngredient();
		ingredient.tag = tag;
		ingredient.amountRequired = amount;
		return ingredient;
	}

	public static FluidIngredient fromFluid(Fluid fluid, int amount) {
		FluidStackIngredient ingredient = new FluidStackIngredient();
		ingredient.fluid = fluid;
		ingredient.amountRequired = amount;
		ingredient.fixFlowing();
		return ingredient;
	}

	public static FluidIngredient fromFluidStack(FluidStack fluidStack) {
		FluidStackIngredient ingredient = new FluidStackIngredient();
		ingredient.fluid = fluidStack.getFluid();
		ingredient.amountRequired = fluidStack.getAmount();
		ingredient.fixFlowing();
		if (fluidStack.hasTag())
			ingredient.tagToMatch = fluidStack.getTag();
		return ingredient;
	}

	protected int amountRequired;

	protected abstract boolean testInternal(FluidStack t);

	protected abstract void readInternal(JsonObject json);

	protected abstract void writeInternal(JsonObject json);

	protected abstract List<FluidStack> determineMatchingFluidStacks();

	public int getRequiredAmount() {
		return amountRequired;
	}

	public List<FluidStack> getMatchingFluidStacks() {
		if (matchingFluidStacks != null)
			return matchingFluidStacks;
		return matchingFluidStacks = determineMatchingFluidStacks();
	}

	@Override
	public boolean test(FluidStack t) {
		if (t == null)
			throw new IllegalArgumentException("FluidStack cannot be null");
		return testInternal(t);
	}

	public JsonObject serialize() {
		JsonObject json = new JsonObject();
		writeInternal(json);
		if (amountRequired != 0)
			json.addProperty("amount", amountRequired);
		return json;
	}

	public static boolean isFluidIngredient(@Nullable JsonElement je) {
		if (je == null || je.isJsonNull())
			return false;
		if (!je.isJsonObject())
			return false;
		JsonObject json = je.getAsJsonObject();
		if (json.has("null_fluid"))
			return true;
		else if (json.has("fluidTag"))
			return true;
		else if (json.has("fluid"))
			return true;
		return false;
	}

	public static FluidIngredient deserialize(@Nullable JsonElement je) {
		if (!isFluidIngredient(je))
			throw new JsonSyntaxException("Invalid fluid ingredient: " + Objects.toString(je));

		JsonObject json = je.getAsJsonObject();
		if (json.has("null_fluid")) {
			if (!json.get("null_fluid").getAsBoolean())
				throw new JsonSyntaxException("'null_fluid' can NOT be false, delete it: " + Objects.toString(je));
			return FluidIngredient.EMPTY;
		}
		FluidIngredient ingredient = json.has("fluidTag") ? new FluidTagIngredient() : new FluidStackIngredient();
		ingredient.readInternal(json);

		if (!json.has("amount"))
			throw new JsonSyntaxException("Fluid ingredient has to define an amount");
		ingredient.amountRequired = GsonHelper.getAsInt(json, "amount");
		return ingredient;
	}

	private static class NullFluidIngredient extends FluidIngredient {
		private static final NullFluidIngredient EMPTY = new NullFluidIngredient();

		private NullFluidIngredient() {
		}

		@Override
		protected boolean testInternal(FluidStack t) {
			return true;
		}

		@Override
		protected void readInternal(JsonObject json) {
		}

		@Override
		protected void writeInternal(JsonObject json) {
			json.addProperty("null_fluid", true);
		}

		@Override
		protected List<FluidStack> determineMatchingFluidStacks() {
			return ImmutableList.of(new FluidStack(Fluids.EMPTY, 0));
		}

	}

	public static class FluidStackIngredient extends FluidIngredient {

		protected Fluid fluid;
		protected CompoundTag tagToMatch;

		public FluidStackIngredient() {
			tagToMatch = new CompoundTag();
		}

		void fixFlowing() {
			if (fluid instanceof FlowingFluid)
				fluid = ((FlowingFluid) fluid).getSource();
		}

		@Override
		protected boolean testInternal(FluidStack t) {
			if (!t.getFluid()
					.isSame(fluid))
				return false;
			if (tagToMatch.isEmpty())
				return true;
			CompoundTag tag = t.getOrCreateTag();
			return tag.copy()
					.merge(tagToMatch)
					.equals(tag);
		}

		@Override
		protected void readInternal(JsonObject json) {
			FluidStack stack = FluidHelper.deserializeFluidStack(json);
			fluid = stack.getFluid();
			tagToMatch = stack.getOrCreateTag();
		}

		@Override
		protected void writeInternal(JsonObject json) {
			json.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(fluid)
					.toString());
			json.add("nbt", JsonParser.parseString(tagToMatch.toString()));
		}

		@Override
		protected List<FluidStack> determineMatchingFluidStacks() {
			return ImmutableList.of(tagToMatch.isEmpty() ? new FluidStack(fluid, amountRequired)
					: new FluidStack(fluid, amountRequired, tagToMatch));
		}

	}

	public static class FluidTagIngredient extends FluidIngredient {

		protected TagKey<Fluid> tag;

		@Override
		protected boolean testInternal(FluidStack t) {
			if (tag == null)
				for (FluidStack accepted : getMatchingFluidStacks())
					if (accepted.getFluid().isSame(t.getFluid()))
						return true;
			return ForgeRegistries.FLUIDS.tags().getReverseTag(t.getFluid()).get().containsTag(tag);
		}

		@Override
		protected void readInternal(JsonObject json) {
			ResourceLocation name = new ResourceLocation(GsonHelper.getAsString(json, "fluidTag"));
			tag = FluidTags.create(name);
		}

		@Override
		protected void writeInternal(JsonObject json) {
			json.addProperty("fluidTag", tag.location().toString());
		}

		@Override
		protected List<FluidStack> determineMatchingFluidStacks() {
			return ForgeRegistries.FLUIDS.tags()
					.getTag(tag)
					.stream()
					.map(f -> {
						if (f instanceof FlowingFluid)
							return ((FlowingFluid) f).getSource();
						return f;
					})
					.distinct()
					.map(f -> new FluidStack(f, amountRequired))
					.collect(Collectors.toList());
		}

	}

}
