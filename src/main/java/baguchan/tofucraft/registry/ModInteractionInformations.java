package baguchan.tofucraft.registry;

import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

public class ModInteractionInformations {
	public static void init() {
		FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK.get(),
				fluidState -> TofuBlocks.TOFUSLATE.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK_HELL.get(),
				fluidState -> TofuBlocks.TOFUSLATE.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK_SOUL.get(),
				fluidState -> TofuBlocks.TOFUSLATE.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(TofuFluidTypes.BITTERN.get(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK.get(),
				fluidState -> TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(TofuFluidTypes.BITTERN.get(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK_HELL.get(),
				fluidState -> TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(TofuFluidTypes.BITTERN.get(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK_SOUL.get(),
				fluidState -> TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()
		));
	}
}