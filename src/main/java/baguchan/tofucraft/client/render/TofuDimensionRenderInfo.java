package baguchan.tofucraft.client.render;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.IWeatherRenderHandler;

import javax.annotation.Nullable;

public class TofuDimensionRenderInfo extends DimensionRenderInfo {
	private IWeatherRenderHandler weatherRenderer;

	public TofuDimensionRenderInfo() {
		super(128.0F, true, FogType.NORMAL, false, false);
	}

	public Vector3d getBrightnessDependentFogColor(Vector3d p_230494_1_, float p_230494_2_) {
		return p_230494_1_.multiply(p_230494_2_ * 0.94F + 0.06F, p_230494_2_ * 0.94F + 0.06F, p_230494_2_ * 0.91F + 0.09F);
	}

	public boolean isFoggyAt(int p_230493_1_, int p_230493_2_) {
		return false;
	}

	@Nullable
	public IWeatherRenderHandler getWeatherRenderHandler() {
		if (this.weatherRenderer == null)
			this.weatherRenderer = new TofuWeatherRenderer();
		return this.weatherRenderer;
	}
}
