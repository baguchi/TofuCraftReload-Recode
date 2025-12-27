package baguchi.tofucraft.data.resources;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.Optional;

public class TofunianTradeSets {
	public static final ResourceKey<TradeSet> TOFUNIAN_TRAVELER_BUYING = resourceKey("tofunian_traveler/buying");
	public static final ResourceKey<TradeSet> TOFUNIAN_TRAVELER_COMMON = resourceKey("tofunian_traveler/common");
	public static final ResourceKey<TradeSet> TOFUNIAN_TRAVELER_UNCOMMON = resourceKey("tofunian_traveler/uncommon");

	public static final ResourceKey<TradeSet> SOY_WORKER_LEVEL_1 = resourceKey("soy_worker/level_1");
	public static final ResourceKey<TradeSet> SOY_WORKER_LEVEL_2 = resourceKey("soy_worker/level_2");
	public static final ResourceKey<TradeSet> SOY_WORKER_LEVEL_3 = resourceKey("soy_worker/level_3");
	public static final ResourceKey<TradeSet> SOY_WORKER_LEVEL_4 = resourceKey("soy_worker/level_4");
	public static final ResourceKey<TradeSet> SOY_WORKER_LEVEL_5 = resourceKey("soy_worker/level_5");
	public static final ResourceKey<TradeSet> FARMER_LEVEL_1 = resourceKey("farmer/level_1");
	public static final ResourceKey<TradeSet> FARMER_LEVEL_2 = resourceKey("farmer/level_2");
	public static final ResourceKey<TradeSet> FARMER_LEVEL_3 = resourceKey("farmer/level_3");
	public static final ResourceKey<TradeSet> FARMER_LEVEL_4 = resourceKey("farmer/level_4");
	public static final ResourceKey<TradeSet> FARMER_LEVEL_5 = resourceKey("farmer/level_5");

	public static final ResourceKey<TradeSet> SMITH_LEVEL_1 = resourceKey("smith/level_1");
	public static final ResourceKey<TradeSet> SMITH_LEVEL_2 = resourceKey("smith/level_2");
	public static final ResourceKey<TradeSet> SMITH_LEVEL_3 = resourceKey("smith/level_3");
	public static final ResourceKey<TradeSet> SMITH_LEVEL_4 = resourceKey("smith/level_4");
	public static final ResourceKey<TradeSet> SMITH_LEVEL_5 = resourceKey("smith/level_5");

	/*public static final ResourceKey<TradeSet> ENGINEER_LEVEL_1 = resourceKey("engineer/level_1");
	public static final ResourceKey<TradeSet> ENGINEER_LEVEL_2 = resourceKey("engineer/level_2");
	public static final ResourceKey<TradeSet> ENGINEER_LEVEL_3 = resourceKey("engineer/level_3");
	public static final ResourceKey<TradeSet> ENGINEER_LEVEL_4 = resourceKey("engineer/level_4");
	public static final ResourceKey<TradeSet> ENGINEER_LEVEL_5 = resourceKey("engineer/level_5");
*/

	public static void bootstrap(BootstrapContext<TradeSet> context) {
		register(context, TOFUNIAN_TRAVELER_BUYING, TofuTags.TofunianTrade.TOFUNIAN_TRAVELER_BUYING);
		register(context, TOFUNIAN_TRAVELER_COMMON, TofuTags.TofunianTrade.TOFUNIAN_TRAVELER_COMMON, ConstantValue.exactly(5.0F));
		register(context, TOFUNIAN_TRAVELER_UNCOMMON, TofuTags.TofunianTrade.TOFUNIAN_TRAVELER_UNCOMMON);
		register(context, SOY_WORKER_LEVEL_1, TofuTags.TofunianTrade.SOY_WORKER_LEVEL_1);
		register(context, SOY_WORKER_LEVEL_2, TofuTags.TofunianTrade.SOY_WORKER_LEVEL_2);
		register(context, SOY_WORKER_LEVEL_3, TofuTags.TofunianTrade.SOY_WORKER_LEVEL_3);
		register(context, SOY_WORKER_LEVEL_4, TofuTags.TofunianTrade.SOY_WORKER_LEVEL_4);
		register(context, SOY_WORKER_LEVEL_5, TofuTags.TofunianTrade.SOY_WORKER_LEVEL_5);
		register(context, FARMER_LEVEL_1, TofuTags.TofunianTrade.FARMER_LEVEL_1);
		register(context, FARMER_LEVEL_2, TofuTags.TofunianTrade.FARMER_LEVEL_2);
		register(context, FARMER_LEVEL_3, TofuTags.TofunianTrade.FARMER_LEVEL_3);
		register(context, FARMER_LEVEL_4, TofuTags.TofunianTrade.FARMER_LEVEL_4);
		register(context, FARMER_LEVEL_5, TofuTags.TofunianTrade.FARMER_LEVEL_5);

		register(context, SMITH_LEVEL_1, TofuTags.TofunianTrade.SMITH_LEVEL_1);
		register(context, SMITH_LEVEL_2, TofuTags.TofunianTrade.SMITH_LEVEL_2);
		register(context, SMITH_LEVEL_3, TofuTags.TofunianTrade.SMITH_LEVEL_3);
		register(context, SMITH_LEVEL_4, TofuTags.TofunianTrade.SMITH_LEVEL_4);
		register(context, SMITH_LEVEL_5, TofuTags.TofunianTrade.SMITH_LEVEL_5);

		/*register(context, ENGINEER_LEVEL_1, TofuTags.TofunianTrade.ENGINEER_LEVEL_1);
		register(context, ENGINEER_LEVEL_2, TofuTags.TofunianTrade.ENGINEER_LEVEL_2);
		register(context, ENGINEER_LEVEL_3, TofuTags.TofunianTrade.ENGINEER_LEVEL_3);
		register(context, ENGINEER_LEVEL_4, TofuTags.TofunianTrade.ENGINEER_LEVEL_4);
		register(context, ENGINEER_LEVEL_5, TofuTags.TofunianTrade.ENGINEER_LEVEL_5);*/
	}

	public static Holder.Reference<TradeSet> register(BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag) {
		return register(context, resourceKey, tradeTag, ConstantValue.exactly(2.0F));
	}

	public static Holder.Reference<TradeSet> register(
			BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag, NumberProvider numberProvider
	) {
		return context.register(
				resourceKey,
				new TradeSet(
						context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag),
						numberProvider,
						false,
						Optional.of(resourceKey.identifier().withPrefix("trade_set/"))
				)
		);
	}

	public static ResourceKey<TradeSet> resourceKey(String path) {
		return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, path));
	}
}
