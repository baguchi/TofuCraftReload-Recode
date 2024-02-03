package baguchan.tofucraft.blockentity.tfenergy;

import baguchan.tofucraft.block.tfenergy.TFCollectorBlock;
import baguchan.tofucraft.blockentity.tfenergy.base.SenderBaseBlockEntity;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class TFCollectorBlockEntity extends SenderBaseBlockEntity {

	private static final int POWER = 10;
	public static final List<BlockPos> COLLECTOR_OFFSETS = BlockPos.betweenClosedStream(-2, -2, -2, 2, 2, 2)
			.filter(p_207914_ -> Math.abs(p_207914_.getX()) == 1 || Math.abs(p_207914_.getZ()) == 1)
			.map(BlockPos::immutable)
			.toList();

	public TFCollectorBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_COLLECTOR.get(), p_155229_, p_155230_, 10000);
	}

	public TFCollectorBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int energyMax) {
		super(p_155228_, p_155229_, p_155230_, energyMax);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFCollectorBlockEntity tfCollector) {
		if (level.isClientSide()) return;
		SenderBaseBlockEntity.senderUpdate(tfCollector);


		int j = 0;
		for (BlockPos blockpos2 : COLLECTOR_OFFSETS) {
			if (isValidTF(level, blockPos, blockpos2)) {
				j += POWER;
			}
		}
		if (level.dimension() == TofuDimensions.tofu_world) {
			j *= 2;
		}

		boolean worked = false;
		if (j > 0) {
			worked = true;
			tfCollector.receive(j, false);
		}

		if (blockState.getValue(TFCollectorBlock.LIT) != worked) {
			level.setBlock(blockPos, blockState.setValue(TFCollectorBlock.LIT, worked), 2);
		}
		if (worked) {
			tfCollector.setChanged();
		}
	}

	public static boolean isValidTF(Level p_207910_, BlockPos p_207911_, BlockPos p_207912_) {
		return p_207910_.getBlockState(p_207911_.offset(p_207912_)).is(TofuTags.Blocks.TF_TRANSMITTER);
	}


	public void saveAdditional(CompoundTag cmp) {
		super.saveAdditional(cmp);
	}

	public void load(CompoundTag cmp) {
		super.load(cmp);
	}
}
