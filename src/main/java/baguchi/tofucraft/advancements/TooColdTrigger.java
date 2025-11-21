package baguchi.tofucraft.advancements;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuAdvancements;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class TooColdTrigger extends SimpleCriterionTrigger<TooColdTrigger.Instance> {

	public static final Identifier ID = TofuCraftReload.prefix("too_cold");

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	@Override
	public Codec<TooColdTrigger.Instance> codec() {
		return TooColdTrigger.Instance.CODEC;
	}

	public record Instance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
		public static final Codec<TooColdTrigger.Instance> CODEC = RecordCodecBuilder.create((p_311988_) -> {
			return p_311988_.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TooColdTrigger.Instance::player)).apply(p_311988_, TooColdTrigger.Instance::new);
		});

		@Override
		public Optional<ContextAwarePredicate> player() {
			return this.player;
		}
	}

	public static Criterion<TooColdTrigger.Instance> get() {
		return TofuAdvancements.TOO_COLD.get().createCriterion(new Instance(Optional.empty()));
	}
}