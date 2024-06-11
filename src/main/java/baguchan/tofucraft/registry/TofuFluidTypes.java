package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.fluidtype.BitternFluidType;
import baguchan.tofucraft.fluidtype.SoymilkFluidType;
import baguchan.tofucraft.fluidtype.SoymilkHellFluidType;
import baguchan.tofucraft.fluidtype.SoymilkSoulFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class TofuFluidTypes {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, TofuCraftReload.MODID);

	public static final Supplier<FluidType> SOYMILK = FLUID_TYPES.register("soymilk", () -> new SoymilkFluidType(FluidType.Properties.create().pathType(PathType.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH).supportsBoating(true)));
	public static final Supplier<FluidType> SOYMILK_HELL = FLUID_TYPES.register("soymilk_hell", () -> new SoymilkHellFluidType(FluidType.Properties.create().pathType(PathType.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).supportsBoating(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)));
	public static final Supplier<FluidType> SOYMILK_SOUL = FLUID_TYPES.register("soymilk_soul", () -> new SoymilkSoulFluidType(FluidType.Properties.create().pathType(PathType.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).supportsBoating(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)));
	public static final Supplier<FluidType> BITTERN = FLUID_TYPES.register("bittern", () -> new BitternFluidType(FluidType.Properties.create().pathType(PathType.WATER).canExtinguish(true).fallDistanceModifier(0F).motionScale(0.007F).canHydrate(true).supportsBoating(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)));
	public static final DeferredHolder<FluidType, FluidType> CRIMSON = DeferredHolder.create(NeoForgeRegistries.Keys.FLUID_TYPES, ResourceLocation.parse("crimson"));
	public static final DeferredHolder<FluidType, FluidType> WARPED = DeferredHolder.create(NeoForgeRegistries.Keys.FLUID_TYPES, ResourceLocation.parse("warped"));

}
