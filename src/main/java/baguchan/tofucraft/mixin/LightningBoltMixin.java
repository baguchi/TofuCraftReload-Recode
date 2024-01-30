package baguchan.tofucraft.mixin;


import baguchan.tofucraft.block.TofuTerrainBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin extends Entity {

	@Shadow
	private int life;

	@Shadow
	protected abstract BlockPos getStrikePosition();

	public LightningBoltMixin(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LightningBolt;gameEvent(Lnet/minecraft/world/level/gameevent/GameEvent;)V", shift = At.Shift.AFTER))
	public void tick(CallbackInfo callbackInfo) {
		if (this.life == 2) {
			makeZundaOnLightningStrike(this.level(), this.getStrikePosition());
		}
	}

	private static void makeZundaOnLightningStrike(Level p_147151_, BlockPos p_147152_) {
		BlockState blockstate = p_147151_.getBlockState(p_147152_);
		BlockPos blockpos;
		BlockState blockstate1;
		if (blockstate.is(Blocks.LIGHTNING_ROD)) {
			blockpos = p_147152_.relative(blockstate.getValue(LightningRodBlock.FACING).getOpposite());
			blockstate1 = p_147151_.getBlockState(blockpos);
		} else {
			blockpos = p_147152_;
			blockstate1 = blockstate;
		}

		if (blockstate1.getBlock() instanceof TofuTerrainBlock) {
			p_147151_.setBlockAndUpdate(blockpos, TofuBlocks.TOFU_TERRAIN_ZUNDA.get().defaultBlockState());
			BlockPos.MutableBlockPos blockpos$mutableblockpos = p_147152_.mutable();
			int i = p_147151_.random.nextInt(3) + 3;

			for (int j = 0; j < i; ++j) {
				int k = p_147151_.random.nextInt(8) + 1;
				randomWalkCleaningCopper(p_147151_, blockpos, blockpos$mutableblockpos, k);
			}
		}
	}

	private static void randomWalkCleaningCopper(Level p_147146_, BlockPos p_147147_, BlockPos.MutableBlockPos p_147148_, int p_147149_) {
		p_147148_.set(p_147147_);

		for (int i = 0; i < p_147149_; ++i) {
			Optional<BlockPos> optional = randomStepZunda(p_147146_, p_147148_);
			if (optional.isEmpty()) {
				break;
			}

			p_147148_.set(optional.get());
		}
	}

	private static Optional<BlockPos> randomStepZunda(Level p_147154_, BlockPos p_147155_) {
		for (BlockPos blockpos : BlockPos.randomInCube(p_147154_.random, 10, p_147155_, 1)) {
			BlockState blockstate = p_147154_.getBlockState(blockpos);
			if (blockstate.getBlock() instanceof TofuTerrainBlock) {
				if (TofuTerrainBlock.canBeGrass(blockstate, p_147154_, blockpos)) {
					p_147154_.setBlockAndUpdate(blockpos, TofuBlocks.TOFU_TERRAIN_ZUNDA.get().defaultBlockState());
				}
				return Optional.of(blockpos);
			}
		}

		return Optional.empty();
	}
}
