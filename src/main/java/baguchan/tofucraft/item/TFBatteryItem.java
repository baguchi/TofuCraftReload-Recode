package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyContained;
import baguchan.tofucraft.api.tfenergy.IEnergyExtractable;
import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class TFBatteryItem extends Item implements IEnergyInsertable, IEnergyContained, IEnergyExtractable {

	public TFBatteryItem(Properties p_43089_) {
		super(p_43089_);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stackBattery = player.getItemInHand(hand);
		ItemStack stack = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
		if (stack.getItem() instanceof IEnergyInsertable energyContained) {
			this.drain(stackBattery, energyContained.fill(stack, 50, false), false);
			return InteractionResultHolder.success(stackBattery);
		}

		return super.use(level, player, hand);
	}

	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		if (!simulate) {
			int calculated2 = Math.min(energy, getEnergyMax(inst) - getEnergy(inst));
			setEnergy(inst, getEnergy(inst) + calculated2);
			return calculated2;
		}
		return 0;
	}

	public static final String TAG_TF = "tf_energy";
	public static final String TAG_TFMAX = "tf_energymax";


	@Override
	public int getEnergy(ItemStack inst) {
		return inst.getTag() != null && inst.getTag().contains(TAG_TF) ? inst.getTag().getInt(TAG_TF) : 0;
	}

	@Override
	public int getEnergyMax(ItemStack inst) {
		return 5000;
	}

	@Override
	public void setEnergy(ItemStack inst, int amount) {
		inst.getOrCreateTag().putInt(TAG_TF, amount);
	}

	@Override
	public void setEnergyMax(ItemStack inst, int amount) {
	}

	private boolean getShowState(ItemStack stack) {
		return getEnergy(stack) != 0;
	}

	@Override
	public boolean isBarVisible(ItemStack p_150899_) {
		return getShowState(p_150899_) || super.isBarVisible(p_150899_);
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		return getShowState(stack) ? Math.round(((float) getEnergy(stack)) / (float) getEnergyMax(stack) * 13.0F) : super.getBarWidth(stack);
	}

	@Override
	public int getBarColor(ItemStack p_150901_) {
		return getShowState(p_150901_) ? Color.white.getRGB() : super.getBarColor(p_150901_);
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
		p_41423_.add(Component.translatable("tooltip.tofucraft.energy", getEnergy(p_41421_), getEnergyMax(p_41421_)));

	}

	@Override
	public int drain(ItemStack inst, int amount, boolean simulate) {
		if (!simulate) {
			int calculated2 = Math.min(getEnergy(inst), amount);
			setEnergy(inst, getEnergy(inst) - calculated2);
			return calculated2;
		}
		return 0;
	}
}