package baguchan.tofucraft.recipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import baguchan.tofucraft.registry.TofuRecipes;
import cn.mcmod_mmf.mmlib.recipe.AbstractRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AggregatorRecipe extends AbstractRecipe {
    @Expose
    @SerializedName("ingredient")
    public Ingredient inputItems;
    @Expose()
    @SerializedName("result")
    public ItemStack output;
    
    @Override
    public boolean isSpecial() {
        return true;
    }
    
    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        if (inv.isEmpty())
            return false;
        return inputItems.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(RecipeWrapper p_44001_) {
        return this.output;
    }

    @Override
    public boolean canCraftInDimensions(int u, int v) {
        return u*v >= 1;
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
