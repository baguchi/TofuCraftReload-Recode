package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuDamageSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class TofuDamageTypeTags extends TagsProvider<DamageType> {

	public TofuDamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
		super(output, Registries.DAMAGE_TYPE, future, TofuCraftReload.MODID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(TofuDamageSource.ZUNDA);
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(TofuDamageSource.ZUNDA);
		this.tag(DamageTypeTags.WITCH_RESISTANT_TO).add(TofuDamageSource.ZUNDA);
		this.tag(DamageTypeTags.NO_IMPACT).add(TofuDamageSource.ZUNDA);
		this.tag(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS).add(TofuDamageSource.ZUNDA);
		this.tag(DamageTypeTags.DAMAGES_HELMET).add(TofuDamageSource.FALLING_TOFU);
	}
}