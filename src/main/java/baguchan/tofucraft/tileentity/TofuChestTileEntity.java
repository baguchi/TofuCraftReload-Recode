package baguchan.tofucraft.tileentity;

import baguchan.tofucraft.registry.TofuTileEntitys;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TofuChestTileEntity extends ChestTileEntity {
	protected TofuChestTileEntity(TileEntityType<?> p_i48287_1_) {
		super(p_i48287_1_);
	}

	public TofuChestTileEntity() {
		this(TofuTileEntitys.TOFUCHEST);
	}
}
