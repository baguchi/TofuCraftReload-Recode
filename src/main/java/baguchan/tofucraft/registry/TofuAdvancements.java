package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.advancements.ChiliDistractionTrigger;
import baguchan.tofucraft.advancements.MyTofuChildTrigger;
import baguchan.tofucraft.advancements.NightmaresEchoTrigger;
import baguchan.tofucraft.advancements.TofuPigPopTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuAdvancements {
	public static final DeferredRegister<CriterionTrigger<?>> CRITERIONS_REGISTER = DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, TofuCraftReload.MODID);

	public static final Supplier<ChiliDistractionTrigger> CHILI_DISTRACTION = CRITERIONS_REGISTER.register(ChiliDistractionTrigger.ID.getPath(), () -> new ChiliDistractionTrigger());
	public static final Supplier<TofuPigPopTrigger> TOFUPIG_POP = CRITERIONS_REGISTER.register(TofuPigPopTrigger.ID.getPath(), () -> new TofuPigPopTrigger());

	public static final Supplier<NightmaresEchoTrigger> NIGHTMARES_ECHO = CRITERIONS_REGISTER.register(NightmaresEchoTrigger.ID.getPath(), () -> new NightmaresEchoTrigger());
	public static final Supplier<MyTofuChildTrigger> MY_TOFU_CHILD = CRITERIONS_REGISTER.register(MyTofuChildTrigger.ID.getPath(), () -> new MyTofuChildTrigger());

	public static void init() {
	}
}
