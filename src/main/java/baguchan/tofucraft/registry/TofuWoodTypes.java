package baguchan.tofucraft.registry;

import net.minecraft.world.level.block.state.properties.WoodType;

public class TofuWoodTypes {
	public static final WoodType LEEKSTEM = WoodType.register(new WoodType("tofucraft:leekstem", TofuBlockSetTypes.LEEKSTEM));

	public static final WoodType TOFUSTEM = WoodType.register(new WoodType("tofucraft:tofustem", TofuBlockSetTypes.TOFUSTEM));

	public static void init() {

	}
}
