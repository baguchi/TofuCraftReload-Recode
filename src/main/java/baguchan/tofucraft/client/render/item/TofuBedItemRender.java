package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.block.utils.TofuBedBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.Comparator;

public class TofuBedItemRender implements ResourceManagerReloadListener {
	private static final ShulkerBoxBlockEntity[] SHULKER_BOXES = Arrays.stream(DyeColor.values()).sorted(Comparator.comparingInt(DyeColor::getId)).map((p_172557_) -> {
		return new ShulkerBoxBlockEntity(p_172557_, BlockPos.ZERO, Blocks.SHULKER_BOX.defaultBlockState());
	}).toArray((p_172553_) -> {
		return new ShulkerBoxBlockEntity[p_172553_];
	});
	private static final ShulkerBoxBlockEntity DEFAULT_SHULKER_BOX = new ShulkerBoxBlockEntity(BlockPos.ZERO, Blocks.SHULKER_BOX.defaultBlockState());
	private final ChestBlockEntity chest = new ChestBlockEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState());
	private final ChestBlockEntity trappedChest = new TrappedChestBlockEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState());
	private final EnderChestBlockEntity enderChest = new EnderChestBlockEntity(BlockPos.ZERO, Blocks.ENDER_CHEST.defaultBlockState());
	private final BannerBlockEntity banner = new BannerBlockEntity(BlockPos.ZERO, Blocks.WHITE_BANNER.defaultBlockState());
	private final BedBlockEntity bed = new BedBlockEntity(BlockPos.ZERO, Blocks.RED_BED.defaultBlockState());
	private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
	private final EntityModelSet entityModelSet;

	public TofuBedItemRender(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
		this.blockEntityRenderDispatcher = p_172550_;
		this.entityModelSet = p_172551_;
	}

	public void onResourceManagerReload(ResourceManager p_172555_) {
	}

	public void renderByItem(ItemStack p_108830_, ItemTransforms.TransformType p_108831_, PoseStack p_108832_, MultiBufferSource p_108833_, int p_108834_, int p_108835_) {
		Item item = p_108830_.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();

			BlockState blockstate = block.defaultBlockState();
			BlockEntity blockentity = null;
			if (block instanceof TofuBedBlock) {
				blockentity = this.bed;

			} else if (blockstate.is(TofuBlocks.TOFUCHEST)) {
				blockentity = this.chest;
			}

			if (blockentity != null) {
				this.blockEntityRenderDispatcher.renderItem(blockentity, p_108832_, p_108833_, p_108834_, p_108835_);
			}
		}
	}
}