package baguchan.tofucraft.tileentity;

import baguchan.tofucraft.registry.TofuTileEntitys;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class TofuBedTileEntity extends TileEntity {
	public TofuBedTileEntity() {
		super(TofuTileEntitys.TOFUBED);
	}

	public SUpdateTileEntityPacket func_189518_D_() {
		return new SUpdateTileEntityPacket(this.field_174879_c, 11, func_189517_E_());
	}
}
