package baguchan.tofucraft.registry;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class TofuFoods {
	public static final FoodProperties TOFU = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).fast().build();

	public static final FoodProperties ISHITOFU = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).fast().build();
	public static final FoodProperties TOFUHELL = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600), 1.0F).build();
	public static final FoodProperties TOFUSOUL = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200), 1.0F).build();
	public static final FoodProperties TOFUGRILLED = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast().build();
	public static final FoodProperties TOFUZUNDA = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast().build();
	public static final FoodProperties TOFUMISO = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().build();
	public static final FoodProperties TOFUDRIED = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.12F).fast().build();
	public static final FoodProperties TOFUFRIED = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().build();
	public static final FoodProperties TOFUFRIED_POUCH = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().build();
	public static final FoodProperties AGEDASHI_TOFU = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().build();


	public static final FoodProperties BOILED_EDAMAME = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).fast().build();

	public static final FoodProperties TOFU_HAMBURG = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).build();

	public static final FoodProperties RAW_TOFUFISH = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();

	public static final FoodProperties COOKED_TOFUFISH = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();

	public static final FoodProperties TOFUCOOKIE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
	public static final FoodProperties TTTBURGER = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.85F).build();

	public static final FoodProperties SOYSTICK = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().build();

	public static final FoodProperties SALTYMELON = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.35F).build();

	public static final FoodProperties CHILI = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();

	public static final FoodProperties KINAKO_MANJU = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.35F).fast().build();
	public static final FoodProperties ZUNDA_MANJU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.35F).fast().build();
	public static final FoodProperties NETHER_MANJU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.35F).fast().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600), 1.0F).build();
	public static final FoodProperties SOUL_MANJU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.35F).fast().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200), 1.0F).build();

	public static final FoodProperties SOY_CHOCOLATE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).fast().build();

	public static final FoodProperties ZUNDA_MOCHI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties KINAKO_MOCHI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties ONIGIRI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties ONIGIRI_SALT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.625F).build();
	public static final FoodProperties YAKIONIGIRI_MISO = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.65F).build();
	public static final FoodProperties YAKIONIGIRI_SHOYU = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.65F).build();


	public static final FoodProperties TOMATO_SOYBEAN_STEW = stew(5).build();
	public static final FoodProperties YUDOFU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast().build();
	public static final FoodProperties EDAMAME_RICE = stew(5).build();

	//Tofu delight
	public static final FoodProperties TOFU_EGG = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.32F).fast().build();
	public static final FoodProperties SOYSAUSE_RAMEN = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.85F).build();
	public static final FoodProperties SOY_CHEESE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.45F).build();


	private static FoodProperties.Builder stew(int p_150384_) {
		return (new FoodProperties.Builder()).nutrition(p_150384_).saturationMod(0.6F);
	}
}
