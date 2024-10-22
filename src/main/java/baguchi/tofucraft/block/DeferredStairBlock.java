package baguchi.tofucraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DeferredStairBlock extends StairBlock {
	private final DeferredBlock<? extends Block> base;

	public DeferredStairBlock(DeferredBlock<? extends Block> block, BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties);
		this.base = block;
	}

	@Override
	public float getExplosionResistance() {
		return this.base.get().getExplosionResistance();
	}

}
