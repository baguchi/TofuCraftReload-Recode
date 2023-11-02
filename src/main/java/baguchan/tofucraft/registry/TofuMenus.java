package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.inventory.SaltFurnaceMenu;
import baguchan.tofucraft.inventory.TFStorageMenu;
import baguchan.tofucraft.inventory.TofuWorkStationMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuMenus {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TofuCraftReload.MODID);


	public static final RegistryObject<MenuType<SaltFurnaceMenu>> SALT_FURNACE = MENU_TYPES.register("salt_furnace", () -> new MenuType<>(SaltFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));

	public static final RegistryObject<MenuType<TFStorageMenu>> TF_STORAGE = MENU_TYPES.register("tf_storage", () -> new MenuType<>(TFStorageMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final RegistryObject<MenuType<TofuWorkStationMenu>> TOFU_WORK_STATION = MENU_TYPES.register("tofu_work_station", () -> new MenuType<>(TofuWorkStationMenu::new, FeatureFlags.DEFAULT_FLAGS));

}
