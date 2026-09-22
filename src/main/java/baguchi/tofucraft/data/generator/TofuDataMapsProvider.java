package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.datamap.TofuSignal;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDataMaps;
import baguchi.tofucraft.registry.TofuProfessions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.RaidHeroGift;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;

import java.util.concurrent.CompletableFuture;

public class TofuDataMapsProvider extends DataMapProvider {
	public TofuDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void gather(HolderLookup.Provider provider) {

		final var raidHeroGifts = builder(NeoForgeDataMaps.RAID_HERO_GIFTS);
		raidHeroGifts.add(BuiltInRegistries.VILLAGER_PROFESSION.wrapAsHolder(TofuProfessions.TOFU_CRAFTSMAN.get()), new RaidHeroGift(ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "gameplay/hero_of_the_village/tofu_craftsman_gift"))), false);
		final var tofuSignal = builder(TofuDataMaps.TOFU_SIGNAL);
		tofuSignal.add(TofuBlocks.ISHITOFU, new TofuSignal(3), false);
		tofuSignal.add(TofuBlocks.METALTOFU, new TofuSignal(6), false);

		final var waxableBlockBuilder = builder(NeoForgeDataMaps.WAXABLES);
		waxableBlockBuilder.add(BuiltInRegistries.BLOCK.wrapAsHolder(TofuBlocks.KINUTOFU.get()), new Waxable(TofuBlocks.WAXED_KINUTOFU.get()), false);
		waxableBlockBuilder.add(BuiltInRegistries.BLOCK.wrapAsHolder(TofuBlocks.MOMENTOFU.get()), new Waxable(TofuBlocks.WAXED_MOMENTOFU.get()), false);
		waxableBlockBuilder.add(BuiltInRegistries.BLOCK.wrapAsHolder(TofuBlocks.ISHITOFU.get()), new Waxable(TofuBlocks.WAXED_ISHITOFU.get()), false);
	}
}