package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.inventory.SaltFurnaceMenu;
import baguchan.tofucraft.inventory.TFAggregatorMenu;
import baguchan.tofucraft.inventory.TFStorageMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuContainers {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TofuCraftReload.MODID);


	public static final RegistryObject<MenuType<SaltFurnaceMenu>> SALT_FURNACE = MENU_TYPES.register("salt_furnace", () -> new MenuType<>(SaltFurnaceMenu::new));
	public static final RegistryObject<MenuType<TFStorageMenu>> TF_STORAGE = MENU_TYPES.register("tf_storage", () -> new MenuType<>(TFStorageMenu::new));
	public static final RegistryObject<MenuType<TFAggregatorMenu>> TF_AGGREGATOR = MENU_TYPES.register("tf_aggregator", () -> IForgeMenuType.create(TFAggregatorMenu::new));
}
