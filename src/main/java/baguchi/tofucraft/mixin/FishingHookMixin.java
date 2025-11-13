package baguchi.tofucraft.mixin;

import baguchi.tofucraft.registry.TofuDimensions;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuLootTables;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = FishingHook.class)
public abstract class FishingHookMixin extends Projectile {

	@Shadow
	private int nibble;
	@Shadow
	@Final
	private int luck;
	@Shadow
	private int timeUntilHooked;
	@Shadow
	private int timeUntilLured;
	@Shadow
	private float fishAngle;

	public FishingHookMixin(EntityType<? extends Projectile> entityType, Level world) {
		super(entityType, world);
	}

	@Redirect(method = "tick()V",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z",
					ordinal = 1))
	private boolean tofucraftreload$bobberFloat(FluidState instance, TagKey<Fluid> p_205071_) {
		return instance.is(p_205071_) || instance.is(TofuTags.Fluids.SOYMILK);
	}

	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
	private boolean tofucraftreload$CheckHeight(FluidState instance, TagKey<Fluid> p_205071_) {
		if (instance.is(TofuTags.Fluids.SOYMILK)) {
			return true;
		}
		return instance.is(p_205071_);
	}

	@Inject(method = "retrieve(Lnet/minecraft/world/item/ItemStack;)I",
			at = @At(value = "HEAD"), cancellable = true)
	private void tofucraftreload$dimensionFishingLoot(ItemStack p_37157_, CallbackInfoReturnable<Integer> cir) {
		Player player = this.getPlayerOwner();
		FishingHook fishingHook = (FishingHook) ((Object) this);
		if (!this.level().isClientSide() && player != null && !this.shouldStopFishing(player)) {
			net.neoforged.neoforge.event.entity.player.ItemFishedEvent event = null;
			if (this.level() != null && this.level().dimension().equals(TofuDimensions.tofu_world) && this.nibble > 0) {
				LootParams lootparams = new LootParams.Builder((ServerLevel) this.level())
						.withParameter(LootContextParams.ORIGIN, this.position())
						.withParameter(LootContextParams.TOOL, p_37157_)
						.withParameter(LootContextParams.THIS_ENTITY, this)
						.withParameter(LootContextParams.ATTACKING_ENTITY, this.getOwner())
						.withLuck((float) this.luck + player.getLuck())
						.create(LootContextParamSets.FISHING);
				LootTable loottable = this.level().getServer().reloadableRegistries().getLootTable(TofuLootTables.TOFU_WORLD_FISHING_LOOT_TABLE);
				List<ItemStack> list = loottable.getRandomItems(lootparams);
				event = new net.neoforged.neoforge.event.entity.player.ItemFishedEvent(list, this.onGround() ? 2 : 1, fishingHook);
				NeoForge.EVENT_BUS.post(event);
				if (event.isCanceled()) {
					this.discard();
					cir.setReturnValue(event.getRodDamage());
				}
				CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer) player, p_37157_, fishingHook, list);

				for (ItemStack itemstack : list) {
					ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), itemstack);
					double d0 = player.getX() - this.getX();
					double d1 = player.getY() - this.getY();
					double d2 = player.getZ() - this.getZ();
					double d3 = 0.1D;
					itementity.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
					this.level().addFreshEntity(itementity);
					player.level().addFreshEntity(new ExperienceOrb(player.level(), player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, this.random.nextInt(6) + 1));
					if (itemstack.is(ItemTags.FISHES) || itemstack.is(TofuItems.RAW_TOFU_FISH.get())) {
						player.awardStat(Stats.FISH_CAUGHT, 1);
					}
				}

				int i = 1;
				this.discard();
				cir.setReturnValue(event == null ? i : event.getRodDamage());
			}
		}
	}

	@Shadow
	private boolean shouldStopFishing(Player player) {
		return false;
	}

	@Shadow
	public Player getPlayerOwner() {
		return null;
	}


	@Redirect(method = ("getOpenWaterTypeForBlock"), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
	private boolean getOpenWaterTypeForBlock(FluidState instance, TagKey<Fluid> p_205071_) {
		if (instance.is(TofuTags.Fluids.SOYMILK)) {
			return true;
		}
		return instance.is(p_205071_);
	}
}
