package baguchan.tofucraft.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TofuFoods {
	public static final Food TOFU = (new Food.Builder()).nutrition(2).saturationMod(0.12F).fast().build();

	public static final Food ISHITOFU = (new Food.Builder()).nutrition(2).saturationMod(0.1F).fast().build();

	public static final Food TOFUHELL = (new Food.Builder()).nutrition(2).saturationMod(0.12F).alwaysEat().effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 600), 1.0F).build();

	public static final Food TOFUSOUL = (new Food.Builder()).nutrition(2).saturationMod(0.12F).fast().build();

	public static final Food TOFUGRILLED = (new Food.Builder()).nutrition(3).saturationMod(0.15F).fast().build();

	public static final Food TOFUZUNDA = (new Food.Builder()).nutrition(3).saturationMod(0.2F).fast().build();

	public static final Food BOILED_EDAMAME = (new Food.Builder()).nutrition(1).saturationMod(0.1F).fast().build();

	public static final Food TOFU_HAMBURG = (new Food.Builder()).nutrition(6).saturationMod(0.6F).build();

	public static final Food RAW_TOFUFISH = (new Food.Builder()).nutrition(1).saturationMod(0.1F).build();

	public static final Food COOKED_TOFUFISH = (new Food.Builder()).nutrition(5).saturationMod(0.6F).build();

	public static final Food TOFUCOOKIE = (new Food.Builder()).nutrition(1).saturationMod(0.1F).build();

	public static final Food SOYSTICK = (new Food.Builder()).nutrition(4).saturationMod(0.3F).fast().build();

	public static final Food SALTYMELON = (new Food.Builder()).nutrition(2).saturationMod(0.32F).build();

	public static final Food KINAKO_MANJU = (new Food.Builder()).nutrition(2).saturationMod(0.24F).fast().build();

	public static final Food ZUNDA_MANJU = (new Food.Builder()).nutrition(3).saturationMod(0.26F).fast().build();

	public static final Food NETHER_MANJU = (new Food.Builder()).nutrition(3).saturationMod(0.26F).fast().alwaysEat().effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 600), 1.0F).build();

	public static final Food SOUL_MANJU = (new Food.Builder()).nutrition(3).saturationMod(0.26F).fast().alwaysEat().effect(() -> new EffectInstance(Effects.ABSORPTION, 1200), 1.0F).build();

	public static final Food SOY_CHOCOLATE = (new Food.Builder()).nutrition(2).saturationMod(0.16F).fast().build();
}
