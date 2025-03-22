package baguchi.tofucraft.client;

import baguchi.tofucraft.client.screen.book.TofuCraftersBookScreen;
import net.minecraft.client.Minecraft;
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

	public void openBookGUI() {
		Minecraft.getInstance().setScreen(new TofuCraftersBookScreen());
	}
}
