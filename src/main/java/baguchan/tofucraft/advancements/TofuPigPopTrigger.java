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

public class TofuPigPopTrigger extends SimpleCriterionTrigger<TofuPigPopTrigger.Instance> {

	public static final ResourceLocation ID = TofuCraftReload.prefix("tofupig_pop");

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	@Override
	public Codec<TofuPigPopTrigger.Instance> codec() {
		return TofuPigPopTrigger.Instance.CODEC;
	}

	public record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TofuPigPopTrigger.Instance> CODEC = RecordCodecBuilder.create((p_311988_) -> {
			return p_311988_.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TofuPigPopTrigger.Instance::player)).apply(p_311988_, TofuPigPopTrigger.Instance::new);
		});
		@Override
		public Optional<ContextAwarePredicate> player() {
			return this.player;
		}
	}

	public static Criterion<TofuPigPopTrigger.Instance> get() {
		return TofuAdvancements.TOFUPIG_POP.get().createCriterion(new Instance(Optional.empty()));
	}
}