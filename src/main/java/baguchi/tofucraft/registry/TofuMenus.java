package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.inventory.SaltFurnaceMenu;
import baguchi.tofucraft.inventory.TFCrafterMenu;
import baguchi.tofucraft.inventory.TFOvenMenu;
import baguchi.tofucraft.inventory.TFStorageMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuMenus {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, TofuCraftReload.MODID);


	public static final Supplier<MenuType<SaltFurnaceMenu>> SALT_FURNACE = MENU_TYPES.register("salt_furnace", () -> new MenuType<>(SaltFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));

	public static final Supplier<MenuType<TFStorageMenu>> TF_STORAGE = MENU_TYPES.register("tf_storage", () -> new MenuType<>(TFStorageMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final Supplier<MenuType<TFCrafterMenu>> TF_CRAFTER = MENU_TYPES.register("tf_crafter", () -> new MenuType<>(TFCrafterMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final Supplier<MenuType<TFOvenMenu>> TF_OVEN = MENU_TYPES.register("tf_oven", () -> new MenuType<>(TFOvenMenu::new, FeatureFlags.DEFAULT_FLAGS));

}
