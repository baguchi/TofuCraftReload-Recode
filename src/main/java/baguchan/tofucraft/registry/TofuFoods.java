package baguchan.tofucraft.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TofuFoods {
	public static final Food TOFU = (new Food.Builder()).func_221456_a(2).func_221454_a(0.12F).func_221457_c().func_221453_d();

	public static final Food ISHITOFU = (new Food.Builder()).func_221456_a(2).func_221454_a(0.1F).func_221457_c().func_221453_d();

	public static final Food TOFUHELL = (new Food.Builder()).func_221456_a(2).func_221454_a(0.12F).func_221455_b().effect(() -> new EffectInstance(Effects.field_76426_n, 600), 1.0F).func_221453_d();

	public static final Food TOFUSOUL = (new Food.Builder()).func_221456_a(2).func_221454_a(0.12F).func_221457_c().func_221453_d();

	public static final Food TOFUGRILLED = (new Food.Builder()).func_221456_a(3).func_221454_a(0.15F).func_221457_c().func_221453_d();

	public static final Food TOFUZUNDA = (new Food.Builder()).func_221456_a(3).func_221454_a(0.2F).func_221457_c().func_221453_d();

	public static final Food BOILED_EDAMAME = (new Food.Builder()).func_221456_a(1).func_221454_a(0.1F).func_221457_c().func_221453_d();

	public static final Food TOFU_HAMBURG = (new Food.Builder()).func_221456_a(6).func_221454_a(0.6F).func_221453_d();

	public static final Food RAW_TOFUFISH = (new Food.Builder()).func_221456_a(1).func_221454_a(0.1F).func_221453_d();

	public static final Food COOKED_TOFUFISH = (new Food.Builder()).func_221456_a(5).func_221454_a(0.6F).func_221453_d();

	public static final Food TOFUCOOKIE = (new Food.Builder()).func_221456_a(1).func_221454_a(0.1F).func_221453_d();

	public static final Food SOYSTICK = (new Food.Builder()).func_221456_a(4).func_221454_a(0.3F).func_221457_c().func_221453_d();

	public static final Food SALTYMELON = (new Food.Builder()).func_221456_a(2).func_221454_a(0.32F).func_221453_d();

	public static final Food KINAKO_MANJU = (new Food.Builder()).func_221456_a(2).func_221454_a(0.24F).func_221457_c().func_221453_d();

	public static final Food ZUNDA_MANJU = (new Food.Builder()).func_221456_a(3).func_221454_a(0.26F).func_221457_c().func_221453_d();

	public static final Food NETHER_MANJU = (new Food.Builder()).func_221456_a(3).func_221454_a(0.26F).func_221457_c().effect(() -> new EffectInstance(Effects.field_76426_n, 600), 1.0F).func_221453_d();

	public static final Food SOUL_MANJU = (new Food.Builder()).func_221456_a(3).func_221454_a(0.26F).func_221457_c().effect(() -> new EffectInstance(Effects.field_76444_x, 1200), 1.0F).func_221453_d();

	public static final Food SOY_CHOCOLATE = (new Food.Builder()).func_221456_a(2).func_221454_a(0.16F).func_221457_c().func_221453_d();
}
