package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.fluid.NigariFluid;
import baguchan.tofucraft.fluid.SoyMilkFluid;
import baguchan.tofucraft.fluid.SoyMilkHellFluid;
import baguchan.tofucraft.fluid.SoyMilkSoulFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuFluids {
	public static final FlowingFluid SOYMILK = registerFluid("soymilk", new SoyMilkFluid.Source());

	public static final FlowingFluid SOYMILK_FLOW = registerFluid("soymilk_flow", new SoyMilkFluid.Flowing());

	public static final FlowingFluid SOYMILK_HELL = registerFluid("soymilk_hell", new SoyMilkHellFluid.Source());

	public static final FlowingFluid SOYMILK_HELL_FLOW = registerFluid("soymilk_hell_flow", new SoyMilkHellFluid.Flowing());

	public static final FlowingFluid SOYMILK_SOUL = registerFluid("soymilk_soul", new SoyMilkSoulFluid.Source());

	public static final FlowingFluid SOYMILK_SOUL_FLOW = registerFluid("soymilk_soul_flow", new SoyMilkSoulFluid.Flowing());

	public static final FlowingFluid BITTERN = registerFluid("bittern", new NigariFluid.Source());

	public static final FlowingFluid BITTERN_FLOW = registerFluid("bittern_flow", new NigariFluid.Flowing());

	private static <T extends Fluid> T registerFluid(String key, T p_215710_1_) {
		ResourceLocation location = new ResourceLocation("tofucraft:" + key);
		p_215710_1_.setRegistryName(location);
		return p_215710_1_;
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Fluid> registry) {
		registry.getRegistry().register(SOYMILK);
		registry.getRegistry().register(SOYMILK_FLOW);
		registry.getRegistry().register(SOYMILK_HELL);
		registry.getRegistry().register(SOYMILK_HELL_FLOW);
		registry.getRegistry().register(SOYMILK_SOUL);
		registry.getRegistry().register(SOYMILK_SOUL_FLOW);
		registry.getRegistry().register(BITTERN);
		registry.getRegistry().register(BITTERN_FLOW);
	}
}
