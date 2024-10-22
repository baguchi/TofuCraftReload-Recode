package baguchan.tofucraft.data.generator;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.concurrent.CompletableFuture;

public class EnchantTagGenerator extends EnchantmentTagsProvider {
	public EnchantTagGenerator(PackOutput p_341093_, CompletableFuture<HolderLookup.Provider> p_341136_, @org.jetbrains.annotations.Nullable net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper) {
		super(p_341093_, p_341136_, TofuCraftReload.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		HolderLookup<Enchantment> lookup = p_256380_.lookupOrThrow(Registries.ENCHANTMENT);
		this.tag(EnchantmentTags.TOOLTIP_ORDER).add(
				lookup.get(TofuEnchantments.BATCH).get().key()
				,
				lookup.get(TofuEnchantments.DRAIN).get().key(),
				lookup.get(TofuEnchantments.EFFECT_PROTECTION).get().key(),
				lookup.get(TofuEnchantments.SHAPED_BEAN).get().key(),
				lookup.get(TofuEnchantments.CRACK_BURST).get().key()
		);
		this.tag(EnchantmentTags.TREASURE)
				.add(
						TofuEnchantments.BATCH,
						TofuEnchantments.DRAIN,
						TofuEnchantments.EFFECT_PROTECTION
				);
	}
}
