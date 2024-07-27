package baguchan.tofucraft.fluidtype;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

public class SoymilkHellFluidType extends FluidType {
	public SoymilkHellFluidType(FluidType.Properties properties) {
		super(properties);
	}

	@Override
	public boolean isVaporizedOnPlacement(Level level, BlockPos pos, FluidStack stack) {
		return false;
	}
}
