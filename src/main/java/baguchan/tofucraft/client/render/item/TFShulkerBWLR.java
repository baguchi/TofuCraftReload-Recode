package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;

import java.util.Arrays;
import java.util.Comparator;

public class TFShulkerBWLR extends BlockEntityWithoutLevelRenderer {
	private static final ShulkerBoxBlockEntity[] SHULKER_BOXES = Arrays.stream(DyeColor.values())
			.sorted(Comparator.comparingInt(DyeColor::getId))
			.map(p_172557_ -> new ShulkerBoxBlockEntity(p_172557_, BlockPos.ZERO, Blocks.SHULKER_BOX.defaultBlockState()))
			.toArray(ShulkerBoxBlockEntity[]::new);
	private static final ShulkerBoxBlockEntity DEFAULT_SHULKER_BOX = new ShulkerBoxBlockEntity(BlockPos.ZERO, Blocks.SHULKER_BOX.defaultBlockState());

	private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
	private final EntityModelSet entityModelSet;

	public TFShulkerBWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
		this.blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
		this.entityModelSet = Minecraft.getInstance().getEntityModels();
	}

	@Override
	public void renderByItem(ItemStack p_108830_, ItemDisplayContext p_270899_, PoseStack p_108832_, MultiBufferSource p_108833_, int p_108834_, int p_108835_) {
		super.renderByItem(p_108830_, p_270899_, p_108832_, p_108833_, p_108834_, p_108835_);
		if (p_108830_.is(TofuItems.TF_SHULKER.get())) {

			BlockEntity blockentity;

			DyeColor dyecolor1 = p_108830_.get(DataComponents.BASE_COLOR);
			if (dyecolor1 == null) {
				blockentity = DEFAULT_SHULKER_BOX;
			} else {
				blockentity = SHULKER_BOXES[dyecolor1.getId()];
			}

			this.blockEntityRenderDispatcher.renderItem(blockentity, p_108832_, p_108833_, p_108834_, p_108835_);
		}
	}
}
