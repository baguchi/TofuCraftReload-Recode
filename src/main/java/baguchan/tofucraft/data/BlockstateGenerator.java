package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.TofuCakeBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

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
		simpleBlock(TofuBlocks.DRIEDTOFU.get());
		simpleBlock(TofuBlocks.SCULK_BONE.get());

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
		stairs(TofuBlocks.TOFUSTAIR_HELL.get(), TofuBlocks.HELLTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_SOUL.get(), TofuBlocks.SOULTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK.get(), TofuBlocks.ISHITOFU_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_HELLBRICK.get(), TofuBlocks.HELLTOFU_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_SOULBRICK.get(), TofuBlocks.SOULTOFU_BRICK.get());
		stairs(TofuBlocks.TOFUSTAIR_MISO.get(), TofuBlocks.MISOTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_DRIED.get(), TofuBlocks.DRIEDTOFU.get());

		slab(TofuBlocks.TOFUSLAB_KINU.get(), TofuBlocks.KINUTOFU.get());
		slab(TofuBlocks.TOFUSLAB_MOMEN.get(), TofuBlocks.MOMENTOFU.get());
		slab(TofuBlocks.TOFUSLAB_ISHI.get(), TofuBlocks.ISHITOFU.get());
		slab(TofuBlocks.TOFUSLAB_METAL.get(), TofuBlocks.METALTOFU.get());
		slab(TofuBlocks.TOFUSLAB_ZUNDA.get(), TofuBlocks.ZUNDATOFU.get());
		slab(TofuBlocks.TOFUSLAB_HELL.get(), TofuBlocks.HELLTOFU.get());
		slab(TofuBlocks.TOFUSLAB_SOUL.get(), TofuBlocks.SOULTOFU.get());
		slab(TofuBlocks.TOFUSLAB_ISHIBRICK.get(), TofuBlocks.ISHITOFU_BRICK.get());
		slab(TofuBlocks.TOFUSLAB_HELLBRICK.get(), TofuBlocks.HELLTOFU_BRICK.get());
		slab(TofuBlocks.TOFUSLAB_SOULBRICK.get(), TofuBlocks.SOULTOFU_BRICK.get());
		slab(TofuBlocks.TOFUSLAB_MISO.get(), TofuBlocks.MISOTOFU.get());
		slab(TofuBlocks.TOFUSLAB_DRIED.get(), TofuBlocks.DRIEDTOFU.get());

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

		trapdoor(TofuBlocks.TOFUTRAPDOOR_KINU);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_MOMEN);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_ISHI);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_METAL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_HELL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_SOUL);

		torchBlock(TofuBlocks.TOFUTORCH_KINU.get(), TofuBlocks.WALLTOFUTORCH_KINU.get());
		torchBlock(TofuBlocks.TOFUTORCH_MOMEN.get(), TofuBlocks.WALLTOFUTORCH_MOMEN.get());
		torchBlock(TofuBlocks.TOFUTORCH_ISHI.get(), TofuBlocks.WALLTOFUTORCH_ISHI.get());
		torchBlock(TofuBlocks.TOFUTORCH_METAL.get(), TofuBlocks.WALLTOFUTORCH_METAL.get());

		simpleBlock(TofuBlocks.TOFU_TERRAIN.get());
		simpleBlock(TofuBlocks.TOFUSLATE.get());
		simpleBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		simpleBlock(TofuBlocks.ORE_TOFU_DIAMOND.get());
		simpleBlock(TofuBlocks.ORE_TOFUGEM.get());
		simpleBlock(TofuBlocks.TOFU_BEDROCK.get());

		logBlock(TofuBlocks.LEEK_GREEN_STEM.get());
		logBlock(TofuBlocks.LEEK_STEM.get());
		crossBlock(TofuBlocks.ZUNDATOFU_MUSHROOM.get());
		logBlock(TofuBlocks.TOFU_STEM.get());
		simpleBlock(TofuBlocks.TOFU_STEM_PLANKS.get());

		crossBlock(TofuBlocks.SAPLING_TOFU.get());
		simpleLeavesBlock(TofuBlocks.LEAVES_TOFU.get());

		crossBlock(TofuBlocks.SAPLING_APRICOT.get());
		simpleLeavesBlock(TofuBlocks.LEAVES_APRICOT.get());

		crossBlock(TofuBlocks.LEEK.get());

		simpleBlock(TofuBlocks.TOFU_PORTAL.get());

		simpleBlock(TofuBlocks.EGGTOFU.get());
		stairs(TofuBlocks.TOFUSTAIR_EGG.get(), TofuBlocks.EGGTOFU.get());
		slab(TofuBlocks.TOFUSLAB_EGG.get(), TofuBlocks.EGGTOFU.get());

		crossBlock(TofuBlocks.ANTENNA_BASIC.get());

		cake(TofuBlocks.TOFUCAKE, "tofucake");
		cake(TofuBlocks.ZUNDATOFUCAKE, "zundatofucake");
	}

	public ModelFile cubeLeavesAll(Block block) {
		return models().cubeAll(name(block), blockTexture(block)).renderType("minecraft:cutout_mipped");
	}

	public void simpleLeavesBlock(Block block) {
		simpleBlock(block, cubeLeavesAll(block));
	}

	public void torchBlock(Block block, Block wall) {
		ModelFile torch = models().torch(name(block), texture(name(block))).renderType("minecraft:cutout");
		ModelFile torchwall = models().torchWall(name(wall), texture(name(block))).renderType("minecraft:cutout");
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
		crossBlock(block, models().cross(name(block), texture(name(block))).renderType("minecraft:cutout"));
	}

	private void crossBlock(Block block, ModelFile model) {
		getVariantBuilder(block).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(model)
						.build());
	}


	public void cake(Supplier<? extends TofuCakeBlock> block, String name) {
		cakeBlockInternal(block.get(), name(block.get()), texture(name + "_bottom"), texture(name + "_top"), texture(name + "_side"), texture(name + "_inner"));
	}

	private void cakeBlockInternal(TofuCakeBlock block, String baseName, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation inside) {
		ModelFile cake = cake(baseName + "_uneaten", bottom, top, side);
		ModelFile sliced1 = slicedCake(baseName + "_slice1", "cake_slice1", bottom, top, side, inside);
		ModelFile sliced2 = slicedCake(baseName + "_slice2", "cake_slice2", bottom, top, side, inside);
		ModelFile sliced3 = slicedCake(baseName + "_slice3", "cake_slice3", bottom, top, side, inside);
		ModelFile sliced4 = slicedCake(baseName + "_slice4", "cake_slice4", bottom, top, side, inside);
		ModelFile sliced5 = slicedCake(baseName + "_slice5", "cake_slice5", bottom, top, side, inside);
		ModelFile sliced6 = slicedCake(baseName + "_slice6", "cake_slice6", bottom, top, side, inside);

		cakeBlock(block, cake, sliced1, sliced2, sliced3, sliced4, sliced5, sliced6);
	}

	public void cakeBlock(TofuCakeBlock block, ModelFile uneat, ModelFile sliced1, ModelFile sliced2, ModelFile sliced3, ModelFile sliced4, ModelFile sliced5, ModelFile sliced6) {
		getVariantBuilder(block).forAllStatesExcept(state -> {
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
		doorBlockInternal(block.get(), ForgeRegistries.BLOCKS.getKey(block.get()).toString(), texture("tofudoor_" + name + "_lower"), texture("tofudoor_" + name + "_upper"));
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
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	@Nonnull
	@Override
	public String getName() {
		return "TofuCraftReload blockstates and block models";
	}
}
