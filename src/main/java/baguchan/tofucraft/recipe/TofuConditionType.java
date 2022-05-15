package baguchan.tofucraft.recipe;

import javax.annotation.Nullable;
import java.util.Locale;

public enum TofuConditionType {
	BLOCK,
	KOUYA;

	@Nullable
	public static TofuConditionType get(String nameIn) {
		for (TofuConditionType condition : values()) {
			if (condition.name().toLowerCase(Locale.ROOT).equals(nameIn))
				return condition;
		}
		return null;
	}
}
