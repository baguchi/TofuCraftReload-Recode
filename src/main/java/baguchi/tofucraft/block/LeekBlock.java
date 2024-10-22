package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuTags;
import baguchi.tofucraft.world.gen.features.TofuWorldFeatures;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;

import java.util.Optional;

public class LeekBlock extends BushBlock implements BonemealableBlock {

	public static final MapCodec<LeekBlock> CODEC = simpleCodec(LeekBlock::new);
	public LeekBlock(Properties p_51021_) {
		super(p_51021_);
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(TofuTags.Blocks.TOFU_TERRAIN) || p_51042_.is(TofuBlocks.MOMENTOFU.get());
	}

	private Optional<? extends Holder<ConfiguredFeature<?, ?>>> getFeature(LevelReader p_256589_) {
		return p_256589_.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(TofuWorldFeatures.BIG_LEEK);
	}

	public void growLeek(ServerLevel p_54860_, BlockPos p_54861_, BlockState p_54862_, RandomSource p_54863_) {
		p_54860_.removeBlock(p_54861_, true);
		this.getFeature(p_54860_).ifPresent((p_256352_) -> {
			BlockGrowFeatureEvent event = EventHooks.fireBlockGrowFeature(p_54860_, p_54863_, p_54861_, p_256352_);
			if (event.isCanceled()) return;
			p_256352_.value().place(p_54860_, p_54860_.getChunkSource().getGenerator(), p_54863_, p_54861_);
		});
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader p_256655_, BlockPos p_256553_, BlockState p_256213_) {
		BlockState blockstate = p_256655_.getBlockState(p_256553_.below());
		return blockstate.is(TofuTags.Blocks.TOFU_TERRAIN) || blockstate.is(TofuBlocks.MOMENTOFU.get());
	}

	@Override
	public boolean isBonemealSuccess(Level p_50901_, RandomSource p_50902_, BlockPos p_50903_, BlockState p_50904_) {
		return (double) p_50902_.nextFloat() < 0.125D;
	}

	@Override
	public void performBonemeal(ServerLevel p_50893_, RandomSource p_50894_, BlockPos p_50895_, BlockState p_50896_) {
		growLeek(p_50893_, p_50895_, p_50896_, p_50894_);
	}
}
