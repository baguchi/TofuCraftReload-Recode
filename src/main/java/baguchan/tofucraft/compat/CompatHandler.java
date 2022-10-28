package baguchan.tofucraft.compat;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class CompatHandler {
	//support hunter illager's bounce
	public static final ResourceKey<Enchantment> HUNTERILLAGER_BOUNCE = ResourceKey.create(Registry.ENCHANTMENT_REGISTRY, new ResourceLocation("hunterillager", "bounce"));
}
