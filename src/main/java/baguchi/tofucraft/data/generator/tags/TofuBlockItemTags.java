package baguchi.tofucraft.data.generator.tags;

import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.stream.Stream;

public class TofuBlockItemTags {
	private final Function<BlockItemTagId, TofuCombinedAppender> tagSupplier;

	public TofuBlockItemTags(Function<BlockItemTagId, TofuBlockItemTags.TofuCombinedAppender> tagSupplier) {
		this.tagSupplier = tagSupplier;
	}


	public void run() {
		Block[] smallFlowersInteractingWithBees = new Block[]{TofuBlocks.TOFU_FLOWER.get()};
		this.tag(BlockItemTags.PLANKS).add(TofuBlocks.SPROUT_PLANKS.get()).add(TofuBlocks.TOFU_STEM_PLANKS.get()).add(TofuBlocks.LEEK_PLANKS.get()).add(TofuBlocks.LEEK_GREEN_PLANKS.get());

		tag(BlockItemTags.WOODEN_SLABS).add(TofuBlocks.SPROUT_PLANKS_SLAB.get()).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get()).add(TofuBlocks.LEEK_PLANKS_SLAB.get()).add(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get());
		tag(BlockItemTags.WOODEN_STAIRS).add(TofuBlocks.SPROUT_PLANKS_STAIR.get()).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get()).add(TofuBlocks.LEEK_PLANKS_STAIR.get()).add(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get());
		tag(BlockItemTags.WOODEN_FENCES).add(TofuBlocks.SPROUT_FENCE.get()).add(TofuBlocks.LEEK_FENCE.get()).add(TofuBlocks.LEEK_GREEN_FENCE.get()).add(TofuBlocks.TOFU_STEM_FENCE.get());
		tag(BlockItemTags.FENCE_GATES).add(TofuBlocks.SPROUT_FENCE_GATE.get()).add(TofuBlocks.TOFU_STEM_FENCE_GATE.get()).add(TofuBlocks.LEEK_FENCE_GATE.get()).add(TofuBlocks.LEEK_GREEN_FENCE_GATE.get());
		tag(BlockItemTags.WOODEN_DOORS).add(TofuBlocks.SPROUT_DOOR.get()).add(TofuBlocks.TOFU_STEM_DOOR.get()).add(TofuBlocks.LEEK_GREEN_DOOR.get()).add(TofuBlocks.LEEK_DOOR.get());
		tag(BlockItemTags.WOODEN_TRAPDOORS).add(TofuBlocks.SPROUT_TRAPDOOR.get()).add(TofuBlocks.TOFU_STEM_TRAPDOOR.get()).add(TofuBlocks.LEEK_GREEN_TRAPDOOR.get()).add(TofuBlocks.LEEK_TRAPDOOR.get());
		tag(BlockItemTags.WOODEN_PRESSURE_PLATES).add(TofuBlocks.SPROUT_PRESSURE_PLATE.get()).add(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.get()).add(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.get()).add(TofuBlocks.LEEK_PRESSURE_PLATE.get());
		tag(BlockItemTags.WOODEN_BUTTONS).add(TofuBlocks.SPROUT_BUTTON.get()).add(TofuBlocks.TOFU_STEM_BUTTON.get()).add(TofuBlocks.LEEK_GREEN_BUTTON.get()).add(TofuBlocks.LEEK_BUTTON.get());


		this.tag(BlockItemTags.WOODEN_SHELVES)
				.add(
						TofuBlocks.LEEK_GREEN_SHELF.get(),
						TofuBlocks.LEEK_SHELF.get(),
						TofuBlocks.TOFU_STEM_SHELF.get(),
						TofuBlocks.SPROUT_SHELF.get()
				);
		this.tag(BlockItemTags.SIGNS)
				.add(
						TofuBlocks.LEEK_SIGN.get(),
						TofuBlocks.LEEK_GREEN_SIGN.get(),
						TofuBlocks.TOFU_STEM_SIGN.get(),
						TofuBlocks.SPROUT_SIGN.get()
				);
		this.tag(BlockItemTags.HANGING_SIGNS)
				.add(
						TofuBlocks.LEEK_HANGING_SIGN.get(),
						TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(),
						TofuBlocks.TOFU_STEM_HANGING_SIGN.get(),
						TofuBlocks.SPROUT_HANGING_SIGN.get()
				);
		this.tag(BlockItemTags.SAPLINGS).add(BlockItemIds.OAK_SAPLING, BlockItemIds.SPRUCE_SAPLING, BlockItemIds.BIRCH_SAPLING, BlockItemIds.JUNGLE_SAPLING, BlockItemIds.ACACIA_SAPLING, BlockItemIds.DARK_OAK_SAPLING, BlockItemIds.PALE_OAK_SAPLING, BlockItemIds.AZALEA, BlockItemIds.FLOWERING_AZALEA, BlockItemIds.MANGROVE_PROPAGULE, BlockItemIds.CHERRY_SAPLING);

		this.tag(BlockItemTags.LOGS_THAT_BURN).add(TofuBlocks.SPROUT_STEM.get(), TofuBlocks.TOFU_STEM.get(), TofuBlocks.LEEK_STEM.get(), TofuBlocks.LEEK_GREEN_STEM.get());

		this.tag(BlockItemTags.SLABS).add(TofuBlocks.TOFU_SLAB_KINU.get(), TofuBlocks.TOFU_SLAB_MOMEN.get(), TofuBlocks.TOFU_SLAB_ISHI.get(), TofuBlocks.TOFU_SLAB_ISHIBRICK.get(), TofuBlocks.TOFU_SLAB_METAL.get()
				, TofuBlocks.TOFU_SLAB_HELL.get(), TofuBlocks.TOFU_SLAB_HELLBRICK.get(), TofuBlocks.TOFU_SLAB_SOUL.get(), TofuBlocks.TOFU_SLAB_SOULBRICK.get()
				, TofuBlocks.TOFU_SLAB_MISO.get(), TofuBlocks.TOFU_SLAB_DRIED.get(), TofuBlocks.TOFU_SLAB_DRIEDBRICK.get(), TofuBlocks.TOFU_SLAB_EGG.get(), TofuBlocks.TOFU_SLAB_EGGBRICK.get(), TofuBlocks.TOFU_SLAB_SESAME.get()
				, TofuBlocks.TOFU_SLAB_ZUNDA.get(), TofuBlocks.TOFU_SLAB_ZUNDABRICK.get()
				, TofuBlocks.TOFU_SLAB_STRAWBERRY.get(), TofuBlocks.TOFU_SLAB_FRIED.get(), TofuBlocks.TOFU_SLAB_FRIED_POUCH.get());
		this.tag(BlockItemTags.STAIRS).add(TofuBlocks.TOFU_STAIR_KINU.get(), TofuBlocks.TOFU_STAIR_MOMEN.get(), TofuBlocks.TOFU_STAIR_ISHI.get(), TofuBlocks.TOFU_STAIR_ISHIBRICK.get(), TofuBlocks.TOFU_STAIR_METAL.get()
				, TofuBlocks.TOFU_STAIR_HELL.get(), TofuBlocks.TOFU_STAIR_HELLBRICK.get(), TofuBlocks.TOFU_STAIR_SOUL.get(), TofuBlocks.TOFU_STAIR_SOULBRICK.get()
				, TofuBlocks.TOFU_STAIR_MISO.get(), TofuBlocks.TOFU_STAIR_DRIED.get(), TofuBlocks.TOFU_STAIR_DRIEDBRICK.get(), TofuBlocks.TOFU_STAIR_EGG.get(), TofuBlocks.TOFU_STAIR_EGGBRICK.get(), TofuBlocks.TOFU_STAIR_SESAME.get()
				, TofuBlocks.TOFU_STAIR_ZUNDA.get(), TofuBlocks.TOFU_STAIR_ZUNDABRICK.get()
				, TofuBlocks.TOFU_STAIR_STRAWBERRY.get(), TofuBlocks.TOFU_STAIR_FRIED.get(), TofuBlocks.TOFU_STAIR_FRIED_POUCH.get());
		this.tag(BlockItemTags.LEAVES).add(TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.LEAVES_APRICOT.get());
		this.tag(BlockItemTags.TRAPDOORS).add(TofuBlocks.TOFU_TRAPDOOR_KINU.get(), TofuBlocks.TOFU_TRAPDOOR_MOMEN.get(), TofuBlocks.TOFU_TRAPDOOR_ISHI.get(), TofuBlocks.TOFU_TRAPDOOR_METAL.get()
				, TofuBlocks.TOFU_TRAPDOOR_HELL.get(), TofuBlocks.TOFU_TRAPDOOR_SOUL.get()
				, TofuBlocks.TOFU_TRAPDOOR_MISO.get(), TofuBlocks.TOFU_TRAPDOOR_DRIED.get(), TofuBlocks.TOFU_TRAPDOOR_EGG.get(), TofuBlocks.TOFU_TRAPDOOR_SESAME.get()
				, TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get(), TofuBlocks.TOFU_DOOR_STRAWBERRY.get(), TofuBlocks.TOFU_DOOR_FRIED.get(), TofuBlocks.TOFU_DOOR_FRIED_POUCH.get());
		this.tag(BlockItemTags.DOORS).add(TofuBlocks.TOFU_DOOR_KINU.get(), TofuBlocks.TOFU_DOOR_MOMEN.get(), TofuBlocks.TOFU_DOOR_ISHI.get(), TofuBlocks.TOFU_DOOR_METAL.get()
				, TofuBlocks.TOFU_DOOR_HELL.get(), TofuBlocks.TOFU_DOOR_SOUL.get()
				, TofuBlocks.TOFU_DOOR_MISO.get(), TofuBlocks.TOFU_DOOR_DRIED.get(), TofuBlocks.TOFU_DOOR_EGG.get(), TofuBlocks.TOFU_DOOR_SESAME.get()
				, TofuBlocks.TOFU_DOOR_ZUNDA.get(), TofuBlocks.TOFU_DOOR_STRAWBERRY.get(), TofuBlocks.TOFU_DOOR_FRIED.get(), TofuBlocks.TOFU_DOOR_FRIED_POUCH.get());
		this.tag(BlockItemTags.SMALL_FLOWERS).add(smallFlowersInteractingWithBees);

		this.tag(BlockItemTags.BEDS).add(TofuBlocks.TOFUBED.get());
		this.tag(BlockItemTags.SOUL_FIRE_BASE_BLOCKS).add(TofuBlocks.SOULTOFU.get(), TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get());

		this.tag(BlockItemTags.CHAINS).add(TofuBlocks.TOFU_METAL_CHAIN.get());
		this.tag(BlockItemTags.LANTERNS).add(TofuBlocks.TOFU_METAL_LANTERN.get(), TofuBlocks.TOFU_METAL_SOUL_LANTERN.get());
		this.tag(BlockItemTags.BARS).add(TofuBlocks.METAL_TOFU_BARS.get());
		this.tag(BlockItemTags.HANGING_SIGNS).add(BlockItemIds.OAK_HANGING_SIGN, BlockItemIds.SPRUCE_HANGING_SIGN, BlockItemIds.BIRCH_HANGING_SIGN, BlockItemIds.ACACIA_HANGING_SIGN, BlockItemIds.CHERRY_HANGING_SIGN, BlockItemIds.JUNGLE_HANGING_SIGN, BlockItemIds.DARK_OAK_HANGING_SIGN, BlockItemIds.PALE_OAK_HANGING_SIGN, BlockItemIds.CRIMSON_HANGING_SIGN, BlockItemIds.WARPED_HANGING_SIGN, BlockItemIds.MANGROVE_HANGING_SIGN, BlockItemIds.BAMBOO_HANGING_SIGN);
		this.tag(BlockItemTags.BEE_FOOD).add(smallFlowersInteractingWithBees);

	}

	public static TofuBlockItemTags.TofuCombinedAppender wrapForBlocks(final TagAppender<Block> appender) {
		return new TofuBlockItemTags.TofuCombinedAppender() {
			public TofuBlockItemTags.TofuCombinedAppender addAll(Stream<BlockItemId> ids) {
				appender.addAll(ids.map(BlockItemId::block));
				return this;
			}

			public TofuBlockItemTags.TofuCombinedAppender addTag(BlockItemTagId id) {
				appender.addTag(id.block());
				return this;
			}
		};
	}

	public static TofuBlockItemTags.TofuCombinedAppender wrapForItems(final TagAppender<Item> appender) {
		return new TofuBlockItemTags.TofuCombinedAppender() {
			public TofuBlockItemTags.TofuCombinedAppender addAll(Stream<BlockItemId> ids) {
				appender.addAll(ids.map(BlockItemId::item));
				return this;
			}

			public TofuBlockItemTags.TofuCombinedAppender addTag(BlockItemTagId id) {
				appender.addTag(id.item());
				return this;
			}
		};
	}

	protected TofuCombinedAppender tag(BlockItemTagId tag) {
		return this.tagSupplier.apply(tag);
	}

	public interface TofuCombinedAppender extends BlockItemTagsProvider.CombinedAppender {

		default TofuCombinedAppender add(Block... items) {
			for (Block item : items) {
				Identifier id = BuiltInRegistries.BLOCK.wrapAsHolder(item).getKey().identifier();

				add(BlockItemId.create(id, id));
			}
			return this;
		}

	}
}
