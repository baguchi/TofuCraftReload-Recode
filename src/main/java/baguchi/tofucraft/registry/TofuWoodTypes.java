package baguchi.tofucraft.registry;

import net.minecraft.world.level.block.state.properties.WoodType;

public class TofuWoodTypes {
	public static final WoodType LEEK = WoodType.register(new WoodType("tofucraft:leek", TofuBlockSetTypes.LEEK));
	public static final WoodType LEEK_GREEN = WoodType.register(new WoodType("tofucraft:leek_green", TofuBlockSetTypes.LEEK_GREEN));


	public static final WoodType TOFU_STEM = WoodType.register(new WoodType("tofucraft:tofu_stem", TofuBlockSetTypes.TOFU_STEM));
}
