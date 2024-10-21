package baguchan.tofucraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultConfig;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.minecraft.world.level.block.entity.vault.VaultSharedData;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class TofuVaultBlock extends VaultBlock {
	public TofuVaultBlock(Properties p_324605_) {
		super(p_324605_);
	}

	@Override
	public InteractionResult useItemOn(
			ItemStack p_324161_, BlockState p_323816_, Level p_324403_, BlockPos p_324623_, Player p_324219_, InteractionHand p_324416_, BlockHitResult p_324261_
	) {
		if (p_324161_.isEmpty() || p_323816_.getValue(STATE) != VaultState.ACTIVE) {
			return InteractionResult.TRY_WITH_EMPTY_HAND;
		} else if (p_324403_ instanceof ServerLevel serverlevel) {
			if (serverlevel.getBlockEntity(p_324623_) instanceof VaultBlockEntity vaultblockentity) {
				tryInsertKey(
						serverlevel,
						p_324623_,
						p_323816_,
						vaultblockentity.getConfig(),
						vaultblockentity.getServerData(),
						vaultblockentity.getSharedData(),
						p_324219_,
						p_324161_
				);
				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.TRY_WITH_EMPTY_HAND;
			}
		} else {
			return InteractionResult.CONSUME;
		}
	}

	public static void tryInsertKey(
			ServerLevel p_323533_,
			BlockPos p_323777_,
			BlockState p_324589_,
			VaultConfig p_323660_,
			VaultServerData p_323829_,
			VaultSharedData p_324341_,
			Player p_324373_,
			ItemStack p_324551_
	) {
		VaultState vaultstate = p_324589_.getValue(VaultBlock.STATE);
		if (canEjectReward(p_323660_, vaultstate)) {
			if (!isValidToInsert(p_323660_, p_324551_)) {
				playInsertFailSound(p_323533_, p_323829_, p_323777_, SoundEvents.VAULT_INSERT_ITEM_FAIL);
			} else {
				List<ItemStack> list = resolveItemsToEject(p_323533_, p_323660_, p_323777_, p_324373_);
				if (!list.isEmpty()) {
					p_324373_.awardStat(Stats.ITEM_USED.get(p_324551_.getItem()));
					p_324551_.consume(p_323660_.keyItem().getCount(), p_324373_);
					unlock(p_323533_, p_324589_, p_323777_, p_323660_, p_323829_, p_324341_, list);
				}
			}
		}
	}

	private static void unlock(
			ServerLevel p_323501_,
			BlockState p_323758_,
			BlockPos p_323773_,
			VaultConfig p_324195_,
			VaultServerData p_324600_,
			VaultSharedData p_324277_,
			List<ItemStack> p_324574_
	) {
		p_324600_.setItemsToEject(p_324574_);
		p_324277_.setDisplayItem(p_324600_.getNextItemToEject());
		p_324600_.pauseStateUpdatingUntil(p_323501_.getGameTime() + 14L);
		setVaultState(p_323501_, p_323773_, p_323758_, p_323758_.setValue(VaultBlock.STATE, VaultState.UNLOCKING), p_324195_, p_324277_);
	}

	static void setVaultState(
			ServerLevel p_324091_, BlockPos p_324620_, BlockState p_323759_, BlockState p_324027_, VaultConfig p_324140_, VaultSharedData p_323624_
	) {
		VaultState vaultstate = p_323759_.getValue(VaultBlock.STATE);
		VaultState vaultstate1 = p_324027_.getValue(VaultBlock.STATE);
		p_324091_.setBlock(p_324620_, p_324027_, 3);
		vaultstate.onTransition(p_324091_, p_324620_, vaultstate1, p_324140_, p_323624_, p_324027_.getValue(VaultBlock.OMINOUS));
	}

	private static List<ItemStack> resolveItemsToEject(ServerLevel p_323877_, VaultConfig p_324041_, BlockPos p_324255_, Player p_324347_) {
		LootTable loottable = p_323877_.getServer().reloadableRegistries().getLootTable(p_324041_.lootTable());
		LootParams lootparams = new LootParams.Builder(p_323877_)
				.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p_324255_))
				.withLuck(p_324347_.getLuck())
				.withParameter(LootContextParams.THIS_ENTITY, p_324347_)
				.create(LootContextParamSets.VAULT);
		return loottable.getRandomItems(lootparams);
	}

	private static void playInsertFailSound(ServerLevel p_324555_, VaultServerData p_324017_, BlockPos p_324186_, SoundEvent p_350407_) {
		if (p_324555_.getGameTime() >= p_324017_.getLastInsertFailTimestamp() + 15L) {
			p_324555_.playSound(null, p_324186_, p_350407_, SoundSource.BLOCKS);
			p_324017_.setLastInsertFailTimestamp(p_324555_.getGameTime());
		}
	}

	private static boolean canEjectReward(VaultConfig p_323595_, VaultState p_324160_) {
		return !p_323595_.keyItem().isEmpty() && p_324160_ != VaultState.INACTIVE;
	}


	private static boolean isValidToInsert(VaultConfig p_323488_, ItemStack p_324101_) {
		return ItemStack.isSameItemSameComponents(p_324101_, p_323488_.keyItem()) && p_324101_.getCount() >= p_323488_.keyItem().getCount();
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_324543_, BlockState p_323652_) {
		return new VaultBlockEntity(p_324543_, p_323652_);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_323525_, BlockState p_324070_, BlockEntityType<T> p_323541_) {
		return p_323525_ instanceof ServerLevel serverlevel
				? createTickerHelper(
				p_323541_,
				BlockEntityType.VAULT,
				(p_323957_, p_324322_, p_323828_, p_323769_) -> VaultBlockEntity.Server.tick(
						serverlevel, p_324322_, p_323828_, p_323769_.getConfig(), p_323769_.getServerData(), p_323769_.getSharedData()
				)
		)
				: createTickerHelper(
				p_323541_,
				BlockEntityType.VAULT,
				(p_324290_, p_323926_, p_323941_, p_323489_) -> VaultBlockEntity.Client.tick(
						p_324290_, p_323926_, p_323941_, p_323489_.getClientData(), p_323489_.getSharedData()
				)
		);
	}
}
