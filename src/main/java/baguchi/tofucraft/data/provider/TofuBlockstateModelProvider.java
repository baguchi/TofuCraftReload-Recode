package baguchi.tofucraft.data.provider;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.special.FoodPlateSpecialRenderer;
import baguchi.tofucraft.client.render.special.TofunianStatueSpecialRenderer;
import baguchi.tofucraft.registry.TofuBlocks;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.BedSpecialRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;
import static net.minecraft.client.data.models.model.TexturedModel.createDefault;

public abstract class TofuBlockstateModelProvider extends BlockModelGenerators {
	public static final ModelTemplate GLOW_CUBE = TofuModelTemplate.GLOW_CUBE.extend().renderType("cutout").build();
	public static final ModelTemplate TRANSLUCENT_CUBE = ModelTemplates.CUBE_ALL.extend().renderType("translucent").build();
	public static final ModelTemplate CUTOUT_CUBE = ModelTemplates.CUBE_ALL.extend().renderType("cutout").build();
	public static final ModelTemplate CROP = ModelTemplates.CROP.extend().renderType("cutout").build();
	public static final TexturedModel.Provider LEAVES_PROVIDER = createDefault(TextureMapping::cube, ModelTemplates.LEAVES.extend().renderType("cutout").build());
	public static final TexturedModel.Provider CHAIN = createDefault(TextureMapping::cube, TofuModelTemplate.CHAIN.extend().renderType("cutout").build());
	public static final TexturedModel.Provider LANTERN = createDefault(TextureMapping::lantern, ModelTemplates.LANTERN.extend().renderType("cutout").build());
	public static final TexturedModel.Provider HANGING_LANTERN = createDefault(TextureMapping::lantern, ModelTemplates.HANGING_LANTERN.extend().renderType("cutout").build());

	public TofuBlockstateModelProvider(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
		super(blockStateOutput, itemModelOutput, modelOutput);
	}

	@Override
	public void createBarsAndItem(Block p_436790_) {
		TextureMapping texturemapping = TextureMapping.bars(p_436790_);
		this.createBars(p_436790_, ModelTemplates.BARS_POST_ENDS.extend().renderType("cutout").build().create(p_436790_, texturemapping, this.modelOutput), ModelTemplates.BARS_POST.extend().renderType("cutout").build().create(p_436790_, texturemapping, this.modelOutput), ModelTemplates.BARS_CAP.extend().renderType("cutout").build().create(p_436790_, texturemapping, this.modelOutput), ModelTemplates.BARS_CAP_ALT.extend().renderType("cutout").build().create(p_436790_, texturemapping, this.modelOutput), ModelTemplates.BARS_POST_SIDE.extend().renderType("cutout").build().create(p_436790_, texturemapping, this.modelOutput), ModelTemplates.BARS_POST_SIDE_ALT.extend().renderType("cutout").build().create(p_436790_, texturemapping, this.modelOutput));
		this.registerSimpleFlatItemModel(p_436790_);
	}

	@Override
	public void createBarsAndItem(Block p_436811_, Block p_436809_) {
		TextureMapping texturemapping = TextureMapping.bars(p_436811_);
		ResourceLocation resourcelocation = ModelTemplates.BARS_POST_ENDS.extend().renderType("cutout").build().create(p_436811_, texturemapping, this.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.BARS_POST.extend().renderType("cutout").build().create(p_436811_, texturemapping, this.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.BARS_CAP.extend().renderType("cutout").build().create(p_436811_, texturemapping, this.modelOutput);
		ResourceLocation resourcelocation3 = ModelTemplates.BARS_CAP_ALT.extend().renderType("cutout").build().create(p_436811_, texturemapping, this.modelOutput);
		ResourceLocation resourcelocation4 = ModelTemplates.BARS_POST_SIDE.extend().renderType("cutout").build().create(p_436811_, texturemapping, this.modelOutput);
		ResourceLocation resourcelocation5 = ModelTemplates.BARS_POST_SIDE_ALT.extend().renderType("cutout").build().create(p_436811_, texturemapping, this.modelOutput);
		this.createBars(p_436811_, resourcelocation, resourcelocation1, resourcelocation2, resourcelocation3, resourcelocation4, resourcelocation5);
		this.createBars(p_436809_, resourcelocation, resourcelocation1, resourcelocation2, resourcelocation3, resourcelocation4, resourcelocation5);
		this.registerSimpleFlatItemModel(p_436811_);
		this.itemModelOutput.copy(p_436811_.asItem(), p_436809_.asItem());
	}

	@Override
	public void createTrapdoor(Block p_387551_) {
		TextureMapping texturemapping = TextureMapping.defaultTexture(p_387551_);
		MultiVariant multivariant = plainVariant(ModelTemplates.TRAPDOOR_TOP.extend().renderType("cutout").build().create(p_387551_, texturemapping, this.modelOutput));
		ResourceLocation resourcelocation = ModelTemplates.TRAPDOOR_BOTTOM.extend().renderType("cutout").build().create(p_387551_, texturemapping, this.modelOutput);
		MultiVariant multivariant1 = plainVariant(ModelTemplates.TRAPDOOR_OPEN.extend().renderType("cutout").build().create(p_387551_, texturemapping, this.modelOutput));
		this.blockStateOutput.accept(createTrapdoor(p_387551_, multivariant, plainVariant(resourcelocation), multivariant1));
		this.registerSimpleItemModel(p_387551_, resourcelocation);
	}

	@Override
	public void createCropBlock(Block p_387553_, Property<Integer> p_386757_, int... p_388514_) {
		this.registerSimpleFlatItemModel(p_387553_.asItem());
		if (p_386757_.getPossibleValues().size() != p_388514_.length) {
			throw new IllegalArgumentException();
		} else {
			Int2ObjectMap<ResourceLocation> int2objectmap = new Int2ObjectOpenHashMap();
			this.blockStateOutput.accept(MultiVariantGenerator.dispatch(p_387553_).with(PropertyDispatch.initial(p_386757_).generate((p_408977_) -> {
				int i = p_388514_[p_408977_];
				return plainVariant((ResourceLocation) int2objectmap.computeIfAbsent(i, (p_387308_) -> this.createSuffixedVariant(p_387553_, "_" + p_387308_, ModelTemplates.CROP.extend().renderType("cutout").build(), TextureMapping::crop)));
			})));
		}
	}

	@Override
	public void createOrientableTrapdoor(Block p_388937_) {
		TextureMapping texturemapping = TextureMapping.defaultTexture(p_388937_);
		MultiVariant multivariant = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.extend().renderType("cutout").build().create(p_388937_, texturemapping, this.modelOutput));
		ResourceLocation resourcelocation = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.extend().renderType("cutout").build().create(p_388937_, texturemapping, this.modelOutput);
		MultiVariant multivariant1 = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.extend().renderType("cutout").build().create(p_388937_, texturemapping, this.modelOutput));
		this.blockStateOutput.accept(createOrientableTrapdoor(p_388937_, multivariant, plainVariant(resourcelocation), multivariant1));
		this.registerSimpleItemModel(p_388937_, resourcelocation);
	}

	@Override
	public void createDoor(Block block) {
		TextureMapping bottomMapping = TofuTextureMapping.doorBottom(block);
		TextureMapping topMapping = TofuTextureMapping.doorTop(block);
		MultiVariant left = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.extend().renderType("cutout").build().create(block, bottomMapping, this.modelOutput));
		MultiVariant bottomLeftOpen = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.extend().renderType("cutout").build().create(block, bottomMapping, this.modelOutput));
		MultiVariant bottomRight = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.extend().renderType("cutout").build().create(block, bottomMapping, this.modelOutput));
		MultiVariant bottomRightOpen = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.extend().renderType("cutout").build().create(block, bottomMapping, this.modelOutput));
		MultiVariant topLeft = plainVariant(ModelTemplates.DOOR_TOP_LEFT.extend().renderType("cutout").build().create(block, topMapping, this.modelOutput));
		MultiVariant topLeftOpen = plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.extend().renderType("cutout").build().create(block, topMapping, this.modelOutput));
		MultiVariant topRight = plainVariant(ModelTemplates.DOOR_TOP_RIGHT.extend().renderType("cutout").build().create(block, topMapping, this.modelOutput));
		MultiVariant topRightOpen = plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.extend().renderType("cutout").build().create(block, topMapping, this.modelOutput));
		this.registerSimpleFlatItemModel(block.asItem());
		this.blockStateOutput.accept(createDoor(block, left, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen));
	}


	public void createLantern(Block p_386669_) {
		MultiVariant multivariant = plainVariant(LANTERN.create(p_386669_, this.modelOutput));
		MultiVariant multivariant1 = plainVariant(HANGING_LANTERN.create(p_386669_, this.modelOutput));
		this.registerSimpleFlatItemModel(p_386669_.asItem());
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(p_386669_).with(createBooleanModelDispatch(BlockStateProperties.HANGING, multivariant1, multivariant)));
	}
	public void createItemWithDoubleGrassTint(Block p_388714_) {
		this.registerSimpleTintedItemModel(p_388714_, ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(p_388714_), TextureMapping.layer0(getBlockTexture(p_388714_).withSuffix("_top")), this.modelOutput), new GrassColorSource());
	}

	@Override
	public void createTintedDoublePlant(Block p_388276_) {
		createItemWithDoubleGrassTint(p_388276_);
		createDoublePlant(p_388276_, BlockModelGenerators.PlantType.TINTED);
	}

	@Override
	public void createDoublePlant(Block p_388543_, BlockModelGenerators.PlantType p_388551_) {
		MultiVariant multivariant = plainVariant(this.createSuffixedVariant(p_388543_, "_top", p_388551_.getCross().extend().renderType("cutout").build(), TextureMapping::cross));
		MultiVariant multivariant1 = plainVariant(this.createSuffixedVariant(p_388543_, "_bottom", p_388551_.getCross().extend().renderType("cutout").build(), TextureMapping::cross));
		this.createDoubleBlock(p_388543_, multivariant, multivariant1);
	}

	public void createTrivialCube(Block p_386512_) {
		this.createTrivialBlock(p_386512_, TexturedModel.CUBE);
	}

	public void createTrivialBlock(Block p_387678_, TexturedModel.Provider p_386545_) {
		this.blockStateOutput.accept(createSimpleBlock(p_387678_, p_386545_.create(p_387678_, this.modelOutput)));
	}

	public void logWithHorizontal(Block p_387121_) {
		MultiVariant resourcelocation = plainVariant(ModelTemplates.CUBE_COLUMN.create(p_387121_, TextureMapping.logColumn(p_387121_), this.modelOutput));
		MultiVariant resourcelocation1 = plainVariant(ModelTemplates.CUBE_COLUMN_HORIZONTAL
				.create(p_387121_, TextureMapping.logColumn(p_387121_), this.modelOutput));

		this.blockStateOutput
				.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(p_387121_, resourcelocation, resourcelocation1));
	}

	public void logWithHorizontalGlow(Block p_387121_) {
		MultiVariant resourcelocation = plainVariant(TofuModelTemplate.CUBE_COLUMN.create(p_387121_, TofuTextureMapping.logGlowColumn(p_387121_), this.modelOutput));
		MultiVariant resourcelocation1 = plainVariant(TofuModelTemplate.CUBE_COLUMN_HORIZONTAL
				.create(p_387121_, TofuTextureMapping.logGlowColumn(p_387121_), this.modelOutput));

		this.blockStateOutput
				.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(p_387121_, resourcelocation, resourcelocation1));
	}

	public void createCrossBlock(Block p_388178_, BlockModelGenerators.PlantType p_387157_) {
		TextureMapping texturemapping = p_387157_.getTextureMapping(p_388178_);
		this.createCrossBlock(p_388178_, p_387157_, texturemapping);
	}

	public void createCrossBlock(Block p_388360_, BlockModelGenerators.PlantType p_386631_, TextureMapping p_388352_) {
		ResourceLocation resourcelocation = p_386631_.getCross().extend().renderType("cutout").build().create(p_388360_, p_388352_, this.modelOutput);
		this.blockStateOutput.accept(createSimpleBlock(p_388360_, resourcelocation));
	}

	public void createCutoutCube(Block block) {
		this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, plainVariant(CUTOUT_CUBE.create(block, TextureMapping.cube(block), this.modelOutput))));
	}

	public void createSingleCrop(Block block) {
		this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, plainVariant(CROP.create(block, TextureMapping.crop(getBlockTexture(block)), this.modelOutput))));
	}

	public void createTranslucentCube(Block block) {
		this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, plainVariant(TRANSLUCENT_CUBE.create(block, TextureMapping.cube(block), this.modelOutput))));
	}

	public void createGlowCube(Block block) {

		this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, plainVariant(GLOW_CUBE.create(block, TofuTextureMapping.glowCube(block), this.modelOutput))));
	}

	public void createTofuCandleCake(Block candle, Block cake, Block defaultCake) {

		MultiVariant resourcelocation8 = plainVariant(ModelTemplates.CANDLE_CAKE.create(cake, TofuTextureMapping.candleCake(candle, defaultCake, false), this.modelOutput));
		MultiVariant resourcelocation9 = plainVariant(ModelTemplates.CANDLE_CAKE
				.createWithSuffix(cake, "_lit", TofuTextureMapping.candleCake(candle, defaultCake, false), this.modelOutput));
		this.blockStateOutput
				.accept(
						MultiVariantGenerator.dispatch(cake).with(createBooleanModelDispatch(BlockStateProperties.LIT, resourcelocation9, resourcelocation8))
				);
	}

	public BlockFamilyProvider familyWithExistingFullBlockWithTop(Block fullBlock) {
		BlockFamilyProvider provider = new BlockFamilyProvider(TofuTextureMapping.cubeWithTop(fullBlock));
		provider.fullBlock = plainModel(ModelLocationUtils.getModelLocation(fullBlock));
		return provider;
	}

	public static String getBlockName(Block p_387523_) {
		ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(p_387523_);
		return resourcelocation.getPath();
	}

	public void createTofuFarmland() {
		TextureMapping texturemapping = new TextureMapping().put(TextureSlot.DIRT, getBlockTexture(TofuBlocks.TOFU_TERRAIN.get())).put(TextureSlot.TOP, getBlockTexture(TofuBlocks.TOFU_FARMLAND.get()));
		TextureMapping texturemapping1 = new TextureMapping().put(TextureSlot.DIRT, getBlockTexture(TofuBlocks.TOFU_TERRAIN.get())).put(TextureSlot.TOP, getBlockTexture(TofuBlocks.TOFU_FARMLAND.get(), "_moist"));
		MultiVariant resourcelocation = plainVariant(ModelTemplates.FARMLAND.create(TofuBlocks.TOFU_FARMLAND.get(), texturemapping, this.modelOutput));
		MultiVariant resourcelocation1 = plainVariant(ModelTemplates.FARMLAND.create(getBlockTexture(TofuBlocks.TOFU_FARMLAND.get(), "_moist"), texturemapping1, this.modelOutput));
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(TofuBlocks.TOFU_FARMLAND.get()).with(BlockModelGenerators.createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, resourcelocation1, resourcelocation)));
	}

	public static MultiVariantGenerator createSimpleBlock(Block p_387997_, ResourceLocation p_388814_) {
		return MultiVariantGenerator.dispatch(p_387997_, plainVariant(p_388814_));
	}

	public void createTofuPortalBlock() {
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(TofuBlocks.TOFU_PORTAL.get()).with(PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_AXIS)
				.select(Direction.Axis.X, plainVariant(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_PORTAL.get(), "_ns")))
				.select(Direction.Axis.Z, plainVariant(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_PORTAL.get(), "_ew")))));
	}


	public void createTofuCakeBlock(Block block) {
		this.registerSimpleFlatItemModel(block.asItem());
		this.blockStateOutput
				.accept(
						MultiVariantGenerator.dispatch(block)
								.with(
										PropertyDispatch.initial(BlockStateProperties.BITES)
												.select(0, plainVariant(ModelLocationUtils.getModelLocation(block)))
												.select(1, plainVariant(ModelLocationUtils.getModelLocation(block, "_slice1")))
												.select(2, plainVariant(ModelLocationUtils.getModelLocation(block, "_slice2")))
												.select(3, plainVariant(ModelLocationUtils.getModelLocation(block, "_slice3")))
												.select(4, plainVariant(ModelLocationUtils.getModelLocation(block, "_slice4")))
												.select(5, plainVariant(ModelLocationUtils.getModelLocation(block, "_slice5")))
												.select(6, plainVariant(ModelLocationUtils.getModelLocation(block, "_slice6")))
								)
				);
	}

	public void createTofuBed(Block p_387718_, Block p_386452_, ResourceLocation p_387181_) {
		ResourceLocation resourcelocation = ModelLocationUtils.decorateBlockModelLocation("bed");
		this.blockStateOutput.accept(createSimpleBlock(p_387718_, resourcelocation));
		Item item = p_387718_.asItem();
		ResourceLocation resourcelocation1 = ModelTemplates.BED_INVENTORY
				.create(ModelLocationUtils.getModelLocation(item), TextureMapping.particle(p_386452_), this.modelOutput);
		this.itemModelOutput.accept(item, ItemModelUtils.specialModel(resourcelocation1, new BedSpecialRenderer.Unbaked(p_387181_)));
	}

	public void createTofunianState(Block p_387718_, Block p_386452_) {
		this.createParticleOnlyBlock(p_387718_, p_386452_);
		Item item = p_387718_.asItem();
		ResourceLocation resourcelocation1 = ModelTemplates.BED_INVENTORY
				.create(ModelLocationUtils.getModelLocation(item), TextureMapping.particle(p_386452_), this.modelOutput);
		this.itemModelOutput.accept(item, ItemModelUtils.specialModel(resourcelocation1, new TofunianStatueSpecialRenderer.Unbaked()));
	}


	public void createLadder(Block p_388554_) {
		this.registerSimpleFlatItemModel(p_388554_);
		MultiVariant multiVariant = plainVariant(TofuModelTemplate.LADDER.create(p_388554_, TofuTextureMapping.ladder(p_388554_), this.modelOutput));

		this.blockStateOutput
				.accept(MultiVariantGenerator.dispatch(p_388554_, multiVariant).with(ROTATION_HORIZONTAL_FACING));
	}

	public void createFoodPlate(Block block) {
		//this.createParticleOnlyBlock(p_387020_, p_388374_);
		Item item = block.asItem();
		ResourceLocation resourcelocation = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "item/template_foodplate");
		ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.specialModel(resourcelocation, new FoodPlateSpecialRenderer.Unbaked());
		this.itemModelOutput.accept(item, itemmodel$unbaked);
	}
}