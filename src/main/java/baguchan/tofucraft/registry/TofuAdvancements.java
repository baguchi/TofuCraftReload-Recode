package baguchan.tofucraft.registry;

import baguchan.tofucraft.advancements.ChiliDistractionTrigger;
import baguchan.tofucraft.advancements.MyTofuChildTrigger;
import baguchan.tofucraft.advancements.NightmaresEchoTrigger;
import baguchan.tofucraft.advancements.TofuPigPopTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class TofuAdvancements {
	public static final ChiliDistractionTrigger CHILI_DISTRACTION = CriteriaTriggers.register(ChiliDistractionTrigger.ID.toString(), new ChiliDistractionTrigger());
	public static final TofuPigPopTrigger TOFUPIG_POP = CriteriaTriggers.register(TofuPigPopTrigger.ID.toString(), new TofuPigPopTrigger());

	public static final NightmaresEchoTrigger NIGHTMARES_ECHO = CriteriaTriggers.register(NightmaresEchoTrigger.ID.toString(), new NightmaresEchoTrigger());
	public static final MyTofuChildTrigger MY_TOFU_CHILD = CriteriaTriggers.register(MyTofuChildTrigger.ID.toString(), new MyTofuChildTrigger());

	public static void init() {
	}
}
