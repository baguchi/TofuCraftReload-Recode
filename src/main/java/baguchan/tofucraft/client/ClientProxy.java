package baguchan.tofucraft.client;

import baguchan.tofucraft.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {
	public static TileEntity refrencedTileEntity;

	@OnlyIn(Dist.CLIENT)
	public TileEntity getRefrencedTE() {
		return refrencedTileEntity;
	}

	@OnlyIn(Dist.CLIENT)
	public void setRefrencedTE(TileEntity te) {
		refrencedTileEntity = te;
	}
}
