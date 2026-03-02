package baguchi.tofucraft.client.render.blockentity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class FoodPlateRenderState extends BlockEntityRenderState {
	public Direction direction = Direction.NORTH;
	@Nullable
	public BlockState plateState;
	public ItemStackRenderState plateItem = new ItemStackRenderState();
	public BlockModelRenderState plateBlock = new BlockModelRenderState();
	public boolean fire;
	public boolean candle;
	public boolean cake;
	public int renderAmount;
}
