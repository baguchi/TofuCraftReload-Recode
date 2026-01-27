package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class EnchantTagGenerator extends EnchantmentTagsProvider {
	public EnchantTagGenerator(PackOutput p_341093_, CompletableFuture<HolderLookup.Provider> p_341136_) {
		super(p_341093_, p_341136_, TofuCraftReload.MODID);
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
		this.tag(EnchantmentTags.NON_TREASURE).add(
				TofuEnchantments.SHAPED_BEAN
		);

		this.tag(EnchantmentTags.TREASURE)
				.add(
						TofuEnchantments.BATCH,
						TofuEnchantments.DRAIN,
						TofuEnchantments.EFFECT_PROTECTION,
						TofuEnchantments.CRACK_BURST
				);
	}
}
