package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuParticles;
import baguchan.tofucraft.world.dimension.TofuWorldTeleporter;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ITeleporter;

public class TofuPortalBlock extends BreakableBlock {
	private static final VoxelShape AABB = VoxelShapes.func_197881_a(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D));

	public TofuPortalBlock(Properties props) {
		super(props);
	}

	public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB;
	}

	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		Size size = new Size((IWorld) worldIn, pos);
		if (size.isValid()) {
			size.placePortalBlocks();
			worldIn.func_184133_a(null, pos, SoundEvents.field_193782_bq, SoundCategory.BLOCKS, 0.7F, 1.0F);
			return true;
		}
		Size size1 = new Size((IWorld) worldIn, pos);
		if (size1.isValid()) {
			size1.placePortalBlocks();
			worldIn.func_184133_a(null, pos, SoundEvents.field_193782_bq, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return true;
		}
		return false;
	}

	@Deprecated
	public void func_220069_a(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		boolean good = world.getBlockState(pos.func_177977_b()).func_215704_f();
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			if (!good)
				break;
			BlockState neighboringState = world.getBlockState(pos.func_177972_a(facing));
			good = (neighboringState.getBlock() == TofuBlocks.GRILLEDTOFU || neighboringState == state);
		}
		if (!good) {
			world.func_175669_a(2001, pos, Block.func_196246_j(state));
			world.func_180501_a(pos, TofuBlocks.SOYMILK.func_176223_P(), 3);
		}
	}

	public void func_196262_a(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn.func_242280_ah()) {
			entityIn.func_242279_ag();
		} else {
			attemptSendPlayer(entityIn, false);
		}
	}

	private static RegistryKey<World> getDestination(Entity entity) {
		RegistryKey<World> tofu_world = RegistryKey.func_240903_a_(Registry.field_239699_ae_, new ResourceLocation("tofucraft:tofu_world"));
		return (entity.level.func_234923_W_() != tofu_world) ? tofu_world : World.field_234918_g_;
	}

	public static void attemptSendPlayer(Entity entity, boolean forcedEntry) {
		if (!entity.func_70089_S() || entity.level.isClientSide())
			return;
		if (entity.func_184218_aH() || !entity.func_184222_aU())
			return;
		if (!forcedEntry && entity.func_242280_ah())
			return;
		RegistryKey<World> destination = getDestination(entity);
		ServerWorld serverWorld = entity.level.func_73046_m().func_71218_a(destination);
		if (serverWorld == null)
			return;
		entity.level.func_217381_Z().func_76320_a("tofu_portal");
		entity.func_242279_ag();
		entity.changeDimension(serverWorld, (ITeleporter) new TofuWorldTeleporter());
		entity.level.func_217381_Z().func_76319_b();
	}

	@OnlyIn(Dist.CLIENT)
	public void func_180655_c(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		int random = rand.nextInt(100);
		if (random == 0)
			worldIn.func_184134_a(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.field_187810_eg, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		for (int i = 0; i < 4; i++) {
			double xPos = (pos.getX() + rand.nextFloat());
			double yPos = pos.getY() + 1.0D;
			double zPos = (pos.getZ() + rand.nextFloat());
			double xSpeed = (rand.nextFloat() - 0.5D) * 0.5D;
			double ySpeed = rand.nextFloat();
			double zSpeed = (rand.nextFloat() - 0.5D) * 0.5D;
			worldIn.func_195594_a((IParticleData) TofuParticles.TOFU_PORTAL, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
		}
	}

	public static class Size {
		private static final int MAX_SIZE = 12;

		private static final int MIN_SIZE = 1;

		private final IWorld world;

		private boolean valid = false;

		private BlockPos nw;

		private BlockPos se;

		public Size(IWorld world, BlockPos pos) {
			this.world = world;
			int east = getDistanceUntilEdge(pos, Direction.EAST);
			int west = getDistanceUntilEdge(pos, Direction.WEST);
			int north = getDistanceUntilEdge(pos, Direction.NORTH);
			int south = getDistanceUntilEdge(pos, Direction.SOUTH);
			int width = east + west - 1;
			int length = north + south - 1;
			if (width > 12 || length > 12)
				return;
			if (width < 1 || length < 1)
				return;
			BlockPos neCorner = pos.func_177965_g(east).func_177964_d(north);
			BlockPos nwCorner = pos.func_177985_f(west).func_177964_d(north);
			BlockPos seCorner = pos.func_177965_g(east).func_177970_e(south);
			BlockPos swCorner = pos.func_177985_f(west).func_177970_e(south);
			this.nw = nwCorner.func_177982_a(1, 0, 1);
			this.se = seCorner.func_177982_a(-1, 0, -1);
			int wallWidth = width + 2;
			int wallLength = length + 2;
			for (int y = 0; y <= 1; y++) {
				for (int x = 0; x < wallWidth; x++) {
					for (int z = 0; z < wallLength; z++) {
						if (((y == 0 && x != 0 && z != 0 && x != wallWidth - 1 && z != wallLength - 1) || (y == 1 && (x == 0 || z == 0 || x == wallWidth - 1 || z == wallLength - 1))) &&
								!isTofuBlock(world.getBlockState(nwCorner.func_177977_b().func_177982_a(x, y, z))))
							return;
					}
				}
			}
			this.valid = true;
		}

		int getDistanceUntilEdge(BlockPos pos, Direction facing) {
			int i;
			for (i = 0; i < 9; i++) {
				BlockPos blockpos = pos.func_177967_a(facing, i);
				if (!isEmptyBlock(this.world.getBlockState(blockpos)) || !isTofuBlock(this.world.getBlockState(blockpos.func_177977_b())))
					break;
			}
			BlockState state = this.world.getBlockState(pos.func_177967_a(facing, i));
			return isTofuBlock(state) ? i : 0;
		}

		boolean isEmptyBlock(BlockState state) {
			return (state.getBlock() == TofuBlocks.SOYMILK);
		}

		boolean isTofuBlock(BlockState state) {
			return (state.getBlock() == TofuBlocks.GRILLEDTOFU);
		}

		public boolean isValid() {
			return this.valid;
		}

		void placePortalBlocks() {
			for (BlockPos portalPos : BlockPos.Mutable.func_218278_a(this.nw, this.se))
				this.world.func_180501_a(portalPos, TofuBlocks.TOFU_PORTAL.func_176223_P(), 2);
		}
	}
}
