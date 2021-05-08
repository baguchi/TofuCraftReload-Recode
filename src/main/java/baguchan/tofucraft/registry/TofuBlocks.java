package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.*;
import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.block.utils.SaltFurnaceBlock;
import baguchan.tofucraft.block.utils.SaltPanBlock;
import baguchan.tofucraft.block.utils.TofuBedBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.state.Property;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuBlocks {
	public static final Block SOYMILK = new TofuFluidBlock(TofuFluids.SOYMILK, AbstractBlock.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
	public static final Block SOYMILK_HELL = new TofuFluidBlock(TofuFluids.SOYMILK_HELL, AbstractBlock.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
	public static final Block SOYMILK_SOUL = new TofuFluidBlock(TofuFluids.SOYMILK_SOUL, AbstractBlock.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());

	public static final Block BITTERN = new TofuFluidBlock(TofuFluids.BITTERN, AbstractBlock.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
	public static final Block SOYBEAN = new SoybeanCropsBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.0F).sound(SoundType.CROP));
	public static final Block SOYBEAN_NETHER = new SoybeanNetherCropsBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.0F).sound(SoundType.CROP));
	public static final Block SOYBEAN_SOUL = new SoybeanSoulCropsBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.0F).sound(SoundType.CROP));

	public static final Block KINUTOFU = new KinuTofuBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).randomTicks().harvestTool(ToolType.SHOVEL).strength(0.1F, 0.2F).sound(SoundType.SNOW));
	public static final Block MOMENTOFU = new TofuBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).randomTicks().harvestTool(ToolType.SHOVEL).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block ISHITOFU = new TofuBlock(AbstractBlock.Properties.of(Material.STONE).randomTicks().harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE));
	public static final Block METALTOFU = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(1).strength(4.0F, 7.5F).sound(SoundType.METAL));
	public static final Block DIAMONDTOFU = new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(2).strength(5.0F, 8.0F).sound(SoundType.METAL));
	public static final Block HELLTOFU = new Block(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block SOULTOFU = new Block(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block GRILLEDTOFU = new Block(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.35F, 0.5F).sound(SoundType.SNOW));
	public static final Block ZUNDATOFU = new Block(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.35F, 0.5F).sound(SoundType.SNOW));

	public static final Block ISHITOFU_BRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block ISHITOFU_SMOOTH_BRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block ISHITOFU_CHISELED_BRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block ZUNDATOFU_BRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block ZUNDATOFU_SMOOTHBRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block HELLTOFU_BRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block HELLTOFU_SMOOTHBRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block SOULTOFU_BRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));
	public static final Block SOULTOFU_SMOOTHBRICK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.6F, 6.5F).sound(SoundType.STONE));

	public static final Block TOFUSTAIR_KINU = new StairsBlock(KINUTOFU::defaultBlockState, AbstractBlock.Properties.copy(KINUTOFU));
	public static final Block TOFUSTAIR_MOMEN = new StairsBlock(MOMENTOFU::defaultBlockState, AbstractBlock.Properties.copy(MOMENTOFU));
	public static final Block TOFUSTAIR_ISHI = new StairsBlock(ISHITOFU::defaultBlockState, AbstractBlock.Properties.copy(ISHITOFU));
	public static final Block TOFUSTAIR_METAL = new StairsBlock(METALTOFU::defaultBlockState, AbstractBlock.Properties.copy(METALTOFU));
	public static final Block TOFUSTAIR_ZUNDA = new StairsBlock(ZUNDATOFU::defaultBlockState, AbstractBlock.Properties.copy(ZUNDATOFU));
	public static final Block TOFUSTAIR_ZUNDABRICK = new StairsBlock(ZUNDATOFU_BRICK::defaultBlockState, AbstractBlock.Properties.copy(ZUNDATOFU_BRICK));
	public static final Block TOFUSTAIR_ISHIBRICK = new StairsBlock(ISHITOFU_BRICK::defaultBlockState, AbstractBlock.Properties.copy(ISHITOFU_BRICK));
	public static final Block TOFUSTAIR_HELLBRICK = new StairsBlock(HELLTOFU_BRICK::defaultBlockState, AbstractBlock.Properties.copy(HELLTOFU_BRICK));
	public static final Block TOFUSTAIR_SOULBRICK = new StairsBlock(SOULTOFU_BRICK::defaultBlockState, AbstractBlock.Properties.copy(SOULTOFU_BRICK));

	public static final Block TOFUSLAB_KINU = new SlabBlock(AbstractBlock.Properties.copy(KINUTOFU));
	public static final Block TOFUSLAB_MOMEN = new SlabBlock(AbstractBlock.Properties.copy(MOMENTOFU));
	public static final Block TOFUSLAB_ISHI = new SlabBlock(AbstractBlock.Properties.copy(ISHITOFU));
	public static final Block TOFUSLAB_METAL = new SlabBlock(AbstractBlock.Properties.copy(METALTOFU));
	public static final Block TOFUSLAB_ZUNDA = new SlabBlock(AbstractBlock.Properties.copy(ZUNDATOFU));
	public static final Block TOFUSLAB_ZUNDABRICK = new SlabBlock(AbstractBlock.Properties.copy(ZUNDATOFU_BRICK));
	public static final Block TOFUSLAB_ISHIBRICK = new SlabBlock(AbstractBlock.Properties.copy(ISHITOFU_BRICK));
	public static final Block TOFUSLAB_HELLBRICK = new SlabBlock(AbstractBlock.Properties.copy(HELLTOFU_BRICK));
	public static final Block TOFUSLAB_SOULBRICK = new SlabBlock(AbstractBlock.Properties.copy(SOULTOFU_BRICK));

	public static final Block TOFUTORCH_KINU = new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME);

	public static final Block TOFUTORCH_MOMEN = new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW), ParticleTypes.FLAME);

	public static final Block TOFUTORCH_ISHI = new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.STONE), ParticleTypes.FLAME);

	public static final Block TOFUTORCH_METAL = new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.METAL), ParticleTypes.FLAME);

	public static final Block WALLTOFUTORCH_KINU = new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW).dropsLike(TOFUTORCH_KINU), ParticleTypes.FLAME);

	public static final Block WALLTOFUTORCH_MOMEN = new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 0.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.SNOW).dropsLike(TOFUTORCH_MOMEN), ParticleTypes.FLAME);

	public static final Block WALLTOFUTORCH_ISHI = new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 6.0F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.STONE).dropsLike(TOFUTORCH_ISHI), ParticleTypes.FLAME);

	public static final Block WALLTOFUTORCH_METAL = new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F, 7.5F).lightLevel(state -> 14)
			.noCollission().noOcclusion().sound(SoundType.METAL).dropsLike(TOFUTORCH_METAL), ParticleTypes.FLAME);

	public static final Block TOFULADDER_KINU = new LadderBlock(AbstractBlock.Properties.copy(KINUTOFU).noOcclusion());
	public static final Block TOFULADDER_MOMEN = new LadderBlock(AbstractBlock.Properties.copy(MOMENTOFU).noOcclusion());
	public static final Block TOFULADDER_ISHI = new LadderBlock(AbstractBlock.Properties.copy(ISHITOFU).noOcclusion());
	public static final Block TOFULADDER_METAL = new LadderBlock(AbstractBlock.Properties.copy(METALTOFU).noOcclusion());
	public static final Block TOFULADDER_ISHIBRICK = new LadderBlock(AbstractBlock.Properties.copy(ISHITOFU_BRICK).noOcclusion());

	public static final Block TOFUFENCE_KINU = new WallBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.1F, 0.2F).sound(SoundType.SNOW).noOcclusion());
	public static final Block TOFUFENCE_MOMEN = new WallBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion());
	public static final Block TOFUFENCE_ISHI = new WallBlock(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion());
	public static final Block TOFUFENCE_METAL = new WallBlock(AbstractBlock.Properties.copy(METALTOFU).noOcclusion());

	public static final Block TOFUDOOR_KINU = new DoorBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.1F, 0.2F).sound(SoundType.SNOW).noOcclusion());
	public static final Block TOFUDOOR_MOMEN = new DoorBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion());
	public static final Block TOFUDOOR_ISHI = new DoorBlock(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion());
	public static final Block TOFUDOOR_METAL = new DoorBlock(AbstractBlock.Properties.copy(METALTOFU).noOcclusion());

	public static final Block TOFU_TERRAIN = new TofuTerrainBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.5F, 1.0F).sound(SoundType.SNOW));
	public static final Block ZUNDATOFU_TERRAIN = new TofuTerrainBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.5F, 1.0F).sound(SoundType.SNOW));

	public static final Block ORE_TOFU_DIAMOND = new Block(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.5F, 1.5F).sound(SoundType.SNOW));
	public static final Block TOFUBEDROCK = new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(-1.0F).sound(SoundType.STONE));
	public static final Block TOFU_FLOWER = new TofuFlowerBlock(Effects.REGENERATION, 2, AbstractBlock.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.GRASS));
	public static final Block BLOCKLEEK = new TofuBushBlock(AbstractBlock.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.GRASS));
	public static final Block LEEK_GREEN_STEM = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final Block LEEK_STEM = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final Block ZUNDATOFU_MUSHROOM = new TofuMushroomBlock(AbstractBlock.Properties.of(Material.PLANT).instabreak().noCollission().sound(SoundType.FUNGUS));
	public static final Block TOFU_STEM = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final Block TOFU_STEM_MOSS = new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.STEM));
	public static final Block TOFU_STEM_CACHE = new TofuStemCacheBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).lightLevel(state -> ((Boolean) state.getValue((Property) TofuStemCacheBlock.ZUNDAMA)).booleanValue() ? 13 : 0).sound(SoundType.STEM));
	public static final Block TOFU_STEM_PLANKS = new Block(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final Block POTTED_TOFU_FLOWER = new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> TOFU_FLOWER, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noCollission().sound(SoundType.STONE));

	public static final Block TOFU_FARMLAND = new TofuFarmlandBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).strength(0.5F, 1.0F).noOcclusion().sound(SoundType.SNOW));
	public static final Block SALTPAN = new SaltPanBlock(AbstractBlock.Properties.of(Material.WOOD).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).randomTicks().noOcclusion().sound(SoundType.WOOD));
	public static final Block SALT_FURNACE = new SaltFurnaceBlock(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(2.5F).randomTicks().sound(SoundType.METAL));
	public static final Block TOFUBED = new TofuBedBlock(AbstractBlock.Properties.of(TofuMaterial.TOFU).harvestTool(ToolType.AXE).strength(0.2F).noOcclusion().sound(SoundType.SNOW));
	public static final Block TOFUCHEST = new TofuChestBlock(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(2.5F).noOcclusion().sound(SoundType.STONE), () -> TofuTileEntitys.TOFUCHEST);

	public static final TofuPortalBlock TOFU_PORTAL = new TofuPortalBlock(AbstractBlock.Properties.of(Material.PORTAL).noCollission().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel(state -> 11)
			.noDrops());

	public static final Block TOFUCAKE = new TofuCakeBlock(1, 0.08F, AbstractBlock.Properties.of(Material.DECORATION).strength(0.5F).sound(SoundType.WOOL));

	public static final Block ZUNDATOFUCAKE = new TofuCakeBlock(2, 0.12F, AbstractBlock.Properties.of(Material.DECORATION).strength(0.5F).sound(SoundType.WOOL));

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
		registry.getRegistry().register(METALTOFU.setRegistryName("blocktofumetal"));
		registry.getRegistry().register(DIAMONDTOFU.setRegistryName("blocktofudiamond"));
		registry.getRegistry().register(HELLTOFU.setRegistryName("blocktofuhell"));
		registry.getRegistry().register(SOULTOFU.setRegistryName("blocktofusoul"));
		registry.getRegistry().register(GRILLEDTOFU.setRegistryName("blocktofugrilled"));
		registry.getRegistry().register(ZUNDATOFU.setRegistryName("blocktofuzunda"));
		registry.getRegistry().register(ISHITOFU_BRICK.setRegistryName("tofuishi_brick"));
		registry.getRegistry().register(ISHITOFU_SMOOTH_BRICK.setRegistryName("tofuishi_smooth_brick"));
		registry.getRegistry().register(ISHITOFU_CHISELED_BRICK.setRegistryName("tofuishi_chiseled_brick"));
		registry.getRegistry().register(ZUNDATOFU_BRICK.setRegistryName("tofuzunda_brick"));
		registry.getRegistry().register(ZUNDATOFU_SMOOTHBRICK.setRegistryName("tofuzunda_smooth_brick"));
		registry.getRegistry().register(HELLTOFU_BRICK.setRegistryName("tofuhell_brick"));
		registry.getRegistry().register(HELLTOFU_SMOOTHBRICK.setRegistryName("tofuhell_smooth_brick"));
		registry.getRegistry().register(SOULTOFU_BRICK.setRegistryName("tofusoul_brick"));
		registry.getRegistry().register(SOULTOFU_SMOOTHBRICK.setRegistryName("tofusoul_smooth_brick"));
		registry.getRegistry().register(TOFUSTAIR_KINU.setRegistryName("tofustair_kinu"));
		registry.getRegistry().register(TOFUSTAIR_MOMEN.setRegistryName("tofustair_momen"));
		registry.getRegistry().register(TOFUSTAIR_ISHI.setRegistryName("tofustair_ishi"));
		registry.getRegistry().register(TOFUSTAIR_METAL.setRegistryName("tofustair_metal"));
		registry.getRegistry().register(TOFUSTAIR_ZUNDA.setRegistryName("tofustair_zunda"));
		registry.getRegistry().register(TOFUSTAIR_ZUNDABRICK.setRegistryName("tofustair_zundabrick"));
		registry.getRegistry().register(TOFUSTAIR_ISHIBRICK.setRegistryName("tofustair_ishibrick"));
		registry.getRegistry().register(TOFUSTAIR_HELLBRICK.setRegistryName("tofustair_hellbrick"));
		registry.getRegistry().register(TOFUSTAIR_SOULBRICK.setRegistryName("tofustair_soulbrick"));
		registry.getRegistry().register(TOFUSLAB_KINU.setRegistryName("tofuslab_kinu"));
		registry.getRegistry().register(TOFUSLAB_MOMEN.setRegistryName("tofuslab_momen"));
		registry.getRegistry().register(TOFUSLAB_ISHI.setRegistryName("tofuslab_ishi"));
		registry.getRegistry().register(TOFUSLAB_METAL.setRegistryName("tofuslab_metal"));
		registry.getRegistry().register(TOFUSLAB_ISHIBRICK.setRegistryName("tofuslab_ishibrick"));
		registry.getRegistry().register(TOFUSLAB_ZUNDA.setRegistryName("tofuslab_zunda"));
		registry.getRegistry().register(TOFUSLAB_ZUNDABRICK.setRegistryName("tofuslab_zundabrick"));
		registry.getRegistry().register(TOFUSLAB_HELLBRICK.setRegistryName("tofuslab_hellbrick"));
		registry.getRegistry().register(TOFUSLAB_SOULBRICK.setRegistryName("tofuslab_soulbrick"));
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
		registry.getRegistry().register(TOFUFENCE_KINU.setRegistryName("tofufence_kinu"));
		registry.getRegistry().register(TOFUFENCE_MOMEN.setRegistryName("tofufence_momen"));
		registry.getRegistry().register(TOFUFENCE_ISHI.setRegistryName("tofufence_ishi"));
		registry.getRegistry().register(TOFUFENCE_METAL.setRegistryName("tofufence_metal"));
		registry.getRegistry().register(TOFUDOOR_KINU.setRegistryName("tofudoor_kinu"));
		registry.getRegistry().register(TOFUDOOR_MOMEN.setRegistryName("tofudoor_momen"));
		registry.getRegistry().register(TOFUDOOR_ISHI.setRegistryName("tofudoor_ishi"));
		registry.getRegistry().register(TOFUDOOR_METAL.setRegistryName("tofudoor_metal"));
		registry.getRegistry().register(TOFU_TERRAIN.setRegistryName("tofu_terrain"));
		registry.getRegistry().register(ZUNDATOFU_TERRAIN.setRegistryName("zundatofu_terrain"));
		registry.getRegistry().register(ORE_TOFU_DIAMOND.setRegistryName("ore_tofudiamond"));
		registry.getRegistry().register(TOFUBEDROCK.setRegistryName("tofubedrock"));
		registry.getRegistry().register(TOFU_FLOWER.setRegistryName("tofuflower"));
		registry.getRegistry().register(BLOCKLEEK.setRegistryName("blockleek"));
		registry.getRegistry().register(LEEK_GREEN_STEM.setRegistryName("leek_green_stem"));
		registry.getRegistry().register(LEEK_STEM.setRegistryName("leek_stem"));
		registry.getRegistry().register(ZUNDATOFU_MUSHROOM.setRegistryName("zundatofu_mushroom"));
		registry.getRegistry().register(TOFU_STEM.setRegistryName("tofustem"));
		registry.getRegistry().register(TOFU_STEM_MOSS.setRegistryName("tofustem_moss"));
		registry.getRegistry().register(TOFU_STEM_CACHE.setRegistryName("tofustem_cache"));
		registry.getRegistry().register(TOFU_STEM_PLANKS.setRegistryName("tofustem_planks"));
		registry.getRegistry().register(POTTED_TOFU_FLOWER.setRegistryName("potted_tofuflower"));
		registry.getRegistry().register(TOFU_FARMLAND.setRegistryName("tofu_farmland"));
		registry.getRegistry().register(SALTPAN.setRegistryName("blocksaltpan"));
		registry.getRegistry().register(SALT_FURNACE.setRegistryName("salt_furnace"));
		registry.getRegistry().register(TOFUBED.setRegistryName("tofubed"));
		registry.getRegistry().register(TOFUCHEST.setRegistryName("tofuchest"));
		registry.getRegistry().register(TOFU_PORTAL.setRegistryName("tofuportal"));
		registry.getRegistry().register(TOFUCAKE.setRegistryName("tofucake"));
		registry.getRegistry().register(ZUNDATOFUCAKE.setRegistryName("zundatofucake"));
		((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(TOFU_FLOWER.getRegistryName(), () -> POTTED_TOFU_FLOWER);
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> registry) {
		TofuItems.register(registry, new BlockItem(KINUTOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(MOMENTOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(METALTOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(DIAMONDTOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(GRILLEDTOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_BRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_SMOOTH_BRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_CHISELED_BRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_BRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_SMOOTHBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU_BRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU_SMOOTHBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU_BRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU_SMOOTHBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_KINU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_MOMEN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ISHI, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_METAL, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ZUNDA, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ZUNDABRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ISHIBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_HELLBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_SOULBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_KINU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_MOMEN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ISHI, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_METAL, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ZUNDA, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ZUNDABRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ISHIBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_HELLBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_SOULBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_KINU, WALLTOFUTORCH_KINU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_MOMEN, WALLTOFUTORCH_MOMEN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_ISHI, WALLTOFUTORCH_ISHI, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_METAL, WALLTOFUTORCH_METAL, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_KINU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_MOMEN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_ISHI, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_ISHIBRICK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_METAL, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_KINU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_MOMEN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_ISHI, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_METAL, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_KINU, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_MOMEN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_ISHI, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_METAL, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_TERRAIN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_TERRAIN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ORE_TOFU_DIAMOND, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUBEDROCK, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_FLOWER, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(LEEK_GREEN_STEM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(LEEK_STEM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_MUSHROOM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM_MOSS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM_CACHE, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM_PLANKS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_FARMLAND, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SALTPAN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SALT_FURNACE, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BedItem(TOFUBED, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT).setISTER(() -> baguchan.tofucraft.client.render.item.TofuBedItemRender::new)));
		TofuItems.register(registry, new BlockItem(TOFUCHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT).setISTER(() -> baguchan.tofucraft.client.render.item.TofuChestItemRender::new)));
		TofuItems.register(registry, new BlockItem(TOFUCAKE, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFUCAKE, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT)));
	}
}
