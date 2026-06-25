package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.tfenergy.TFEnergyData;
import baguchi.tofucraft.block.BagBlock;
import baguchi.tofucraft.block.BurnableRotatedPillarBlock;
import baguchi.tofucraft.block.CandleTofuCakeBlock;
import baguchi.tofucraft.block.FallFoodBlock;
import baguchi.tofucraft.block.KinuTofuBlock;
import baguchi.tofucraft.block.LeekBlock;
import baguchi.tofucraft.block.MorijioBlock;
import baguchi.tofucraft.block.RiceBlock;
import baguchi.tofucraft.block.SuspiciousTofuTerrainBlock;
import baguchi.tofucraft.block.TallLeekBlock;
import baguchi.tofucraft.block.TofuBlock;
import baguchi.tofucraft.block.TofuCakeBlock;
import baguchi.tofucraft.block.TofuDetectorBlock;
import baguchi.tofucraft.block.TofuFarmlandBlock;
import baguchi.tofucraft.block.TofuFlowerBlock;
import baguchi.tofucraft.block.TofuGemBlock;
import baguchi.tofucraft.block.TofuGrateBlock;
import baguchi.tofucraft.block.TofuGrilledBlock;
import baguchi.tofucraft.block.TofuLeavesBlock;
import baguchi.tofucraft.block.TofuMagmaBlock;
import baguchi.tofucraft.block.TofuMushroomBlock;
import baguchi.tofucraft.block.TofuPortalBlock;
import baguchi.tofucraft.block.TofuPotBlock;
import baguchi.tofucraft.block.TofuSaplingBlock;
import baguchi.tofucraft.block.TofuTerrainBlock;
import baguchi.tofucraft.block.TofuTrapDoorBlock;
import baguchi.tofucraft.block.TofunianStatueBlock;
import baguchi.tofucraft.block.WildSproutsBlock;
import baguchi.tofucraft.block.YubaBlock;
import baguchi.tofucraft.block.ZundamaBlock;
import baguchi.tofucraft.block.crop.ChiliCropsBlock;
import baguchi.tofucraft.block.crop.LeekCropsBlock;
import baguchi.tofucraft.block.crop.RiceCropsBlock;
import baguchi.tofucraft.block.crop.RiceRootBlock;
import baguchi.tofucraft.block.crop.SoybeanCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanPaleCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchi.tofucraft.block.crop.SproutsCropBlock;
import baguchi.tofucraft.block.tfenergy.TFAntennaAdvanceBlock;
import baguchi.tofucraft.block.tfenergy.TFAntennaBlock;
import baguchi.tofucraft.block.tfenergy.TFCollectorBlock;
import baguchi.tofucraft.block.tfenergy.TFCraftingTableBlock;
import baguchi.tofucraft.block.tfenergy.TFOvenBlock;
import baguchi.tofucraft.block.tfenergy.TFStorageBlock;
import baguchi.tofucraft.block.tfenergy.TFTofuMakerBlock;
import baguchi.tofucraft.block.tfenergy.TofuWorkStationBlock;
import baguchi.tofucraft.block.tree.ApricotLeavesBlock;
import baguchi.tofucraft.block.tree.ApricotSaplingBlock;
import baguchi.tofucraft.block.utils.ChikuwaBlock;
import baguchi.tofucraft.block.utils.FoodPlateBlock;
import baguchi.tofucraft.block.utils.MisoBarrelBlock;
import baguchi.tofucraft.block.utils.NoWeightBaseBlock;
import baguchi.tofucraft.block.utils.SaltFurnaceBlock;
import baguchi.tofucraft.block.utils.SaltPanBlock;
import baguchi.tofucraft.block.utils.SoymilkCauldronBlock;
import baguchi.tofucraft.block.utils.SproutsJarBlock;
import baguchi.tofucraft.block.utils.TofuBedBlock;
import baguchi.tofucraft.block.utils.TofuChestBlock;
import baguchi.tofucraft.block.utils.TofuDoorBlock;
import baguchi.tofucraft.block.utils.WeightBaseBlock;
import baguchi.tofucraft.item.block.EdiableBlockItem;
import baguchi.tofucraft.world.gen.grower.TofuTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
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
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class TofuBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TofuCraftReload.MODID);

	public static final DeferredBlock<Block> SOYMILK = registerWithoutItem("soymilk", (properties) -> new LiquidBlock(TofuFluids.SOYMILK.value(), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).replaceable().noCollision().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
	public static final DeferredBlock<Block> SOYMILK_HELL = registerWithoutItem("soymilk_hell", (properties) -> new LiquidBlock(TofuFluids.SOYMILK_HELL.value(), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).replaceable().noCollision().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
	public static final DeferredBlock<Block> SOYMILK_SOUL = registerWithoutItem("soymilk_soul", (properties) -> new LiquidBlock(TofuFluids.SOYMILK_SOUL.value(), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollision().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
	public static final DeferredBlock<Block> BITTERN = registerWithoutItem("bittern", (properties) -> new LiquidBlock(TofuFluids.BITTERN.value(), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).replaceable().noCollision().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
	public static final DeferredBlock<Block> DOUBANJIANG = registerWithoutItem("doubanjiang", (properties) -> new LiquidBlock(TofuFluids.DOUBANJIANG.value(), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).replaceable().noCollision().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().lightLevel((blockstate) -> 10).sound(SoundType.EMPTY));

	public static final DeferredBlock<Block> YUBA = registerWithoutItem("yuba", (properties) -> new YubaBlock(properties), () -> BlockBehaviour.Properties.of().noOcclusion().randomTicks().strength(0.25F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOL));


	public static final DeferredBlock<Block> SOYBEAN = registerWithoutItem("soybean", (properties) -> new SoybeanCropsBlock(properties), () -> BlockBehaviour.Properties.of().noCollision().randomTicks().instabreak().sound(SoundType.CROP));
	public static final DeferredBlock<Block> SOYBEAN_NETHER = registerWithoutItem("soybean_nether", (properties) -> new SoybeanNetherCropsBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));
	public static final DeferredBlock<Block> SOYBEAN_SOUL = registerWithoutItem("soybean_soul", (properties) -> new SoybeanSoulCropsBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));
	public static final DeferredBlock<Block> SOYBEAN_PALE = registerWithoutItem("soybean_pale", (properties) -> new SoybeanPaleCropsBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));
	public static final DeferredBlock<Block> LEEK_CROP = registerWithoutItem("leek_crop", (properties) -> new LeekCropsBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));
	public static final DeferredBlock<Block> RICE_CROP = registerWithoutItem("rice", (properties) -> new RiceCropsBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));
	public static final DeferredBlock<Block> RICE_ROOT = registerWithoutItem("rice_root", (properties) -> new RiceRootBlock(properties), () -> BlockBehaviour.Properties.of().noCollision().randomTicks().strength(0.1F).sound(SoundType.CROP));
	public static final DeferredBlock<Block> CHILI_CROP = registerWithoutItem("chili_crop", (properties) -> new ChiliCropsBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));
	public static final DeferredBlock<Block> SPROUTS = registerWithoutItem("sprouts_crop", (properties) -> new SproutsCropBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));
	public static final DeferredBlock<Block> WILD_SPROUTS = register("wild_sprouts", (properties) -> new WildSproutsBlock(properties), () -> BlockBehaviour.Properties.ofFullCopy(SOYBEAN.value()));

	public static final DeferredBlock<Block> KINUTOFU = register("block_tofu_kinu", (properties) -> new KinuTofuBlock(properties), () -> BlockBehaviour.Properties.of().randomTicks().strength(0.1F, 0.2F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> WAXED_KINUTOFU = register("waxed_tofu_kinu", Block::new, () -> BlockBehaviour.Properties.of().strength(0.1F, 0.2F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> MOMENTOFU = register("block_tofu_momen", (properties) -> new TofuBlock(properties), () -> BlockBehaviour.Properties.of().randomTicks().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> WAXED_MOMENTOFU = register("waxed_tofu_momen", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> ISHITOFU = register("block_tofu_ishi", (properties) -> new TofuBlock(properties), () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> WAXED_ISHITOFU = register("waxed_tofu_ishi", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> ISHITOFU_BRICK = register("tofuishi_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> ISHITOFU_SMOOTH_BRICK = register("tofuishi_smooth_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> ISHITOFU_CHISELED_BRICK = register("tofuishi_chiseled_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> METALTOFU = register("block_tofu_metal", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.IRON));
	public static final DeferredBlock<Block> METAL_TOFU_GRATE = register("tofu_metal_grate", (properties) -> new TofuGrateBlock(properties), () -> BlockBehaviour.Properties.of().strength(3.0F, 6.0F)
			.sound(SoundType.COPPER_GRATE)
			.mapColor(MapColor.COLOR_LIGHT_GRAY)
			.noOcclusion()
			.requiresCorrectToolForDrops()
			.isValidSpawn(Blocks::never)
			.isRedstoneConductor(TofuBlocks::never)
			.isSuffocating(TofuBlocks::never)
			.isViewBlocking(TofuBlocks::never));
	public static final DeferredBlock<Block> METAL_TOFU_LUMP = register("tofu_metal_lump", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).lightLevel(state -> {
		return 15;
	}).sound(SoundType.IRON));
	public static final DeferredBlock<Block> METAL_TOFU_BARS = register("tofu_metal_bars", (properties) -> new IronBarsBlock(properties), () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.IRON).noOcclusion());
	public static final DeferredBlock<Block> DIAMONDTOFU = register("block_tofu_diamond", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
	public static final DeferredBlock<Block> TOFU_GEM_BLOCK = register("tofu_gem_block", (properties) -> new TofuGemBlock(properties), () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.METAL));
	public static final DeferredBlock<Block> ADVANCE_TOFU_GEM_BLOCK = register("adv_tofu_gem_block", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.METAL));
	public static final DeferredBlock<Block> ZUNDA_ALLOY_TOFU_BLOCK = register("zunda_alloy_tofu_block", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.NETHERITE_BLOCK));

	public static final DeferredBlock<RotatedPillarBlock> GRILLED_TOFU = register("block_tofu_grilled", (properties) -> new TofuGrilledBlock(properties), () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> ZUNDATOFU = register("block_tofu_zunda", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> ZUNDATOFU_BRICK = register("tofuzunda_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> ZUNDATOFU_SMOOTH_BRICK = register("tofuzunda_smooth_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));

	public static final DeferredBlock<Block> MISOTOFU = register("block_tofu_miso", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> DRIEDTOFU = register("block_tofu_dried", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> DRIEDTOFU_BRICK = register("tofudried_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> DRIEDTOFU_SMOOTH_BRICK = register("tofudried_smooth_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> DRIEDTOFU_CHISELED_BRICK = register("tofudried_chiseled_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));

	public static final DeferredBlock<Block> EGGTOFU = register("block_tofu_egg", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> EGGTOFU_BRICK = register("tofuegg_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> EGGTOFU_SMOOTH_BRICK = register("tofuegg_smooth_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> EGGTOFU_CHISELED_BRICK = register("tofuegg_chiseled_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));

	public static final DeferredBlock<Block> SESAMETOFU = register("block_tofu_sesame", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));

	public static final DeferredBlock<Block> HELLTOFU = register("block_tofu_hell", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> HELLTOFU_BRICK = register("tofuhell_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> HELLTOFU_SMOOTH_BRICK = register("tofuhell_smooth_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> HELLTOFU_CHISELED_BRICK = register("tofuhell_chiseled_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));

	public static final DeferredBlock<Block> SOULTOFU = register("block_tofu_soul", Block::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> SOULTOFU_BRICK = register("tofusoul_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> SOULTOFU_SMOOTH_BRICK = register("tofusoul_smooth_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> SOULTOFU_CHISELED_BRICK = register("tofusoul_chiseled_brick", Block::new, () -> BlockBehaviour.Properties.of().randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> MINCEDTOFU = register("block_tofu_minced", (properties) -> new FallFoodBlock(properties), () -> BlockBehaviour.Properties.of().strength(0.2F, 0.3F).sound(SoundType.SNOW));

	public static final DeferredBlock<StairBlock> TOFU_STAIR_KINU = register("tofu_stair_kinu", (properties) -> new StairBlock(KINUTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(KINUTOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_MOMEN = register("tofu_stair_momen", (properties) -> new StairBlock(MOMENTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(MOMENTOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_ISHI = register("tofu_stair_ishi", (properties) -> new StairBlock(ISHITOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(ISHITOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_METAL = register("tofu_stair_metal", (properties) -> new StairBlock(METALTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_GRILLED = register("tofu_stair_grilled", (properties) -> new StairBlock(GRILLED_TOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(GRILLED_TOFU.get()));

	public static final DeferredBlock<StairBlock> TOFU_STAIR_ZUNDA = register("tofu_stair_zunda", (properties) -> new StairBlock(ZUNDATOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_ZUNDABRICK = register("tofu_stair_zundabrick", (properties) -> new StairBlock(ZUNDATOFU_BRICK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU_BRICK.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_HELL = register("tofu_stair_hell", (properties) -> new StairBlock(HELLTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_SOUL = register("tofu_stair_soul", (properties) -> new StairBlock(SOULTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()));

	public static final DeferredBlock<StairBlock> TOFU_STAIR_ISHIBRICK = register("tofu_stair_ishibrick", (properties) -> new StairBlock(ISHITOFU_BRICK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(ISHITOFU_BRICK.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_HELLBRICK = register("tofu_stair_hellbrick", (properties) -> new StairBlock(HELLTOFU_BRICK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(HELLTOFU_BRICK.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_SOULBRICK = register("tofu_stair_soulbrick", (properties) -> new StairBlock(SOULTOFU_BRICK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SOULTOFU_BRICK.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_MISO = register("tofu_stair_miso", (properties) -> new StairBlock(MISOTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(MISOTOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_DRIED = register("tofu_stair_dried", (properties) -> new StairBlock(DRIEDTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(DRIEDTOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_DRIEDBRICK = register("tofu_stair_driedbrick", (properties) -> new StairBlock(DRIEDTOFU_BRICK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(DRIEDTOFU_BRICK.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_EGG = register("tofu_stair_egg", (properties) -> new StairBlock(EGGTOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(EGGTOFU.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_EGGBRICK = register("tofu_stair_eggbrick", (properties) -> new StairBlock(EGGTOFU_BRICK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(EGGTOFU_BRICK.get()));
	public static final DeferredBlock<StairBlock> TOFU_STAIR_SESAME = register("tofu_stair_sesame", (properties) -> new StairBlock(SESAMETOFU.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SESAMETOFU.get()));

	public static final DeferredBlock<SlabBlock> TOFU_SLAB_KINU = register("tofu_slab_kinu", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(KINUTOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_MOMEN = register("tofu_slab_momen", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(MOMENTOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_ISHI = register("tofu_slab_ishi", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ISHITOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_METAL = register("tofu_slab_metal", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_GRILLED = register("tofu_slab_grilled", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GRILLED_TOFU.get()));

	public static final DeferredBlock<SlabBlock> TOFU_SLAB_ZUNDA = register("tofu_slab_zunda", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_ZUNDABRICK = register("tofu_slab_zundabrick", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU_BRICK.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_HELL = register("tofu_slab_hell", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_SOUL = register("tofu_slab_soul", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()));

	public static final DeferredBlock<SlabBlock> TOFU_SLAB_ISHIBRICK = register("tofu_slab_ishibrick", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ISHITOFU_BRICK.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_HELLBRICK = register("tofu_slab_hellbrick", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(HELLTOFU_BRICK.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_SOULBRICK = register("tofu_slab_soulbrick", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SOULTOFU_BRICK.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_MISO = register("tofu_slab_miso", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(MISOTOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_DRIED = register("tofu_slab_dried", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DRIEDTOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_DRIEDBRICK = register("tofu_slab_driedbrick", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DRIEDTOFU_BRICK.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_EGG = register("tofu_slab_egg", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(EGGTOFU.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_EGGBRICK = register("tofu_slab_eggbrick", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(EGGTOFU_BRICK.get()));
	public static final DeferredBlock<SlabBlock> TOFU_SLAB_SESAME = register("tofu_slab_sesame", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SESAMETOFU.get()));


	public static final DeferredBlock<Block> TOFU_TORCH_KINU = register("tofu_torch_kinu", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> TOFU_TORCH_MOMEN = register("tofu_torch_momen", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> TOFU_TORCH_ISHI = register("tofu_torch_ishi", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 6.0F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> TOFU_TORCH_METAL = register("tofu_torch_metal", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 7.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.METAL));
	public static final DeferredBlock<Block> TOFU_TORCH_GRILLED = register("tofu_torch_grilled", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> TOFU_TORCH_ZUNDA = register("tofu_torch_zunda", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> TOFU_TORCH_HELL = register("tofu_torch_hell", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> TOFU_TORCH_SOUL = register("tofu_torch_soul", (properties) -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 10)
			.noOcclusion().sound(SoundType.SNOW));

	public static final DeferredBlock<Block> WALL_TOFU_TORCH_KINU = registerWithoutItem("wall_tofu_torch_kinu", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> WALL_TOFU_TORCH_MOMEN = registerWithoutItem("wall_tofu_torch_momen", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> WALL_TOFU_TORCH_ISHI = registerWithoutItem("wall_tofu_torch_ishi", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 6.0F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.CALCITE));
	public static final DeferredBlock<Block> WALL_TOFU_TORCH_METAL = registerWithoutItem("wall_tofu_torch_metal", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 7.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.METAL));
	public static final DeferredBlock<Block> WALL_TOFU_TORCH_GRILLED = registerWithoutItem("wall_tofu_torch_grilled", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> WALL_TOFU_TORCH_ZUNDA = registerWithoutItem("wall_tofu_torch_zunda", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> WALL_TOFU_TORCH_HELL = registerWithoutItem("wall_tofu_torch_hell", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> WALL_TOFU_TORCH_SOUL = registerWithoutItem("wall_tofu_torch_soul", (properties) -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties), () -> BlockBehaviour.Properties.of().strength(0.0F, 0.5F).noCollision().lightLevel(state -> 14).noCollision().sound(SoundType.SNOW));

	public static final DeferredBlock<Block> TOFU_METAL_CHAIN = register("tofu_metal_chain", ChainBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN)
			.sound(SoundType.CHAIN));
	public static final DeferredBlock<Block> TOFU_METAL_LANTERN = register("tofu_metal_lantern", LanternBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> 15)
			.sound(SoundType.LANTERN));
	public static final DeferredBlock<Block> TOFU_METAL_SOUL_LANTERN = register("tofu_metal_soul_lantern", LanternBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN).lightLevel(state -> 10)
			.sound(SoundType.LANTERN));

	public static final DeferredBlock<Block> TOFU_LADDER_KINU = register("tofu_ladder_kinu", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(KINUTOFU.get()).noOcclusion());
	public static final DeferredBlock<Block> TOFU_LADDER_MOMEN = register("tofu_ladder_momen", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(MOMENTOFU.get()).noOcclusion());
	public static final DeferredBlock<Block> TOFU_LADDER_ISHI = register("tofu_ladder_ishi", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ISHITOFU.get()).noOcclusion());
	public static final DeferredBlock<Block> TOFU_LADDER_METAL = register("tofu_ladder_metal", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion());

	public static final DeferredBlock<Block> TOFU_LADDER_GRILLED = register("tofu_ladder_grilled", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GRILLED_TOFU.get()).noOcclusion());
	public static final DeferredBlock<Block> TOFU_LADDER_ZUNDA = register("tofu_ladder_zunda", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion());
	public static final DeferredBlock<Block> TOFU_LADDER_HELL = register("tofu_ladder_hell", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion());
	public static final DeferredBlock<Block> TOFU_LADDER_SOUL = register("tofu_ladder_soul", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion());

	public static final DeferredBlock<Block> TOFU_LADDER_ISHIBRICK = register("tofu_ladder_ishibrick", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ISHITOFU_BRICK.get()).noOcclusion());

	public static final DeferredBlock<WallBlock> TOFUFENCE_KINU = register("tofu_fence_kinu", WallBlock::new, () -> BlockBehaviour.Properties.of().strength(0.1F, 0.2F).forceSolidOn().sound(SoundType.SNOW).noOcclusion());
	public static final DeferredBlock<WallBlock> TOFUFENCE_MOMEN = register("tofu_fence_momen", WallBlock::new, () -> BlockBehaviour.Properties.of().strength(0.35F, 0.5F).forceSolidOn().sound(SoundType.SNOW).noOcclusion());
	public static final DeferredBlock<WallBlock> TOFUFENCE_ISHI = register("tofu_fence_ishi", WallBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).forceSolidOn().sound(SoundType.CALCITE).noOcclusion());
	public static final DeferredBlock<WallBlock> TOFUFENCE_METAL = register("tofu_fence_metal", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion().forceSolidOn());
	public static final DeferredBlock<WallBlock> TOFUFENCE_HELL = register("tofu_fence_hell", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()).noOcclusion().forceSolidOn());
	public static final DeferredBlock<WallBlock> TOFUFENCE_SOUL = register("tofu_fence_soul", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()).noOcclusion().forceSolidOn());
	public static final DeferredBlock<WallBlock> TOFUFENCE_GRILLED = register("tofu_fence_grilled", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GRILLED_TOFU.get()).noOcclusion().forceSolidOn());
	public static final DeferredBlock<WallBlock> TOFUFENCE_ZUNDA = register("tofu_fence_zunda", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion().forceSolidOn());


	public static final DeferredBlock<DoorBlock> TOFUDOOR_KINU = register("tofu_door_kinu", (properties) -> new TofuDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.of().strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion());
	public static final DeferredBlock<DoorBlock> TOFUDOOR_MOMEN = register("tofu_door_momen", (properties) -> new TofuDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion());
	public static final DeferredBlock<DoorBlock> TOFUDOOR_ISHI = register("tofu_door_ishi", (properties) -> new TofuDoorBlock(properties, TofuBlockSetTypes.TOFU_ISHI), () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE).noOcclusion());
	public static final DeferredBlock<DoorBlock> TOFUDOOR_METAL = register("tofu_door_metal", (properties) -> new TofuDoorBlock(properties, BlockSetType.IRON), () -> BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion());
	public static final DeferredBlock<DoorBlock> TOFUDOOR_HELL = register("tofu_door_hell", (properties) -> new TofuDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()).noOcclusion());
	public static final DeferredBlock<DoorBlock> TOFUDOOR_SOUL = register("tofu_door_soul", (properties) -> new TofuDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()).noOcclusion());
	public static final DeferredBlock<DoorBlock> TOFUDOOR_GRILLED = register("tofu_door_grilled", (properties) -> new TofuDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(GRILLED_TOFU.get()).noOcclusion());
	public static final DeferredBlock<DoorBlock> TOFUDOOR_ZUNDA = register("tofu_door_zunda", (properties) -> new TofuDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion());

	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_KINU = register("tofu_trapdoor_kinu", (properties) -> new TofuTrapDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.of().strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_MOMEN = register("tofu_trapdoor_momen", (properties) -> new TofuTrapDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_ISHI = register("tofu_trapdoor_ishi", (properties) -> new TofuTrapDoorBlock(properties, TofuBlockSetTypes.TOFU_ISHI), () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CALCITE).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_METAL = register("tofu_trapdoor_metal", (properties) -> new TofuTrapDoorBlock(properties, BlockSetType.IRON), () -> BlockBehaviour.Properties.ofFullCopy(METALTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_HELL = register("tofu_trapdoor_hell", (properties) -> new TofuTrapDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(HELLTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_SOUL = register("tofu_trapdoor_soul", (properties) -> new TofuTrapDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(SOULTOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_GRILLED = register("tofu_trapdoor_grilled", (properties) -> new TofuTrapDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(GRILLED_TOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_ZUNDA = register("tofu_trapdoor_zunda", (properties) -> new TofuTrapDoorBlock(properties, BlockSetType.OAK), () -> BlockBehaviour.Properties.ofFullCopy(ZUNDATOFU.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));

	public static final DeferredBlock<Block> TOFU_TERRAIN = register("tofu_terrain", TofuTerrainBlock::new, () -> BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> MABOU_TERRAIN = register("mabou_terrain", (properties) -> new TofuMagmaBlock(properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM)
			.lightLevel(p_152684_ -> 3)
			.strength(0.6F)
			.isValidSpawn((p_187421_, p_187422_, p_187423_, p_187424_) -> p_187424_.fireImmune())
			.postProcess(TofuBlocks::postProcessSelf)
			.sound(SoundType.SNOW));
	public static final DeferredBlock<Block> TOFU_TERRAIN_ZUNDA = register("tofu_terrain_zunda", TofuTerrainBlock::new, () -> BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).randomTicks().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> SUSPICIOUS_TOFU_TERRAIN = register("suspicious_tofu_terrain", (properties) -> new SuspiciousTofuTerrainBlock(properties), () -> BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.SNOW));

	public static final DeferredBlock<Block> TOFUSLATE = register("tofuslate", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.5F, 4.0F).sound(SoundType.DEEPSLATE));
	public static final DeferredBlock<Block> TOFUSLATE_TOFU_DIAMOND_ORE = register("tofuslate_tofu_diamond_ore", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties), () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F, 4.0F).sound(SoundType.DEEPSLATE));
	public static final DeferredBlock<Block> TOFUSLATE_SOY_FORCE_ORE = register("tofuslate_soy_force_ore", (properties) -> new DropExperienceBlock(UniformInt.of(4, 6), properties), () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F, 4.0F).sound(SoundType.DEEPSLATE));

	public static final DeferredBlock<Block> ORE_TOFU_DIAMOND = register("ore_tofu_diamond", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties), () -> BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> ORE_TOFUGEM = register("ore_tofugem", (properties) -> new DropExperienceBlock(UniformInt.of(2, 3), properties), () -> BlockBehaviour.Properties.of().strength(0.5F, 1.0F).sound(SoundType.SNOW));

	public static final DeferredBlock<Block> TOFU_BEDROCK = register("tofu_bedrock", Block::new, () -> BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).sound(SoundType.STONE).isValidSpawn((state, blockGetter, blockPos, entityType) -> false));

	public static final DeferredBlock<Block> SAPLING_TOFU = register("sapling_tofu", (properties) -> new TofuSaplingBlock(TofuTreeGrowers.TOFU_TREE, properties), () -> BlockBehaviour.Properties.of().noCollision().randomTicks().instabreak().sound(SoundType.GRASS));
	public static final DeferredBlock<Block> LEAVES_TOFU = register("leaves_tofu", TofuLeavesBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).noOcclusion().randomTicks().isSuffocating((state, getter, pos) -> false).sound(SoundType.GRASS));

	public static final DeferredBlock<Block> TOFU_FLOWER = register("tofu_flower", (properties) -> new TofuFlowerBlock(TofuEffects.SOY_HEALTHY, 20.0F, properties), () -> BlockBehaviour.Properties.of().instabreak().noOcclusion().noCollision().sound(SoundType.GRASS));

	public static final DeferredBlock<Block> LEEK = register("blockleek", (properties) -> new LeekBlock(properties), () -> BlockBehaviour.Properties.of().instabreak().noOcclusion().noCollision().sound(SoundType.GRASS));
	public static final DeferredBlock<Block> TALL_LEEK = register("tall_leek", (properties) -> new TallLeekBlock(properties), () -> BlockBehaviour.Properties.of().instabreak().noOcclusion().noCollision().sound(SoundType.GRASS));

	public static final DeferredBlock<Block> SAPLING_APRICOT = register("sapling_apricot", (properties) -> new ApricotSaplingBlock(TofuTreeGrowers.APRICOT_TREE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
	public static final DeferredBlock<Block> LEAVES_APRICOT = register("leaves_apricot", (properties) -> new ApricotLeavesBlock(properties), () -> BlockBehaviour.Properties.of().strength(0.2F).noOcclusion().isSuffocating((state, getter, pos) -> false).randomTicks().sound(SoundType.GRASS));

	public static final DeferredBlock<RotatedPillarBlock> SPROUT_STEM = register("sprout_stem", BurnableRotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final DeferredBlock<RotatedPillarBlock> YELLOW_SPROUT_STEM = register("yellow_sprout_stem", BurnableRotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM));

	public static final DeferredBlock<Block> SPROUT_PLANKS = register("sprout_planks", Block::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<StairBlock> SPROUT_PLANKS_STAIR = register("sprout_stair", (properties) -> new StairBlock(SPROUT_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SPROUT_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> SPROUT_PLANKS_SLAB = register("sprout_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SPROUT_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> SPROUT_FENCE = register("sprout_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<FenceGateBlock> SPROUT_FENCE_GATE = register("sprout_fence_gate", (properties) -> new FenceGateBlock(TofuWoodTypes.SPROUT, properties), () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F));
	public static final DeferredBlock<StandingSignBlock> SPROUT_SIGN = register("sprout_sign", (properties) -> new StandingSignBlock(TofuWoodTypes.SPROUT, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN));
	public static final DeferredBlock<WallSignBlock> SPROUT_WALL_SIGN = registerWithoutItem("sprout_wall_sign", (properties) -> new WallSignBlock(TofuWoodTypes.SPROUT, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> SPROUT_HANGING_SIGN = register("sprout_hanging_sign", (properties) -> new CeilingHangingSignBlock(TofuWoodTypes.SPROUT, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> SPROUT_WALL_HANGING_SIGN = registerWithoutItem("sprout_wall_hanging_sign", (properties) -> new WallHangingSignBlock(TofuWoodTypes.SPROUT, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN));
	public static final DeferredBlock<DoorBlock> SPROUT_DOOR = register("sprout_door", (properties) -> new DoorBlock(TofuBlockSetTypes.SPROUT, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPROUT_PLANKS.get()).noOcclusion());
	public static final DeferredBlock<TrapDoorBlock> SPROUT_TRAPDOOR = register("sprout_trapdoor", (properties) -> new TrapDoorBlock(TofuBlockSetTypes.SPROUT, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPROUT_PLANKS.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<ButtonBlock> SPROUT_BUTTON = register("sprout_button", (properties) -> new ButtonBlock(TofuBlockSetTypes.SPROUT, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> SPROUT_PRESSURE_PLATE = register("sprout_pressure_plate", (properties) -> new PressurePlateBlock(TofuBlockSetTypes.SPROUT, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE));
	public static final DeferredBlock<ShelfBlock> SPROUT_SHELF = register(
			"sprout_shelf", ShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SHELF));


	public static final DeferredBlock<RotatedPillarBlock> LEEK_GREEN_STEM = register("leek_green_stem", (properties) -> new BurnableRotatedPillarBlock(properties), () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM));

	public static final DeferredBlock<Block> LEEK_GREEN_PLANKS = register("leek_green_planks", Block::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<StairBlock> LEEK_GREEN_PLANKS_STAIR = register("leek_green_stair", (properties) -> new StairBlock(LEEK_GREEN_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> LEEK_GREEN_PLANKS_SLAB = register("leek_green_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> LEEK_GREEN_FENCE = register("leek_green_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<FenceGateBlock> LEEK_GREEN_FENCE_GATE = register("leek_green_fence_gate", (properties) -> new FenceGateBlock(TofuWoodTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F));
	public static final DeferredBlock<StandingSignBlock> LEEK_GREEN_SIGN = register("leek_green_sign", (properties) -> new StandingSignBlock(TofuWoodTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN));
	public static final DeferredBlock<WallSignBlock> LEEK_GREEN_WALL_SIGN = registerWithoutItem("leek_green_wall_sign", (properties) -> new WallSignBlock(TofuWoodTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> LEEK_GREEN_HANGING_SIGN = register("leek_green_hanging_sign", (properties) -> new CeilingHangingSignBlock(TofuWoodTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> LEEK_GREEN_WALL_HANGING_SIGN = registerWithoutItem("leek_green_wall_hanging_sign", (properties) -> new WallHangingSignBlock(TofuWoodTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN));
	public static final DeferredBlock<DoorBlock> LEEK_GREEN_DOOR = register("leek_green_door", (properties) -> new DoorBlock(TofuBlockSetTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get()).noOcclusion());
	public static final DeferredBlock<TrapDoorBlock> LEEK_GREEN_TRAPDOOR = register("leek_green_trapdoor", (properties) -> new TrapDoorBlock(TofuBlockSetTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(LEEK_GREEN_PLANKS.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<ButtonBlock> LEEK_GREEN_BUTTON = register("leek_green_button", (properties) -> new ButtonBlock(TofuBlockSetTypes.LEEK_GREEN, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> LEEK_GREEN_PRESSURE_PLATE = register("leek_green_pressure_plate", (properties) -> new PressurePlateBlock(TofuBlockSetTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE));
	public static final DeferredBlock<ShelfBlock> LEEK_GREEN_SHELF = register(
			"leek_green_shelf", ShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SHELF));

	public static final DeferredBlock<RotatedPillarBlock> LEEK_STEM = register("leek_stem", BurnableRotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM));

	public static final DeferredBlock<Block> LEEK_PLANKS = register("leek_planks", Block::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<StairBlock> LEEK_PLANKS_STAIR = register("leek_stair", (properties) -> new StairBlock(LEEK_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(LEEK_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> LEEK_PLANKS_SLAB = register("leek_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LEEK_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> LEEK_FENCE = register("leek_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<FenceGateBlock> LEEK_FENCE_GATE = register("leek_fence_gate", (properties) -> new FenceGateBlock(TofuWoodTypes.LEEK, properties), () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F));
	public static final DeferredBlock<StandingSignBlock> LEEK_SIGN = register("leek_sign", (properties) -> new StandingSignBlock(TofuWoodTypes.LEEK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN));
	public static final DeferredBlock<WallSignBlock> LEEK_WALL_SIGN = registerWithoutItem("leek_wall_sign", (properties) -> new WallSignBlock(TofuWoodTypes.LEEK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> LEEK_HANGING_SIGN = register("leek_hanging_sign", (properties) -> new CeilingHangingSignBlock(TofuWoodTypes.LEEK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> LEEK_WALL_HANGING_SIGN = registerWithoutItem("leek_wall_hanging_sign", (properties) -> new WallHangingSignBlock(TofuWoodTypes.LEEK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN));
	public static final DeferredBlock<DoorBlock> LEEK_DOOR = register("leek_door", (properties) -> new DoorBlock(TofuBlockSetTypes.LEEK, properties), () -> BlockBehaviour.Properties.ofFullCopy(LEEK_PLANKS.get()).noOcclusion());
	public static final DeferredBlock<TrapDoorBlock> LEEK_TRAPDOOR = register("leek_trapdoor", (properties) -> new TrapDoorBlock(TofuBlockSetTypes.LEEK, properties), () -> BlockBehaviour.Properties.ofFullCopy(LEEK_PLANKS.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<ButtonBlock> LEEK_BUTTON = register("leek_button", (properties) -> new ButtonBlock(TofuBlockSetTypes.LEEK, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> LEEK_PRESSURE_PLATE = register("leek_pressure_plate", (properties) -> new PressurePlateBlock(TofuBlockSetTypes.LEEK_GREEN, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE));
	public static final DeferredBlock<ShelfBlock> LEEK_SHELF = register(
			"leek_shelf", ShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SHELF));


	public static final DeferredBlock<Block> ZUNDA_TOFU_MUSHROOM = register("zunda_tofu_mushroom", (properties) -> new TofuMushroomBlock(TofuTreeGrowers.ZUNDA_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().instabreak().noCollision().sound(SoundType.FUNGUS));
	public static final DeferredBlock<Block> ZUNDA_MUSHROOM_BLOCK = register("zunda_mushroom_block", Block::new, () -> BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.FUNGUS));

	public static final DeferredBlock<RotatedPillarBlock> ISHI_TOFU_STEM = register("ishi_tofu_stem", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.BASALT));

	public static final DeferredBlock<RotatedPillarBlock> TOFU_STEM = register("tofu_stem", BurnableRotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final DeferredBlock<Block> TOFU_STEM_PLANKS = register("tofu_stem_planks", Block::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<StairBlock> TOFU_STEM_PLANKS_STAIR = register("tofu_stem_stair", (properties) -> new StairBlock(TOFU_STEM_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> TOFU_STEM_PLANKS_SLAB = register("tofu_stem_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> TOFU_STEM_FENCE = register("tofu_stem_fence", FenceBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<FenceGateBlock> TOFU_STEM_FENCE_GATE = register("tofu_stem_fence_gate", (properties) -> new FenceGateBlock(TofuWoodTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F));


	public static final DeferredBlock<StandingSignBlock> TOFU_STEM_SIGN = register("tofu_stem_sign", (properties) -> new StandingSignBlock(TofuWoodTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SIGN));
	public static final DeferredBlock<WallSignBlock> TOFU_STEM_WALL_SIGN = registerWithoutItem("tofu_stem_wall_sign", (properties) -> new WallSignBlock(TofuWoodTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> TOFU_STEM_HANGING_SIGN = register("tofu_stem_hanging_sign", (properties) -> new CeilingHangingSignBlock(TofuWoodTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> TOFU_STEM_WALL_HANGING_SIGN = registerWithoutItem("tofu_stem_wall_hanging_sign", (properties) -> new WallHangingSignBlock(TofuWoodTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN));
	public static final DeferredBlock<DoorBlock> TOFU_STEM_DOOR = register("tofu_stem_door", (properties) -> new DoorBlock(TofuBlockSetTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get()).noOcclusion());
	public static final DeferredBlock<TrapDoorBlock> TOFU_STEM_TRAPDOOR = register("tofu_stem_trapdoor", (properties) -> new TrapDoorBlock(TofuBlockSetTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.ofFullCopy(TOFU_STEM_PLANKS.get()).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false));
	public static final DeferredBlock<ButtonBlock> TOFU_STEM_BUTTON = register("tofu_stem_button", (properties) -> new ButtonBlock(TofuBlockSetTypes.TOFU_STEM, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> TOFU_STEM_PRESSURE_PLATE = register("tofu_stem_pressure_plate", (properties) -> new PressurePlateBlock(TofuBlockSetTypes.TOFU_STEM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PRESSURE_PLATE));
	public static final DeferredBlock<ShelfBlock> TOFU_STEM_SHELF = register(
			"tofu_stem_shelf", ShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SHELF));

	public static final DeferredBlock<TofuPortalBlock> TOFU_PORTAL = registerWithoutItem("tofuportal", TofuPortalBlock::new, () -> BlockBehaviour.Properties.of().strength(-1.0F).noCollision().noLootTable().sound(SoundType.GLASS).lightLevel((p_50872_) -> {
		return 11;
	}));
	public static final DeferredBlock<Block> TOFU_FARMLAND = register("tofu_farmland", TofuFarmlandBlock::new, () -> BlockBehaviour.Properties.of().strength(0.5F, 1.0F).noOcclusion().randomTicks().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> SALTPAN = register("blocksaltpan", SaltPanBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().noOcclusion().sound(SoundType.WOOD));
	public static final DeferredBlock<Block> SALT_FURNACE = register("salt_furnace", (properties) -> new SaltFurnaceBlock(properties), () -> BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.STONE).lightLevel((p_50872_) -> {
		return p_50872_.getValue(SaltFurnaceBlock.LIT) ? 13 : 0;
	}));
	public static final DeferredBlock<Block> SPROUTSJAR = register("blocksproutsjar", (properties) -> new SproutsJarBlock(properties), () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().noOcclusion().isValidSpawn(Blocks::never)
			.isRedstoneConductor(TofuBlocks::never)
			.isSuffocating(TofuBlocks::never)
			.isViewBlocking(TofuBlocks::never).sound(SoundType.GLASS));
	public static final DeferredBlock<Block> SALT_BLOCK = register("salt_block", (properties) -> new FallFoodBlock(properties), () -> BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> OKARA_BLOCK = register("okara_block", (properties) -> new FallFoodBlock(properties), () -> BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL));
	public static final DeferredBlock<Block> GIANT_OKARA_DONUT = register("giant_okara_donut", Block::new, () -> BlockBehaviour.Properties.of().strength(0.75F).sound(SoundType.NETHER_SPROUTS));

	public static final DeferredBlock<Block> MORIJIO = register("morijio", (properties) -> new MorijioBlock(properties), () -> BlockBehaviour.Properties.of().strength(0.5F, 3.0F).noOcclusion().sound(SoundType.WOOD));
	//BARREL
	public static final DeferredBlock<Block> BARREL_MISO = register("barrel_miso", (properties) -> new MisoBarrelBlock(TofuItems.BOTTLE_SOYSAUSE, properties), () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD));
	public static final DeferredBlock<Block> BARREL_MISOTOFU = register("barrel_misotofu", WeightBaseBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD));
	public static final DeferredBlock<Block> BARREL_ADV_TOFUGEM = register("barrel_adv_tofugem", WeightBaseBlock::new, () -> BlockBehaviour.Properties.of().strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD));
	public static final DeferredBlock<Block> NATTOBED = register("nattobed", NoWeightBaseBlock::new, () -> BlockBehaviour.Properties.of().strength(1.0F, 2.0F).randomTicks().sound(SoundType.GRASS));
	public static final DeferredBlock<Block> NETHER_NATTOBED = register("nether_nattobed", NoWeightBaseBlock::new, () -> BlockBehaviour.Properties.of().strength(1.0F, 2.0F).randomTicks().sound(SoundType.GRASS));

	public static final DeferredBlock<Block> TOFU_CHIKUWA_BLOCK = register("tofu_chikuwa_block", ChikuwaBlock::new, () -> BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).isValidSpawn(Blocks::never).noOcclusion().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> CHIKUWA_BLOCK = register("chikuwa_block", ChikuwaBlock::new, () -> BlockBehaviour.Properties.of().strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).isValidSpawn(Blocks::never).noOcclusion().sound(SoundType.SNOW));


	public static final DeferredBlock<Block> TOFU_CAKE = register("tofu_cake", (properties) -> new TofuCakeBlock(properties, 1, 0.1F), () -> BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().sound(SoundType.WOOL));
	public static final DeferredBlock<Block> ZUNDA_TOFU_CAKE = register("zunda_tofucake", (properties) -> new TofuCakeBlock(properties, 1, 0.2F), () -> BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().sound(SoundType.WOOL));
	public static final DeferredBlock<Block> SOYCHEESE_TART = register("soycheese_tart", (properties) -> new TofuCakeBlock(properties, 2, 0.3F), () -> BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().sound(SoundType.WOOL));

	public static final DeferredBlock<Block> SOY_CHEESE_BLOCK = register("soy_cheese_block", Block::new, () -> BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.HONEY_BLOCK));
	public static final DeferredBlock<Block> SOY_NETHER_CHEESE_BLOCK = register("soy_nether_cheese_block", Block::new, () -> BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.HONEY_BLOCK));
	public static final DeferredBlock<Block> SOY_SOUL_CHEESE_BLOCK = register("soy_soul_cheese_block", Block::new, () -> BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.HONEY_BLOCK));


	public static final DeferredBlock<Block> TOFU_CANDLE_CAKE = registerWithoutItem("candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.CANDLE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> WHITE_TOFU_CANDLE_CAKE = registerWithoutItem("white_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.white(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> ORANGE_TOFU_CANDLE_CAKE = registerWithoutItem("orange_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.orange(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> MAGENTA_TOFU_CANDLE_CAKE = registerWithoutItem("magenta_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.magenta(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_TOFU_CANDLE_CAKE = registerWithoutItem("light_blue_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.lightBlue(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> YELLOW_TOFU_CANDLE_CAKE = registerWithoutItem("yellow_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.yellow(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIME_TOFU_CANDLE_CAKE = registerWithoutItem("lime_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.lime(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PINK_TOFU_CANDLE_CAKE = registerWithoutItem("pink_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.pink(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GRAY_TOFU_CANDLE_CAKE = registerWithoutItem("gray_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.gray(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_TOFU_CANDLE_CAKE = registerWithoutItem("light_gray_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.lightGray(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> CYAN_TOFU_CANDLE_CAKE = registerWithoutItem("cyan_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.cyan(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PURPLE_TOFU_CANDLE_CAKE = registerWithoutItem("purple_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.purple(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLUE_TOFU_CANDLE_CAKE = registerWithoutItem("blue_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.blue(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BROWN_TOFU_CANDLE_CAKE = registerWithoutItem("brown_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.brown(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GREEN_TOFU_CANDLE_CAKE = registerWithoutItem("green_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.green(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> RED_TOFU_CANDLE_CAKE = registerWithoutItem("red_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.red(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLACK_TOFU_CANDLE_CAKE = registerWithoutItem("black_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFU_CAKE.get(), Blocks.DYED_CANDLE.black(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));

	public static final DeferredBlock<Block> ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.CANDLE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> WHITE_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("white_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.white(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> ORANGE_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("orange_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.orange(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> MAGENTA_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("magenta_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.magenta(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("light_blue_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.lightBlue(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> YELLOW_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("yellow_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.yellow(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIME_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("lime_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.lime(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PINK_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("pink_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.pink(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GRAY_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("gray_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.gray(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("light_gray_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.lightGray(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> CYAN_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("cyan_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.cyan(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PURPLE_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("purple_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.purple(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLUE_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("blue_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.blue(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BROWN_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("brown_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.brown(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GREEN_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("green_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.green(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> RED_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("red_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.red(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLACK_ZUNDA_TOFU_CANDLE_CAKE = registerWithoutItem("black_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDA_TOFU_CAKE.get(), Blocks.DYED_CANDLE.black(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));


	public static final DeferredBlock<Block> SOYCHEESE_CANDLE_TART = registerWithoutItem("candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.CANDLE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> WHITE_SOYCHEESE_CANDLE_TART = registerWithoutItem("white_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.white(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> ORANGE_SOYCHEESE_CANDLE_TART = registerWithoutItem("orange_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.orange(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> MAGENTA_SOYCHEESE_CANDLE_TART = registerWithoutItem("magenta_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.magenta(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_SOYCHEESE_CANDLE_TART = registerWithoutItem("light_blue_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.lightBlue(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> YELLOW_SOYCHEESE_CANDLE_TART = registerWithoutItem("yellow_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.yellow(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIME_SOYCHEESE_CANDLE_TART = registerWithoutItem("lime_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.lime(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PINK_SOYCHEESE_CANDLE_TART = registerWithoutItem("pink_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.pink(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GRAY_SOYCHEESE_CANDLE_TART = registerWithoutItem("gray_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.gray(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_SOYCHEESE_CANDLE_TART = registerWithoutItem("light_gray_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.lightGray(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> CYAN_SOYCHEESE_CANDLE_TART = registerWithoutItem("cyan_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.cyan(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PURPLE_SOYCHEESE_CANDLE_TART = registerWithoutItem("purple_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.purple(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLUE_SOYCHEESE_CANDLE_TART = registerWithoutItem("blue_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.blue(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BROWN_SOYCHEESE_CANDLE_TART = registerWithoutItem("brown_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.brown(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GREEN_SOYCHEESE_CANDLE_TART = registerWithoutItem("green_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.green(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> RED_SOYCHEESE_CANDLE_TART = registerWithoutItem("red_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.red(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLACK_SOYCHEESE_CANDLE_TART = registerWithoutItem("black_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.DYED_CANDLE.black(), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));

	public static final DeferredBlock<ZundamaBlock> ZUNDAMA_BLOCK = register("zundama_block", ZundamaBlock::new, () -> BlockBehaviour.Properties.of().strength(0.4F).noOcclusion().lightLevel((blockState) -> 9).sound(SoundType.HONEY_BLOCK));

	public static final DeferredBlock<SoymilkCauldronBlock> SOYMILK_CAULDRON = registerWithoutItem("soymilk_cauldron", (properties) -> new SoymilkCauldronBlock(properties, TofuItems.SOYMILK_BUCKET, () -> TofuBlocks.SOY_CHEESE_BLOCK.asItem()), () -> BlockBehaviour.Properties.of().strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL));
	public static final DeferredBlock<SoymilkCauldronBlock> SOYMILK_NETHER_CAULDRON = registerWithoutItem("soymilk_nether_cauldron", (properties) -> new SoymilkCauldronBlock(properties, TofuItems.SOYMILK_NETHER_BUCKET, () -> TofuBlocks.SOY_NETHER_CHEESE_BLOCK.asItem()), () -> BlockBehaviour.Properties.of().strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL));
	public static final DeferredBlock<SoymilkCauldronBlock> SOYMILK_SOUL_CAULDRON = registerWithoutItem("soymilk_soul_cauldron", (properties) -> new SoymilkCauldronBlock(properties, TofuItems.SOYMILK_SOUL_BUCKET, () -> TofuBlocks.SOY_SOUL_CHEESE_BLOCK.asItem()), () -> BlockBehaviour.Properties.of().strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL));

	public static final DeferredBlock<Block> TOFUBED = register("tofubed", TofuBedBlock::new, () -> BlockBehaviour.Properties.of().strength(0.2F).noOcclusion().sound(SoundType.NETHER_WOOD));
	public static final DeferredBlock<Block> TOFUCHEST = register("tofuchest", (properties) -> new TofuChestBlock(properties, TofuBlockEntitys.TOFUCHEST::get), () -> BlockBehaviour.Properties.of().strength(2.5F, 6.0F).noOcclusion().sound(SoundType.CALCITE));

	public static final DeferredBlock<Block> FOODPLATE = register("foodplate", FoodPlateBlock::new, () -> BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.METAL)
			.pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<Block> TOFUNIAN_STATUE = register("tofunian_statue", TofunianStatueBlock::new, () -> BlockBehaviour.Properties.of().strength(100F, 3600000.0F).requiresCorrectToolForDrops().pushReaction(PushReaction.BLOCK).sound(SoundType.LODESTONE));

	public static final DeferredBlock<Block> RICE_BLOCK = register("rice_block",
			RiceBlock::new, () -> BlockBehaviour.Properties.of()
					.strength(2.0F)
					.sound(SoundType.GRASS)
					.noOcclusion());
	public static final DeferredBlock<Block> SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_block",
			BagBlock::new, () -> BlockBehaviour.Properties.of()
					.strength(1.0F)
					.sound(SoundType.WOOL)
					.noOcclusion());
	public static final DeferredBlock<Block> NETHER_SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_nether_block",
			BagBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SOYBEANS_SEEDS_BLOCK.get())
	);

	public static final DeferredBlock<Block> SOUL_SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_soul_block",
			BagBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SOYBEANS_SEEDS_BLOCK.get())
	);

	public static final DeferredBlock<FlowerPotBlock> POTTED_LEEK = registerWithoutItem("potted_leek", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LEEK, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));

	public static final DeferredBlock<FlowerPotBlock> POTTED_TOFU_SAPLING = registerWithoutItem("potted_tofu_sapling", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SAPLING_TOFU, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_TOFU_FLOWER = registerWithoutItem("potted_tofu_flower", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TOFU_FLOWER, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_ZUNDA_TOFU_MUSHROOM = registerWithoutItem("potted_zunda_tofu_mushroom", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ZUNDA_TOFU_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_APRICOT_SAPLING = registerWithoutItem("potted_apricot_sapling", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SAPLING_APRICOT, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));

	public static final DeferredBlock<Block> TOFU_DETECTOR = register("tofu_detector", TofuDetectorBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().isRedstoneConductor(TofuBlocks::never));
	public static final DeferredBlock<Block> TF_STORAGE = register("tf_storage", TFStorageBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFStorageBlock.LIT) ? 13 : 0;
	}));
	public static final DeferredBlock<Block> TF_OVEN = register("tf_oven", TFOvenBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFOvenBlock.LIT) ? 13 : 0;
	}));
	public static final DeferredBlock<Block> TF_TOFU_MAKER = register("tf_tofu_maker", TFTofuMakerBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFTofuMakerBlock.LIT) ? 13 : 0;
	}));
	public static final DeferredBlock<Block> TF_COLLECTOR = register("tf_collector", TFCollectorBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFCollectorBlock.LIT) ? 13 : 0;
	}));

	public static final DeferredBlock<Block> ANTENNA_BASIC = register("antenna_basic", TFAntennaBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().noCollision().strength(5.0F, 6.0F).sound(SoundType.METAL));
	public static final DeferredBlock<Block> ANTENNA_ADVANCE = register("antenna_advance", TFAntennaAdvanceBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().noCollision().strength(5.0F, 6.0F).sound(SoundType.METAL));
	public static final DeferredBlock<Block> TOFU_WORK_STATION = register("tofu_work_station", TofuWorkStationBlock::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().strength(1.5F, 6.0F).sound(SoundType.STONE));

	public static final DeferredBlock<Block> TOFU_POT = register("tofu_pot",
			TofuPotBlock::new, () -> Block.Properties.of().mapColor(MapColor.METAL).strength(0.6F, 6.0F).sound(SoundType.LANTERN));
	public static final DeferredBlock<Block> TF_CRAFTING_TABLE = register("tf_crafting_table",
			TFCraftingTableBlock::new, () -> Block.Properties.of().mapColor(MapColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL).lightLevel((p_50872_) -> {
				return p_50872_.getValue(TFCraftingTableBlock.LIT) ? 13 : 0;
			}));


	private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
		return true;
	}

	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}

	private static <T extends Block> DeferredBlock<Block> registerWithoutItem(String name, Supplier<Block.Properties> properties) {
		return registerWithoutItem(name, Block::new, properties);
	}

	private static <T extends Block> DeferredBlock<T> registerWithoutItem(String name, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
		return registerWithoutItem(name, createKey(name), builder, properties);
	}

	private static <T extends Block> DeferredBlock<T> registerWithoutItem(String name, ResourceKey<Block> key, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
		return BLOCKS.register(name, () -> builder.apply(properties.get().setId(key)));
	}

	private static <T extends Block> DeferredBlock<Block> register(String name, Supplier<Block.Properties> properties) {
		return register(name, Block::new, properties);
	}

	private static <T extends Block> DeferredBlock<T> register(String name, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
		return register(name, createKey(name), builder, properties);
	}

	private static <T extends Block> DeferredBlock<T> register(String name, ResourceKey<Block> key, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
		return baseRegister(name, key, builder, properties, (deferredBlock) -> registerBlockItem(deferredBlock, name));
	}

	private static BlockPos postProcessSelf(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
		return blockPos;
	}

	private static ResourceKey<Block> createKey(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
	}

	private static <T extends Block> DeferredBlock<T> baseRegister(String name, ResourceKey<Block> key, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
		DeferredBlock<T> registered = BLOCKS.register(name, () -> builder.apply(properties.get().setId(key)));
		TofuItems.ITEMS.register(name, item.apply(registered));
		return registered;
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> deferredBlock, String name) {
		return () -> {
			DeferredBlock<T> block = Objects.requireNonNull(deferredBlock);
			Item.Properties properties = new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name))).useBlockDescriptionPrefix();

			if (block.get() == TOFU_CAKE.get() || block.get() == ZUNDA_TOFU_CAKE.get() || block.get() == SOYCHEESE_TART.get()) {
				return new BlockItem(block.get(), properties.stacksTo(1));
			} else if (block.get() == GRILLED_TOFU.get()) {
				return new EdiableBlockItem(GRILLED_TOFU.get(), properties.food(TofuFoods.TOFU_GRILLED_BLOCK));
			} else if (block.get() == TOFU_TORCH_KINU.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_KINU.get(), WALL_TOFU_TORCH_KINU.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_TORCH_MOMEN.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_MOMEN.get(), WALL_TOFU_TORCH_MOMEN.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_TORCH_ISHI.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_ISHI.get(), WALL_TOFU_TORCH_ISHI.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_TORCH_METAL.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_METAL.get(), WALL_TOFU_TORCH_METAL.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_TORCH_GRILLED.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_GRILLED.get(), WALL_TOFU_TORCH_GRILLED.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_TORCH_ZUNDA.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_ZUNDA.get(), WALL_TOFU_TORCH_ZUNDA.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_TORCH_HELL.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_HELL.get(), WALL_TOFU_TORCH_HELL.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_TORCH_SOUL.get()) {
				return new StandingAndWallBlockItem(TOFU_TORCH_SOUL.get(), WALL_TOFU_TORCH_SOUL.get(), Direction.DOWN, properties);
			} else if (block.get() == TOFU_STEM_SIGN.get()) {
				return new SignItem(TOFU_STEM_SIGN.get(), TOFU_STEM_WALL_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == LEEK_GREEN_SIGN.get()) {
				return new SignItem(LEEK_GREEN_SIGN.get(), LEEK_GREEN_WALL_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == LEEK_SIGN.get()) {
				return new SignItem(LEEK_SIGN.get(), LEEK_WALL_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == SPROUT_SIGN.get()) {
				return new HangingSignItem(SPROUT_SIGN.get(), SPROUT_WALL_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == TOFU_STEM_HANGING_SIGN.get()) {
				return new HangingSignItem(TOFU_STEM_HANGING_SIGN.get(), TOFU_STEM_WALL_HANGING_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == LEEK_GREEN_HANGING_SIGN.get()) {
				return new HangingSignItem(LEEK_GREEN_HANGING_SIGN.get(), LEEK_GREEN_WALL_HANGING_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == LEEK_HANGING_SIGN.get()) {
				return new HangingSignItem(LEEK_HANGING_SIGN.get(), LEEK_WALL_HANGING_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == SPROUT_HANGING_SIGN.get()) {
				return new HangingSignItem(SPROUT_HANGING_SIGN.get(), SPROUT_WALL_HANGING_SIGN.get(), properties.stacksTo(16));
			} else if (block.get() == TF_STORAGE.get() || block.get() == TF_OVEN.get()) {
				return new BlockItem(block.get(), properties.component(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(0, 5000)));
			} else if (block.get() == TF_COLLECTOR.get()) {
				return new BlockItem(block.get(), properties.component(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(0, 10000)));
			} else if (Objects.requireNonNull(block.get()) == TF_CRAFTING_TABLE.get()) {
				return new BlockItem(Objects.requireNonNull(block.get()), properties.component(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(0, 10000)));
			} else if (block.get() == GIANT_OKARA_DONUT.get()) {
				return new EdiableBlockItem(GIANT_OKARA_DONUT.get(), properties.food(TofuFoods.GIANT_OKARA_DONUT));
			} else if (block.get() == FOODPLATE.get()) {
				return new BlockItem(FOODPLATE.get(), properties.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
			} else {
				return new BlockItem(block.get(), properties);
			}
		};
	}

	public static void flamableInit() {
		FireBlock fireBlock = (FireBlock) Blocks.FIRE;
		fireBlock.setFlammable(ZUNDA_TOFU_MUSHROOM.get(), 20, 20);
		fireBlock.setFlammable(LEEK.get(), 20, 20);


		fireBlock.setFlammable(SPROUT_STEM.get(), 5, 20);
		fireBlock.setFlammable(YELLOW_SPROUT_STEM.get(), 5, 20);

		fireBlock.setFlammable(TOFU_STEM.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_FENCE.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_SHELF.get(), 30, 20);

		fireBlock.setFlammable(LEEK_STEM.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(LEEK_FENCE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_SHELF.get(), 30, 20);

		fireBlock.setFlammable(LEEK_GREEN_STEM.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_FENCE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_SHELF.get(), 30, 20);
	}
}
