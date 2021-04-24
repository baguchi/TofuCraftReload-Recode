package baguchan.tofucraft.client.render.tileentity;

import baguchan.tofucraft.tileentity.TofuChestTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.*;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuChestBlockRenderer<T extends TofuChestTileEntity> extends TileEntityRenderer<T> {
	public static final ResourceLocation TOFUCHEST_LOCATION = new ResourceLocation("tofucraft", "entity/chest/tofuchest");
	public static final ResourceLocation TOFUCHEST_LEFT_LOCATION = new ResourceLocation("tofucraft", "entity/chest/tofuchest_left");
	public static final ResourceLocation TOFUCHEST_RIGHT_LOCATION = new ResourceLocation("tofucraft", "entity/chest/tofuchest_right");

	public static final RenderMaterial TEXTURE_NORMALTOFU = getChestRenderMaterial(new ResourceLocation("tofucraft", "entity/chest/tofuchest"));
	public static final RenderMaterial TEXTURE_TOFU_LEFT = getChestRenderMaterial(new ResourceLocation("tofucraft", "entity/chest/tofuchest_left"));
	public static final RenderMaterial TEXTURE_TOFU_RIGHT = getChestRenderMaterial(new ResourceLocation("tofucraft", "entity/chest/tofuchest_right"));

	private final ModelRenderer lid;
	private final ModelRenderer bottom;
	private final ModelRenderer lock;
	private final ModelRenderer doubleLeftLid;
	private final ModelRenderer doubleLeftBottom;
	private final ModelRenderer doubleLeftLock;
	private final ModelRenderer doubleRightLid;
	private final ModelRenderer doubleRightBottom;
	private final ModelRenderer doubleRightLock;

	public TofuChestBlockRenderer(TileEntityRendererDispatcher p_i226008_1_) {
		super(p_i226008_1_);
		this.bottom = new ModelRenderer(64, 64, 0, 19);
		this.bottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
		this.lid = new ModelRenderer(64, 64, 0, 0);
		this.lid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.lid.y = 9.0F;
		this.lid.z = 1.0F;
		this.lock = new ModelRenderer(64, 64, 0, 0);
		this.lock.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
		this.lock.y = 8.0F;
		this.doubleLeftBottom = new ModelRenderer(64, 64, 0, 19);
		this.doubleLeftBottom.addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.doubleLeftLid = new ModelRenderer(64, 64, 0, 0);
		this.doubleLeftLid.addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.doubleLeftLid.y = 9.0F;
		this.doubleLeftLid.z = 1.0F;
		this.doubleLeftLock = new ModelRenderer(64, 64, 0, 0);
		this.doubleLeftLock.addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.doubleLeftLock.y = 8.0F;
		this.doubleRightBottom = new ModelRenderer(64, 64, 0, 19);
		this.doubleRightBottom.addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.doubleRightLid = new ModelRenderer(64, 64, 0, 0);
		this.doubleRightLid.addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.doubleRightLid.y = 9.0F;
		this.doubleRightLid.z = 1.0F;
		this.doubleRightLock = new ModelRenderer(64, 64, 0, 0);
		this.doubleRightLock.addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.doubleRightLock.y = 8.0F;
	}

	public void render(T p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
		World world = p_225616_1_.getLevel();
		boolean flag = world != null;
		BlockState blockstate = flag ? p_225616_1_.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = blockstate.getBlock();
		if (block instanceof AbstractChestBlock) {
			AbstractChestBlock<?> abstractchestblock = (AbstractChestBlock) block;
			boolean flag1 = chesttype != ChestType.SINGLE;
			p_225616_3_.pushPose();
			float f = blockstate.getValue(ChestBlock.FACING).toYRot();
			p_225616_3_.translate(0.5D, 0.5D, 0.5D);
			p_225616_3_.mulPose(Vector3f.YP.rotationDegrees(-f));
			p_225616_3_.translate(-0.5D, -0.5D, -0.5D);
			TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> icallbackwrapper;
			if (flag) {
				icallbackwrapper = abstractchestblock.combine(blockstate, world, p_225616_1_.getBlockPos(), true);
			} else {
				icallbackwrapper = TileEntityMerger.ICallback::acceptNone;
			}

			float f1 = icallbackwrapper.apply(ChestBlock.opennessCombiner(p_225616_1_)).get(p_225616_2_);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).applyAsInt(p_225616_5_);
			RenderMaterial rendermaterial = this.getMaterial(p_225616_1_, chesttype);
			IVertexBuilder ivertexbuilder = rendermaterial.buffer(p_225616_4_, RenderType::entityCutout);
			if (flag1) {
				if (chesttype == ChestType.LEFT) {
					this.render(p_225616_3_, ivertexbuilder, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, p_225616_6_);
				} else {
					this.render(p_225616_3_, ivertexbuilder, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, p_225616_6_);
				}
			} else {
				this.render(p_225616_3_, ivertexbuilder, this.lid, this.lock, this.bottom, f1, i, p_225616_6_);
			}

			p_225616_3_.popPose();
		}
	}

	private void render(MatrixStack p_228871_1_, IVertexBuilder p_228871_2_, ModelRenderer p_228871_3_, ModelRenderer p_228871_4_, ModelRenderer p_228871_5_, float p_228871_6_, int p_228871_7_, int p_228871_8_) {
		p_228871_3_.xRot = -(p_228871_6_ * ((float) Math.PI / 2F));
		p_228871_4_.xRot = p_228871_3_.xRot;
		p_228871_3_.render(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
		p_228871_4_.render(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
		p_228871_5_.render(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
	}

	private static RenderMaterial getChestRenderMaterial(ResourceLocation p_228774_0_) {
		return new RenderMaterial(Atlases.CHEST_SHEET, p_228774_0_);
	}

	protected RenderMaterial getMaterial(T tileEntity, ChestType chestType) {
		return chooseMaterial(chestType, TEXTURE_NORMALTOFU, TEXTURE_TOFU_LEFT, TEXTURE_TOFU_RIGHT);
	}

	private static RenderMaterial chooseMaterial(ChestType p_228772_0_, RenderMaterial p_228772_1_, RenderMaterial p_228772_2_, RenderMaterial p_228772_3_) {
		switch (p_228772_0_) {
			case LEFT:
				return p_228772_2_;
			case RIGHT:
				return p_228772_3_;
		}
		return p_228772_1_;
	}
}
