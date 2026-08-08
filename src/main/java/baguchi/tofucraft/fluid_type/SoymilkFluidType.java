package baguchi.tofucraft.fluid_type;

import baguchi.tofucraft.registry.TofuFluidTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

public class SoymilkFluidType extends FluidType {
	public SoymilkFluidType(FluidType.Properties properties) {
		super(properties);
	}


	@Override
	public boolean isVaporizedOnPlacement(Level level, BlockPos pos, FluidStack stack) {
		if (!level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
			return false;
		} else {
			return this == TofuFluidTypes.SOYMILK.get();
		}
	}
}

