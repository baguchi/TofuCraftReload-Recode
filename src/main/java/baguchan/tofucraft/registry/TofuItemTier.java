package baguchan.tofucraft.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum TofuItemTier implements Tier {
	KINU(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1, 0.1F, 0.0F, 2, () -> Ingredient.of(TofuItems.TOFUKINU.get())),
	MOMEN(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1, 0.25F, 0.25F, 5, () -> Ingredient.of(TofuItems.TOFUMOMEN.get())),
	SOLID(BlockTags.INCORRECT_FOR_STONE_TOOL, 126, 5.0F, 1.0F, 12, () -> Ingredient.of(TofuItems.TOFUISHI.get())),
	METAL(BlockTags.INCORRECT_FOR_IRON_TOOL, 245, 6.0F, 2.0F, 16, () -> Ingredient.of(TofuItems.TOFUMETAL.get())),
	TOFUDIAMOND(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2224, 8.5F, 5.0F, 18, () -> Ingredient.of(TofuItems.TOFUDIAMOND.get()));

	private final TagKey<Block> incorrectBlocksForDrops;

	private final int uses;

	private final float speed;

	private final float damage;

	private final int enchantmentValue;

	private final LazyLoadedValue<Ingredient> repairIngredient;

	TofuItemTier(TagKey<Block> p_336171_, int p_i48458_4_, float p_i48458_5_, float p_i48458_6_, int p_i48458_7_, Supplier<Ingredient> p_i48458_8_) {
		this.incorrectBlocksForDrops = p_336171_;
		this.uses = p_i48458_4_;
		this.speed = p_i48458_5_;
		this.damage = p_i48458_6_;
		this.enchantmentValue = p_i48458_7_;
		this.repairIngredient = new LazyLoadedValue(p_i48458_8_);
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

	@Override
	public TagKey<Block> getIncorrectBlocksForDrops() {
		return this.incorrectBlocksForDrops;
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
