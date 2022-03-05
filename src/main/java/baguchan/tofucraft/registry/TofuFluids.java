package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.fluid.NigariFluid;
import baguchan.tofucraft.fluid.SoyMilkFluid;
import baguchan.tofucraft.fluid.SoyMilkHellFluid;
import baguchan.tofucraft.fluid.SoyMilkSoulFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TofuCraftReload.MODID);

	public static final RegistryObject<FlowingFluid> SOYMILK = FLUIDS.register("soymilk", () -> new SoyMilkFluid.Source());
	public static final RegistryObject<FlowingFluid> SOYMILK_FLOW = FLUIDS.register("soymilk_flow", () -> new SoyMilkFluid.Flowing());

	public static final RegistryObject<FlowingFluid> SOYMILK_HELL = FLUIDS.register("soymilk_hell", () -> new SoyMilkHellFluid.Source());
	public static final RegistryObject<FlowingFluid> SOYMILK_HELL_FLOW = FLUIDS.register("soymilk_hell_flow", () -> new SoyMilkHellFluid.Flowing());

	public static final RegistryObject<FlowingFluid> SOYMILK_SOUL = FLUIDS.register("soymilk_soul", () -> new SoyMilkSoulFluid.Source());
	public static final RegistryObject<FlowingFluid> SOYMILK_SOUL_FLOW = FLUIDS.register("soymilk_soul_flow", () -> new SoyMilkSoulFluid.Flowing());

	public static final RegistryObject<FlowingFluid> BITTERN = FLUIDS.register("bittern", () -> new NigariFluid.Source());
	public static final RegistryObject<FlowingFluid> BITTERN_FLOW = FLUIDS.register("bittern_flow", () -> new NigariFluid.Flowing());
}
