package baguchi.tofucraft.client.render.item.extension;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class StickFoodItemRender implements IClientItemExtensions {
	public final float scale;

	public StickFoodItemRender(float scale) {
		this.scale = scale;
	}


	@Override
	public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
		if (player.isUsingItem()) {
			boolean flag2 = arm == HumanoidArm.RIGHT;
			int i = flag2 ? 1 : -1;
			float f = player.getUseItemRemainingTicks() - partialTick + 1.0F;
			float f1 = f / itemInHand.getUseDuration(player);

			if (f1 < 0.8F) {
				float f2 = Mth.clamp(f1 + 0.2F, 0F, 1.0F);
				poseStack.translate(0.0F, 0.0F, (1.0F - f2) * this.scale);
			} else {
				poseStack.translate(0.0F, 0.0F, -0.2F * this.scale);
			}

			float f3 = 1.0F - (float) Math.pow(f1, 27.0);
			poseStack.translate(0.1F, 0, 0);
			poseStack.translate(f3 * i * -0.3F + f3 * i * 0.5F, 0.0F, -0.2F + -0.2F * -i);
			poseStack.mulPose(Axis.YP.rotationDegrees(i * 45.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
			//poseStack.mulPose(Axis.YP.rotationDegrees(i * 10.0F));
			poseStack.mulPose(Axis.ZN.rotationDegrees(i * 90.0F));
			//this.applyItemArmTransform(poseStack, arm, swingProcess);

			return true;
		}

		return IClientItemExtensions.super.applyForgeHandTransform(poseStack, player, arm, itemInHand, partialTick, equipProcess, swingProcess);
	}

	private void applyEatTransform(PoseStack p_109331_, float p_109332_, HumanoidArm p_109333_, ItemStack p_109334_, Player p_346361_) {
		float f = p_346361_.getUseItemRemainingTicks() - p_109332_ + 1.0F;
		float f1 = f / p_109334_.getUseDuration(p_346361_);
		if (f1 < 0.8F) {
			float f2 = Mth.abs(Mth.cos(f / 4.0F * (float) Math.PI) * 0.1F);
			p_109331_.translate(0.0F, f2, 0.0F);
		}

		float f3 = 1.0F - (float) Math.pow(f1, 27.0);
		int i = p_109333_ == HumanoidArm.RIGHT ? 1 : -1;
		p_109331_.translate(f3 * 0.6F * i, f3 * -0.5F, f3 * 0.0F);
		p_109331_.mulPose(Axis.YP.rotationDegrees(i * f3 * 90.0F));
		p_109331_.mulPose(Axis.XP.rotationDegrees(f3 * 10.0F));
		p_109331_.mulPose(Axis.ZP.rotationDegrees(i * f3 * 30.0F));
	}

	private void applyItemArmTransform(PoseStack p_109383_, HumanoidArm p_109384_, float p_109385_) {
		int i = p_109384_ == HumanoidArm.RIGHT ? 1 : -1;
		p_109383_.translate(i * 0.56F, -0.52F + p_109385_ * -0.6F, -0.72F);
	}
}
