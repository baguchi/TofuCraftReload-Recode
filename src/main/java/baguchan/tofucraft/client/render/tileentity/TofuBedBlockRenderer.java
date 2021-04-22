package baguchan.tofucraft.client.render.tileentity;

import baguchan.tofucraft.registry.TofuTileEntitys;
import baguchan.tofucraft.tileentity.TofuBedTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.BedBlock;
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
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuBedBlockRenderer extends TileEntityRenderer<TofuBedTileEntity> {
	public static final ResourceLocation TOFUBED_LOCATION = new ResourceLocation("tofucraft", "entity/tofubed");

	private static final RenderMaterial DEFAULT_TEXTURE = new RenderMaterial(Atlases.field_228743_b_, TOFUBED_LOCATION);

	private final ModelRenderer headPiece;

	private final ModelRenderer footPiece;

	private final ModelRenderer[] legs = new ModelRenderer[4];

	public TofuBedBlockRenderer(TileEntityRendererDispatcher p_i226006_1_) {
		super(p_i226006_1_);
		this.headPiece = new ModelRenderer(64, 64, 0, 0);
		this.headPiece.func_228301_a_(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
		this.footPiece = new ModelRenderer(64, 64, 0, 22);
		this.footPiece.func_228301_a_(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
		this.legs[0] = new ModelRenderer(64, 64, 50, 0);
		this.legs[1] = new ModelRenderer(64, 64, 50, 6);
		this.legs[2] = new ModelRenderer(64, 64, 50, 12);
		this.legs[3] = new ModelRenderer(64, 64, 50, 18);
		this.legs[0].func_228300_a_(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
		this.legs[1].func_228300_a_(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
		this.legs[2].func_228300_a_(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
		this.legs[3].func_228300_a_(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
		(this.legs[0]).field_78795_f = 1.5707964F;
		(this.legs[1]).field_78795_f = 1.5707964F;
		(this.legs[2]).field_78795_f = 1.5707964F;
		(this.legs[3]).field_78795_f = 1.5707964F;
		(this.legs[0]).field_78808_h = 0.0F;
		(this.legs[1]).field_78808_h = 1.5707964F;
		(this.legs[2]).field_78808_h = 4.712389F;
		(this.legs[3]).field_78808_h = 3.1415927F;
	}

	public void render(TofuBedTileEntity p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
		RenderMaterial rendermaterial = DEFAULT_TEXTURE;
		World world = p_225616_1_.func_145831_w();
		if (world != null) {
			BlockState blockstate = p_225616_1_.func_195044_w();
			TileEntityMerger.ICallbackWrapper<? extends TofuBedTileEntity> icallbackwrapper = TileEntityMerger.func_226924_a_(TofuTileEntitys.TOFUBED, BedBlock::func_226863_i_, BedBlock::func_226862_h_, ChestBlock.field_176459_a, blockstate, (IWorld) world, p_225616_1_.getBlockPos(), (p_228846_0_, p_228846_1_) -> false);
			int i = ((Int2IntFunction) icallbackwrapper.apply(new DualBrightnessCallback())).get(p_225616_5_);
			renderPiece(p_225616_3_, p_225616_4_, (blockstate.func_177229_b((Property) BedBlock.field_176472_a) == BedPart.HEAD), (Direction) blockstate.func_177229_b((Property) BedBlock.field_185512_D), rendermaterial, i, p_225616_6_, false);
		} else {
			renderPiece(p_225616_3_, p_225616_4_, true, Direction.SOUTH, rendermaterial, p_225616_5_, p_225616_6_, false);
			renderPiece(p_225616_3_, p_225616_4_, false, Direction.SOUTH, rendermaterial, p_225616_5_, p_225616_6_, true);
		}
	}

	private void renderPiece(MatrixStack p_228847_1_, IRenderTypeBuffer p_228847_2_, boolean p_228847_3_, Direction p_228847_4_, RenderMaterial p_228847_5_, int p_228847_6_, int p_228847_7_, boolean p_228847_8_) {
		this.headPiece.field_78806_j = p_228847_3_;
		this.footPiece.field_78806_j = !p_228847_3_;
		(this.legs[0]).field_78806_j = !p_228847_3_;
		(this.legs[1]).field_78806_j = p_228847_3_;
		(this.legs[2]).field_78806_j = !p_228847_3_;
		(this.legs[3]).field_78806_j = p_228847_3_;
		p_228847_1_.func_227860_a_();
		p_228847_1_.func_227861_a_(0.0D, 0.5625D, p_228847_8_ ? -1.0D : 0.0D);
		p_228847_1_.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(90.0F));
		p_228847_1_.func_227861_a_(0.5D, 0.5D, 0.5D);
		p_228847_1_.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(180.0F + p_228847_4_.func_185119_l()));
		p_228847_1_.func_227861_a_(-0.5D, -0.5D, -0.5D);
		IVertexBuilder ivertexbuilder = p_228847_5_.func_229311_a_(p_228847_2_, RenderType::func_228634_a_);
		this.headPiece.func_228308_a_(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.footPiece.func_228308_a_(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[0].func_228308_a_(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[1].func_228308_a_(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[2].func_228308_a_(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		this.legs[3].func_228308_a_(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
		p_228847_1_.func_227865_b_();
	}
}
