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

public class NarrowEscapeTrigger extends SimpleCriterionTrigger<NarrowEscapeTrigger.Instance> {

	public static final Identifier ID = TofuCraftReload.prefix("narrow_escape");

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	@Override
	public Codec<Instance> codec() {
		return Instance.CODEC;
	}

	public record Instance(
			Optional<Holder<LootItemCondition>> player) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<Instance> CODEC = RecordCodecBuilder.create((i) -> i.group(LootItemCondition.CODEC.optionalFieldOf("player").forGetter(Instance::player)).apply(i, Instance::new));
	}

	public static Criterion<Instance> get() {
		return TofuAdvancements.NARROW_ESCAPE_TRIGGER.get().createCriterion(new NarrowEscapeTrigger.Instance(Optional.empty()));
	}
}