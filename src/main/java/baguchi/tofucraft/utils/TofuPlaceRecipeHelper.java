package baguchi.tofucraft.utils;

import baguchi.tofucraft.recipe.TFShapedRecipe;
import net.minecraft.recipebook.PlaceRecipeHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Iterator;

public class TofuPlaceRecipeHelper {
	static <T> void placeRecipe(int p_380087_, int p_379836_, Recipe<?> p_379573_, Iterable<T> p_379986_, PlaceRecipeHelper.Output<T> p_380252_) {
		if (p_379573_ instanceof TFShapedRecipe shapedrecipe) {
			placeRecipe(p_380087_, p_379836_, shapedrecipe.getWidth(), shapedrecipe.getHeight(), p_379986_, p_380252_);
		} else {
			placeRecipe(p_380087_, p_379836_, p_380087_, p_379836_, p_379986_, p_380252_);
		}
	}

	static <T> void placeRecipe(int p_362769_, int p_364202_, int p_380112_, int p_379800_, Iterable<T> p_360986_, PlaceRecipeHelper.Output<T> p_362411_) {
		Iterator<T> iterator = p_360986_.iterator();
		int i = 0;

		for (int j = 0; j < p_364202_; j++) {
			boolean flag = p_379800_ < p_364202_ / 2.0F;
			int k = Mth.floor(p_364202_ / 2.0F - p_379800_ / 2.0F);
			if (flag && k > j) {
				i += p_362769_;
				j++;
			}

			for (int l = 0; l < p_362769_; l++) {
				if (!iterator.hasNext()) {
					return;
				}

				flag = p_380112_ < p_362769_ / 2.0F;
				k = Mth.floor(p_362769_ / 2.0F - p_380112_ / 2.0F);
				int i1 = p_380112_;
				boolean flag1 = l < p_380112_;
				if (flag) {
					i1 = k + p_380112_;
					flag1 = k <= l && l < k + p_380112_;
				}

				if (flag1) {
					p_362411_.addItemToSlot(iterator.next(), i, l, j);
				} else if (i1 == l) {
					i += p_362769_ - l;
					break;
				}

				i++;
			}
		}
	}
}
