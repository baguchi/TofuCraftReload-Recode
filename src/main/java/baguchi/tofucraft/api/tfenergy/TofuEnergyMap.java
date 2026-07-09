package baguchi.tofucraft.api.tfenergy;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidStackTemplate;

import java.util.HashMap;
import java.util.Map;

public class TofuEnergyMap {
	private static HashMap<ItemStackTemplate, Integer> recipes = new HashMap<>();
	private static HashMap<FluidStackTemplate, Integer> fluidRecipes = new HashMap<>();

	public static void init() {
		register(new ItemStackTemplate(TofuItems.TOFU_KINU, 1), 100);
		register(new ItemStackTemplate(TofuItems.TOFU_MOMEN, 1), 100);
		register(new ItemStackTemplate(TofuItems.TOFU_ISHI, 1), 100);
		register(new ItemStackTemplate(TofuItems.TOFU_EGG, 1), 120);
		register(new ItemStackTemplate(TofuItems.TOFU_ZUNDA, 1), 200);
		register(new ItemStackTemplate(TofuItems.TOFU_DRIED, 1), 120);
		register(new ItemStackTemplate(TofuItems.TOFU_GRILLED, 1), 120);
		register(new ItemStackTemplate(TofuItems.TOFU_MISO, 1), 200);
		register(new ItemStackTemplate(TofuItems.TOFU_MINCED, 1), 100);
		register(new ItemStackTemplate(TofuItems.TOFU_HELL, 1), 200);
		register(new ItemStackTemplate(TofuItems.TOFU_SOUL, 1), 250);
		register(new ItemStackTemplate(TofuBlocks.KINUTOFU.asItem(), 1), 400);
		register(new ItemStackTemplate(TofuBlocks.MOMENTOFU.asItem(), 1), 400);
		register(new ItemStackTemplate(TofuBlocks.ISHITOFU.asItem(), 1), 400);
		register(new ItemStackTemplate(TofuBlocks.EGGTOFU.asItem(), 1), 450);
		register(new ItemStackTemplate(TofuBlocks.ZUNDATOFU.asItem(), 1), 800);
		register(new ItemStackTemplate(TofuBlocks.HELLTOFU.asItem(), 1), 800);
		register(new ItemStackTemplate(TofuBlocks.SOULTOFU.asItem(), 1), 900);
		register(new ItemStackTemplate(TofuItems.SEEDS_SOYBEANS, 1), 400);
		register(new ItemStackTemplate(TofuItems.SOYBEAN_PARCHED, 1), 400);
		register(new FluidStackTemplate(TofuFluids.SOYMILK, 100), 400);
		register(new FluidStackTemplate(TofuFluids.SOYMILK_HELL, 100), 60);
		register(new FluidStackTemplate(TofuFluids.SOYMILK_SOUL, 100), 80);
	}

	public static void register(ItemStackTemplate item, int loader) {
		recipes.put(item, loader);
	}

	public static void register(FluidStackTemplate fluid, int loader) {
		fluidRecipes.put(fluid, loader);
	}

	public static int getFuel(ItemStack item) {
		for (ItemStackTemplate rep : recipes.keySet()) {
			if (rep.is(item.getItem())) {
				return recipes.get(rep);
			}
		}
		return -1;
	}

	public static Map.Entry<FluidStackTemplate, Integer> getLiquidFuel(FluidStack fluid) {
		for (Map.Entry<FluidStackTemplate, Integer> rep : fluidRecipes.entrySet()) {
			if (rep.getKey().fluid().equals(fluid.getFluid()))
				return rep;
		}
		return null;
	}
}