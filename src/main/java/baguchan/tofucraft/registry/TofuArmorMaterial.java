package baguchan.tofucraft.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum TofuArmorMaterial implements IArmorMaterial {
	KINU("tofucraft:kinu", 1, new int[]{0, 0, 0, 0}, 8, SoundEvents.SNOW_BREAK, 0.0F, 0.0F, () -> Ingredient.of(TofuItems.TOFUKINU)),
	MOMEN("tofucraft:momen", 1, new int[]{0, 1, 1, 0}, 10, SoundEvents.SNOW_BREAK, 0.0F, 0.0F, () -> Ingredient.of(TofuItems.TOFUMOMEN)),
	SOLID("tofucraft:solid", 10, new int[]{1, 4, 5, 2}, 16, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(TofuItems.TOFUISHI)),
	METAL("tofucraft:metal", 15, new int[]{2, 5, 6, 2}, 12, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(TofuItems.TOFUMETAL)),
	DIAMOND("tofucraft:diamond", 40, new int[]{4, 7, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(TofuItems.TOFUDIAMOND));

	private static final int[] HEALTH_PER_SLOT;

	private final String name;

	private final int durabilityMultiplier;

	private final int[] slotProtections;

	private final int enchantmentValue;

	private final SoundEvent sound;

	private final float toughness;

	private final float knockbackResistance;

	private final LazyValue<Ingredient> repairIngredient;

	static {
		HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	}

	TofuArmorMaterial(String p_i231593_3_, int p_i231593_4_, int[] p_i231593_5_, int p_i231593_6_, SoundEvent p_i231593_7_, float p_i231593_8_, float p_i231593_9_, Supplier<Ingredient> p_i231593_10_) {
		this.name = p_i231593_3_;
		this.durabilityMultiplier = p_i231593_4_;
		this.slotProtections = p_i231593_5_;
		this.enchantmentValue = p_i231593_6_;
		this.sound = p_i231593_7_;
		this.toughness = p_i231593_8_;
		this.knockbackResistance = p_i231593_9_;
		this.repairIngredient = new LazyValue(p_i231593_10_);
	}

	public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
		return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
	}

	public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
		return this.slotProtections[p_200902_1_.getIndex()];
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

	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}
