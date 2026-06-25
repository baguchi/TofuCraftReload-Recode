package baguchi.tofucraft.data.generator.models;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.CandleTofuCakeBlock;
import baguchi.tofucraft.data.provider.TofuBlockstateModelProvider;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TofuBlockModels extends TofuBlockstateModelProvider {
	private Supplier<CandleTofuCakeBlock> block;

	public TofuBlockModels(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
		super(blockStateOutput, itemModelOutput, modelOutput);
	}
	@Override
	public void run() {
		TofuBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach((family) -> family(family.getBaseBlock()).generateFor(family));


		createTrivialCube(TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		this.createTrivialCube(TofuBlocks.METAL_TOFU_GRATE.get());
		createTrivialCube(TofuBlocks.METAL_TOFU_LUMP.get());
		this.createBarsAndItem(TofuBlocks.METAL_TOFU_BARS.get());
		createTrivialCube(TofuBlocks.DIAMONDTOFU.get());
		createTrivialCube(TofuBlocks.TOFU_GEM_BLOCK.get());
		createTrivialCube(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get());
		createTrivialCube(TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.get());

		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.GRILLED_TOFU.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);

		createTrivialCube(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());

		family(TofuBlocks.ZUNDATOFU_BRICK.get())
				.stairs(TofuBlocks.TOFU_STAIR_ZUNDABRICK.get())
				.slab(TofuBlocks.TOFU_SLAB_ZUNDABRICK.get());

		family(TofuBlocks.EGGTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_EGG.get())
				.slab(TofuBlocks.TOFU_SLAB_EGG.get())
				.wall(TofuBlocks.TOFU_FENCE_EGG.get())
				.door(TofuBlocks.TOFU_DOOR_EGG.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_EGG.get());

		family(TofuBlocks.SESAMETOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_SESAME.get())
				.slab(TofuBlocks.TOFU_SLAB_SESAME.get())
				.wall(TofuBlocks.TOFU_FENCE_SESAME.get())
				.door(TofuBlocks.TOFU_DOOR_SESAME.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_SESAME.get());

		family(TofuBlocks.EGGTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFU_STAIR_EGGBRICK.get())
				.slab(TofuBlocks.TOFU_SLAB_EGGBRICK.get());

		createTrivialCube(TofuBlocks.EGGTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.EGGTOFU_CHISELED_BRICK.get());

		family(TofuBlocks.DRIEDTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFU_STAIR_DRIEDBRICK.get())
				.slab(TofuBlocks.TOFU_SLAB_DRIEDBRICK.get());

		createTrivialCube(TofuBlocks.DRIEDTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.DRIEDTOFU_CHISELED_BRICK.get());


		createTrivialCube(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.HELLTOFU_CHISELED_BRICK.get());
		createTrivialCube(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.SOULTOFU_CHISELED_BRICK.get());
		createTrivialCube(TofuBlocks.MINCEDTOFU.get());

		family(TofuBlocks.HELLTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFU_STAIR_HELLBRICK.get())
				.slab(TofuBlocks.TOFU_SLAB_HELLBRICK.get());

		family(TofuBlocks.SOULTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFU_STAIR_SOULBRICK.get())
				.slab(TofuBlocks.TOFU_SLAB_SOULBRICK.get());

		family(TofuBlocks.KINUTOFU.get())
				.donateModelTo(TofuBlocks.KINUTOFU.get(), TofuBlocks.WAXED_KINUTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_KINU.get())
				.slab(TofuBlocks.TOFU_SLAB_KINU.get())
				.wall(TofuBlocks.TOFU_FENCE_KINU.get())
				.door(TofuBlocks.TOFU_DOOR_KINU.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_KINU.get());

		family(TofuBlocks.MOMENTOFU.get())
				.donateModelTo(TofuBlocks.MOMENTOFU.get(), TofuBlocks.WAXED_MOMENTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_MOMEN.get())
				.slab(TofuBlocks.TOFU_SLAB_MOMEN.get())
				.wall(TofuBlocks.TOFU_FENCE_MOMEN.get())
				.door(TofuBlocks.TOFU_DOOR_MOMEN.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_MOMEN.get());

		family(TofuBlocks.ISHITOFU.get())
				.donateModelTo(TofuBlocks.ISHITOFU.get(), TofuBlocks.WAXED_ISHITOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_ISHI.get())
				.slab(TofuBlocks.TOFU_SLAB_ISHI.get())
				.wall(TofuBlocks.TOFU_FENCE_ISHI.get())
				.door(TofuBlocks.TOFU_DOOR_ISHI.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_ISHI.get());

		family(TofuBlocks.METALTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_METAL.get())
				.slab(TofuBlocks.TOFU_SLAB_METAL.get())
				.wall(TofuBlocks.TOFU_FENCE_METAL.get())
				.door(TofuBlocks.TOFU_DOOR_METAL.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_METAL.get());

		family(TofuBlocks.HELLTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_HELL.get())
				.slab(TofuBlocks.TOFU_SLAB_HELL.get())
				.wall(TofuBlocks.TOFU_FENCE_HELL.get())
				.door(TofuBlocks.TOFU_DOOR_HELL.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_HELL.get());
		family(TofuBlocks.SOULTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_SOUL.get())
				.slab(TofuBlocks.TOFU_SLAB_SOUL.get())
				.wall(TofuBlocks.TOFU_FENCE_SOUL.get())
				.door(TofuBlocks.TOFU_DOOR_SOUL.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_SOUL.get());
		familyWithExistingFullBlockWithTop(TofuBlocks.GRILLED_TOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_GRILLED.get())
				.slab(TofuBlocks.TOFU_SLAB_GRILLED.get())
				.wall(TofuBlocks.TOFU_FENCE_GRILLED.get())
				.door(TofuBlocks.TOFU_DOOR_GRILLED.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_GRILLED.get());

		family(TofuBlocks.ZUNDATOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_ZUNDA.get())
				.slab(TofuBlocks.TOFU_SLAB_ZUNDA.get())
				.wall(TofuBlocks.TOFU_FENCE_ZUNDA.get())
				.door(TofuBlocks.TOFU_DOOR_ZUNDA.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get());

		family(TofuBlocks.ISHITOFU_BRICK.get())
				.stairs(TofuBlocks.TOFU_STAIR_ISHIBRICK.get())
				.slab(TofuBlocks.TOFU_SLAB_ISHIBRICK.get());

		family(TofuBlocks.MISOTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_MISO.get())
				.slab(TofuBlocks.TOFU_SLAB_MISO.get())
				.wall(TofuBlocks.TOFU_FENCE_MISO.get())
				.door(TofuBlocks.TOFU_DOOR_MISO.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_MISO.get());

		family(TofuBlocks.DRIEDTOFU.get())
				.stairs(TofuBlocks.TOFU_STAIR_DRIED.get())
				.slab(TofuBlocks.TOFU_SLAB_DRIED.get())
				.wall(TofuBlocks.TOFU_FENCE_DRIED.get())
				.door(TofuBlocks.TOFU_DOOR_DRIED.get())
				.trapdoor(TofuBlocks.TOFU_TRAPDOOR_DRIED.get());

		this.createNormalTorch(TofuBlocks.TOFU_TORCH_KINU.get(), TofuBlocks.WALL_TOFU_TORCH_KINU.get());
		this.createNormalTorch(TofuBlocks.TOFU_TORCH_MOMEN.get(), TofuBlocks.WALL_TOFU_TORCH_MOMEN.get());
		this.createNormalTorch(TofuBlocks.TOFU_TORCH_ISHI.get(), TofuBlocks.WALL_TOFU_TORCH_ISHI.get());
		this.createNormalTorch(TofuBlocks.TOFU_TORCH_METAL.get(), TofuBlocks.WALL_TOFU_TORCH_METAL.get());
		this.createNormalTorch(TofuBlocks.TOFU_TORCH_GRILLED.get(), TofuBlocks.WALL_TOFU_TORCH_GRILLED.get());
		this.createNormalTorch(TofuBlocks.TOFU_TORCH_ZUNDA.get(), TofuBlocks.WALL_TOFU_TORCH_ZUNDA.get());
		this.createNormalTorch(TofuBlocks.TOFU_TORCH_HELL.get(), TofuBlocks.WALL_TOFU_TORCH_HELL.get());
		this.createNormalTorch(TofuBlocks.TOFU_TORCH_SOUL.get(), TofuBlocks.WALL_TOFU_TORCH_SOUL.get());


		createLadder(TofuBlocks.TOFU_LADDER_KINU.get());
		createLadder(TofuBlocks.TOFU_LADDER_MOMEN.get());
		createLadder(TofuBlocks.TOFU_LADDER_ISHI.get());
		createLadder(TofuBlocks.TOFU_LADDER_ISHIBRICK.get());
		createLadder(TofuBlocks.TOFU_LADDER_METAL.get());
		createLadder(TofuBlocks.TOFU_LADDER_GRILLED.get());
		createLadder(TofuBlocks.TOFU_LADDER_ZUNDA.get());
		createLadder(TofuBlocks.TOFU_LADDER_HELL.get());
		createLadder(TofuBlocks.TOFU_LADDER_SOUL.get());

		createTrivialCube(TofuBlocks.TOFU_TERRAIN.get());
		createTrivialCube(TofuBlocks.MABOU_TERRAIN.get());
		createTrivialCube(TofuBlocks.TOFUSLATE.get());
		createTrivialCube(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		createTrivialCube(TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.get());
		createTrivialCube(TofuBlocks.ORE_TOFU_DIAMOND.get());
		createTrivialCube(TofuBlocks.ORE_TOFUGEM.get());
		createTrivialCube(TofuBlocks.TOFU_BEDROCK.get());
		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.ISHI_TOFU_STEM.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.TOFU_STEM.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);

		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.YELLOW_SPROUT_STEM.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.SPROUT_STEM.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);

		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.LEEK_STEM.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.LEEK_GREEN_STEM.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);

		this.createShelf(TofuBlocks.LEEK_SHELF.get(), TofuBlocks.LEEK_STEM.get());
		this.createShelf(TofuBlocks.LEEK_GREEN_SHELF.get(), TofuBlocks.LEEK_GREEN_STEM.get());
		this.createShelf(TofuBlocks.TOFU_STEM_SHELF.get(), TofuBlocks.TOFU_STEM.get());
		this.createShelf(TofuBlocks.SPROUT_SHELF.get(), TofuBlocks.SPROUT_STEM.get());

		this.createCrossBlockWithDefaultItem(TofuBlocks.ZUNDA_TOFU_MUSHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createTrivialCube(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get());
		this.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_TOFU.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(TofuBlocks.LEAVES_TOFU.get(), TexturedModel.LEAVES);

		this.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_APRICOT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(TofuBlocks.LEAVES_APRICOT.get(), TexturedModel.LEAVES);

		this.createCrossBlockWithDefaultItem(TofuBlocks.TOFU_FLOWER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createCrossBlockWithDefaultItem(TofuBlocks.LEEK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createDoublePlantWithDefaultItem(TofuBlocks.TALL_LEEK.get(), BlockModelGenerators.PlantType.NOT_TINTED);

		createTofuCakeBlock(TofuBlocks.TOFU_CAKE.get());
		createTofuCakeBlock(TofuBlocks.ZUNDA_TOFU_CAKE.get());
		createTofuCakeBlock(TofuBlocks.SOYCHEESE_TART.get());

		this.createTrivialCube(TofuBlocks.SOY_CHEESE_BLOCK.get());
		this.createTrivialCube(TofuBlocks.SOY_NETHER_CHEESE_BLOCK.get());
		this.createTrivialCube(TofuBlocks.SOY_SOUL_CHEESE_BLOCK.get());

		this.createTofuCandleCake(Blocks.DYED_CANDLE.white(), TofuBlocks.WHITE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.orange(), TofuBlocks.ORANGE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.magenta(), TofuBlocks.MAGENTA_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lightBlue(), TofuBlocks.LIGHT_BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.yellow(), TofuBlocks.YELLOW_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lime(), TofuBlocks.LIME_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.pink(), TofuBlocks.PINK_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.gray(), TofuBlocks.GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lightGray(), TofuBlocks.LIGHT_GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.cyan(), TofuBlocks.CYAN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.purple(), TofuBlocks.PURPLE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.blue(), TofuBlocks.BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.brown(), TofuBlocks.BROWN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.green(), TofuBlocks.GREEN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.red(), TofuBlocks.RED_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.black(), TofuBlocks.BLACK_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFU_CAKE.get());

		this.createTofuCandleCake(Blocks.DYED_CANDLE.white(), TofuBlocks.WHITE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.orange(), TofuBlocks.ORANGE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.magenta(), TofuBlocks.MAGENTA_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lightBlue(), TofuBlocks.LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.yellow(), TofuBlocks.YELLOW_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lime(), TofuBlocks.LIME_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.pink(), TofuBlocks.PINK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.gray(), TofuBlocks.GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lightGray(), TofuBlocks.LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.cyan(), TofuBlocks.CYAN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.purple(), TofuBlocks.PURPLE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.blue(), TofuBlocks.BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.brown(), TofuBlocks.BROWN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.green(), TofuBlocks.GREEN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.red(), TofuBlocks.RED_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.black(), TofuBlocks.BLACK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDA_TOFU_CAKE.get());

		this.createTofuCandleCake(Blocks.DYED_CANDLE.white(), TofuBlocks.WHITE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.orange(), TofuBlocks.ORANGE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.magenta(), TofuBlocks.MAGENTA_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lightBlue(), TofuBlocks.LIGHT_BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.yellow(), TofuBlocks.YELLOW_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lime(), TofuBlocks.LIME_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.pink(), TofuBlocks.PINK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.gray(), TofuBlocks.GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.lightGray(), TofuBlocks.LIGHT_GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.cyan(), TofuBlocks.CYAN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.purple(), TofuBlocks.PURPLE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.blue(), TofuBlocks.BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.brown(), TofuBlocks.BROWN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.green(), TofuBlocks.GREEN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.red(), TofuBlocks.RED_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.DYED_CANDLE.black(), TofuBlocks.BLACK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());

		//createSingleCarpetBlocks(TofuBlocks.YUBA.get());
		createTrivialCube(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());

		this.registerSimpleFlatItemModel(TofuBlocks.TOFU_METAL_CHAIN.get().asItem());
		this.createAxisAlignedPillarBlockCustomModel(TofuBlocks.TOFU_METAL_CHAIN.get(), plainVariant(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_METAL_CHAIN.get())));

		createLantern(TofuBlocks.TOFU_METAL_LANTERN.get());
		createLantern(TofuBlocks.TOFU_METAL_SOUL_LANTERN.get());
		createTranslucentCube(TofuBlocks.ZUNDAMA_BLOCK.get());
		this.createCrossBlockWithDefaultItem(TofuBlocks.ANTENNA_BASIC.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createCrossBlockWithDefaultItem(TofuBlocks.ANTENNA_ADVANCE.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialCube(TofuBlocks.TF_COLLECTOR.get());
		createTrivialCube(TofuBlocks.SALT_BLOCK.get());
		createTrivialCube(TofuBlocks.OKARA_BLOCK.get());
		createTrivialCube(TofuBlocks.GIANT_OKARA_DONUT.get());

		this.createCropBlock(TofuBlocks.SOYBEAN.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		this.createCropBlock(TofuBlocks.SOYBEAN_NETHER.get(), BlockStateProperties.AGE_7, 0, 1, 1, 1, 2, 2, 2, 3);
		this.createCropBlock(TofuBlocks.SOYBEAN_SOUL.get(), BlockStateProperties.AGE_7, 0, 1, 1, 1, 2, 2, 2, 3);

		this.createCropBlock(TofuBlocks.CHILI_CROP.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		this.createCropBlock(TofuBlocks.RICE_CROP.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		this.createCropBlock(TofuBlocks.LEEK_CROP.get(), BlockStateProperties.AGE_3, 0, 1, 1, 2);
		this.createCropBlock(TofuBlocks.SPROUTS.get(), BlockStateProperties.AGE_3, 0, 1, 2, 3);
		this.createSingleCropBlock(TofuBlocks.RICE_ROOT.get());
		this.createSingleCropBlock(TofuBlocks.WILD_SPROUTS.get());
		this.registerSimpleFlatItemModel(TofuBlocks.WILD_SPROUTS.get());


		this.createTofuFarmland();
		this.createTofuPortalBlock();
		this.createTofunianState(TofuBlocks.TOFUNIAN_STATUE.get(), TofuBlocks.TOFUSLATE.get());
		this.createChest(TofuBlocks.TOFUCHEST.get(), TofuBlocks.ISHITOFU.get(), TofuCraftReload.prefix("tofuchest"), false);
		this.createTofuBed(TofuBlocks.TOFUBED.get());
		this.itemModelOutput.accept(TofuBlocks.TOFU_FARMLAND.asItem(), ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_FARMLAND.get())));
		this.itemModelOutput.accept(TofuBlocks.SALTPAN.asItem(), ItemModelUtils.plainModel(TofuCraftReload.prefix("block/saltpan_inventory")));
		this.itemModelOutput.accept(TofuBlocks.SPROUTSJAR.asItem(), ItemModelUtils.plainModel(TofuCraftReload.prefix("block/sprouts_jar_inventory")));
	}
}
