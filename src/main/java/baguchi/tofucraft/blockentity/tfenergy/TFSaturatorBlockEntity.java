package baguchi.tofucraft.blockentity.tfenergy;

import baguchi.tofucraft.block.HarderCondition;
import baguchi.tofucraft.block.tfenergy.TFCollectorBlock;
import baguchi.tofucraft.blockentity.tfenergy.base.WorkerBaseBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuTags;
import baguchi.tofucraft.utils.TileScanner;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TFSaturatorBlockEntity extends WorkerBaseBlockEntity {
	public static final double COST_TF_PER_TICK = 0.008D;
	public static final int RADIUS = 16;
	private static final int POWER = 20;
	private int step;
	private int tick;
	private int interval;

	public TFSaturatorBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_SATURATOR.get(), p_155229_, p_155230_, 1000);
	}

	public TFSaturatorBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int energyMax) {
		super(p_155228_, p_155229_, p_155230_, energyMax);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFSaturatorBlockEntity tfSaturate) {
		if (level.isClientSide()) return;

		int j = 0;

		boolean worked = false;

		if (level.hasNeighborSignal(blockPos) && tfSaturate.energy >= 20 && level.getBlockState(blockPos.above()).isAir()) {
			j = 20;
		}

		if (j > 0) {
			worked = true;
			tfSaturate.drain(j, false);
		}

		if (blockState.getValue(TFCollectorBlock.LIT) != worked) {
			level.setBlock(blockPos, blockState.setValue(TFCollectorBlock.LIT, worked), 2);
		}
		if (worked) {
			tfSaturate.tick++;

			if (tfSaturate.tick >= tfSaturate.interval) {
				tfSaturate.saturateAround();
				tfSaturate.tick = 0;
				tfSaturate.interval = tfSaturate.getNextInterval();
			}
			tfSaturate.setChanged();
		}
	}

	protected int getNextInterval() {
		return 200 + level.getRandom().nextInt(400);
	}

	public void saturateAround() {
		TileScanner scanner = new TileScanner(this.level, this.worldPosition);

		int len = Math.min(step * 2, RADIUS);
		scanner.scan(len, TileScanner.Method.full, new TileScanner.Impl() {
			@Override
			public void apply(Level level, BlockPos pos) {
				BlockState state = level.getBlockState(pos);
				if (state.getBlock() instanceof HarderCondition tofuBlock) {
					tofuBlock.harder(level, state, pos);
					if (level instanceof ServerLevel serverLevel) {
						double x = pos.getX() + (1.0F - level.getRandom().nextDouble() * 2.0F);
						double y = pos.getY() + (1.0F - level.getRandom().nextDouble() * 2.0F);
						double z = pos.getZ() + (1.0F - level.getRandom().nextDouble() * 2.0F);

						serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 8, 0, 0, 0, 0);
					}
				}
			}
		});

		if (++step * 2 > RADIUS) step = 1;
	}

	public static boolean isValidTF(Level p_207910_, BlockPos p_207911_, BlockPos p_207912_) {
		return p_207910_.getBlockState(p_207911_.offset(p_207912_)).is(TofuTags.Blocks.TF_TRANSMITTER);
	}
}
