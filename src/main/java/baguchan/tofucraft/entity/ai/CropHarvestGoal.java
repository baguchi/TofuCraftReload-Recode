package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.entity.TofunianEntity;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class CropHarvestGoal extends MoveToBlockGoal {
	private final TofunianEntity tofunian;

	private boolean wantsToHarvest;

	private boolean canHarvest;

	private boolean canPlant;

	public CropHarvestGoal(TofunianEntity tofunianIn, double speed) {
		super(tofunianIn, speed, 8);
		this.tofunian = tofunianIn;
	}

	public boolean canUse() {
		if (this.nextStartTick <= 0) {
			if (!ForgeEventFactory.getMobGriefingEvent(this.tofunian.level, this.tofunian))
				return false;
			this.canHarvest = false;
			this.canPlant = false;
			this.wantsToHarvest = true;
		}
		return (this.tofunian.level.isDay() && this.tofunian.getRole() == TofunianEntity.Roles.TOFUCOOK && super.canUse());
	}

	public boolean canContinueToUse() {
		return (this.tofunian.level.isDay() && (this.canHarvest || this.canPlant || this.wantsToHarvest) && super.canContinueToUse());
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
		this.tofunian.getLookControl().setLookAt(this.mob.getX() + 0.5D, (this.mob.getY() + 1), this.mob.getZ() + 0.5D, 10.0F, this.tofunian.getMaxHeadXRot());
		if (isReachedTarget()) {
			World world = this.tofunian.getLevel();
			BlockPos blockpos = this.blockPos.above();
			BlockState blockstate = world.getBlockState(blockpos);
			Block block = blockstate.getBlock();
			if (this.canHarvest && block instanceof CropsBlock) {
				Integer integer = blockstate.getValue(CropsBlock.AGE);
				if (integer.intValue() == 7)
					world.destroyBlock(blockpos, true);
			}
			BlockState blockstate2 = world.getBlockState(this.blockPos);
			ItemStack stack = findSeeds(this.tofunian);
			if (this.canPlant && blockstate2.getBlock() == TofuBlocks.TOFU_FARMLAND && !stack.isEmpty()) {
				world.setBlock(this.blockPos.above(), TofuBlocks.SOYBEAN.defaultBlockState(), 2);
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

	protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
		Block block = p_179488_1_.getBlockState(p_179488_2_).getBlock();
		if (block == TofuBlocks.TOFU_FARMLAND && this.wantsToHarvest) {
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

	private ItemStack findSeeds(TofunianEntity tofunian) {
		Inventory inventory = tofunian.getInventory();
		int i = inventory.getContainerSize();
		for (int j = 0; j < i; j++) {
			ItemStack itemstack = inventory.getItem(j);
			if (itemstack.getItem() == TofuItems.SEEDS_SOYBEANS)
				return itemstack;
		}
		return ItemStack.EMPTY;
	}
}
