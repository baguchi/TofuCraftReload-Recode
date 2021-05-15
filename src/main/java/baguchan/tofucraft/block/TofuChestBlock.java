package baguchan.tofucraft.block;

import baguchan.tofucraft.tileentity.TofuChestTileEntity;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class TofuChestBlock extends ChestBlock {
	public TofuChestBlock(Properties p_i225757_1_, Supplier<TileEntityType<? extends ChestTileEntity>> p_i225757_2_) {
		super(p_i225757_1_, p_i225757_2_);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
		return new TofuChestTileEntity();
	}
}
