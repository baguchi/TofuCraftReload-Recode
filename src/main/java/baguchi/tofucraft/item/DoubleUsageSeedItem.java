package baguchi.tofucraft.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class DoubleUsageSeedItem extends BlockItem {
	private final Supplier<Block> sprouts;
	private final Supplier<Block> block;

	public DoubleUsageSeedItem(Supplier<Block> blockSupplier, Supplier<Block> sprouts, Properties p_220227_) {
		super(Blocks.AIR, p_220227_);
		this.sprouts = sprouts;
		this.block = blockSupplier;
	}

	@Override
	public InteractionResult place(BlockPlaceContext context) {
		if (!this.block.get().isEnabled(context.getLevel().enabledFeatures())) {
			return super.place(context);
		} else if (!context.canPlace()) {
			return super.place(context);
		} else {
			BlockPlaceContext blockplacecontext = this.updatePlacementContext(context);
			if (blockplacecontext == null) {
				return super.place(context);
			} else {
				BlockState blockstate = this.getSproutPlacementState(blockplacecontext);
				if (blockstate == null) {
					return super.place(context);
				} else if (!this.placeBlock(blockplacecontext, blockstate)) {
					return super.place(context);
				} else {
					BlockPos blockpos = blockplacecontext.getClickedPos();
					Level level = blockplacecontext.getLevel();
					Player player = blockplacecontext.getPlayer();
					ItemStack itemstack = blockplacecontext.getItemInHand();
					BlockState blockstate1 = level.getBlockState(blockpos);
					if (blockstate1.is(blockstate.getBlock())) {
						blockstate1 = this.updateBlockStateFromTag(blockpos, level, itemstack, blockstate1);
						this.updateCustomBlockEntityTag(blockpos, level, player, itemstack, blockstate1);
						updateBlockEntityComponents(level, blockpos, itemstack);
						blockstate1.getBlock().setPlacedBy(level, blockpos, blockstate1, player, itemstack);
						if (player instanceof ServerPlayer) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
						}
					}

					SoundType soundtype = blockstate1.getSoundType(level, blockpos, context.getPlayer());
					level.playSound(
							player,
							blockpos,
							this.getPlaceSound(blockstate1, level, blockpos, context.getPlayer()),
							SoundSource.BLOCKS,
							(soundtype.getVolume() + 1.0F) / 2.0F,
							soundtype.getPitch() * 0.8F
					);
					level.gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(player, blockstate1));
					itemstack.consume(1, player);
					return InteractionResult.SUCCESS_SERVER;
				}
			}
		}
	}

	private static void updateBlockEntityComponents(Level level, BlockPos poa, ItemStack stack) {
		BlockEntity blockentity = level.getBlockEntity(poa);
		if (blockentity != null) {
			blockentity.applyComponentsFromItemStack(stack);
			blockentity.setChanged();
		}
	}


	@Nullable
	protected BlockState getSproutPlacementState(BlockPlaceContext context) {
		BlockState blockstate = this.getSprouts().getStateForPlacement(context);
		return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
	}

	private BlockState updateBlockStateFromTag(BlockPos pos, Level level, ItemStack stack, BlockState state) {
		BlockItemStateProperties blockitemstateproperties = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
		if (blockitemstateproperties.isEmpty()) {
			return state;
		} else {
			BlockState blockstate = blockitemstateproperties.apply(state);
			if (blockstate != state) {
				level.setBlock(pos, blockstate, 2);
			}

			return blockstate;
		}
	}

	@Override
	public Block getBlock() {
		return this.block.get();
	}

	public Block getSprouts() {
		return sprouts.get();
	}
}
