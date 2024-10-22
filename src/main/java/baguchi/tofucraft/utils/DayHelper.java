package baguchi.tofucraft.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DayHelper {
	public static boolean isHalloween() {
		LocalDate localdate = LocalDate.now();
		int i = localdate.get(ChronoField.DAY_OF_MONTH);
		int j = localdate.get(ChronoField.MONTH_OF_YEAR);
		if ((j == 10 && i == 31)) {
			return true;
		}
		return false;
	}
}
