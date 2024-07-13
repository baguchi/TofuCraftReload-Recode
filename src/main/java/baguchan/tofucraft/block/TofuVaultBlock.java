package baguchan.tofucraft.block;

import baguchan.tofucraft.blockentity.TofuVaultBlockEntity;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class TofuVaultBlock extends VaultBlock {
	public TofuVaultBlock(Properties p_324605_) {
		super(p_324605_);
	}

	@Override
	public ItemInteractionResult useItemOn(
			ItemStack p_324161_, BlockState p_323816_, Level p_324403_, BlockPos p_324623_, Player p_324219_, InteractionHand p_324416_, BlockHitResult p_324261_
	) {
		if (p_324161_.isEmpty() || p_323816_.getValue(STATE) != VaultState.ACTIVE) {
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else if (p_324403_ instanceof ServerLevel serverlevel) {
			if (serverlevel.getBlockEntity(p_324623_) instanceof TofuVaultBlockEntity vaultblockentity) {
				TofuVaultBlockEntity.tryInsertKey(
						serverlevel,
						p_324623_,
						p_323816_,
						vaultblockentity.getConfig(),
						vaultblockentity.getServerData(),
						vaultblockentity.getSharedData(),
						p_324219_,
						p_324161_
				);
				return ItemInteractionResult.SUCCESS;
			} else {
				return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			}
		} else {
			return ItemInteractionResult.CONSUME;
		}
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_324543_, BlockState p_323652_) {
		return new TofuVaultBlockEntity(p_324543_, p_323652_);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_323525_, BlockState p_324070_, BlockEntityType<T> p_323541_) {
		return p_323525_ instanceof ServerLevel serverlevel
				? createTickerHelper(
				p_323541_,
				TofuBlockEntitys.TOFU_VAULT.get(),
				(p_323957_, p_324322_, p_323828_, p_323769_) -> TofuVaultBlockEntity.Server.tick(
						serverlevel, p_324322_, p_323828_, p_323769_.getConfig(), p_323769_.getServerData(), p_323769_.getSharedData()
				)
		)
				: createTickerHelper(
				p_323541_,
				TofuBlockEntitys.TOFU_VAULT.get(),
				(p_324290_, p_323926_, p_323941_, p_323489_) -> TofuVaultBlockEntity.Client.tick(
						p_324290_, p_323926_, p_323941_, p_323489_.getClientData(), p_323489_.getSharedData()
				)
		);
	}
}
