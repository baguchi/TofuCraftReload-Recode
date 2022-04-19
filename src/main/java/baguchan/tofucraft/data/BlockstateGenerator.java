package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class BlockstateGenerator extends BlockStateProvider {
	public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, TofuCraftReload.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(TofuBlocks.KINUTOFU.get());
		simpleBlock(TofuBlocks.MOMENTOFU.get());
		simpleBlock(TofuBlocks.ISHITOFU.get());
		simpleBlock(TofuBlocks.ISHITOFU_BRICK.get());
		simpleBlock(TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		simpleBlock(TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		simpleBlock(TofuBlocks.METALTOFU.get());
		simpleBlock(TofuBlocks.DIAMONDTOFU.get());
		simpleBlock(TofuBlocks.GRILLEDTOFU.get());
		simpleBlock(TofuBlocks.ZUNDATOFU.get());
		simpleBlock(TofuBlocks.MISOTOFU.get());

		simpleBlock(TofuBlocks.HELLTOFU.get());
		simpleBlock(TofuBlocks.HELLTOFU_BRICK.get());
		simpleBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		simpleBlock(TofuBlocks.SOULTOFU.get());
		simpleBlock(TofuBlocks.SOULTOFU_BRICK.get());
		simpleBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());

		stairs(TofuBlocks.TOFUSTAIR_KINU.get(), TofuBlocks.KINUTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_MOMEN.get(), TofuBlocks.MOMENTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_ISHI.get(), TofuBlocks.ISHITOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_METAL.get(), TofuBlocks.METALTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_ZUNDA.get(), TofuBlocks.ZUNDATOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK.get(), TofuBlocks.ISHITOFU_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_HELLBRICK.get(), TofuBlocks.HELLTOFU_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_SOULBRICK.get(), TofuBlocks.SOULTOFU_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_MISO.get(), TofuBlocks.MISOTOFU.get());

		slab(TofuBlocks.TOFUSLAB_KINU.get(), TofuBlocks.KINUTOFU.get());
		slab(TofuBlocks.TOFUSLAB_MOMEN.get(), TofuBlocks.MOMENTOFU.get());
		slab(TofuBlocks.TOFUSLAB_ISHI.get(), TofuBlocks.ISHITOFU.get());
		slab(TofuBlocks.TOFUSLAB_METAL.get(), TofuBlocks.METALTOFU.get());
		slab(TofuBlocks.TOFUSLAB_ZUNDA.get(), TofuBlocks.ZUNDATOFU.get());
		slab(TofuBlocks.TOFUSLAB_ISHIBRICK.get(), TofuBlocks.ISHITOFU_BRICK.get());
		slab(TofuBlocks.TOFUSLAB_HELLBRICK.get(), TofuBlocks.HELLTOFU_BRICK.get());
		slab(TofuBlocks.TOFUSLAB_SOULBRICK.get(), TofuBlocks.SOULTOFU_BRICK.get());
		slab(TofuBlocks.TOFUSLAB_MISO.get(), TofuBlocks.MISOTOFU.get());

		wall(TofuBlocks.TOFUFENCE_KINU, TofuBlocks.KINUTOFU);
		wall(TofuBlocks.TOFUFENCE_MOMEN, TofuBlocks.MOMENTOFU);
		wall(TofuBlocks.TOFUFENCE_ISHI, TofuBlocks.ISHITOFU);
		wall(TofuBlocks.TOFUFENCE_METAL, TofuBlocks.METALTOFU);
		wall(TofuBlocks.TOFUFENCE_HELL, TofuBlocks.HELLTOFU);
		wall(TofuBlocks.TOFUFENCE_SOUL, TofuBlocks.SOULTOFU);

		ancientFormatDoor(TofuBlocks.TOFUDOOR_KINU, "kinu");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_MOMEN, "momen");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_ISHI, "ishi");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_METAL, "metal");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_HELL, "hell");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_SOUL, "soul");

		torchBlock(TofuBlocks.TOFUTORCH_KINU.get(), TofuBlocks.WALLTOFUTORCH_KINU.get());
		torchBlock(TofuBlocks.TOFUTORCH_MOMEN.get(), TofuBlocks.WALLTOFUTORCH_MOMEN.get());
		torchBlock(TofuBlocks.TOFUTORCH_ISHI.get(), TofuBlocks.WALLTOFUTORCH_ISHI.get());
		torchBlock(TofuBlocks.TOFUTORCH_METAL.get(), TofuBlocks.WALLTOFUTORCH_METAL.get());

		simpleBlock(TofuBlocks.TOFU_TERRAIN.get());
		simpleBlock(TofuBlocks.TOFUSLATE.get());
		simpleBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		simpleBlock(TofuBlocks.ORE_TOFU_DIAMOND.get());
		simpleBlock(TofuBlocks.TOFU_BEDROCK.get());

		logBlock(TofuBlocks.LEEK_GREEN_STEM.get());
		logBlock(TofuBlocks.LEEK_STEM.get());
		crossBlock(TofuBlocks.ZUNDATOFU_MUSHROOM.get());
		logBlock(TofuBlocks.TOFU_STEM.get());
		simpleBlock(TofuBlocks.TOFU_STEM_PLANKS.get());

		crossBlock(TofuBlocks.SAPLING_TOFU.get());
		simpleBlock(TofuBlocks.LEAVES_TOFU.get());

		crossBlock(TofuBlocks.LEEK.get());

		simpleBlock(TofuBlocks.TOFU_PORTAL.get());

		simpleBlock(TofuBlocks.EGGTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_EGG.get(), TofuBlocks.EGGTOFU.get());
		slab(TofuBlocks.TOFUSLAB_EGG.get(), TofuBlocks.EGGTOFU.get());

		crossBlock(TofuBlocks.ANTENNA_BASIC.get());
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

	public void door(Supplier<? extends DoorBlock> block, String name) {
		doorBlock(block.get(), texture(name + "_door_bottom"), texture(name + "_door_top"));
	}

	public void ancientFormatDoor(Supplier<? extends DoorBlock> block, String name) {
		doorBlock(block.get(), texture("tofudoor_" + name + "_lower"), texture("tofudoor_" + name + "_upper"));
	}

	public void wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		wallBlock(wall.get(), texture(name(fullBlock.get())));
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
