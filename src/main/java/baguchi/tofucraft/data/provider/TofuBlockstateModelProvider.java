package baguchi.tofucraft.data.provider;

import baguchi.tofucraft.client.render.special.TofunianStatueSpecialRenderer;
import baguchi.tofucraft.registry.TofuBlocks;
import com.mojang.math.Transformation;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.joml.Vector3f;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class TofuBlockstateModelProvider extends BlockModelGenerators {
	public TofuBlockstateModelProvider(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
		super(blockStateOutput, itemModelOutput, modelOutput);
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

	public void createTofuFarmland() {
		TextureMapping dryTextures = (new TextureMapping()).put(TextureSlot.DIRT, TextureMapping.getBlockTexture(TofuBlocks.TOFU_TERRAIN.get())).put(TextureSlot.TOP, TextureMapping.getBlockTexture(TofuBlocks.TOFU_FARMLAND.get()));
		TextureMapping moistTextures = (new TextureMapping()).put(TextureSlot.DIRT, TextureMapping.getBlockTexture(TofuBlocks.TOFU_TERRAIN.get())).put(TextureSlot.TOP, TextureMapping.getBlockTexture(TofuBlocks.TOFU_FARMLAND.get(), "_moist"));
		MultiVariant dryModel = plainVariant(ModelTemplates.FARMLAND.create(TofuBlocks.TOFU_FARMLAND.get(), dryTextures, this.modelOutput));
		MultiVariant moistModel = plainVariant(ModelTemplates.FARMLAND.create(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_FARMLAND.get(), "_moist"), moistTextures, this.modelOutput));
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(TofuBlocks.TOFU_FARMLAND.get()).with(createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, moistModel, dryModel)));
	}

	public static MultiVariantGenerator createSimpleBlock(Block p_387997_, Identifier p_388814_) {
		return MultiVariantGenerator.dispatch(p_387997_, plainVariant(p_388814_));
	}


	public void createTrivialCube(Block block) {
		this.createTrivialBlock(block, TexturedModel.CUBE);
		this.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
	}

	public void createTranslucentCube(Block block) {
		this.createTrivialBlock(block, TexturedModel.CUBE.updateTexture(TextureMapping::forceAllTranslucent));
	}

	public void createSingleCropBlock(Block block) {
		TextureMapping mapping = TextureMapping.crop(TextureMapping.getBlockTexture(block));

		Identifier variant = ModelTemplates.CROP.create(block, mapping, this.modelOutput);
		this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(variant)));
	}

	public BlockFamilyProvider familyWithExistingFullBlockWithTop(Block fullBlock) {
		BlockFamilyProvider provider = new BlockFamilyProvider(TofuTextureMapping.cubeTop(fullBlock));
		provider.fullBlock = plainModel(ModelLocationUtils.getModelLocation(fullBlock));
		return provider;
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

	public void createTofuBed(Block bed) {
		Identifier head = ModelTemplates.BED_HEAD.createWithSuffix(bed, "_" + BedPart.HEAD, TextureMapping.bed(bed, BedPart.HEAD), this.modelOutput);
		Identifier foot = ModelTemplates.BED_FOOT.createWithSuffix(bed, "_" + BedPart.FOOT, TextureMapping.bed(bed, BedPart.FOOT), this.modelOutput);
		MultiVariant blockModelHead = plainVariant(head);
		MultiVariant blockModelFoot = plainVariant(foot);
		this.blockStateOutput.accept(createBed(bed, blockModelHead, blockModelFoot));
		Transformation footTransformation = new Transformation(new Vector3f(0.0F, 0.0F, 1.0F), null, null, null);
		ItemModel.Unbaked itemModelHead = ItemModelUtils.plainModel(head);
		ItemModel.Unbaked itemModelFoot = ItemModelUtils.plainModel(foot, footTransformation);
		this.itemModelOutput.accept(bed.asItem(), ItemModelUtils.composite(itemModelHead, itemModelFoot));
	}

	public void createTofunianState(Block p_387718_, Block p_386452_) {
		this.createParticleOnlyBlock(p_387718_, p_386452_);
		Item item = p_387718_.asItem();
		Identifier resourcelocation1 = TofuModelTemplates.STATUE_INVENTORY
				.create(ModelLocationUtils.getModelLocation(item), TextureMapping.particle(p_386452_), this.modelOutput);
		this.itemModelOutput.accept(item, ItemModelUtils.specialModel(resourcelocation1, new TofunianStatueSpecialRenderer.Unbaked()));
	}


	public void createLadder(Block p_388554_) {
		this.registerSimpleFlatItemModel(p_388554_);
		MultiVariant multiVariant = plainVariant(TofuModelTemplates.LADDER.create(p_388554_, TofuTextureMapping.ladder(p_388554_), this.modelOutput));

		this.blockStateOutput
				.accept(MultiVariantGenerator.dispatch(p_388554_, multiVariant).with(ROTATION_HORIZONTAL_FACING));
	}
}