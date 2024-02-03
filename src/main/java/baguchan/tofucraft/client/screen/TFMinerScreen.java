package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.blockentity.tfenergy.TFMinerBlockEntity;
import baguchan.tofucraft.network.SetTFMinerBlockPacket;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TFMinerScreen extends Screen {
	private static final Component POSITION_LABEL = Component.translatable("structure_block.position");
	private static final Component SIZE_LABEL = Component.translatable("structure_block.size");
	private final TFMinerBlockEntity tfMiner;
	private EditBox posXEdit;
	private EditBox posYEdit;
	private EditBox posZEdit;
	private EditBox sizeXEdit;
	private EditBox sizeYEdit;
	private EditBox sizeZEdit;
	private Button startMiningButton;
	private Button loadButton;
	private final DecimalFormat decimalFormat = new DecimalFormat("0.0###");

	public TFMinerScreen(TFMinerBlockEntity p_99398_) {
		super(Component.translatable(TofuBlocks.TF_MINER.get().getDescriptionId()));
		this.tfMiner = p_99398_;
		this.decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
	}

	private void onDone() {
		if (this.sendToServer(false)) {
			this.minecraft.setScreen(null);
		}
	}

	private void onCancel() {
		this.minecraft.setScreen(null);
	}

	@Override
	protected void init() {
		this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, p_99460_ -> this.onDone()).bounds(this.width / 2 - 4 - 150, 210, 150, 20).build());
		this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, p_99457_ -> this.onCancel()).bounds(this.width / 2 + 4, 210, 150, 20).build());
		this.startMiningButton = this.addRenderableWidget(Button.builder(Component.translatable("tofucraft.tfminer.button.start_mining"), p_280866_ -> {
			this.sendToServer(true);
			this.minecraft.setScreen(null);
		}).bounds(this.width / 2 + 4, 185, 100, 20).build());
		BlockPos blockpos = this.tfMiner.getStructurePos();
		this.posXEdit = new EditBox(this.font, this.width / 2 - 152, 80, 80, 20, Component.translatable("structure_block.position.x"));
		this.posXEdit.setMaxLength(15);
		this.posXEdit.setValue(Integer.toString(blockpos.getX()));
		this.addWidget(this.posXEdit);
		this.posYEdit = new EditBox(this.font, this.width / 2 - 72, 80, 80, 20, Component.translatable("structure_block.position.y"));
		this.posYEdit.setMaxLength(15);
		this.posYEdit.setValue(Integer.toString(blockpos.getY()));
		this.addWidget(this.posYEdit);
		this.posZEdit = new EditBox(this.font, this.width / 2 + 8, 80, 80, 20, Component.translatable("structure_block.position.z"));
		this.posZEdit.setMaxLength(15);
		this.posZEdit.setValue(Integer.toString(blockpos.getZ()));
		this.addWidget(this.posZEdit);
		Vec3i vec3i = this.tfMiner.getStructureSize();
		this.sizeXEdit = new EditBox(this.font, this.width / 2 - 152, 120, 80, 20, Component.translatable("structure_block.size.x"));
		this.sizeXEdit.setMaxLength(15);
		this.sizeXEdit.setValue(Integer.toString(vec3i.getX()));
		this.addWidget(this.sizeXEdit);
		this.sizeYEdit = new EditBox(this.font, this.width / 2 - 72, 120, 80, 20, Component.translatable("structure_block.size.y"));
		this.sizeYEdit.setMaxLength(15);
		this.sizeYEdit.setValue(Integer.toString(vec3i.getY()));
		this.addWidget(this.sizeYEdit);
		this.sizeZEdit = new EditBox(this.font, this.width / 2 + 8, 120, 80, 20, Component.translatable("structure_block.size.z"));
		this.sizeZEdit.setMaxLength(15);
		this.sizeZEdit.setValue(Integer.toString(vec3i.getZ()));
		this.addWidget(this.sizeZEdit);
		this.addWidget(this.startMiningButton);
	}

	@Override
	public void resize(Minecraft p_99411_, int p_99412_, int p_99413_) {
		String s1 = this.posXEdit.getValue();
		String s2 = this.posYEdit.getValue();
		String s3 = this.posZEdit.getValue();
		String s4 = this.sizeXEdit.getValue();
		String s5 = this.sizeYEdit.getValue();
		String s6 = this.sizeZEdit.getValue();
		this.init(p_99411_, p_99412_, p_99413_);
		this.posXEdit.setValue(s1);
		this.posYEdit.setValue(s2);
		this.posZEdit.setValue(s3);
		this.sizeXEdit.setValue(s4);
		this.sizeYEdit.setValue(s5);
		this.sizeZEdit.setValue(s6);
	}

	private boolean sendToServer(boolean work) {
		BlockPos blockpos = new BlockPos(
				this.parseCoordinate(this.posXEdit.getValue()), this.parseCoordinate(this.posYEdit.getValue()), this.parseCoordinate(this.posZEdit.getValue())
		);
		Vec3i vec3i = new Vec3i(
				this.parseCoordinate(this.sizeXEdit.getValue()), this.parseCoordinate(this.sizeYEdit.getValue()), this.parseCoordinate(this.sizeZEdit.getValue())
		);
		if (tfMiner.getLevel() != null) {
			PacketDistributor.SERVER.noArg().send(new SetTFMinerBlockPacket(tfMiner.getBlockPos(), blockpos, vec3i, work));
		}
		return true;
	}

	private int parseCoordinate(String p_99436_) {
		try {
			return Integer.parseInt(p_99436_);
		} catch (NumberFormatException numberformatexception) {
			return 0;
		}
	}

	@Override
	public void onClose() {
		this.onCancel();
	}

	@Override
	public boolean keyPressed(int p_99400_, int p_99401_, int p_99402_) {
		if (super.keyPressed(p_99400_, p_99401_, p_99402_)) {
			return true;
		} else if (p_99400_ != 257 && p_99400_ != 335) {
			return false;
		} else {
			this.onDone();
			return true;
		}
	}

	@Override
	public void render(GuiGraphics p_281951_, int p_99407_, int p_99408_, float p_99409_) {
		super.render(p_281951_, p_99407_, p_99408_, p_99409_);
		p_281951_.drawCenteredString(this.font, this.title, this.width / 2, 10, 16777215);

		p_281951_.drawString(this.font, POSITION_LABEL, this.width / 2 - 153, 70, 10526880);
		this.posXEdit.render(p_281951_, p_99407_, p_99408_, p_99409_);
		this.posYEdit.render(p_281951_, p_99407_, p_99408_, p_99409_);
		this.posZEdit.render(p_281951_, p_99407_, p_99408_, p_99409_);
		p_281951_.drawString(this.font, SIZE_LABEL, this.width / 2 - 153, 110, 10526880);
		this.sizeXEdit.render(p_281951_, p_99407_, p_99408_, p_99409_);
		this.sizeYEdit.render(p_281951_, p_99407_, p_99408_, p_99409_);
		this.sizeZEdit.render(p_281951_, p_99407_, p_99408_, p_99409_);
		//p_281951_.drawString(this.font, TofuBlocks.TF_MINER.get().getDescriptionId(), this.width / 2 - 153, 174, 10526880);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
