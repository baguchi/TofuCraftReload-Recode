package baguchan.tofucraft.registry;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class TofuFoods {
	public static final FoodProperties TOFU = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).fast().build();

	public static final FoodProperties ISHITOFU = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).fast().build();
	public static final FoodProperties TOFUSTEAK = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).fast().build();
	public static final FoodProperties OAGE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast().build();

	public static final FoodProperties TOFUHELL = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600), 1.0F).build();
	public static final FoodProperties TOFUSOUL = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200), 1.0F).build();
	public static final FoodProperties TOFUGRILLED = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast().build();
	public static final FoodProperties TOFUZUNDA = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast().build();
	public static final FoodProperties TOFUMISO = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().build();
	public static final FoodProperties TOFUDRIED = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.12F).fast().build();
	public static final FoodProperties TOFUFRIED = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().build();
	public static final FoodProperties TOFUSMOKE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).fast().build();
	public static final FoodProperties TOFUFRIED_POUCH = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().build();
	public static final FoodProperties AGEDASHI_TOFU = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().build();
	public static final FoodProperties TOFUANNIN = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.12F).fast().build();

	public static final FoodProperties CHIKUWA = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().build();
	public static final FoodProperties TOFU_CHIKUWA = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).build();

	public static final FoodProperties BOILED_EDAMAME = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).fast().build();

	public static final FoodProperties TOFU_HAMBURG = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).build();

	public static final FoodProperties RAW_TOFUFISH = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();

	public static final FoodProperties COOKED_TOFUFISH = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();

	public static final FoodProperties MISODENGAKU = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).fast().build();
	public static final FoodProperties TOFUCOOKIE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
	public static final FoodProperties TTTBURGER = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.85F).build();
	public static final FoodProperties MEAT_WRAPPED_YUBA = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.825F).build();


	public static final FoodProperties SOYSTICK = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().build();

	public static final FoodProperties MISOSOUP = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties SALTYMELON = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.35F).build();

	public static final FoodProperties CHILI = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();
	public static final FoodProperties MABODOFU = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.9F).build();
	public static final FoodProperties NANBAN = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.7F).build();
	public static final FoodProperties NANBANTOFU = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();

	public static final FoodProperties FUKUMENI = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
	public static final FoodProperties KOYADOFUSTEW = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.35F).build();

	public static final FoodProperties KINAKO_MANJU = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.35F).fast().build();
	public static final FoodProperties ZUNDA_MANJU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.35F).fast().build();
	public static final FoodProperties NETHER_MANJU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.35F).fast().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600), 1.0F).build();
	public static final FoodProperties SOUL_MANJU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.35F).fast().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200), 1.0F).build();

	public static final FoodProperties SOY_CHOCOLATE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).fast().build();

	public static final FoodProperties ZUNDA_MOCHI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties KINAKO_MOCHI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties CRIMSON_SOUP = stew(6).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600), 1.0F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100), 0.1F).build();

	public static final FoodProperties ONIGIRI = (new FoodProperties.Builder()).nutrition(3).fast().saturationMod(0.6F).build();
	public static final FoodProperties ONIGIRI_SALT = (new FoodProperties.Builder()).nutrition(3).fast().saturationMod(0.625F).build();
	public static final FoodProperties YAKIONIGIRI_MISO = (new FoodProperties.Builder()).nutrition(3).fast().saturationMod(0.65F).build();
	public static final FoodProperties YAKIONIGIRI_SHOYU = (new FoodProperties.Builder()).nutrition(3).fast().saturationMod(0.65F).build();
	public static final FoodProperties RICE_BURGER = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.85F).build();
	public static final FoodProperties RICE_NATTO = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.65F).build();
	public static final FoodProperties RICE_NATTOLEEK = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.675F).build();
	public static final FoodProperties RICE_TOFU = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.625F).build();
	public static final FoodProperties RICE_SOBORO_TOFU = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.65F).build();
	public static final FoodProperties GOHEIMOCHI = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.65F).build();

	public static final FoodProperties INARI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.8F).build();

	public static final FoodProperties OKARASTICK = (new FoodProperties.Builder()).nutrition(2).fast().saturationMod(0.1F).fast().build();

	public static final FoodProperties SOBOROTOFUSAUTE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.8F).build();

	public static final FoodProperties PUDDING = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties PUDDING_SOYMILK = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.35F).build();
	public static final FoodProperties NIKUJAGA = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.65F).build();

	public static final FoodProperties APRICOT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
	public static final FoodProperties APRICOT_BREAD = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.65F).build();


	public static final FoodProperties TOMATO_SOYBEAN_STEW = stew(5).build();
	public static final FoodProperties YUDOFU = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast().build();
	public static final FoodProperties EDAMAME_RICE = stew(5).build();

	public static final FoodProperties TOFUSOMEN = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
	public static final FoodProperties TASTYSTEW = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.90F).build();

	public static final FoodProperties HIYAYAKKO = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
	public static final FoodProperties NATTOHIYAYAKKO = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.35F).build();

	//Tofu delight
	public static final FoodProperties TOFU_EGG = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.32F).fast().build();
	public static final FoodProperties SOYSAUSE_RAMEN = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.85F).build();
	public static final FoodProperties SOY_CHEESE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.45F).build();


	private static FoodProperties.Builder stew(int p_150384_) {
		return (new FoodProperties.Builder()).nutrition(p_150384_).saturationMod(0.6F);
	}
}
