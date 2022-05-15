package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.inventory.SaltFurnaceMenu;
import baguchan.tofucraft.inventory.TFAggregatorMenu;
import baguchan.tofucraft.inventory.TFStorageMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuContainers {
	public static final MenuType<SaltFurnaceMenu> SALT_FURNACE = new MenuType<>(SaltFurnaceMenu::new);
	public static final MenuType<TFStorageMenu> TF_STORAGE = new MenuType<>(TFStorageMenu::new);
	public static final MenuType<TFAggregatorMenu> TF_AGGREGATOR = IForgeMenuType.create(TFAggregatorMenu::new);
	@SubscribeEvent
	public static void registerContainer(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().register(SALT_FURNACE.setRegistryName("salt_furnace"));
		event.getRegistry().register(TF_STORAGE.setRegistryName("tf_storage"));
		event.getRegistry().register(TF_AGGREGATOR.setRegistryName("tf_aggregator"));
	}
}
