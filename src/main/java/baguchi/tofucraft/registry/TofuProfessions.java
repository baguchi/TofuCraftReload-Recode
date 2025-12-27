package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.resources.TofuTradeSets;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Predicate;

public class TofuProfessions {
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, TofuCraftReload.MODID);

	public static final DeferredHolder<VillagerProfession, VillagerProfession> TOFU_CRAFTSMAN = register("tofu_craftsman", TofuPoiTypes.TOFU_CRAFTSMAN, SoundEvents.VILLAGER_WORK_LEATHERWORKER,
			Int2ObjectMap.ofEntries(
					Int2ObjectMap.entry(1, TofuTradeSets.TOFU_CRAFTSMAN_LEVEL_1),
					Int2ObjectMap.entry(2, TofuTradeSets.TOFU_CRAFTSMAN_LEVEL_2),
					Int2ObjectMap.entry(3, TofuTradeSets.TOFU_CRAFTSMAN_LEVEL_3),
					Int2ObjectMap.entry(4, TofuTradeSets.TOFU_CRAFTSMAN_LEVEL_4),
					Int2ObjectMap.entry(5, TofuTradeSets.TOFU_CRAFTSMAN_LEVEL_5)
			));

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(
			String name,
			ResourceKey<PoiType> jobSite,
			@org.jspecify.annotations.Nullable SoundEvent workSound,
			Int2ObjectMap<ResourceKey<TradeSet>> trades
	) {
		return register(name, poiType -> poiType.is(jobSite), poiType -> poiType.is(jobSite), workSound, trades);
	}

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(
			String name,
			Predicate<Holder<PoiType>> heldJobSite,
			Predicate<Holder<PoiType>> acquirableJobSite,
			@org.jspecify.annotations.Nullable SoundEvent workSound
	) {
		return register(name, heldJobSite, acquirableJobSite, ImmutableSet.of(), ImmutableSet.of(), workSound, Int2ObjectMap.ofEntries());
	}

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(
			String name,
			Predicate<Holder<PoiType>> heldJobSite,
			Predicate<Holder<PoiType>> acquirableJobSite,
			@org.jspecify.annotations.Nullable SoundEvent workSound,
			Int2ObjectMap<ResourceKey<TradeSet>> trades
	) {
		return register(name, heldJobSite, acquirableJobSite, ImmutableSet.of(), ImmutableSet.of(), workSound, trades);
	}

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(
			String name,
			ResourceKey<PoiType> jobSite,
			ImmutableSet<Item> requestedItems,
			ImmutableSet<Block> secondaryPoi,
			@org.jspecify.annotations.Nullable SoundEvent workSound,
			Int2ObjectMap<ResourceKey<TradeSet>> trades
	) {
		return register(name, poiType -> poiType.is(jobSite), poiType -> poiType.is(jobSite), requestedItems, secondaryPoi, workSound, trades);
	}

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(
			String name,
			Predicate<Holder<PoiType>> heldJobSite,
			Predicate<Holder<PoiType>> acquirableJobSite,
			ImmutableSet<Item> requestedItems,
			ImmutableSet<Block> secondaryPoi,
			@org.jspecify.annotations.Nullable SoundEvent workSound,
			Int2ObjectMap<ResourceKey<TradeSet>> trades
	) {
		return PROFESSIONS.register(
				name,
				() -> new VillagerProfession(
						Component.translatable("entity." + TofuCraftReload.MODID + ".villager." + name),
						heldJobSite,
						acquirableJobSite,
						requestedItems,
						secondaryPoi,
						workSound,
						trades
				)
		);
	}
}
