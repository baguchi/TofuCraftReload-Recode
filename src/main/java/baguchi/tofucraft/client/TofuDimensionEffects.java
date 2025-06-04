package baguchi.tofucraft.client;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

public class TofuDimensionEffects extends DimensionSpecialEffects {
	public TofuDimensionEffects() {
		super(SkyType.OVERWORLD, false, false);
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 p_108908_, float p_108909_) {
		return p_108908_.multiply((double) (p_108909_ * 0.94F + 0.06F), (double) (p_108909_ * 0.94F + 0.06F), (double) (p_108909_ * 0.91F + 0.09F));
	}

	@Override
	public boolean isFoggyAt(int p_108905_, int p_108906_) {
		return false;
	}

}
