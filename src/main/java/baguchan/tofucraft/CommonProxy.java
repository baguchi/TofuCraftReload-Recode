package baguchan.tofucraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CommonProxy {
	public TileEntity getRefrencedTE() {
		return null;
	}

	@OnlyIn(Dist.CLIENT)
	public void setRefrencedTE(TileEntity te) {
	}
}
