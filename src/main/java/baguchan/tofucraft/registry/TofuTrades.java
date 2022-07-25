package baguchan.tofucraft.registry;

import baguchan.tofucraft.entity.Tofunian;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.util.RandomSource;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class TofuTrades {
	public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRAVELER_TOFUNIAN_TRADE = gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.WHITE_WOOL, 8, 6, 5), new ZundaRubyForItemsTrade(Blocks.GLASS, 9, 6, 5), new ZundaRubyForItemsTrade(Items.WHEAT, 16, 6, 5)}, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED.get(), 1, 9, 5, 5), new ItemsForZundaRubyTrade(TofuItems.TOFUSTICK.get(), 10, 1, 2, 5)}));

	public static final Map<Tofunian.Roles, Int2ObjectMap<VillagerTrades.ItemListing[]>> TOFUNIAN_TRADE = Util.make(Maps.newHashMap(), p_221237_0_ -> {
		p_221237_0_.put(Tofunian.Roles.TOFUCOOK, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.SEEDS_SOYBEANS.get(), 16, 14, 1), new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED.get(), 1, 10, 2), new ItemsForZundaRubyTrade(TofuItems.SEEDS_CHILI.get(), 1, 8, 1)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUKINU.get(), 28, 9, 6), new ItemsForZundaRubyTrade(TofuBlocks.ISHITOFU_BRICK.get(), 1, 12, 4, 8), new ItemsForZundaRubyTrade(TofuItems.KINAKO_MANJU.get(), 1, 6, 8, 6)}
				, 3, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUCOOKIE.get(), 1, 12, 6, 13), new ItemsForZundaRubyTrade(TofuItems.TOFUMISO.get(), 1, 6, 6, 13), new ZundaRubyForItemsTrade(TofuItems.TOFUSESAME.get(), 6, 6, 13)}
				, 4, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.YUBA.get(), 18, 12, 15), new ZundaRubyForItemsTrade(TofuBlocks.LEEK_STEM.get(), 16, 8, 15), new FairTrade(TofuItems.APRICOT.get(), TofuItems.APRICOTJERRY_BOTTLE.get(), 16, 2)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuBlocks.TOFUCAKE.get().asItem(), 3, 1, 20), new ItemsForZundaRubyTrade(TofuItems.TOFUSTRAWBERRY.get().asItem(), 2, 6, 20)})));
		p_221237_0_.put(Tofunian.Roles.TOFUSMITH, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUISHI.get(), 16, 8, 2), new ItemsForZundaRubyTrade(TofuItems.TOFU_SOLID_PICKAXE.get(), 1, 1, 3), new ItemsForZundaRubyTrade(TofuItems.TOFU_SOLID_SHOVEL.get(), 1, 1, 3)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUMETAL.get(), 8, 8, 6), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_PICKAXE.get(), 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SHOVEL.get(), 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SWORD.get(), 6, 1, 4, 10), new ItemsForZundaRubyTrade(TofuItems.TOFU_METAL_AXE.get(), 6, 1, 4, 10)}
				, 3, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALBOOTS.get(), 4, 1, 4, 12), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALCHESTPLATE.get(), 7, 1, 4, 16), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALLEGGINGS.get(), 6, 1, 4, 15), new ItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_METALHELMET.get(), 5, 1, 4, 14)}
				, 4, new VillagerTrades.ItemListing[]{new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_PICKAXE.get()), 16, 1, 2, 20, 0.085F), new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_SHOVEL.get()), 14, 1, 2, 20, 0.085F), new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_SWORD.get()), 15, 1, 2, 20, 0.085F), new EnchantedItemsForZundaRubyTrade(new ItemStack(TofuItems.TOFU_DIAMOND_AXE.get()), 16, 1, 2, 20, 0.085F)}
				, 5, new VillagerTrades.ItemListing[]{new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDBOOTS.get(), 14, 1, 2, 25), new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDCHESTPLATE.get(), 18, 1, 2, 25), new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDLEGGINGS.get(), 16, 1, 2, 25), new EnchantedItemsForZundaRubyTrade(TofuItems.ARMOR_TOFU_DIAMONDHELMET.get(), 15, 1, 2, 25)})));
		p_221237_0_.put(Tofunian.Roles.SOYWORKER, gatAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.GLASS, 10, 6, 2), new ZundaRubyForItemsTrade(TofuItems.LEEK.get(), 26, 6, 2)}
				, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFU_HAMBURG.get(), 1, 9, 6, 7), new ItemsForZundaRubyTrade(TofuItems.COOKED_TOFU_FISH.get(), 1, 12, 5, 7)}
				, 3, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.BITTERN_BOTTLE.get(), 4, 8, 15), new ItemsForZundaRubyTrade(TofuItems.SOY_CHOCOLATE.get(), 1, 6, 8, 15)}
				, 4, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK.get(), 1, 3, 6, 20), new ItemsForZundaRubyTrade(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get(), 1, 6, 8, 20)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK_ANNIN.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_APPLE.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_FRUITS.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_HONEY.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_KINAKO.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUMPKIN.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_RAMUNE.get(), 1, 3, 6, 30),new ItemsForZundaRubyTrade(TofuItems.SOYMILK_SAKURA.get(), 1, 3, 6, 30),new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUDDING.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_STRAWBERRY.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_TEA.get(), 1, 3, 6, 30)})));
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

		public MerchantOffer getOffer(Entity trader, RandomSource rand) {
			ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
			return new MerchantOffer(itemstack, new ItemStack(TofuItems.ZUNDARUBY.get()), this.maxUses, this.xpValue, this.priceMultiplier);
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

		public MerchantOffer getOffer(Entity trader, RandomSource rand) {
			int i = 5 + rand.nextInt(15);
			int j = Math.min(this.rubyCount + i, 64);
			ItemStack stack = new ItemStack(this.sellingItem.getItem(), 1);
			EnchantmentHelper.enchantItem(rand, stack, i, false);
			return new MerchantOffer(new ItemStack(TofuItems.ZUNDARUBY.get(), j), stack, this.maxUses, this.xpValue, this.priceMultiplier);
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

		public MerchantOffer getOffer(Entity trader, RandomSource rand) {
			return new MerchantOffer(new ItemStack(TofuItems.ZUNDARUBY.get(), this.rubyCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}

	static class FairTrade implements VillagerTrades.ItemListing {
		private final Item sellingItem;
		private final Item targetItem;
		private final int maxUses;
		private final int xpValue;

		public FairTrade(Item sellingItem, Item targetItem, int maxUses, int xpValue) {
			this.sellingItem = sellingItem;
			this.targetItem = targetItem;
			this.maxUses = maxUses;
			this.xpValue = xpValue;
		}

		@Nullable
		@Override
		public MerchantOffer getOffer(@NotNull Entity entity, @NotNull RandomSource randomSource) {
			return new MerchantOffer(new ItemStack(targetItem), new ItemStack(sellingItem), maxUses, xpValue, 0.05f);
		}
	}
}
