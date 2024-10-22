package baguchi.tofucraft.api;

import net.minecraft.core.Holder;
import net.minecraft.world.level.material.Fluid;

public interface IFluidBottle {

	Holder<Fluid> getFluid();
}
