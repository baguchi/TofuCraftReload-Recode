package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.tileentity.TofuBedTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class TofuBedBlock extends HorizontalBlock implements ITileEntityProvider {
	public static final EnumProperty<BedPart> PART = BlockStateProperties.field_208139_an;

	public static final BooleanProperty OCCUPIED = BlockStateProperties.field_208192_s;

	protected static final VoxelShape BASE = Block.func_208617_a(0.0D, 3.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	protected static final VoxelShape LEG_NORTH_WEST = Block.func_208617_a(0.0D, 0.0D, 0.0D, 3.0D, 3.0D, 3.0D);

	protected static final VoxelShape LEG_SOUTH_WEST = Block.func_208617_a(0.0D, 0.0D, 13.0D, 3.0D, 3.0D, 16.0D);

	protected static final VoxelShape LEG_NORTH_EAST = Block.func_208617_a(13.0D, 0.0D, 0.0D, 16.0D, 3.0D, 3.0D);

	protected static final VoxelShape LEG_SOUTH_EAST = Block.func_208617_a(13.0D, 0.0D, 13.0D, 16.0D, 3.0D, 16.0D);

	protected static final VoxelShape NORTH_SHAPE = VoxelShapes.func_216384_a(BASE, new VoxelShape[]{LEG_NORTH_WEST, LEG_NORTH_EAST});

	protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.func_216384_a(BASE, new VoxelShape[]{LEG_SOUTH_WEST, LEG_SOUTH_EAST});

	protected static final VoxelShape WEST_SHAPE = VoxelShapes.func_216384_a(BASE, new VoxelShape[]{LEG_NORTH_WEST, LEG_SOUTH_WEST});

	protected static final VoxelShape EAST_SHAPE = VoxelShapes.func_216384_a(BASE, new VoxelShape[]{LEG_NORTH_EAST, LEG_SOUTH_EAST});

	public TofuBedBlock(Properties p_i48442_2_) {
		super(p_i48442_2_);
		func_180632_j((BlockState) ((BlockState) this.field_176227_L.func_177621_b()).setValue((Property) PART, (Comparable) BedPart.FOOT).setValue(OCCUPIED, Boolean.valueOf(false)));
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public static Direction getBedOrientation(IBlockReader p_220174_0_, BlockPos p_220174_1_) {
		BlockState blockstate = p_220174_0_.getBlockState(p_220174_1_);
		return (blockstate.getBlock() instanceof net.minecraft.block.BedBlock) ? (Direction) blockstate.func_177229_b((Property) field_185512_D) : null;
	}

	public ActionResultType func_225533_a_(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
		if (p_225533_2_.isClientSide)
			return ActionResultType.CONSUME;
		if (p_225533_1_.func_177229_b((Property) PART) != BedPart.HEAD) {
			p_225533_3_ = p_225533_3_.func_177972_a((Direction) p_225533_1_.func_177229_b((Property) field_185512_D));
			p_225533_1_ = p_225533_2_.getBlockState(p_225533_3_);
			if (!p_225533_1_.is((Block) this))
				return ActionResultType.CONSUME;
		}
		if (!canSetSpawn(p_225533_2_)) {
			p_225533_2_.removeBlock(p_225533_3_, false);
			BlockPos blockpos = p_225533_3_.func_177972_a(((Direction) p_225533_1_.func_177229_b((Property) field_185512_D)).func_176734_d());
			if (p_225533_2_.getBlockState(blockpos).is((Block) this))
				p_225533_2_.removeBlock(blockpos, false);
			p_225533_2_.func_230546_a_((Entity) null, DamageSource.func_233546_a_(), (ExplosionContext) null, p_225533_3_.getX() + 0.5D, p_225533_3_.getY() + 0.5D, p_225533_3_.getZ() + 0.5D, 5.0F, true, Explosion.Mode.DESTROY);
			return ActionResultType.SUCCESS;
		}
		if (((Boolean) p_225533_1_.func_177229_b((Property) OCCUPIED)).booleanValue()) {
			if (!kickVillagerOutOfBed(p_225533_2_, p_225533_3_))
				p_225533_4_.func_146105_b((ITextComponent) new TranslationTextComponent("block.minecraft.bed.occupied"), true);
			return ActionResultType.SUCCESS;
		}
		p_225533_4_.func_213819_a(p_225533_3_).ifLeft(p_220173_1_ -> {
			if (p_220173_1_ != null)
				p_225533_4_.func_146105_b(p_220173_1_.func_221259_a(), true);
		});
		return ActionResultType.SUCCESS;
	}

	public static boolean canSetSpawn(World p_235330_0_) {
		return p_235330_0_.func_230315_m_().func_241510_j_();
	}

	private boolean kickVillagerOutOfBed(World p_226861_1_, BlockPos p_226861_2_) {
		List<VillagerEntity> list = p_226861_1_.func_175647_a(VillagerEntity.class, new AxisAlignedBB(p_226861_2_), LivingEntity::isSleeping);
		if (list.isEmpty())
			return false;
		((VillagerEntity) list.get(0)).func_213366_dy();
		return true;
	}

	public void func_180658_a(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
		super.func_180658_a(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_ * 0.5F);
	}

	public void func_176216_a(IBlockReader p_176216_1_, Entity p_176216_2_) {
		if (p_176216_2_.func_226272_bl_()) {
			super.func_176216_a(p_176216_1_, p_176216_2_);
		} else {
			bounceUp(p_176216_2_);
		}
	}

	private void bounceUp(Entity p_226860_1_) {
		Vector3d vector3d = p_226860_1_.func_213322_ci();
		if (vector3d.field_72448_b < 0.0D) {
			double d0 = (p_226860_1_ instanceof LivingEntity) ? 1.0D : 0.8D;
			p_226860_1_.func_213293_j(vector3d.field_72450_a, -vector3d.field_72448_b * 0.6600000262260437D * d0, vector3d.field_72449_c);
		}
	}

	public BlockState func_196271_a(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
		if (p_196271_2_ == getNeighbourDirection((BedPart) p_196271_1_.func_177229_b((Property) PART), (Direction) p_196271_1_.func_177229_b((Property) field_185512_D)))
			return (p_196271_3_.is((Block) this) && p_196271_3_.func_177229_b((Property) PART) != p_196271_1_.func_177229_b((Property) PART)) ? p_196271_1_.setValue((Property) OCCUPIED, p_196271_3_.func_177229_b((Property) OCCUPIED)) : Blocks.field_150350_a.defaultBlockState();
		return super.func_196271_a(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
	}

	private static Direction getNeighbourDirection(BedPart p_208070_0_, Direction p_208070_1_) {
		return (p_208070_0_ == BedPart.FOOT) ? p_208070_1_ : p_208070_1_.func_176734_d();
	}

	public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, BlockState p_176208_3_, PlayerEntity p_176208_4_) {
		if (!p_176208_1_.isClientSide && p_176208_4_.func_184812_l_()) {
			BedPart bedpart = (BedPart) p_176208_3_.func_177229_b((Property) PART);
			if (bedpart == BedPart.FOOT) {
				BlockPos blockpos = p_176208_2_.func_177972_a(getNeighbourDirection(bedpart, (Direction) p_176208_3_.func_177229_b((Property) field_185512_D)));
				BlockState blockstate = p_176208_1_.getBlockState(blockpos);
				if (blockstate.getBlock() == this && blockstate.func_177229_b((Property) PART) == BedPart.HEAD) {
					p_176208_1_.setBlock(blockpos, Blocks.field_150350_a.defaultBlockState(), 35);
					p_176208_1_.func_217378_a(p_176208_4_, 2001, blockpos, Block.func_196246_j(blockstate));
				}
			}
		}
		super.func_176208_a(p_176208_1_, p_176208_2_, p_176208_3_, p_176208_4_);
	}

	@Nullable
	public BlockState func_196258_a(BlockItemUseContext p_196258_1_) {
		Direction direction = p_196258_1_.func_195992_f();
		BlockPos blockpos = p_196258_1_.getClickedPos();
		BlockPos blockpos1 = blockpos.func_177972_a(direction);
		return p_196258_1_.getLevel().getBlockState(blockpos1).func_196953_a(p_196258_1_) ? defaultBlockState().setValue(field_185512_D, (Comparable) direction) : null;
	}

	public VoxelShape func_220053_a(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		Direction direction = getConnectedDirection(p_220053_1_).func_176734_d();
		switch (direction) {
			case NORTH:
				return NORTH_SHAPE;
			case SOUTH:
				return SOUTH_SHAPE;
			case WEST:
				return WEST_SHAPE;
		}
		return EAST_SHAPE;
	}

	public static Direction getConnectedDirection(BlockState p_226862_0_) {
		Direction direction = (Direction) p_226862_0_.func_177229_b((Property) field_185512_D);
		return (p_226862_0_.func_177229_b((Property) PART) == BedPart.HEAD) ? direction.func_176734_d() : direction;
	}

	@OnlyIn(Dist.CLIENT)
	public static TileEntityMerger.Type getBlockType(BlockState p_226863_0_) {
		BedPart bedpart = (BedPart) p_226863_0_.func_177229_b((Property) PART);
		return (bedpart == BedPart.HEAD) ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
	}

	private static boolean isBunkBed(IBlockReader p_242657_0_, BlockPos p_242657_1_) {
		return p_242657_0_.getBlockState(p_242657_1_.func_177977_b()).getBlock() instanceof net.minecraft.block.BedBlock;
	}

	public static Optional<Vector3d> findStandUpPosition(EntityType<?> p_242652_0_, ICollisionReader p_242652_1_, BlockPos p_242652_2_, float p_242652_3_) {
		Direction direction = (Direction) p_242652_1_.getBlockState(p_242652_2_).func_177229_b((Property) field_185512_D);
		Direction direction1 = direction.func_176746_e();
		Direction direction2 = direction1.func_243532_a(p_242652_3_) ? direction1.func_176734_d() : direction1;
		if (isBunkBed(p_242652_1_, p_242652_2_))
			return findBunkBedStandUpPosition(p_242652_0_, p_242652_1_, p_242652_2_, direction, direction2);
		int[][] aint = bedStandUpOffsets(direction, direction2);
		Optional<Vector3d> optional = findStandUpPositionAtOffset(p_242652_0_, p_242652_1_, p_242652_2_, aint, true);
		return optional.isPresent() ? optional : findStandUpPositionAtOffset(p_242652_0_, p_242652_1_, p_242652_2_, aint, false);
	}

	private static Optional<Vector3d> findBunkBedStandUpPosition(EntityType<?> p_242653_0_, ICollisionReader p_242653_1_, BlockPos p_242653_2_, Direction p_242653_3_, Direction p_242653_4_) {
		int[][] aint = bedSurroundStandUpOffsets(p_242653_3_, p_242653_4_);
		Optional<Vector3d> optional = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint, true);
		if (optional.isPresent())
			return optional;
		BlockPos blockpos = p_242653_2_.func_177977_b();
		Optional<Vector3d> optional1 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, blockpos, aint, true);
		if (optional1.isPresent())
			return optional1;
		int[][] aint1 = bedAboveStandUpOffsets(p_242653_3_);
		Optional<Vector3d> optional2 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint1, true);
		if (optional2.isPresent())
			return optional2;
		Optional<Vector3d> optional3 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint, false);
		if (optional3.isPresent())
			return optional3;
		Optional<Vector3d> optional4 = findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, blockpos, aint, false);
		return optional4.isPresent() ? optional4 : findStandUpPositionAtOffset(p_242653_0_, p_242653_1_, p_242653_2_, aint1, false);
	}

	private static Optional<Vector3d> findStandUpPositionAtOffset(EntityType<?> p_242654_0_, ICollisionReader p_242654_1_, BlockPos p_242654_2_, int[][] p_242654_3_, boolean p_242654_4_) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		for (int[] aint : p_242654_3_) {
			blockpos$mutable.func_181079_c(p_242654_2_.getX() + aint[0], p_242654_2_.getY(), p_242654_2_.getZ() + aint[1]);
			Vector3d vector3d = TransportationHelper.func_242379_a(p_242654_0_, p_242654_1_, (BlockPos) blockpos$mutable, p_242654_4_);
			if (vector3d != null)
				return Optional.of(vector3d);
		}
		return Optional.empty();
	}

	public PushReaction func_149656_h(BlockState p_149656_1_) {
		return PushReaction.DESTROY;
	}

	public BlockRenderType func_149645_b(BlockState p_149645_1_) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	protected void func_206840_a(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		p_206840_1_.func_206894_a(new Property[]{(Property) field_185512_D, PART, OCCUPIED});
	}

	public TileEntity func_196283_a_(IBlockReader p_196283_1_) {
		return new TofuBedTileEntity();
	}

	public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
		super.func_180633_a(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);
		if (!p_180633_1_.isClientSide) {
			BlockPos blockpos = p_180633_2_.func_177972_a((Direction) p_180633_3_.func_177229_b((Property) field_185512_D));
			p_180633_1_.setBlock(blockpos, p_180633_3_.setValue((Property) PART, (Comparable) BedPart.HEAD), 3);
			p_180633_1_.func_230547_a_(p_180633_2_, Blocks.field_150350_a);
			p_180633_3_.func_235734_a_((IWorld) p_180633_1_, p_180633_2_, 3);
		}
	}

	public boolean isBed(BlockState state, IBlockReader world, BlockPos pos, @Nullable Entity player) {
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	public long func_209900_a(BlockState p_209900_1_, BlockPos p_209900_2_) {
		BlockPos blockpos = p_209900_2_.func_177967_a((Direction) p_209900_1_.func_177229_b((Property) field_185512_D), (p_209900_1_.func_177229_b((Property) PART) == BedPart.HEAD) ? 0 : 1);
		return MathHelper.func_180187_c(blockpos.getX(), p_209900_2_.getY(), blockpos.getZ());
	}

	public boolean func_196266_a(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
		return false;
	}

	private static int[][] bedStandUpOffsets(Direction p_242656_0_, Direction p_242656_1_) {
		return (int[][]) ArrayUtils.addAll(bedSurroundStandUpOffsets(p_242656_0_, p_242656_1_), (Object[]) bedAboveStandUpOffsets(p_242656_0_));
	}

	private static int[][] bedSurroundStandUpOffsets(Direction p_242658_0_, Direction p_242658_1_) {
		return new int[][]{{p_242658_1_.func_82601_c(), p_242658_1_.func_82599_e()}, {p_242658_1_.func_82601_c() - p_242658_0_.func_82601_c(), p_242658_1_.func_82599_e() - p_242658_0_.func_82599_e()}, {p_242658_1_.func_82601_c() - p_242658_0_.func_82601_c() * 2, p_242658_1_.func_82599_e() - p_242658_0_.func_82599_e() * 2}, {-p_242658_0_.func_82601_c() * 2, -p_242658_0_.func_82599_e() * 2}, {-p_242658_1_.func_82601_c() - p_242658_0_.func_82601_c() * 2, -p_242658_1_.func_82599_e() - p_242658_0_.func_82599_e() * 2}, {-p_242658_1_.func_82601_c() - p_242658_0_.func_82601_c(), -p_242658_1_.func_82599_e() - p_242658_0_.func_82599_e()}, {-p_242658_1_.func_82601_c(), -p_242658_1_.func_82599_e()}, {-p_242658_1_.func_82601_c() + p_242658_0_.func_82601_c(), -p_242658_1_.func_82599_e() + p_242658_0_.func_82599_e()}, {p_242658_0_.func_82601_c(), p_242658_0_.func_82599_e()}, {p_242658_1_.func_82601_c() + p_242658_0_.func_82601_c(), p_242658_1_.func_82599_e() + p_242658_0_.func_82599_e()}};
	}

	private static int[][] bedAboveStandUpOffsets(Direction p_242655_0_) {
		return new int[][]{{0, 0}, {-p_242655_0_.func_82601_c(), -p_242655_0_.func_82599_e()}};
	}
}
