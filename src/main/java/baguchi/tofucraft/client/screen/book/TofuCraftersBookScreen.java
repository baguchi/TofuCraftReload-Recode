package baguchi.tofucraft.client.screen.book;

import baguchi.bagus_lib.util.DialogHandler;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class TofuCraftersBookScreen extends Screen {
	private BookModel bookModel;
	private static final ResourceLocation BOOK_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/enchanting_table_book.png");

	public DialogHandler dialogHandler;
	public int time;
	public float flip;
	public float oFlip;
	public float flipT;
	public float flipA;
	public float open;
	public float oOpen;
	public float rot;
	public float oRot;
	public float tRot;

	public TofuCraftersBookScreen() {
		super(Component.empty());
	}

	@Override
	protected void init() {
		super.init();
		dialogHandler = new DialogHandler();
		this.open = 0F;
		bookModel = new BookModel(this.minecraft.getEntityModels().bakeLayer(ModelLayers.BOOK));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float particalTick) {
		super.render(guiGraphics, mouseX, mouseY, particalTick);

		dialogHandler.renderDialogue(guiGraphics, Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks(), (float) this.time);
		renderBook(guiGraphics, this.width / 2, this.height - 40, particalTick);
	}

	@Override
	public void tick() {
		super.tick();
		this.time++;
		this.tickBook();
	}

	private void renderBook(GuiGraphics p_289697_, int p_289667_, int p_289669_, float p_289670_) {
		float f = Mth.lerp(p_289670_, this.oOpen, this.open);
		float f1 = Mth.lerp(p_289670_, this.oFlip, this.flip);
		p_289697_.flush();
		Lighting.setupForEntityInInventory();
		p_289697_.pose().pushPose();
		p_289697_.pose().translate((float) p_289667_, (float) p_289669_, 100.0F);
		float f2 = 40.0F;
		p_289697_.pose().scale(-40.0F, 40.0F, 40.0F);
		p_289697_.pose().mulPose(Axis.XP.rotationDegrees(40.0F));
		p_289697_.pose().translate((1.0F - f) * 0.2F, (1.0F - f) * 0.1F, (1.0F - f) * 0.25F);
		float f3 = -(1.0F - f) * 90.0F - 90.0F;
		p_289697_.pose().mulPose(Axis.YP.rotationDegrees(f3));
		p_289697_.pose().mulPose(Axis.XP.rotationDegrees(180.0F));
		float f4 = Mth.clamp(Mth.frac(f1 + 0.25F) * 1.6F - 0.3F, 0.0F, 1.0F);
		float f5 = Mth.clamp(Mth.frac(f1 + 0.75F) * 1.6F - 0.3F, 0.0F, 1.0F);
		this.bookModel.setupAnim(0.0F, f4, f5, f);
		p_289697_.drawSpecial(p_371383_ -> {
			VertexConsumer vertexconsumer = p_371383_.getBuffer(this.bookModel.renderType(BOOK_TEXTURE));
			this.bookModel.renderToBuffer(p_289697_.pose(), vertexconsumer, 15728880, OverlayTexture.NO_OVERLAY);
		});
		p_289697_.flush();
		p_289697_.pose().popPose();
		Lighting.setupFor3DItems();
	}

	public void tickBook() {
		this.oFlip = this.flip;
		this.oOpen = this.open;
		boolean flag = true;


		if (flag) {
			this.open += 0.2F;
		} else {
			this.open -= 0.2F;
		}

		this.open = Mth.clamp(this.open, 0.0F, 1.0F);
		float f1 = (this.flipT - this.flip) * 0.4F;
		float f = 0.2F;
		f1 = Mth.clamp(f1, -0.2F, 0.2F);
		this.flipA = this.flipA + (f1 - this.flipA) * 0.9F;
		this.flip = this.flip + this.flipA;
	}
}
