package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.trialspawner.PlayerDetector;
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
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;

public class TofuVaultBlockEntity extends VaultBlockEntity {
	public TofuVaultBlockEntity(BlockPos p_324016_, BlockState p_324022_) {
		super(p_324016_, p_324022_);
		this.config = new VaultConfig(TofuLootTables.TOFU_VAULT_REWARD,
				4.0,
				4.5,
				new ItemStack(TofuItems.TOFU_KEY.get()),
				Optional.empty(),
				PlayerDetector.INCLUDING_CREATIVE_PLAYERS,
				PlayerDetector.EntitySelector.SELECT_FROM_LEVEL);
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
					//p_323829_.addToRewardedPlayers(p_324373_);
					//p_324341_.updateConnectedPlayersWithinRange(p_323533_, p_323777_, p_323829_, p_323660_, p_323660_.deactivationRange());
				}
			}
		}
	}

	static void setVaultState(
			ServerLevel p_324091_, BlockPos p_324620_, BlockState p_323759_, BlockState p_324027_, VaultConfig p_324140_, VaultSharedData p_323624_
	) {
		VaultState vaultstate = p_323759_.getValue(VaultBlock.STATE);
		VaultState vaultstate1 = p_324027_.getValue(VaultBlock.STATE);
		p_324091_.setBlock(p_324620_, p_324027_, 3);
		vaultstate.onTransition(p_324091_, p_324620_, vaultstate1, p_324140_, p_323624_, p_324027_.getValue(VaultBlock.OMINOUS));
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

	private static List<ItemStack> resolveItemsToEject(ServerLevel p_323877_, VaultConfig p_324041_, BlockPos p_324255_, Player p_324347_) {
		LootTable loottable = p_323877_.getServer().reloadableRegistries().getLootTable(p_324041_.lootTable());
		LootParams lootparams = new LootParams.Builder(p_323877_)
				.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p_324255_))
				.withLuck(p_324347_.getLuck())
				.withParameter(LootContextParams.THIS_ENTITY, p_324347_)
				.create(LootContextParamSets.VAULT);
		return loottable.getRandomItems(lootparams);
	}

	private static boolean canEjectReward(VaultConfig p_323595_, VaultState p_324160_) {
		return p_323595_.lootTable() != BuiltInLootTables.EMPTY && !p_323595_.keyItem().isEmpty() && p_324160_ != VaultState.INACTIVE;
	}

	private static boolean isValidToInsert(VaultConfig p_323488_, ItemStack p_324101_) {
		return ItemStack.isSameItemSameComponents(p_324101_, p_323488_.keyItem()) && p_324101_.getCount() >= p_323488_.keyItem().getCount();
	}

	private static boolean shouldCycleDisplayItem(long p_323548_, VaultState p_323823_) {
		return p_323548_ % 20L == 0L && p_323823_ == VaultState.ACTIVE;
	}

	private static void playInsertFailSound(ServerLevel p_324555_, VaultServerData p_324017_, BlockPos p_324186_, SoundEvent p_350407_) {
		if (p_324555_.getGameTime() >= p_324017_.getLastInsertFailTimestamp() + 15L) {
			p_324555_.playSound(null, p_324186_, p_350407_, SoundSource.BLOCKS);
			p_324017_.setLastInsertFailTimestamp(p_324555_.getGameTime());
		}
	}

	@Override
	public BlockEntityType<?> getType() {
		return TofuBlockEntitys.TOFU_VAULT.get();
	}
}
