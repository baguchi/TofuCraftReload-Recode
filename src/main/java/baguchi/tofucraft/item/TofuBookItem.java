package baguchi.tofucraft.item;

import baguchi.tofucraft.client.ClientProxy;
import baguchi.tofucraft.network.OpenTofuBookPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class TofuBookItem extends Item {
	public TofuBookItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		if (p_41432_.isClientSide()) {
			ClientProxy.handleOpenPageTest(p_41433_);
		}
		return super.use(p_41432_, p_41433_, p_41434_);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		if (state.is(Blocks.LECTERN)) {
			if (LecternBlock.tryPlaceBook(context.getPlayer(), level, pos, state, context.getItemInHand())) {
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	/**
	 * Event handler to control the lectern GUI
	 */
	public static void interactWithBlock(PlayerInteractEvent.RightClickBlock event) {
		Level world = event.getLevel();
		// client side has no access to the book, so just skip
		if (world.isClientSide()) {
			return;
		}
		// must be a lectern, and have the TE
		BlockPos pos = event.getPos();
		BlockState state = world.getBlockState(pos);
		if (state.is(Blocks.LECTERN)) {
			if (world.getBlockEntity(pos) instanceof LecternBlockEntity lectern) {
				ItemStack book = lectern.getBook();
				if (!book.isEmpty() && book.getItem() instanceof TofuBookItem) {

					OpenTofuBookPacket message = new OpenTofuBookPacket(event.getEntity());
					PacketDistributor.sendToPlayersTrackingEntityAndSelf(event.getEntity(), message);

					event.setCanceled(true);
				}

			}
		}
	}
}
