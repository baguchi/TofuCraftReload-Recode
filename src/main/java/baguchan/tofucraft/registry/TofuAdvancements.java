package baguchan.tofucraft.registry;

import baguchan.tofucraft.advancements.ChiliDistractionTrigger;
import baguchan.tofucraft.advancements.TofuPigPopTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class TofuAdvancements {
	public static final ChiliDistractionTrigger CHILI_DISTRACTION = CriteriaTriggers.register(new ChiliDistractionTrigger());
	public static final TofuPigPopTrigger TOFUPIG_POP = CriteriaTriggers.register(new TofuPigPopTrigger());


	public static void init() {
	}
}
