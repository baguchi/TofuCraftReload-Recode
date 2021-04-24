package baguchan.tofucraft.registry;

import baguchan.tofucraft.inventory.SaltFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
public class TofuContainers {
	public static final ContainerType<SaltFurnaceContainer> SALT_FURNACE = new ContainerType<>(SaltFurnaceContainer::new);

	@SubscribeEvent
	public static void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(SALT_FURNACE.setRegistryName("salt_furnace"));
	}
}
