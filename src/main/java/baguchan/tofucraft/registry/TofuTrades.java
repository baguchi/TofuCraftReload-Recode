package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.TofunianEntity;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuTrades {
	public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRAVELER_TOFUNIAN_TRADE = gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.WHITE_WOOL, 8, 6, 5), new ZundaRubyForItemsTrade(Blocks.GLASS, 9, 6, 5), new ZundaRubyForItemsTrade(Items.WHEAT, 16, 6, 5)}, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED, 1, 9, 5, 5), new ItemsForZundaRubyTrade(TofuItems.TOFUSTICK, 10, 1, 2, 5)}));

	public static final Map<TofunianEntity.Roles, Int2ObjectMap<VillagerTrades.ItemListing[]>> TOFUNIAN_TRADE = Util.make(Maps.newHashMap(), p_221237_0_ -> {
		p_221237_0_.put(TofunianEntity.Roles.TOFUCOOK, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.SEEDS_SOYBEANS, 16, 8, 1), new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED, 1, 10, 2)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUKINU, 28, 9, 6), new ItemsForZundaRubyTrade(TofuBlocks.ISHITOFU_BRICK, 1, 12, 4, 8)}
				, 3, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUCOOKIE, 1, 12, 6, 13), new ItemsForZundaRubyTrade(TofuItems.TOFUZUNDA, 1, 8, 4, 13)})));
		p_221237_0_.put(TofunianEntity.Roles.TOFUSMITH, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUISHI, 16, 8, 2), new ItemsForZundaRubyTrade(TofuItems.TOFU_SOLID_PICKAXE, 1, 1, 3), new ItemsForZundaRubyTrade(TofuItems.TOFU_SOLID_SHOVEL, 1, 1, 3)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUMETAL, 8, 8, 6), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_PICKAXE, 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SHOVEL, 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SWORD, 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_AXE, 6, 1, 4, 10)}
				, 3, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALBOOTS, 4, 1, 4, 12), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALCHESTPLATE, 7, 1, 4, 16), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALLEGGINGS, 6, 1, 4, 15), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALHELMET, 5, 1, 4, 14)}
				, 4, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_PICKAXE), 12, 1, 2, 20, 0.055F), new ItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_SHOVEL), 10, 1, 2, 20, 0.055F), new ItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_SWORD), 12, 1, 2, 20, 0.055F), new ItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_AXE), 11, 1, 2, 20, 0.055F)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDBOOTS, 14, 1, 2, 25), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE, 16, 1, 2, 25), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS, 15, 1, 2, 25), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDHELMET, 14, 1, 2, 25)})));
		p_221237_0_.put(TofunianEntity.Roles.SOYWORKER, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.GLASS, 10, 6, 2), new ZundaRubyForItemsTrade(TofuItems.LEEK, 26, 6, 2)}
				, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFU_HAMBURG, 1, 9, 6, 7), new ItemsForZundaRubyTrade(TofuItems.COOKED_TOFU_FISH, 1, 12, 5, 7)}
				, 3, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.BITTERN, 4, 8, 15), new ItemsForZundaRubyTrade(TofuItems.SOY_CHOCOLATE, 1, 6, 8, 15)}
				, 4, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK, 1, 3, 6, 20), new ItemsForZundaRubyTrade(TofuItems.TOFUNIAN_SOY_CHOCOLATE, 1, 6, 8, 20)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK_APPLE, 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_KINAKO, 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUMPKIN, 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUDDING, 1, 3, 6, 30)})));
	});

	@SubscribeEvent
	public static void wanderTradeEvent(VillagerTradesEvent event) {
		List<VillagerTrades.ItemListing> trades = event.getTrades().get(1);
		List<VillagerTrades.ItemListing> trades2 = event.getTrades().get(2);
		List<VillagerTrades.ItemListing> trades3 = event.getTrades().get(3);
		List<VillagerTrades.ItemListing> trades4 = event.getTrades().get(4);
		List<VillagerTrades.ItemListing> trades5 = event.getTrades().get(5);
		if (event.getType() == TofuVillagers.TOFU_CRAFTSMAN) {
			trades.add(new EmeraldForItems(TofuItems.SEEDS_SOYBEANS, 16, 12, 1));
			trades.add(new ItemsForEmeralds(TofuItems.TOFUGRILLED, 9, 8, 2));
			trades2.add(new ItemsForEmeralds(TofuItems.SOYMILK, 3, 8, 7));
			trades2.add(new EmeraldForItems(Items.GLASS_BOTTLE, 3, 8, 6));

			trades3.add(new EmeraldForItems(TofuItems.SALT, 17, 12, 12));
			trades3.add(new ItemsForEmeralds(new ItemStack(TofuItems.TOFUCOOKIE), 2, 8, 12, 14, 0.052F));

			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_APPLE, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_KINAKO, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_PUDDING, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_PUMPKIN, 3, 6, 16));

			trades5.add(new ItemsForEmeralds(TofuItems.SOY_CHOCOLATE, 8, 6, 20));
		}
	}

	private static Int2ObjectMap<VillagerTrades.ItemListing[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
		return new Int2ObjectOpenHashMap<>(p_221238_0_);
	}

	static class EmeraldForItems implements VillagerTrades.ItemListing {
		private final Item item;
		private final int cost;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_) {
			this.item = p_35657_.asItem();
			this.cost = p_35658_;
			this.maxUses = p_35659_;
			this.villagerXp = p_35660_;
			this.priceMultiplier = 0.05F;
		}

		public MerchantOffer getOffer(Entity p_35662_, Random p_35663_) {
			ItemStack itemstack = new ItemStack(this.item, this.cost);
			return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

	static class EmeraldsForVillagerTypeItem implements VillagerTrades.ItemListing {
		private final Map<VillagerType, Item> trades;
		private final int cost;
		private final int maxUses;
		private final int villagerXp;

		public EmeraldsForVillagerTypeItem(int p_35669_, int p_35670_, int p_35671_, Map<VillagerType, Item> p_35672_) {
			Registry.VILLAGER_TYPE.stream().filter((p_35680_) -> {
				return !p_35672_.containsKey(p_35680_);
			}).findAny().ifPresent((p_35677_) -> {
				throw new IllegalStateException("Missing trade for villager type: " + Registry.VILLAGER_TYPE.getKey(p_35677_));
			});
			this.trades = p_35672_;
			this.cost = p_35669_;
			this.maxUses = p_35670_;
			this.villagerXp = p_35671_;
		}

		@Nullable
		public MerchantOffer getOffer(Entity p_35674_, Random p_35675_) {
			if (p_35674_ instanceof VillagerDataHolder) {
				ItemStack itemstack = new ItemStack(this.trades.get(((VillagerDataHolder) p_35674_).getVillagerData().getType()), this.cost);
				return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, 0.05F);
			} else {
				return null;
			}
		}
	}

	static class ItemsForEmeralds implements VillagerTrades.ItemListing {
		private final ItemStack itemStack;
		private final int emeraldCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public ItemsForEmeralds(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
			this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
		}

		public ItemsForEmeralds(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
			this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
		}

		public ItemsForEmeralds(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
			this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
		}

		public ItemsForEmeralds(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
			this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
		}

		public ItemsForEmeralds(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
			this.itemStack = p_35758_;
			this.emeraldCost = p_35759_;
			this.numberOfItems = p_35760_;
			this.maxUses = p_35761_;
			this.villagerXp = p_35762_;
			this.priceMultiplier = p_35763_;
		}

		public MerchantOffer getOffer(Entity p_35771_, Random p_35772_) {
			return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
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
