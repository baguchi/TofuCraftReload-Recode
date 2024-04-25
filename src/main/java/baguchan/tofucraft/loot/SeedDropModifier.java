package baguchan.tofucraft.loot;

import baguchan.tofucraft.registry.TofuItems;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class SeedDropModifier extends LootModifier {

	public static final Supplier<MapCodec<SeedDropModifier>> CODEC = Suppliers.memoize(() ->
			RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
					.apply(inst, SeedDropModifier::new)));

	protected SeedDropModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		List<Item> seeds = Lists.newArrayList(TofuItems.SEEDS_SOYBEANS.get(), TofuItems.SEEDS_RICE.get());
		generatedLoot.add(new ItemStack(seeds.get((int) (Math.random() * seeds.size()))));
		return generatedLoot;
	}

	@Override
	public MapCodec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
