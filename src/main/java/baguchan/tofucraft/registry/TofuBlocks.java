package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.*;
import baguchan.tofucraft.block.crop.LeekCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.block.tfenergy.TFAntennaBlock;
import baguchan.tofucraft.block.tfenergy.TFStorageBlock;
import baguchan.tofucraft.block.utils.*;
import baguchan.tofucraft.client.render.item.TofuBedBWLR;
import baguchan.tofucraft.client.render.item.TofuChestBWLR;
import baguchan.tofucraft.world.gen.features.TofuWorldFeatures;
import baguchan.tofucraft.world.gen.grower.TofuTreeGrower;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TofuCraftReload.MODID);

	public static final RegistryObject<Block> SOYMILK = noItemRegister("soymilk", () -> new LiquidBlock(TofuFluids.SOYMILK, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));
	public static final RegistryObject<Block> SOYMILK_HELL = noItemRegister("soymilk_hell", () -> new LiquidBlock(TofuFluids.SOYMILK_HELL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));
	public static final RegistryObject<Block> SOYMILK_SOUL = noItemRegister("soymilk_soul", () -> new LiquidBlock(TofuFluids.SOYMILK_SOUL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));
	public static final RegistryObject<Block> BITTERN = noItemRegister("bittern", () -> new LiquidBlock(TofuFluids.BITTERN, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));

	public static final RegistryObject<Block> SOYBEAN = noItemRegister("soybean", () -> new SoybeanCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> SOYBEAN_NETHER = noItemRegister("soybean_nether", () -> new SoybeanNetherCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> SOYBEAN_SOUL = noItemRegister("soybean_soul", () -> new SoybeanSoulCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> LEEK_CROP = noItemRegister("leek_crop", () -> new LeekCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));


	public static final RegistryObject<Block> KINUTOFU = register("blocktofukinu", () -> new KinuTofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).randomTicks().strength(0.1F, 0.2F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> MOMENTOFU = register("blocktofumomen", () -> new TofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).randomTicks().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> ISHITOFU = register("blocktofuishi", () -> new TofuBlock(BlockBehaviour.Properties.of(Material.STONE).randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ISHITOFU_BRICK = register("tofuishi_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ISHITOFU_SMOOTH_BRICK = register("tofuishi_smooth_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ISHITOFU_CHISELED_BRICK = register("tofuishi_chiseled_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> METALTOFU = register("blocktofumetal", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DIAMONDTOFU = register("blocktofudiamond", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> GRILLEDTOFU = register("blocktofugrilled", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> ZUNDATOFU = register("blocktofuzunda", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> MISOTOFU = register("blocktofumiso", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final RegistryObject<Block> HELLTOFU = register("blocktofuhell", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> HELLTOFU_BRICK = register("tofuhell_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> HELLTOFU_SMOOTH_BRICK = register("tofuhell_smooth_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> SOULTOFU = register("blocktofusoul", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> SOULTOFU_BRICK = register("tofusoul_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SOULTOFU_SMOOTH_BRICK = register("tofusoul_smooth_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<StairBlock> TOFUSTAIR_KINU = register("tofustair_kinu", () -> new StairBlock(KINUTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(KINUTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_MOMEN = register("tofustair_momen", () -> new StairBlock(MOMENTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(MOMENTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_ISHI = register("tofustair_ishi", () -> new StairBlock(ISHITOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(ISHITOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_METAL = register("tofustair_metal", () -> new StairBlock(METALTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(METALTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_ZUNDA = register("tofustair_zunda", () -> new StairBlock(ZUNDATOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(ZUNDATOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_HELL = register("tofustair_hell", () -> new StairBlock(HELLTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(HELLTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_SOUL = register("tofustair_soul", () -> new StairBlock(SOULTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(SOULTOFU.get())));

	public static final RegistryObject<StairBlock> TOFUSTAIR_ISHIBRICK = register("tofustair_ishibrick", () -> new StairBlock(ISHITOFU_BRICK.get()::defaultBlockState, BlockBehaviour.Properties.copy(ISHITOFU_BRICK.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_HELLBRICK = register("tofustair_hellbrick", () -> new StairBlock(HELLTOFU_BRICK.get()::defaultBlockState, BlockBehaviour.Properties.copy(HELLTOFU_BRICK.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_SOULBRICK = register("tofustair_soulbrick", () -> new StairBlock(SOULTOFU_BRICK.get()::defaultBlockState, BlockBehaviour.Properties.copy(SOULTOFU_BRICK.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_MISO = register("tofustair_miso", () -> new StairBlock(MISOTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(MISOTOFU.get())));


	public static final RegistryObject<SlabBlock> TOFUSLAB_KINU = register("tofuslab_kinu", () -> new SlabBlock(BlockBehaviour.Properties.copy(KINUTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_MOMEN = register("tofuslab_momen", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOMENTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_ISHI = register("tofuslab_ishi", () -> new SlabBlock(BlockBehaviour.Properties.copy(ISHITOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_METAL = register("tofuslab_metal", () -> new SlabBlock(BlockBehaviour.Properties.copy(METALTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_ZUNDA = register("tofuslab_zunda", () -> new SlabBlock(BlockBehaviour.Properties.copy(ZUNDATOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_HELL = register("tofuslab_hell", () -> new SlabBlock(BlockBehaviour.Properties.copy(HELLTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_SOUL = register("tofuslab_soul", () -> new SlabBlock(BlockBehaviour.Properties.copy(SOULTOFU.get())));

	public static final RegistryObject<SlabBlock> TOFUSLAB_ISHIBRICK = register("tofuslab_ishibrick", () -> new SlabBlock(BlockBehaviour.Properties.copy(ISHITOFU_BRICK.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_HELLBRICK = register("tofuslab_hellbrick", () -> new SlabBlock(BlockBehaviour.Properties.copy(HELLTOFU_BRICK.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_SOULBRICK = register("tofuslab_soulbrick", () -> new SlabBlock(BlockBehaviour.Properties.copy(SOULTOFU_BRICK.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_MISO = register("tofuslab_miso", () -> new SlabBlock(BlockBehaviour.Properties.copy(MISOTOFU.get())));

	public static final RegistryObject<Block> TOFUTORCH_KINU = register("tofutorch_kinu", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_MOMEN = register("tofutorch_momen", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_ISHI = register("tofutorch_ishi", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.STONE), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_METAL = register("tofutorch_metal", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.METAL), ParticleTypes.FLAME));

	public static final RegistryObject<Block> WALLTOFUTORCH_KINU = BLOCKS.register("walltofutorch_kinu", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW).dropsLike(TOFUTORCH_KINU.get()), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_MOMEN = BLOCKS.register("walltofutorch_momen", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW).dropsLike(TOFUTORCH_MOMEN.get()), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_ISHI = BLOCKS.register("walltofutorch_ishi", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.STONE).dropsLike(TOFUTORCH_ISHI.get()), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_METAL = BLOCKS.register("walltofutorch_metal", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.METAL).dropsLike(TOFUTORCH_METAL.get()), ParticleTypes.FLAME));

	public static final RegistryObject<Block> TOFULADDER_KINU = register("tofuladder_kinu", () -> new LadderBlock(BlockBehaviour.Properties.copy(KINUTOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_MOMEN = register("tofuladder_momen", () -> new LadderBlock(BlockBehaviour.Properties.copy(MOMENTOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_ISHI = register("tofuladder_ishi", () -> new LadderBlock(BlockBehaviour.Properties.copy(ISHITOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_METAL = register("tofuladder_metal", () -> new LadderBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_ISHIBRICK = register("tofuladder_ishibrick", () -> new LadderBlock(BlockBehaviour.Properties.copy(ISHITOFU_BRICK.get()).noOcclusion()));

	public static final RegistryObject<WallBlock> TOFUFENCE_KINU = register("tofufence_kinu", () -> new WallBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.1F, 0.2F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_MOMEN = register("tofufence_momen", () -> new WallBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_ISHI = register("tofufence_ishi", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_METAL = register("tofufence_metal", () -> new WallBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_HELL = register("tofufence_hell", () -> new WallBlock(BlockBehaviour.Properties.copy(HELLTOFU.get()).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_SOUL = register("tofufence_soul", () -> new WallBlock(BlockBehaviour.Properties.copy(SOULTOFU.get()).noOcclusion()));


	public static final RegistryObject<DoorBlock> TOFUDOOR_KINU = register("tofudoor_kinu", () -> new DoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<DoorBlock> TOFUDOOR_MOMEN = register("tofudoor_momen", () -> new DoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<DoorBlock> TOFUDOOR_ISHI = register("tofudoor_ishi", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<DoorBlock> TOFUDOOR_METAL = register("tofudoor_metal", () -> new DoorBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion()));
	public static final RegistryObject<DoorBlock> TOFUDOOR_HELL = register("tofudoor_hell", () -> new DoorBlock(BlockBehaviour.Properties.copy(HELLTOFU.get()).noOcclusion()));
	public static final RegistryObject<DoorBlock> TOFUDOOR_SOUL = register("tofudoor_soul", () -> new DoorBlock(BlockBehaviour.Properties.copy(SOULTOFU.get()).noOcclusion()));

	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_KINU = register("tofutrapdoor_kinu", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_MOMEN = register("tofutrapdoor_momen", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_ISHI = register("tofutrapdoor_ishi", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_METAL = register("tofutrapdoor_metal", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion()));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_HELL = register("tofutrapdoor_hell", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(HELLTOFU.get()).noOcclusion()));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_SOUL = register("tofutrapdoor_soul", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(SOULTOFU.get()).noOcclusion()));


	public static final RegistryObject<Block> TOFU_TERRAIN = register("tofu_terrain", () -> new TofuTerrainBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.4F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> TOFU_TERRAIN_ZUNDA = register("tofu_terrain_zunda", () -> new TofuTerrainBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.4F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> TOFUSLATE = register("tofuslate", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.5F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> TOFUSLATE_TOFU_DIAMOND_ORE = register("tofuslate_tofu_diamond_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> ORE_TOFU_DIAMOND = register("ore_tofu_diamond", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> TOFU_BEDROCK = register("tofu_bedrock", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> SAPLING_TOFU = register("sapling_tofu", () -> new TofuSaplingBlock(new TofuTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> LEAVES_TOFU = register("leaves_tofu", () -> new TofuLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).noOcclusion().randomTicks().sound(SoundType.GRASS)));


	public static final RegistryObject<Block> LEEK = register("blockleek", () -> new LeekBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noOcclusion().noCollission().sound(SoundType.GRASS)));

	public static final RegistryObject<RotatedPillarBlock> LEEK_GREEN_STEM = register("leek_green_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final RegistryObject<RotatedPillarBlock> LEEK_STEM = register("leek_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final RegistryObject<Block> ZUNDATOFU_MUSHROOM = register("zundatofu_mushroom", () -> new TofuMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.FUNGUS), () -> {
		return TofuWorldFeatures.BIG_ZUNDA_TOFU_MUSHUROOM;
	}));
	public static final RegistryObject<RotatedPillarBlock> TOFU_STEM = register("tofustem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final RegistryObject<Block> TOFU_STEM_PLANKS = register("tofustem_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));


	public static final RegistryObject<TofuPortalBlock> TOFU_PORTAL = BLOCKS.register("tofuportal", () -> new TofuPortalBlock(BlockBehaviour.Properties.of(Material.PORTAL).strength(-1.0F).noCollission().noOcclusion().noDrops().sound(SoundType.GLASS).lightLevel((p_50872_) -> {
		return 11;
	})));
	public static final RegistryObject<Block> TOFU_FARMLAND = register("tofu_farmland", () -> new TofuFarmlandBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).noOcclusion().sound(SoundType.SNOW)));
	public static final RegistryObject<Block> SALTPAN = register("blocksaltpan", () -> new SaltPanBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final RegistryObject<Block> SALT_FURNACE = register("salt_furnace", () -> new SaltFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).sound(SoundType.STONE).lightLevel((p_50872_) -> {
		return p_50872_.getValue(SaltFurnaceBlock.LIT) ? 13 : 0;
	})));
	public static final RegistryObject<Block> MORIJIO = register("morijio", () -> new MorijioBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	//BARREL
	public static final RegistryObject<Block> BARREL_MISO = register("barrel_miso", () -> new MisoBarrelBlock(TofuItems.BOTTLE_SOYSAUSE, BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BARREL_MISOTOFU = register("barrel_misotofu", () -> new WorkedBarrelBaseBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));


	public static final RegistryObject<Block> TOFUCAKE = register("tofucake", () -> new TofuCakeBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).noOcclusion().sound(SoundType.WOOL)));


	public static final RegistryObject<Block> TOFUBED = register("tofubed", () -> new TofuBedBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F).noOcclusion().sound(SoundType.SNOW)));
	public static final RegistryObject<Block> TOFUCHEST = register("tofuchest", () -> new TofuChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).noOcclusion().sound(SoundType.STONE), TofuBlockEntitys.TOFUCHEST::get));

	public static final RegistryObject<Block> TF_STORAGE = register("tf_storage", () -> new TFStorageBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFStorageBlock.LIT) ? 13 : 0;
	})));
	public static final RegistryObject<Block> ANTENNA_BASIC = register("antenna_basic", () -> new TFAntennaBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().noOcclusion().noCollission().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));

	//Tofu delight item
	public static final RegistryObject<Block> EGGTOFU = register("blocktofuegg", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<StairBlock> TOFUSTAIR_EGG = register("tofustair_egg", () -> new StairBlock(EGGTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(EGGTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_EGG = register("tofuslab_egg", () -> new SlabBlock(BlockBehaviour.Properties.copy(EGGTOFU.get())));

	private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		TofuItems.ITEMS.register(name, item.apply(register));
		return register;
	}

	private static <T extends Block> RegistryObject<T> noItemRegister(String name, Supplier<? extends T> block) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		return register;
	}

	private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
		return (RegistryObject<B>) baseRegister(name, block, TofuBlocks::registerBlockItem);
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
		return () -> {
			if (Objects.requireNonNull(block.get()) == TOFUTORCH_KINU.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_KINU.get(), WALLTOFUTORCH_KINU.get(), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT));
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_MOMEN.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_MOMEN.get(), WALLTOFUTORCH_MOMEN.get(), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT));
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_ISHI.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_ISHI.get(), WALLTOFUTORCH_ISHI.get(), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT));
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_METAL.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_METAL.get(), WALLTOFUTORCH_METAL.get(), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT));
			} else if (Objects.requireNonNull(block.get()) == TOFUBED.get()) {
				return new BedItem(Objects.requireNonNull(block.get()), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT).stacksTo(1)) {
					@Override
					public void initializeClient(Consumer<IItemRenderProperties> consumer) {
						consumer.accept(new IItemRenderProperties() {
							@Override
							public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
								return new TofuBedBWLR();
							}
						});
					}
				};
			} else if (Objects.requireNonNull(block.get()) == TOFUCHEST.get()) {
				return new BedItem(Objects.requireNonNull(block.get()), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT)) {
					@Override
					public void initializeClient(Consumer<IItemRenderProperties> consumer) {
						consumer.accept(new IItemRenderProperties() {
							@Override
							public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
								return new TofuChestBWLR();
							}
						});
					}
				};
			} else if (Objects.requireNonNull(block.get()) instanceof DoorBlock) {
				return new DoubleHighBlockItem(Objects.requireNonNull(block.get()), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT));
			} else {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().tab(TofuCreativeModeTab.TOFUCRAFT));
			}
		};
	}
}
