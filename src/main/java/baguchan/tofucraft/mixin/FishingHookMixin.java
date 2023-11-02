package baguchan.tofucraft.mixin;

import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuLootTables;
import baguchan.tofucraft.registry.TofuParticleTypes;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = FishingHook.class, remap = false)
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
		if (!this.level().isClientSide && player != null && !this.shouldStopFishing(player)) {
			net.neoforged.neoforge.event.entity.player.ItemFishedEvent event = null;
			if (this.level() != null && this.level().dimension().equals(TofuDimensions.tofu_world) && this.nibble > 0) {
				LootParams lootparams = (new LootParams.Builder((ServerLevel) this.level())).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.TOOL, p_37157_).withParameter(LootContextParams.THIS_ENTITY, this).withParameter(LootContextParams.KILLER_ENTITY, this.getOwner()).withParameter(LootContextParams.THIS_ENTITY, this).withLuck((float) this.luck + player.getLuck()).create(LootContextParamSets.FISHING);
				LootTable loottable = this.level().getServer().getLootData().getLootTable(TofuLootTables.TOFU_WORLD_FISHING_LOOT_TABLE);
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


	@Inject(method = ("catchingFish"), at = @At("HEAD"), cancellable = true)
	private void tofucraftreload$showSplash(BlockPos blockPos, CallbackInfo callbackInfo) {
		ServerLevel serverlevel = (ServerLevel) this.level();
		int i = 1;
		BlockPos blockpos = blockPos.above();
		if (this.random.nextFloat() < 0.25F && this.level().isRainingAt(blockpos)) {
			++i;
		}

		if (this.random.nextFloat() < 0.5F && !this.level().canSeeSky(blockpos)) {
			--i;
		}

		if (this.nibble <= 0) {
			if (this.timeUntilHooked > 0) {
				this.timeUntilHooked -= i;
				if (this.timeUntilHooked > 0) {
					this.fishAngle += (float) this.random.triangle(0.0D, 9.188D);
					float f = this.fishAngle * ((float) Math.PI / 180F);
					float f1 = Mth.sin(f);
					float f2 = Mth.cos(f);
					double d0 = this.getX() + (double) (f1 * (float) this.timeUntilHooked * 0.1F);
					double d1 = (double) ((float) Mth.floor(this.getY()) + 1.0F);
					double d2 = this.getZ() + (double) (f2 * (float) this.timeUntilHooked * 0.1F);
					FluidState fluidstate = serverlevel.getFluidState(BlockPos.containing(d0, d1 - 1.0D, d2));
					if (fluidstate.is(TofuTags.Fluids.SOYMILK)) {
						if (this.random.nextFloat() < 0.15F) {
							serverlevel.sendParticles(ParticleTypes.BUBBLE, d0, d1 - (double) 0.1F, d2, 1, (double) f1, 0.1D, (double) f2, 0.0D);
						}

						float f3 = f1 * 0.04F;
						float f4 = f2 * 0.04F;
						serverlevel.sendParticles(TofuParticleTypes.SOYMILK_SPLASH.get(), d0, d1, d2, 0, (double) f4, 0.01D, (double) (-f3), 1.0D);
						serverlevel.sendParticles(TofuParticleTypes.SOYMILK_SPLASH.get(), d0, d1, d2, 0, (double) (-f4), 0.01D, (double) f3, 1.0D);
					}
				} else {
					this.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
					double d3 = this.getY() + 0.5D;
					serverlevel.sendParticles(ParticleTypes.BUBBLE, this.getX(), d3, this.getZ(), (int) (1.0F + this.getBbWidth() * 20.0F), (double) this.getBbWidth(), 0.0D, (double) this.getBbWidth(), (double) 0.2F);
					serverlevel.sendParticles(TofuParticleTypes.SOYMILK_SPLASH.get(), this.getX(), d3, this.getZ(), (int) (1.0F + this.getBbWidth() * 20.0F), (double) this.getBbWidth(), 0.0D, (double) this.getBbWidth(), (double) 0.2F);
					this.nibble = Mth.nextInt(this.random, 20, 40);
				}
			} else if (this.timeUntilLured > 0) {
				float f5 = 0.15F;
				if (this.timeUntilLured < 20) {
					f5 += (float) (20 - this.timeUntilLured) * 0.05F;
				} else if (this.timeUntilLured < 40) {
					f5 += (float) (40 - this.timeUntilLured) * 0.02F;
				} else if (this.timeUntilLured < 60) {
					f5 += (float) (60 - this.timeUntilLured) * 0.01F;
				}
				if (this.random.nextFloat() < f5) {
					float f6 = Mth.nextFloat(this.random, 0.0F, 360.0F) * ((float) Math.PI / 180F);
					float f7 = Mth.nextFloat(this.random, 25.0F, 60.0F);
					double d4 = this.getX() + (double) (Mth.sin(f6) * f7) * 0.1D;
					double d5 = (double) ((float) Mth.floor(this.getY()) + 1.0F);
					double d6 = this.getZ() + (double) (Mth.cos(f6) * f7) * 0.1D;
					FluidState fluidstate = serverlevel.getFluidState(BlockPos.containing(d4, d5 - 1.0D, d6));
					if (fluidstate.is(TofuTags.Fluids.SOYMILK)) {
						serverlevel.sendParticles(TofuParticleTypes.SOYMILK_SPLASH.get(), d4, d5, d6, 2 + this.random.nextInt(2), (double) 0.1F, 0.0D, (double) 0.1F, 0.0D);
					}
				}
			}
		}
	}

	@Redirect(method = ("getOpenWaterTypeForBlock"), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
	private boolean getOpenWaterTypeForBlock(FluidState instance, TagKey<Fluid> p_205071_) {
		if (instance.is(TofuTags.Fluids.SOYMILK)) {
			return true;
		}
		return instance.is(p_205071_);
	}
}
