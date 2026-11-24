package baguchi.tofucraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class SoymilkFluidBlock extends LiquidBlock {
	public SoymilkFluidBlock(FlowingFluid soymilk, Properties properties) {
		super(soymilk, properties);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
		super.entityInside(state, level, pos, entity, effectApplier, isPrecise);

		boolean flag = level.getBlockState(pos.below()).isSolid() && level.getBlockState(pos.below(2)).is(BlockTags.FIRE);
		boolean flag2 = level.getBlockState(pos.below()).isSolid() && level.getBlockState(pos.below(2)).isSolid() && level.getBlockState(pos.below(3)).is(BlockTags.FIRE);

		if (flag) {
			if (entity instanceof LivingEntity living && level instanceof ServerLevel serverLevel) {
				entity.hurtServer(serverLevel, entity.damageSources().campfire(), 1);
			}
		}

		if (flag2) {
			if (entity instanceof LivingEntity living && living.tickCount % 80 == 0) {
				living.heal(1);
			}
		}
	}
}
