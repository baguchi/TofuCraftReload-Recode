package baguchan.tofucraft.block;

import baguchan.tofucraft.entity.TofuGolem;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

import javax.annotation.Nullable;

public class TofuGemBlock extends Block {

	@Nullable
	private BlockPattern tofuGolemBase;
	@Nullable
	private BlockPattern tofuGolemFull;

	public TofuGemBlock(Properties properties) {
		super(properties);
	}

	public void onPlace(BlockState p_51387_, Level p_51388_, BlockPos p_51389_, BlockState p_51390_, boolean p_51391_) {
		if (!p_51390_.is(p_51387_.getBlock())) {
			this.trySpawnGolem(p_51388_, p_51389_);
		}
	}

	public boolean canSpawnGolem(LevelReader p_51382_, BlockPos p_51383_) {
		return this.getOrCreateTofuGolemBase().find(p_51382_, p_51383_) != null;
	}

	private void trySpawnGolem(Level p_51379_, BlockPos p_51380_) {
		BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch = this.getOrCreateTofuGolemFull().find(p_51379_, p_51380_);
		if (blockpattern$blockpatternmatch != null) {
			TofuGolem tofuGolem = TofuEntityTypes.TOFU_GOLEM.get().create(p_51379_, EntitySpawnReason.MOB_SUMMONED);
			if (tofuGolem != null) {
				tofuGolem.setPlayerCreated(true);
				spawnGolemInWorld(p_51379_, blockpattern$blockpatternmatch, tofuGolem, blockpattern$blockpatternmatch.getBlock(0, 2, 0).getPos());
			}
		}

	}

	private static void spawnGolemInWorld(Level p_249110_, BlockPattern.BlockPatternMatch p_251293_, Entity p_251251_, BlockPos p_251189_) {
		clearPatternBlocks(p_249110_, p_251293_);
		p_251251_.moveTo((double) p_251189_.getX() + 0.5D, (double) p_251189_.getY() + 0.05D, (double) p_251189_.getZ() + 0.5D, 0.0F, 0.0F);
		p_249110_.addFreshEntity(p_251251_);

		for (ServerPlayer serverplayer : p_249110_.getEntitiesOfClass(ServerPlayer.class, p_251251_.getBoundingBox().inflate(5.0D))) {
			CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, p_251251_);
		}

		updatePatternBlocks(p_249110_, p_251293_);
	}

	public static void clearPatternBlocks(Level p_249604_, BlockPattern.BlockPatternMatch p_251190_) {
		for (int i = 0; i < p_251190_.getWidth(); ++i) {
			for (int j = 0; j < p_251190_.getHeight(); ++j) {
				BlockInWorld blockinworld = p_251190_.getBlock(i, j, 0);
				p_249604_.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
				p_249604_.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
			}
		}

	}

	public static void updatePatternBlocks(Level p_248711_, BlockPattern.BlockPatternMatch p_251935_) {
		for (int i = 0; i < p_251935_.getWidth(); ++i) {
			for (int j = 0; j < p_251935_.getHeight(); ++j) {
				BlockInWorld blockinworld = p_251935_.getBlock(i, j, 0);
				p_248711_.blockUpdated(blockinworld.getPos(), Blocks.AIR);
			}
		}

	}

	private BlockPattern getOrCreateTofuGolemBase() {
		if (this.tofuGolemBase == null) {
			this.tofuGolemBase = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(TofuBlocks.ISHITOFU.get()))).build();
		}

		return this.tofuGolemBase;
	}

	private BlockPattern getOrCreateTofuGolemFull() {
		if (this.tofuGolemFull == null) {
			this.tofuGolemFull = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(TofuBlocks.TOFU_GEM_BLOCK.get()))).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(TofuBlocks.ISHITOFU.get()))).build();
		}

		return this.tofuGolemFull;
	}
}
