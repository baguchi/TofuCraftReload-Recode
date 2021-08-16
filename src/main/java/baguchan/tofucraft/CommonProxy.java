package baguchan.tofucraft;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CommonProxy {
	public BlockEntity getRefrencedTE() {
		return null;
	}

	@OnlyIn(Dist.CLIENT)
	public void setRefrencedTE(BlockEntity te) {
	}
}
