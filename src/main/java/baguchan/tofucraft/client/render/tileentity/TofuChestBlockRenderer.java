package baguchan.tofucraft.client.render.tileentity;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.tileentity.TofuChestTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.Property;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IChestLid;
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
		this.bottom.func_228301_a_(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
		this.lid = new ModelRenderer(64, 64, 0, 0);
		this.lid.func_228301_a_(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.lid.field_78797_d = 9.0F;
		this.lid.field_78798_e = 1.0F;
		this.lock = new ModelRenderer(64, 64, 0, 0);
		this.lock.func_228301_a_(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
		this.lock.field_78797_d = 8.0F;
		this.doubleLeftBottom = new ModelRenderer(64, 64, 0, 19);
		this.doubleLeftBottom.func_228301_a_(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.doubleLeftLid = new ModelRenderer(64, 64, 0, 0);
		this.doubleLeftLid.func_228301_a_(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.doubleLeftLid.field_78797_d = 9.0F;
		this.doubleLeftLid.field_78798_e = 1.0F;
		this.doubleLeftLock = new ModelRenderer(64, 64, 0, 0);
		this.doubleLeftLock.func_228301_a_(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.doubleLeftLock.field_78797_d = 8.0F;
		this.doubleRightBottom = new ModelRenderer(64, 64, 0, 19);
		this.doubleRightBottom.func_228301_a_(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.doubleRightLid = new ModelRenderer(64, 64, 0, 0);
		this.doubleRightLid.func_228301_a_(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.doubleRightLid.field_78797_d = 9.0F;
		this.doubleRightLid.field_78798_e = 1.0F;
		this.doubleRightLock = new ModelRenderer(64, 64, 0, 0);
		this.doubleRightLock.func_228301_a_(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.doubleRightLock.field_78797_d = 8.0F;
	}

	public void render(T p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
		World world = p_225616_1_.func_145831_w();
		boolean flag = (world != null);
		BlockState blockstate = flag ? p_225616_1_.func_195044_w() : TofuBlocks.TOFUCHEST.defaultBlockState().setValue(ChestBlock.field_176459_a, (Comparable) Direction.SOUTH);
		ChestType chesttype = blockstate.func_235901_b_((Property) ChestBlock.field_196314_b) ? (ChestType) blockstate.func_177229_b((Property) ChestBlock.field_196314_b) : ChestType.SINGLE;
		Block block = blockstate.getBlock();
		if (block instanceof AbstractChestBlock) {
			TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> icallbackwrapper;
			AbstractChestBlock<?> abstractchestblock = (AbstractChestBlock) block;
			boolean flag1 = (chesttype != ChestType.SINGLE);
			p_225616_3_.func_227860_a_();
			float f = ((Direction) blockstate.func_177229_b((Property) ChestBlock.field_176459_a)).func_185119_l();
			p_225616_3_.func_227861_a_(0.5D, 0.5D, 0.5D);
			p_225616_3_.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-f));
			p_225616_3_.func_227861_a_(-0.5D, -0.5D, -0.5D);
			if (flag) {
				icallbackwrapper = abstractchestblock.func_225536_a_(blockstate, world, p_225616_1_.getBlockPos(), true);
			} else {
				icallbackwrapper = TileEntityMerger.ICallback::func_225537_b_;
			}
			float f1 = ((Float2FloatFunction) icallbackwrapper.apply(ChestBlock.func_226917_a_((IChestLid) p_225616_1_))).get(p_225616_2_);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			int i = ((Int2IntFunction) icallbackwrapper.apply(new DualBrightnessCallback())).applyAsInt(p_225616_5_);
			RenderMaterial rendermaterial = getMaterial(p_225616_1_, chesttype);
			IVertexBuilder ivertexbuilder = rendermaterial.func_229311_a_(p_225616_4_, RenderType::func_228638_b_);
			if (flag1) {
				if (chesttype == ChestType.LEFT) {
					render(p_225616_3_, ivertexbuilder, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, p_225616_6_);
				} else {
					render(p_225616_3_, ivertexbuilder, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, p_225616_6_);
				}
			} else {
				render(p_225616_3_, ivertexbuilder, this.lid, this.lock, this.bottom, f1, i, p_225616_6_);
			}
			p_225616_3_.func_227865_b_();
		}
	}

	private void render(MatrixStack p_228871_1_, IVertexBuilder p_228871_2_, ModelRenderer p_228871_3_, ModelRenderer p_228871_4_, ModelRenderer p_228871_5_, float p_228871_6_, int p_228871_7_, int p_228871_8_) {
		p_228871_3_.field_78795_f = -(p_228871_6_ * 1.5707964F);
		p_228871_4_.field_78795_f = p_228871_3_.field_78795_f;
		p_228871_3_.func_228308_a_(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
		p_228871_4_.func_228308_a_(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
		p_228871_5_.func_228308_a_(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
	}

	private static RenderMaterial getChestRenderMaterial(ResourceLocation p_228774_0_) {
		return new RenderMaterial(Atlases.field_228747_f_, p_228774_0_);
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
