package baguchan.tofucraft.client.render.state;

import baguchan.tofucraft.entity.TofuPig;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.SaddleableRenderState;

public class TofuPigRenderState extends LivingEntityRenderState implements SaddleableRenderState {
	public TofuPig.TofuPigType type;

	public boolean isSaddled;

	@Override
	public boolean isSaddled() {
		return this.isSaddled;
	}
}
