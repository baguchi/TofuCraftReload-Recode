package baguchan.tofucraft.registry;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum TofuArmorMaterial implements IArmorMaterial {
	KINU("tofucraft:kinu", 1, new int[]{0, 0, 0, 0}, 8, SoundEvents.field_187728_s, 0.0F, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[]{(IItemProvider) TofuItems.TOFUKINU})),
	MOMEN("tofucraft:momen", 1, new int[]{0, 1, 1, 0}, 10, SoundEvents.field_187713_n, 0.0F, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[]{(IItemProvider) TofuItems.TOFUMOMEN})),
	SOLID("tofucraft:solid", 10, new int[]{1, 4, 5, 2}, 16, SoundEvents.field_187725_r, 0.0F, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[]{(IItemProvider) TofuItems.TOFUISHI})),
	METAL("tofucraft:metal", 15, new int[]{2, 5, 6, 2}, 12, SoundEvents.field_187722_q, 0.0F, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[]{(IItemProvider) TofuItems.TOFUMETAL})),
	DIAMOND("tofucraft:diamond", 40, new int[]{4, 7, 9, 4}, 10, SoundEvents.field_187716_o, 2.5F, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[]{(IItemProvider) TofuItems.TOFUDIAMOND}));

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

	public int func_200896_a(EquipmentSlotType p_200896_1_) {
		return HEALTH_PER_SLOT[p_200896_1_.func_188454_b()] * this.durabilityMultiplier;
	}

	public int func_200902_b(EquipmentSlotType p_200902_1_) {
		return this.slotProtections[p_200902_1_.func_188454_b()];
	}

	public int func_200900_a() {
		return this.enchantmentValue;
	}

	public SoundEvent func_200899_b() {
		return this.sound;
	}

	public Ingredient func_200898_c() {
		return (Ingredient) this.repairIngredient.func_179281_c();
	}

	@OnlyIn(Dist.CLIENT)
	public String func_200897_d() {
		return this.name;
	}

	public float func_200901_e() {
		return this.toughness;
	}

	public float func_230304_f_() {
		return this.knockbackResistance;
	}
}
