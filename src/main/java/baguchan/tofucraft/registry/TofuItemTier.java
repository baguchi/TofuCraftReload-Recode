package baguchan.tofucraft.registry;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum TofuItemTier implements Tier {
	KINU(0, 1, 0.1F, 0.0F, 2, () -> Ingredient.of(TofuItems.TOFUKINU.get())),
	MOMEN(0, 1, 0.25F, 0.25F, 5, () -> Ingredient.of(TofuItems.TOFUMOMEN.get())),
	SOLID(1, 126, 5.0F, 1.0F, 12, () -> Ingredient.of(TofuItems.TOFUISHI.get())),
	METAL(2, 245, 6.0F, 2.0F, 16, () -> Ingredient.of(TofuItems.TOFUMETAL.get())),
	TOFUDIAMOND(3, 1724, 8.5F, 5.0F, 18, () -> Ingredient.of(TofuItems.TOFUDIAMOND.get()));

	private final int level;

	private final int uses;

	private final float speed;

	private final float damage;

	private final int enchantmentValue;

	private final LazyLoadedValue<Ingredient> repairIngredient;

	TofuItemTier(int p_i48458_3_, int p_i48458_4_, float p_i48458_5_, float p_i48458_6_, int p_i48458_7_, Supplier<Ingredient> p_i48458_8_) {
		this.level = p_i48458_3_;
		this.uses = p_i48458_4_;
		this.speed = p_i48458_5_;
		this.damage = p_i48458_6_;
		this.enchantmentValue = p_i48458_7_;
		this.repairIngredient = new LazyLoadedValue(p_i48458_8_);
	}

	@Override
	public int getLevel() {
		return level;
	}

	public int getUses() {
		return this.uses;
	}

	public float getSpeed() {
		return this.speed;
	}

	public float getAttackDamageBonus() {
		return this.damage;
	}

	public int level() {
		return this.level;
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
