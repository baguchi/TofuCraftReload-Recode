package baguchan.tofucraft.item;

import net.minecraft.world.effect.MobEffects;

public class RamuneSoymilkBottleItem extends SoymilkBottleItem {
	public RamuneSoymilkBottleItem(Properties properties) {
		super(MobEffects.WATER_BREATHING, MobEffects.REGENERATION, properties);
	}
}
