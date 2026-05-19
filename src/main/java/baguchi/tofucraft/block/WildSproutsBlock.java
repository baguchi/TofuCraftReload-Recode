package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuTags;
import baguchi.tofucraft.world.gen.features.ModTreeFeatures;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;

import java.util.Optional;

public class WildSproutsBlock extends VegetationBlock implements BonemealableBlock {
	public static final MapCodec<WildSproutsBlock> CODEC = simpleCodec(WildSproutsBlock::new);

	public WildSproutsBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends VegetationBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(TofuTags.Blocks.SUPPORTS_ROUGH_TOFU_PLANT);
	}

	@Override
	protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
		return new ItemStack(TofuItems.SPROUTS);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
		BlockState blockstate = levelReader.getBlockState(blockPos.below());
		return blockstate.is(TofuTags.Blocks.SUPPORTS_TOFU_PLANT);
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		return randomSource.nextFloat() < 0.1F - level.getPathfindingCostFromLightLevels(blockPos) * 0.1F;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		growSprout(serverLevel, randomSource, blockPos, blockState);
	}

	public void growSprout(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		serverLevel.removeBlock(blockPos, true);
		serverLevel.removeBlock(blockPos.above(), true);
		this.getFeature(serverLevel).ifPresent((p_256352_) -> {
			BlockGrowFeatureEvent event = EventHooks.fireBlockGrowFeature(serverLevel, randomSource, blockPos, p_256352_);
			if (event.isCanceled()) return;
			p_256352_.value().place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos);
		});

	}

	private Optional<? extends Holder<ConfiguredFeature<?, ?>>> getFeature(LevelReader p_256589_) {
		return p_256589_.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(ModTreeFeatures.SPROUT);
	}
}
