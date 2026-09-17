package baguchi.tofucraft.advancements;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuAdvancements;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

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

	public record Instance(
			Optional<Holder<LootItemCondition>> player) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<Instance> CODEC = RecordCodecBuilder.create((i) -> i.group(LootItemCondition.CODEC.optionalFieldOf("player").forGetter(Instance::player)).apply(i, Instance::new));
	}

	public static Criterion<Instance> get() {
		return TofuAdvancements.TOO_COLD.get().createCriterion(new Instance(Optional.empty()));
	}
}