package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.TFMiningTurret;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TFMinerTurretItem extends Item {
	public TFMinerTurretItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		if (context.getLevel().getBlockState(context.getClickedPos()).is(TofuBlocks.TF_MINER.get())) {

			ItemStack stack = context.getItemInHand();
			if (!level.isClientSide()) {
				stack.shrink(1);
				TFMiningTurret tfMiningTurret = new TFMiningTurret(TofuEntityTypes.TF_MINING_TURRET.get(), level);
				tfMiningTurret.setPos(pos.above().getCenter());
				tfMiningTurret.setHomePos(pos.above());

				level.addFreshEntity(tfMiningTurret);
			}
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.CONSUME;
	}

	public void appendHoverText(ItemStack p_41157_, @Nullable Level p_41158_, List<Component> p_41159_, TooltipFlag p_41160_) {
		p_41159_.add(Component.translatable("item.tofucraft.tf_miner_turret.tip"));
	}
}
