package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.fluidtype.BitternFluidType;
import baguchan.tofucraft.fluidtype.SoymilkFluidType;
import baguchan.tofucraft.fluidtype.SoymilkHellFluidType;
import baguchan.tofucraft.fluidtype.SoymilkSoulFluidType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuFluidTypes {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TofuCraftReload.MODID);

	public static final RegistryObject<FluidType> SOYMILK = FLUID_TYPES.register("soymilk", () -> new SoymilkFluidType(FluidType.Properties.create().pathType(BlockPathTypes.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH).supportsBoating(true)));
	public static final RegistryObject<FluidType> SOYMILK_HELL = FLUID_TYPES.register("soymilk_hell", () -> new SoymilkHellFluidType(FluidType.Properties.create().pathType(BlockPathTypes.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).supportsBoating(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)));
	public static final RegistryObject<FluidType> SOYMILK_SOUL = FLUID_TYPES.register("soymilk_soul", () -> new SoymilkSoulFluidType(FluidType.Properties.create().pathType(BlockPathTypes.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).supportsBoating(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)));
	public static final RegistryObject<FluidType> BITTERN = FLUID_TYPES.register("bittern", () -> new BitternFluidType(FluidType.Properties.create().pathType(BlockPathTypes.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).supportsBoating(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)));
}
