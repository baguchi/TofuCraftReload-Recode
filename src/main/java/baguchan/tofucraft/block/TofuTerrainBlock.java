package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.placement.TofuWorldPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
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
import java.util.Optional;

public class TofuTerrainBlock extends Block implements BonemealableBlock {
	public TofuTerrainBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos p_50898_, BlockState p_50899_, boolean p_50900_) {
		return false;
	}

	public boolean isBonemealSuccess(Level p_53697_, RandomSource p_53698_, BlockPos p_53699_, BlockState p_53700_) {
		return true;
	}

	public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_, BlockState p_221273_) {
		BlockPos blockpos = p_221272_.above();
		BlockState blockstate = TofuBlocks.LEEK.get().defaultBlockState();
		Optional<Holder.Reference<PlacedFeature>> optional = p_221270_.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(TofuWorldPlacements.LEEK_BONEMEAL);

		label49:
		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(p_221271_.nextInt(3) - 1, (p_221271_.nextInt(3) - 1) * p_221271_.nextInt(3) / 2, p_221271_.nextInt(3) - 1);
				if (!p_221270_.getBlockState(blockpos1.below()).is(this) || p_221270_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_221270_, blockpos1)) {
					continue label49;
				}
			}

			BlockState blockstate1 = p_221270_.getBlockState(blockpos1);
			if (blockstate1.is(blockstate.getBlock()) && blockstate.getBlock() instanceof BonemealableBlock && p_221271_.nextInt(10) == 0) {
				((BonemealableBlock) blockstate.getBlock()).performBonemeal(p_221270_, p_221271_, blockpos1, blockstate1);
			}

			if (blockstate1.isAir()) {
				Holder<PlacedFeature> holder;
				if (p_221271_.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = p_221270_.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}

					holder = ((RandomPatchConfiguration) list.get(0).config()).feature();
				} else {
					if (!optional.isPresent()) {
						continue;
					}

					holder = optional.get();
				}

				holder.value().place(p_221270_, p_221270_.getChunkSource().getGenerator(), p_221271_, blockpos1);
			}
		}
	}

	@Override
	public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		return toolAction == ToolActions.HOE_TILL ? TofuBlocks.TOFU_FARMLAND.get().defaultBlockState() : null;
	}
}