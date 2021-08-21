package baguchan.tofucraft.world.gen;

import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.OreVeinifier;
import net.minecraft.world.level.levelgen.RandomSource;
import net.minecraft.world.level.levelgen.SimpleRandomSource;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class TofuOreVeinifier extends OreVeinifier {
	private static final float RARITY = 1.0F;
	private static final float RIDGE_NOISE_FREQUENCY = 4.0F;
	private static final float THICKNESS = 0.08F;
	private static final float VEININESS_THRESHOLD = 0.5F;
	private static final double VEININESS_FREQUENCY = 1.5D;
	private static final int EDGE_ROUNDOFF_BEGIN = 20;
	private static final double MAX_EDGE_ROUNDOFF = 0.2D;
	private static final float VEIN_SOLIDNESS = 0.7F;
	private static final float MIN_RICHNESS = 0.1F;
	private static final float MAX_RICHNESS = 0.3F;
	private static final float MAX_RICHNESS_THRESHOLD = 0.6F;
	private static final float CHANCE_OF_RAW_ORE_BLOCK = 0.02F;
	private static final float SKIP_ORE_IF_GAP_NOISE_IS_BELOW = -0.3F;
	private final int veinMaxY;
	private final int veinMinY;
	private final BlockState normalBlock;
	private final NormalNoise veininessNoiseSource;
	private final NormalNoise veinANoiseSource;
	private final NormalNoise veinBNoiseSource;
	private final NormalNoise gapNoise;
	private final int cellWidth;
	private final int cellHeight;

	public TofuOreVeinifier(long p_158806_, BlockState p_158807_, int p_158808_, int p_158809_, int p_158810_) {
		super(p_158806_, p_158807_, p_158808_, p_158809_, p_158810_);
		Random var7 = new Random(p_158806_);
		this.normalBlock = p_158807_;
		this.veininessNoiseSource = NormalNoise.create(new SimpleRandomSource(var7.nextLong()), -8, new double[]{1.0D});
		this.veinANoiseSource = NormalNoise.create(new SimpleRandomSource(var7.nextLong()), -7, new double[]{1.0D});
		this.veinBNoiseSource = NormalNoise.create(new SimpleRandomSource(var7.nextLong()), -7, new double[]{1.0D});
		this.gapNoise = NormalNoise.create(new SimpleRandomSource(0L), -5, new double[]{1.0D});
		this.cellWidth = p_158808_;
		this.cellHeight = p_158809_;
		this.veinMaxY = Stream.of(VeinType.values()).mapToInt((p_158842_) -> {
			return p_158842_.maxY;
		}).max().orElse(p_158810_);
		this.veinMinY = Stream.of(VeinType.values()).mapToInt((p_158818_) -> {
			return p_158818_.minY;
		}).min().orElse(p_158810_);
	}

	private boolean isVein(double p_158812_, double p_158813_) {
		double var5 = Math.abs(1.0D * p_158812_) - 0.07999999821186066D;
		double var7 = Math.abs(1.0D * p_158813_) - 0.07999999821186066D;
		return Math.max(var5, var7) < 0.0D;
	}

	public BlockState oreVeinify(RandomSource p_158820_, int p_158821_, int p_158822_, int p_158823_, double p_158824_, double p_158825_, double p_158826_) {
		BlockState var11 = this.normalBlock;
		VeinType var12 = this.getVeinType(p_158824_, p_158822_);
		if (var12 == null) {
			return var11;
		} else if (p_158820_.nextFloat() > 0.7F) {
			return var11;
		} else if (this.isVein(p_158825_, p_158826_)) {
			double var13 = Mth.clampedMap(Math.abs(p_158824_), 0.5D, 0.6000000238418579D, 0.10000000149011612D, 0.30000001192092896D);
			if ((double) p_158820_.nextFloat() < var13 && this.gapNoise.getValue((double) p_158821_, (double) p_158822_, (double) p_158823_) > -0.30000001192092896D) {
				return p_158820_.nextFloat() < 0.02F ? var12.rawOreBlock : var12.ore;
			} else {
				return var12.filler;
			}
		} else {
			return var11;
		}
	}

	@Nullable
	private VeinType getVeinType(double p_158815_, int p_158816_) {
		VeinType var4 = p_158815_ > 0.0D ? VeinType.COPPER : VeinType.IRON;
		int var5 = var4.maxY - p_158816_;
		int var6 = p_158816_ - var4.minY;
		if (var6 >= 0 && var5 >= 0) {
			int var7 = Math.min(var5, var6);
			double var8 = Mth.clampedMap((double) var7, 0.0D, 20.0D, -0.2D, 0.0D);
			return Math.abs(p_158815_) + var8 < 0.5D ? null : var4;
		} else {
			return null;
		}
	}

	static enum VeinType {
		COPPER(Blocks.COPPER_ORE.defaultBlockState(), Blocks.RAW_COPPER_BLOCK.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), 0, 50),
		IRON(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), Blocks.RAW_IRON_BLOCK.defaultBlockState(), Blocks.TUFF.defaultBlockState(), -60, -8);

		final BlockState ore;
		final BlockState rawOreBlock;
		final BlockState filler;
		final int minY;
		final int maxY;

		private VeinType(BlockState p_158867_, BlockState p_158868_, BlockState p_158869_, int p_158870_, int p_158871_) {
			this.ore = p_158867_;
			this.rawOreBlock = p_158868_;
			this.filler = p_158869_;
			this.minY = p_158870_;
			this.maxY = p_158871_;
		}
	}
}
