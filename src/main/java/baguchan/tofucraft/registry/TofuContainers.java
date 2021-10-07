package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.inventory.SaltFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuContainers {
	public static final MenuType<SaltFurnaceMenu> SALT_FURNACE = new MenuType<>(SaltFurnaceMenu::new);

	@SubscribeEvent
	public static void registerContainer(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().register(SALT_FURNACE.setRegistryName("salt_furnace"));
	}
}
