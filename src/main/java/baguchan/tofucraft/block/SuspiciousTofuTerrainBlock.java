package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.BrushableBlock;

public class SuspiciousTofuTerrainBlock extends BrushableBlock {
	public SuspiciousTofuTerrainBlock(Properties properties) {
		super(TofuBlocks.TOFU_TERRAIN.get(), SoundEvents.WOOL_HIT, SoundEvents.WOOL_BREAK, properties);
	}
}
