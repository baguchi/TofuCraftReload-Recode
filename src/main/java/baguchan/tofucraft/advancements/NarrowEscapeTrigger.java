package baguchan.tofucraft.advancements;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuAdvancements;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class NarrowEscapeTrigger extends SimpleCriterionTrigger<NarrowEscapeTrigger.Instance> {

	public static final ResourceLocation ID = TofuCraftReload.prefix("narrow_escape");

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	@Override
	public Codec<Instance> codec() {
		return Instance.CODEC;
	}

	public record Instance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
		public static final Codec<NarrowEscapeTrigger.Instance> CODEC = RecordCodecBuilder.create((p_311988_) -> {
			return p_311988_.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(NarrowEscapeTrigger.Instance::player)).apply(p_311988_, NarrowEscapeTrigger.Instance::new);
		});

		@Override
		public Optional<ContextAwarePredicate> player() {
			return this.player;
		}
	}

	public static Criterion<NarrowEscapeTrigger.Instance> get() {
		return TofuAdvancements.NARROW_ESCAPE_TRIGGER.get().createCriterion(new NarrowEscapeTrigger.Instance(Optional.empty()));
	}
}