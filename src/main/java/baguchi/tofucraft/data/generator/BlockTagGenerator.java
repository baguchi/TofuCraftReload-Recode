package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
	public BlockTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, TofuCraftReload.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(TofuBlocks.KINUTOFU.getKey(), TofuBlocks.MOMENTOFU.getKey(), TofuBlocks.HELLTOFU.getKey(), TofuBlocks.SOULTOFU.getKey()
						, TofuBlocks.GRILLEDTOFU.getKey(), TofuBlocks.ZUNDATOFU.getKey()
						, TofuBlocks.TOFUSTAIR_KINU.getKey(), TofuBlocks.TOFUSTAIR_MOMEN.getKey(), TofuBlocks.TOFUSTAIR_ZUNDA.getKey(), TofuBlocks.TOFUSTAIR_HELL.getKey(), TofuBlocks.TOFUSTAIR_SOUL.getKey()
						, TofuBlocks.TOFUSLAB_KINU.getKey(), TofuBlocks.TOFUSLAB_MOMEN.getKey(), TofuBlocks.TOFUSLAB_ZUNDA.getKey(), TofuBlocks.TOFUSLAB_HELL.getKey(), TofuBlocks.TOFUSLAB_SOUL.getKey()
						, TofuBlocks.TOFULADDER_KINU.getKey(), TofuBlocks.TOFULADDER_MOMEN.getKey()
						, TofuBlocks.TOFUFENCE_KINU.getKey(), TofuBlocks.TOFUFENCE_MOMEN.getKey(), TofuBlocks.TOFUFENCE_HELL.getKey(), TofuBlocks.TOFUFENCE_SOUL.getKey()
						, TofuBlocks.TOFUDOOR_KINU.getKey(), TofuBlocks.TOFUDOOR_MOMEN.getKey(), TofuBlocks.TOFUDOOR_HELL.getKey(), TofuBlocks.TOFUDOOR_SOUL.getKey()
						, TofuBlocks.TOFUTRAPDOOR_KINU.getKey(), TofuBlocks.TOFUTRAPDOOR_MOMEN.getKey(), TofuBlocks.TOFUTRAPDOOR_HELL.getKey(), TofuBlocks.TOFUTRAPDOOR_SOUL.getKey()
						, TofuBlocks.TOFU_TERRAIN.getKey(), TofuBlocks.MABOU_TERRAIN.getKey(), TofuBlocks.TOFU_TERRAIN_ZUNDA.getKey(), TofuBlocks.ORE_TOFU_DIAMOND.getKey(), TofuBlocks.ORE_TOFUGEM.getKey()
						, TofuBlocks.EGGTOFU.getKey(), TofuBlocks.TOFUSTAIR_EGG.getKey(), TofuBlocks.TOFUSLAB_EGG.getKey()
						, TofuBlocks.SESAMETOFU.getKey(), TofuBlocks.TOFUSTAIR_SESAME.getKey(), TofuBlocks.TOFUSLAB_SESAME.getKey()
						, TofuBlocks.MISOTOFU.getKey(), TofuBlocks.TOFUSTAIR_MISO.getKey(), TofuBlocks.TOFUSLAB_MISO.getKey()
						, TofuBlocks.DRIEDTOFU.getKey(), TofuBlocks.TOFUSTAIR_DRIED.getKey(), TofuBlocks.TOFUSLAB_DRIED.getKey(), TofuBlocks.MINCEDTOFU.getKey())
				.add(TofuBlocks.SALT_BLOCK.getKey())
				.add(TofuBlocks.OKARA_BLOCK.getKey())
				.add(TofuBlocks.WAXED_KINUTOFU.getKey(), TofuBlocks.WAXED_MOMENTOFU.getKey());
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TofuBlocks.ISHITOFU.getKey(), TofuBlocks.ISHITOFU_BRICK.getKey(), TofuBlocks.ISHITOFU_SMOOTH_BRICK.getKey(), TofuBlocks.ISHITOFU_CHISELED_BRICK.getKey()
						, TofuBlocks.METALTOFU.getKey(), TofuBlocks.METAL_TOFU_GRATE.getKey(), TofuBlocks.METAL_TOFU_LUMP.getKey(), TofuBlocks.METAL_TOFU_BARS.getKey(), TofuBlocks.DIAMONDTOFU.getKey(), TofuBlocks.TOFU_GEM_BLOCK.getKey(), TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.getKey()
						, TofuBlocks.HELLTOFU_BRICK.getKey(), TofuBlocks.HELLTOFU_SMOOTH_BRICK.getKey(), TofuBlocks.HELLTOFU_CHISELED_BRICK.getKey()
						, TofuBlocks.SOULTOFU_BRICK.getKey(), TofuBlocks.SOULTOFU_SMOOTH_BRICK.getKey(), TofuBlocks.SOULTOFU_CHISELED_BRICK.getKey()
						, TofuBlocks.DRIEDTOFU_BRICK.getKey(), TofuBlocks.DRIEDTOFU_SMOOTH_BRICK.getKey(), TofuBlocks.DRIEDTOFU_CHISELED_BRICK.getKey(), TofuBlocks.TOFUSTAIR_DRIEDBRICK.getKey(), TofuBlocks.TOFUSLAB_DRIEDBRICK.getKey()
						, TofuBlocks.EGGTOFU_BRICK.getKey(), TofuBlocks.EGGTOFU_SMOOTH_BRICK.getKey(), TofuBlocks.EGGTOFU_CHISELED_BRICK.getKey(), TofuBlocks.TOFUSTAIR_EGGBRICK.getKey(), TofuBlocks.TOFUSLAB_EGGBRICK.getKey()
						, TofuBlocks.ZUNDATOFU_BRICK.getKey(), TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.getKey(), TofuBlocks.TOFUSTAIR_ZUNDABRICK.getKey(), TofuBlocks.TOFUSLAB_ZUNDABRICK.getKey()
						, TofuBlocks.TOFUSTAIR_ISHI.getKey(), TofuBlocks.TOFUSTAIR_METAL.getKey(), TofuBlocks.TOFUSTAIR_ISHIBRICK.getKey(), TofuBlocks.TOFUSTAIR_HELLBRICK.getKey(), TofuBlocks.TOFUSTAIR_SOULBRICK.getKey()
						, TofuBlocks.TOFUSLAB_ISHI.getKey(), TofuBlocks.TOFUSLAB_METAL.getKey(), TofuBlocks.TOFUSLAB_ISHIBRICK.getKey(), TofuBlocks.TOFUSLAB_HELLBRICK.getKey(), TofuBlocks.TOFUSLAB_SOULBRICK.getKey()
						, TofuBlocks.TOFULADDER_ISHI.getKey(), TofuBlocks.TOFULADDER_ISHIBRICK.getKey(), TofuBlocks.TOFULADDER_METAL.getKey()
						, TofuBlocks.TOFUFENCE_ISHI.getKey(), TofuBlocks.TOFUFENCE_METAL.getKey()
						, TofuBlocks.TOFUDOOR_ISHI.getKey(), TofuBlocks.TOFUDOOR_METAL.getKey()
						, TofuBlocks.TOFUTRAPDOOR_ISHI.getKey(), TofuBlocks.TOFUTRAPDOOR_METAL.getKey()
						, TofuBlocks.TOFUCHEST.getKey()
						, TofuBlocks.TOFU_BEDROCK.getKey()
						, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.getKey()
						, TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.getKey()
						, TofuBlocks.TOFUSLATE.getKey()
						, TofuBlocks.ISHI_TOFU_STEM.getKey()
						, TofuBlocks.SALT_FURNACE.getKey()
						, TofuBlocks.SOYMILK_CAULDRON.getKey(), TofuBlocks.SOYMILK_NETHER_CAULDRON.getKey(), TofuBlocks.SOYMILK_SOUL_CAULDRON.getKey()
						, TofuBlocks.TOFU_METAL_LANTERN.getKey(), TofuBlocks.TOFU_METAL_SOUL_LANTERN.getKey(), TofuBlocks.TOFU_METAL_CHAIN.getKey()
						, TofuBlocks.TOFU_DETECTOR.getKey(), TofuBlocks.TF_STORAGE.getKey(), TofuBlocks.TF_OVEN.getKey(), TofuBlocks.TF_TOFU_MAKER.getKey(), TofuBlocks.ANTENNA_BASIC.getKey(), TofuBlocks.ANTENNA_ADVANCE.getKey(), TofuBlocks.TOFU_WORK_STATION.getKey(), TofuBlocks.TF_COLLECTOR.getKey()
						, TofuBlocks.TOFUNIAN_STATUE.getKey()
						, TofuBlocks.TOFU_POT.getKey()
						, TofuBlocks.TF_CRAFTING_TABLE.getKey(), TofuBlocks.WAXED_ISHITOFU.getKey(), TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.getKey())
				.add(TofuBlocks.FOODPLATE.getKey());
		tag(BlockTags.MINEABLE_WITH_AXE).add(TofuBlocks.SALTPAN.getKey()
						, TofuBlocks.SPROUT_STEM.getKey(), TofuBlocks.YELLOW_SPROUT_STEM.getKey()
						, TofuBlocks.TOFU_STEM.getKey(), TofuBlocks.TOFU_STEM_PLANKS.getKey(), TofuBlocks.TOFU_STEM_PLANKS_STAIR.getKey(), TofuBlocks.TOFU_STEM_PLANKS_SLAB.getKey(), TofuBlocks.TOFU_STEM_FENCE.getKey(), TofuBlocks.TOFU_STEM_FENCE_GATE.getKey(), TofuBlocks.TOFU_STEM_DOOR.getKey(), TofuBlocks.TOFU_STEM_TRAPDOOR.getKey()
						, TofuBlocks.LEEK_STEM.getKey(), TofuBlocks.LEEK_PLANKS.getKey(), TofuBlocks.LEEK_PLANKS_STAIR.getKey(), TofuBlocks.LEEK_PLANKS_SLAB.getKey(), TofuBlocks.LEEK_FENCE.getKey(), TofuBlocks.LEEK_FENCE_GATE.getKey()
						, TofuBlocks.LEEK_GREEN_STEM.getKey(), TofuBlocks.LEEK_GREEN_PLANKS.getKey(), TofuBlocks.LEEK_GREEN_PLANKS_STAIR.getKey(), TofuBlocks.LEEK_GREEN_PLANKS_SLAB.getKey(), TofuBlocks.LEEK_GREEN_FENCE.getKey(), TofuBlocks.LEEK_GREEN_FENCE_GATE.getKey(), TofuBlocks.LEEK_GREEN_DOOR.getKey(), TofuBlocks.LEEK_GREEN_TRAPDOOR.getKey(), TofuBlocks.LEEK_DOOR.getKey(), TofuBlocks.LEEK_TRAPDOOR.getKey()
						, TofuBlocks.SPROUT_STEM.getKey(), TofuBlocks.SPROUT_PLANKS.getKey(), TofuBlocks.SPROUT_PLANKS_STAIR.getKey(), TofuBlocks.SPROUT_PLANKS_SLAB.getKey(), TofuBlocks.SPROUT_FENCE.getKey(), TofuBlocks.SPROUT_FENCE_GATE.getKey(), TofuBlocks.SPROUT_DOOR.getKey(), TofuBlocks.SPROUT_TRAPDOOR.getKey(), TofuBlocks.LEEK_DOOR.getKey(), TofuBlocks.LEEK_TRAPDOOR.getKey()
						, TofuBlocks.MORIJIO.getKey()
						, TofuBlocks.BARREL_MISO.getKey(), TofuBlocks.BARREL_MISOTOFU.getKey(), TofuBlocks.BARREL_ADV_TOFUGEM.getKey()
						, TofuBlocks.SPROUT_SIGN.getKey(), TofuBlocks.SPROUT_WALL_SIGN.getKey(), TofuBlocks.SPROUT_HANGING_SIGN.getKey(), TofuBlocks.SPROUT_WALL_HANGING_SIGN.getKey()
						, TofuBlocks.TOFU_STEM_SIGN.getKey(), TofuBlocks.TOFU_STEM_WALL_SIGN.getKey(), TofuBlocks.TOFU_STEM_HANGING_SIGN.getKey(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.getKey()
						, TofuBlocks.LEEK_GREEN_SIGN.getKey(), TofuBlocks.LEEK_GREEN_WALL_SIGN.getKey(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.getKey(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.getKey()
						, TofuBlocks.LEEK_SIGN.getKey(), TofuBlocks.LEEK_WALL_SIGN.getKey(), TofuBlocks.LEEK_HANGING_SIGN.getKey(), TofuBlocks.LEEK_WALL_HANGING_SIGN.getKey())
				.add(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.getKey(), TofuBlocks.TOFU_STEM_BUTTON.getKey())
				.add(TofuBlocks.LEEK_PRESSURE_PLATE.getKey(), TofuBlocks.LEEK_BUTTON.getKey())
				.add(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.getKey(), TofuBlocks.LEEK_GREEN_BUTTON.getKey())
				.add(TofuBlocks.SPROUT_PRESSURE_PLATE.getKey(), TofuBlocks.SPROUT_BUTTON.getKey())
				.add(
						TofuBlocks.LEEK_GREEN_SHELF.getKey(),
						TofuBlocks.LEEK_SHELF.getKey(),
						TofuBlocks.TOFU_STEM_SHELF.getKey(),
						TofuBlocks.SPROUT_SHELF.getKey()
				);
		tag(BlockTags.LANTERNS).add(TofuBlocks.TOFU_METAL_LANTERN.getKey()).add(TofuBlocks.TOFU_METAL_SOUL_LANTERN.getKey());
		tag(BlockTags.CHAINS).add(TofuBlocks.TOFU_METAL_CHAIN.getKey());
		tag(BlockTags.MINEABLE_WITH_HOE).add(TofuBlocks.GIANT_OKARA_DONUT.getKey()).add(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.getKey()).add(TofuBlocks.RICE_ROOT.getKey(), TofuBlocks.LEAVES_APRICOT.getKey(), TofuBlocks.LEAVES_TOFU.getKey(), TofuBlocks.RICE_BLOCK.getKey(), TofuBlocks.SOYBEANS_SEEDS_BLOCK.getKey(), TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.getKey(), TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.getKey())
				.add(TofuBlocks.SOY_CHEESE_BLOCK.getKey()).add(TofuBlocks.SOY_NETHER_CHEESE_BLOCK.getKey()).add(TofuBlocks.SOY_SOUL_CHEESE_BLOCK.getKey());

		tag(BlockTags.NEEDS_STONE_TOOL)
				.add(TofuBlocks.METALTOFU.getKey(), TofuBlocks.TOFUSTAIR_METAL.getKey(), TofuBlocks.TOFUSLAB_METAL.getKey(), TofuBlocks.TOFULADDER_METAL.getKey(), TofuBlocks.TOFUFENCE_METAL.getKey(), TofuBlocks.TOFUDOOR_METAL.getKey(), TofuBlocks.TOFUTRAPDOOR_METAL.getKey())
				.add(TofuBlocks.TOFU_GEM_BLOCK.getKey()).add(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.getKey()).add(TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.getKey());
		tag(BlockTags.NEEDS_IRON_TOOL)
				.add(TofuBlocks.DIAMONDTOFU.getKey(), TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.getKey());
		tag(BlockTags.PIGLIN_REPELLENTS).add(TofuBlocks.SOYBEAN_SOUL.getKey());

		tag(TofuTags.Blocks.TOFU_FARMLANDS).add(TofuBlocks.TOFU_FARMLAND.getKey());

		tag(TofuTags.Blocks.HAS_INFO).add(TofuBlocks.SALT_FURNACE.getKey()).add(TofuBlocks.SALTPAN.getKey());
		tag(TofuTags.Blocks.PICKABLE_TOFU).add(TofuBlocks.KINUTOFU.getKey(), TofuBlocks.MOMENTOFU.getKey(), TofuBlocks.HELLTOFU.getKey(), TofuBlocks.SOULTOFU.getKey()
				, TofuBlocks.GRILLEDTOFU.getKey(), TofuBlocks.ZUNDATOFU.getKey(), TofuBlocks.MINCEDTOFU.getKey()).add(TofuBlocks.METALTOFU.getKey()).add(TofuBlocks.ISHITOFU.getKey());

		tag(BlockTags.REPLACEABLE).add(TofuBlocks.LEEK.getKey()).add(TofuBlocks.TALL_LEEK.getKey());
		tag(TofuTags.Blocks.TF_TRANSMITTER).addTag(BlockTags.REPLACEABLE).add(TofuBlocks.ANTENNA_BASIC.getKey()).add(TofuBlocks.ANTENNA_ADVANCE.getKey());

		tag(BlockTags.ENABLES_BUBBLE_COLUMN_DRAG_DOWN).add(TofuBlocks.MABOU_TERRAIN.getKey());


		tag(BlockTags.BEDS).add(TofuBlocks.TOFUBED.getKey());
		tag(BlockTags.WALLS).add(TofuBlocks.TOFUFENCE_KINU.getKey(), TofuBlocks.TOFUFENCE_MOMEN.getKey(), TofuBlocks.TOFUFENCE_HELL.getKey(), TofuBlocks.TOFUFENCE_SOUL.getKey(), TofuBlocks.TOFUFENCE_ISHI.getKey(), TofuBlocks.TOFUFENCE_METAL.getKey())
				.add(TofuBlocks.TOFUFENCE_GRILLED.getKey(), TofuBlocks.TOFUFENCE_ZUNDA.getKey());
		tag(BlockTags.CLIMBABLE).add(TofuBlocks.TOFULADDER_KINU.getKey(), TofuBlocks.TOFULADDER_MOMEN.getKey(), TofuBlocks.TOFULADDER_ISHI.getKey(), TofuBlocks.TOFULADDER_ISHIBRICK.getKey(), TofuBlocks.TOFULADDER_METAL.getKey(), TofuBlocks.TOFULADDER_GRILLED.getKey(), TofuBlocks.TOFULADDER_ZUNDA.getKey(), TofuBlocks.TOFULADDER_HELL.getKey(), TofuBlocks.TOFULADDER_SOUL.getKey());
		tag(BlockTags.WITHER_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.getKey());
		tag(BlockTags.DRAGON_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.getKey());
		tag(BlockTags.FEATURES_CANNOT_REPLACE).add(TofuBlocks.TOFU_BEDROCK.getKey());
		tag(BlockTags.TRAPDOORS).add(TofuBlocks.TOFUTRAPDOOR_KINU.getKey(), TofuBlocks.TOFUTRAPDOOR_MOMEN.getKey(), TofuBlocks.TOFUTRAPDOOR_HELL.getKey(), TofuBlocks.TOFUTRAPDOOR_SOUL.getKey(), TofuBlocks.TOFUTRAPDOOR_ISHI.getKey(), TofuBlocks.TOFUTRAPDOOR_METAL.getKey(), TofuBlocks.TOFUTRAPDOOR_GRILLED.getKey(), TofuBlocks.TOFUTRAPDOOR_ZUNDA.getKey());
		tag(BlockTags.WOODEN_DOORS).add(TofuBlocks.TOFUDOOR_KINU.getKey(), TofuBlocks.TOFUDOOR_MOMEN.getKey(), TofuBlocks.TOFUDOOR_HELL.getKey(), TofuBlocks.TOFUDOOR_SOUL.getKey(), TofuBlocks.TOFUDOOR_ISHI.getKey(), TofuBlocks.TOFUDOOR_GRILLED.getKey(), TofuBlocks.TOFUDOOR_ZUNDA.getKey())
				.add(TofuBlocks.SPROUT_DOOR.getKey(), TofuBlocks.TOFU_STEM_DOOR.getKey(), TofuBlocks.LEEK_GREEN_DOOR.getKey(), TofuBlocks.LEEK_DOOR.getKey());
		tag(BlockTags.DOORS).add(TofuBlocks.TOFUDOOR_METAL.getKey()).add(TofuBlocks.TOFUDOOR_KINU.getKey(), TofuBlocks.TOFUDOOR_MOMEN.getKey(), TofuBlocks.TOFUDOOR_HELL.getKey(), TofuBlocks.TOFUDOOR_SOUL.getKey(), TofuBlocks.TOFUDOOR_ISHI.getKey(), TofuBlocks.TOFUDOOR_GRILLED.getKey(), TofuBlocks.TOFUDOOR_ZUNDA.getKey())
				.add(TofuBlocks.SPROUT_DOOR.getKey(), TofuBlocks.TOFU_STEM_DOOR.getKey(), TofuBlocks.LEEK_GREEN_DOOR.getKey(), TofuBlocks.LEEK_DOOR.getKey());

		this.tag(BlockTags.WOODEN_SHELVES)
				.add(
						TofuBlocks.LEEK_GREEN_SHELF.getKey(),
						TofuBlocks.LEEK_SHELF.getKey(),
						TofuBlocks.TOFU_STEM_SHELF.getKey()
				);

		tag(BlockTags.WOODEN_TRAPDOORS).add(TofuBlocks.TOFUTRAPDOOR_KINU.getKey(), TofuBlocks.TOFUTRAPDOOR_MOMEN.getKey(), TofuBlocks.TOFUTRAPDOOR_HELL.getKey(), TofuBlocks.TOFUTRAPDOOR_SOUL.getKey(), TofuBlocks.TOFUTRAPDOOR_ISHI.getKey(), TofuBlocks.TOFUTRAPDOOR_GRILLED.getKey(), TofuBlocks.TOFUTRAPDOOR_ZUNDA.getKey())
				.add(TofuBlocks.SPROUT_TRAPDOOR.getKey(), TofuBlocks.TOFU_STEM_TRAPDOOR.getKey(), TofuBlocks.LEEK_GREEN_TRAPDOOR.getKey(), TofuBlocks.LEEK_TRAPDOOR.getKey());
		tag(BlockTags.TRAPDOORS).add(TofuBlocks.TOFUTRAPDOOR_METAL.getKey()).add(TofuBlocks.TOFUTRAPDOOR_KINU.getKey(), TofuBlocks.TOFUTRAPDOOR_MOMEN.getKey(), TofuBlocks.TOFUTRAPDOOR_HELL.getKey(), TofuBlocks.TOFUTRAPDOOR_SOUL.getKey(), TofuBlocks.TOFUTRAPDOOR_ISHI.getKey(), TofuBlocks.TOFUTRAPDOOR_GRILLED.getKey(), TofuBlocks.TOFUTRAPDOOR_ZUNDA.getKey())
				.add(TofuBlocks.SPROUT_TRAPDOOR.getKey(), TofuBlocks.TOFU_STEM_TRAPDOOR.getKey(), TofuBlocks.LEEK_GREEN_TRAPDOOR.getKey(), TofuBlocks.LEEK_TRAPDOOR.getKey());

		tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(TofuBlocks.SOULTOFU.getKey(), TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.getKey());
		tag(BlockTags.INFINIBURN_OVERWORLD).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.getKey());

		tag(BlockTags.SOUL_SPEED_BLOCKS).add(TofuBlocks.SOULTOFU.getKey());
		tag(BlockTags.CAULDRONS).add(TofuBlocks.SOYMILK_CAULDRON.getKey(), TofuBlocks.SOYMILK_NETHER_CAULDRON.getKey(), TofuBlocks.SOYMILK_SOUL_CAULDRON.getKey());

		tag(BlockTags.PLANKS).add(TofuBlocks.SPROUT_PLANKS.getKey()).add(TofuBlocks.TOFU_STEM_PLANKS.getKey()).add(TofuBlocks.LEEK_PLANKS.getKey()).add(TofuBlocks.LEEK_GREEN_PLANKS.getKey());
		tag(BlockTags.WOODEN_SLABS).add(TofuBlocks.SPROUT_PLANKS_SLAB.getKey()).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.getKey()).add(TofuBlocks.LEEK_PLANKS_SLAB.getKey()).add(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.getKey());
		tag(BlockTags.WOODEN_STAIRS).add(TofuBlocks.SPROUT_PLANKS_STAIR.getKey()).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.getKey()).add(TofuBlocks.LEEK_PLANKS_STAIR.getKey()).add(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.getKey());
		tag(BlockTags.WOODEN_FENCES).add(TofuBlocks.SPROUT_FENCE.getKey()).add(TofuBlocks.TOFU_STEM_FENCE.getKey()).add(TofuBlocks.LEEK_FENCE.getKey()).add(TofuBlocks.LEEK_GREEN_FENCE.getKey());
		tag(BlockTags.FENCE_GATES).add(TofuBlocks.SPROUT_FENCE_GATE.getKey()).add(TofuBlocks.TOFU_STEM_FENCE_GATE.getKey()).add(TofuBlocks.LEEK_FENCE_GATE.getKey()).add(TofuBlocks.LEEK_GREEN_FENCE_GATE.getKey());

		this.tag(BlockTags.FEATURES_CANNOT_REPLACE).add(TofuBlocks.TOFU_BEDROCK.getKey(), TofuBlocks.TOFUCHEST.getKey());
		this.tag(BlockTags.PORTALS).add(TofuBlocks.TOFU_PORTAL.getKey());

		tag(TofuTags.Blocks.TOFU_TERRAIN).add(TofuBlocks.TOFU_TERRAIN.getKey(), TofuBlocks.TOFU_TERRAIN_ZUNDA.getKey());


		tag(TofuTags.Blocks.SUBSTRATE_TOFU_WORLD).addTag(TofuTags.Blocks.TOFU_TERRAIN);

		tag(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE).addTag(TofuTags.Blocks.TOFU_TERRAIN).add(TofuBlocks.SOYMILK.getKey())
				.add(TofuBlocks.TOFUSLATE.getKey()).add(TofuBlocks.OKARA_BLOCK.getKey());

		this.tag(TofuTags.Blocks.SUPPORTS_TOFU_PLANT)
				.addTag(TofuTags.Blocks.SUBSTRATE_TOFU_WORLD)
				.add(TofuBlocks.MOMENTOFU.getKey());
		this.tag(TofuTags.Blocks.SUPPORTS_ROUGH_TOFU_PLANT)
				.addTag(TofuTags.Blocks.SUPPORTS_TOFU_PLANT)
				.add(TofuBlocks.MINCEDTOFU.getKey());

		tag(BlockTags.LEAVES).add(TofuBlocks.LEAVES_APRICOT.getKey(), TofuBlocks.LEAVES_TOFU.getKey());
		this.tag(BlockTags.REPLACEABLE_BY_TREES).add(TofuBlocks.LEAVES_APRICOT.getKey(), TofuBlocks.LEAVES_TOFU.getKey(), TofuBlocks.LEEK.getKey(), TofuBlocks.TALL_LEEK.getKey()).add(TofuBlocks.ZUNDATOFU_MUSHROOM.getKey());
		this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(TofuBlocks.TOFU_STEM.getKey()).add(TofuBlocks.LEEK_STEM.getKey()).add(TofuBlocks.LEEK_GREEN_STEM.getKey());

		tag(BlockTags.CROPS).add(TofuBlocks.RICE_CROP.getKey()).add(TofuBlocks.SOYBEAN.getKey()).add(TofuBlocks.SOYBEAN_NETHER.getKey()).add(TofuBlocks.SOYBEAN_SOUL.getKey()).add(TofuBlocks.SOYBEAN_PALE.getKey()).add(TofuBlocks.LEEK_CROP.getKey());

		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.TOFU_CANDLE_CAKE.getKey(), TofuBlocks.WHITE_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.ORANGE_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.MAGENTA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.LIGHT_BLUE_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.YELLOW_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.LIME_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.PINK_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.GRAY_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.LIGHT_GRAY_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.CYAN_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.PURPLE_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.BLUE_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.BROWN_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.GREEN_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.RED_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.BLACK_TOFU_CANDLE_CAKE.getKey());
		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.WHITE_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.ORANGE_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.MAGENTA_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.YELLOW_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.LIME_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.PINK_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.GRAY_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.CYAN_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.PURPLE_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.BLUE_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.BROWN_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.GREEN_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.RED_ZUNDA_TOFU_CANDLE_CAKE.getKey(), TofuBlocks.BLACK_ZUNDA_TOFU_CANDLE_CAKE.getKey());
		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.WHITE_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.ORANGE_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.MAGENTA_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.LIGHT_BLUE_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.YELLOW_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.LIME_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.PINK_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.GRAY_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.LIGHT_GRAY_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.CYAN_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.PURPLE_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.BLUE_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.BROWN_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.GREEN_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.RED_SOYCHEESE_CANDLE_TART.getKey(), TofuBlocks.BLACK_SOYCHEESE_CANDLE_TART.getKey());
		this.tag(BlockTags.STANDING_SIGNS).add(TofuBlocks.SPROUT_SIGN.getKey(), TofuBlocks.TOFU_STEM_SIGN.getKey(), TofuBlocks.LEEK_GREEN_SIGN.getKey(), TofuBlocks.LEEK_SIGN.getKey());
		this.tag(BlockTags.WALL_SIGNS).add(TofuBlocks.SPROUT_WALL_SIGN.getKey(), TofuBlocks.TOFU_STEM_WALL_SIGN.getKey(), TofuBlocks.LEEK_GREEN_WALL_SIGN.getKey(), TofuBlocks.LEEK_WALL_SIGN.getKey());
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(TofuBlocks.SPROUT_HANGING_SIGN.getKey(), TofuBlocks.TOFU_STEM_HANGING_SIGN.getKey(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.getKey(), TofuBlocks.LEEK_HANGING_SIGN.getKey());
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(TofuBlocks.SPROUT_WALL_HANGING_SIGN.getKey(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.getKey(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.getKey(), TofuBlocks.LEEK_WALL_HANGING_SIGN.getKey());


		this.tag(BlockTags.SMALL_FLOWERS).add(TofuBlocks.TOFU_FLOWER.getKey());
		this.tag(BlockTags.BEE_GROWABLES).add(TofuBlocks.SOYBEAN.getKey()).add(TofuBlocks.SOYBEAN_NETHER.getKey()).add(TofuBlocks.SOYBEAN_SOUL.getKey()).add(TofuBlocks.SOYBEAN_PALE.getKey())
				.add(TofuBlocks.RICE_CROP.getKey());
		this.tag(BlockTags.MAINTAINS_FARMLAND).add(TofuBlocks.SOYBEAN.getKey());
		this.tag(Tags.Blocks.ORES).add(TofuBlocks.ORE_TOFUGEM.getKey(), TofuBlocks.ORE_TOFU_DIAMOND.getKey(), TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.getKey(), TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.getKey());

		tag(TofuTags.Blocks.TOFUNIAN_SMITH).add(Blocks.BLAST_FURNACE.builtInRegistryHolder().key());
		tag(TofuTags.Blocks.TOFUNIAN_FARMER).add(Blocks.COMPOSTER.builtInRegistryHolder().key());
		tag(TofuTags.Blocks.TOFUNIAN_SOY_WORKER).add(Blocks.CAULDRON.builtInRegistryHolder().key()).add(Blocks.WATER_CAULDRON.builtInRegistryHolder().key()).add(TofuBlocks.SOYMILK_CAULDRON.getKey());
		tag(TofuTags.Blocks.TOFUNIAN_ENGINEER).add(TofuBlocks.TOFU_WORK_STATION.getKey());
	}
}