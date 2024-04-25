package baguchan.tofucraft.blockentity.tfenergy;

import baguchan.tofucraft.block.tfenergy.TFCollectorBlock;
import baguchan.tofucraft.block.tfenergy.TFMinerBlock;
import baguchan.tofucraft.blockentity.tfenergy.base.WorkerBaseBlockEntity;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TFMinerBlockEntity extends WorkerBaseBlockEntity {

	protected boolean working;
	protected int processTime;
	protected BlockPos workingBlockPos;
	protected BlockPos offset = BlockPos.ZERO;

	protected Vec3i size = Vec3i.ZERO;

	public TFMinerBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_MINER.get(), p_155229_, p_155230_, 10000);
	}

	public TFMinerBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int energyMax) {
		super(p_155228_, p_155229_, p_155230_, energyMax);
	}

	//tick the client and server because client need beam render
	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFMinerBlockEntity tfMinerBlock) {

		int j = 0;

		boolean worked = false;
		if (tfMinerBlock.working && tfMinerBlock.getEnergyStored() > 50) {
			if (tfMinerBlock.workingBlockPos != null) {
				if (!tfMinerBlock.isMinable(level, tfMinerBlock.workingBlockPos.mutable())) {
					tfMinerBlock.nextPos();

					j = 10;
				} else {
					++tfMinerBlock.processTime;

					if (tfMinerBlock.processTime == 4) {
						if (level instanceof ServerLevel serverLevel) {
							LootParams.Builder lootparams$builder = new LootParams.Builder(serverLevel)
									.withParameter(LootContextParams.ORIGIN, tfMinerBlock.workingBlockPos.getCenter())
									.withParameter(LootContextParams.TOOL, ItemStack.EMPTY);
							for (ItemStack stack : level.getBlockState(tfMinerBlock.workingBlockPos).getDrops(lootparams$builder)) {
								TFMinerBlockEntity.dispenseItem(level, tfMinerBlock.getBlockPos(), tfMinerBlock, stack, blockState);
							}
						}
						if (!level.isClientSide()) {
							level.levelEvent(2001, tfMinerBlock.workingBlockPos, Block.getId(level.getBlockState(tfMinerBlock.workingBlockPos)));
							level.removeBlock(tfMinerBlock.workingBlockPos, false);
						}
						tfMinerBlock.processTime = 0;
						tfMinerBlock.nextPos();
					}
					j = 100;
				}
			}

			if (j > 0) {
				worked = true;
				if (!level.isClientSide()) {
					tfMinerBlock.drain(j, false);
				}
			}
		}
		if (!level.isClientSide()) {
			if (blockState.getValue(TFCollectorBlock.LIT) != worked) {
				level.setBlock(blockPos, blockState.setValue(TFMinerBlock.LIT, worked), 2);
			}
			if (worked) {
				tfMinerBlock.setChanged();
			}
		}
	}

	private static void dispenseItem(Level p_307361_, BlockPos p_307620_, TFMinerBlockEntity p_307387_, ItemStack p_307296_, BlockState p_307501_) {
		Direction direction = (p_307501_.getValue(TFMinerBlock.FACING));
		Container container = HopperBlockEntity.getContainerAt(p_307361_, p_307620_.relative(direction));
		ItemStack itemstack = p_307296_.copy();
		if (container == null || !(container instanceof TFMinerBlockEntity) && p_307296_.getCount() <= container.getMaxStackSize()) {
			if (container != null) {
				while (!itemstack.isEmpty()) {
					int i = itemstack.getCount();
					itemstack = HopperBlockEntity.addItem(null, container, itemstack, direction.getOpposite());
					if (i == itemstack.getCount()) {
						break;
					}
				}
			}
		} else {
			while (!itemstack.isEmpty()) {
				ItemStack itemstack2 = itemstack.copyWithCount(1);
				ItemStack itemstack1 = HopperBlockEntity.addItem(null, container, itemstack2, direction.getOpposite());
				if (!itemstack1.isEmpty()) {
					break;
				}

				itemstack.shrink(1);
			}
		}

		if (!itemstack.isEmpty()) {
			Vec3 vec3 = Vec3.atCenterOf(p_307620_).relative(direction, 0.7);
			DefaultDispenseItemBehavior.spawnItem(p_307361_, itemstack, 6, direction, vec3);
			p_307361_.levelEvent(1049, p_307620_, 0);
			p_307361_.levelEvent(2010, p_307620_, direction.get3DDataValue());
		}

	}

	public void nextPos() {
		if (workingBlockPos.getX() + 1 > this.getBlockPos().getX() + this.offset.getX() + this.size.getX()) {
			workingBlockPos = workingBlockPos.offset(-size.getX(), 0, 1);
			if (workingBlockPos.getZ() + 1 > this.getBlockPos().getZ() + this.offset.getZ() + this.size.getZ()) {
				workingBlockPos = workingBlockPos.offset(0, 1, -size.getZ());
			}
			if (workingBlockPos.getY() + 1 > this.getBlockPos().getY() + this.offset.getY() + this.size.getY()) {
				workingBlockPos = null;
				working = false;
			}
		} else {
			workingBlockPos = workingBlockPos.offset(1, 0, 0);
		}
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

	public void setWorkingBlockPos(@Nullable BlockPos blockPos) {
		this.workingBlockPos = blockPos;
	}

	public void setSize(Vec3i vec3i) {
		this.size = vec3i;
	}

	public void saveAdditional(CompoundTag cmp, HolderLookup.Provider p_338445_) {
		super.saveAdditional(cmp, p_338445_);
		cmp.putInt("posX", this.offset.getX());
		cmp.putInt("posY", this.offset.getY());
		cmp.putInt("posZ", this.offset.getZ());
		cmp.putInt("sizeX", this.size.getX());
		cmp.putInt("sizeY", this.size.getY());
		cmp.putInt("sizeZ", this.size.getZ());
		if (this.workingBlockPos != null) {
			cmp.putInt("working_posX", this.workingBlockPos.getX());
			cmp.putInt("working_posY", this.workingBlockPos.getY());
			cmp.putInt("working_posZ", this.workingBlockPos.getZ());
		}
		cmp.putBoolean("Working", this.working);
		cmp.putInt("process", this.processTime);
	}

	public void loadAdditional(CompoundTag cmp, HolderLookup.Provider p_338445_) {
		super.loadAdditional(cmp, p_338445_);
		int i = Mth.clamp(cmp.getInt("posX"), -15, 5);
		int j = Mth.clamp(cmp.getInt("posY"), -15, 5);
		int k = Mth.clamp(cmp.getInt("posZ"), -15, 5);
		this.offset = new BlockPos(i, j, k);
		int l = Mth.clamp(cmp.getInt("sizeX"), 0, 10);
		int i1 = Mth.clamp(cmp.getInt("sizeY"), 0, 10);
		int j1 = Mth.clamp(cmp.getInt("sizeZ"), 0, 10);
		this.size = new Vec3i(l, i1, j1);
		if (cmp.contains("working_posX") && cmp.contains("working_posY") && cmp.contains("working_posZ")) {
			this.workingBlockPos = new BlockPos(cmp.getInt("working_posX"), cmp.getInt("working_posY"), cmp.getInt("working_posZ"));
		}
		this.working = cmp.getBoolean("Working");
		this.processTime = cmp.getInt("process");
	}

	public Vec3i getStructureSize() {
		return this.size;
	}

	public BlockPos getStructurePos() {
		return offset;
	}

	@Nullable
	public BlockPos getWorkingBlockPos() {
		return workingBlockPos;
	}
}
