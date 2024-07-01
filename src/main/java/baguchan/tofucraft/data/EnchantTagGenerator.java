package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class EnchantTagGenerator extends EnchantmentTagsProvider {
	public EnchantTagGenerator(PackOutput p_341093_, CompletableFuture<HolderLookup.Provider> p_341136_, @org.jetbrains.annotations.Nullable net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper) {
		super(p_341093_, p_341136_, TofuCraftReload.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		this.tag(EnchantmentTags.TOOLTIP_ORDER).add(
				TofuEnchantments.BATCH,
				TofuEnchantments.DRAIN,
				TofuEnchantments.EFFECT_PROTECTION,
				TofuEnchantments.SHAPED_BEAN,
				TofuEnchantments.CRACK_BURST
		);
		this.tag(EnchantmentTags.TREASURE)
				.add(
						TofuEnchantments.BATCH,
						TofuEnchantments.DRAIN,
						TofuEnchantments.EFFECT_PROTECTION
				);
	}
}
