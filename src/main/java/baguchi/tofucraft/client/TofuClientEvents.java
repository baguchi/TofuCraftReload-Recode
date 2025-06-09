package baguchi.tofucraft.client;

import baguchi.bagus_lib.animation.BaguAnimationController;
import baguchi.bagus_lib.client.event.BagusModelEvent;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.animation.definitions.CoughAnimation;
import baguchi.tofucraft.client.animation.definitions.HumanoidAnimations;
import baguchi.tofucraft.client.sound.TofuMusicManager;
import baguchi.tofucraft.entity.TofuGandlem;
import baguchi.tofucraft.registry.TofuAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT)
public class TofuClientEvents {
	public static Map<UUID, Mob> BOSS_BARS = new HashMap<>();

	public static final ResourceLocation GANDLEM_BOSS_BAR = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "boss_bar/tofu_gandlem_progress");
	public static final ResourceLocation GANDLEM_BOSS_BAR_BACKGROUND = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "boss_bar/tofu_gandlem_background");

	@SubscribeEvent
	public static void onClientTickEvent(ClientTickEvent.Post event) {
		Player player = Minecraft.getInstance().player;
		if (player != null && !Minecraft.getInstance().isPaused()) {
			TofuMusicManager.tick();
		}
	}

	@SubscribeEvent
	public static void onBossOverlay(CustomizeGuiOverlayEvent.BossEventProgress event) {
		Minecraft minecraft = Minecraft.getInstance();
		int i = minecraft.getWindow().getGuiScaledWidth();
		if (BOSS_BARS.containsKey(event.getBossEvent().getId())) {
			Mob boss = BOSS_BARS.get(event.getBossEvent().getId());
			if (!(boss instanceof TofuGandlem tofuGandlem)) return;
			event.setCanceled(true);
			int k = i / 2 - 91;

			drawBar(event.getGuiGraphics(), k, event.getY(), event.getBossEvent(), boss);
			Component itextcomponent = boss.getDisplayName();
			int l = minecraft.font.width(itextcomponent);
			int i1 = i / 2 - l / 2;
			event.getGuiGraphics().drawString(minecraft.font, itextcomponent, i1, event.getY() - 9, 16777215);
			if (event.getY() >= minecraft.getWindow().getGuiScaledHeight() / 3) {
				return;
			}
			//event.setIncrement(12 + minecraft.font.lineHeight);
		}
	}

	private static void drawBar(GuiGraphics p_283672_, int p_283570_, int p_283306_, BossEvent p_283156_, Mob mob) {
		if (mob instanceof TofuGandlem) {
			drawBar(p_283672_, p_283570_, p_283306_, p_283156_, 182, GANDLEM_BOSS_BAR_BACKGROUND);
			int i = Mth.lerpDiscrete(p_283156_.getProgress(), 0, 182);
			if (i > 0) {
				drawBar(p_283672_, p_283570_, p_283306_, p_283156_, i, GANDLEM_BOSS_BAR);
			}
		}
	}

	private static void drawBar(
			GuiGraphics p_281657_, int p_283675_, int p_282498_, BossEvent p_281288_, int p_283619_, ResourceLocation p_296156_
	) {
		p_281657_.blitSprite(RenderPipelines.GUI_TEXTURED, p_296156_, 182, 9, 0, 0, p_283675_, p_282498_, p_283619_, 9);
	}

	public static void addBossBar(UUID id, Mob mob) {
		BOSS_BARS.put(id, mob);
	}

	public static void removeBossBar(UUID id, Mob mob) {
		BOSS_BARS.remove(id, mob);
	}

	@SubscribeEvent
	public static void onAnimateModelEvent(BagusModelEvent.PostAnimate event) {
		BaguAnimationController controller = event.getBaguAnimationController();
		if (controller != null && (event.getModel().root().hasChild("head"))) {
			CoughAnimation.COUGH.bake(event.getModel().root()).apply(controller.getAnimationState(TofuAnimations.COUGH), event.getEntityRenderState().ageInTicks);
		}

		if (controller != null && event.getModel().root().hasChild("right_arm") && event.getModel().root().hasChild("left_arm")) {
			if (controller.getAnimationState(TofuAnimations.THROWN_RIGHT).isStarted() || controller.getAnimationState(TofuAnimations.THROWN_LEFT).isStarted()) {
				event.getModel().root().getChild("right_arm").resetPose();
				event.getModel().root().getChild("left_arm").resetPose();


				HumanoidAnimations.thrown_right.bake(event.getModel().root()).apply(controller.getAnimationState(TofuAnimations.THROWN_RIGHT), event.getEntityRenderState().ageInTicks);
				HumanoidAnimations.thrown_left.bake(event.getModel().root()).apply(controller.getAnimationState(TofuAnimations.THROWN_LEFT), event.getEntityRenderState().ageInTicks);
			}

			if (controller.getAnimationState(TofuAnimations.BUSTER_RIGHT).isStarted() || controller.getAnimationState(TofuAnimations.BUSTER_LEFT).isStarted()) {
				event.getModel().root().getChild("right_arm").resetPose();
				event.getModel().root().getChild("left_arm").resetPose();
				event.getModel().root().getChild("right_leg").resetPose();
				event.getModel().root().getChild("left_leg").resetPose();
				event.getModel().root().getChild("body").resetPose();
				event.getModel().root().getChild("head").resetPose();

				event.getModel().root().getChild("head").xRot = event.getEntityRenderState().xRot * (float) (Math.PI / 180.0);
				event.getModel().root().getChild("head").yRot = event.getEntityRenderState().yRot * (float) (Math.PI / 180.0);
				HumanoidAnimations.buster_right.bake(event.getModel().root()).apply(controller.getAnimationState(TofuAnimations.BUSTER_RIGHT), event.getEntityRenderState().ageInTicks);
				HumanoidAnimations.buster_left.bake(event.getModel().root()).apply(controller.getAnimationState(TofuAnimations.BUSTER_LEFT), event.getEntityRenderState().ageInTicks);
			}
		}
	}
}
