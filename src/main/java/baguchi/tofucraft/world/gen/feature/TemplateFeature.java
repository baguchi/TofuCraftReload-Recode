package baguchi.tofucraft.world.gen.feature;

import baguchi.tofucraft.registry.TofuTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProtectedBlockProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class TemplateFeature extends Feature<NoneFeatureConfiguration> {
	protected final int offsetX;

	protected final int offsetZ;

	private final ResourceLocation[] TEMPLATE;


	public TemplateFeature(Codec<NoneFeatureConfiguration> codec, int offsetX, int offsetZ, ResourceLocation[] template) {
		super(codec);
		this.offsetX = offsetX;
		this.offsetZ = offsetZ;
		TEMPLATE = template;
	}


	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159471_) {
		BlockPos blockpos = p_159471_.origin();
		WorldGenLevel worldgenlevel = p_159471_.level();
		RandomSource random = p_159471_.random();

		Rotation rotation = Rotation.getRandom(random);
		int i = random.nextInt(this.TEMPLATE.length);
		StructureTemplateManager templatemanager = worldgenlevel.getLevel().getServer().getStructureManager();
		StructureTemplate template = templatemanager.getOrCreate(this.TEMPLATE[i]);
		StructurePlaceSettings placementsettings = (new StructurePlaceSettings()).setRotation(rotation).addProcessor(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE));
		BlockPos blockpos2 = (new BlockPos(-this.offsetX / 2, 0, -this.offsetZ / 2)).rotate(rotation);
		BlockPos blockpos3 = (new BlockPos(this.offsetX / 2, 0, this.offsetZ / 2)).rotate(rotation);
		BlockPos blockpos4 = blockpos.offset(blockpos2);
		BlockPos blockpos5 = blockpos.offset(blockpos3);
		placementsettings.setBoundingBox(template.getBoundingBox(placementsettings, blockpos4));
		if (!isTofu(worldgenlevel.getBlockState(blockpos.below())) || !worldgenlevel.isEmptyBlock(blockpos))
			return false;
		template.placeInWorld(worldgenlevel, blockpos4, blockpos5, placementsettings, random, 3);
		return true;
	}

	public static boolean isTofu(BlockState p_159760_) {
		return p_159760_.is(TofuTags.Blocks.TOFU_TERRAIN);
	}
}
