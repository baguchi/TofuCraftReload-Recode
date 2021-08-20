package baguchan.tofucraft.world;

public class SeedHolder {
	private static long seed = 0L;

	public static void putInSeed(long seedInput) {
		seed = seedInput;
	}

	public static long getSeed() {
		return seed;
	}
}