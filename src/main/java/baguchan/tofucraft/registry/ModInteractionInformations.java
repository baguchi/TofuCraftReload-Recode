package baguchan.tofucraft.registry;

import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;

public class ModInteractionInformations {
	public static void init() {
		FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK.get(),
				fluidState -> TofuBlocks.TOFUSLATE.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				TofuFluidTypes.SOYMILK_HELL.get(),
				fluidState -> TofuBlocks.TOFUSLATE.get().defaultBlockState()
		));
		FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
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