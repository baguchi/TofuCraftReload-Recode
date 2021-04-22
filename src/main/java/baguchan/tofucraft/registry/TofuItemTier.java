package baguchan.tofucraft.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum TofuItemTier implements IItemTier {
	KINU(0, 1, 0.1F, 0.0F, 2, () -> Ingredient.func_199804_a(new IItemProvider[]{TofuItems.TOFUKINU})),
	MOMEN(0, 2, 0.25F, 0.25F, 5, () -> Ingredient.func_199804_a(new IItemProvider[]{TofuItems.TOFUMOMEN})),
	SOLID(1, 131, 5.0F, 1.0F, 12, () -> Ingredient.func_199804_a(new IItemProvider[]{TofuItems.TOFUISHI})),
	METAL(2, 250, 6.0F, 2.0F, 15, () -> Ingredient.func_199804_a(new IItemProvider[]{TofuItems.TOFUMETAL})),
	TOFUDIAMOND(3, 1600, 8.0F, 4.0F, 12, () -> Ingredient.func_199804_a(new IItemProvider[]{TofuItems.TOFUDIAMOND}));

	private final int level;

	private final int uses;

	private final float speed;

	private final float damage;

	private final int enchantmentValue;

	private final LazyValue<Ingredient> repairIngredient;

	TofuItemTier(int p_i48458_3_, int p_i48458_4_, float p_i48458_5_, float p_i48458_6_, int p_i48458_7_, Supplier<Ingredient> p_i48458_8_) {
		this.level = p_i48458_3_;
		this.uses = p_i48458_4_;
		this.speed = p_i48458_5_;
		this.damage = p_i48458_6_;
		this.enchantmentValue = p_i48458_7_;
		this.repairIngredient = new LazyValue(p_i48458_8_);
	}

	public int func_200926_a() {
		return this.uses;
	}

	public float func_200928_b() {
		return this.speed;
	}

	public float func_200929_c() {
		return this.damage;
	}

	public int func_200925_d() {
		return this.level;
	}

	public int func_200927_e() {
		return this.enchantmentValue;
	}

	public Ingredient func_200924_f() {
		return (Ingredient) this.repairIngredient.func_179281_c();
	}
}
