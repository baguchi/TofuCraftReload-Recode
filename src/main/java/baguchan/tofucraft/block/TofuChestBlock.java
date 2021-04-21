package baguchan.tofucraft.block;

import baguchan.tofucraft.tileentity.TofuChestTileEntity;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;

public class TofuChestBlock extends ChestBlock {
	public TofuChestBlock(Properties p_i225757_1_, Supplier<TileEntityType<? extends ChestTileEntity>> p_i225757_2_) {
		super(p_i225757_1_, p_i225757_2_);
	}

	public TileEntity func_196283_a_(IBlockReader p_196283_1_) {
		return (TileEntity) new TofuChestTileEntity();
	}
}
