package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuProfessions;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
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
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuVillagerTradeEvent {
	@SubscribeEvent
	public static void wanderTradeEvent(VillagerTradesEvent event) {
		List<VillagerTrades.ItemListing> trades = event.getTrades().get(1);
		List<VillagerTrades.ItemListing> trades2 = event.getTrades().get(2);
		List<VillagerTrades.ItemListing> trades3 = event.getTrades().get(3);
		List<VillagerTrades.ItemListing> trades4 = event.getTrades().get(4);
		List<VillagerTrades.ItemListing> trades5 = event.getTrades().get(5);
		if (event.getType() == TofuProfessions.TOFU_CRAFTSMAN.get()) {
			trades.add(new EmeraldForItems(TofuItems.SEEDS_SOYBEANS.get(), 16, 12, 1));
			trades.add(new ItemsForEmeralds(TofuItems.TOFUGRILLED.get(), 1, 9, 8, 2));

			trades2.add(new ItemsForEmeralds(TofuItems.SOYMILK.get(), 1, 3, 8, 7));
			trades2.add(new EmeraldForItems(Items.GLASS_BOTTLE, 5, 8, 6));

			trades3.add(new EmeraldForItems(TofuItems.SALT.get(), 17, 12, 12));
			trades3.add(new ItemsForEmeralds(new ItemStack(TofuItems.TOFUCOOKIE.get()), 2, 8, 12, 14, 0.052F));

			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_APPLE.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_KINAKO.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_PUDDING.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_PUMPKIN.get(), 1, 3, 6, 16));

			trades5.add(new ItemsForEmeralds(TofuBlocks.MORIJIO.get(), 10, 2, 6, 20));
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

		public MerchantOffer getOffer(Entity p_35662_, RandomSource p_35663_) {
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
		public MerchantOffer getOffer(Entity p_35674_, RandomSource p_35675_) {
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

		public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
			return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}
}
