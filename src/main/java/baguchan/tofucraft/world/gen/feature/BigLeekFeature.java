package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.registry.TofuBlocks;
import com.mojang.serialization.Codec;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BigLeekFeature extends Feature<NoFeatureConfig> {
	private static final Direction[] directionArray = Direction.values();

	private static final BlockState GREEN_LEEK = TofuBlocks.LEEK_GREEN_STEM.func_176223_P();

	private static final BlockState LEEK = TofuBlocks.LEEK_STEM.func_176223_P();

	public BigLeekFeature(Codec<NoFeatureConfig> p_i49919_1_) {
		super(p_i49919_1_);
	}

	public boolean place(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
		BlockPos pos2 = seedReader.func_205770_a(Heightmap.Type.MOTION_BLOCKING, pos);
		if (!seedReader.func_175623_d(pos2))
			return false;
		BlockState blockstate = seedReader.getBlockState(pos2.func_177977_b());
		if (!blockstate.func_203425_a(TofuBlocks.TOFU_TERRAIN))
			return false;
		setLeekBlock((IWorld) seedReader, rand, pos2);
		return true;
	}

	private void setLeekBlock(IWorld world, Random rand, BlockPos pos) {
		int height = 4 + rand.nextInt(4);
		for (int i = 0; i < height; i++) {
			if ((height - i) < height / 2.5D) {
				world.func_180501_a(pos.func_177981_b(i), GREEN_LEEK, 2);
			} else {
				world.func_180501_a(pos.func_177981_b(i), LEEK, 2);
			}
		}
	}
}
