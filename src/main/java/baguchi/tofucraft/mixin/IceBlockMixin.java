package baguchi.tofucraft.mixin;

import baguchi.tofucraft.utils.TofuBlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin {

	@Shadow
	protected abstract void melt(BlockState p_54169_, Level p_54170_, BlockPos p_54171_);

	@Inject(method = "randomTick", at = @At(value = "TAIL"))
	public void randomTick(BlockState p_221355_, ServerLevel p_221356_, BlockPos p_221357_, RandomSource p_221358_, CallbackInfo ci) {
		if (TofuBlockUtil.isSaltAround(p_221356_, p_221357_)) {
			this.melt(p_221355_, p_221356_, p_221357_);
		}
	}
}
