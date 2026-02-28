package baguchi.tofucraft.world.gen.trunk;

import baguchi.tofucraft.registry.TofuTrunkPlacerType;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class SproutTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<SproutTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((i) -> trunkPlacerParts(i).apply(i, SproutTrunkPlacer::new));

	public SproutTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
		super(baseHeight, heightRandA, heightRandB);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TofuTrunkPlacerType.SPROUT_TRUNK_PLACER.get();
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
		placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);
		List<FoliagePlacer.FoliageAttachment> attachments = Lists.newArrayList();
		Direction leanDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
		int leanHeight = treeHeight - random.nextInt(4) - 1;
		int leanSteps = 3 - random.nextInt(3);
		BlockPos.MutableBlockPos logPos = new BlockPos.MutableBlockPos();
		int tx = origin.getX();
		int tz = origin.getZ();
		OptionalInt ey = OptionalInt.empty();

		for (int yo = 0; yo < treeHeight; ++yo) {
			int yy = origin.getY() + yo;
			if (yo >= leanHeight && leanSteps > 0) {
				tx += leanDirection.getStepX();
				tz += leanDirection.getStepZ();
				--leanSteps;
			}

			if (this.placeLog(level, trunkSetter, random, logPos.set(tx, yy, tz), config)) {
				ey = OptionalInt.of(yy + 1);
			}
		}

		if (ey.isPresent()) {
			attachments.add(new FoliagePlacer.FoliageAttachment(new BlockPos(tx, ey.getAsInt(), tz), 1, false));
		}
		return attachments;
	}
}
