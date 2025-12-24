package baguchi.tofucraft;

import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.utils.transfer.fluid.BottleResourceHandler;
import baguchi.tofucraft.utils.transfer.fluid.BucketResourceHandler;
import baguchi.tofucraft.utils.transfer.fluid.WaterBottleResourceHandler;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class ModTofuCommonEvents {

	@SubscribeEvent
	private static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BottleResourceHandler(access, TofuFluids.SOYMILK.get()), TofuItems.SOYMILK_BOTTLE.get());
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BottleResourceHandler(access, TofuFluids.SOYMILK_HELL.get()), TofuItems.SOYMILK_HELL_BOTTLE.get());
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BottleResourceHandler(access, TofuFluids.SOYMILK_SOUL.get()), TofuItems.SOYMILK_SOUL_BOTTLE.get());
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BottleResourceHandler(access, TofuFluids.BITTERN.get()), TofuItems.BITTERN_BOTTLE.get());
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new WaterBottleResourceHandler(access, stack, Fluids.WATER), Items.POTION);


		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BucketResourceHandler(access, stack, TofuFluids.SOYMILK.get()), TofuItems.SOYMILK_BUCKET.get());
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BucketResourceHandler(access, stack, TofuFluids.SOYMILK_HELL.get()), TofuItems.SOYMILK_NETHER_BUCKET.get());
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BucketResourceHandler(access, stack, TofuFluids.SOYMILK_SOUL.get()), TofuItems.SOYMILK_SOUL_BUCKET.get());
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BucketResourceHandler(access, stack, Fluids.WATER), Items.WATER_BUCKET);


		event.registerBlockEntity(Capabilities.Fluid.BLOCK, // capability to register for
				TofuBlockEntitys.SALT_FURNACE.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return myBlockEntity.waterTank;
				});

		event.registerBlockEntity(Capabilities.Fluid.BLOCK, // capability to register for
				TofuBlockEntitys.TF_STORAGE.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return myBlockEntity.getTank();
				});
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, // capability to register for
				TofuBlockEntitys.TOFU_POT.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return myBlockEntity.fluidTank;
				});

		event.registerBlockEntity(Capabilities.Item.BLOCK, TofuBlockEntitys.SALT_FURNACE.get(), WorldlyContainerWrapper::new);
	}
}
