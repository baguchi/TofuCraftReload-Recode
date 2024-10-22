package baguchi.tofucraft.inventory.slot;

import baguchi.tofucraft.blockentity.SaltFurnaceBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SaltFurnaceResultSlot extends Slot {
	private final Player player;

	public SaltFurnaceResultSlot(Player p_39542_, Container p_39543_, int p_39544_, int p_39545_, int p_39546_) {
		super(p_39543_, p_39544_, p_39545_, p_39546_);
		this.player = p_39542_;
	}

	@Override
	public boolean mayPlace(ItemStack p_39553_) {
		return false;
	}

	@Override
	public void onTake(Player p_150563_, ItemStack p_150564_) {
		this.checkTakeAchievements(p_150564_);
		super.onTake(p_150563_, p_150564_);
	}

	@Override
	protected void onQuickCraft(ItemStack p_39555_, int p_39556_) {
		this.checkTakeAchievements(p_39555_);
	}

	@Override
	protected void checkTakeAchievements(ItemStack p_39558_) {
		if (this.player instanceof ServerPlayer && this.container instanceof SaltFurnaceBlockEntity) {
			((SaltFurnaceBlockEntity) this.container).popExperience((ServerPlayer) this.player, p_39558_);
		}
	}
}
