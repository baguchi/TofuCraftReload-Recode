package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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

		simpleBlock(TofuBlocks.HELLTOFU);
		simpleBlock(TofuBlocks.HELLTOFU_BRICK);
		simpleBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK);
		simpleBlock(TofuBlocks.SOULTOFU);
		simpleBlock(TofuBlocks.SOULTOFU_BRICK);
		simpleBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK);

		simpleBlock(TofuBlocks.TOFU_TERRAIN);
		simpleBlock(TofuBlocks.ORE_TOFU_DIAMOND);

		crossBlock(TofuBlocks.LEEK);

		simpleBlock(TofuBlocks.TOFU_PORTAL);
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
