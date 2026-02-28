package baguchi.tofucraft.world.gen.treedecorators;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuTreeDecoratorType;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;

public class SproutTopDecorator extends TreeDecorator {
	public static final MapCodec<SproutTopDecorator> CODEC = MapCodec.unit(SproutTopDecorator::new);

	public SproutTopDecorator() {
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return TofuTreeDecoratorType.SPROUT_TOP.get();
	}

	@Override
	public void place(TreeDecorator.Context context) {
		List<BlockPos> leaves = context.leaves();
		List<BlockPos> logs = context.logs();
		if (!logs.isEmpty()) {
			if (logs.size() > 4) {
				int extra = Mth.floor(logs.size() / 4F);

				for (int i = logs.size() - 1; i >= logs.size() - 1 - extra; i--) {
					context.setBlock(logs.get(i), TofuBlocks.YELLOW_SPROUT_STEM.get().defaultBlockState());
				}
			} else {
				context.setBlock(logs.getLast(), TofuBlocks.YELLOW_SPROUT_STEM.get().defaultBlockState());
			}
		}

	}
}
