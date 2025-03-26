package baguchi.tofucraft.client.render.state;

import baguchi.tofucraft.entity.TofuPig;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.ItemStack;

public class TofuPigRenderState extends LivingEntityRenderState {
	public TofuPig.TofuPigType type;

	public ItemStack saddle = ItemStack.EMPTY;

}
