package baguchi.tofucraft.data.generator.tags;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;

import java.util.concurrent.CompletableFuture;

public class TofuDamageTypeTags extends KeyTagProvider<DamageType> {

	public TofuDamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, Registries.DAMAGE_TYPE, future, TofuCraftReload.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(TofuDamageTypes.ZUNDA);
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(TofuDamageTypes.ZUNDA);
		this.tag(DamageTypeTags.WITCH_RESISTANT_TO).add(TofuDamageTypes.ZUNDA).add(TofuDamageTypes.ZUNDA_EXPLOSION);
		this.tag(DamageTypeTags.NO_IMPACT).add(TofuDamageTypes.ZUNDA).add(TofuDamageTypes.FUKUMAME);
		this.tag(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS).add(TofuDamageTypes.ZUNDA);
		this.tag(DamageTypeTags.DAMAGES_HELMET).add(TofuDamageTypes.FALLING_TOFU);
		this.tag(DamageTypeTags.NO_KNOCKBACK).add(TofuDamageTypes.FUKUMAME);
		this.tag(DamageTypeTags.BYPASSES_COOLDOWN).add(TofuDamageTypes.FUKUMAME);
		this.tag(DamageTypeTags.PANIC_CAUSES).add(TofuDamageTypes.FUKUMAME, TofuDamageTypes.ZUNDA, TofuDamageTypes.FALLING_TOFU, TofuDamageTypes.SOY_SPORE, TofuDamageTypes.SOY_SPLASH);
	}
}