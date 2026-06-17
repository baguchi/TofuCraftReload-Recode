package baguchi.tofucraft.advancements;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuAdvancements;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class NightmaresEchoTrigger extends SimpleCriterionTrigger<NightmaresEchoTrigger.Instance> {

	public static final Identifier ID = TofuCraftReload.prefix("nightmares_echo");

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	@Override
	public Codec<NightmaresEchoTrigger.Instance> codec() {
		return NightmaresEchoTrigger.Instance.CODEC;
	}

	public record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<NightmaresEchoTrigger.Instance> CODEC = RecordCodecBuilder.create((p_311988_) -> {
			return p_311988_.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(NightmaresEchoTrigger.Instance::player)).apply(p_311988_, NightmaresEchoTrigger.Instance::new);
		});
		@Override
		public Optional<ContextAwarePredicate> player() {
			return this.player;
		}
	}

	public static Criterion<Instance> get() {
		return TofuAdvancements.NIGHTMARES_ECHO.get().createCriterion(new NightmaresEchoTrigger.Instance(Optional.empty()));
	}
}