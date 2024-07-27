package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.fluid.DoubanjiangFluid;
import baguchan.tofucraft.fluid.NigariFluid;
import baguchan.tofucraft.fluid.SoyMilkFluid;
import baguchan.tofucraft.fluid.SoyMilkHellFluid;
import baguchan.tofucraft.fluid.SoyMilkSoulFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

public class TofuFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, TofuCraftReload.MODID);

	public static final DeferredHolder<Fluid, FlowingFluid> SOYMILK = FLUIDS.register("soymilk", () -> new SoyMilkFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> SOYMILK_FLOW = FLUIDS.register("soymilk_flow", () -> new SoyMilkFluid.Flowing());

	public static final DeferredHolder<Fluid, FlowingFluid> SOYMILK_HELL = FLUIDS.register("soymilk_hell", () -> new SoyMilkHellFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> SOYMILK_HELL_FLOW = FLUIDS.register("soymilk_hell_flow", () -> new SoyMilkHellFluid.Flowing());

	public static final DeferredHolder<Fluid, FlowingFluid> SOYMILK_SOUL = FLUIDS.register("soymilk_soul", () -> new SoyMilkSoulFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> SOYMILK_SOUL_FLOW = FLUIDS.register("soymilk_soul_flow", () -> new SoyMilkSoulFluid.Flowing());

	public static final DeferredHolder<Fluid, FlowingFluid> BITTERN = FLUIDS.register("bittern", () -> new NigariFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> BITTERN_FLOW = FLUIDS.register("bittern_flow", () -> new NigariFluid.Flowing());

	public static final DeferredHolder<Fluid, FlowingFluid> DOUBANJIANG = FLUIDS.register("doubanjiang", () -> new DoubanjiangFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> DOUBANJIANG_FLOW = FLUIDS.register("doubanjiang_flow", () -> new DoubanjiangFluid.Flowing());

	public static final DeferredHolder<Fluid, Fluid> CRIMSON = DeferredHolder.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "crimson"));
	public static final DeferredHolder<Fluid, Fluid> FLOWING_CRIMSON = DeferredHolder.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "flowing_crimson"));

	public static final DeferredHolder<Fluid, Fluid> WARPED = DeferredHolder.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "warped"));
	public static final DeferredHolder<Fluid, Fluid> FLOWING_WARPED = DeferredHolder.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "flowing_warped"));


	public static void registerFluids(RegisterEvent event) {
		// register fluid type
		event.register(NeoForgeRegistries.Keys.FLUID_TYPES, helper -> helper.register(TofuFluidTypes.CRIMSON.unwrapKey().orElseThrow(), new FluidType(
				FluidType.Properties.create().density(1024).viscosity(1024)
						.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
						.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA))));

		// register fluids
		event.register(Registries.FLUID, helper -> {
			// set up properties
			BaseFlowingFluid.Properties properties = new BaseFlowingFluid.Properties(TofuFluidTypes.CRIMSON::value, CRIMSON::value, FLOWING_CRIMSON::value);

			helper.register(CRIMSON.getId(), new BaseFlowingFluid.Source(properties));
			helper.register(FLOWING_CRIMSON.getId(), new BaseFlowingFluid.Flowing(properties));
		});


		event.register(NeoForgeRegistries.Keys.FLUID_TYPES, helper -> helper.register(TofuFluidTypes.WARPED.unwrapKey().orElseThrow(), new FluidType(
				FluidType.Properties.create().density(1024).viscosity(1024)
						.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
						.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA))));

		// register fluids
		event.register(Registries.FLUID, helper -> {
			// set up properties
			BaseFlowingFluid.Properties properties = new BaseFlowingFluid.Properties(TofuFluidTypes.WARPED::value, WARPED::value, FLOWING_WARPED::value);

			helper.register(WARPED.getId(), new BaseFlowingFluid.Source(properties));
			helper.register(FLOWING_WARPED.getId(), new BaseFlowingFluid.Flowing(properties));
		});
	}

}
