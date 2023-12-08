package baguchan.tofucraft.advancements;

import baguchan.tofucraft.TofuCraftReload;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;

import java.util.Optional;

public class MyTofuChildTrigger extends SimpleCriterionTrigger<MyTofuChildTrigger.Instance> {

	public static final ResourceLocation ID = TofuCraftReload.prefix("my_tofu_child");


	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	@Override
	public Codec<MyTofuChildTrigger.Instance> codec() {
		return MyTofuChildTrigger.Instance.CODEC;
	}

	public static class Instance implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<MyTofuChildTrigger.Instance> CODEC = RecordCodecBuilder.create((p_311988_) -> {
			return p_311988_.group(ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(MyTofuChildTrigger.Instance::player)).apply(p_311988_, MyTofuChildTrigger.Instance::new);
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