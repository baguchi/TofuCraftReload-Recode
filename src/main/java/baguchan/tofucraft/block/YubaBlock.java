package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class YubaBlock extends Block {
	private static final VoxelShape STABLE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16D, 1.0D, 16D);

	public YubaBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState p_56068_, BlockGetter p_56069_, BlockPos p_56070_, CollisionContext p_56071_) {
		if (p_56071_.isAbove(Shapes.block(), p_56070_, true)) {
			return STABLE_SHAPE;
		} else {
			return Shapes.empty();
		}
	}

	@Override
	public boolean canSurvive(BlockState p_152289_, LevelReader p_152290_, BlockPos p_152291_) {
		BlockPos blockpos = p_152291_.below();
		BlockState blockstate = p_152290_.getBlockState(blockpos);
		return blockstate.is(TofuBlocks.SOYMILK.get()) || blockstate.isFaceSturdy(p_152290_, blockpos, Direction.UP);
	}

	@Override
	public BlockState updateShape(BlockState p_152293_, Direction p_152294_, BlockState p_152295_, LevelAccessor p_152296_, BlockPos p_152297_, BlockPos p_152298_) {
		if (!p_152296_.isClientSide()) {
			p_152296_.scheduleTick(p_152297_, this, 5);
		}

		return p_152293_;
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos blockPos, Entity entity) {
		super.entityInside(state, level, blockPos, entity);

		if (!level.isClientSide() && canEntityTilt(blockPos, entity)) {
			level.scheduleTick(blockPos, this, 5);
		}
	}

	private static boolean canEntityTilt(BlockPos p_152302_, Entity p_152303_) {
		return p_152303_.isOnGround() && p_152303_.position().y > (double) ((float) p_152302_.getY() + 1 / 16D) && p_152303_.position().y <= (double) ((float) p_152302_.getY() + 2 / 16D);
	}

	@Override
	public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
		super.tick(p_222945_, p_222946_, p_222947_, p_222948_);

		if (!this.canSurvive(p_222945_, p_222946_, p_222947_)) {
			FallingBlockEntity.fall(p_222946_, p_222947_, p_222945_);
		} else if (!p_222945_.isFaceSturdy(p_222946_, p_222947_, Direction.UP)) {
			p_222946_.levelEvent(2001, p_222947_, Block.getId(p_222946_.getBlockState(p_222947_)));
			p_222946_.removeBlock(p_222947_, false);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack itemstack = player.getItemInHand(handIn);

		if (itemstack.is(Tags.Items.RODS_WOODEN)) {
			if (!worldIn.isClientSide()) {
				ItemStack salt = new ItemStack(TofuItems.YUBA.get(), 1);
				float f = 0.7F;
				double d0 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				double d1 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
				double d2 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, salt);
				itemEntity.setPickUpDelay(10);
				worldIn.addFreshEntity(itemEntity);
				worldIn.removeBlock(pos, false);
				player.playSound(SoundEvents.BOAT_PADDLE_WATER, 1.0F, 1.0F);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
