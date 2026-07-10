package baguchi.tofucraft.data.generator.tags;

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

public class TofuBlockTags extends BlockTagsProvider {
	public TofuBlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, TofuCraftReload.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(TofuBlocks.KINUTOFU.get(), TofuBlocks.MOMENTOFU.get(), TofuBlocks.HELLTOFU.get(), TofuBlocks.SOULTOFU.get()
						, TofuBlocks.GRILLED_TOFU.get(), TofuBlocks.ZUNDATOFU.get()
						, TofuBlocks.TOFU_STAIR_KINU.get(), TofuBlocks.TOFU_STAIR_MOMEN.get(), TofuBlocks.TOFU_STAIR_ZUNDA.get(), TofuBlocks.TOFU_STAIR_HELL.get(), TofuBlocks.TOFU_STAIR_SOUL.get()
						, TofuBlocks.TOFU_SLAB_KINU.get(), TofuBlocks.TOFU_SLAB_MOMEN.get(), TofuBlocks.TOFU_SLAB_ZUNDA.get(), TofuBlocks.TOFU_SLAB_HELL.get(), TofuBlocks.TOFU_SLAB_SOUL.get()
						, TofuBlocks.TOFU_LADDER_KINU.get(), TofuBlocks.TOFU_LADDER_MOMEN.get()
						, TofuBlocks.TOFU_FENCE_KINU.get(), TofuBlocks.TOFU_FENCE_MOMEN.get(), TofuBlocks.TOFU_FENCE_HELL.get(), TofuBlocks.TOFU_FENCE_SOUL.get()
						, TofuBlocks.TOFU_DOOR_KINU.get(), TofuBlocks.TOFU_DOOR_MOMEN.get(), TofuBlocks.TOFU_DOOR_HELL.get(), TofuBlocks.TOFU_DOOR_SOUL.get()
						, TofuBlocks.TOFU_DOOR_ZUNDA.get(), TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get(), TofuBlocks.TOFU_FENCE_ZUNDA.get()
						, TofuBlocks.TOFU_DOOR_MISO.get(), TofuBlocks.TOFU_TRAPDOOR_MISO.get(), TofuBlocks.TOFU_FENCE_MISO.get()
						, TofuBlocks.TOFU_DOOR_DRIED.get(), TofuBlocks.TOFU_TRAPDOOR_DRIED.get(), TofuBlocks.TOFU_FENCE_DRIED.get()
						, TofuBlocks.TOFU_DOOR_EGG.get(), TofuBlocks.TOFU_TRAPDOOR_EGG.get(), TofuBlocks.TOFU_FENCE_EGG.get()
						, TofuBlocks.TOFU_DOOR_SESAME.get(), TofuBlocks.TOFU_TRAPDOOR_SESAME.get(), TofuBlocks.TOFU_FENCE_SESAME.get()
						, TofuBlocks.TOFU_DOOR_STRAWBERRY.get(), TofuBlocks.TOFU_TRAPDOOR_STRAWBERRY.get(), TofuBlocks.TOFU_FENCE_STRAWBERRY.get()
						, TofuBlocks.TOFU_DOOR_FRIED.get(), TofuBlocks.TOFU_TRAPDOOR_FRIED.get(), TofuBlocks.TOFU_FENCE_FRIED.get()
						, TofuBlocks.TOFU_DOOR_FRIED_POUCH.get(), TofuBlocks.TOFU_TRAPDOOR_FRIED_POUCH.get(), TofuBlocks.TOFU_FENCE_FRIED_POUCH.get()
						, TofuBlocks.TOFU_TRAPDOOR_KINU.get(), TofuBlocks.TOFU_TRAPDOOR_MOMEN.get(), TofuBlocks.TOFU_TRAPDOOR_HELL.get(), TofuBlocks.TOFU_TRAPDOOR_SOUL.get()
						, TofuBlocks.TOFU_TERRAIN.get(), TofuBlocks.MABOU_TERRAIN.get(), TofuBlocks.TOFU_TERRAIN_ZUNDA.get(), TofuBlocks.ORE_TOFU_DIAMOND.get(), TofuBlocks.ORE_TOFUGEM.get()
						, TofuBlocks.EGGTOFU.get(), TofuBlocks.TOFU_STAIR_EGG.get(), TofuBlocks.TOFU_SLAB_EGG.get()
						, TofuBlocks.SESAMETOFU.get(), TofuBlocks.TOFU_STAIR_SESAME.get(), TofuBlocks.TOFU_SLAB_SESAME.get()
						, TofuBlocks.MISOTOFU.get(), TofuBlocks.TOFU_STAIR_MISO.get(), TofuBlocks.TOFU_SLAB_MISO.get()
						, TofuBlocks.STRAWBERRY_TOFU.get(), TofuBlocks.TOFU_STAIR_STRAWBERRY.get(), TofuBlocks.TOFU_SLAB_STRAWBERRY.get()
						, TofuBlocks.FRIED_TOFU.get(), TofuBlocks.TOFU_STAIR_FRIED.get(), TofuBlocks.TOFU_SLAB_FRIED.get()
						, TofuBlocks.FRIED_POUCH_TOFU.get(), TofuBlocks.TOFU_STAIR_FRIED_POUCH.get(), TofuBlocks.TOFU_SLAB_FRIED_POUCH.get()
						, TofuBlocks.ZUNDATOFU.get(), TofuBlocks.TOFU_STAIR_ZUNDA.get(), TofuBlocks.TOFU_SLAB_ZUNDA.get(), TofuBlocks.MISOTOFU.get(), TofuBlocks.TOFU_STAIR_MISO.get(), TofuBlocks.TOFU_SLAB_MISO.get()
						, TofuBlocks.DRIEDTOFU.get(), TofuBlocks.TOFU_STAIR_DRIED.get(), TofuBlocks.TOFU_SLAB_DRIED.get(), TofuBlocks.MINCEDTOFU.get())
				.add(TofuBlocks.SALT_BLOCK.get())
				.add(TofuBlocks.OKARA_BLOCK.get())
				.add(TofuBlocks.WAXED_KINUTOFU.get(), TofuBlocks.WAXED_MOMENTOFU.get());
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TofuBlocks.ISHITOFU.get(), TofuBlocks.ISHITOFU_BRICK.get(), TofuBlocks.ISHITOFU_SMOOTH_BRICK.get(), TofuBlocks.ISHITOFU_CHISELED_BRICK.get()
						, TofuBlocks.METALTOFU.get(), TofuBlocks.METAL_TOFU_GRATE.get(), TofuBlocks.METAL_TOFU_LUMP.get(), TofuBlocks.METAL_TOFU_BARS.get(), TofuBlocks.DIAMONDTOFU.get(), TofuBlocks.TOFU_GEM_BLOCK.get(), TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get()
						, TofuBlocks.HELLTOFU_BRICK.get(), TofuBlocks.HELLTOFU_SMOOTH_BRICK.get(), TofuBlocks.HELLTOFU_CHISELED_BRICK.get()
						, TofuBlocks.SOULTOFU_BRICK.get(), TofuBlocks.SOULTOFU_SMOOTH_BRICK.get(), TofuBlocks.SOULTOFU_CHISELED_BRICK.get()
						, TofuBlocks.DRIEDTOFU_BRICK.get(), TofuBlocks.DRIEDTOFU_SMOOTH_BRICK.get(), TofuBlocks.DRIEDTOFU_CHISELED_BRICK.get(), TofuBlocks.TOFU_STAIR_DRIEDBRICK.get(), TofuBlocks.TOFU_SLAB_DRIEDBRICK.get()
						, TofuBlocks.EGGTOFU_BRICK.get(), TofuBlocks.EGGTOFU_SMOOTH_BRICK.get(), TofuBlocks.EGGTOFU_CHISELED_BRICK.get(), TofuBlocks.TOFU_STAIR_EGGBRICK.get(), TofuBlocks.TOFU_SLAB_EGGBRICK.get()
						, TofuBlocks.ZUNDATOFU_BRICK.get(), TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get(), TofuBlocks.TOFU_STAIR_ZUNDABRICK.get(), TofuBlocks.TOFU_SLAB_ZUNDABRICK.get()
						, TofuBlocks.TOFU_STAIR_ISHI.get(), TofuBlocks.TOFU_STAIR_METAL.get(), TofuBlocks.TOFU_STAIR_ISHIBRICK.get(), TofuBlocks.TOFU_STAIR_HELLBRICK.get(), TofuBlocks.TOFU_STAIR_SOULBRICK.get()
						, TofuBlocks.TOFU_SLAB_ISHI.get(), TofuBlocks.TOFU_SLAB_METAL.get(), TofuBlocks.TOFU_SLAB_ISHIBRICK.get(), TofuBlocks.TOFU_SLAB_HELLBRICK.get(), TofuBlocks.TOFU_SLAB_SOULBRICK.get()
						, TofuBlocks.TOFU_LADDER_ISHI.get(), TofuBlocks.TOFU_LADDER_ISHIBRICK.get(), TofuBlocks.TOFU_LADDER_METAL.get()
						, TofuBlocks.TOFU_FENCE_ISHI.get(), TofuBlocks.TOFU_FENCE_METAL.get()
						, TofuBlocks.TOFU_DOOR_ISHI.get(), TofuBlocks.TOFU_DOOR_METAL.get()
						, TofuBlocks.TOFU_TRAPDOOR_ISHI.get(), TofuBlocks.TOFU_TRAPDOOR_METAL.get()
				, TofuBlocks.TOFUCHEST.get()
				, TofuBlocks.TOFU_BEDROCK.get()
				, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get()
				, TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.get()
				, TofuBlocks.TOFUSLATE.get()
						, TofuBlocks.ISHI_TOFU_STEM.get()
				, TofuBlocks.SALT_FURNACE.get()
				, TofuBlocks.SOYMILK_CAULDRON.get(), TofuBlocks.SOYMILK_NETHER_CAULDRON.get(), TofuBlocks.SOYMILK_SOUL_CAULDRON.get()
				, TofuBlocks.TOFU_METAL_LANTERN.get(), TofuBlocks.TOFU_METAL_SOUL_LANTERN.get(), TofuBlocks.TOFU_METAL_CHAIN.get()
						, TofuBlocks.TOFU_DETECTOR.get(), TofuBlocks.TF_STORAGE.get(), TofuBlocks.TF_OVEN.get(), TofuBlocks.TF_TOFU_MAKER.get(), TofuBlocks.ANTENNA_BASIC.get(), TofuBlocks.ANTENNA_ADVANCE.get(), TofuBlocks.TOFU_WORK_STATION.get(), TofuBlocks.TF_COLLECTOR.get()
				, TofuBlocks.TOFUNIAN_STATUE.get()
				, TofuBlocks.TOFU_POT.get()
						, TofuBlocks.TF_CRAFTING_TABLE.get(), TofuBlocks.WAXED_ISHITOFU.get(), TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.get())
				.add(TofuBlocks.FOODPLATE.get());
		tag(BlockTags.MINEABLE_WITH_AXE).add(TofuBlocks.SALTPAN.get()
						, TofuBlocks.SPROUT_STEM.get(), TofuBlocks.YELLOW_SPROUT_STEM.get()
				, TofuBlocks.TOFU_STEM.get(), TofuBlocks.TOFU_STEM_PLANKS.get(), TofuBlocks.TOFU_STEM_PLANKS_STAIR.get(), TofuBlocks.TOFU_STEM_PLANKS_SLAB.get(), TofuBlocks.TOFU_STEM_FENCE.get(), TofuBlocks.TOFU_STEM_FENCE_GATE.get(), TofuBlocks.TOFU_STEM_DOOR.get(), TofuBlocks.TOFU_STEM_TRAPDOOR.get()
				, TofuBlocks.LEEK_STEM.get(), TofuBlocks.LEEK_PLANKS.get(), TofuBlocks.LEEK_PLANKS_STAIR.get(), TofuBlocks.LEEK_PLANKS_SLAB.get(), TofuBlocks.LEEK_FENCE.get(), TofuBlocks.LEEK_FENCE_GATE.get()
						, TofuBlocks.LEEK_GREEN_STEM.get(), TofuBlocks.LEEK_GREEN_PLANKS.get(), TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get(), TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get(), TofuBlocks.LEEK_GREEN_FENCE.get(), TofuBlocks.LEEK_GREEN_FENCE_GATE.get(), TofuBlocks.LEEK_GREEN_DOOR.get(), TofuBlocks.LEEK_GREEN_TRAPDOOR.get(), TofuBlocks.LEEK_DOOR.get(), TofuBlocks.LEEK_TRAPDOOR.get()
						, TofuBlocks.SPROUT_STEM.get(), TofuBlocks.SPROUT_PLANKS.get(), TofuBlocks.SPROUT_PLANKS_STAIR.get(), TofuBlocks.SPROUT_PLANKS_SLAB.get(), TofuBlocks.SPROUT_FENCE.get(), TofuBlocks.SPROUT_FENCE_GATE.get(), TofuBlocks.SPROUT_DOOR.get(), TofuBlocks.SPROUT_TRAPDOOR.get(), TofuBlocks.LEEK_DOOR.get(), TofuBlocks.LEEK_TRAPDOOR.get()
				, TofuBlocks.MORIJIO.get()
				, TofuBlocks.BARREL_MISO.get(), TofuBlocks.BARREL_MISOTOFU.get(), TofuBlocks.BARREL_ADV_TOFUGEM.get()
						, TofuBlocks.SPROUT_SIGN.get(), TofuBlocks.SPROUT_WALL_SIGN.get(), TofuBlocks.SPROUT_HANGING_SIGN.get(), TofuBlocks.SPROUT_WALL_HANGING_SIGN.get()
				, TofuBlocks.TOFU_STEM_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_SIGN.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get()
				, TofuBlocks.LEEK_GREEN_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get()
						, TofuBlocks.LEEK_SIGN.get(), TofuBlocks.LEEK_WALL_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get())
				.add(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.get(), TofuBlocks.TOFU_STEM_BUTTON.get())
				.add(TofuBlocks.LEEK_PRESSURE_PLATE.get(), TofuBlocks.LEEK_BUTTON.get())
				.add(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.get(), TofuBlocks.LEEK_GREEN_BUTTON.get())
				.add(TofuBlocks.SPROUT_PRESSURE_PLATE.get(), TofuBlocks.SPROUT_BUTTON.get())
				.add(
						TofuBlocks.LEEK_GREEN_SHELF.get(),
						TofuBlocks.LEEK_SHELF.get(),
						TofuBlocks.TOFU_STEM_SHELF.get(),
						TofuBlocks.SPROUT_SHELF.get()
				);
		tag(BlockTags.LANTERNS).add(TofuBlocks.TOFU_METAL_LANTERN.get()).add(TofuBlocks.TOFU_METAL_SOUL_LANTERN.get());
		tag(BlockTags.CHAINS).add(TofuBlocks.TOFU_METAL_CHAIN.get());
		tag(BlockTags.MINEABLE_WITH_HOE).add(TofuBlocks.GIANT_OKARA_DONUT.get()).add(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get()).add(TofuBlocks.RICE_ROOT.get(), TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.RICE_BLOCK.get(), TofuBlocks.SOYBEANS_SEEDS_BLOCK.get(), TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get(), TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get())
				.add(TofuBlocks.SOY_CHEESE_BLOCK.get()).add(TofuBlocks.SOY_NETHER_CHEESE_BLOCK.get()).add(TofuBlocks.SOY_SOUL_CHEESE_BLOCK.get());

		tag(BlockTags.NEEDS_STONE_TOOL)
				.add(TofuBlocks.METALTOFU.get(), TofuBlocks.TOFU_STAIR_METAL.get(), TofuBlocks.TOFU_SLAB_METAL.get(), TofuBlocks.TOFU_LADDER_METAL.get(), TofuBlocks.TOFU_FENCE_METAL.get(), TofuBlocks.TOFU_DOOR_METAL.get(), TofuBlocks.TOFU_TRAPDOOR_METAL.get())
				.add(TofuBlocks.TOFU_GEM_BLOCK.get()).add(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get()).add(TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.get());
		tag(BlockTags.NEEDS_IRON_TOOL)
				.add(TofuBlocks.DIAMONDTOFU.get(), TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		tag(BlockTags.PIGLIN_REPELLENTS).add(TofuBlocks.SOYBEAN_SOUL.get());

		tag(TofuTags.Blocks.TOFU_FARMLANDS).add(TofuBlocks.TOFU_FARMLAND.get());

		tag(TofuTags.Blocks.HAS_INFO).add(TofuBlocks.SALT_FURNACE.get()).add(TofuBlocks.SALTPAN.get());
		tag(TofuTags.Blocks.PICKABLE_TOFU).add(TofuBlocks.KINUTOFU.get(), TofuBlocks.MOMENTOFU.get(), TofuBlocks.HELLTOFU.get(), TofuBlocks.SOULTOFU.get()
				, TofuBlocks.GRILLED_TOFU.get(), TofuBlocks.ZUNDATOFU.get(), TofuBlocks.MINCEDTOFU.get()).add(TofuBlocks.METALTOFU.get()).add(TofuBlocks.ISHITOFU.get());

		tag(BlockTags.REPLACEABLE).add(TofuBlocks.LEEK.get()).add(TofuBlocks.TALL_LEEK.get());
		tag(TofuTags.Blocks.TF_TRANSMITTER).addTag(BlockTags.REPLACEABLE).add(TofuBlocks.ANTENNA_BASIC.get()).add(TofuBlocks.ANTENNA_ADVANCE.get());

		tag(BlockTags.ENABLES_BUBBLE_COLUMN_DRAG_DOWN).add(TofuBlocks.MABOU_TERRAIN.get());


		tag(BlockTags.BEDS).add(TofuBlocks.TOFUBED.get());
		tag(BlockTags.WALLS).add(TofuBlocks.TOFU_FENCE_KINU.get(), TofuBlocks.TOFU_FENCE_MOMEN.get(), TofuBlocks.TOFU_FENCE_HELL.get(), TofuBlocks.TOFU_FENCE_SOUL.get(), TofuBlocks.TOFU_FENCE_ISHI.get(), TofuBlocks.TOFU_FENCE_METAL.get())
				.add(TofuBlocks.TOFU_FENCE_GRILLED.get(), TofuBlocks.TOFU_FENCE_ZUNDA.get())
				.add(TofuBlocks.TOFU_FENCE_FRIED.get(), TofuBlocks.TOFU_FENCE_FRIED_POUCH.get(), TofuBlocks.TOFU_FENCE_STRAWBERRY.get(), TofuBlocks.TOFU_FENCE_ZUNDA.get())
				.add(TofuBlocks.TOFU_FENCE_MISO.get(), TofuBlocks.TOFU_FENCE_DRIED.get(), TofuBlocks.TOFU_FENCE_EGG.get(), TofuBlocks.TOFU_FENCE_SESAME.get());
		tag(BlockTags.CLIMBABLE).add(TofuBlocks.TOFU_LADDER_KINU.get(), TofuBlocks.TOFU_LADDER_MOMEN.get(), TofuBlocks.TOFU_LADDER_ISHI.get(), TofuBlocks.TOFU_LADDER_ISHIBRICK.get(), TofuBlocks.TOFU_LADDER_METAL.get(), TofuBlocks.TOFU_LADDER_GRILLED.get(), TofuBlocks.TOFU_LADDER_ZUNDA.get(), TofuBlocks.TOFU_LADDER_HELL.get(), TofuBlocks.TOFU_LADDER_SOUL.get());
		tag(BlockTags.WITHER_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.get());
		tag(BlockTags.DRAGON_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.get());
		tag(BlockTags.FEATURES_CANNOT_REPLACE).add(TofuBlocks.TOFU_BEDROCK.get());
		tag(BlockTags.TRAPDOORS).add(TofuBlocks.TOFU_TRAPDOOR_KINU.get(), TofuBlocks.TOFU_TRAPDOOR_MOMEN.get(), TofuBlocks.TOFU_TRAPDOOR_HELL.get(), TofuBlocks.TOFU_TRAPDOOR_SOUL.get(), TofuBlocks.TOFU_TRAPDOOR_ISHI.get(), TofuBlocks.TOFU_TRAPDOOR_METAL.get(), TofuBlocks.TOFU_TRAPDOOR_GRILLED.get(), TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get()
				, TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get(), TofuBlocks.TOFU_TRAPDOOR_STRAWBERRY.get(), TofuBlocks.TOFU_TRAPDOOR_FRIED.get(), TofuBlocks.TOFU_TRAPDOOR_FRIED_POUCH.get());
		tag(BlockTags.WOODEN_DOORS).add(TofuBlocks.TOFU_DOOR_KINU.get(), TofuBlocks.TOFU_DOOR_MOMEN.get(), TofuBlocks.TOFU_DOOR_HELL.get(), TofuBlocks.TOFU_DOOR_SOUL.get(), TofuBlocks.TOFU_DOOR_ISHI.get(), TofuBlocks.TOFU_DOOR_GRILLED.get(), TofuBlocks.TOFU_DOOR_ZUNDA.get()
						, TofuBlocks.TOFU_DOOR_ZUNDA.get(), TofuBlocks.TOFU_DOOR_STRAWBERRY.get(), TofuBlocks.TOFU_DOOR_FRIED.get(), TofuBlocks.TOFU_DOOR_FRIED_POUCH.get())
				.add(TofuBlocks.TOFU_DOOR_MISO.get(), TofuBlocks.TOFU_DOOR_DRIED.get(), TofuBlocks.TOFU_DOOR_EGG.get(), TofuBlocks.TOFU_DOOR_SESAME.get())
				.add(TofuBlocks.SPROUT_DOOR.get(), TofuBlocks.TOFU_STEM_DOOR.get(), TofuBlocks.LEEK_GREEN_DOOR.get(), TofuBlocks.LEEK_DOOR.get());
		tag(BlockTags.DOORS).add(TofuBlocks.TOFU_DOOR_METAL.get()).add(TofuBlocks.TOFU_DOOR_KINU.get(), TofuBlocks.TOFU_DOOR_MOMEN.get(), TofuBlocks.TOFU_DOOR_HELL.get(), TofuBlocks.TOFU_DOOR_SOUL.get(), TofuBlocks.TOFU_DOOR_ISHI.get(), TofuBlocks.TOFU_DOOR_GRILLED.get(), TofuBlocks.TOFU_DOOR_ZUNDA.get())
				.add(TofuBlocks.SPROUT_DOOR.get(), TofuBlocks.TOFU_STEM_DOOR.get(), TofuBlocks.LEEK_GREEN_DOOR.get(), TofuBlocks.LEEK_DOOR.get());

		this.tag(BlockTags.WOODEN_SHELVES)
				.add(
						TofuBlocks.LEEK_GREEN_SHELF.get(),
						TofuBlocks.LEEK_SHELF.get(),
						TofuBlocks.TOFU_STEM_SHELF.get()
				);

		tag(BlockTags.WOODEN_TRAPDOORS).add(TofuBlocks.TOFU_TRAPDOOR_KINU.get(), TofuBlocks.TOFU_TRAPDOOR_MOMEN.get(), TofuBlocks.TOFU_TRAPDOOR_HELL.get(), TofuBlocks.TOFU_TRAPDOOR_SOUL.get(), TofuBlocks.TOFU_TRAPDOOR_ISHI.get(), TofuBlocks.TOFU_TRAPDOOR_GRILLED.get(), TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get())
				.add(TofuBlocks.TOFU_TRAPDOOR_MISO.get(), TofuBlocks.TOFU_TRAPDOOR_DRIED.get(), TofuBlocks.TOFU_TRAPDOOR_EGG.get(), TofuBlocks.TOFU_TRAPDOOR_SESAME.get())
				.add(TofuBlocks.SPROUT_TRAPDOOR.get(), TofuBlocks.TOFU_STEM_TRAPDOOR.get(), TofuBlocks.LEEK_GREEN_TRAPDOOR.get(), TofuBlocks.LEEK_TRAPDOOR.get());
		tag(BlockTags.TRAPDOORS).add(TofuBlocks.TOFU_TRAPDOOR_METAL.get()).add(TofuBlocks.TOFU_TRAPDOOR_KINU.get(), TofuBlocks.TOFU_TRAPDOOR_MOMEN.get(), TofuBlocks.TOFU_TRAPDOOR_HELL.get(), TofuBlocks.TOFU_TRAPDOOR_SOUL.get(), TofuBlocks.TOFU_TRAPDOOR_ISHI.get(), TofuBlocks.TOFU_TRAPDOOR_GRILLED.get(), TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get())
				.add(TofuBlocks.SPROUT_TRAPDOOR.get(), TofuBlocks.TOFU_STEM_TRAPDOOR.get(), TofuBlocks.LEEK_GREEN_TRAPDOOR.get(), TofuBlocks.LEEK_TRAPDOOR.get());

		tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(TofuBlocks.SOULTOFU.get(), TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get());
		tag(BlockTags.INFINIBURN_OVERWORLD).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get());

		tag(BlockTags.SOUL_SPEED_BLOCKS).add(TofuBlocks.SOULTOFU.get());
		tag(BlockTags.CAULDRONS).add(TofuBlocks.SOYMILK_CAULDRON.get(), TofuBlocks.SOYMILK_NETHER_CAULDRON.get(), TofuBlocks.SOYMILK_SOUL_CAULDRON.get());

		tag(BlockTags.PLANKS).add(TofuBlocks.SPROUT_PLANKS.get()).add(TofuBlocks.TOFU_STEM_PLANKS.get()).add(TofuBlocks.LEEK_PLANKS.get()).add(TofuBlocks.LEEK_GREEN_PLANKS.get());
		tag(BlockTags.WOODEN_SLABS).add(TofuBlocks.SPROUT_PLANKS_SLAB.get()).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get()).add(TofuBlocks.LEEK_PLANKS_SLAB.get()).add(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get());
		tag(BlockTags.WOODEN_STAIRS).add(TofuBlocks.SPROUT_PLANKS_STAIR.get()).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get()).add(TofuBlocks.LEEK_PLANKS_STAIR.get()).add(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get());
		tag(BlockTags.WOODEN_FENCES).add(TofuBlocks.SPROUT_FENCE.get()).add(TofuBlocks.TOFU_STEM_FENCE.get()).add(TofuBlocks.LEEK_FENCE.get()).add(TofuBlocks.LEEK_GREEN_FENCE.get());
		tag(BlockTags.FENCE_GATES).add(TofuBlocks.SPROUT_FENCE_GATE.get()).add(TofuBlocks.TOFU_STEM_FENCE_GATE.get()).add(TofuBlocks.LEEK_FENCE_GATE.get()).add(TofuBlocks.LEEK_GREEN_FENCE_GATE.get());

		this.tag(BlockTags.FEATURES_CANNOT_REPLACE).add(TofuBlocks.TOFU_BEDROCK.get(), TofuBlocks.TOFUCHEST.get());
		this.tag(BlockTags.PORTALS).add(TofuBlocks.TOFU_PORTAL.get());

		tag(TofuTags.Blocks.TOFU_TERRAIN).add(TofuBlocks.TOFU_TERRAIN.get(), TofuBlocks.TOFU_TERRAIN_ZUNDA.get());


		tag(TofuTags.Blocks.SUBSTRATE_TOFU_WORLD).addTag(TofuTags.Blocks.TOFU_TERRAIN);

		tag(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE).addTag(TofuTags.Blocks.TOFU_TERRAIN).add(TofuBlocks.SOYMILK.get())
				.add(TofuBlocks.TOFUSLATE.get()).add(TofuBlocks.OKARA_BLOCK.get());

		this.tag(TofuTags.Blocks.SUPPORTS_TOFU_PLANT)
				.addTag(TofuTags.Blocks.SUBSTRATE_TOFU_WORLD)
				.add(TofuBlocks.MOMENTOFU.get());
		this.tag(TofuTags.Blocks.SUPPORTS_ROUGH_TOFU_PLANT)
				.addTag(TofuTags.Blocks.SUPPORTS_TOFU_PLANT)
				.add(TofuBlocks.MINCEDTOFU.get());

		tag(BlockTags.LEAVES).add(TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.LEAVES_TOFU.get());
		this.tag(BlockTags.REPLACEABLE_BY_TREES).add(TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.LEEK.get(), TofuBlocks.TALL_LEEK.get()).add(TofuBlocks.ZUNDA_TOFU_MUSHROOM.get());
		this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(TofuBlocks.TOFU_STEM.get()).add(TofuBlocks.LEEK_STEM.get()).add(TofuBlocks.LEEK_GREEN_STEM.get());

		tag(BlockTags.CROPS).add(TofuBlocks.RICE_CROP.get()).add(TofuBlocks.SOYBEAN.get()).add(TofuBlocks.SOYBEAN_NETHER.get()).add(TofuBlocks.SOYBEAN_SOUL.get()).add(TofuBlocks.SOYBEAN_PALE.get()).add(TofuBlocks.LEEK_CROP.get());
		tag(BlockTags.SAPLINGS).add(TofuBlocks.SAPLING_TOFU.get(), TofuBlocks.SAPLING_APRICOT.get());

		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.TOFU_CANDLE_CAKE.get(), TofuBlocks.WHITE_TOFU_CANDLE_CAKE.get(), TofuBlocks.ORANGE_TOFU_CANDLE_CAKE.get(), TofuBlocks.MAGENTA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.YELLOW_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIME_TOFU_CANDLE_CAKE.get(), TofuBlocks.PINK_TOFU_CANDLE_CAKE.get(), TofuBlocks.GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.CYAN_TOFU_CANDLE_CAKE.get(), TofuBlocks.PURPLE_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.BROWN_TOFU_CANDLE_CAKE.get(), TofuBlocks.GREEN_TOFU_CANDLE_CAKE.get(), TofuBlocks.RED_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLACK_TOFU_CANDLE_CAKE.get());
		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.WHITE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ORANGE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.MAGENTA_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.YELLOW_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIME_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.PINK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.CYAN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.PURPLE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.BROWN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.GREEN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.RED_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLACK_ZUNDA_TOFU_CANDLE_CAKE.get());
		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.SOYCHEESE_CANDLE_TART.get(), TofuBlocks.WHITE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.ORANGE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.MAGENTA_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.LIGHT_BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.YELLOW_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.LIME_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.PINK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.LIGHT_GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.CYAN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.PURPLE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.BROWN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.GREEN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.RED_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.BLACK_SOYCHEESE_CANDLE_TART.get());
		this.tag(BlockTags.STANDING_SIGNS).add(TofuBlocks.SPROUT_SIGN.get(), TofuBlocks.TOFU_STEM_SIGN.get(), TofuBlocks.LEEK_GREEN_SIGN.get(), TofuBlocks.LEEK_SIGN.get());
		this.tag(BlockTags.WALL_SIGNS).add(TofuBlocks.SPROUT_WALL_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_SIGN.get(), TofuBlocks.LEEK_WALL_SIGN.get());
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(TofuBlocks.SPROUT_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get());
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(TofuBlocks.SPROUT_WALL_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get());


		this.tag(BlockTags.SMALL_FLOWERS).add(TofuBlocks.TOFU_FLOWER.get());
		this.tag(BlockTags.BEE_GROWABLES).add(TofuBlocks.SOYBEAN.get()).add(TofuBlocks.SOYBEAN_NETHER.get()).add(TofuBlocks.SOYBEAN_SOUL.get()).add(TofuBlocks.SOYBEAN_PALE.get())
				.add(TofuBlocks.RICE_CROP.get());
		this.tag(BlockTags.MAINTAINS_FARMLAND).add(TofuBlocks.SOYBEAN.get());
		this.tag(Tags.Blocks.ORES).add(TofuBlocks.ORE_TOFUGEM.get(), TofuBlocks.ORE_TOFU_DIAMOND.get(), TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get(), TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.get());

		tag(TofuTags.Blocks.TOFUNIAN_SMITH).add(Blocks.BLAST_FURNACE);
		tag(TofuTags.Blocks.TOFUNIAN_FARMER).add(Blocks.COMPOSTER);
		tag(TofuTags.Blocks.TOFUNIAN_SOY_WORKER).add(Blocks.CAULDRON).add(Blocks.WATER_CAULDRON).add(TofuBlocks.SOYMILK_CAULDRON.get());
		tag(TofuTags.Blocks.TOFUNIAN_ENGINEER).add(TofuBlocks.TOFU_WORK_STATION.get());
	}
}