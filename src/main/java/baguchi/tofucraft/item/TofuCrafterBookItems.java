package baguchi.tofucraft.item;

import baguchi.tofucraft.client.ClientProxy;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class TofuCrafterBookItems extends Item {
	public TofuCrafterBookItems(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		if (p_41432_.isClientSide()) {
			ClientProxy.PROXY.openBookGUI();
		}
		return InteractionResult.SUCCESS;
	}
}
