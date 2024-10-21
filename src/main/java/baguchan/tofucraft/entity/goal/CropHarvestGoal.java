package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.entity.Tofunian;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;

public class CropHarvestGoal extends MoveToBlockGoal {
	private final Tofunian tofunian;

	private boolean wantsToHarvest;

	private boolean canHarvest;

	private boolean canPlant;

	public CropHarvestGoal(Tofunian tofunianIn, double speed) {
		super(tofunianIn, speed, 8);
		this.tofunian = tofunianIn;
	}

	public boolean canUse() {
		if (this.nextStartTick <= 0) {
			if (!EventHooks.canEntityGrief((ServerLevel) this.tofunian.level(), this.tofunian))
				return false;
			this.canHarvest = false;
			this.canPlant = false;
			this.wantsToHarvest = true;
		}
		return (this.tofunian.level().isDay() && this.tofunian.getRole() == Tofunian.Roles.TOFUCOOK && super.canUse());
	}

	public boolean canContinueToUse() {
		return (this.tofunian.level().isDay() && (this.canHarvest || this.canPlant || this.wantsToHarvest) && super.canContinueToUse());
	}

	public void start() {
		super.start();
		this.nextStartTick = 0;
		this.canHarvest = false;
		this.canPlant = false;
		this.wantsToHarvest = true;
	}

	public void tick() {
		super.tick();
		this.tofunian.getLookControl().setLookAt(this.blockPos.getX() + 0.5D, (this.blockPos.getY()), this.blockPos.getZ() + 0.5D, 10.0F, this.tofunian.getMaxHeadXRot());
		if (isReachedTarget()) {
			Level world = this.tofunian.level();
			BlockPos blockpos = this.blockPos.above();
			BlockState blockstate = world.getBlockState(blockpos);
			Block block = blockstate.getBlock();
			if (this.canHarvest && block instanceof CropBlock) {
				Integer integer = blockstate.getValue(CropBlock.AGE);
				if (integer.intValue() == 7)
					world.destroyBlock(blockpos, true);
			}
			BlockState blockstate2 = world.getBlockState(this.blockPos);
			ItemStack stack = findSeeds(this.tofunian);
			if (this.canPlant && blockstate2.getBlock() == TofuBlocks.TOFU_FARMLAND.get() && !stack.isEmpty()) {
				world.setBlock(this.blockPos.above(), TofuBlocks.SOYBEAN.get().defaultBlockState(), 2);
				stack.shrink(1);
			}
			this.canPlant = false;
			this.canHarvest = false;
			this.nextStartTick = 10;
		}
		if (this.wantsToHarvest && !this.canPlant && !this.canHarvest && --this.nextStartTick <= 0)
			if (findNearestBlock()) {
				this.canPlant = true;
				this.canHarvest = true;
			} else {
				this.wantsToHarvest = false;
			}
	}


	protected boolean isValidTarget(LevelReader p_179488_1_, BlockPos p_179488_2_) {
		Block block = p_179488_1_.getBlockState(p_179488_2_).getBlock();
		if (block == TofuBlocks.TOFU_FARMLAND.get() && this.wantsToHarvest) {
			p_179488_2_ = p_179488_2_.above();
			BlockState blockstate = p_179488_1_.getBlockState(p_179488_2_);
			block = blockstate.getBlock();
			if (block instanceof SoybeanCropsBlock && ((SoybeanCropsBlock) block).isMaxAge(blockstate)) {
				this.canHarvest = true;
				this.canPlant = true;
				return true;
			}
			if (!findSeeds(this.tofunian).isEmpty() && blockstate.isAir()) {
				this.canHarvest = true;
				this.canPlant = true;
				return true;
			}
		}
		return false;
	}

	private ItemStack findSeeds(Tofunian tofunian) {
		SimpleContainer inventory = tofunian.getInventory();
		int i = inventory.getContainerSize();
		for (int j = 0; j < i; j++) {
			ItemStack itemstack = inventory.getItem(j);
			if (itemstack.getItem() == TofuItems.SEEDS_SOYBEANS.get())
				return itemstack;
		}
		return ItemStack.EMPTY;
	}
}