package baguchan.tofucraft.blockentity.tfenergy;

import baguchan.tofucraft.block.tfenergy.TFCollectorBlock;
import baguchan.tofucraft.blockentity.tfenergy.base.WorkerBaseBlockEntity;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class TFMinerBlockEntity extends WorkerBaseBlockEntity {

	private static final int POWER = 20;
	protected boolean working;
	protected BlockPos workingBlockPos;
	protected BlockPos offset = BlockPos.ZERO;

	protected Vec3i size = Vec3i.ZERO;

	public TFMinerBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_MINER.get(), p_155229_, p_155230_, 10000);
	}

	public TFMinerBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int energyMax) {
		super(p_155228_, p_155229_, p_155230_, energyMax);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFMinerBlockEntity tfCollector) {
		if (level.isClientSide()) return;
		int j = 0;

		boolean worked = false;
		if (tfCollector.working) {

			if (tfCollector.workingBlockPos == null || !tfCollector.isMinable(level, tfCollector.workingBlockPos.mutable())) {
				Optional<BlockPos> blockPos1 = tfCollector.findMinableBlock(level, blockPos);
				if (blockPos1.isPresent()) {
					tfCollector.workingBlockPos = blockPos1.get();
				} else {
					tfCollector.workingBlockPos = null;
					tfCollector.working = false;
				}

				j = 40;
			}

			if (j > 0) {
				worked = true;
				tfCollector.drain(j, false);
			}
		}
		if (blockState.getValue(TFCollectorBlock.LIT) != worked) {
			level.setBlock(blockPos, blockState.setValue(TFCollectorBlock.LIT, worked), 2);
		}
		if (worked) {
			tfCollector.setChanged();
		}
	}

	public Optional<BlockPos> findMinableBlock(Level level, BlockPos blockPos) {
		BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
		BlockPos nearest = null;
		for (int x = blockPos.getX() + offset.getX(); x < blockPos.getX() + offset.getX() + size.getX(); x++) {
			for (int z = blockPos.getZ() + offset.getZ(); z < blockPos.getZ() + offset.getZ() + size.getZ(); z++) {
				for (int y = blockPos.getY() + offset.getY(); y < blockPos.getY() + offset.getY() + size.getY(); y++) {
					mutableBlockPos.set(x, y, z);
					if (isMinable(level, mutableBlockPos) && (nearest == null || nearest.distSqr(blockPos) > mutableBlockPos.distSqr(blockPos))) {
						nearest = mutableBlockPos.immutable();
					}
				}
			}
		}
		return Optional.ofNullable(nearest);
	}

	private boolean isMinable(Level level, BlockPos.MutableBlockPos mutableBlockPos) {
		return !level.getBlockState(mutableBlockPos).is(TofuBlocks.TF_MINER.get()) && !level.getBlockState(mutableBlockPos).isAir() && level.getBlockState(mutableBlockPos).getDestroySpeed(level, mutableBlockPos) < 10F && level.getBlockState(mutableBlockPos).getDestroySpeed(level, mutableBlockPos) >= 0F && !level.getBlockState(mutableBlockPos).is(BlockTags.FEATURES_CANNOT_REPLACE);
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean isWorking() {
		return working;
	}

	public void setOffset(BlockPos blockPos) {
		this.offset = blockPos;
	}

	public void setWorkingBlockPos(BlockPos blockPos) {
		this.workingBlockPos = blockPos;
	}

	public void setSize(Vec3i vec3i) {
		this.size = vec3i;
	}

	public void saveAdditional(CompoundTag cmp) {
		super.saveAdditional(cmp);
		cmp.putInt("posX", this.offset.getX());
		cmp.putInt("posY", this.offset.getY());
		cmp.putInt("posZ", this.offset.getZ());
		cmp.putInt("sizeX", this.size.getX());
		cmp.putInt("sizeY", this.size.getY());
		cmp.putInt("sizeZ", this.size.getZ());
	}

	public void load(CompoundTag cmp) {
		super.load(cmp);
		int i = Mth.clamp(cmp.getInt("posX"), -15, 5);
		int j = Mth.clamp(cmp.getInt("posY"), -15, 5);
		int k = Mth.clamp(cmp.getInt("posZ"), -15, 5);
		this.offset = new BlockPos(i, j, k);
		int l = Mth.clamp(cmp.getInt("sizeX"), 0, 10);
		int i1 = Mth.clamp(cmp.getInt("sizeY"), 0, 10);
		int j1 = Mth.clamp(cmp.getInt("sizeZ"), 0, 10);
		this.size = new Vec3i(l, i1, j1);
	}

	public Vec3i getStructureSize() {
		return this.size;
	}

	public BlockPos getStructurePos() {
		return offset;
	}

	public BlockPos getWorkingBlockPos() {
		return workingBlockPos;
	}
}
