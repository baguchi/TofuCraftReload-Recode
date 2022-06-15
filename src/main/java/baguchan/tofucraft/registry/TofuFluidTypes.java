package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.fluidtype.BitternFluidType;
import baguchan.tofucraft.fluidtype.SoymilkFluidType;
import baguchan.tofucraft.fluidtype.SoymilkHellFluidType;
import baguchan.tofucraft.fluidtype.SoymilkSoulFluidType;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuFluidTypes {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TofuCraftReload.MODID);

	public static final RegistryObject<FluidType> SOYMILK = FLUID_TYPES.register("soymilk", () -> new SoymilkFluidType(FluidType.Properties.create().canExtinguish(true).fallDistanceModifier(0F).canHydrate(true).supportsBoating(true)));
	public static final RegistryObject<FluidType> SOYMILK_HELL = FLUID_TYPES.register("soymilk_hell", () -> new SoymilkHellFluidType(FluidType.Properties.create().canExtinguish(true).fallDistanceModifier(0F).canHydrate(true).supportsBoating(true)));
	public static final RegistryObject<FluidType> SOYMILK_SOUL = FLUID_TYPES.register("soymilk_soul", () -> new SoymilkSoulFluidType(FluidType.Properties.create().canExtinguish(true).fallDistanceModifier(0F).canHydrate(true).supportsBoating(true)));
	public static final RegistryObject<FluidType> BITTERN = FLUID_TYPES.register("bittern", () -> new BitternFluidType(FluidType.Properties.create().canExtinguish(true).fallDistanceModifier(0F).canHydrate(true).supportsBoating(true)));

}
