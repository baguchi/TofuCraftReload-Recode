package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.registry.TofuTags;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class SurfaceTofuTemplateFeature extends Feature<NoFeatureConfig> {
	protected final int offsetX;

	protected final int offsetZ;

	private final ResourceLocation[] TEMPLATE;

	public SurfaceTofuTemplateFeature(Codec<NoFeatureConfig> p_i231955_1_, int offsetX, int offsetZ, ResourceLocation[] resourceLocations) {
		super(p_i231955_1_);
		this.offsetX = offsetX;
		this.offsetZ = offsetZ;
		this.TEMPLATE = resourceLocations;
	}

	private boolean isSoil(IWorld worldIn, BlockPos down) {
		return TofuTags.Blocks.TOFU_TERRAIN.contains(worldIn.getBlockState(down).getBlock());
	}

	public boolean place(ISeedReader world, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos pos, NoFeatureConfig p_241855_5_) {
		BlockPos pos2 = world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, pos);
		Rotation rotation = Rotation.getRandom(p_241855_3_);
		int i = p_241855_3_.nextInt(this.TEMPLATE.length);
		TemplateManager templatemanager = world.getLevel().getServer().getStructureManager();
		Template template = templatemanager.getOrCreate(this.TEMPLATE[i]);
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).popProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR);
		BlockPos blockpos2 = (new BlockPos(-this.offsetX / 2, 0, -this.offsetZ / 2)).rotate(rotation);
		BlockPos blockpos3 = (new BlockPos(this.offsetX / 2, 0, this.offsetZ / 2)).rotate(rotation);
		BlockPos blockpos4 = pos2.offset(blockpos2);
		BlockPos blockpos5 = pos2.offset(blockpos3);
		placementsettings.setBoundingBox(template.getBoundingBox(placementsettings, blockpos4));
		if (!isSoil(world, pos2.below()) || !world.isEmptyBlock(pos2))
			return false;
		template.placeInWorld(world, blockpos4, blockpos5, placementsettings, p_241855_3_, 3);
		return true;
	}
}
