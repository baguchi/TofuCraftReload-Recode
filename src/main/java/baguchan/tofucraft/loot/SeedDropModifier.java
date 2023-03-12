package baguchan.tofucraft.loot;

import baguchan.tofucraft.registry.TofuItems;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class SeedDropModifier extends LootModifier {

    /**
     * This loot modifier adds an item to the loot table, given the conditions specified.
     */
    protected SeedDropModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        List<Item> seeds = Lists.newArrayList(TofuItems.SEEDS_SOYBEANS.get(), TofuItems.SEEDS_RICE.get());
        generatedLoot.add(new ItemStack(seeds.get((int) (Math.random() * seeds.size()))));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SeedDropModifier> {
        @Override
        public SeedDropModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            return new SeedDropModifier(ailootcondition);
        }

        @Override
        public JsonObject write(SeedDropModifier instance) {
            return new JsonObject();
        }
    }
}