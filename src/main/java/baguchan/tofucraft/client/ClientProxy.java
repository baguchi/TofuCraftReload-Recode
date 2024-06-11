package baguchan.tofucraft.client;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ClientProxy {

	public static final ClientProxy PROXY = new ClientProxy();
	public static BlockEntity refrencedTileEntity;

	@OnlyIn(Dist.CLIENT)
	public BlockEntity getRefrencedTE() {
		return refrencedTileEntity;
	}

	@OnlyIn(Dist.CLIENT)
	public void setRefrencedTE(BlockEntity te) {
		refrencedTileEntity = te;
	}
}
