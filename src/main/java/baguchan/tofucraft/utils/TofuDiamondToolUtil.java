package baguchan.tofucraft.utils;

import baguchan.tofucraft.registry.TofuEnchantments;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;

public class TofuDiamondToolUtil {
	protected static BlockHitResult rayTrace(Level level, Player playerIn, boolean useLiquids) {
		float f = playerIn.getXRot();
		float f1 = playerIn.getYRot();
		double d0 = playerIn.getX();
		double d1 = playerIn.getY() + (double) playerIn.getEyeHeight();
		double d2 = playerIn.getZ();
		Vec3 vec3d = new Vec3(d0, d1, d2);
		float f2 = Mth.cos(-f1 * 0.017453292F - (float) Math.PI);
		float f3 = Mth.sin(-f1 * 0.017453292F - (float) Math.PI);
		float f4 = -Mth.cos(-f * 0.017453292F);
		float f5 = Mth.sin(-f * 0.017453292F);
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d3 = playerIn.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE);
		Vec3 vec3d1 = vec3d.add((double) f6 * d3, (double) f5 * d3, (double) f7 * d3);
		return level.clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.COLLIDER, !useLiquids ? ClipContext.Fluid.NONE : ClipContext.Fluid.ANY, playerIn));
	}

	public static ImmutableList<BlockPos> calcAOEBlocks(ItemStack stack, Level world, Player player, BlockPos origin, int width, int height, int depth) {
		return calcAOEBlocks(stack, world, player, origin, width, height, depth, -1);
	}

	public static ImmutableList<BlockPos> calcAOEBlocks(ItemStack stack, Level world, Player player, BlockPos origin, int width, int height, int depth, int distance) {
		if (stack.isEmpty()) {
			return ImmutableList.of();
		}
		// find out where the player is hitting the block
		BlockState state = world.getBlockState(origin);
		if (state.isAir()) {
			// what are you DOING?
			return ImmutableList.of();
		}
		// raytrace to get the side, but has to result in the same block
		BlockHitResult mop = rayTrace(world, player, true);
		if (mop == null || !origin.equals(mop.getBlockPos())) {
			mop = rayTrace(world, player, false);
			if (mop == null || !origin.equals(mop.getBlockPos())) {
				return ImmutableList.of();
			}
		}
		// we know the block and we know which side of the block we're hitting. time to calculate the depth along the different axes
		int x, y, z;
		BlockPos start = origin;
		switch (mop.getDirection()) {
			case DOWN:
			case UP:
				// x y depends on the angle we look?
				Vec3i vec = player.getDirection().getNormal();
				x = vec.getX() * height + vec.getZ() * width;
				y = mop.getDirection().getAxisDirection().getStep() * -depth;
				z = vec.getX() * width + vec.getZ() * height;
				start = start.offset(-x / 2, 0, -z / 2);
				if (x % 2 == 0) {
					if (x > 0 && mop.getLocation().x - mop.getBlockPos().getX() > 0.5d) {
						start = start.offset(1, 0, 0);
					} else if (x < 0 && mop.getLocation().x - mop.getBlockPos().getX() < 0.5d) {
						start = start.offset(-1, 0, 0);
					}
				}
				if (z % 2 == 0) {
					if (z > 0 && mop.getLocation().z - mop.getBlockPos().getZ() > 0.5d) {
						start = start.offset(0, 0, 1);
					} else if (z < 0 && mop.getLocation().z - mop.getBlockPos().getZ() < 0.5d) {
						start = start.offset(0, 0, -1);
					}
				}
				break;
			case NORTH:
			case SOUTH:
				x = width;
				y = height;
				z = mop.getDirection().getAxisDirection().getStep() * -depth;
				start = start.offset(-x / 2, -y / 2, 0);
				if (x % 2 == 0 && mop.getLocation().x - mop.getBlockPos().getX() > 0.5d) {
					start = start.offset(1, 0, 0);
				}
				if (y % 2 == 0 && mop.getLocation().y - mop.getBlockPos().getY() > 0.5d) {
					start = start.offset(0, 1, 0);
				}
				break;
			case WEST:
			case EAST:
				x = mop.getDirection().getAxisDirection().getStep() * -depth;
				y = height;
				z = width;
				start = start.offset(-0, -y / 2, -z / 2);
				if (y % 2 == 0 && mop.getLocation().y - mop.getBlockPos().getY() > 0.5d) {
					start = start.offset(0, 1, 0);
				}
				if (z % 2 == 0 && mop.getLocation().z - mop.getBlockPos().getZ() > 0.5d) {
					start = start.offset(0, 0, 1);
				}
				break;
			default:
				x = y = z = 0;
		}

		ImmutableList.Builder<BlockPos> builder = ImmutableList.builder();
		for (int xp = start.getX(); xp != start.getX() + x; xp += x / Mth.abs(x)) {
			for (int yp = start.getY(); yp != start.getY() + y; yp += y / Mth.abs(y)) {
				for (int zp = start.getZ(); zp != start.getZ() + z; zp += z / Mth.abs(z)) {
					// don't add the origin block
					if (xp == origin.getX() && yp == origin.getY() && zp == origin.getZ()) {
						continue;
					}
					if (distance > 0 && Mth.abs(xp - origin.getX()) + Mth.abs(yp - origin.getY()) + Mth.abs(
							zp - origin.getZ()) > distance) {
						continue;
					}
					builder.add(new BlockPos(xp, yp, zp));
				}
			}
		}

		return builder.build();
	}

	public static void onBlockStartBreak(ItemStack stack, Level level, Block blockDestroyed, BlockPos pos, Player owner) {
		int lvl = EnchantmentHelper.getEnchantmentLevel(TofuEnchantments.BATCH.get(), owner);
		if (lvl > 0) {
			ImmutableList<BlockPos> poses = calcAOEBlocks(stack, level, owner, pos, 1 + lvl * 2, 1 + lvl * 2, lvl);
			for (BlockPos extraPos : poses) {
				breakExtraBlock(stack, owner.level(), owner, extraPos, pos);
			}
		}

	}

	private static boolean canBreakExtraBlock(ItemStack stack, Level world, Player player, BlockPos pos, BlockPos refPos) {
		if (world.isEmptyBlock(pos)) {
			return false;
		}
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (stack.getDestroySpeed(state) <= 1.0F)
			return false;

		BlockState refState = world.getBlockState(refPos);
		FluidState fluidState = world.getFluidState(refPos);
		float refStrength = refState.getDestroySpeed(world, refPos);
		float strength = state.getDestroySpeed(world, pos);

		if (!CommonHooks.canEntityDestroy(world, pos, player) || refStrength / strength > 10f) {
			return false;
		}

		if (player.isCreative()) {
			block.playerWillDestroy(world, pos, state, player);
			if (block.onDestroyedByPlayer(state, world, pos, player, false, fluidState)) {
				block.destroy(world, pos, state);
			}
			// send update to client
			if (!world.isClientSide()) {
				if (player instanceof ServerPlayer && ((ServerPlayer) player).connection != null) {
					((ServerPlayer) player).connection.send(new ClientboundBlockUpdatePacket(world, pos));
				}
			}
			return false;
		}
		return true;
	}


	public static void breakExtraBlock(ItemStack stack, Level level, Player player, BlockPos pos, BlockPos refPos) {
		if (!canBreakExtraBlock(stack, level, player, pos, refPos) ||
				level.getBlockState(pos).getBlock() != level.getBlockState(refPos).getBlock()) {
			return;
		}

		BlockState state = level.getBlockState(pos);
		FluidState fluidState = level.getFluidState(refPos);
		Block block = state.getBlock();

		stack.mineBlock(level, state, pos, player);

		if (!level.isClientSide()) {

			int xp = CommonHooks.onBlockBreakEvent(level, ((ServerPlayer) player).gameMode.getGameModeForPlayer(), (ServerPlayer) player, pos);
			if (xp == -1) {
				return;
			}


			BlockEntity tileEntity = level.getBlockEntity(pos);
			if (block.onDestroyedByPlayer(state, level, pos, player, true, fluidState)) {
				block.destroy(level, pos, state);
				block.playerDestroy(level, player, pos, state, tileEntity, stack);
				if (level instanceof ServerLevel serverLevel)
					block.popExperience(serverLevel, pos, xp);
			}

			//send breaking block update
			if (((ServerPlayer) player).connection != null) {
				((ServerPlayer) player).connection.send(new ClientboundBlockUpdatePacket(level, pos));
			}
		} else {
			level.levelEvent(2001, pos, Block.getId(state));
			if (block.onDestroyedByPlayer(state, level, pos, player, true, fluidState)) {
				block.destroy(level, pos, state);
			}
			stack.mineBlock(level, state, pos, player);

			if (stack.getCount() == 0 && stack == player.getMainHandItem()) {
				EventHooks.onPlayerDestroyItem(player, stack, InteractionHand.MAIN_HAND);
				player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
			}

			ClientPacketListener netHandlerPlayClient = Minecraft.getInstance().getConnection();
			assert netHandlerPlayClient != null;
			//send player action update
			netHandlerPlayClient.send(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.STOP_DESTROY_BLOCK, pos, Minecraft
					.getInstance().cameraEntity.getDirection()));
		}
	}
}
