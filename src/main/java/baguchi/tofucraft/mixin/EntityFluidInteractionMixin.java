package baguchi.tofucraft.mixin;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Set;

@Mixin(EntityFluidInteraction.class)
public class EntityFluidInteractionMixin {
	@Shadow
	@Final
	public Map<TagKey<Fluid>, EntityFluidInteraction.Tracker> trackerByFluid;

	@Inject(method = "<init>", at = @At(value = "TAIL"))
	public void init(Set<Fluid> fluids, CallbackInfo ci) {
		for (TagKey<Fluid> fluid : Set.of(TofuTags.Fluids.SOYMILK, TofuTags.Fluids.WATER_LIKE, TofuTags.Fluids.DOUBANJIANG)) {
			this.trackerByFluid.put(fluid, new EntityFluidInteraction.Tracker());
		}
	}
}