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

public class ChiliDistractionTrigger extends SimpleCriterionTrigger<ChiliDistractionTrigger.Instance> {

	public static final ResourceLocation ID = TofuCraftReload.prefix("chili_distraction");

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	@Override
	public Codec<Instance> codec() {
		return Instance.CODEC;
	}

	public record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<ChiliDistractionTrigger.Instance> CODEC = RecordCodecBuilder.create((p_311988_) -> {
			return p_311988_.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(ChiliDistractionTrigger.Instance::player)).apply(p_311988_, ChiliDistractionTrigger.Instance::new);
		});
		@Override
		public Optional<ContextAwarePredicate> player() {
			return this.player;
		}
	}

	public static Criterion<ChiliDistractionTrigger.Instance> get() {
		return TofuAdvancements.CHILI_DISTRACTION.get().createCriterion(new ChiliDistractionTrigger.Instance(Optional.empty()));
	}
}