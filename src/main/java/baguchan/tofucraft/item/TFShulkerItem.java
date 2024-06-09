package baguchan.tofucraft.item;

import baguchan.tofucraft.client.render.item.TFShulkerBWLR;
import baguchan.tofucraft.entity.TFShulker;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class TFShulkerItem extends Item {
	public TFShulkerItem(Properties properties) {
		super(properties);
	}

	/**
	 * [CODE COPY] - {@link net.minecraft.world.item.SpawnEggItem#useOn(UseOnContext)}<br><br>
	 * Modified for Moa spawning specifically and ensuring all the Moa's NBT tags are set.
	 *
	 * @param context The {@link UseOnContext} of the usage interaction.
	 */
	@Override
	public InteractionResult useOn(UseOnContext context) {
		ItemStack itemStack = context.getItemInHand();
		Player player = context.getPlayer();
		if (player != null && !player.isShiftKeyDown()) {
			Level level = context.getLevel();
			if (!(level instanceof ServerLevel serverLevel)) {
				return InteractionResult.SUCCESS;
			} else {
				BlockPos blockPos = context.getClickedPos();
				Direction direction = context.getClickedFace();
				BlockState blockState = level.getBlockState(blockPos);
				BlockPos relativePos;
				if (blockState.getCollisionShape(level, blockPos).isEmpty()) {
					relativePos = blockPos;
				} else {
					relativePos = blockPos.relative(direction);
				}

				ItemStack spawnStack = itemStack; // Setup tags for spawning entity.
				Entity entity = TofuEntityTypes.TF_SHULKER.get().spawn(serverLevel, spawnStack, player, relativePos, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockPos, relativePos) && direction == Direction.UP);
				if (entity instanceof TFShulker tfShulker) {
					CustomData customData = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
					DyeColor dyeColor = spawnStack.get(DataComponents.BASE_COLOR);

					customData.update(compoundTag -> {
						if (compoundTag.contains("TargetPos")) {
							tfShulker.setTargetPos(NbtUtils.readBlockPos(compoundTag, "TargetPos"));
						}
					});

					if (dyeColor != null) {
						tfShulker.setVariant(Optional.ofNullable(dyeColor));
					}
					player.playSound(SoundEvents.SHULKER_CLOSE, 1F, 1F);

					level.gameEvent(player, GameEvent.ENTITY_PLACE, blockPos);
					if (!player.isCreative()) {
						itemStack.shrink(1);
					}
				}
				return InteractionResult.CONSUME;
			}
		} else {
			if (player != null) {
				BlockPos blockPos = context.getClickedPos();
				CustomData customData = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
				CompoundTag compoundTag2 = customData.copyTag();
				compoundTag2.put("TargetPos", NbtUtils.writeBlockPos(blockPos));
				CustomData.set(DataComponents.CUSTOM_DATA, itemStack, compoundTag2);
				player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 1F, 1F);
			}
			return InteractionResult.CONSUME;
		}
	}

	/**
	 * [CODE COPY] - {@link net.minecraft.world.item.SpawnEggItem#use(Level, Player, InteractionHand)}<br><br>
	 * Modified for Moa spawning specifically and ensuring all the Moa's NBT tags are set.
	 *
	 * @param level  The {@link Level} of the user.
	 * @param player The {@link Player} using this item.
	 * @param hand   The {@link InteractionHand} in which the item is being used.
	 */
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);
		BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (hitResult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(heldStack);
		} else if (!(level instanceof ServerLevel serverLevel)) {
			return InteractionResultHolder.fail(heldStack);
		} else {
			BlockPos blockpos = hitResult.getBlockPos();
			if (!(level.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
				return InteractionResultHolder.pass(heldStack);
			} else if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos, hitResult.getDirection(), heldStack)) {
				ItemStack spawnStack = heldStack;
				Entity entity = TofuEntityTypes.TF_SHULKER.get().spawn(serverLevel, spawnStack, player, blockpos, MobSpawnType.SPAWN_EGG, false, false);
				if (entity == null) {
					return InteractionResultHolder.pass(heldStack);
				} else {
					if (entity instanceof TFShulker tfShulker) {
						CustomData customData = heldStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
						DyeColor dyeColor = spawnStack.get(DataComponents.BASE_COLOR);

						customData.update(compoundTag -> {
							if (compoundTag.contains("TargetPos")) {
								tfShulker.setTargetPos(NbtUtils.readBlockPos(compoundTag, "TargetPos"));
							}
						});

						if (dyeColor != null) {
							tfShulker.setVariant(Optional.ofNullable(dyeColor));
						}
					}
					player.playSound(SoundEvents.SHULKER_CLOSE, 1F, 1F);
					player.awardStat(Stats.ITEM_USED.get(this));
					level.gameEvent(player, GameEvent.ENTITY_PLACE, blockpos);
					if (!player.isCreative()) {
						heldStack.shrink(1);
					}
					return InteractionResultHolder.consume(heldStack);
				}
			} else {
				return InteractionResultHolder.fail(heldStack);
			}
		}
	}


	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new TFShulkerBWLR();
			}
		});
	}
}
