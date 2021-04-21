package baguchan.tofucraft.registry;

import baguchan.tofucraft.fluid.NigariFluid;
import baguchan.tofucraft.fluid.SoyMilkFluid;
import baguchan.tofucraft.fluid.SoyMilkHellFluid;
import baguchan.tofucraft.fluid.SoyMilkSoulFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
public class TofuFluids {
	public static final FlowingFluid SOYMILK = (FlowingFluid) registerFluid("soymilk", new SoyMilkFluid.Source());

	public static final FlowingFluid SOYMILK_FLOW = (FlowingFluid) registerFluid("soymilk_flow", new SoyMilkFluid.Flowing());

	public static final FlowingFluid SOYMILK_HELL = (FlowingFluid) registerFluid("soymilk_hell", new SoyMilkHellFluid.Source());

	public static final FlowingFluid SOYMILK_HELL_FLOW = (FlowingFluid) registerFluid("soymilk_hell_flow", new SoyMilkHellFluid.Flowing());

	public static final FlowingFluid SOYMILK_SOUL = (FlowingFluid) registerFluid("soymilk_soul", new SoyMilkSoulFluid.Source());

	public static final FlowingFluid SOYMILK_SOUL_FLOW = (FlowingFluid) registerFluid("soymilk_soul_flow", new SoyMilkSoulFluid.Flowing());

	public static final FlowingFluid BITTERN = (FlowingFluid) registerFluid("bittern", new NigariFluid.Source());

	public static final FlowingFluid BITTERN_FLOW = (FlowingFluid) registerFluid("bittern_flow", new NigariFluid.Flowing());

	private static <T extends Fluid> T registerFluid(String key, T p_215710_1_) {
		ResourceLocation location = new ResourceLocation("tofucraft:" + key);
		p_215710_1_.setRegistryName(location);
		return p_215710_1_;
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Fluid> registry) {
		registry.getRegistry().register((IForgeRegistryEntry) SOYMILK);
		registry.getRegistry().register((IForgeRegistryEntry) SOYMILK_FLOW);
		registry.getRegistry().register((IForgeRegistryEntry) SOYMILK_HELL);
		registry.getRegistry().register((IForgeRegistryEntry) SOYMILK_HELL_FLOW);
		registry.getRegistry().register((IForgeRegistryEntry) SOYMILK_SOUL);
		registry.getRegistry().register((IForgeRegistryEntry) SOYMILK_SOUL_FLOW);
		registry.getRegistry().register((IForgeRegistryEntry) BITTERN);
		registry.getRegistry().register((IForgeRegistryEntry) BITTERN_FLOW);
	}
}
