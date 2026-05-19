package baguchi.tofucraft.data.generator.models;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.CandleTofuCakeBlock;
import baguchi.tofucraft.data.provider.TofuBlockstateModelProvider;
import baguchi.tofucraft.data.resources.builder.TofuBlockFamilies;
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

		this.createRotatedPillarWithHorizontalVariant(TofuBlocks.GRILLEDTOFU.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);

		createTrivialCube(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());

		family(TofuBlocks.ZUNDATOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_ZUNDABRICK.get())
				.slab(TofuBlocks.TOFUSLAB_ZUNDABRICK.get());

		family(TofuBlocks.EGGTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_EGG.get())
				.slab(TofuBlocks.TOFUSLAB_EGG.get());

		family(TofuBlocks.SESAMETOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_SESAME.get())
				.slab(TofuBlocks.TOFUSLAB_SESAME.get());

		family(TofuBlocks.EGGTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_EGGBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_EGGBRICK.get());

		createTrivialCube(TofuBlocks.EGGTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.EGGTOFU_CHISELED_BRICK.get());

		family(TofuBlocks.DRIEDTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_DRIEDBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_DRIEDBRICK.get());

		createTrivialCube(TofuBlocks.DRIEDTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.DRIEDTOFU_CHISELED_BRICK.get());


		createTrivialCube(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.HELLTOFU_CHISELED_BRICK.get());
		createTrivialCube(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.SOULTOFU_CHISELED_BRICK.get());
		createTrivialCube(TofuBlocks.MINCEDTOFU.get());

		family(TofuBlocks.HELLTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_HELLBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_HELLBRICK.get());

		family(TofuBlocks.SOULTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_SOULBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_SOULBRICK.get());

		family(TofuBlocks.KINUTOFU.get())
				.donateModelTo(TofuBlocks.KINUTOFU.get(), TofuBlocks.WAXED_KINUTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_KINU.get())
				.slab(TofuBlocks.TOFUSLAB_KINU.get())
				.wall(TofuBlocks.TOFUFENCE_KINU.get())
				.door(TofuBlocks.TOFUDOOR_KINU.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_KINU.get());

		family(TofuBlocks.MOMENTOFU.get())
				.donateModelTo(TofuBlocks.MOMENTOFU.get(), TofuBlocks.WAXED_MOMENTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_MOMEN.get())
				.slab(TofuBlocks.TOFUSLAB_MOMEN.get())
				.wall(TofuBlocks.TOFUFENCE_MOMEN.get())
				.door(TofuBlocks.TOFUDOOR_MOMEN.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_MOMEN.get());

		family(TofuBlocks.ISHITOFU.get())
				.donateModelTo(TofuBlocks.ISHITOFU.get(), TofuBlocks.WAXED_ISHITOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_ISHI.get())
				.slab(TofuBlocks.TOFUSLAB_ISHI.get())
				.wall(TofuBlocks.TOFUFENCE_ISHI.get())
				.door(TofuBlocks.TOFUDOOR_ISHI.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_ISHI.get());

		family(TofuBlocks.METALTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_METAL.get())
				.slab(TofuBlocks.TOFUSLAB_METAL.get())
				.wall(TofuBlocks.TOFUFENCE_METAL.get())
				.door(TofuBlocks.TOFUDOOR_METAL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_METAL.get());

		family(TofuBlocks.HELLTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_HELL.get())
				.slab(TofuBlocks.TOFUSLAB_HELL.get())
				.wall(TofuBlocks.TOFUFENCE_HELL.get())
				.door(TofuBlocks.TOFUDOOR_HELL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_HELL.get());
		family(TofuBlocks.SOULTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_SOUL.get())
				.slab(TofuBlocks.TOFUSLAB_SOUL.get())
				.wall(TofuBlocks.TOFUFENCE_SOUL.get())
				.door(TofuBlocks.TOFUDOOR_SOUL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_SOUL.get());
		familyWithExistingFullBlockWithTop(TofuBlocks.GRILLEDTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_GRILLED.get())
				.slab(TofuBlocks.TOFUSLAB_GRILLED.get())
				.wall(TofuBlocks.TOFUFENCE_GRILLED.get())
				.door(TofuBlocks.TOFUDOOR_GRILLED.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_GRILLED.get());

		family(TofuBlocks.ZUNDATOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_ZUNDA.get())
				.slab(TofuBlocks.TOFUSLAB_ZUNDA.get())
				.wall(TofuBlocks.TOFUFENCE_ZUNDA.get())
				.door(TofuBlocks.TOFUDOOR_ZUNDA.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_ZUNDA.get());

		family(TofuBlocks.ISHITOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_ISHIBRICK.get());

		family(TofuBlocks.MISOTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_MISO.get())
				.slab(TofuBlocks.TOFUSLAB_MISO.get());

		family(TofuBlocks.DRIEDTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_DRIED.get())
				.slab(TofuBlocks.TOFUSLAB_DRIED.get());

		this.createNormalTorch(TofuBlocks.TOFUTORCH_KINU.get(), TofuBlocks.WALLTOFUTORCH_KINU.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_MOMEN.get(), TofuBlocks.WALLTOFUTORCH_MOMEN.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_ISHI.get(), TofuBlocks.WALLTOFUTORCH_ISHI.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_METAL.get(), TofuBlocks.WALLTOFUTORCH_METAL.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_GRILLED.get(), TofuBlocks.WALLTOFUTORCH_GRILLED.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_ZUNDA.get(), TofuBlocks.WALLTOFUTORCH_ZUNDA.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_HELL.get(), TofuBlocks.WALLTOFUTORCH_HELL.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_SOUL.get(), TofuBlocks.WALLTOFUTORCH_SOUL.get());


		createLadder(TofuBlocks.TOFULADDER_KINU.get());
		createLadder(TofuBlocks.TOFULADDER_MOMEN.get());
		createLadder(TofuBlocks.TOFULADDER_ISHI.get());
		createLadder(TofuBlocks.TOFULADDER_ISHIBRICK.get());
		createLadder(TofuBlocks.TOFULADDER_METAL.get());
		createLadder(TofuBlocks.TOFULADDER_GRILLED.get());
		createLadder(TofuBlocks.TOFULADDER_ZUNDA.get());
		createLadder(TofuBlocks.TOFULADDER_HELL.get());
		createLadder(TofuBlocks.TOFULADDER_SOUL.get());

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

		this.createHangingSign(TofuBlocks.LEEK_STEM.get(), TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get());
		this.createHangingSign(TofuBlocks.LEEK_GREEN_STEM.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get());
		this.createHangingSign(TofuBlocks.TOFU_STEM.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get());
		this.createHangingSign(TofuBlocks.SPROUT_STEM.get(), TofuBlocks.SPROUT_HANGING_SIGN.get(), TofuBlocks.SPROUT_WALL_HANGING_SIGN.get());

		this.createShelf(TofuBlocks.LEEK_SHELF.get(), TofuBlocks.LEEK_STEM.get());
		this.createShelf(TofuBlocks.LEEK_GREEN_SHELF.get(), TofuBlocks.LEEK_GREEN_STEM.get());
		this.createShelf(TofuBlocks.TOFU_STEM_SHELF.get(), TofuBlocks.TOFU_STEM.get());
		this.createShelf(TofuBlocks.SPROUT_SHELF.get(), TofuBlocks.SPROUT_STEM.get());

		this.createCrossBlockWithDefaultItem(TofuBlocks.ZUNDATOFU_MUSHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createTrivialCube(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get());
		this.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_TOFU.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(TofuBlocks.LEAVES_TOFU.get(), TexturedModel.LEAVES);

		this.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_APRICOT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(TofuBlocks.LEAVES_APRICOT.get(), TexturedModel.LEAVES);

		this.createCrossBlockWithDefaultItem(TofuBlocks.TOFU_FLOWER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createCrossBlockWithDefaultItem(TofuBlocks.LEEK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createDoublePlantWithDefaultItem(TofuBlocks.TALL_LEEK.get(), BlockModelGenerators.PlantType.NOT_TINTED);

		createTofuCakeBlock(TofuBlocks.TOFUCAKE.get());
		createTofuCakeBlock(TofuBlocks.ZUNDATOFUCAKE.get());
		createTofuCakeBlock(TofuBlocks.SOYCHEESE_TART.get());

		this.createTrivialCube(TofuBlocks.SOY_CHEESE_BLOCK.get());
		this.createTrivialCube(TofuBlocks.SOY_NETHER_CHEESE_BLOCK.get());
		this.createTrivialCube(TofuBlocks.SOY_SOUL_CHEESE_BLOCK.get());

		this.createTofuCandleCake(Blocks.WHITE_CANDLE, TofuBlocks.WHITE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.ORANGE_CANDLE, TofuBlocks.ORANGE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.MAGENTA_CANDLE, TofuBlocks.MAGENTA_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_BLUE_CANDLE, TofuBlocks.LIGHT_BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.YELLOW_CANDLE, TofuBlocks.YELLOW_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIME_CANDLE, TofuBlocks.LIME_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PINK_CANDLE, TofuBlocks.PINK_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GRAY_CANDLE, TofuBlocks.GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_GRAY_CANDLE, TofuBlocks.LIGHT_GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CYAN_CANDLE, TofuBlocks.CYAN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PURPLE_CANDLE, TofuBlocks.PURPLE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLUE_CANDLE, TofuBlocks.BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BROWN_CANDLE, TofuBlocks.BROWN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GREEN_CANDLE, TofuBlocks.GREEN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.RED_CANDLE, TofuBlocks.RED_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLACK_CANDLE, TofuBlocks.BLACK_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());

		this.createTofuCandleCake(Blocks.WHITE_CANDLE, TofuBlocks.WHITE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.ORANGE_CANDLE, TofuBlocks.ORANGE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.MAGENTA_CANDLE, TofuBlocks.MAGENTA_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_BLUE_CANDLE, TofuBlocks.LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.YELLOW_CANDLE, TofuBlocks.YELLOW_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIME_CANDLE, TofuBlocks.LIME_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PINK_CANDLE, TofuBlocks.PINK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GRAY_CANDLE, TofuBlocks.GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_GRAY_CANDLE, TofuBlocks.LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CYAN_CANDLE, TofuBlocks.CYAN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PURPLE_CANDLE, TofuBlocks.PURPLE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLUE_CANDLE, TofuBlocks.BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BROWN_CANDLE, TofuBlocks.BROWN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GREEN_CANDLE, TofuBlocks.GREEN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.RED_CANDLE, TofuBlocks.RED_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLACK_CANDLE, TofuBlocks.BLACK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());

		this.createTofuCandleCake(Blocks.WHITE_CANDLE, TofuBlocks.WHITE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.ORANGE_CANDLE, TofuBlocks.ORANGE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.MAGENTA_CANDLE, TofuBlocks.MAGENTA_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.LIGHT_BLUE_CANDLE, TofuBlocks.LIGHT_BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.YELLOW_CANDLE, TofuBlocks.YELLOW_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.LIME_CANDLE, TofuBlocks.LIME_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.PINK_CANDLE, TofuBlocks.PINK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.GRAY_CANDLE, TofuBlocks.GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.LIGHT_GRAY_CANDLE, TofuBlocks.LIGHT_GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.CYAN_CANDLE, TofuBlocks.CYAN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.PURPLE_CANDLE, TofuBlocks.PURPLE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.BLUE_CANDLE, TofuBlocks.BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.BROWN_CANDLE, TofuBlocks.BROWN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.GREEN_CANDLE, TofuBlocks.GREEN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.RED_CANDLE, TofuBlocks.RED_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.BLACK_CANDLE, TofuBlocks.BLACK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
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
		this.createTofuBed();
		this.itemModelOutput.accept(TofuBlocks.TOFU_FARMLAND.asItem(), ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_FARMLAND.get())));
		this.itemModelOutput.accept(TofuBlocks.SALTPAN.asItem(), ItemModelUtils.plainModel(TofuCraftReload.prefix("block/saltpan_inventory")));
		this.itemModelOutput.accept(TofuBlocks.SPROUTSJAR.asItem(), ItemModelUtils.plainModel(TofuCraftReload.prefix("block/sprouts_jar_inventory")));
	}
}
