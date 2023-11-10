package baguchan.tofucraft.registry;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum TofuArmorMaterial implements ArmorMaterial {
	KINU("tofucraft:kinu", 1, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
		p_266652_.put(ArmorItem.Type.BOOTS, 0);
		p_266652_.put(ArmorItem.Type.LEGGINGS, 0);
		p_266652_.put(ArmorItem.Type.CHESTPLATE, 0);
		p_266652_.put(ArmorItem.Type.HELMET, 0);
	}), 1, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(TofuItems.TOFUKINU.get());
	}),
	MOMEN("tofucraft:momen", 1, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
		p_266652_.put(ArmorItem.Type.BOOTS, 0);
		p_266652_.put(ArmorItem.Type.LEGGINGS, 0);
		p_266652_.put(ArmorItem.Type.CHESTPLATE, 0);
		p_266652_.put(ArmorItem.Type.HELMET, 0);
	}), 1, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(TofuItems.TOFUMOMEN.get());
	}),
	SOLID("tofucraft:solid", 9, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
		p_266652_.put(ArmorItem.Type.BOOTS, 1);
		p_266652_.put(ArmorItem.Type.LEGGINGS, 3);
		p_266652_.put(ArmorItem.Type.CHESTPLATE, 4);
		p_266652_.put(ArmorItem.Type.HELMET, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(TofuItems.TOFUISHI.get());
	}),
	METAL("tofucraft:metal", 14, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266654_) -> {
		p_266654_.put(ArmorItem.Type.BOOTS, 2);
		p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
		p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
		p_266654_.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(TofuItems.TOFUMETAL.get());
	}),
	DIAMOND("tofucraft:diamond", 36, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
		p_266655_.put(ArmorItem.Type.BOOTS, 3);
		p_266655_.put(ArmorItem.Type.LEGGINGS, 7);
		p_266655_.put(ArmorItem.Type.CHESTPLATE, 9);
		p_266655_.put(ArmorItem.Type.HELMET, 4);
	}), 12, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.25F, 0.05F, () -> {
		return Ingredient.of(TofuItems.TOFUDIAMOND.get());
	}),
	SCULK_BONE("tofucraft:sculk_bone", 40, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
		p_266655_.put(ArmorItem.Type.BOOTS, 3);
		p_266655_.put(ArmorItem.Type.LEGGINGS, 7);
		p_266655_.put(ArmorItem.Type.CHESTPLATE, 9);
		p_266655_.put(ArmorItem.Type.HELMET, 4);
	}), 16, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.1F, () -> {
		return Ingredient.of(TofuItems.SOY_SCULK_BONE.get());
	});

	private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
		p_266653_.put(ArmorItem.Type.BOOTS, 13);
		p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
		p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
		p_266653_.put(ArmorItem.Type.HELMET, 11);
	});
	private final String name;
	private final int durabilityMultiplier;
	private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	private TofuArmorMaterial(String p_268171_, int p_268303_, EnumMap<ArmorItem.Type, Integer> p_267941_, int p_268086_, SoundEvent p_268145_, float p_268058_, float p_268180_, Supplier<Ingredient> p_268256_) {
		this.name = p_268171_;
		this.durabilityMultiplier = p_268303_;
		this.protectionFunctionForType = p_267941_;
		this.enchantmentValue = p_268086_;
		this.sound = p_268145_;
		this.toughness = p_268058_;
		this.knockbackResistance = p_268180_;
		this.repairIngredient = new LazyLoadedValue<>(p_268256_);
	}

	public int getDurabilityForType(ArmorItem.Type p_266745_) {
		return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
	}

	public int getDefenseForType(ArmorItem.Type p_266752_) {
		return this.protectionFunctionForType.get(p_266752_);
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public SoundEvent getEquipSound() {
		return this.sound;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

	public String getSerializedName() {
		return this.name;
	}
}