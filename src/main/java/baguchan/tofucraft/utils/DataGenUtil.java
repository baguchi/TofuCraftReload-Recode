package baguchan.tofucraft.utils;

import baguchan.tofucraft.recipe.AbstractSerializer;
import baguchan.tofucraft.recipe.FluidIngredient;
import baguchan.tofucraft.recipe.NonNullListDeserializer;
import baguchan.tofucraft.recipe.TofuPotCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

public class DataGenUtil {
	public static final Gson DATA_GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting()
			.registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
			.create();
	public static final Gson NETWORK_GSON = new GsonBuilder().disableHtmlEscaping().enableComplexMapKeySerialization()
			.registerTypeAdapter(ItemStack.class, AbstractSerializer.ItemStackSerializer.getInstance())
			.registerTypeAdapter(Ingredient.class, AbstractSerializer.IngredientSerializer.getInstance())
			.registerTypeAdapter(FluidStack.class, AbstractSerializer.FluidStackSerializer.getInstance())
			.registerTypeAdapter(FluidIngredient.class, AbstractSerializer.FluidIngredientSerializer.getInstance())
			.registerTypeAdapter(NonNullList.class, NonNullListDeserializer.getInstance())
			.registerTypeAdapter(TofuPotCategory.class, AbstractSerializer.TofuPotCategorySerializer.getInstance())
			.excludeFieldsWithoutExposeAnnotation().create();

}
