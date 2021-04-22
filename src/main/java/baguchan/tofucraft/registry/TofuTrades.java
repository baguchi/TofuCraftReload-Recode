package baguchan.tofucraft.registry;

import baguchan.tofucraft.entity.TofunianEntity;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;

import java.util.Map;
import java.util.Random;

public class TofuTrades {
	public static final Int2ObjectMap<VillagerTrades.ITrade[]> TRAVELER_TOFUNIAN_TRADE = gatAsIntMap(ImmutableMap.of(Integer.valueOf(1), new VillagerTrades.ITrade[]{new ZundaRubyForItemsTrade((IItemProvider) Blocks.field_150359_w, 8, 6, 5), new ZundaRubyForItemsTrade((IItemProvider) Blocks.field_196556_aL, 9, 6, 5), new ZundaRubyForItemsTrade((IItemProvider) Items.field_151015_O, 16, 6, 5)}Integer.valueOf(2), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED, 1, 9, 5, 5), new ItemsForZundaRubyTrade(TofuItems.TOFUSTICK, 10, 1, 2, 5)}));

	public static final Map<TofunianEntity.Roles, Int2ObjectMap<VillagerTrades.ITrade[]>> TOFUNIAN_TRADE;

	static {
		TOFUNIAN_TRADE = (Map<TofunianEntity.Roles, Int2ObjectMap<VillagerTrades.ITrade[]>>) Util.func_200696_a(Maps.newHashMap(), p_221237_0_ -> {
			p_221237_0_.put(TofunianEntity.Roles.TOFUCOOK, gatAsIntMap(ImmutableMap.of(Integer.valueOf(1), new VillagerTrades.ITrade[]{new ZundaRubyForItemsTrade(TofuItems.SEEDS_SOYBEANS, 16, 8, 1), new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED, 1, 10, 2)}Integer.valueOf(2), new VillagerTrades.ITrade[]{new ZundaRubyForItemsTrade(TofuItems.TOFUKINU, 28, 9, 5), new ItemsForZundaRubyTrade(TofuBlocks.ISHITOFU_BRICK, 1, 12, 4, 7)}Integer.valueOf(3), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.TOFUCOOKIE, 1, 12, 6, 15), new ItemsForZundaRubyTrade(TofuItems.TOFUZUNDA, 1, 8, 4, 15)}Integer.valueOf(4), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuBlocks.TOFUCAKE, 2, 1, 4, 20), new ItemsForZundaRubyTrade(TofuBlocks.ZUNDATOFUCAKE, 2, 1, 4, 20)}Integer.valueOf(5), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.TOFUHOE, 3, 1, 3, 20)})));
			p_221237_0_.put(TofunianEntity.Roles.TOFUSMITH, gatAsIntMap(ImmutableMap.of(Integer.valueOf(1), new VillagerTrades.ITrade[]{new ZundaRubyForItemsTrade(TofuItems.TOFUISHI, 16, 8, 2), new ItemsForZundaRubyTrade(TofuItems.SOLIDPICKAXE, 1, 1, 3), new ItemsForZundaRubyTrade(TofuItems.SOLIDSHOVEL, 1, 1, 3)}Integer.valueOf(2), new VillagerTrades.ITrade[]{new ZundaRubyForItemsTrade(TofuItems.TOFUMETAL, 8, 8, 6), new ItemsForZundaRubyTrade(TofuItems.METALPICKAXE, 6, 1, 4, 5), new ItemsForZundaRubyTrade(TofuItems.METALSHOVEL, 6, 1, 4, 5), new ItemsForZundaRubyTrade(TofuItems.METALSWORD, 6, 1, 4, 5), new ItemsForZundaRubyTrade(TofuItems.METALAXE, 6, 1, 4, 5)}Integer.valueOf(3), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.ARMOR_METALBOOTS, 4, 1, 4, 12), new ItemsForZundaRubyTrade(TofuItems.ARMOR_METALCHESTPLATE, 7, 1, 4, 12), new ItemsForZundaRubyTrade(TofuItems.ARMOR_METALLEGGINS, 6, 1, 4, 12), new ItemsForZundaRubyTrade(TofuItems.ARMOR_METALHELMET, 5, 1, 4, 12)}Integer.valueOf(4), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.TOFUDIAMONDPICKAXE, 12, 1, 2, 18), new ItemsForZundaRubyTrade(TofuItems.TOFUDIAMONDSHOVEL, 10, 1, 2, 18), new ItemsForZundaRubyTrade(TofuItems.TOFUDIAMONDSWORD, 12, 1, 2, 18), new ItemsForZundaRubyTrade(TofuItems.TOFUDIAMONDAXE, 11, 1, 2, 18)}Integer.valueOf(5), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.ARMOR_DIAMONDBOOTS, 14, 1, 2, 18), new ItemsForZundaRubyTrade(TofuItems.ARMOR_DIAMONDCHESTPLATE, 16, 1, 2, 18), new ItemsForZundaRubyTrade(TofuItems.ARMOR_DIAMONDLEGGINS, 15, 1, 2, 18), new ItemsForZundaRubyTrade(TofuItems.ARMOR_DIAMONDHELMET, 14, 1, 2, 18)})));
			p_221237_0_.put(TofunianEntity.Roles.SOYWORKER, gatAsIntMap(ImmutableMap.of(Integer.valueOf(1), new VillagerTrades.ITrade[]{new ZundaRubyForItemsTrade((IItemProvider) Blocks.field_150359_w, 10, 6, 2), new ZundaRubyForItemsTrade(TofuItems.LEEK, 26, 6, 2)}Integer.valueOf(2), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.TOFU_HAMBURG, 1, 9, 6, 5), new ItemsForZundaRubyTrade(TofuItems.COOKED_TOFU_FISH, 1, 12, 5, 5)}Integer.valueOf(3), new VillagerTrades.ITrade[]{new ZundaRubyForItemsTrade(TofuItems.BITTERN, 4, 8, 12), new ItemsForZundaRubyTrade(TofuItems.SOY_CHOCOLATE, 1, 6, 8, 12)}Integer.valueOf(4), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK, 1, 3, 6, 15), new ItemsForZundaRubyTrade(TofuItems.SOY_CHOCOLATE, 1, 6, 8, 15)}Integer.valueOf(5), new VillagerTrades.ITrade[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK_APPLE, 1, 3, 6, 15), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_KINAKO, 1, 3, 6, 15), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUMPKIN, 1, 3, 6, 15), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUDDING, 1, 3, 6, 15)})));
		});
	}

	private static Int2ObjectMap<VillagerTrades.ITrade[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
		return (Int2ObjectMap<VillagerTrades.ITrade[]>) new Int2ObjectOpenHashMap(p_221238_0_);
	}

	static class ZundaRubyForItemsTrade implements VillagerTrades.ITrade {
		private final Item tradeItem;

		private final int count;

		private final int maxUses;

		private final int xpValue;

		private final float priceMultiplier;

		public ZundaRubyForItemsTrade(IItemProvider tradeItemIn, int countIn, int maxUsesIn, int xpValueIn) {
			this.tradeItem = tradeItemIn.func_199767_j();
			this.count = countIn;
			this.maxUses = maxUsesIn;
			this.xpValue = xpValueIn;
			this.priceMultiplier = 0.05F;
		}

		public MerchantOffer func_221182_a(Entity trader, Random rand) {
			ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
			return new MerchantOffer(itemstack, new ItemStack(TofuItems.ZUNDARUBY), this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}

	static class ItemsForZundaRubyTrade implements VillagerTrades.ITrade {
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

		public MerchantOffer func_221182_a(Entity trader, Random rand) {
			return new MerchantOffer(new ItemStack(TofuItems.ZUNDARUBY, this.rubyCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}
}
