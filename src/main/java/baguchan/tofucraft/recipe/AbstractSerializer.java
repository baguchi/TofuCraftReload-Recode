package baguchan.tofucraft.recipe;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Type;

public abstract class AbstractSerializer<T> implements JsonSerializer<T>, JsonDeserializer<T> {
	public static class ItemStackSerializer extends AbstractSerializer<ItemStack> {
		private static final ItemStackSerializer INSTANCE = new ItemStackSerializer();

		private ItemStackSerializer() {
		}

		@Override
		public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject objectResult = new JsonObject();
			objectResult.addProperty("item", ForgeRegistries.ITEMS.getKey(src.getItem()).toString());
			if (src.getCount() > 1) {
				objectResult.addProperty("count", src.getCount());
			}
			if (src.hasTag()) {
				objectResult.add("nbt", JsonParser.parseString(src.getTag().toString()));
			}
			return objectResult;
		}

		@Override
		public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return CraftingHelper.getItemStack(json.getAsJsonObject(), true);
		}

		public static ItemStackSerializer getInstance() {
			return INSTANCE;
		}
	}

	public static class IngredientSerializer extends AbstractSerializer<Ingredient> {
		private static final IngredientSerializer INSTANCE = new IngredientSerializer();

		private IngredientSerializer() {
		}

		@Override
		public JsonElement serialize(Ingredient src, Type typeOfSrc, JsonSerializationContext context) {
			return src.toJson();
		}

		@Override
		public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return Ingredient.fromJson(json);
		}

		public static IngredientSerializer getInstance() {
			return INSTANCE;
		}
	}

	public static class TofuPotCategorySerializer extends AbstractSerializer<TofuPotCategory> {
		private static final TofuPotCategorySerializer INSTANCE = new TofuPotCategorySerializer();

		private TofuPotCategorySerializer() {
		}

		@Override
		public JsonElement serialize(TofuPotCategory src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject json = new JsonObject();
			json.addProperty("category", src.getSerializedName());
			return json;
		}

		@Override
		public TofuPotCategory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return TofuPotCategory.get(GsonHelper.getAsString(json.getAsJsonObject(), "category"));
		}

		public static TofuPotCategorySerializer getInstance() {
			return INSTANCE;
		}
	}

	public static class FluidStackSerializer extends AbstractSerializer<FluidStack> {
		private static final FluidStackSerializer INSTANCE = new FluidStackSerializer();

		private FluidStackSerializer() {
		}

		@Override
		public JsonElement serialize(FluidStack src, Type typeOfSrc, JsonSerializationContext context) {
			return FluidHelper.serializeFluidStack(src);
		}

		@Override
		public FluidStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return FluidHelper.deserializeFluidStack(json.getAsJsonObject());
		}

		public static FluidStackSerializer getInstance() {
			return INSTANCE;
		}
	}

	public static class FluidIngredientSerializer extends AbstractSerializer<FluidIngredient> {
		private static final FluidIngredientSerializer INSTANCE = new FluidIngredientSerializer();

		private FluidIngredientSerializer() {
		}

		@Override
		public JsonElement serialize(FluidIngredient src, Type typeOfSrc, JsonSerializationContext context) {
			return src.serialize();
		}

		@Override
		public FluidIngredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return FluidIngredient.deserialize(json);
		}

		public static FluidIngredientSerializer getInstance() {
			return INSTANCE;
		}
	}
}
