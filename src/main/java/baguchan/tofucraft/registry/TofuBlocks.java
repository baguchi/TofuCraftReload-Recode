package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.BagBlock;
import baguchan.tofucraft.block.BurnableRotatedPillarBlock;
import baguchan.tofucraft.block.CandleTofuCakeBlock;
import baguchan.tofucraft.block.FallFoodBlock;
import baguchan.tofucraft.block.FoodPlateBlock;
import baguchan.tofucraft.block.KinuTofuBlock;
import baguchan.tofucraft.block.LeekBlock;
import baguchan.tofucraft.block.MorijioBlock;
import baguchan.tofucraft.block.RiceBlock;
import baguchan.tofucraft.block.SuspiciousTofuTerrainBlock;
import baguchan.tofucraft.block.TofuBlock;
import baguchan.tofucraft.block.TofuCakeBlock;
import baguchan.tofucraft.block.TofuCeilingHangingSignBlock;
import baguchan.tofucraft.block.TofuDetectorBlock;
import baguchan.tofucraft.block.TofuFarmlandBlock;
import baguchan.tofucraft.block.TofuFlowerBlock;
import baguchan.tofucraft.block.TofuGemBlock;
import baguchan.tofucraft.block.TofuGrateBlock;
import baguchan.tofucraft.block.TofuGrilledBlock;
import baguchan.tofucraft.block.TofuLeavesBlock;
import baguchan.tofucraft.block.TofuMagmaBlock;
import baguchan.tofucraft.block.TofuMushroomBlock;
import baguchan.tofucraft.block.TofuPortalBlock;
import baguchan.tofucraft.block.TofuSaplingBlock;
import baguchan.tofucraft.block.TofuStandingSignBlock;
import baguchan.tofucraft.block.TofuTerrainBlock;
import baguchan.tofucraft.block.TofuTrapDoorBlock;
import baguchan.tofucraft.block.TofuVaultBlock;
import baguchan.tofucraft.block.TofuWallHangingSignBlock;
import baguchan.tofucraft.block.TofuWallSignBlock;
import baguchan.tofucraft.block.TofunianStatueBlock;
import baguchan.tofucraft.block.YubaBlock;
import baguchan.tofucraft.block.ZundamaBlock;
import baguchan.tofucraft.block.crop.ChiliCropsBlock;
import baguchan.tofucraft.block.crop.LeekCropsBlock;
import baguchan.tofucraft.block.crop.RiceCropsBlock;
import baguchan.tofucraft.block.crop.RiceRootBlock;
import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.block.crop.SproutsCropBlock;
import baguchan.tofucraft.block.tfenergy.TFAntennaBlock;
import baguchan.tofucraft.block.tfenergy.TFCollectorBlock;
import baguchan.tofucraft.block.tfenergy.TFCrafterBlock;
import baguchan.tofucraft.block.tfenergy.TFOvenBlock;
import baguchan.tofucraft.block.tfenergy.TFStorageBlock;
import baguchan.tofucraft.block.tfenergy.TofuWorkStationBlock;
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
import baguchan.tofucraft.world.gen.grower.TofuTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class TofuBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, TofuCraftReload.MODID);

	public static final Supplier<Block> SOYMILK = noItemRegister("soymilk", () -> new LiquidBlock(TofuFluids.SOYMILK.value(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final Supplier<Block> SOYMILK_HELL = noItemRegister("soymilk_hell", () -> new LiquidBlock(TofuFluids.SOYMILK_HELL.value(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final Supplier<Block> SOYMILK_SOUL = noItemRegister("soymilk_soul", () -> new LiquidBlock(TofuFluids.SOYMILK_SOUL.value(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final Supplier<Block> BITTERN = noItemRegister("bittern", () -> new LiquidBlock(TofuFluids.BITTERN.value(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final Supplier<Block> DOUBANJIANG = noItemRegister("doubanjiang", () -> new LiquidBlock(TofuFluids.DOUBANJIANG.value(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().lightLevel((blockstate) -> 10).sound(SoundType.EMPTY)));

	public static final Supplier<Block> YUBA = noItemRegister("yuba", () -> new YubaBlock(BlockBehaviour.Properties.of().noOcclusion().randomTicks().strength(0.25F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOL)));


	public static final Supplier<Block> SOYBEAN = noItemRegister("soybean", () -> new SoybeanCropsBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final Supplier<Block> SOYBEAN_NETHER = noItemRegister("soybean_nether", () -> new SoybeanNetherCropsBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final Supplier<Block> SOYBEAN_SOUL = noItemRegister("soybean_soul", () -> new SoybeanSoulCropsBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final Supplier<Block> LEEK_CROP = noItemRegister("leek_crop", () -> new LeekCropsBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final Supplier<Block> RICE_CROP = noItemRegister("rice", () -> new RiceCropsBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final Supplier<Block> RICE_ROOT = noItemRegister("rice_root", () -> new RiceRootBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().strength(0.1F).sound(SoundType.CROP)));
	public static final Supplier<Block> CHILI_CROP = noItemRegister("chili_crop", () -> new ChiliCropsBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final Supplier<Block> SPROUTS = noItemRegister("sprouts_crop", () -> new SproutsCropBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));

	public static final Supplier<Block> KINUTOFU = register("blocktofukinu", () -> new KinuTofuBlock(BlockBehaviour.Properties.of().randomTicks().strength(0.1F, 0.2F).sound(SoundType.SNOW)));
	public static final Supplier<Block> MOMENTOFU = register("blocktofumomen", () -> new TofuBlock(BlockBehaviour.Properties.of().randomTicks().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final Supplier<Block> ISHITOFU = register("blocktofuishi", () -> new TofuBlock(BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> ISHITOFU_BRICK = register("tofuishi_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> ISHITOFU_SMOOTH_BRICK = register("tofuishi_smooth_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> ISHITOFU_CHISELED_BRICK = register("tofuishi_chiseled_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> METALTOFU = register("blocktofumetal", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final Supplier<Block> METAL_TOFU_GRATE = register("tofu_metal_grate", () -> new TofuGrateBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion().sound(SoundType.COPPER_GRATE)));
	public static final Supplier<Block> METAL_TOFU_LUMP = register("tofu_metal_lump", () -> new TofuGrateBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).lightLevel(state -> {
		return 15;
	}).sound(SoundType.METAL)));
	public static final Supplier<Block> DIAMONDTOFU = register("blocktofudiamond", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final Supplier<Block> TOFU_GEM_BLOCK = register("tofu_gem_block", () -> new TofuGemBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.METAL)));
	public static final Supplier<Block> ADVANCE_TOFU_GEM_BLOCK = register("adv_tofu_gem_block", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.METAL)));

	public static final Supplier<RotatedPillarBlock> GRILLEDTOFU = register("blocktofugrilled", () -> new TofuGrilledBlock(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final Supplier<Block> ZUNDATOFU = register("blocktofuzunda", () -> new Block(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final Supplier<Block> ZUNDATOFU_BRICK = register("tofuzunda_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> ZUNDATOFU_SMOOTH_BRICK = register("tofuzunda_smooth_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Supplier<Block> MISOTOFU = register("blocktofumiso", () -> new Block(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final Supplier<Block> DRIEDTOFU = register("blocktofudried", () -> new Block(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final Supplier<Block> EGGTOFU = register("blocktofuegg", () -> new Block(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final Supplier<Block> EGGTOFU_BRICK = register("tofuegg_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Supplier<Block> SESAMETOFU = register("blocktofusesame", () -> new Block(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final Supplier<Block> HELLTOFU = register("blocktofuhell", () -> new Block(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final Supplier<Block> HELLTOFU_BRICK = register("tofuhell_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> HELLTOFU_SMOOTH_BRICK = register("tofuhell_smooth_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Supplier<Block> SOULTOFU = register("blocktofusoul", () -> new TofuBlock(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).randomTicks().sound(SoundType.SNOW)));
	public static final Supplier<Block> SOULTOFU_BRICK = register("tofusoul_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> SOULTOFU_SMOOTH_BRICK = register("tofusoul_smooth_brick", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> MINCEDTOFU = register("blocktofuminced", () -> new FallFoodBlock(BlockBehaviour.Properties.of().strength(0.2F, 0.3F).sound(SoundType.SNOW)));

	public static final Supplier<StairBlock> TOFUSTAIR_KINU = register("tofustair_kinu", () -> new StairBlock(KINUTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(KINUTOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_MOMEN = register("tofustair_momen", () -> new StairBlock(MOMENTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOMENTOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_ISHI = register("tofustair_ishi", () -> new StairBlock(ISHITOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ISHITOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_METAL = register("tofustair_metal", () -> new StairBlock(METALTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(METALTOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_GRILLED = register("tofustair_grilled", () -> new StairBlock(GRILLEDTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GRILLEDTOFU.get())));

	public static final Supplier<StairBlock> TOFUSTAIR_ZUNDA = register("tofustair_zunda", () -> new StairBlock(ZUNDATOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_ZUNDABRICK = register("tofustair_zundabrick", () -> new StairBlock(ZUNDATOFU_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU_BRICK.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_HELL = register("tofustair_hell", () -> new StairBlock(HELLTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_SOUL = register("tofustair_soul", () -> new StairBlock(SOULTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get())));

	public static final Supplier<StairBlock> TOFUSTAIR_ISHIBRICK = register("tofustair_ishibrick", () -> new StairBlock(ISHITOFU_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ISHITOFU_BRICK.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_HELLBRICK = register("tofustair_hellbrick", () -> new StairBlock(HELLTOFU_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(HELLTOFU_BRICK.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_SOULBRICK = register("tofustair_soulbrick", () -> new StairBlock(SOULTOFU_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SOULTOFU_BRICK.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_MISO = register("tofustair_miso", () -> new StairBlock(MISOTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MISOTOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_DRIED = register("tofustair_dried", () -> new StairBlock(DRIEDTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DRIEDTOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_EGG = register("tofustair_egg", () -> new StairBlock(EGGTOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(EGGTOFU.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_EGGBRICK = register("tofustair_eggbrick", () -> new StairBlock(EGGTOFU_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(EGGTOFU_BRICK.get())));
	public static final Supplier<StairBlock> TOFUSTAIR_SESAME = register("tofustair_sesame", () -> new StairBlock(SESAMETOFU.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SESAMETOFU.get())));

	public static final Supplier<SlabBlock> TOFUSLAB_KINU = register("tofuslab_kinu", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(KINUTOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_MOMEN = register("tofuslab_momen", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOMENTOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_ISHI = register("tofuslab_ishi", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ISHITOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_METAL = register("tofuslab_metal", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(METALTOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_GRILLED = register("tofuslab_grilled", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(GRILLEDTOFU.get())));

	public static final Supplier<SlabBlock> TOFUSLAB_ZUNDA = register("tofuslab_zunda", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_ZUNDABRICK = register("tofuslab_zundabrick", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU_BRICK.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_HELL = register("tofuslab_hell", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_SOUL = register("tofuslab_soul", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get())));

	public static final Supplier<SlabBlock> TOFUSLAB_ISHIBRICK = register("tofuslab_ishibrick", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ISHITOFU_BRICK.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_HELLBRICK = register("tofuslab_hellbrick", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(HELLTOFU_BRICK.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_SOULBRICK = register("tofuslab_soulbrick", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SOULTOFU_BRICK.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_MISO = register("tofuslab_miso", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MISOTOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_DRIED = register("tofuslab_dried", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(DRIEDTOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_EGG = register("tofuslab_egg", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(EGGTOFU.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_EGGBRICK = register("tofuslab_eggbrick", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(EGGTOFU_BRICK.get())));
	public static final Supplier<SlabBlock> TOFUSLAB_SESAME = register("tofuslab_sesame", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SESAMETOFU.get())));


	public static final Supplier<Block> TOFUTORCH_KINU = register("tofutorch_kinu", () -> new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final Supplier<Block> TOFUTORCH_MOMEN = register("tofutorch_momen", () -> new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final Supplier<Block> TOFUTORCH_ISHI = register("tofutorch_ishi", () -> new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 6.0F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.STONE)));
	public static final Supplier<Block> TOFUTORCH_METAL = register("tofutorch_metal", () -> new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 7.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.METAL)));
	public static final Supplier<Block> TOFUTORCH_GRILLED = register("tofutorch_grilled", () -> new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW)));
	public static final Supplier<Block> TOFUTORCH_ZUNDA = register("tofutorch_zunda", () -> new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW)));
	public static final Supplier<Block> TOFUTORCH_HELL = register("tofutorch_hell", () -> new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW)));
	public static final Supplier<Block> TOFUTORCH_SOUL = register("tofutorch_soul", () -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 10)
			.noOcclusion().sound(SoundType.SNOW)));

	public static final Supplier<Block> WALLTOFUTORCH_KINU = BLOCKS.register("walltofutorch_kinu", () -> new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_KINU)));
	public static final Supplier<Block> WALLTOFUTORCH_MOMEN = BLOCKS.register("walltofutorch_momen", () -> new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_MOMEN)));
	public static final Supplier<Block> WALLTOFUTORCH_ISHI = BLOCKS.register("walltofutorch_ishi", () -> new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 6.0F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.STONE).lootFrom(TOFUTORCH_ISHI)));
	public static final Supplier<Block> WALLTOFUTORCH_METAL = BLOCKS.register("walltofutorch_metal", () -> new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 7.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.METAL).lootFrom(TOFUTORCH_METAL)));
	public static final Supplier<Block> WALLTOFUTORCH_GRILLED = BLOCKS.register("walltofutorch_grilled", () -> new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_GRILLED)));
	public static final Supplier<Block> WALLTOFUTORCH_ZUNDA = BLOCKS.register("walltofutorch_zunda", () -> new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_ZUNDA)));
	public static final Supplier<Block> WALLTOFUTORCH_HELL = BLOCKS.register("walltofutorch_hell", () -> new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_HELL)));
	public static final Supplier<Block> WALLTOFUTORCH_SOUL = BLOCKS.register("walltofutorch_soul", () -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW).lootFrom(TOFUTORCH_SOUL)));

	public static final Supplier<Block> TOFU_METAL_CHAIN = register("tofu_metal_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)
			.sound(SoundType.CHAIN)));
	public static final Supplier<Block> TOFU_METAL_LANTERN = register("tofu_metal_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> 15)
			.sound(SoundType.LANTERN)));
	public static final Supplier<Block> TOFU_METAL_SOUL_LANTERN = register("tofu_metal_soul_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN).lightLevel(state -> 10)
			.sound(SoundType.LANTERN)));

	public static final Supplier<Block> TOFULADDER_KINU = register("tofuladder_kinu", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(KINUTOFU.get()).noOcclusion()));
	public static final Supplier<Block> TOFULADDER_MOMEN = register("tofuladder_momen", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(MOMENTOFU.get()).noOcclusion()));
	public static final Supplier<Block> TOFULADDER_ISHI = register("tofuladder_ishi", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(ISHITOFU.get()).noOcclusion()));
	public static final Supplier<Block> TOFULADDER_METAL = register("tofuladder_metal", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion()));

	public static final Supplier<Block> TOFULADDER_GRILLED = register("tofuladder_grilled", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(GRILLEDTOFU.get()).noOcclusion()));
	public static final Supplier<Block> TOFULADDER_ZUNDA = register("tofuladder_zunda", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion()));
	public static final Supplier<Block> TOFULADDER_HELL = register("tofuladder_hell", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion()));
	public static final Supplier<Block> TOFULADDER_SOUL = register("tofuladder_soul", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion()));

	public static final Supplier<Block> TOFULADDER_ISHIBRICK = register("tofuladder_ishibrick", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(ISHITOFU_BRICK.get()).noOcclusion()));

	public static final Supplier<WallBlock> TOFUFENCE_KINU = register("tofufence_kinu", () -> new WallBlock(BlockBehaviour.Properties.of().strength(0.1F, 0.2F).sound(SoundType.SNOW).noOcclusion()));
	public static final Supplier<WallBlock> TOFUFENCE_MOMEN = register("tofufence_momen", () -> new WallBlock(BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));
	public static final Supplier<WallBlock> TOFUFENCE_ISHI = register("tofufence_ishi", () -> new WallBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
	public static final Supplier<WallBlock> TOFUFENCE_METAL = register("tofufence_metal", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion()));
	public static final Supplier<WallBlock> TOFUFENCE_HELL = register("tofufence_hell", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()).noOcclusion()));
	public static final Supplier<WallBlock> TOFUFENCE_SOUL = register("tofufence_soul", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()).noOcclusion()));
	public static final Supplier<WallBlock> TOFUFENCE_GRILLED = register("tofufence_grilled", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(GRILLEDTOFU.get()).noOcclusion()));
	public static final Supplier<WallBlock> TOFUFENCE_ZUNDA = register("tofufence_zunda", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion()));


	public static final Supplier<DoorBlock> TOFUDOOR_KINU = register("tofudoor_kinu", () -> new TofuDoorBlock(BlockBehaviour.Properties.of().strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));
	public static final Supplier<DoorBlock> TOFUDOOR_MOMEN = register("tofudoor_momen", () -> new TofuDoorBlock(BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));
	public static final Supplier<DoorBlock> TOFUDOOR_ISHI = register("tofudoor_ishi", () -> new TofuDoorBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion(), BlockSetType.OAK));
	public static final Supplier<DoorBlock> TOFUDOOR_METAL = register("tofudoor_metal", () -> new TofuDoorBlock(BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion(), BlockSetType.IRON));
	public static final Supplier<DoorBlock> TOFUDOOR_HELL = register("tofudoor_hell", () -> new TofuDoorBlock(BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()).noOcclusion(), BlockSetType.OAK));
	public static final Supplier<DoorBlock> TOFUDOOR_SOUL = register("tofudoor_soul", () -> new TofuDoorBlock(BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()).noOcclusion(), BlockSetType.OAK));
	public static final Supplier<DoorBlock> TOFUDOOR_GRILLED = register("tofudoor_grilled", () -> new TofuDoorBlock(BlockBehaviour.Properties.ofFullCopy(GRILLEDTOFU.get()).noOcclusion(), BlockSetType.OAK));
	public static final Supplier<DoorBlock> TOFUDOOR_ZUNDA = register("tofudoor_zunda", () -> new TofuDoorBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion(), BlockSetType.OAK));

	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_KINU = register("tofutrapdoor_kinu", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.of().strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_MOMEN = register("tofutrapdoor_momen", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_ISHI = register("tofutrapdoor_ishi", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_METAL = register("tofutrapdoor_metal", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.IRON));
	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_HELL = register("tofutrapdoor_hell", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_SOUL = register("tofutrapdoor_soul", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_GRILLED = register("tofutrapdoor_grilled", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(GRILLEDTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final Supplier<TrapDoorBlock> TOFUTRAPDOOR_ZUNDA = register("tofutrapdoor_zunda", () -> new TofuTrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));


	public static final Supplier<Block> TOFU_TERRAIN = register("tofu_terrain", () -> new TofuTerrainBlock(BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.SNOW)));
	public static final Supplier<Block> MABOU_TERRAIN = register("mabou_terrain", () -> new TofuMagmaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM)
			.lightLevel(p_152684_ -> 3)
			.strength(0.6F)
			.isValidSpawn((p_187421_, p_187422_, p_187423_, p_187424_) -> p_187424_.fireImmune())
			.hasPostProcess(TofuBlocks::always)
			.sound(SoundType.SNOW)));
	public static final Supplier<Block> TOFU_TERRAIN_ZUNDA = register("tofu_terrain_zunda", () -> new TofuTerrainBlock(BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).randomTicks().sound(SoundType.SNOW)));
	public static final Supplier<Block> SUSPICIOUS_TOFU_TERRAIN = register("suspicious_tofu_terrain", () -> new SuspiciousTofuTerrainBlock(BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.SNOW)));

	public static final Supplier<Block> TOFUSLATE = register("tofuslate", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.5F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final Supplier<Block> TOFUSLATE_TOFU_DIAMOND_ORE = register("tofuslate_tofu_diamond_ore", () -> new DropExperienceBlock(UniformInt.of(3, 5), BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final Supplier<Block> ORE_TOFU_DIAMOND = register("ore_tofu_diamond", () -> new DropExperienceBlock(UniformInt.of(3, 5), BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW)));
	public static final Supplier<Block> ORE_TOFUGEM = register("ore_tofugem", () -> new DropExperienceBlock(UniformInt.of(2, 3), BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW)));

	public static final Supplier<Block> TOFU_BEDROCK = register("tofu_bedrock", () -> new Block(BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).sound(SoundType.STONE).isValidSpawn((state, blockGetter, blockPos, entityType) -> false)));

	public static final Supplier<Block> SAPLING_TOFU = register("sapling_tofu", () -> new TofuSaplingBlock(TofuTreeGrowers.TOFU_TREE, BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Supplier<Block> LEAVES_TOFU = register("leaves_tofu", () -> new TofuLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).noOcclusion().randomTicks().isSuffocating((state, getter, pos) -> false).sound(SoundType.GRASS)));

	public static final Supplier<Block> TOFU_FLOWER = register("tofu_flower", () -> new TofuFlowerBlock(TofuEffects.SOY_HEALTHY, 20.0F, BlockBehaviour.Properties.of().instabreak().noOcclusion().noCollission().sound(SoundType.GRASS)));

	public static final Supplier<Block> LEEK = register("blockleek", () -> new LeekBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().noCollission().sound(SoundType.GRASS)));

	public static final Supplier<Block> SAPLING_APRICOT = register("sapling_apricot", () -> new ApricotSaplingBlock(TofuTreeGrowers.APRICOT_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
	public static final Supplier<Block> LEAVES_APRICOT = register("leaves_apricot", () -> new ApricotLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).noOcclusion().isSuffocating((state, getter, pos) -> false).randomTicks().sound(SoundType.GRASS)));

	public static final Supplier<RotatedPillarBlock> LEEK_GREEN_STEM = register("leek_green_stem", () -> new BurnableRotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM)));

	public static final Supplier<Block> LEEK_GREEN_PLANKS = register("leek_green_planks", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final Supplier<StairBlock> LEEK_GREEN_PLANKS_STAIR = register("leek_green_planks_stair", () -> new StairBlock(LEEK_GREEN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get())));
	public static final Supplier<SlabBlock> LEEK_GREEN_PLANKS_SLAB = register("leek_green_planks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get())));
	public static final Supplier<FenceBlock> LEEK_GREEN_FENCE = register("leek_green_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final Supplier<FenceGateBlock> LEEK_GREEN_FENCE_GATE = register("leek_green_fence_gate", () -> new FenceGateBlock(TofuWoodTypes.LEEK_GREEN, BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
	public static final Supplier<StandingSignBlock> LEEK_GREEN_SIGN = register("leek_green_sign", () -> new TofuStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN), TofuWoodTypes.LEEK_GREEN));
	public static final Supplier<WallSignBlock> LEEK_GREEN_WALL_SIGN = BLOCKS.register("leek_green_wall_sign", () -> new TofuWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN), TofuWoodTypes.LEEK_GREEN));
	public static final Supplier<CeilingHangingSignBlock> LEEK_GREEN_HANGING_SIGN = register("leek_green_hanging_sign", () -> new TofuCeilingHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN), TofuWoodTypes.LEEK_GREEN));
	public static final Supplier<WallHangingSignBlock> LEEK_GREEN_WALL_HANGING_SIGN = BLOCKS.register("leek_green_wall_hanging_sign", () -> new TofuWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN), TofuWoodTypes.LEEK_GREEN));
	public static final Supplier<DoorBlock> LEEK_GREEN_DOOR = register("leek_green_door", () -> new DoorBlock(TofuBlockSetTypes.LEEK_GREEN, BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get()).noOcclusion()));
	public static final Supplier<TrapDoorBlock> LEEK_GREEN_TRAPDOOR = register("leek_green_trapdoor", () -> new TrapDoorBlock(TofuBlockSetTypes.LEEK_GREEN, BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false)));



	public static final Supplier<RotatedPillarBlock> LEEK_STEM = register("leek_stem", () -> new BurnableRotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM)));

	public static final Supplier<Block> LEEK_PLANKS = register("leek_planks", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final Supplier<StairBlock> LEEK_PLANKS_STAIR = register("leek_planks_stair", () -> new StairBlock(LEEK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LEEK_PLANKS.get())));
	public static final Supplier<SlabBlock> LEEK_PLANKS_SLAB = register("leek_planks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LEEK_PLANKS.get())));
	public static final Supplier<FenceBlock> LEEK_FENCE = register("leek_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final Supplier<FenceGateBlock> LEEK_FENCE_GATE = register("leek_fence_gate", () -> new FenceGateBlock(TofuWoodTypes.LEEK, BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
	public static final Supplier<StandingSignBlock> LEEK_SIGN = register("leek_sign", () -> new TofuStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN), TofuWoodTypes.LEEK));
	public static final Supplier<WallSignBlock> LEEK_WALL_SIGN = BLOCKS.register("leek_wall_sign", () -> new TofuWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN), TofuWoodTypes.LEEK));
	public static final Supplier<CeilingHangingSignBlock> LEEK_HANGING_SIGN = register("leek_hanging_sign", () -> new TofuCeilingHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN), TofuWoodTypes.LEEK));
	public static final Supplier<WallHangingSignBlock> LEEK_WALL_HANGING_SIGN = BLOCKS.register("leek_wall_hanging_sign", () -> new TofuWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN), TofuWoodTypes.LEEK));


	public static final Supplier<Block> ZUNDATOFU_MUSHROOM = register("zundatofu_mushroom", () -> new TofuMushroomBlock(TofuTreeGrowers.ZUNDA_MUSHROOM, BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.FUNGUS)));


	public static final Supplier<RotatedPillarBlock> TOFU_STEM = register("tofustem", () -> new BurnableRotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final Supplier<Block> TOFU_STEM_PLANKS = register("tofustem_planks", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final Supplier<StairBlock> TOFU_STEM_PLANKS_STAIR = register("tofustem_planks_stair", () -> new StairBlock(TOFU_STEM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get())));
	public static final Supplier<SlabBlock> TOFU_STEM_PLANKS_SLAB = register("tofustem_planks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get())));
	public static final Supplier<FenceBlock> TOFU_STEM_FENCE = register("tofustem_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final Supplier<FenceGateBlock> TOFU_STEM_FENCE_GATE = register("tofustem_fence_gate", () -> new FenceGateBlock(TofuWoodTypes.TOFU_STEM, BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));


	public static final Supplier<StandingSignBlock> TOFU_STEM_SIGN = register("tofustem_sign", () -> new TofuStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN), TofuWoodTypes.TOFU_STEM));
	public static final Supplier<WallSignBlock> TOFU_STEM_WALL_SIGN = BLOCKS.register("tofustem_wall_sign", () -> new TofuWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN), TofuWoodTypes.TOFU_STEM));
	public static final Supplier<CeilingHangingSignBlock> TOFU_STEM_HANGING_SIGN = register("tofustem_hanging_sign", () -> new TofuCeilingHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN), TofuWoodTypes.TOFU_STEM));
	public static final Supplier<WallHangingSignBlock> TOFU_STEM_WALL_HANGING_SIGN = BLOCKS.register("tofustem_wall_hanging_sign", () -> new TofuWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN), TofuWoodTypes.TOFU_STEM));
	public static final Supplier<DoorBlock> TOFU_STEM_DOOR = register("tofustem_door", () -> new DoorBlock(TofuBlockSetTypes.TOFU_STEM, BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get()).noOcclusion()));
	public static final Supplier<TrapDoorBlock> TOFU_STEM_TRAPDOOR = register("tofustem_trapdoor", () -> new TrapDoorBlock(TofuBlockSetTypes.TOFU_STEM, BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false)));

	public static final Supplier<TofuPortalBlock> TOFU_PORTAL = BLOCKS.register("tofuportal", () -> new TofuPortalBlock(BlockBehaviour.Properties.of().strength(-1.0F).noCollission().noLootTable().sound(SoundType.GLASS).lightLevel((p_50872_) -> {
		return 11;
	})));
	public static final Supplier<Block> TOFU_FARMLAND = register("tofu_farmland", () -> new TofuFarmlandBlock(BlockBehaviour.Properties.of().strength(0.5F, 1.0F).noOcclusion().randomTicks().sound(SoundType.SNOW)));
	public static final Supplier<Block> SALTPAN = register("blocksaltpan", () -> new SaltPanBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final Supplier<Block> SALT_FURNACE = register("salt_furnace", () -> new SaltFurnaceBlock(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.STONE).lightLevel((p_50872_) -> {
		return p_50872_.getValue(SaltFurnaceBlock.LIT) ? 13 : 0;
	})));
	public static final Supplier<Block> SPROUTSJAR = register("blocksproutsjar", () -> new SproutsJarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().noCollission().sound(SoundType.GLASS)));
	public static final Supplier<Block> SALT_BLOCK = register("salt_block", () -> new FallFoodBlock(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.SAND)));

	public static final Supplier<Block> MORIJIO = register("morijio", () -> new MorijioBlock(BlockBehaviour.Properties.of().strength(0.5F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	//BARREL
	public static final Supplier<Block> BARREL_MISO = register("barrel_miso", () -> new MisoBarrelBlock(TofuItems.BOTTLE_SOYSAUSE, BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final Supplier<Block> BARREL_MISOTOFU = register("barrel_misotofu", () -> new WeightBaseBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final Supplier<Block> NATTOBED = register("nattobed", () -> new NoWeightBaseBlock(BlockBehaviour.Properties.of().strength(1.0F, 2.0F).randomTicks().sound(SoundType.GRASS)));
	public static final Supplier<Block> NETHER_NATTOBED = register("nether_nattobed", () -> new NoWeightBaseBlock(BlockBehaviour.Properties.of().strength(1.0F, 2.0F).randomTicks().sound(SoundType.GRASS)));


	public static final Supplier<Block> TOFUCAKE = register("tofucake", () -> new TofuCakeBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().sound(SoundType.WOOL), 1, 0.1F));
	public static final Supplier<Block> ZUNDATOFUCAKE = register("zundatofucake", () -> new TofuCakeBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().sound(SoundType.WOOL), 1, 0.2F));
	public static final Supplier<Block> SOYCHEESE_TART = register("soycheese_tart", () -> new TofuCakeBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().sound(SoundType.WOOL), 2, 0.3F));

	public static final Supplier<Block> TOFU_CANDLE_CAKE = noItemRegister("candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> WHITE_TOFU_CANDLE_CAKE = noItemRegister("white_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.WHITE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> ORANGE_TOFU_CANDLE_CAKE = noItemRegister("orange_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> MAGENTA_TOFU_CANDLE_CAKE = noItemRegister("magenta_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIGHT_BLUE_TOFU_CANDLE_CAKE = noItemRegister("light_blue_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> YELLOW_TOFU_CANDLE_CAKE = noItemRegister("yellow_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIME_TOFU_CANDLE_CAKE = noItemRegister("lime_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.LIME_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> PINK_TOFU_CANDLE_CAKE = noItemRegister("pink_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.PINK_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> GRAY_TOFU_CANDLE_CAKE = noItemRegister("gray_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.GRAY_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIGHT_GRAY_TOFU_CANDLE_CAKE = noItemRegister("light_gray_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> CYAN_TOFU_CANDLE_CAKE = noItemRegister("cyan_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.CYAN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> PURPLE_TOFU_CANDLE_CAKE = noItemRegister("purple_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BLUE_TOFU_CANDLE_CAKE = noItemRegister("blue_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.BLUE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BROWN_TOFU_CANDLE_CAKE = noItemRegister("brown_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.BROWN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> GREEN_TOFU_CANDLE_CAKE = noItemRegister("green_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.GREEN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> RED_TOFU_CANDLE_CAKE = noItemRegister("red_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.RED_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BLACK_TOFU_CANDLE_CAKE = noItemRegister("black_candle_tofu_cake", () -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.BLACK_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));

	public static final Supplier<Block> ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> WHITE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("white_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.WHITE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> ORANGE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("orange_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> MAGENTA_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("magenta_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("light_blue_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> YELLOW_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("yellow_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIME_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("lime_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.LIME_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> PINK_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("pink_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.PINK_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> GRAY_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("gray_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.GRAY_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("light_gray_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> CYAN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("cyan_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.CYAN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> PURPLE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("purple_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BLUE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("blue_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.BLUE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BROWN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("brown_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.BROWN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> GREEN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("green_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.GREEN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> RED_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("red_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.RED_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BLACK_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("black_candle_zundatofu_cake", () -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.BLACK_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));


	public static final Supplier<Block> SOYCHEESE_CANDLE_TART = noItemRegister("candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> WHITE_SOYCHEESE_CANDLE_TART = noItemRegister("white_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.WHITE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> ORANGE_SOYCHEESE_CANDLE_TART = noItemRegister("orange_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> MAGENTA_SOYCHEESE_CANDLE_TART = noItemRegister("magenta_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIGHT_BLUE_SOYCHEESE_CANDLE_TART = noItemRegister("light_blue_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> YELLOW_SOYCHEESE_CANDLE_TART = noItemRegister("yellow_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIME_SOYCHEESE_CANDLE_TART = noItemRegister("lime_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.LIME_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> PINK_SOYCHEESE_CANDLE_TART = noItemRegister("pink_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.PINK_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> GRAY_SOYCHEESE_CANDLE_TART = noItemRegister("gray_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.GRAY_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> LIGHT_GRAY_SOYCHEESE_CANDLE_TART = noItemRegister("light_gray_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> CYAN_SOYCHEESE_CANDLE_TART = noItemRegister("cyan_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.CYAN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> PURPLE_SOYCHEESE_CANDLE_TART = noItemRegister("purple_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BLUE_SOYCHEESE_CANDLE_TART = noItemRegister("blue_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.BLUE_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BROWN_SOYCHEESE_CANDLE_TART = noItemRegister("brown_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.BROWN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> GREEN_SOYCHEESE_CANDLE_TART = noItemRegister("green_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.GREEN_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> RED_SOYCHEESE_CANDLE_TART = noItemRegister("red_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.RED_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final Supplier<Block> BLACK_SOYCHEESE_CANDLE_TART = noItemRegister("black_candle_soycheese_candle_tart", () -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.BLACK_CANDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));

	public static final Supplier<ZundamaBlock> ZUNDAMA_BLOCK = register("zundama_block", () -> new ZundamaBlock(BlockBehaviour.Properties.of().strength(0.4F).noOcclusion().lightLevel((blockState) -> 9).sound(SoundType.HONEY_BLOCK)));

	public static final Supplier<SoymilkCauldronBlock> SOYMILK_CAULDRON = noItemRegister("soymilk_cauldron", () -> new SoymilkCauldronBlock(BlockBehaviour.Properties.of().strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK, TofuItems.SOY_CHEESE));
	public static final Supplier<SoymilkCauldronBlock> SOYMILK_NETHER_CAULDRON = noItemRegister("soymilk_nether_cauldron", () -> new SoymilkCauldronBlock(BlockBehaviour.Properties.of().strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK_NETHER, TofuItems.SOY_NETHER_CHEESE));
	public static final Supplier<SoymilkCauldronBlock> SOYMILK_SOUL_CAULDRON = noItemRegister("soymilk_soul_cauldron", () -> new SoymilkCauldronBlock(BlockBehaviour.Properties.of().strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK_SOUL, TofuItems.SOY_SOUL_CHEESE));

	public static final Supplier<Block> TOFUBED = register("tofubed", () -> new TofuBedBlock(BlockBehaviour.Properties.of().strength(0.2F).noOcclusion().sound(SoundType.SNOW)));
	public static final Supplier<Block> TOFUCHEST = register("tofuchest", () -> new TofuChestBlock(BlockBehaviour.Properties.of().strength(2.5F).noOcclusion().sound(SoundType.STONE), TofuBlockEntitys.TOFUCHEST::get));

	public static final Supplier<Block> FOODPLATE = register("foodplate", () -> new FoodPlateBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.METAL)));
	public static final Supplier<Block> TOFUNIAN_STATUE = register("tofunian_statue", () -> new TofunianStatueBlock(BlockBehaviour.Properties.of().strength(100F, 3600000.0F).requiresCorrectToolForDrops().pushReaction(PushReaction.BLOCK).sound(SoundType.LODESTONE)));

	public static final Supplier<Block> RICE_BLOCK = register("rice_block",
			() -> new RiceBlock(BlockBehaviour.Properties.of()
					.strength(2.0F)
					.sound(SoundType.GRASS)
					.noOcclusion())
	);
	public static final Supplier<Block> SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_block",
			() -> new BagBlock(BlockBehaviour.Properties.of()
					.strength(1.0F)
					.sound(SoundType.WOOL)
					.noOcclusion())
	);
	public static final Supplier<Block> NETHER_SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_nether_block",
			() -> new BagBlock(BlockBehaviour.Properties.of()
					.strength(1.0F)
					.sound(SoundType.WOOL)
					.noOcclusion())
	);

	public static final Supplier<Block> SOUL_SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_soul_block",
			() -> new BagBlock(BlockBehaviour.Properties.of()
					.strength(1.0F)
					.sound(SoundType.WOOL)
					.noOcclusion())
	);


	public static final Supplier<FlowerPotBlock> POTTED_LEEK = BLOCKS.register("potted_leek", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LEEK, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

	public static final Supplier<FlowerPotBlock> POTTED_TOFU_SAPLING = BLOCKS.register("potted_tofu_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SAPLING_TOFU, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final Supplier<FlowerPotBlock> POTTED_TOFU_FLOWER = BLOCKS.register("potted_tofu_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TOFU_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final Supplier<FlowerPotBlock> POTTED_ZUNDA_TOFU_MUSHROOM = BLOCKS.register("potted_zunda_tofu_mushroom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ZUNDATOFU_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final Supplier<FlowerPotBlock> POTTED_APRICOT_SAPLING = BLOCKS.register("potted_apricot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SAPLING_APRICOT, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

	public static final Supplier<Block> TOFU_DETECTOR = register("tofu_detector", () -> new TofuDetectorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().isRedstoneConductor(TofuBlocks::never)));
	public static final Supplier<Block> TF_STORAGE = register("tf_storage", () -> new TFStorageBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFStorageBlock.LIT) ? 13 : 0;
	})));
	public static final Supplier<Block> TF_CRAFTER = register("tf_crafter", () -> new TFCrafterBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFCrafterBlock.CRAFTING) ? 10 : 0;
	})));
	public static final Supplier<Block> TF_OVEN = register("tf_oven", () -> new TFOvenBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFOvenBlock.LIT) ? 13 : 0;
	})));
	public static final Supplier<Block> TF_COLLECTOR = register("tf_collector", () -> new TFCollectorBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFCollectorBlock.LIT) ? 13 : 0;
	})));

	public static final Supplier<Block> ANTENNA_BASIC = register("antenna_basic", () -> new TFAntennaBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().noCollission().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final Supplier<Block> TOFU_WORK_STATION = register("tofu_work_station", () -> new TofuWorkStationBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Supplier<Block> TOFU_VAULT = register("tofu_vault", () -> new TofuVaultBlock(BlockBehaviour.Properties.of().lightLevel(p_323402_ -> p_323402_.getValue(VaultBlock.STATE).lightLevel())
			.strength(50.0F).noOcclusion().isViewBlocking((state, blockGetter, blockPos) -> false).sound(SoundType.VAULT)));


	private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
		return true;
	}

	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}

	private static <T extends Block> Supplier<T> baseRegister(String name, Supplier<? extends T> block, Function<Supplier<T>, Supplier<? extends Item>> item) {
		Supplier<T> register = BLOCKS.register(name, block);
		Supplier<? extends Item> itemSupplier = item.apply(register);
		TofuItems.ITEMS.register(name, itemSupplier);
		return register;
	}

	private static <T extends Block> Supplier<T> noItemRegister(String name, Supplier<? extends T> block) {
		Supplier<T> register = BLOCKS.register(name, block);
		return register;
	}

	private static <B extends Block> Supplier<B> register(String name, Supplier<? extends Block> block) {
		return (Supplier<B>) baseRegister(name, block, (object) -> TofuBlocks.registerBlockItem(object));
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final Supplier<T> block) {
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
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_HELL.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_HELL.get(), WALLTOFUTORCH_HELL.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFUTORCH_SOUL.get()) {
				return new StandingAndWallBlockItem(TOFUTORCH_SOUL.get(), WALLTOFUTORCH_SOUL.get(), new Item.Properties(), Direction.DOWN);
			} else if (Objects.requireNonNull(block.get()) == TOFU_STEM_SIGN.get()) {
				return new SignItem(new Item.Properties().stacksTo(16), TOFU_STEM_SIGN.get(), TOFU_STEM_WALL_SIGN.get());
			} else if (Objects.requireNonNull(block.get()) == LEEK_GREEN_SIGN.get()) {
				return new SignItem(new Item.Properties().stacksTo(16), LEEK_GREEN_SIGN.get(), LEEK_GREEN_WALL_SIGN.get());
			} else if (Objects.requireNonNull(block.get()) == LEEK_SIGN.get()) {
				return new SignItem(new Item.Properties().stacksTo(16), LEEK_SIGN.get(), LEEK_WALL_SIGN.get());
			} else if (Objects.requireNonNull(block.get()) == TOFU_STEM_HANGING_SIGN.get()) {
				return new HangingSignItem(TOFU_STEM_HANGING_SIGN.get(), TOFU_STEM_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == LEEK_GREEN_HANGING_SIGN.get()) {
				return new HangingSignItem(LEEK_GREEN_HANGING_SIGN.get(), LEEK_GREEN_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) == LEEK_HANGING_SIGN.get()) {
				return new HangingSignItem(LEEK_HANGING_SIGN.get(), LEEK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16));
			} else if (Objects.requireNonNull(block.get()) instanceof TofuCakeBlock) {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().stacksTo(1));
			} else if (Objects.requireNonNull(block.get()) instanceof DoorBlock) {
				return new DoubleHighBlockItem(Objects.requireNonNull(block.get()), new Item.Properties().stacksTo(16));
			} else {
				return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
			}
		};
	}

	public static void flamableInit() {
		FireBlock fireBlock = (FireBlock) Blocks.FIRE;
		fireBlock.setFlammable(ZUNDATOFU_MUSHROOM.get(), 20, 20);
		fireBlock.setFlammable(LEEK.get(), 20, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_FENCE.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(LEEK_FENCE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_FENCE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_FENCE_GATE.get(), 5, 20);
	}
}
