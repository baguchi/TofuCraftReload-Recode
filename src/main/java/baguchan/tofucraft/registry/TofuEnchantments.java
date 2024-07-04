package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.item.enchantment.effect.NoItemDamageExplodeEffect;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.AllOf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TofuEnchantments {
	public static final ResourceKey<Enchantment> BATCH = key("batch");
	public static final ResourceKey<Enchantment> EFFECT_PROTECTION = key("effect_protection");
	public static final ResourceKey<Enchantment> DRAIN = key("drain");
	public static final ResourceKey<Enchantment> SHAPED_BEAN = key("shaped_bean");
	public static final ResourceKey<Enchantment> CRACK_BURST = key("crack_burst");

	private static ResourceKey<Enchantment> key(String p_345314_) {
		return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_345314_));
	}

	public static void bootstrap(BootstrapContext<Enchantment> p_345935_) {
		HolderGetter<DamageType> holdergetter = p_345935_.lookup(Registries.DAMAGE_TYPE);
		HolderGetter<Enchantment> holdergetter1 = p_345935_.lookup(Registries.ENCHANTMENT);
		HolderGetter<Item> holdergetter2 = p_345935_.lookup(Registries.ITEM);
		HolderGetter<Block> holdergetter3 = p_345935_.lookup(Registries.BLOCK);
		register(
				p_345935_,
				EFFECT_PROTECTION,
				Enchantment.enchantment(
						Enchantment.definition(
								holdergetter2.getOrThrow(TofuTags.Items.TOFU_DIAMOND_ARMOR_ENCHANTABLE),
								2,
								1,
								Enchantment.dynamicCost(25, 25),
								Enchantment.dynamicCost(75, 25),
								4,
								EquipmentSlotGroup.ARMOR
						)
				)
		);
		register(
				p_345935_,
				BATCH,
				Enchantment.enchantment(
						Enchantment.definition(
								holdergetter2.getOrThrow(TofuTags.Items.TOFU_DIAMOND_MINEABLE_ENCHANTABLE),
								1,
								1,
								Enchantment.dynamicCost(25, 25),
								Enchantment.dynamicCost(75, 25),
								6,
								EquipmentSlotGroup.MAINHAND
						)
				)
		);
		register(
				p_345935_,
				DRAIN,
				Enchantment.enchantment(
						Enchantment.definition(
								holdergetter2.getOrThrow(TofuTags.Items.TOFU_DIAMOND_SWORD_ENCHANTABLE),
								1,
								1,
								Enchantment.dynamicCost(25, 25),
								Enchantment.dynamicCost(75, 25),
								6,
								EquipmentSlotGroup.MAINHAND
						)
				)
		);
		register(
				p_345935_,
				SHAPED_BEAN,
				Enchantment.enchantment(
								Enchantment.definition(
										holdergetter2.getOrThrow(TofuTags.Items.FUKUMAME_ENCHANTABLE),
										10,
										5,
										Enchantment.dynamicCost(1, 10),
										Enchantment.dynamicCost(16, 10),
										1,
										EquipmentSlotGroup.MAINHAND
								)
						)
						.withEffect(
								EnchantmentEffectComponents.DAMAGE,
								new AddValue(LevelBasedValue.perLevel(0.1F)),
								LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(TofuTags.EntityTypes.FUKUMAME).build()
								)
						)
		);
		register(
				p_345935_,
				CRACK_BURST,
				Enchantment.enchantment(
								Enchantment.definition(
										holdergetter2.getOrThrow(TofuTags.Items.FUKUMAME_ENCHANTABLE),
										1,
										3,
										Enchantment.dynamicCost(15, 9),
										Enchantment.dynamicCost(65, 9),
										4,
										EquipmentSlotGroup.MAINHAND
								)
						)
						.withEffect(
								EnchantmentEffectComponents.POST_ATTACK,
								EnchantmentTarget.ATTACKER,
								EnchantmentTarget.DAMAGING_ENTITY,
								new NoItemDamageExplodeEffect(
										true,
										Optional.of(holdergetter.getOrThrow(DamageTypes.EXPLOSION)),
										Optional.of(LevelBasedValue.lookup(List.of(1.0F, 1.25F, 1.5F), LevelBasedValue.perLevel(0.5F, 0.25F))),
										holdergetter3.get(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()),
										Vec3.ZERO,
										LevelBasedValue.perLevel(0.25F, 0.25F),
										false,
										Level.ExplosionInteraction.NONE,
										ParticleTypes.GUST_EMITTER_SMALL,
										ParticleTypes.GUST_EMITTER_SMALL,
										SoundEvents.WIND_CHARGE_BURST
								)
						).withEffect(EnchantmentEffectComponents.HIT_BLOCK,
								AllOf.entityEffects(
										new NoItemDamageExplodeEffect(
												true,
												Optional.of(holdergetter.getOrThrow(DamageTypes.EXPLOSION)),
												Optional.of(LevelBasedValue.lookup(List.of(1.0F, 1.25F, 1.5F), LevelBasedValue.perLevel(0.5F, 0.25F))),
												Optional.empty(),
												Vec3.ZERO,
												LevelBasedValue.perLevel(0.5F, 0.25F),
												false,
												Level.ExplosionInteraction.NONE,
												ParticleTypes.GUST_EMITTER_SMALL,
												ParticleTypes.GUST_EMITTER_SMALL,
												SoundEvents.WIND_CHARGE_BURST
										)
								))
		);
	}

	private static void register(BootstrapContext<Enchantment> p_346019_, ResourceKey<Enchantment> p_345703_, Enchantment.Builder p_345607_) {
		p_346019_.register(p_345703_, p_345607_.build(p_345703_.location()));
	}
}