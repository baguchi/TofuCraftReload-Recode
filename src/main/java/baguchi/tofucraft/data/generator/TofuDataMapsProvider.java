package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.datamap.TofuHarden;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDataMaps;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuProfessions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
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
		final var compostables = builder(NeoForgeDataMaps.COMPOSTABLES);
		compostables.add(TofuItems.EDAMAME, new Compostable(0.1F), false);
		compostables.add(TofuItems.BOILED_EDAMAME, new Compostable(0.1F), false);
		compostables.add(TofuItems.SEEDS_SOYBEANS, new Compostable(0.3F), false);
		compostables.add(TofuItems.SEEDS_SOYBEANS_NETHER, new Compostable(0.3F), false);
		compostables.add(TofuItems.SEEDS_SOYBEANS_SOUL, new Compostable(0.3F), false);
		compostables.add(TofuItems.SEEDS_SOYBEANS_PALE, new Compostable(0.3F), false);
		compostables.add(TofuItems.SEEDS_SOYBEANS_PALE_GLOW, new Compostable(0.3F), false);
		compostables.add(TofuItems.LEEK, new Compostable(0.5F), false);
		compostables.add(TofuItems.TOFU_KINU, new Compostable(0.3F), false);
		compostables.add(TofuItems.TOFU_MOMEN, new Compostable(0.3F), false);
		compostables.add(TofuItems.TOFU_DRIED, new Compostable(0.3F), false);
		compostables.add(TofuItems.TOFU_FRIED, new Compostable(0.5F), false);
		compostables.add(TofuItems.TOFU_FRIED_POUCH, new Compostable(0.5F), false);
		compostables.add(TofuItems.TOFU_ZUNDA, new Compostable(0.5F), false);
		compostables.add(TofuItems.TOFU_ANNIN, new Compostable(0.5F), false);
		compostables.add(TofuItems.TOFU_EGG, new Compostable(0.5F), false);
		compostables.add(TofuItems.TOFU_MISO, new Compostable(0.5F), false);
		compostables.add(TofuItems.SEEDS_CHILI, new Compostable(0.3F), false);
		compostables.add(TofuItems.SEEDS_RICE, new Compostable(0.3F), false);
		compostables.add(TofuItems.RICE, new Compostable(0.5F), false);
		compostables.add(TofuItems.TOFUCOOKIE, new Compostable(0.85F), false);
		compostables.add(TofuItems.ONIGIRI, new Compostable(0.35F), false);
		compostables.add(TofuItems.ONIGIRI_SALT, new Compostable(0.35F), false);
		compostables.add(TofuItems.SALTYMELON, new Compostable(0.3F), false);
		compostables.add(TofuBlocks.SAPLING_APRICOT.get().asItem().builtInRegistryHolder(), new Compostable(0.3F), false);
		compostables.add(TofuBlocks.SAPLING_TOFU.get().asItem().builtInRegistryHolder(), new Compostable(0.3F), false);
		compostables.add(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get().asItem().builtInRegistryHolder(), new Compostable(0.5F), false);
		compostables.add(TofuBlocks.ZUNDATOFU_MUSHROOM.get().asItem().builtInRegistryHolder(), new Compostable(0.3F), false);
		/*final var fuels = builder(NeoForgeDataMaps.FURNACE_FUELS);
		fuels.add()*/

		final var raidHeroGifts = builder(NeoForgeDataMaps.RAID_HERO_GIFTS);
		raidHeroGifts.add(BuiltInRegistries.VILLAGER_PROFESSION.wrapAsHolder(TofuProfessions.TOFU_CRAFTSMAN.get()), new RaidHeroGift(ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "gameplay/hero_of_the_village/tofu_craftsman_gift"))), false);
		final var tofuHarden = builder(TofuDataMaps.TOFU_HARDEN);
		tofuHarden.add(TofuBlocks.ISHITOFU, new TofuHarden(3), false);
		tofuHarden.add(TofuBlocks.METALTOFU, new TofuHarden(6), false);

		final var waxableBlockBuilder = builder(NeoForgeDataMaps.WAXABLES);
		waxableBlockBuilder.add(BuiltInRegistries.BLOCK.wrapAsHolder(TofuBlocks.KINUTOFU.get()), new Waxable(TofuBlocks.WAXED_KINUTOFU.get()), false);
		waxableBlockBuilder.add(BuiltInRegistries.BLOCK.wrapAsHolder(TofuBlocks.MOMENTOFU.get()), new Waxable(TofuBlocks.WAXED_MOMENTOFU.get()), false);
		waxableBlockBuilder.add(BuiltInRegistries.BLOCK.wrapAsHolder(TofuBlocks.ISHITOFU.get()), new Waxable(TofuBlocks.WAXED_ISHITOFU.get()), false);
	}
}