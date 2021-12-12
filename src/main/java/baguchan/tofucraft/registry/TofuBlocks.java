package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.*;
import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.block.tfenergy.TFStorageBlock;
import baguchan.tofucraft.block.utils.SaltFurnaceBlock;
import baguchan.tofucraft.block.utils.SaltPanBlock;
import baguchan.tofucraft.block.utils.TofuBedBlock;
import baguchan.tofucraft.block.utils.TofuChestBlock;
import baguchan.tofucraft.blockentity.TofuBedBlockEntity;
import baguchan.tofucraft.blockentity.TofuChestBlockEntity;
import baguchan.tofucraft.world.gen.feature.TofuWorldFeatures;
import baguchan.tofucraft.world.gen.grower.TofuTreeGrower;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuBlocks {
	public static final Block SOYMILK = new TofuFluidBlock(TofuFluids.SOYMILK, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
	public static final Block SOYMILK_HELL = new TofuFluidBlock(TofuFluids.SOYMILK_HELL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
	public static final Block SOYMILK_SOUL = new TofuFluidBlock(TofuFluids.SOYMILK_SOUL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
	public static final Block BITTERN = new TofuFluidBlock(TofuFluids.BITTERN, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());

	public static final Block SOYBEAN = new SoybeanCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
	public static final Block SOYBEAN_NETHER = new SoybeanNetherCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
	public static final Block SOYBEAN_SOUL = new SoybeanSoulCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));

	public static final Block KINUTOFU = new KinuTofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).randomTicks().strength(0.1F, 0.2F).sound(SoundType.SNOW));
	public static final Block MOMENTOFU = new TofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).randomTicks().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block ISHITOFU = new TofuBlock(BlockBehaviour.Properties.of(Material.STONE).randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));
	public static final Block ISHITOFU_BRICK = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));
	public static final Block ISHITOFU_SMOOTH_BRICK = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));
	public static final Block ISHITOFU_CHISELED_BRICK = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));
	public static final Block METALTOFU = new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
	public static final Block DIAMONDTOFU = new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
	public static final Block GRILLEDTOFU = new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block ZUNDATOFU = new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW));

	public static final Block HELLTOFU = new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block HELLTOFU_BRICK = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));
	public static final Block HELLTOFU_SMOOTH_BRICK = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));

	public static final Block SOULTOFU = new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block SOULTOFU_BRICK = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));
	public static final Block SOULTOFU_SMOOTH_BRICK = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));

	public static final StairBlock TOFUSTAIR_KINU = new StairBlock(KINUTOFU::defaultBlockState, BlockBehaviour.Properties.copy(KINUTOFU));
	public static final StairBlock TOFUSTAIR_MOMEN = new StairBlock(MOMENTOFU::defaultBlockState, BlockBehaviour.Properties.copy(MOMENTOFU));
	public static final StairBlock TOFUSTAIR_ISHI = new StairBlock(ISHITOFU::defaultBlockState, BlockBehaviour.Properties.copy(ISHITOFU));
	public static final StairBlock TOFUSTAIR_METAL = new StairBlock(METALTOFU::defaultBlockState, BlockBehaviour.Properties.copy(METALTOFU));
	public static final StairBlock TOFUSTAIR_ZUNDA = new StairBlock(ZUNDATOFU::defaultBlockState, BlockBehaviour.Properties.copy(ZUNDATOFU));
	public static final StairBlock TOFUSTAIR_ISHIBRICK = new StairBlock(ISHITOFU_BRICK::defaultBlockState, BlockBehaviour.Properties.copy(ISHITOFU_BRICK));
	public static final StairBlock TOFUSTAIR_HELLBRICK = new StairBlock(HELLTOFU_BRICK::defaultBlockState, BlockBehaviour.Properties.copy(HELLTOFU_BRICK));
	public static final StairBlock TOFUSTAIR_SOULBRICK = new StairBlock(SOULTOFU_BRICK::defaultBlockState, BlockBehaviour.Properties.copy(SOULTOFU_BRICK));

	public static final SlabBlock TOFUSLAB_KINU = new SlabBlock(BlockBehaviour.Properties.copy(KINUTOFU));
	public static final SlabBlock TOFUSLAB_MOMEN = new SlabBlock(BlockBehaviour.Properties.copy(MOMENTOFU));
	public static final SlabBlock TOFUSLAB_ISHI = new SlabBlock(BlockBehaviour.Properties.copy(ISHITOFU));
	public static final SlabBlock TOFUSLAB_METAL = new SlabBlock(BlockBehaviour.Properties.copy(METALTOFU));
	public static final SlabBlock TOFUSLAB_ZUNDA = new SlabBlock(BlockBehaviour.Properties.copy(ZUNDATOFU));
	public static final SlabBlock TOFUSLAB_ISHIBRICK = new SlabBlock(BlockBehaviour.Properties.copy(ISHITOFU_BRICK));
	public static final SlabBlock TOFUSLAB_HELLBRICK = new SlabBlock(BlockBehaviour.Properties.copy(HELLTOFU_BRICK));
	public static final SlabBlock TOFUSLAB_SOULBRICK = new SlabBlock(BlockBehaviour.Properties.copy(SOULTOFU_BRICK));

	public static final Block TOFUTORCH_KINU = new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME);
	public static final Block TOFUTORCH_MOMEN = new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME);
	public static final Block TOFUTORCH_ISHI = new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.STONE), ParticleTypes.FLAME);
	public static final Block TOFUTORCH_METAL = new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.METAL), ParticleTypes.FLAME);

	public static final Block WALLTOFUTORCH_KINU = new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW).dropsLike(TOFUTORCH_KINU), ParticleTypes.FLAME);
	public static final Block WALLTOFUTORCH_MOMEN = new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW).dropsLike(TOFUTORCH_MOMEN), ParticleTypes.FLAME);
	public static final Block WALLTOFUTORCH_ISHI = new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.STONE).dropsLike(TOFUTORCH_ISHI), ParticleTypes.FLAME);
	public static final Block WALLTOFUTORCH_METAL = new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.METAL).dropsLike(TOFUTORCH_METAL), ParticleTypes.FLAME);

	public static final Block TOFULADDER_KINU = new LadderBlock(BlockBehaviour.Properties.copy(KINUTOFU).noOcclusion());
	public static final Block TOFULADDER_MOMEN = new LadderBlock(BlockBehaviour.Properties.copy(MOMENTOFU).noOcclusion());
	public static final Block TOFULADDER_ISHI = new LadderBlock(BlockBehaviour.Properties.copy(ISHITOFU).noOcclusion());
	public static final Block TOFULADDER_METAL = new LadderBlock(BlockBehaviour.Properties.copy(METALTOFU).noOcclusion());
	public static final Block TOFULADDER_ISHIBRICK = new LadderBlock(BlockBehaviour.Properties.copy(ISHITOFU_BRICK).noOcclusion());

	public static final Block TOFUFENCE_KINU = new WallBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.1F, 0.2F).sound(SoundType.SNOW).noOcclusion());
	public static final Block TOFUFENCE_MOMEN = new WallBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion());
	public static final Block TOFUFENCE_ISHI = new WallBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion());
	public static final Block TOFUFENCE_METAL = new WallBlock(BlockBehaviour.Properties.copy(METALTOFU).noOcclusion());


	public static final Block TOFU_TERRAIN = new TofuTerrainBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block TOFUSLATE = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.5F, 4.0F).sound(SoundType.DEEPSLATE));
	public static final Block TOFUSLATE_TOFU_DIAMOND_ORE = new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F, 4.0F).sound(SoundType.DEEPSLATE));
	public static final Block ORE_TOFU_DIAMOND = new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW));
	public static final Block TOFU_BEDROCK = new Block(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F).sound(SoundType.STONE));

	public static final Block SAPLING_TOFU = new TofuSaplingBlock(new TofuTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final Block LEAVES_TOFU = new TofuLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).noOcclusion().randomTicks().sound(SoundType.GRASS));


	public static final Block LEEK = new LeekBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noOcclusion().noCollission().sound(SoundType.GRASS));

	public static final RotatedPillarBlock LEEK_GREEN_STEM = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final RotatedPillarBlock LEEK_STEM = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final Block ZUNDATOFU_MUSHROOM = new TofuMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.FUNGUS), () -> {
		return TofuWorldFeatures.BIG_ZUNDA_TOFU_MUSHUROOM;
	});
	public static final RotatedPillarBlock TOFU_STEM = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final Block TOFU_STEM_PLANKS = new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD));


	public static final TofuPortalBlock TOFU_PORTAL = new TofuPortalBlock(BlockBehaviour.Properties.of(Material.PORTAL).strength(-1.0F).noCollission().noOcclusion().noDrops().sound(SoundType.GLASS).lightLevel((p_50872_) -> {
		return 11;
	}));
	public static final Block TOFU_FARMLAND = new TofuFarmlandBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).noOcclusion().sound(SoundType.SNOW));
	public static final Block SALTPAN = new SaltPanBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).randomTicks().noOcclusion().sound(SoundType.WOOD));
	public static final Block SALT_FURNACE = new SaltFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).sound(SoundType.STONE).lightLevel((p_50872_) -> {
		return p_50872_.getValue(SaltFurnaceBlock.LIT) ? 13 : 0;
	}));

	public static final Block TOFUBED = new TofuBedBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F).noOcclusion().sound(SoundType.SNOW));
	public static final Block TOFUCHEST = new TofuChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).noOcclusion().sound(SoundType.STONE), () -> TofuBlockEntitys.TOFUCHEST);

	public static final Block TF_STORAGE = new TFStorageBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFStorageBlock.LIT) ? 13 : 0;
	}));

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> registry) {
		registry.getRegistry().register(SOYMILK.setRegistryName("soymilk"));
		registry.getRegistry().register(SOYMILK_HELL.setRegistryName("soymilk_hell"));
		registry.getRegistry().register(SOYMILK_SOUL.setRegistryName("soymilk_soul"));
		registry.getRegistry().register(BITTERN.setRegistryName("bittern"));

		registry.getRegistry().register(SOYBEAN.setRegistryName("soybean"));
		registry.getRegistry().register(SOYBEAN_NETHER.setRegistryName("soybean_nether"));
		registry.getRegistry().register(SOYBEAN_SOUL.setRegistryName("soybean_soul"));

		registry.getRegistry().register(KINUTOFU.setRegistryName("blocktofukinu"));
		registry.getRegistry().register(MOMENTOFU.setRegistryName("blocktofumomen"));
		registry.getRegistry().register(ISHITOFU.setRegistryName("blocktofuishi"));
		registry.getRegistry().register(ISHITOFU_BRICK.setRegistryName("tofuishi_brick"));
		registry.getRegistry().register(ISHITOFU_SMOOTH_BRICK.setRegistryName("tofuishi_smooth_brick"));
		registry.getRegistry().register(ISHITOFU_CHISELED_BRICK.setRegistryName("tofuishi_chiseled_brick"));
		registry.getRegistry().register(METALTOFU.setRegistryName("blocktofumetal"));
		registry.getRegistry().register(DIAMONDTOFU.setRegistryName("blocktofudiamond"));
		registry.getRegistry().register(GRILLEDTOFU.setRegistryName("blocktofugrilled"));
		registry.getRegistry().register(ZUNDATOFU.setRegistryName("blocktofuzunda"));

		registry.getRegistry().register(HELLTOFU.setRegistryName("blocktofuhell"));
		registry.getRegistry().register(HELLTOFU_BRICK.setRegistryName("tofuhell_brick"));
		registry.getRegistry().register(HELLTOFU_SMOOTH_BRICK.setRegistryName("tofuhell_smooth_brick"));
		registry.getRegistry().register(SOULTOFU.setRegistryName("blocktofusoul"));
		registry.getRegistry().register(SOULTOFU_BRICK.setRegistryName("tofusoul_brick"));
		registry.getRegistry().register(SOULTOFU_SMOOTH_BRICK.setRegistryName("tofusoul_smooth_brick"));

		registry.getRegistry().register(TOFUSTAIR_KINU.setRegistryName("tofustair_kinu"));
		registry.getRegistry().register(TOFUSTAIR_MOMEN.setRegistryName("tofustair_momen"));
		registry.getRegistry().register(TOFUSTAIR_ISHI.setRegistryName("tofustair_ishi"));
		registry.getRegistry().register(TOFUSTAIR_METAL.setRegistryName("tofustair_metal"));
		registry.getRegistry().register(TOFUSTAIR_ZUNDA.setRegistryName("tofustair_zunda"));
		registry.getRegistry().register(TOFUSTAIR_ISHIBRICK.setRegistryName("tofustair_ishibrick"));
		registry.getRegistry().register(TOFUSTAIR_HELLBRICK.setRegistryName("tofustair_hellbrick"));
		registry.getRegistry().register(TOFUSTAIR_SOULBRICK.setRegistryName("tofustair_soulbrick"));

		registry.getRegistry().register(TOFUSLAB_KINU.setRegistryName("tofuslab_kinu"));
		registry.getRegistry().register(TOFUSLAB_MOMEN.setRegistryName("tofuslab_momen"));
		registry.getRegistry().register(TOFUSLAB_ISHI.setRegistryName("tofuslab_ishi"));
		registry.getRegistry().register(TOFUSLAB_METAL.setRegistryName("tofuslab_metal"));

		registry.getRegistry().register(TOFUSLAB_ISHIBRICK.setRegistryName("tofuslab_ishibrick"));
		registry.getRegistry().register(TOFUSLAB_ZUNDA.setRegistryName("tofuslab_zunda"));
		registry.getRegistry().register(TOFUSLAB_HELLBRICK.setRegistryName("tofuslab_hellbrick"));
		registry.getRegistry().register(TOFUSLAB_SOULBRICK.setRegistryName("tofuslab_soulbrick"));

		registry.getRegistry().register(TOFUFENCE_KINU.setRegistryName("tofufence_kinu"));
		registry.getRegistry().register(TOFUFENCE_MOMEN.setRegistryName("tofufence_momen"));
		registry.getRegistry().register(TOFUFENCE_ISHI.setRegistryName("tofufence_ishi"));
		registry.getRegistry().register(TOFUFENCE_METAL.setRegistryName("tofufence_metal"));

		registry.getRegistry().register(TOFUTORCH_KINU.setRegistryName("tofutorch_kinu"));
		registry.getRegistry().register(TOFUTORCH_MOMEN.setRegistryName("tofutorch_momen"));
		registry.getRegistry().register(TOFUTORCH_ISHI.setRegistryName("tofutorch_ishi"));
		registry.getRegistry().register(TOFUTORCH_METAL.setRegistryName("tofutorch_metal"));

		registry.getRegistry().register(WALLTOFUTORCH_KINU.setRegistryName("walltofutorch_kinu"));
		registry.getRegistry().register(WALLTOFUTORCH_MOMEN.setRegistryName("walltofutorch_momen"));
		registry.getRegistry().register(WALLTOFUTORCH_ISHI.setRegistryName("walltofutorch_ishi"));
		registry.getRegistry().register(WALLTOFUTORCH_METAL.setRegistryName("walltofutorch_metal"));

		registry.getRegistry().register(TOFULADDER_KINU.setRegistryName("tofuladder_kinu"));
		registry.getRegistry().register(TOFULADDER_MOMEN.setRegistryName("tofuladder_momen"));
		registry.getRegistry().register(TOFULADDER_ISHI.setRegistryName("tofuladder_ishi"));
		registry.getRegistry().register(TOFULADDER_ISHIBRICK.setRegistryName("tofuladder_ishibrick"));
		registry.getRegistry().register(TOFULADDER_METAL.setRegistryName("tofuladder_metal"));

		registry.getRegistry().register(TOFU_TERRAIN.setRegistryName("tofu_terrain"));
		registry.getRegistry().register(TOFUSLATE.setRegistryName("tofuslate"));
		registry.getRegistry().register(TOFUSLATE_TOFU_DIAMOND_ORE.setRegistryName("tofuslate_tofu_diamond_ore"));
		registry.getRegistry().register(ORE_TOFU_DIAMOND.setRegistryName("ore_tofu_diamond"));
		registry.getRegistry().register(TOFU_BEDROCK.setRegistryName("tofu_bedrock"));

		registry.getRegistry().register(SAPLING_TOFU.setRegistryName("sapling_tofu"));
		registry.getRegistry().register(LEAVES_TOFU.setRegistryName("leaves_tofu"));

		registry.getRegistry().register(LEEK.setRegistryName("blockleek"));

		registry.getRegistry().register(LEEK_GREEN_STEM.setRegistryName("leek_green_stem"));
		registry.getRegistry().register(LEEK_STEM.setRegistryName("leek_stem"));
		registry.getRegistry().register(ZUNDATOFU_MUSHROOM.setRegistryName("zundatofu_mushroom"));
		registry.getRegistry().register(TOFU_STEM.setRegistryName("tofustem"));
		registry.getRegistry().register(TOFU_STEM_PLANKS.setRegistryName("tofustem_planks"));

		registry.getRegistry().register(TOFU_PORTAL.setRegistryName("tofuportal"));
		registry.getRegistry().register(TOFU_FARMLAND.setRegistryName("tofu_farmland"));
		registry.getRegistry().register(SALTPAN.setRegistryName("blocksaltpan"));
		registry.getRegistry().register(SALT_FURNACE.setRegistryName("salt_furnace"));

		registry.getRegistry().register(TOFUBED.setRegistryName("tofubed"));
		registry.getRegistry().register(TOFUCHEST.setRegistryName("tofuchest"));

		registry.getRegistry().register(TF_STORAGE.setRegistryName("tf_storage"));
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> registry) {
		TofuItems.register(registry, new BlockItem(KINUTOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(MOMENTOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_BRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_SMOOTH_BRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_CHISELED_BRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(METALTOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(DIAMONDTOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(GRILLEDTOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(HELLTOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU_BRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU_SMOOTH_BRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(SOULTOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU_BRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU_SMOOTH_BRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(TOFUSTAIR_KINU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_MOMEN, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ISHI, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_METAL, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ZUNDA, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ISHIBRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_HELLBRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_SOULBRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(TOFUSLAB_KINU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_MOMEN, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ISHI, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_METAL, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ZUNDA, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(TOFUSLAB_ISHIBRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_HELLBRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_SOULBRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(TOFUFENCE_KINU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_MOMEN, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_ISHI, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_METAL, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new StandingAndWallBlockItem(TOFUTORCH_KINU, WALLTOFUTORCH_KINU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new StandingAndWallBlockItem(TOFUTORCH_MOMEN, WALLTOFUTORCH_MOMEN, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new StandingAndWallBlockItem(TOFUTORCH_ISHI, WALLTOFUTORCH_ISHI, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new StandingAndWallBlockItem(TOFUTORCH_METAL, WALLTOFUTORCH_METAL, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(TOFULADDER_KINU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_MOMEN, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_ISHI, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_ISHIBRICK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_METAL, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(TOFU_TERRAIN, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLATE, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLATE_TOFU_DIAMOND_ORE, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(ORE_TOFU_DIAMOND, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_BEDROCK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(SAPLING_TOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(LEAVES_TOFU, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(LEEK, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(LEEK_GREEN_STEM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(LEEK_STEM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_MUSHROOM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM_PLANKS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));

		TofuItems.register(registry, new BlockItem(TOFU_FARMLAND, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SALTPAN, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SALT_FURNACE, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUBED, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT).stacksTo(1)) {
			@Override
			public void initializeClient(@Nonnull Consumer<IItemRenderProperties> consumer) {
				consumer.accept(new IItemRenderProperties() {
					BlockEntityWithoutLevelRenderer myRenderer;

					@Override
					public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
						if (Minecraft.getInstance().getEntityRenderDispatcher() != null && myRenderer == null) {
							myRenderer = new BlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()) {
								private TofuBedBlockEntity blockEntity;

								@Override
								public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemTransforms.TransformType transformType, @Nonnull PoseStack matrix, @Nonnull MultiBufferSource buffer, int x, int y) {
									if (blockEntity == null) {
										blockEntity = new TofuBedBlockEntity(BlockPos.ZERO, TofuBlocks.TOFUBED.defaultBlockState());
									}
									Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, matrix, buffer, x, y);
								}
							};
						}

						return myRenderer;
					}
				});
			}
		});
		TofuItems.register(registry, new BlockItem(TOFUCHEST, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)) {
			@Override
			public void initializeClient(@Nonnull Consumer<IItemRenderProperties> consumer) {
				consumer.accept(new IItemRenderProperties() {
					BlockEntityWithoutLevelRenderer myRenderer;

					@Override
					public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
						if (Minecraft.getInstance().getEntityRenderDispatcher() != null && myRenderer == null) {
							myRenderer = new BlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()) {
								private TofuChestBlockEntity blockEntity;

								@Override
								public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemTransforms.TransformType transformType, @Nonnull PoseStack matrix, @Nonnull MultiBufferSource buffer, int x, int y) {
									if (blockEntity == null) {
										blockEntity = new TofuChestBlockEntity(BlockPos.ZERO, TofuBlocks.TOFUCHEST.defaultBlockState());
									}
									Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, matrix, buffer, x, y);
								}
							};
						}

						return myRenderer;
					}
				});
			}
		});

		TofuItems.register(registry, new BlockItem(TF_STORAGE, new Item.Properties().tab(TofuItemGroup.TOFUCRAFT)));
	}
}
