package baguchi.tofucraft.world;

import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WorldData;

public class TofuLevelData extends DerivedLevelData {
	private final ServerLevelData wrapped;

	public TofuLevelData(WorldData worldData, ServerLevelData levelData) {
		super(worldData, levelData);
		this.wrapped = levelData;
	}

	@Override
	public long getDayTime() {
		return this.wrapped.getDayTime();
	}

	@Override
	public void setDayTime(long pTime) {
		this.wrapped.setDayTime(pTime);
	}

	@Override
	public void setClearWeatherTime(int pTime) {
		this.wrapped.setClearWeatherTime(pTime);
	}

	@Override
	public void setRaining(boolean pRaining) {
		this.wrapped.setRaining(pRaining);
	}

	/**
	 * Sets the number of ticks until rain.
	 */
	@Override
	public void setRainTime(int pTime) {
		this.wrapped.setRainTime(pTime);
	}

	@Override
	public void setThundering(boolean pThundering) {
		this.wrapped.setThundering(pThundering);
	}

	/**
	 * Defines the number of ticks until next thunderbolt.
	 */
	@Override
	public void setThunderTime(int pTime) {
		this.wrapped.setThunderTime(pTime);
	}
}