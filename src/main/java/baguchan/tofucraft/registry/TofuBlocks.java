package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.BurnableRotatedPillarBlock;
import baguchan.tofucraft.block.CandleTofuCakeBlock;
import baguchan.tofucraft.block.FoodPlateBlock;
import baguchan.tofucraft.block.KinuTofuBlock;
import baguchan.tofucraft.block.LeekBlock;
import baguchan.tofucraft.block.MincedTofuBlock;
import baguchan.tofucraft.block.MorijioBlock;
import baguchan.tofucraft.block.RiceBlock;
import baguchan.tofucraft.block.TofuBlock;
import baguchan.tofucraft.block.TofuCakeBlock;
import baguchan.tofucraft.block.TofuFarmlandBlock;
import baguchan.tofucraft.block.TofuGemBlock;
import baguchan.tofucraft.block.TofuLeavesBlock;
import baguchan.tofucraft.block.TofuMushroomBlock;
import baguchan.tofucraft.block.TofuPortalBlock;
import baguchan.tofucraft.block.TofuSaplingBlock;
import baguchan.tofucraft.block.TofuTerrainBlock;
import baguchan.tofucraft.block.crop.ChiliCropsBlock;
import baguchan.tofucraft.block.crop.LeekCropsBlock;
import baguchan.tofucraft.block.crop.RiceCropsBlock;
import baguchan.tofucraft.block.crop.RiceRootBlock;
import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.block.tfenergy.TFAggregatorBlock;
import baguchan.tofucraft.block.tfenergy.TFAntennaBlock;
import baguchan.tofucraft.block.tfenergy.TFStorageBlock;
import baguchan.tofucraft.block.tree.ApricotLeavesBlock;
import baguchan.tofucraft.block.tree.ApricotSaplingBlock;
import baguchan.tofucraft.block.utils.MisoBarrelBlock;
import baguchan.tofucraft.block.utils.NoWeightBaseBlock;
import baguchan.tofucraft.block.utils.SaltFurnaceBlock;
import baguchan.tofucraft.block.utils.SaltPanBlock;
import baguchan.tofucraft.block.utils.SoymilkCauldronBlock;
import baguchan.tofucraft.block.utils.SproutsJarBlock;
import baguchan.tofucraft.block.utils.TofuBedBlock;
import baguchan.tofucraft.block.utils.TofuChestBlock;
import baguchan.tofucraft.block.utils.TofuDoorBlock;
import baguchan.tofucraft.block.utils.WeightBaseBlock;
import baguchan.tofucraft.client.render.item.TofuBedBWLR;
import baguchan.tofucraft.client.render.item.TofuChestBWLR;
import baguchan.tofucraft.world.gen.features.TofuWorldFeatures;
import baguchan.tofucraft.world.gen.grower.ApricotTreeGrower;
import baguchan.tofucraft.world.gen.grower.TofuTreeGrower;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TofuBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TofuCraftReload.MODID);

	public static final RegistryObject<Block> SOYMILK = noItemRegister("soymilk", () -> new LiquidBlock(TofuFluids.SOYMILK, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> SOYMILK_HELL = noItemRegister("soymilk_hell", () -> new LiquidBlock(TofuFluids.SOYMILK_HELL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> SOYMILK_SOUL = noItemRegister("soymilk_soul", () -> new LiquidBlock(TofuFluids.SOYMILK_SOUL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()));
	public static final RegistryObject<Block> BITTERN = noItemRegister("bittern", () -> new LiquidBlock(TofuFluids.BITTERN, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()));

	public static final RegistryObject<Block> SOYBEAN = noItemRegister("soybean", () -> new SoybeanCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> SOYBEAN_NETHER = noItemRegister("soybean_nether", () -> new SoybeanNetherCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> SOYBEAN_SOUL = noItemRegister("soybean_soul", () -> new SoybeanSoulCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> LEEK_CROP = noItemRegister("leek_crop", () -> new LeekCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> RICE_CROP = noItemRegister("rice", () -> new RiceCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final RegistryObject<Block> RICE_ROOT = noItemRegister("rice_root", () -> new RiceRootBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.1F).sound(SoundType.CROP)));
	public static final RegistryObject<Block> CHILI_CROP = noItemRegister("chili_crop", () -> new ChiliCropsBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));

	public static final RegistryObject<Block> KINUTOFU = register("blocktofukinu", () -> new KinuTofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).randomTicks().strength(0.1F, 0.2F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> MOMENTOFU = register("blocktofumomen", () -> new TofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).randomTicks().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> ISHITOFU = register("blocktofuishi", () -> new TofuBlock(BlockBehaviour.Properties.of(Material.STONE).randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ISHITOFU_BRICK = register("tofuishi_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ISHITOFU_SMOOTH_BRICK = register("tofuishi_smooth_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ISHITOFU_CHISELED_BRICK = register("tofuishi_chiseled_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> METALTOFU = register("blocktofumetal", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DIAMONDTOFU = register("blocktofudiamond", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TOFU_GEM_BLOCK = register("tofu_gem_block", () -> new TofuGemBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.METAL)));

	public static final RegistryObject<Block> GRILLEDTOFU = register("blocktofugrilled", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> ZUNDATOFU = register("blocktofuzunda", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> MISOTOFU = register("blocktofumiso", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> DRIEDTOFU = register("blocktofudried", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final RegistryObject<Block> EGGTOFU = register("blocktofuegg", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> SESAMETOFU = register("blocktofusesame", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final RegistryObject<Block> HELLTOFU = register("blocktofuhell", () -> new Block(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> HELLTOFU_BRICK = register("tofuhell_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> HELLTOFU_SMOOTH_BRICK = register("tofuhell_smooth_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> SOULTOFU = register("blocktofusoul", () -> new TofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).randomTicks().sound(SoundType.SNOW)));
	public static final RegistryObject<Block> SOULTOFU_BRICK = register("tofusoul_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SOULTOFU_SMOOTH_BRICK = register("tofusoul_smooth_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> MINCEDTOFU = register("blocktofuminced", () -> new MincedTofuBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F, 0.3F).sound(SoundType.SNOW)));

	public static final RegistryObject<StairBlock> TOFUSTAIR_KINU = register("tofustair_kinu", () -> new StairBlock(KINUTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(KINUTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_MOMEN = register("tofustair_momen", () -> new StairBlock(MOMENTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(MOMENTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_ISHI = register("tofustair_ishi", () -> new StairBlock(ISHITOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(ISHITOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_METAL = register("tofustair_metal", () -> new StairBlock(METALTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(METALTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_GRILLED = register("tofustair_grilled", () -> new StairBlock(GRILLEDTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(GRILLEDTOFU.get())));

	public static final RegistryObject<StairBlock> TOFUSTAIR_ZUNDA = register("tofustair_zunda", () -> new StairBlock(ZUNDATOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(ZUNDATOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_HELL = register("tofustair_hell", () -> new StairBlock(HELLTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(HELLTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_SOUL = register("tofustair_soul", () -> new StairBlock(SOULTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(SOULTOFU.get())));

	public static final RegistryObject<StairBlock> TOFUSTAIR_ISHIBRICK = register("tofustair_ishibrick", () -> new StairBlock(ISHITOFU_BRICK.get()::defaultBlockState, BlockBehaviour.Properties.copy(ISHITOFU_BRICK.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_HELLBRICK = register("tofustair_hellbrick", () -> new StairBlock(HELLTOFU_BRICK.get()::defaultBlockState, BlockBehaviour.Properties.copy(HELLTOFU_BRICK.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_SOULBRICK = register("tofustair_soulbrick", () -> new StairBlock(SOULTOFU_BRICK.get()::defaultBlockState, BlockBehaviour.Properties.copy(SOULTOFU_BRICK.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_MISO = register("tofustair_miso", () -> new StairBlock(MISOTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(MISOTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_DRIED = register("tofustair_dried", () -> new StairBlock(DRIEDTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(DRIEDTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_EGG = register("tofustair_egg", () -> new StairBlock(EGGTOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(EGGTOFU.get())));
	public static final RegistryObject<StairBlock> TOFUSTAIR_SESAME = register("tofustair_sesame", () -> new StairBlock(SESAMETOFU.get()::defaultBlockState, BlockBehaviour.Properties.copy(SESAMETOFU.get())));

	public static final RegistryObject<SlabBlock> TOFUSLAB_KINU = register("tofuslab_kinu", () -> new SlabBlock(BlockBehaviour.Properties.copy(KINUTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_MOMEN = register("tofuslab_momen", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOMENTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_ISHI = register("tofuslab_ishi", () -> new SlabBlock(BlockBehaviour.Properties.copy(ISHITOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_METAL = register("tofuslab_metal", () -> new SlabBlock(BlockBehaviour.Properties.copy(METALTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_GRILLED = register("tofuslab_grilled", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRILLEDTOFU.get())));

	public static final RegistryObject<SlabBlock> TOFUSLAB_ZUNDA = register("tofuslab_zunda", () -> new SlabBlock(BlockBehaviour.Properties.copy(ZUNDATOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_HELL = register("tofuslab_hell", () -> new SlabBlock(BlockBehaviour.Properties.copy(HELLTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_SOUL = register("tofuslab_soul", () -> new SlabBlock(BlockBehaviour.Properties.copy(SOULTOFU.get())));

	public static final RegistryObject<SlabBlock> TOFUSLAB_ISHIBRICK = register("tofuslab_ishibrick", () -> new SlabBlock(BlockBehaviour.Properties.copy(ISHITOFU_BRICK.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_HELLBRICK = register("tofuslab_hellbrick", () -> new SlabBlock(BlockBehaviour.Properties.copy(HELLTOFU_BRICK.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_SOULBRICK = register("tofuslab_soulbrick", () -> new SlabBlock(BlockBehaviour.Properties.copy(SOULTOFU_BRICK.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_MISO = register("tofuslab_miso", () -> new SlabBlock(BlockBehaviour.Properties.copy(MISOTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_DRIED = register("tofuslab_dried", () -> new SlabBlock(BlockBehaviour.Properties.copy(DRIEDTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_EGG = register("tofuslab_egg", () -> new SlabBlock(BlockBehaviour.Properties.copy(EGGTOFU.get())));
	public static final RegistryObject<SlabBlock> TOFUSLAB_SESAME = register("tofuslab_sesame", () -> new SlabBlock(BlockBehaviour.Properties.copy(SESAMETOFU.get())));


	public static final RegistryObject<Block> TOFUTORCH_KINU = register("tofutorch_kinu", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_MOMEN = register("tofutorch_momen", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_ISHI = register("tofutorch_ishi", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.STONE), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_METAL = register("tofutorch_metal", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.METAL), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_GRILLED = register("tofutorch_grilled", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME));
	public static final RegistryObject<Block> TOFUTORCH_ZUNDA = register("tofutorch_zunda", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME));

	public static final RegistryObject<Block> WALLTOFUTORCH_KINU = BLOCKS.register("walltofutorch_kinu", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_KINU), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_MOMEN = BLOCKS.register("walltofutorch_momen", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_MOMEN), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_ISHI = BLOCKS.register("walltofutorch_ishi", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.STONE).lootFrom(TOFUTORCH_ISHI), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_METAL = BLOCKS.register("walltofutorch_metal", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.METAL).lootFrom(TOFUTORCH_METAL), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_GRILLED = BLOCKS.register("walltofutorch_grilled", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_GRILLED), ParticleTypes.FLAME));
	public static final RegistryObject<Block> WALLTOFUTORCH_ZUNDA = BLOCKS.register("walltofutorch_zunda", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_ZUNDA), ParticleTypes.FLAME));

	public static final RegistryObject<Block> TOFULADDER_KINU = register("tofuladder_kinu", () -> new LadderBlock(BlockBehaviour.Properties.copy(KINUTOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_MOMEN = register("tofuladder_momen", () -> new LadderBlock(BlockBehaviour.Properties.copy(MOMENTOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_ISHI = register("tofuladder_ishi", () -> new LadderBlock(BlockBehaviour.Properties.copy(ISHITOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_METAL = register("tofuladder_metal", () -> new LadderBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion()));

	public static final RegistryObject<Block> TOFULADDER_GRILLED = register("tofuladder_grilled", () -> new LadderBlock(BlockBehaviour.Properties.copy(GRILLEDTOFU.get()).noOcclusion()));
	public static final RegistryObject<Block> TOFULADDER_ZUNDA = register("tofuladder_zunda", () -> new LadderBlock(BlockBehaviour.Properties.copy(ZUNDATOFU.get()).noOcclusion()));

	public static final RegistryObject<Block> TOFULADDER_ISHIBRICK = register("tofuladder_ishibrick", () -> new LadderBlock(BlockBehaviour.Properties.copy(ISHITOFU_BRICK.get()).noOcclusion()));

	public static final RegistryObject<WallBlock> TOFUFENCE_KINU = register("tofufence_kinu", () -> new WallBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.1F, 0.2F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_MOMEN = register("tofufence_momen", () -> new WallBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_ISHI = register("tofufence_ishi", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_METAL = register("tofufence_metal", () -> new WallBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_HELL = register("tofufence_hell", () -> new WallBlock(BlockBehaviour.Properties.copy(HELLTOFU.get()).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_SOUL = register("tofufence_soul", () -> new WallBlock(BlockBehaviour.Properties.copy(SOULTOFU.get()).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_GRILLED = register("tofufence_grilled", () -> new WallBlock(BlockBehaviour.Properties.copy(GRILLEDTOFU.get()).noOcclusion()));
	public static final RegistryObject<WallBlock> TOFUFENCE_ZUNDA = register("tofufence_zunda", () -> new WallBlock(BlockBehaviour.Properties.copy(ZUNDATOFU.get()).noOcclusion()));


	public static final RegistryObject<DoorBlock> TOFUDOOR_KINU = register("tofudoor_kinu", () -> new TofuDoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<DoorBlock> TOFUDOOR_MOMEN = register("tofudoor_momen", () -> new TofuDoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<DoorBlock> TOFUDOOR_ISHI = register("tofudoor_ishi", () -> new TofuDoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<DoorBlock> TOFUDOOR_METAL = register("tofudoor_metal", () -> new TofuDoorBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion(), SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN));
	public static final RegistryObject<DoorBlock> TOFUDOOR_HELL = register("tofudoor_hell", () -> new TofuDoorBlock(BlockBehaviour.Properties.copy(HELLTOFU.get()).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<DoorBlock> TOFUDOOR_SOUL = register("tofudoor_soul", () -> new TofuDoorBlock(BlockBehaviour.Properties.copy(SOULTOFU.get()).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<DoorBlock> TOFUDOOR_GRILLED = register("tofudoor_grilled", () -> new TofuDoorBlock(BlockBehaviour.Properties.copy(GRILLEDTOFU.get()).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<DoorBlock> TOFUDOOR_ZUNDA = register("tofudoor_zunda", () -> new TofuDoorBlock(BlockBehaviour.Properties.copy(ZUNDATOFU.get()).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));

	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_KINU = register("tofutrapdoor_kinu", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_MOMEN = register("tofutrapdoor_momen", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_ISHI = register("tofutrapdoor_ishi", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_METAL = register("tofutrapdoor_metal", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(METALTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_HELL = register("tofutrapdoor_hell", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(HELLTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_SOUL = register("tofutrapdoor_soul", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(SOULTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_GRILLED = register("tofutrapdoor_grilled", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(GRILLEDTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN));
	public static final RegistryObject<TrapDoorBlock> TOFUTRAPDOOR_ZUNDA = register("tofutrapdoor_zunda", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(ZUNDATOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN));


	public static final RegistryObject<Block> TOFU_TERRAIN = register("tofu_terrain", () -> new TofuTerrainBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.4F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> TOFU_TERRAIN_ZUNDA = register("tofu_terrain_zunda", () -> new TofuTerrainBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.4F, 0.5F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> TOFUSLATE = register("tofuslate", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.5F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> TOFUSLATE_TOFU_DIAMOND_ORE = register("tofuslate_tofu_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F, 4.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 5)));
	public static final RegistryObject<Block> ORE_TOFU_DIAMOND = register("ore_tofu_diamond", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW), UniformInt.of(3, 5)));
	public static final RegistryObject<Block> ORE_TOFUGEM = register("ore_tofugem", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).sound(SoundType.SNOW), UniformInt.of(2, 3)));

	public static final RegistryObject<Block> TOFU_BEDROCK = register("tofu_bedrock", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).sound(SoundType.STONE).isValidSpawn((state, blockGetter, blockPos, entityType) -> false)));

	public static final RegistryObject<Block> SAPLING_TOFU = register("sapling_tofu", () -> new TofuSaplingBlock(new TofuTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> LEAVES_TOFU = register("leaves_tofu", () -> new TofuLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).noOcclusion().randomTicks().isSuffocating((state, getter, pos) -> false).sound(SoundType.GRASS)));

	public static final RegistryObject<Block> LEEK = register("blockleek", () -> new LeekBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noOcclusion().noCollission().sound(SoundType.GRASS)));

	public static final RegistryObject<Block> SAPLING_APRICOT = register("sapling_apricot", () -> new ApricotSaplingBlock(new ApricotTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> LEAVES_APRICOT = register("leaves_apricot", () -> new ApricotLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).noOcclusion().isSuffocating((state, getter, pos) -> false).randomTicks().sound(SoundType.GRASS)));

	public static final RegistryObject<RotatedPillarBlock> LEEK_GREEN_STEM = register("leek_green_stem", () -> new BurnableRotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final RegistryObject<RotatedPillarBlock> LEEK_STEM = register("leek_stem", () -> new BurnableRotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final RegistryObject<Block> ZUNDATOFU_MUSHROOM = register("zundatofu_mushroom", () -> new TofuMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.FUNGUS), TofuWorldFeatures.BIG_ZUNDA_TOFU_MUSHUROOM));
	public static final RegistryObject<RotatedPillarBlock> TOFU_STEM = register("tofustem", () -> new BurnableRotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final RegistryObject<Block> TOFU_STEM_PLANKS = register("tofustem_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));


	public static final RegistryObject<TofuPortalBlock> TOFU_PORTAL = BLOCKS.register("tofuportal", () -> new TofuPortalBlock(BlockBehaviour.Properties.of(Material.PORTAL).strength(-1.0F).noCollission().noLootTable().sound(SoundType.GLASS).lightLevel((p_50872_) -> {
		return 11;
	})));
	public static final RegistryObject<Block> TOFU_FARMLAND = register("tofu_farmland", () -> new TofuFarmlandBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.5F, 1.0F).noOcclusion().sound(SoundType.SNOW)));
	public static final RegistryObject<Block> SALTPAN = register("blocksaltpan", () -> new SaltPanBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final RegistryObject<Block> SALT_FURNACE = register("salt_furnace", () -> new SaltFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).sound(SoundType.STONE).lightLevel((p_50872_) -> {
		return p_50872_.getValue(SaltFurnaceBlock.LIT) ? 13 : 0;
	})));
	public static final RegistryObject<Block> SPROUTSJAR = register("blocksproutsjar", () -> new SproutsJarBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(2.0F, 3.0F).randomTicks().sound(SoundType.GLASS)));
	public static final RegistryObject<Block> MORIJIO = register("morijio", () -> new MorijioBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	//BARREL
	public static final RegistryObject<Block> BARREL_MISO = register("barrel_miso", () -> new MisoBarrelBlock(TofuItems.BOTTLE_SOYSAUSE, BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BARREL_MISOTOFU = register("barrel_misotofu", () -> new WeightBaseBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final RegistryObject<Block> NATTOBED = register("nattobed", () -> new NoWeightBaseBlock(BlockBehaviour.Properties.of(TofuMaterial.NATTO).strength(1.0F, 2.0F).randomTicks().sound(SoundType.GRASS)));


	public static final RegistryObject<Block> TOFUCAKE = register("tofucake", () -> new TofuCakeBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).noOcclusion().sound(SoundType.WOOL), 1, 0.1F));
	public static final RegistryObject<Block> ZUNDATOFUCAKE = register("zundatofucake", () -> new TofuCakeBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).noOcclusion().sound(SoundType.WOOL), 1, 0.2F));
	public static final RegistryObject<Block> SOYCHEESE_TART = register("soycheese_tart", () -> new TofuCakeBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).noOcclusion().sound(SoundType.WOOL), 2, 0.3F));

	public static final RegistryObject<Block> TOFU_CANDLE_CAKE = noItemRegister("candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> WHITE_TOFU_CANDLE_CAKE = noItemRegister("white_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.WHITE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> ORANGE_TOFU_CANDLE_CAKE = noItemRegister("orange_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> MAGENTA_TOFU_CANDLE_CAKE = noItemRegister("magenta_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIGHT_BLUE_TOFU_CANDLE_CAKE = noItemRegister("light_blue_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> YELLOW_TOFU_CANDLE_CAKE = noItemRegister("yellow_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIME_TOFU_CANDLE_CAKE = noItemRegister("lime_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.LIME_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> PINK_TOFU_CANDLE_CAKE = noItemRegister("pink_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.PINK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> GRAY_TOFU_CANDLE_CAKE = noItemRegister("gray_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIGHT_GRAY_TOFU_CANDLE_CAKE = noItemRegister("light_gray_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> CYAN_TOFU_CANDLE_CAKE = noItemRegister("cyan_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.CYAN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> PURPLE_TOFU_CANDLE_CAKE = noItemRegister("purple_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BLUE_TOFU_CANDLE_CAKE = noItemRegister("blue_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BROWN_TOFU_CANDLE_CAKE = noItemRegister("brown_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.BROWN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> GREEN_TOFU_CANDLE_CAKE = noItemRegister("green_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.GREEN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> RED_TOFU_CANDLE_CAKE = noItemRegister("red_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.RED_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BLACK_TOFU_CANDLE_CAKE = noItemRegister("black_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE, Blocks.BLACK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));

	public static final RegistryObject<Block> ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> WHITE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("white_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.WHITE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> ORANGE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("orange_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> MAGENTA_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("magenta_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("light_blue_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> YELLOW_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("yellow_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIME_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("lime_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.LIME_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> PINK_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("pink_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.PINK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> GRAY_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("gray_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("light_gray_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> CYAN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("cyan_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.CYAN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> PURPLE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("purple_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BLUE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("blue_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BROWN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("brown_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.BROWN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> GREEN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("green_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.GREEN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> RED_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("red_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.RED_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BLACK_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("black_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE, Blocks.BLACK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));


	public static final RegistryObject<Block> SOYCHEESE_CANDLE_TART = noItemRegister("candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> WHITE_SOYCHEESE_CANDLE_TART = noItemRegister("white_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.WHITE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> ORANGE_SOYCHEESE_CANDLE_TART = noItemRegister("orange_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> MAGENTA_SOYCHEESE_CANDLE_TART = noItemRegister("magenta_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIGHT_BLUE_SOYCHEESE_CANDLE_TART = noItemRegister("light_blue_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> YELLOW_SOYCHEESE_CANDLE_TART = noItemRegister("yellow_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIME_SOYCHEESE_CANDLE_TART = noItemRegister("lime_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.LIME_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> PINK_SOYCHEESE_CANDLE_TART = noItemRegister("pink_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.PINK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> GRAY_SOYCHEESE_CANDLE_TART = noItemRegister("gray_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> LIGHT_GRAY_SOYCHEESE_CANDLE_TART = noItemRegister("light_gray_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> CYAN_SOYCHEESE_CANDLE_TART = noItemRegister("cyan_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.CYAN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> PURPLE_SOYCHEESE_CANDLE_TART = noItemRegister("purple_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BLUE_SOYCHEESE_CANDLE_TART = noItemRegister("blue_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BROWN_SOYCHEESE_CANDLE_TART = noItemRegister("brown_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.BROWN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> GREEN_SOYCHEESE_CANDLE_TART = noItemRegister("green_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.GREEN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> RED_SOYCHEESE_CANDLE_TART = noItemRegister("red_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.RED_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));
	public static final RegistryObject<Block> BLACK_SOYCHEESE_CANDLE_TART = noItemRegister("black_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART, Blocks.BLACK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE)));


	public static final RegistryObject<SoymilkCauldronBlock> SOYMILK_CAULDRON = noItemRegister("soymilk_cauldron", () -> new SoymilkCauldronBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK, TofuItems.SOY_CHEESE));
	public static final RegistryObject<SoymilkCauldronBlock> SOYMILK_NETHER_CAULDRON = noItemRegister("soymilk_nether_cauldron", () -> new SoymilkCauldronBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK_NETHER, TofuItems.SOY_NETHER_CHEESE));
	public static final RegistryObject<SoymilkCauldronBlock> SOYMILK_SOUL_CAULDRON = noItemRegister("soymilk_soul_cauldron", () -> new SoymilkCauldronBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK_SOUL, TofuItems.SOY_SOUL_CHEESE));

	public static final RegistryObject<Block> TOFUBED = register("tofubed", () -> new TofuBedBlock(BlockBehaviour.Properties.of(TofuMaterial.TOFU).strength(0.2F).noOcclusion().sound(SoundType.SNOW)));
	public static final RegistryObject<Block> TOFUCHEST = register("tofuchest", () -> new TofuChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).noOcclusion().sound(SoundType.STONE), TofuBlockEntitys.TOFUCHEST::get));

	public static final RegistryObject<Block> TF_STORAGE = register("tf_storage", () -> new TFStorageBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFStorageBlock.LIT) ? 13 : 0;
	})));
	public static final RegistryObject<Block> ANTENNA_BASIC = register("antenna_basic", () -> new TFAntennaBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().noOcclusion().noCollission().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<Block> TF_AGGREGATOR = register("tf_aggregator", () -> new TFAggregatorBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFAggregatorBlock.LIT) ? 13 : 0;
	})));

	public static final RegistryObject<Block> FOODPLATE = register("foodplate", () -> new FoodPlateBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.0F).sound(SoundType.METAL)));

	public static final RegistryObject<Block> RICE_BLOCK = register("rice_block",
			() -> new RiceBlock(BlockBehaviour.Properties.of(Material.GRASS)
					.strength(2.0F)
					.sound(SoundType.GRASS)
					.noOcclusion())
	);

	private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		Supplier<? extends Item> itemSupplier = item.apply(register);
		TofuItems.ITEMS.register(name, itemSupplier);
		return register;
	}

	private static <T extends Block> RegistryObject<T> noItemRegister(String name, Supplier<? extends T> block) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		return register;
	}

	private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
		return (RegistryObject<B>) baseRegister(name, block, (object) -> TofuBlocks.registerBlockItem(object));
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
		return () -> {
			if (Objects.requireNonNull(block.get()) == TOFUTORCH_KINU.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_KINU.get(), WALLTOFUTORCH_KINU.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_MOMEN.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_MOMEN.get(), WALLTOFUTORCH_MOMEN.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_ISHI.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_ISHI.get(), WALLTOFUTORCH_ISHI.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_METAL.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_METAL.get(), WALLTOFUTORCH_METAL.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_GRILLED.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_GRILLED.get(), WALLTOFUTORCH_GRILLED.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_ZUNDA.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_ZUNDA.get(), WALLTOFUTORCH_ZUNDA.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFUBED.get()) {
				return new BedItem(Objects.requireNonNull(block.get()), new Item.Properties().stacksTo(1)) {
					@Override
					public void initializeClient(Consumer<IClientItemExtensions> consumer) {
						super.initializeClient(consumer);
						consumer.accept(new IClientItemExtensions() {
							@Override
							public BlockEntityWithoutLevelRenderer getCustomRenderer() {
								return new TofuBedBWLR();
							}
						});
					}
				};
			} else if (Objects.requireNonNull(block.get()) == TOFUCHEST.get()) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties()) {
					@Override
					public void initializeClient(Consumer<IClientItemExtensions> consumer) {
						super.initializeClient(consumer);
						consumer.accept(new IClientItemExtensions() {
							@Override
							public BlockEntityWithoutLevelRenderer getCustomRenderer() {
								return new TofuChestBWLR();
							}
						});
					}
				};
			} else if (Objects.requireNonNull(block.get()) instanceof TofuCakeBlock) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().stacksTo(1));
			} else if (Objects.requireNonNull(block.get()) instanceof DoorBlock) {
				return new DoubleHighBlockItem(Objects.requireNonNull(block.get()), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) instanceof TFAntennaBlock || Objects.requireNonNull(block.get()) instanceof TFAggregatorBlock || Objects.requireNonNull(block.get()) instanceof TFStorageBlock) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
			} else {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
			}
		};
	}
}
