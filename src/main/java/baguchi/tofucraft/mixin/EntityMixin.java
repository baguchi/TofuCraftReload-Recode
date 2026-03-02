package baguchi.tofucraft.mixin;

import baguchi.tofucraft.registry.TofuFluidTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class)
public abstract class EntityMixin implements net.neoforged.neoforge.common.extensions.IEntityExtension {

	@Shadow
	public abstract boolean isAttackable();

	@Shadow
	public abstract DamageSources damageSources();

	@Shadow
	public abstract boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount);

	@Shadow
	public abstract double getFluidTypeHeight(FluidType type);

	@Shadow
	@Final
	protected RandomSource random;

	@Inject(method = "baseTick",
			at = @At(value = "TAIL"))
	public void baseTick(CallbackInfo ci) {
		Entity entity = (Entity) (Object) this;
		if (this.tofuCraftReload_Recode$isInDoubanjang() && this.isAttackable()) {
			if (this.level() instanceof ServerLevel serverlevel
					&& this.hurtServer(serverlevel, this.damageSources().lava(), 2.0F)) {
			}
		}
	}

	@Unique
	public boolean tofuCraftReload_Recode$isInDoubanjang() {
		return this.getFluidTypeHeight(TofuFluidTypes.DOUBANJIANG.get()) > 0.0F;
	}

	@Shadow
	public Level level() {
		return null;
	}
}
