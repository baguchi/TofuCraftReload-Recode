package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.item.enchantment.effect.NoItemDamageExplodeEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuEnchantmentLocationBasedEffects {

	public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> LOCATION_BASED_EFFECT = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, TofuCraftReload.MODID);

	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<NoItemDamageExplodeEffect>> NO_ITEM_DAMAGE_EXPLODE = LOCATION_BASED_EFFECT.register("no_item_damage_explode", () -> NoItemDamageExplodeEffect.CODEC);
}