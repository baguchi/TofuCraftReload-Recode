package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class AggregatorRecipe implements Recipe<Inventory> {

    protected final ResourceLocation id;
    @Expose
    @SerializedName("ingredient")
    public final Ingredient inputItems;
    @Expose()
    @SerializedName("result")
    public final ItemStack output;

    public final int recipeTime;

    public AggregatorRecipe(ResourceLocation id, Ingredient ingredient, ItemStack results, int recipeTime) {

        this.id = id;
        this.inputItems = ingredient;
        this.output = results;
        this.recipeTime = recipeTime;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public boolean matches(Inventory inv, Level worldIn) {
        if (inv.isEmpty())
            return false;
        return inputItems.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(Inventory p_44001_) {
        return this.output;
    }

    @Override
    public boolean canCraftInDimensions(int u, int v) {
        return u * v >= 1;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TofuRecipes.RECIPE_AGGREGATOR.get();
    }

    @Override
    public RecipeType<?> getType() {
        return TofuRecipes.RECIPETYPE_AGGREGATOR;
    }

}
