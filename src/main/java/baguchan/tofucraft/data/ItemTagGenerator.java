package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
	public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> provider, ExistingFileHelper exFileHelper) {
		super(packOutput, lookupProvider, provider, TofuCraftReload.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(TofuTags.Items.DUST_SALT).add(TofuItems.SALT.get());
		tag(TofuTags.Items.SALT).add(TofuItems.SALT.get());
		tag(TofuTags.Items.SOYBEAN).add(TofuItems.SEEDS_SOYBEANS.get());
		tag(TofuTags.Items.SOYMILK).addTag(TofuTags.Items.MILK_SOYMILK);
		tag(TofuTags.Items.MILK_SOYMILK).add(TofuItems.BUCKET_SOYMILK.get(), TofuItems.SOYMILK.get());
		tag(TofuTags.Items.RICE).add(TofuItems.RICE.get());
		tag(ItemTags.LOGS_THAT_BURN).add(TofuBlocks.TOFU_STEM.get().asItem(), TofuBlocks.LEEK_STEM.get().asItem(), TofuBlocks.LEEK_GREEN_STEM.get().asItem());
		tag(ItemTags.PLANKS).add(TofuBlocks.TOFU_STEM_PLANKS.get().asItem()).add(TofuBlocks.LEEK_PLANKS.get().asItem()).add(TofuBlocks.LEEK_GREEN_PLANKS.get().asItem());
		tag(ItemTags.ARROWS).add(TofuItems.ZUNDA_ARROW.get());
		tag(Tags.Items.SEEDS).add(TofuItems.SEEDS_CHILI.get(), TofuItems.SEEDS_SOYBEANS.get(), TofuItems.SEEDS_SOYBEANS_NETHER.get(), TofuItems.SEEDS_SOYBEANS_SOUL.get(), TofuItems.SEEDS_RICE.get());
		tag(TofuTags.Items.RICE_BLOCK).add(TofuBlocks.RICE_BLOCK.get().asItem());
		tag(ItemTags.TRIMMABLE_ARMOR).add(TofuItems.TOFU_DIAMOND_HELMET.get(), TofuItems.TOFU_DIAMOND_CHESTPLATE.get(), TofuItems.TOFU_DIAMOND_LEGGINGS.get(), TofuItems.TOFU_DIAMOND_BOOTS.get())
				.add(TofuItems.TOFU_DIAMOND_HELMET.get(), TofuItems.TOFU_METAL_CHESTPLATE.get(), TofuItems.TOFU_METAL_LEGGINGS.get(), TofuItems.TOFU_METAL_BOOTS.get())
				.add(TofuItems.SCULK_BONE_HELMET.get(), TofuItems.SCULK_BONE_CHESTPLATE.get(), TofuItems.SCULK_BONE_LEGGINGS.get(), TofuItems.SCULK_BONE_BOOTS.get());
		tag(TofuTags.Items.NATTO).add(TofuItems.NATTO.get());
		tag(TofuTags.Items.MISO).add(TofuItems.MISO.get());
		tag(TofuTags.Items.SOYSAUCE_SOYSAUCE).add(TofuItems.BOTTLE_SOYSAUSE.get());
		tag(TofuTags.Items.SOYSAUCE).addTag(TofuTags.Items.SOYSAUCE_SOYSAUCE);
		tag(TofuTags.Items.TOFU).add(TofuItems.TOFUKINU.get()).add(TofuItems.TOFUMOMEN.get());
		tag(TofuTags.Items.TOFU_FRIED).add(TofuItems.TOFUFRIED.get());
		tag(ItemTags.PIGLIN_REPELLENTS).add(TofuItems.SOUL_FUKUMAME.get(), TofuItems.SOUL_MANJU.get(), TofuItems.SEEDS_SOYBEANS_SOUL.get());
		tag(ItemTags.TRIM_MATERIALS).add(TofuItems.ZUNDARUBY.get(), TofuItems.TOFUMETAL.get(), TofuItems.TOFUDIAMOND.get());
		tag(ItemTags.BOATS).add(TofuItems.LEEK_BOAT.get(), TofuItems.LEEK_GREEN_BOAT.get(), TofuItems.TOFU_STEM_BOAT.get());
		tag(ItemTags.CHEST_BOATS).add(TofuItems.LEEK_CHEST_BOAT.get(), TofuItems.LEEK_GREEN_CHEST_BOAT.get(), TofuItems.TOFU_STEM_CHEST_BOAT.get());
	}
}