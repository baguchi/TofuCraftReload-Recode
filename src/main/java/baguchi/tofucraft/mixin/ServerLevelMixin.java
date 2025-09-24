package baguchi.tofucraft.mixin;

import baguchi.tofucraft.utils.TofuBlockUtil;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {

	@WrapWithCondition(method = "tickPrecipitation", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z", ordinal = 0))
	public boolean tickPrecipitation(ServerLevel instance, BlockPos blockPos, BlockState blockState) {
		if (TofuBlockUtil.isSaltAround(instance, blockPos)) {
			return false;
		}
		return true;
	}
}
