package baguchan.tofucraft.registry;

import baguchan.tofucraft.entity.Tofunian;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.Random;


public class TofuTrades {
	public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRAVELER_TOFUNIAN_TRADE = gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.WHITE_WOOL, 8, 6, 5), new ZundaRubyForItemsTrade(Blocks.GLASS, 9, 6, 5), new ZundaRubyForItemsTrade(Items.WHEAT, 16, 6, 5)}, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED, 1, 9, 5, 5), new ItemsForZundaRubyTrade(TofuItems.TOFUSTICK, 10, 1, 2, 5)}));

	public static final Map<Tofunian.Roles, Int2ObjectMap<VillagerTrades.ItemListing[]>> TOFUNIAN_TRADE = Util.make(Maps.newHashMap(), p_221237_0_ -> {
		p_221237_0_.put(Tofunian.Roles.TOFUCOOK, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.SEEDS_SOYBEANS, 16, 8, 1), new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED, 1, 10, 2)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUKINU, 28, 9, 6), new ItemsForZundaRubyTrade(TofuBlocks.ISHITOFU_BRICK, 1, 12, 4, 8)}
				, 3, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUCOOKIE, 1, 12, 6, 13), new ItemsForZundaRubyTrade(TofuItems.TOFUMISO, 1, 6, 6, 13)}
				, 4, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.YUBA, 18, 8, 15), new ZundaRubyForItemsTrade(TofuBlocks.LEEK_STEM, 16, 8, 15)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuBlocks.TOFUCAKE.asItem(), 3, 1, 20)})));
		p_221237_0_.put(Tofunian.Roles.TOFUSMITH, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUISHI, 16, 8, 2), new ItemsForZundaRubyTrade(TofuItems.TOFU_SOLID_PICKAXE, 1, 1, 3), new ItemsForZundaRubyTrade(TofuItems.TOFU_SOLID_SHOVEL, 1, 1, 3)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUMETAL, 8, 8, 6), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_PICKAXE, 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SHOVEL, 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SWORD, 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_AXE, 6, 1, 4, 10)}
				, 3, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALBOOTS, 4, 1, 4, 12), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALCHESTPLATE, 7, 1, 4, 16), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALLEGGINGS, 6, 1, 4, 15), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALHELMET, 5, 1, 4, 14)}
				, 4, new VillagerTrades.ItemListing[]{new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_PICKAXE), 16, 1, 2, 20, 0.055F), new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_SHOVEL), 14, 1, 2, 20, 0.055F), new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_SWORD), 15, 1, 2, 20, 0.055F), new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_AXE), 16, 1, 2, 20, 0.055F)}
				, 5, new VillagerTrades.ItemListing[]{new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDBOOTS, 14, 1, 2, 25), new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, 18, 1, 2, 25), new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, 16, 1, 2, 25), new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDHELMET, 15, 1, 2, 25)})));
		p_221237_0_.put(Tofunian.Roles.SOYWORKER, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.GLASS, 10, 6, 2), new ZundaRubyForItemsTrade(TofuItems.LEEK, 26, 6, 2)}
				, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFU_HAMBURG, 1, 9, 6, 7), new ItemsForZundaRubyTrade(TofuItems.COOKED_TOFU_FISH, 1, 12, 5, 7)}
				, 3, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.BITTERN, 4, 8, 15), new ItemsForZundaRubyTrade(TofuItems.SOY_CHOCOLATE, 1, 6, 8, 15)}
				, 4, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK, 1, 3, 6, 20), new ItemsForZundaRubyTrade(TofuItems.TOFUNIAN_SOY_CHOCOLATE, 1, 6, 8, 20)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK_APPLE, 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_KINAKO, 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUMPKIN, 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUDDING, 1, 3, 6, 30)})));
	});

	private static Int2ObjectMap<VillagerTrades.ItemListing[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
		return new Int2ObjectOpenHashMap<>(p_221238_0_);
	}

	static class ZundaRubyForItemsTrade implements VillagerTrades.ItemListing {
		private final Item tradeItem;

		private final int count;

		private final int maxUses;

		private final int xpValue;

		private final float priceMultiplier;

		public ZundaRubyForItemsTrade(ItemLike tradeItemIn, int countIn, int maxUsesIn, int xpValueIn) {
			this.tradeItem = tradeItemIn.asItem();
			this.count = countIn;
			this.maxUses = maxUsesIn;
			this.xpValue = xpValueIn;
			this.priceMultiplier = 0.05F;
		}

		public MerchantOffer getOffer(Entity trader, Random rand) {
			ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
			return new MerchantOffer(itemstack, new ItemStack(TofuItems.ZUNDARUBY), this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}

	static class EnchantedItemsForZundaRubyTrade implements VillagerTrades.ItemListing {
		private final ItemStack sellingItem;

		private final int rubyCount;

		private final int sellingItemCount;

		private final int maxUses;

		private final int xpValue;

		private final float priceMultiplier;

		public EnchantedItemsForZundaRubyTrade(Block sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public EnchantedItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, 12, xpValue);
		}

		public EnchantedItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public EnchantedItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(sellingItem, rubyCount, sellingItemCount, maxUses, xpValue, 0.05F);
		}

		public EnchantedItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
			this.sellingItem = sellingItem;
			this.rubyCount = rubyCount;
			this.sellingItemCount = sellingItemCount;
			this.maxUses = maxUses;
			this.xpValue = xpValue;
			this.priceMultiplier = priceMultiplier;
		}

		public MerchantOffer getOffer(Entity trader, Random rand) {
			int i = 5 + rand.nextInt(15);
			int j = Math.min(this.rubyCount + i, 64);
			ItemStack stack = new ItemStack(this.sellingItem.getItem(), 1);
			EnchantmentHelper.enchantItem(rand, stack, i, false);
			return new MerchantOffer(new ItemStack(TofuItems.ZUNDARUBY, j), stack, this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}

	static class ItemsForZundaRubyTrade implements VillagerTrades.ItemListing {
		private final ItemStack sellingItem;

		private final int rubyCount;

		private final int sellingItemCount;

		private final int maxUses;

		private final int xpValue;

		private final float priceMultiplier;

		public ItemsForZundaRubyTrade(Block sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public ItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, 12, xpValue);
		}

		public ItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public ItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(sellingItem, rubyCount, sellingItemCount, maxUses, xpValue, 0.05F);
		}

		public ItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
			this.sellingItem = sellingItem;
			this.rubyCount = rubyCount;
			this.sellingItemCount = sellingItemCount;
			this.maxUses = maxUses;
			this.xpValue = xpValue;
			this.priceMultiplier = priceMultiplier;
		}

		public MerchantOffer getOffer(Entity trader, Random rand) {
			return new MerchantOffer(new ItemStack(TofuItems.ZUNDARUBY, this.rubyCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}
}
