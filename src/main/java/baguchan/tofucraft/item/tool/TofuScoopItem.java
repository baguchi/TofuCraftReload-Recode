package baguchan.tofucraft.item.tool;

import baguchan.tofucraft.block.SuspiciousTofuTerrainBlock;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TofuScoopItem extends Item {

	public TofuScoopItem(Properties group) {
		super(group);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		ItemStack stack = context.getItemInHand();
		if (context.getLevel().getBlockState(context.getClickedPos()).is(TofuTags.Blocks.SOFT_TOFU)) {
			ItemStack stack2 = new ItemStack(Item.BY_BLOCK.get(context.getLevel().getBlockState(context.getClickedPos()).getBlock()));
			worldIn.levelEvent(2001, context.getClickedPos(), Block.getId(worldIn.getBlockState(context.getClickedPos())));
			worldIn.removeBlock(context.getClickedPos(), false);
			if (!worldIn.isClientSide()) {
				if (context.getPlayer() != null)
					stack.hurtAndBreak(1, (LivingEntity) context.getPlayer(), LivingEntity.getSlotForHand(context.getHand()));
				double d0 = (worldIn.random.nextFloat() * 0.5F) + 0.25D;
				double d1 = (worldIn.random.nextFloat() * 0.5F);
				double d2 = (worldIn.random.nextFloat() * 0.5F) + 0.25D;
				ItemEntity itementity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, stack2);
				itementity.setDefaultPickUpDelay();
				worldIn.addFreshEntity(itementity);
			}
			return InteractionResult.SUCCESS;
		}
		Player player = context.getPlayer();
		if (player != null && this.calculateHitResult(player).getType() == HitResult.Type.BLOCK) {
			player.startUsingItem(context.getHand());
		}

		return InteractionResult.CONSUME;
	}

	public UseAnim getUseAnimation(ItemStack p_273490_) {
		return UseAnim.BRUSH;
	}

	public int getUseDuration(ItemStack p_272765_) {
		return 200;
	}

	public void onUseTick(Level p_273467_, LivingEntity p_273619_, ItemStack p_273316_, int p_273101_) {
		if (p_273101_ >= 0 && p_273619_ instanceof Player player) {
			HitResult hitresult = this.calculateHitResult(player);
			if (hitresult instanceof BlockHitResult blockhitresult) {
				if (hitresult.getType() == HitResult.Type.BLOCK) {
					int i = this.getUseDuration(p_273316_) - p_273101_ + 1;
					boolean flag = i % 10 == 5;
					if (flag) {
						BlockPos blockpos = blockhitresult.getBlockPos();
						BlockState blockstate = p_273467_.getBlockState(blockpos);
						HumanoidArm humanoidarm = p_273619_.getUsedItemHand() == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
						this.spawnDustParticles(p_273467_, blockhitresult, blockstate, p_273619_.getViewVector(0.0F), humanoidarm);
						Block $$18 = blockstate.getBlock();
						SoundEvent soundevent;
						if ($$18 instanceof SuspiciousTofuTerrainBlock) {
							SuspiciousTofuTerrainBlock brushableblock = (SuspiciousTofuTerrainBlock) $$18;
							soundevent = brushableblock.getBrushSound();
						} else {
							soundevent = SoundEvents.BRUSH_GENERIC;
						}

						p_273467_.playSound(player, blockpos, soundevent, SoundSource.BLOCKS);
						if (!p_273467_.isClientSide()) {
							BlockEntity blockentity = p_273467_.getBlockEntity(blockpos);
							if ($$18 instanceof SuspiciousTofuTerrainBlock) {
								if (blockentity instanceof BrushableBlockEntity) {
									BrushableBlockEntity brushableblockentity = (BrushableBlockEntity) blockentity;
									boolean flag1 = brushableblockentity.brush(p_273467_.getGameTime(), player, blockhitresult.getDirection());
									if (flag1) {
										EquipmentSlot equipmentslot = p_273316_.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
										p_273316_.hurtAndBreak(1, p_273619_, equipmentslot);
									}
								}
							}
						}
					}

					return;
				}
			}

			p_273619_.releaseUsingItem();
		} else {
			p_273619_.releaseUsingItem();
		}
	}

	private HitResult calculateHitResult(Player p_305856_) {
		return ProjectileUtil.getHitResultOnViewVector(
				p_305856_, p_281111_ -> !p_281111_.isSpectator() && p_281111_.isPickable(), p_305856_.blockInteractionRange()
		);
	}

	public void spawnDustParticles(Level p_278327_, BlockHitResult p_278272_, BlockState p_278235_, Vec3 p_278337_, HumanoidArm p_285071_) {
		double d0 = 3.0D;
		int i = p_285071_ == HumanoidArm.RIGHT ? 1 : -1;
		int j = p_278327_.getRandom().nextInt(7, 12);
		BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.BLOCK, p_278235_);
		Direction direction = p_278272_.getDirection();
		TofuScoopItem.DustParticlesDelta brushitem$dustparticlesdelta = TofuScoopItem.DustParticlesDelta.fromDirection(p_278337_, direction);
		Vec3 vec3 = p_278272_.getLocation();

		for (int k = 0; k < j; ++k) {
			p_278327_.addParticle(blockparticleoption, vec3.x - (double) (direction == Direction.WEST ? 1.0E-6F : 0.0F), vec3.y, vec3.z - (double) (direction == Direction.NORTH ? 1.0E-6F : 0.0F), brushitem$dustparticlesdelta.xd() * (double) i * 3.0D * p_278327_.getRandom().nextDouble(), 0.0D, brushitem$dustparticlesdelta.zd() * (double) i * 3.0D * p_278327_.getRandom().nextDouble());
		}

	}

	static record DustParticlesDelta(double xd, double yd, double zd) {
		private static final double ALONG_SIDE_DELTA = 1.0D;
		private static final double OUT_FROM_SIDE_DELTA = 0.1D;

		public static TofuScoopItem.DustParticlesDelta fromDirection(Vec3 p_273421_, Direction p_272987_) {
			double d0 = 0.0D;
			TofuScoopItem.DustParticlesDelta brushitem$dustparticlesdelta;
			switch (p_272987_) {
				case DOWN:
				case UP:
					brushitem$dustparticlesdelta = new TofuScoopItem.DustParticlesDelta(p_273421_.z(), 0.0D, -p_273421_.x());
					break;
				case NORTH:
					brushitem$dustparticlesdelta = new TofuScoopItem.DustParticlesDelta(1.0D, 0.0D, -0.1D);
					break;
				case SOUTH:
					brushitem$dustparticlesdelta = new TofuScoopItem.DustParticlesDelta(-1.0D, 0.0D, 0.1D);
					break;
				case WEST:
					brushitem$dustparticlesdelta = new TofuScoopItem.DustParticlesDelta(-0.1D, 0.0D, -1.0D);
					break;
				case EAST:
					brushitem$dustparticlesdelta = new TofuScoopItem.DustParticlesDelta(0.1D, 0.0D, 1.0D);
					break;
				default:
					throw new IncompatibleClassChangeError();
			}

			return brushitem$dustparticlesdelta;
		}
	}
}
