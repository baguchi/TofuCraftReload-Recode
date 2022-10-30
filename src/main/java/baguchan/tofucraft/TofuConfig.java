package baguchan.tofucraft;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class TofuConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static class Common {

		public final ForgeConfigSpec.BooleanValue travelerTofunianSpawn;

		public Common(ForgeConfigSpec.Builder builder) {
			travelerTofunianSpawn = builder
					.translation(TofuCraftReload.MODID + ".config.travelerTofunianSpawn")
					.comment("Spawn Traveler Tofunian.")
					.define("Traveler Tofunian Spawn", false);
		}
	}
}