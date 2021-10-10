package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuVillagers {
	// CREATE TOFU_CRAFTSMAN WORKSTATION AND PROFESSION
	public static final PoiType TOFU_CRAFTSMAN_POI = new PoiType("tofucraft:tofu_craftsman", PoiType.getBlockStates(TofuBlocks.SALT_FURNACE), 1, 1);
	public static final VillagerProfession TOFU_CRAFTSMAN = new VillagerProfession("tofucraft:tofu_craftsman", TOFU_CRAFTSMAN_POI, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LEATHERWORKER);


	@SubscribeEvent
	public static void registerPoi(RegistryEvent.Register<PoiType> registry) {
		registry.getRegistry().register(TOFU_CRAFTSMAN_POI.setRegistryName("tofu_craftsman"));
	}

	@SubscribeEvent
	public static void registerProfession(RegistryEvent.Register<VillagerProfession> registry) {
		registry.getRegistry().register(TOFU_CRAFTSMAN.setRegistryName("tofu_craftsman"));
	}
}
