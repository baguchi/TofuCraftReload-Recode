package baguchi.tofucraft.entity.behaviors;

import baguchi.tofucraft.registry.TofuEffects;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.warden.Warden;

public class CoughTask extends Behavior<Warden> {
	private static final IntProvider SNIFF_COOLDOWN = UniformInt.of(400, 600);

	private long nextCoughStart;

	public CoughTask() {
		super(ImmutableMap.of());
	}

	protected void start(ServerLevel p_217743_, Warden p_217744_, long p_217745_) {
		Brain<Warden> brain = p_217744_.getBrain();

		if (p_217745_ > nextCoughStart + 100L) {
			nextCoughStart = p_217745_;
		}

		brain.setMemoryWithExpiry(MemoryModuleType.SNIFF_COOLDOWN, Unit.INSTANCE, (long) SNIFF_COOLDOWN.sample(p_217743_.getRandom()));
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel p_22538_, Warden p_22539_) {
		return p_22539_.hasEffect(TofuEffects.COUGH);
	}
}