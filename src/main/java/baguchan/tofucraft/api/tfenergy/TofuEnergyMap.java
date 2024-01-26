package baguchan.tofucraft.api.tfenergy;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Map;

public class TofuEnergyMap {
	private static HashMap<ItemStack, Integer> recipes = new HashMap<>();
	private static HashMap<FluidStack, Integer> fluidRecipes = new HashMap<>();

	public static void init() {
		register(new ItemStack(TofuItems.TOFUKINU.get(), 1), 100);
		register(new ItemStack(TofuItems.TOFUMOMEN.get(), 1), 100);
		register(new ItemStack(TofuItems.TOFUISHI.get(), 1), 100);
		register(new ItemStack(TofuItems.TOFUEGG.get(), 1), 120);
		register(new ItemStack(TofuItems.TOFUZUNDA.get(), 1), 200);
		register(new ItemStack(TofuItems.TOFUDRIED.get(), 1), 120);
		register(new ItemStack(TofuItems.TOFUGRILLED.get(), 1), 120);
		register(new ItemStack(TofuItems.TOFUMISO.get(), 1), 200);
		register(new ItemStack(TofuItems.TOFU_MINCED.get(), 1), 100);
		register(new ItemStack(TofuItems.TOFUHELL.get(), 1), 200);
		register(new ItemStack(TofuItems.TOFUSOUL.get(), 1), 250);
		register(new ItemStack(TofuBlocks.KINUTOFU.get(), 1), 400);
		register(new ItemStack(TofuBlocks.MOMENTOFU.get(), 1), 400);
		register(new ItemStack(TofuBlocks.ISHITOFU.get(), 1), 400);
		register(new ItemStack(TofuBlocks.EGGTOFU.get(), 1), 450);
		register(new ItemStack(TofuBlocks.ZUNDATOFU.get(), 1), 800);
		register(new ItemStack(TofuBlocks.HELLTOFU.get(), 1), 800);
		register(new ItemStack(TofuBlocks.SOULTOFU.get(), 1), 900);
		register(new ItemStack(TofuItems.SEEDS_SOYBEANS.get(), 1), 400);
		register(new ItemStack(TofuItems.SOYBEAN_PARCHED.get(), 1), 400);
		register(new FluidStack(TofuFluids.SOYMILK.get(), 100), 400);
		register(new FluidStack(TofuFluids.SOYMILK_HELL.get(), 100), 60);
		register(new FluidStack(TofuFluids.SOYMILK_SOUL.get(), 100), 80);
	}

	public static void register(ItemStack item, int loader) {
		recipes.put(item, loader);
	}

	public static void register(FluidStack fluid, int loader) {
		fluidRecipes.put(fluid, loader);
	}

	public static int getFuel(ItemStack item) {
		for (ItemStack rep : recipes.keySet()) {
			if (rep.is(item.getItem())) {
				return recipes.get(rep);
			}
		}
		return -1;
	}

	public static Map.Entry<FluidStack, Integer> getLiquidFuel(FluidStack fluid) {
		for (Map.Entry<FluidStack, Integer> rep : fluidRecipes.entrySet()) {
			if (rep.getKey().getFluid().equals(fluid.getFluid()))
				return rep;
		}
		return null;
	}
}