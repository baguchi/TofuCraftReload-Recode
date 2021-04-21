package baguchan.tofucraft.world.gen.feature;

import baguchan.tofucraft.registry.TofuTags;
import com.mojang.serialization.Codec;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

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
		return TofuTags.Blocks.TOFU_TERRAIN.func_230235_a_(worldIn.getBlockState(down).getBlock());
	}

	public boolean place(ISeedReader world, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos pos, NoFeatureConfig p_241855_5_) {
		BlockPos pos2 = world.func_205770_a(Heightmap.Type.MOTION_BLOCKING, pos);
		Rotation rotation = Rotation.func_222466_a(p_241855_3_);
		int i = p_241855_3_.nextInt(this.TEMPLATE.length);
		TemplateManager templatemanager = world.func_201672_e().func_184163_y();
		Template template = templatemanager.func_200219_b(this.TEMPLATE[i]);
		PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(rotation).func_215222_a((StructureProcessor) BlockIgnoreStructureProcessor.field_215206_c);
		BlockPos blockpos2 = (new BlockPos(-this.offsetX / 2, 0, -this.offsetZ / 2)).func_190942_a(rotation);
		BlockPos blockpos3 = (new BlockPos(this.offsetX / 2, 0, this.offsetZ / 2)).func_190942_a(rotation);
		BlockPos blockpos4 = pos2.func_177971_a((Vector3i) blockpos2);
		BlockPos blockpos5 = pos2.func_177971_a((Vector3i) blockpos3);
		placementsettings.func_186223_a(template.func_215388_b(placementsettings, blockpos4));
		if (!isSoil((IWorld) world, pos2.func_177977_b()) || !world.func_175623_d(pos2))
			return false;
		template.func_237146_a_((IServerWorld) world, blockpos4, blockpos5, placementsettings, p_241855_3_, 3);
		return true;
	}
}
