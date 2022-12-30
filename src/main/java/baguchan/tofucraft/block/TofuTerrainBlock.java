package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.placement.TofuWorldPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TofuTerrainBlock extends Block implements BonemealableBlock {
	public TofuTerrainBlock(Properties properties) {
		super(properties);
	}

	public boolean isValidBonemealTarget(BlockGetter p_53692_, BlockPos p_53693_, BlockState p_53694_, boolean p_53695_) {
		return p_53692_.getBlockState(p_53693_.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level p_53697_, RandomSource p_53698_, BlockPos p_53699_, BlockState p_53700_) {
		return true;
	}

	public void performBonemeal(ServerLevel p_53687_, RandomSource p_53688_, BlockPos p_53689_, BlockState p_53690_) {
		BlockPos blockpos = p_53689_.above();
		BlockState blockstate = TofuBlocks.LEEK.get().defaultBlockState();

		label46:
		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(p_53688_.nextInt(3) - 1, (p_53688_.nextInt(3) - 1) * p_53688_.nextInt(3) / 2, p_53688_.nextInt(3) - 1);
				if (!p_53687_.getBlockState(blockpos1.below()).is(this) || p_53687_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_53687_, blockpos1)) {
					continue label46;
				}
			}

			BlockState blockstate1 = p_53687_.getBlockState(blockpos1);

			if (blockstate1.isAir()) {
				PlacedFeature placedfeature;
				if (p_53688_.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = p_53687_.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}

					placedfeature = ((RandomPatchConfiguration) list.get(0).config()).feature().value();
					placedfeature.place(p_53687_, p_53687_.getChunkSource().getGenerator(), p_53688_, blockpos1);
				}
				if (p_53688_.nextInt(6) == 0) {
					placedfeature = TofuWorldPlacements.LEEK_BONEMEAL.get();
					placedfeature.place(p_53687_, p_53687_.getChunkSource().getGenerator(), p_53688_, blockpos1);
				}
			}
		}

	}

	@Override
	public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		return toolAction == ToolActions.HOE_TILL ? TofuBlocks.TOFU_FARMLAND.get().defaultBlockState() : null;
	}
}