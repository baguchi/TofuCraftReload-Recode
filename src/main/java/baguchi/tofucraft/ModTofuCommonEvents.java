package baguchi.tofucraft;

import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.utils.TankWrapper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class ModTofuCommonEvents {

	@SubscribeEvent
	private static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, // capability to register for
				TofuBlockEntitys.SALT_FURNACE.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return new TankWrapper(myBlockEntity, myBlockEntity.waterTank);
				});

		event.registerBlockEntity(Capabilities.Fluid.BLOCK, // capability to register for
				TofuBlockEntitys.TF_STORAGE.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return new TankWrapper(myBlockEntity, myBlockEntity.getTank());
				});
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, // capability to register for
				TofuBlockEntitys.TOFU_POT.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return new TankWrapper(myBlockEntity, myBlockEntity.fluidTank);
				});

		event.registerBlockEntity(Capabilities.Item.BLOCK, TofuBlockEntitys.SALT_FURNACE.get(), WorldlyContainerWrapper::new);
	}
}
