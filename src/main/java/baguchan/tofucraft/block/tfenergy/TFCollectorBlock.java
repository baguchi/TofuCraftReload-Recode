package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.blockentity.tfenergy.TFCollectorBlockEntity;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class TFCollectorBlock extends BaseEntityBlock {
	public static final MapCodec<TFCollectorBlock> CODEC = simpleCodec(TFCollectorBlock::new);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public TFCollectorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	public RenderShape getRenderShape(BlockState p_48727_) {
		return RenderShape.MODEL;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
		p_48725_.add(LIT);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new TFCollectorBlockEntity(p_153215_, p_153216_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152382_, BlockState p_152383_, BlockEntityType<T> p_152384_) {
		return createTicker(p_152382_, p_152384_, TofuBlockEntitys.TF_COLLECTOR.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends TFCollectorBlockEntity> p_151990_) {
		return createTickerHelper(p_151989_, p_151990_, TFCollectorBlockEntity::tick);
	}
}