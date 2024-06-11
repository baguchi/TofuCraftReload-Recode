package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.CandleTofuCakeBlock;
import baguchan.tofucraft.block.TofuCakeBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockstateGenerator extends BlockStateProvider {
	private Supplier<CandleTofuCakeBlock> block;

	public BlockstateGenerator(PackOutput gen, ExistingFileHelper exFileHelper) {
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
		simpleBlock(TofuBlocks.TOFU_GEM_BLOCK.get());
		simpleBlock(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get());
		logBlock(TofuBlocks.GRILLEDTOFU.get());
		simpleBlock(TofuBlocks.ZUNDATOFU.get());

		simpleBlock(TofuBlocks.ZUNDATOFU_BRICK.get());
		simpleBlock(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_ZUNDABRICK, TofuBlocks.ZUNDATOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_ZUNDABRICK, TofuBlocks.ZUNDATOFU_BRICK);

		simpleBlock(TofuBlocks.MISOTOFU.get());
		simpleBlock(TofuBlocks.DRIEDTOFU.get());

		simpleBlock(TofuBlocks.EGGTOFU);
		stairs(TofuBlocks.TOFUSTAIR_EGG, TofuBlocks.EGGTOFU);
		slab(TofuBlocks.TOFUSLAB_EGG, TofuBlocks.EGGTOFU);

		simpleBlock(TofuBlocks.SESAMETOFU);
		stairs(TofuBlocks.TOFUSTAIR_SESAME, TofuBlocks.SESAMETOFU);
		slab(TofuBlocks.TOFUSLAB_SESAME, TofuBlocks.SESAMETOFU);

		simpleBlock(TofuBlocks.EGGTOFU_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_EGGBRICK, TofuBlocks.EGGTOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_EGGBRICK, TofuBlocks.EGGTOFU_BRICK);

		simpleBlock(TofuBlocks.HELLTOFU.get());
		simpleBlock(TofuBlocks.HELLTOFU_BRICK.get());
		simpleBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		simpleBlock(TofuBlocks.SOULTOFU.get());
		simpleBlock(TofuBlocks.SOULTOFU_BRICK.get());
		simpleBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		simpleBlock(TofuBlocks.MINCEDTOFU.get());

		stairs(TofuBlocks.TOFUSTAIR_KINU, TofuBlocks.KINUTOFU);
		stairs(TofuBlocks.TOFUSTAIR_MOMEN, TofuBlocks.MOMENTOFU);
		stairs(TofuBlocks.TOFUSTAIR_ISHI, TofuBlocks.ISHITOFU);
		stairs(TofuBlocks.TOFUSTAIR_METAL, TofuBlocks.METALTOFU);
		stairsBlock(TofuBlocks.TOFUSTAIR_GRILLED.get(), texture(name(TofuBlocks.MOMENTOFU.get())), texture(name(TofuBlocks.GRILLEDTOFU.get()) + "_top"), texture(name(TofuBlocks.GRILLEDTOFU.get()) + "_top"));
		stairs(TofuBlocks.TOFUSTAIR_ZUNDA, TofuBlocks.ZUNDATOFU);
		stairs(TofuBlocks.TOFUSTAIR_HELL, TofuBlocks.HELLTOFU);
		stairs(TofuBlocks.TOFUSTAIR_SOUL, TofuBlocks.SOULTOFU);
		stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_MISO, TofuBlocks.MISOTOFU);
		stairs(TofuBlocks.TOFUSTAIR_DRIED, TofuBlocks.DRIEDTOFU);

		slab(TofuBlocks.TOFUSLAB_KINU, TofuBlocks.KINUTOFU);
		slab(TofuBlocks.TOFUSLAB_MOMEN, TofuBlocks.MOMENTOFU);
		slab(TofuBlocks.TOFUSLAB_ISHI, TofuBlocks.ISHITOFU);
		slab(TofuBlocks.TOFUSLAB_METAL, TofuBlocks.METALTOFU);
		slab(TofuBlocks.TOFUSLAB_GRILLED, texture(name(TofuBlocks.MOMENTOFU.get())), texture(name(TofuBlocks.GRILLEDTOFU.get()) + "_top"), texture(name(TofuBlocks.GRILLEDTOFU.get()) + "_top"));
		slab(TofuBlocks.TOFUSLAB_ZUNDA, TofuBlocks.ZUNDATOFU);
		slab(TofuBlocks.TOFUSLAB_HELL, TofuBlocks.HELLTOFU);
		slab(TofuBlocks.TOFUSLAB_SOUL, TofuBlocks.SOULTOFU);
		slab(TofuBlocks.TOFUSLAB_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_MISO, TofuBlocks.MISOTOFU);
		slab(TofuBlocks.TOFUSLAB_DRIED, TofuBlocks.DRIEDTOFU);

		wall(TofuBlocks.TOFUFENCE_KINU, TofuBlocks.KINUTOFU);
		wall(TofuBlocks.TOFUFENCE_MOMEN, TofuBlocks.MOMENTOFU);
		wall(TofuBlocks.TOFUFENCE_ISHI, TofuBlocks.ISHITOFU);
		wall(TofuBlocks.TOFUFENCE_METAL, TofuBlocks.METALTOFU);
		wall(TofuBlocks.TOFUFENCE_HELL, TofuBlocks.HELLTOFU);
		wall(TofuBlocks.TOFUFENCE_SOUL, TofuBlocks.SOULTOFU);
		wallBlock(TofuBlocks.TOFUFENCE_GRILLED.get(), texture(name(TofuBlocks.GRILLEDTOFU.get()) + "_top"));
		wall(TofuBlocks.TOFUFENCE_ZUNDA, TofuBlocks.ZUNDATOFU);

		ancientFormatDoor(TofuBlocks.TOFUDOOR_KINU, "kinu");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_MOMEN, "momen");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_ISHI, "ishi");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_METAL, "metal");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_HELL, "hell");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_SOUL, "soul");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_GRILLED, "grilled");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_ZUNDA, "zunda");

		trapdoor(TofuBlocks.TOFUTRAPDOOR_KINU);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_MOMEN);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_ISHI);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_METAL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_HELL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_SOUL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_GRILLED);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_ZUNDA);

		torchBlock(TofuBlocks.TOFUTORCH_KINU, TofuBlocks.WALLTOFUTORCH_KINU);
		torchBlock(TofuBlocks.TOFUTORCH_MOMEN, TofuBlocks.WALLTOFUTORCH_MOMEN);
		torchBlock(TofuBlocks.TOFUTORCH_ISHI, TofuBlocks.WALLTOFUTORCH_ISHI);
		torchBlock(TofuBlocks.TOFUTORCH_METAL, TofuBlocks.WALLTOFUTORCH_METAL);
		torchBlock(TofuBlocks.TOFUTORCH_GRILLED, TofuBlocks.WALLTOFUTORCH_GRILLED);
		torchBlock(TofuBlocks.TOFUTORCH_ZUNDA, TofuBlocks.WALLTOFUTORCH_ZUNDA);
		torchBlock(TofuBlocks.TOFUTORCH_HELL, TofuBlocks.WALLTOFUTORCH_HELL);
		torchBlock(TofuBlocks.TOFUTORCH_SOUL, TofuBlocks.WALLTOFUTORCH_SOUL);

		simpleBlock(TofuBlocks.TOFU_TERRAIN);
		simpleBlock(TofuBlocks.TOFUSLATE);
		simpleBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE);
		simpleBlock(TofuBlocks.ORE_TOFU_DIAMOND);
		simpleBlock(TofuBlocks.ORE_TOFUGEM);
		simpleBlock(TofuBlocks.TOFU_BEDROCK);

		logBlock(TofuBlocks.LEEK_GREEN_STEM.get());
		simpleBlock(TofuBlocks.LEEK_GREEN_PLANKS);
		stairs(TofuBlocks.LEEK_GREEN_PLANKS_STAIR, TofuBlocks.LEEK_GREEN_PLANKS);
		slab(TofuBlocks.LEEK_GREEN_PLANKS_SLAB, TofuBlocks.LEEK_GREEN_PLANKS);
		this.fenceBlock(TofuBlocks.LEEK_GREEN_FENCE.get(), texture(name(TofuBlocks.LEEK_GREEN_PLANKS.get())));
		this.fenceGateBlock(TofuBlocks.LEEK_GREEN_FENCE_GATE.get(), texture(name(TofuBlocks.LEEK_GREEN_PLANKS.get())));
		door(TofuBlocks.LEEK_GREEN_DOOR, "leek_green");
		trapdoor(TofuBlocks.LEEK_GREEN_TRAPDOOR);


		logBlock(TofuBlocks.LEEK_STEM.get());
		simpleBlock(TofuBlocks.LEEK_PLANKS);
		stairs(TofuBlocks.LEEK_PLANKS_STAIR, TofuBlocks.LEEK_PLANKS);
		slab(TofuBlocks.LEEK_PLANKS_SLAB, TofuBlocks.LEEK_PLANKS);
		this.fenceBlock(TofuBlocks.LEEK_FENCE.get(), texture(name(TofuBlocks.LEEK_PLANKS.get())));
		this.fenceGateBlock(TofuBlocks.LEEK_FENCE_GATE.get(), texture(name(TofuBlocks.LEEK_PLANKS.get())));

		crossBlock(TofuBlocks.ZUNDATOFU_MUSHROOM);

		logGlowBlock(TofuBlocks.TOFU_STEM.get());
		simpleBlock(TofuBlocks.TOFU_STEM_PLANKS);
		stairs(TofuBlocks.TOFU_STEM_PLANKS_STAIR, TofuBlocks.TOFU_STEM_PLANKS);
		slab(TofuBlocks.TOFU_STEM_PLANKS_SLAB, TofuBlocks.TOFU_STEM_PLANKS);
		this.fenceBlock(TofuBlocks.TOFU_STEM_FENCE.get(), texture(name(TofuBlocks.TOFU_STEM_PLANKS.get())));
		this.fenceGateBlock(TofuBlocks.TOFU_STEM_FENCE_GATE.get(), texture(name(TofuBlocks.TOFU_STEM_PLANKS.get())));
		door(TofuBlocks.TOFU_STEM_DOOR, "tofustem");
		trapdoor(TofuBlocks.TOFU_STEM_TRAPDOOR);

		sign(TofuBlocks.TOFU_STEM_SIGN, TofuBlocks.TOFU_STEM_WALL_SIGN, "tofustem_planks");
		sign(TofuBlocks.LEEK_GREEN_SIGN, TofuBlocks.LEEK_GREEN_WALL_SIGN, "leek_green_planks");
		sign(TofuBlocks.LEEK_SIGN, TofuBlocks.LEEK_WALL_SIGN, "leek_planks");

		hangingSign(TofuBlocks.TOFU_STEM_HANGING_SIGN, TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN, "tofustem");
		hangingSign(TofuBlocks.LEEK_GREEN_HANGING_SIGN, TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN, "leek_green_stem");
		hangingSign(TofuBlocks.LEEK_HANGING_SIGN, TofuBlocks.LEEK_WALL_HANGING_SIGN, "leek_stem");


		crossBlock(TofuBlocks.SAPLING_TOFU);
		simpleLeavesBlock(TofuBlocks.LEAVES_TOFU);

		crossBlock(TofuBlocks.SAPLING_APRICOT);
		simpleLeavesBlock(TofuBlocks.LEAVES_APRICOT);

		crossBlock(TofuBlocks.TOFU_FLOWER);
		crossBlock(TofuBlocks.LEEK);

		cake(TofuBlocks.TOFUCAKE, "tofucake");
		cake(TofuBlocks.ZUNDATOFUCAKE, "zundatofucake");
		cake(TofuBlocks.SOYCHEESE_TART, "soycheese_tart");

		CandleTofuCakeBlock.getCandleCakes().forEach((block -> this.candleCake(() -> (CandleTofuCakeBlock) block)));
		this.carpet(TofuBlocks.YUBA);
		this.simpleBlock(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN);
		this.chainBlock(TofuBlocks.TOFU_METAL_CHAIN);
		this.lantern(TofuBlocks.TOFU_METAL_LANTERN);
		this.lantern(TofuBlocks.TOFU_METAL_SOUL_LANTERN);
		this.translucentBlock(TofuBlocks.ZUNDAMA_BLOCK);
		crossBlock(TofuBlocks.ANTENNA_BASIC);
		simpleBlock(TofuBlocks.TF_COLLECTOR);
		simpleBlock(TofuBlocks.SALT_BLOCK);
	}

	public void simpleBlock(Supplier<Block> block) {
		simpleBlock(block.get(), cubeAll(block.get()));
	}

	public void chainBlock(Supplier<Block> block) {
		ModelFile cross = models().withExistingParent(name(block.get()), TofuCraftReload.MODID + ":block/chain").texture("all", blockTexture(block.get())).renderType("minecraft:cutout");
		getVariantBuilder(block.get()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
				.modelForState().modelFile(cross).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
				.modelForState().modelFile(cross).rotationX(90).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
				.modelForState().modelFile(cross).rotationX(90).rotationY(90).addModel();
	}

	public void sign(Supplier<? extends StandingSignBlock> standingBlock, Supplier<? extends WallSignBlock> wallBlock, String name) {
		signBlock(standingBlock.get(), wallBlock.get(), modLoc("block/" + name));
	}

	public void hangingSign(Supplier<? extends CeilingHangingSignBlock> standingBlock, Supplier<? extends WallHangingSignBlock> wallBlock, String name) {
		ModelFile model = models().getBuilder(name(standingBlock.get())).texture("particle", modLoc("block/" + name));
		simpleBlock(standingBlock.get(), model);
		simpleBlock(wallBlock.get(), model);
	}

	public void lantern(Supplier<Block> block) {
		ModelFile nonHanging = models().withExistingParent(name(block.get()), "block/template_lantern").renderType("minecraft:cutout")
				.texture("lantern", blockTexture(block.get()));
		ModelFile hanging = models().withExistingParent(name(block.get()) + "_hanging", "block/template_hanging_lantern").renderType("minecraft:cutout")
				.texture("lantern", blockTexture(block.get()));
		this.getVariantBuilder(block.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(state.getValue(LanternBlock.HANGING) ? hanging : nonHanging)
						.build());
	}


	public void carpet(Supplier<Block> block) {
		ModelFile carpet = models().withExistingParent(name(block.get()), "block/carpet")
				.texture("wool", blockTexture(block.get()));
		this.carpetBlock(block, (state -> carpet));
	}

	public void carpetBlock(Supplier<Block> block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).build());
	}

	public void logGlowBlock(RotatedPillarBlock block) {
		axisGlowBlock(block);
	}

	public void axisGlowBlock(RotatedPillarBlock block) {
		ModelFile glow_column = models().withExistingParent(name(block), TofuCraftReload.prefix("block/glow_column"))
				.texture("end", suffix(blockTexture(block), "_top"))
				.texture("side", blockTexture(block))
				.texture("glowtop", suffix(blockTexture(block), "_top_emissive"))
				.texture("glowside", suffix(blockTexture(block), "_emissive")).renderType("minecraft:cutout");

		ModelFile glow_column_horizontal = models().withExistingParent(name(block) + "_horizontal", TofuCraftReload.prefix("block/glow_column_horizontal"))
				.texture("end", suffix(blockTexture(block), "_top"))
				.texture("side", blockTexture(block))
				.texture("glowtop", suffix(blockTexture(block), "_top_emissive"))
				.texture("glowside", suffix(blockTexture(block), "_emissive")).renderType("minecraft:cutout");

		axisBlock(block,
				glow_column,
				glow_column_horizontal);
	}

	public void candleCake(Supplier<CandleTofuCakeBlock> block) {
		Block candle = block.get().getCandle();
		Block cake = block.get().getCake();

		ModelFile candleCake = models().withExistingParent(name(block.get()), "block/template_cake_with_candle")
				.texture("candle", blockTexture(candle))
				.texture("bottom", suffix(blockTexture(cake), "_bottom"))
				.texture("side", suffix(blockTexture(cake), "_side"))
				.texture("top", suffix(blockTexture(cake), "_top"))
				.texture("particle", suffix(blockTexture(cake), "_side"));

		ModelFile candleCakeLit = models().withExistingParent(name(block.get()) + "_lit", "block/template_cake_with_candle")
				.texture("candle", suffix(blockTexture(candle), "_lit"))
				.texture("bottom", suffix(blockTexture(cake), "_bottom"))
				.texture("side", suffix(blockTexture(cake), "_side"))
				.texture("top", suffix(blockTexture(cake), "_top"))
				.texture("particle", suffix(blockTexture(cake), "_side"));

		this.candleCakeBlock(block, (state -> state.getValue(BlockStateProperties.LIT) ? candleCakeLit : candleCake));
	}

	public void candleCakeBlock(Supplier<CandleTofuCakeBlock> block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).build());
	}

	private ResourceLocation suffix(ResourceLocation rl, String suffix) {
		return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
	}

	public ModelFile cubeLeavesAll(Supplier<Block> block) {
		return models().cubeAll(name(block.get()), blockTexture(block.get())).renderType("minecraft:cutout_mipped");
	}

	public void simpleLeavesBlock(Supplier<Block> block) {
		simpleBlock(block.get(), cubeLeavesAll(block));
	}

	public void translucentBlock(Supplier<? extends Block> block) {
		simpleBlock(block.get(), translucentCubeAll(block));
	}

	private ModelFile translucentCubeAll(Supplier<? extends Block> block) {
		return models().cubeAll(name(block.get()), blockTexture(block.get())).renderType("minecraft:translucent");
	}

	public void torchBlock(Supplier<Block> block, Supplier<Block> wall) {
		ModelFile torch = models().torch(name(block.get()), texture(name(block.get()))).renderType("minecraft:cutout");
		ModelFile torchwall = models().torchWall(name(wall.get()), texture(name(block.get()))).renderType("minecraft:cutout");
		simpleBlock(block.get(), torch);
		getVariantBuilder(wall.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(torchwall)
						.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
						.build());
	}

	public void stairs(Supplier<StairBlock> block, Supplier<Block> fullBlock) {
		stairsBlock(block.get(), texture(name(fullBlock.get())));
	}

	public void slab(Supplier<SlabBlock> block, Supplier<Block> fullBlock) {
		slabBlock(block.get(), texture(name(fullBlock.get())), texture(name(fullBlock.get())));
	}

	public void slab(Supplier<SlabBlock> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		slabBlock(block.get(), texture(name(block.get())), side, bottom, top);
	}

	public void crossBlock(Supplier<Block> block) {
		crossBlock(block, models().cross(name(block.get()), texture(name(block.get()))).renderType("minecraft:cutout"));
	}

	private void crossBlock(Supplier<Block> block, ModelFile model) {
		getVariantBuilder(block.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(model)
						.build());
	}


	public void cake(Supplier<Block> block, String name) {
		cakeBlockInternal(block, name(block.get()), texture(name + "_bottom"), texture(name + "_top"), texture(name + "_side"), texture(name + "_inner"));
	}

	private void cakeBlockInternal(Supplier<Block> block, String baseName, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation inside) {
		ModelFile cake = cake(baseName + "_uneaten", bottom, top, side);
		ModelFile sliced1 = slicedCake(baseName + "_slice1", "cake_slice1", bottom, top, side, inside);
		ModelFile sliced2 = slicedCake(baseName + "_slice2", "cake_slice2", bottom, top, side, inside);
		ModelFile sliced3 = slicedCake(baseName + "_slice3", "cake_slice3", bottom, top, side, inside);
		ModelFile sliced4 = slicedCake(baseName + "_slice4", "cake_slice4", bottom, top, side, inside);
		ModelFile sliced5 = slicedCake(baseName + "_slice5", "cake_slice5", bottom, top, side, inside);
		ModelFile sliced6 = slicedCake(baseName + "_slice6", "cake_slice6", bottom, top, side, inside);

		cakeBlock(block, cake, sliced1, sliced2, sliced3, sliced4, sliced5, sliced6);
	}

	public void cakeBlock(Supplier<Block> block, ModelFile uneat, ModelFile sliced1, ModelFile sliced2, ModelFile sliced3, ModelFile sliced4, ModelFile sliced5, ModelFile sliced6) {
		getVariantBuilder(block.get()).forAllStatesExcept(state -> {
			int bite = ((int) state.getValue(TofuCakeBlock.BITES));
			ModelFile file;
			switch (bite) {
				case 0:
					file = uneat;
					break;
				case 1:
					file = sliced1;
					break;
				case 2:
					file = sliced2;
					break;
				case 3:
					file = sliced3;
					break;
				case 4:
					file = sliced4;
					break;
				case 5:
					file = sliced5;
					break;
				default:
					file = sliced6;
					break;
			}

			return ConfiguredModel.builder().modelFile(file)
					.build();
		}, DoorBlock.POWERED);
	}

	private ModelBuilder<?> slicedCake(String name, String model, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation inside) {
		return models().withExistingParent(name, "block/" + model)
				.texture("particle", top)
				.texture("bottom", bottom)
				.texture("top", top)
				.texture("side", side)
				.texture("inside", inside);
	}

	private ModelBuilder<?> cake(String name, ResourceLocation bottom, ResourceLocation top, ResourceLocation side) {
		return models().withExistingParent(name, "block/" + "cake")
				.texture("particle", top)
				.texture("bottom", bottom)
				.texture("top", top)
				.texture("side", side);
	}

	public void door(Supplier<? extends DoorBlock> block, String name) {
		doorBlock(block.get(), texture(name + "_door_bottom"), texture(name + "_door_top"));
	}

	public void ancientFormatDoor(Supplier<? extends DoorBlock> block, String name) {
		doorBlockInternal(block.get(), BuiltInRegistries.BLOCK.getKey(block.get()).toString(), texture("tofudoor_" + name + "_lower"), texture("tofudoor_" + name + "_upper"));
	}

	private ModelBuilder<?> door(String name, String model, ResourceLocation bottom, ResourceLocation top) {
		return models().withExistingParent(name, "block/" + model)
				.texture("bottom", bottom)
				.texture("top", top)
				.renderType("minecraft:cutout");
	}

	private void doorBlockInternal(DoorBlock block, String baseName, ResourceLocation bottom, ResourceLocation top) {
		ModelFile bottomLeft = door(baseName + "_bottom_left", "door_bottom_left", bottom, top);
		ModelFile bottomLeftOpen = door(baseName + "_bottom_left_open", "door_bottom_left_open", bottom, top);
		ModelFile bottomRight = door(baseName + "_bottom_right", "door_bottom_right", bottom, top);
		ModelFile bottomRightOpen = door(baseName + "_bottom_right_open", "door_bottom_right_open", bottom, top);
		ModelFile topLeft = door(baseName + "_top_left", "door_top_left", bottom, top);
		ModelFile topLeftOpen = door(baseName + "_top_left_open", "door_top_left_open", bottom, top);
		ModelFile topRight = door(baseName + "_top_right", "door_top_right", bottom, top);
		ModelFile topRightOpen = door(baseName + "_top_right_open", "door_top_right_open", bottom, top);
		doorBlock(block, bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
	}

	public void doorBlock(DoorBlock block, ModelFile bottomLeft, ModelFile bottomLeftOpen, ModelFile bottomRight, ModelFile bottomRightOpen, ModelFile topLeft, ModelFile topLeftOpen, ModelFile topRight, ModelFile topRightOpen) {
		getVariantBuilder(block).forAllStatesExcept(state -> {
			int yRot = ((int) state.getValue(DoorBlock.FACING).toYRot()) + 90;
			boolean right = state.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT;
			boolean open = state.getValue(DoorBlock.OPEN);
			if (open) {
				yRot += 90;
			}
			if (right && open) {
				yRot += 180;
			}
			yRot %= 360;
			return ConfiguredModel.builder().modelFile(state.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? (right ? (open ? bottomRightOpen : bottomRight) : (open ? bottomLeftOpen : bottomLeft)) : (right ? (open ? topRightOpen : topRight) : (open ? topLeftOpen : topLeft)))
					.rotationY(yRot)
					.build();
		}, DoorBlock.POWERED);
	}

	public void wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		wallBlock(wall.get(), texture(name(fullBlock.get())));
	}

	public void trapdoor(Supplier<? extends TrapDoorBlock> block) {
		trapdoor(block.get(), texture(name(block.get())), true);
	}

	public void trapdoor(TrapDoorBlock block, ResourceLocation texture, boolean orientable) {
		trapdoorBlockInternal(block, name(block), texture, orientable);
	}


	private void trapdoorBlockInternal(TrapDoorBlock block, String baseName, ResourceLocation texture, boolean orientable) {
		ModelFile bottom = orientable ? models().trapdoorOrientableBottom(baseName + "_bottom", texture).renderType("minecraft:cutout") : models().trapdoorBottom(baseName + "_bottom", texture).renderType("minecraft:cutout");
		ModelFile top = orientable ? models().trapdoorOrientableTop(baseName + "_top", texture).renderType("minecraft:cutout") : models().trapdoorTop(baseName + "_top", texture).renderType("minecraft:cutout");
		ModelFile open = orientable ? models().trapdoorOrientableOpen(baseName + "_open", texture).renderType("minecraft:cutout") : models().trapdoorOpen(baseName + "_open", texture).renderType("minecraft:cutout");
		trapdoorBlock(block, bottom, top, open, orientable);
	}

	protected ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	protected String name(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block).getPath();
	}

	private ResourceLocation extend(ResourceLocation rl, String suffix) {
		return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
	}

	@Nonnull
	@Override
	public String getName() {
		return "TofuCraftReload blockstates and block models";
	}
}
