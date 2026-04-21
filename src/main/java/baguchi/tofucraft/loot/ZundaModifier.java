package baguchi.tofucraft.loot;

import baguchi.tofucraft.registry.TofuEffects;
import baguchi.tofucraft.registry.TofuItems;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class ZundaModifier extends LootModifier {

	public static final Supplier<MapCodec<ZundaModifier>> CODEC = Suppliers.memoize(() ->
			RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
					.apply(inst, ZundaModifier::new)));

	protected ZundaModifier(LootItemCondition[] conditionsIn, int priority) {
		super(conditionsIn, priority);
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		List<ItemStack> seeds = Lists.newArrayList();
		if (context.hasParameter(LootContextParams.THIS_ENTITY) && context.hasParameter(LootContextParams.ATTACKING_ENTITY) && context.hasParameter(LootContextParams.DAMAGE_SOURCE)) {
			if (context.getParameter(LootContextParams.THIS_ENTITY) instanceof LivingEntity living) {
				if (living.hasEffect(TofuEffects.ZUNDAFIED)) {
					if (living instanceof Creeper creeper) {
						seeds.add(new ItemStack(TofuItems.UNSTABLE_ZUNDAMA, 1 + context.getRandom().nextInt(1)));
					} else if (living instanceof Slime slime) {
						seeds.add(new ItemStack(TofuItems.ZUNDAMA, slime.getSize()));
					} else {
						seeds.add(new ItemStack(TofuItems.ZUNDA.get(), (int) (1 + (living.getMaxHealth() / 30))));
					}
				}
			}
		}

		if (!seeds.isEmpty()) {
			seeds.forEach(item -> {
				generatedLoot.add(item);
			});
		}
		return generatedLoot;
	}

	@Override
	public MapCodec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
