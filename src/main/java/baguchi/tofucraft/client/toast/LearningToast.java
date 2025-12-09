package baguchi.tofucraft.client.toast;

import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class LearningToast implements Toast {
	private static final Identifier BACKGROUND_SPRITE = Identifier.withDefaultNamespace("toast/recipe");
	private final Component description;
	private long lastChanged;
	private boolean changed;
	private boolean playedSound;
	private Toast.Visibility wantedVisibility = Toast.Visibility.HIDE;

	public LearningToast(Component description) {
		this.description = description;
	}

	@Override
	public Visibility getWantedVisibility() {
		return this.wantedVisibility;
	}

	@Override
	public void update(ToastManager p_363415_, long p_363939_) {
		if (!this.playedSound && p_363939_ > 0L) {
			this.playedSound = true;
			p_363415_.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F));

		}

		this.wantedVisibility = (double) p_363939_ >= 5000.0 * p_363415_.getNotificationDisplayTimeMultiplier()
				? Toast.Visibility.HIDE
				: Toast.Visibility.SHOW;
	}

	@Override
	public void render(GuiGraphics guiGraphics, Font font, long timeSinceLastVisible) {
		guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, BACKGROUND_SPRITE, 0, 0, this.width(), this.height());
		List<FormattedCharSequence> list = font.split(this.description, 125);
		int i = 16776960;
		if (list.size() == 1) {
			guiGraphics.drawString(font, this.description, 32, 18, -1, false);
			guiGraphics.renderFakeItem(TofuItems.TOFU_CRAFTERS_BOOK.get().getDefaultInstance(), 8, 8);
		} else {
			int l = this.height() / 2 - list.size() * 9 / 2;

			for (FormattedCharSequence formattedcharsequence : list) {
				guiGraphics.drawString(font, formattedcharsequence, 30, l, -1, false);
				l += 9;
			}

			guiGraphics.renderFakeItem(TofuItems.TOFU_CRAFTERS_BOOK.get().getDefaultInstance(), 8, 8);
		}
	}
}