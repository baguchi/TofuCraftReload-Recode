package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class BlockstateGenerator extends BlockStateProvider {
	public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, TofuCraftReload.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(TofuBlocks.KINUTOFU);
		simpleBlock(TofuBlocks.MOMENTOFU);
		simpleBlock(TofuBlocks.ISHITOFU);
		simpleBlock(TofuBlocks.ISHITOFU_BRICK);
		simpleBlock(TofuBlocks.ISHITOFU_SMOOTH_BRICK);
		simpleBlock(TofuBlocks.ISHITOFU_CHISELED_BRICK);
		simpleBlock(TofuBlocks.METALTOFU);
		simpleBlock(TofuBlocks.DIAMONDTOFU);
		simpleBlock(TofuBlocks.GRILLEDTOFU);
		simpleBlock(TofuBlocks.ZUNDATOFU);
		simpleBlock(TofuBlocks.MISOTOFU);

		simpleBlock(TofuBlocks.HELLTOFU);
		simpleBlock(TofuBlocks.HELLTOFU_BRICK);
		simpleBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK);
		simpleBlock(TofuBlocks.SOULTOFU);
		simpleBlock(TofuBlocks.SOULTOFU_BRICK);
		simpleBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK);

		stairs(TofuBlocks.TOFUSTAIR_KINU, TofuBlocks.KINUTOFU);
		stairs(TofuBlocks.TOFUSTAIR_MOMEN, TofuBlocks.MOMENTOFU);
		stairs(TofuBlocks.TOFUSTAIR_ISHI, TofuBlocks.ISHITOFU);
		stairs(TofuBlocks.TOFUSTAIR_METAL, TofuBlocks.METALTOFU);
		stairs(TofuBlocks.TOFUSTAIR_ZUNDA, TofuBlocks.ZUNDATOFU);
		stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_MISO, TofuBlocks.MISOTOFU);

		slab(TofuBlocks.TOFUSLAB_KINU, TofuBlocks.KINUTOFU);
		slab(TofuBlocks.TOFUSLAB_MOMEN, TofuBlocks.MOMENTOFU);
		slab(TofuBlocks.TOFUSLAB_ISHI, TofuBlocks.ISHITOFU);
		slab(TofuBlocks.TOFUSLAB_METAL, TofuBlocks.METALTOFU);
		slab(TofuBlocks.TOFUSLAB_ZUNDA, TofuBlocks.ZUNDATOFU);
		slab(TofuBlocks.TOFUSLAB_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_MISO, TofuBlocks.MISOTOFU);

		torchBlock(TofuBlocks.TOFUTORCH_KINU, TofuBlocks.WALLTOFUTORCH_KINU);
		torchBlock(TofuBlocks.TOFUTORCH_MOMEN, TofuBlocks.WALLTOFUTORCH_MOMEN);
		torchBlock(TofuBlocks.TOFUTORCH_ISHI, TofuBlocks.WALLTOFUTORCH_ISHI);
		torchBlock(TofuBlocks.TOFUTORCH_METAL, TofuBlocks.WALLTOFUTORCH_METAL);

		simpleBlock(TofuBlocks.TOFU_TERRAIN);
		simpleBlock(TofuBlocks.TOFUSLATE);
		simpleBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE);
		simpleBlock(TofuBlocks.ORE_TOFU_DIAMOND);
		simpleBlock(TofuBlocks.TOFU_BEDROCK);

		logBlock(TofuBlocks.LEEK_GREEN_STEM);
		logBlock(TofuBlocks.LEEK_STEM);
		crossBlock(TofuBlocks.ZUNDATOFU_MUSHROOM);
		logBlock(TofuBlocks.TOFU_STEM);
		simpleBlock(TofuBlocks.TOFU_STEM_PLANKS);

		crossBlock(TofuBlocks.SAPLING_TOFU);
		simpleBlock(TofuBlocks.LEAVES_TOFU);

		crossBlock(TofuBlocks.LEEK);

		simpleBlock(TofuBlocks.TOFU_PORTAL);

		simpleBlock(TofuBlocks.EGGTOFU);
		stairs(TofuBlocks.TOFUSTAIR_EGGTOFU, TofuBlocks.EGGTOFU);
		slab(TofuBlocks.TOFUSLAB_EGGTOFU, TofuBlocks.EGGTOFU);
	}

	public void torchBlock(Block block, Block wall) {
		ModelFile torch = models().torch(name(block), texture(name(block)));
		ModelFile torchwall = models().torchWall(name(wall), texture(name(block)));
		simpleBlock(block, torch);
		getVariantBuilder(wall).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(torchwall)
						.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
						.build());
	}

	public void stairs(StairBlock block, Block fullBlock) {
		stairsBlock(block, texture(name(fullBlock)));
	}

	public void slab(SlabBlock block, Block fullBlock) {
		slabBlock(block, texture(name(fullBlock)), texture(name(fullBlock)));
	}

	public void crossBlock(Block block) {
		crossBlock(block, models().cross(name(block), texture(name(block))));
	}

	private void crossBlock(Block block, ModelFile model) {
		getVariantBuilder(block).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(model)
						.build());
	}

	protected ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	protected String name(Block block) {
		return block.getRegistryName().getPath();
	}

	@Nonnull
	@Override
	public String getName() {
		return "TofuCraftReload blockstates and block models";
	}
}
