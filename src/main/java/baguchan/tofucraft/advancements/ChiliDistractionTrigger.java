package baguchan.tofucraft.advancements;

import baguchan.tofucraft.TofuCraftReload;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

	public static class Instance implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<Instance> CODEC = RecordCodecBuilder.create((p_311988_) -> {
			return p_311988_.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player)).apply(p_311988_, Instance::new);
		});
		private final Optional<ContextAwarePredicate> player;
		public Instance(Optional<ContextAwarePredicate> player) {
			this.player = player;
		}

		@Override
		public Optional<ContextAwarePredicate> player() {
			return this.player;
		}
	}
}