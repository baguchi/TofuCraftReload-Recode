package baguchi.tofucraft.client.render.blockentity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;

public class FoodPlateRenderState extends BlockEntityRenderState {
	public Direction direction = Direction.NORTH;
	public ItemStackRenderState plateItem = new ItemStackRenderState();
	public BlockModelRenderState plateBlock = new BlockModelRenderState();
	public boolean fire;
	public int renderAmount;
}
