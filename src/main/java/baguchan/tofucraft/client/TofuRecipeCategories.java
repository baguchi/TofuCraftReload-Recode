package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuRecipeBookTypes;
import baguchan.tofucraft.registry.TofuRecipes;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class TofuRecipeCategories {
	public static final Supplier<RecipeBookCategories> WORK_STATION_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("TOFU_WORKSTATION_SEARCH", new ItemStack(Items.COMPASS)));
	public static final Supplier<RecipeBookCategories> WORK_STATION_TF_MECH = Suppliers.memoize(() -> RecipeBookCategories.create("TOFU_WORKSTATION_TF_MECH", new ItemStack(TofuBlocks.TF_OVEN.get())));

	@SubscribeEvent
	public static void registerRecipeCategories(RegisterRecipeBookCategoriesEvent event) {
		event.registerBookCategories(TofuRecipeBookTypes.WORK_STATION, ImmutableList.of(WORK_STATION_SEARCH.get(), WORK_STATION_TF_MECH.get()));
		event.registerAggregateCategory(WORK_STATION_SEARCH.get(), ImmutableList.of(WORK_STATION_TF_MECH.get()));
		event.registerRecipeCategoryFinder(TofuRecipes.RECIPETYPE_TOFU_WORK_STATION.get(), recipe -> WORK_STATION_TF_MECH.get());
	}
}