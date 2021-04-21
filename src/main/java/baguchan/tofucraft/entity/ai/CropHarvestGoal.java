package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.entity.TofunianEntity;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.state.Property;
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
		super((CreatureEntity) tofunianIn, speed, 8);
		this.tofunian = tofunianIn;
	}

	public boolean canUse() {
		if (this.nextStartTick <= 0) {
			if (!ForgeEventFactory.getMobGriefingEvent(this.tofunian.level, (Entity) this.tofunian))
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
		this.tofunian.getLookControl().setLookAt(this.mob.getX() + 0.5D, (this.mob.getY() + 1), this.mob.getZ() + 0.5D, 10.0F, this.tofunian.func_70646_bf());
		if (isReachedTarget()) {
			World world = this.tofunian.func_190670_t_();
			BlockPos blockpos = this.mob.func_177984_a();
			BlockState blockstate = world.getBlockState(blockpos);
			Block block = blockstate.getBlock();
			if (this.canHarvest && block instanceof CropsBlock) {
				Integer integer = (Integer) blockstate.func_177229_b((Property) CropsBlock.field_176488_a);
				if (integer.intValue() == 7)
					world.func_175655_b(blockpos, true);
			}
			BlockState blockstate2 = world.getBlockState(this.mob);
			ItemStack stack = findSeeds(this.tofunian);
			if (this.canPlant && blockstate2.getBlock() == TofuBlocks.TOFU_FARMLAND && !stack.func_190926_b()) {
				world.func_180501_a(this.mob.func_177984_a(), TofuBlocks.SOYBEAN.func_176223_P(), 2);
				stack.func_190918_g(1);
			}
			this.canPlant = false;
			this.canHarvest = false;
			this.nextStartTick = 10;
		}
		if (this.wantsToHarvest && !this.canPlant && !this.canHarvest && --this.nextStartTick <= 0)
			if (func_179489_g()) {
				this.canPlant = true;
				this.canHarvest = true;
			} else {
				this.wantsToHarvest = false;
			}
	}

	protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
		Block block = p_179488_1_.getBlockState(p_179488_2_).getBlock();
		if (block == TofuBlocks.TOFU_FARMLAND && this.wantsToHarvest) {
			p_179488_2_ = p_179488_2_.func_177984_a();
			BlockState blockstate = p_179488_1_.getBlockState(p_179488_2_);
			block = blockstate.getBlock();
			if (block instanceof SoybeanCropsBlock && ((SoybeanCropsBlock) block).func_185525_y(blockstate)) {
				this.canHarvest = true;
				this.canPlant = true;
				return true;
			}
			if (!findSeeds(this.tofunian).func_190926_b() && blockstate.func_196958_f()) {
				this.canHarvest = true;
				this.canPlant = true;
				return true;
			}
		}
		return false;
	}

	private ItemStack findSeeds(TofunianEntity tofunian) {
		Inventory inventory = tofunian.func_213715_ed();
		int i = inventory.func_70302_i_();
		for (int j = 0; j < i; j++) {
			ItemStack itemstack = inventory.func_70301_a(j);
			if (itemstack.func_77973_b() == TofuItems.SEEDS_SOYBEANS)
				return itemstack;
		}
		return ItemStack.field_190927_a;
	}
}
