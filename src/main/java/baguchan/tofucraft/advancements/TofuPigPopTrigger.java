package baguchan.tofucraft.advancements;

import baguchan.tofucraft.TofuCraftReload;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class TofuPigPopTrigger extends SimpleCriterionTrigger<TofuPigPopTrigger.Instance> {

    public static final ResourceLocation ID = TofuCraftReload.prefix("tofupig_pop");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Instance createInstance(JsonObject json, EntityPredicate.Composite player, DeserializationContext ctx) {
        return new Instance(player);
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, (instance) -> true);
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(EntityPredicate.Composite player) {
            super(TofuPigPopTrigger.ID, player);
        }

        public static Instance killThemAll() {
            return new Instance(EntityPredicate.Composite.ANY);
        }
    }
}