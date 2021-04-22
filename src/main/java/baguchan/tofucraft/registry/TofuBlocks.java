package baguchan.tofucraft.registry;

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
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.state.Property;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
public class TofuBlocks {
	public static final Block SOYMILK = new TofuFluidBlock(TofuFluids.SOYMILK, AbstractBlock.Properties.func_200945_a(Material.field_151586_h).func_200942_a().func_200943_b(100.0F).func_222380_e());

	public static final Block SOYMILK_HELL = new TofuFluidBlock(TofuFluids.SOYMILK_HELL, AbstractBlock.Properties.func_200945_a(Material.field_151586_h).func_200942_a().func_200943_b(100.0F).func_222380_e());

	public static final Block SOYMILK_SOUL = new TofuFluidBlock(TofuFluids.SOYMILK_SOUL, AbstractBlock.Properties.func_200945_a(Material.field_151586_h).func_200942_a().func_200943_b(100.0F).func_222380_e());

	public static final Block BITTERN = new TofuFluidBlock(TofuFluids.BITTERN, AbstractBlock.Properties.func_200945_a(Material.field_151586_h).func_200942_a().func_200943_b(100.0F).func_222380_e());

	public static final Block SOYBEAN = new SoybeanCropsBlock(AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_222472_s));

	public static final Block SOYBEAN_NETHER = new SoybeanNetherCropsBlock(AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_222472_s));

	public static final Block SOYBEAN_SOUL = new SoybeanSoulCropsBlock(AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_222472_s));

	public static final Block KINUTOFU = new KinuTofuBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).func_200944_c().harvestTool(ToolType.SHOVEL).func_200948_a(0.1F, 0.2F).func_200947_a(SoundType.field_185856_i));

	public static final Block MOMENTOFU = new TofuBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).func_200944_c().harvestTool(ToolType.SHOVEL).func_200948_a(0.35F, 0.5F).func_200947_a(SoundType.field_185856_i));

	public static final Block ISHITOFU = new TofuBlock(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).func_200944_c().harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.5F, 6.0F).func_200947_a(SoundType.field_185851_d));

	public static final Block METALTOFU = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151573_f).harvestTool(ToolType.PICKAXE).func_235861_h_().harvestLevel(1).func_200948_a(4.0F, 7.5F).func_200947_a(SoundType.field_185852_e));

	public static final Block DIAMONDTOFU = new Block(AbstractBlock.Properties.func_200949_a(Material.field_151573_f, MaterialColor.field_151674_s).harvestTool(ToolType.PICKAXE).func_235861_h_().harvestLevel(2).func_200948_a(5.0F, 8.0F).func_200947_a(SoundType.field_185852_e));

	public static final Block HELLTOFU = new Block(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.35F, 0.5F).func_200947_a(SoundType.field_185856_i));

	public static final Block SOULTOFU = new Block(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.35F, 0.5F).func_200947_a(SoundType.field_185856_i));

	public static final Block GRILLEDTOFU = new Block(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.35F, 0.5F).func_200947_a(SoundType.field_185856_i));

	public static final Block ZUNDATOFU = new Block(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.35F, 0.5F).func_200947_a(SoundType.field_185856_i));

	public static final Block ISHITOFU_BRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block ISHITOFU_SMOOTH_BRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block ISHITOFU_CHISELED_BRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block ZUNDATOFU_BRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block ZUNDATOFU_SMOOTHBRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block HELLTOFU_BRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block HELLTOFU_SMOOTHBRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block SOULTOFU_BRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block SOULTOFU_SMOOTHBRICK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.6F, 6.5F).func_200947_a(SoundType.field_185851_d));

	public static final Block TOFUSTAIR_KINU = new StairsBlock(KINUTOFU::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) KINUTOFU));

	public static final Block TOFUSTAIR_MOMEN = new StairsBlock(MOMENTOFU::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) MOMENTOFU));

	public static final Block TOFUSTAIR_ISHI = new StairsBlock(ISHITOFU::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) ISHITOFU));

	public static final Block TOFUSTAIR_METAL = new StairsBlock(METALTOFU::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) METALTOFU));

	public static final Block TOFUSTAIR_ZUNDA = new StairsBlock(ZUNDATOFU::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) ZUNDATOFU));

	public static final Block TOFUSTAIR_ZUNDABRICK = new StairsBlock(ZUNDATOFU_BRICK::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) ZUNDATOFU_BRICK));

	public static final Block TOFUSTAIR_ISHIBRICK = new StairsBlock(ISHITOFU_BRICK::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) ISHITOFU_BRICK));

	public static final Block TOFUSTAIR_HELLBRICK = new StairsBlock(HELLTOFU_BRICK::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) HELLTOFU_BRICK));

	public static final Block TOFUSTAIR_SOULBRICK = new StairsBlock(SOULTOFU_BRICK::func_176223_P, AbstractBlock.Properties.func_200950_a((AbstractBlock) SOULTOFU_BRICK));

	public static final Block TOFUSLAB_KINU = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) KINUTOFU));

	public static final Block TOFUSLAB_MOMEN = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) MOMENTOFU));

	public static final Block TOFUSLAB_ISHI = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) ISHITOFU));

	public static final Block TOFUSLAB_METAL = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) METALTOFU));

	public static final Block TOFUSLAB_ZUNDA = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) ZUNDATOFU));

	public static final Block TOFUSLAB_ZUNDABRICK = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) ZUNDATOFU_BRICK));

	public static final Block TOFUSLAB_ISHIBRICK = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) ISHITOFU_BRICK));

	public static final Block TOFUSLAB_HELLBRICK = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) HELLTOFU_BRICK));

	public static final Block TOFUSLAB_SOULBRICK = new SlabBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) SOULTOFU_BRICK));

	public static final Block TOFUTORCH_KINU = new TorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 0.5F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185856_i), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block TOFUTORCH_MOMEN = new TorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 0.5F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185856_i), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block TOFUTORCH_ISHI = new TorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 6.0F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185851_d), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block TOFUTORCH_METAL = new TorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 7.5F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185852_e), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block WALLTOFUTORCH_KINU = new WallTorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 0.5F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185856_i).func_222379_b(TOFUTORCH_KINU), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block WALLTOFUTORCH_MOMEN = new WallTorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 0.5F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185856_i).func_222379_b(TOFUTORCH_MOMEN), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block WALLTOFUTORCH_ISHI = new WallTorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 6.0F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185851_d).func_222379_b(TOFUTORCH_ISHI), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block WALLTOFUTORCH_METAL = new WallTorchBlock(AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200948_a(0.0F, 7.5F).func_235838_a_(state -> 14)

			.func_200942_a().func_226896_b_().func_200947_a(SoundType.field_185852_e).func_222379_b(TOFUTORCH_METAL), (IParticleData) ParticleTypes.field_197631_x);

	public static final Block TOFULADDER_KINU = new LadderBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) KINUTOFU).func_226896_b_());

	public static final Block TOFULADDER_MOMEN = new LadderBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) MOMENTOFU).func_226896_b_());

	public static final Block TOFULADDER_ISHI = new LadderBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) ISHITOFU).func_226896_b_());

	public static final Block TOFULADDER_METAL = new LadderBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) METALTOFU).func_226896_b_());

	public static final Block TOFULADDER_ISHIBRICK = new LadderBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) ISHITOFU_BRICK).func_226896_b_());

	public static final Block TOFUFENCE_KINU = new WallBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.1F, 0.2F).func_200947_a(SoundType.field_185856_i).func_226896_b_());

	public static final Block TOFUFENCE_MOMEN = new WallBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.35F, 0.5F).func_200947_a(SoundType.field_185856_i).func_226896_b_());

	public static final Block TOFUFENCE_ISHI = new WallBlock(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.5F, 6.0F).func_200947_a(SoundType.field_185851_d).func_226896_b_());

	public static final Block TOFUFENCE_METAL = new WallBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) METALTOFU).func_226896_b_());

	public static final Block TOFUDOOR_KINU = new DoorBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.1F, 0.2F).func_200947_a(SoundType.field_185856_i).func_226896_b_());

	public static final Block TOFUDOOR_MOMEN = new DoorBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.35F, 0.5F).func_200947_a(SoundType.field_185856_i).func_226896_b_());

	public static final Block TOFUDOOR_ISHI = new DoorBlock(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200948_a(1.5F, 6.0F).func_200947_a(SoundType.field_185851_d).func_226896_b_());

	public static final Block TOFUDOOR_METAL = new DoorBlock(AbstractBlock.Properties.func_200950_a((AbstractBlock) METALTOFU).func_226896_b_());

	public static final Block TOFU_TERRAIN = new TofuTerrainBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.5F, 1.0F).func_200947_a(SoundType.field_185856_i));

	public static final Block ZUNDATOFU_TERRAIN = new TofuTerrainBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.5F, 1.0F).func_200947_a(SoundType.field_185856_i));

	public static final Block ORE_TOFU_DIAMOND = new Block(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.5F, 1.5F).func_200947_a(SoundType.field_185856_i));

	public static final Block TOFUBEDROCK = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_235861_h_().func_200943_b(-1.0F).func_200947_a(SoundType.field_185851_d));

	public static final Block TOFU_FLOWER = new TofuFlowerBlock(Effects.field_76443_y, 2, AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200946_b().func_200942_a().func_200947_a(SoundType.field_185850_c));

	public static final Block BLOCKLEEK = new TofuBushBlock(AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200946_b().func_200942_a().func_200947_a(SoundType.field_185850_c));

	public static final Block LEEK_GREEN_STEM = new RotatedPillarBlock(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).func_200948_a(2.0F, 3.0F).func_200947_a(SoundType.field_235602_z_));

	public static final Block LEEK_STEM = new RotatedPillarBlock(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).func_200948_a(2.0F, 3.0F).func_200947_a(SoundType.field_235602_z_));

	public static final Block ZUNDATOFU_MUSHROOM = new TofuMushroomBlock(AbstractBlock.Properties.func_200945_a(Material.field_151585_k).func_200946_b().func_200942_a().func_200947_a(SoundType.field_235580_B_));

	public static final Block TOFU_STEM = new RotatedPillarBlock(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).func_200948_a(2.0F, 3.0F).func_200947_a(SoundType.field_235602_z_));

	public static final Block TOFU_STEM_MOSS = new RotatedPillarBlock(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).func_200948_a(2.0F, 3.0F).func_200947_a(SoundType.field_235602_z_));

	public static final Block TOFU_STEM_CACHE;

	static {
		TOFU_STEM_CACHE = new TofuStemCacheBlock(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).func_200948_a(2.0F, 3.0F).func_235838_a_(state -> ((Boolean) state.func_177229_b((Property) TofuStemCacheBlock.ZUNDAMA)).booleanValue() ? 13 : 0).func_200947_a(SoundType.field_235602_z_));
	}

	public static final Block TOFU_STEM_PLANKS = new Block(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).func_200948_a(2.0F, 3.0F).func_200947_a(SoundType.field_185848_a));

	public static final Block POTTED_TOFU_FLOWER = new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.field_150457_bL, () -> TOFU_FLOWER, AbstractBlock.Properties.func_200945_a(Material.field_151594_q).func_200946_b().func_200942_a().func_200947_a(SoundType.field_185851_d));

	public static final Block TOFU_FARMLAND = new TofuFarmlandBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.SHOVEL).func_200948_a(0.5F, 1.0F).func_226896_b_().func_200947_a(SoundType.field_185856_i));

	public static final Block SALTPAN = new SaltPanBlock(AbstractBlock.Properties.func_200945_a(Material.field_151575_d).harvestTool(ToolType.AXE).func_200948_a(2.0F, 3.0F).func_200944_c().func_226896_b_().func_200947_a(SoundType.field_185848_a));

	public static final Block SALT_FURNACE = new SaltFurnaceBlock(AbstractBlock.Properties.func_200945_a(Material.field_151573_f).harvestTool(ToolType.PICKAXE).func_200943_b(2.5F).func_200944_c().func_200947_a(SoundType.field_185852_e));

	public static final Block TOFUBED = new TofuBedBlock(AbstractBlock.Properties.func_200945_a(TofuMaterial.TOFU).harvestTool(ToolType.AXE).func_200943_b(0.2F).func_226896_b_().func_200947_a(SoundType.field_185856_i));

	public static final Block TOFUCHEST = new TofuChestBlock(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).harvestTool(ToolType.PICKAXE).func_200943_b(2.5F).func_226896_b_().func_200947_a(SoundType.field_185851_d), () -> TofuTileEntitys.TOFUCHEST);

	public static final TofuPortalBlock TOFU_PORTAL = new TofuPortalBlock(AbstractBlock.Properties.func_200945_a(Material.field_151567_E).func_200942_a().func_200944_c().func_200943_b(-1.0F).func_200947_a(SoundType.field_185853_f).func_235838_a_(state -> 11)

			.func_222380_e());

	public static final Block TOFUCAKE = new TofuCakeBlock(1, 0.08F, AbstractBlock.Properties.func_200945_a(Material.field_151568_F).func_200943_b(0.5F).func_200947_a(SoundType.field_185854_g));

	public static final Block ZUNDATOFUCAKE = new TofuCakeBlock(2, 0.12F, AbstractBlock.Properties.func_200945_a(Material.field_151568_F).func_200943_b(0.5F).func_200947_a(SoundType.field_185854_g));

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
		((FlowerPotBlock) Blocks.field_150457_bL).addPlant(TOFU_FLOWER.getRegistryName(), () -> POTTED_TOFU_FLOWER);
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> registry) {
		TofuItems.register(registry, new BlockItem(KINUTOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(MOMENTOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(METALTOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(DIAMONDTOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(GRILLEDTOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_BRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_SMOOTH_BRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ISHITOFU_CHISELED_BRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_BRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_SMOOTHBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU_BRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(HELLTOFU_SMOOTHBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU_BRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SOULTOFU_SMOOTHBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_KINU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_MOMEN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ISHI, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_METAL, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ZUNDA, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ZUNDABRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_ISHIBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_HELLBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSTAIR_SOULBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_KINU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_MOMEN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ISHI, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_METAL, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ZUNDA, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ZUNDABRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_ISHIBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_HELLBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUSLAB_SOULBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_KINU, WALLTOFUTORCH_KINU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_MOMEN, WALLTOFUTORCH_MOMEN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_ISHI, WALLTOFUTORCH_ISHI, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new WallOrFloorItem(TOFUTORCH_METAL, WALLTOFUTORCH_METAL, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_KINU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_MOMEN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_ISHI, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_ISHIBRICK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFULADDER_METAL, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_KINU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_MOMEN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_ISHI, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUFENCE_METAL, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_KINU, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_MOMEN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_ISHI, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new TallBlockItem(TOFUDOOR_METAL, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_TERRAIN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_TERRAIN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ORE_TOFU_DIAMOND, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFUBEDROCK, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_FLOWER, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(LEEK_GREEN_STEM, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(LEEK_STEM, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFU_MUSHROOM, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM_MOSS, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM_CACHE, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_STEM_PLANKS, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(TOFU_FARMLAND, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SALTPAN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(SALT_FURNACE, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BedItem(TOFUBED, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT).setISTER(() -> baguchan.tofucraft.client.render.item.TofuBedItemRender::new)));
		TofuItems.register(registry, new BlockItem(TOFUCHEST, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT).setISTER(() -> baguchan.tofucraft.client.render.item.TofuChestItemRender::new)));
		TofuItems.register(registry, new BlockItem(TOFUCAKE, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
		TofuItems.register(registry, new BlockItem(ZUNDATOFUCAKE, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT)));
	}
}
