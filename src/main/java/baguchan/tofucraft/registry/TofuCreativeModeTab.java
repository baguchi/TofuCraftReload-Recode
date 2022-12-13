package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TofuCraftReload.MODID)
public class TofuCreativeModeTab {
	public static CreativeModeTab TOFUCRAFT_CREATIVE_TAB;

	@SubscribeEvent
	public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
		event.registerCreativeModeTab(new ResourceLocation(TofuCraftReload.MODID, "tofucraft")
				, (builder) -> {
					TOFUCRAFT_CREATIVE_TAB = builder.icon(() -> {
						return new ItemStack(TofuItems.TOFUZUNDA.get());
					}).build();
				});
	}

	@SubscribeEvent
	public static void registerCreativeModeTabsItem(CreativeModeTabEvent.BuildContents event) {
		TofuItems.creativeTabItems.forEach((item) -> {
			if (event.getTab() == TOFUCRAFT_CREATIVE_TAB)
				event.accept(item);
		});

	}
}
