package baguchi.tofucraft.mixin;

import baguchi.tofucraft.blockentity.tfenergy.TFCrafterBlockEntity;
import baguchi.tofucraft.inventory.TFCrafterMenu;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ServerboundContainerSlotStateChangedPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.server.network.ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin extends ServerCommonPacketListenerImpl {

	@Shadow
	public ServerPlayer player;

	public ServerGamePacketListenerImplMixin(MinecraftServer p_295057_, Connection p_294822_, CommonListenerCookie p_301980_) {
		super(p_295057_, p_294822_, p_301980_);
	}

	@Inject(method = "handleContainerSlotStateChanged", at = @At("TAIL"))
	public void handleContainerSlotStateChanged(ServerboundContainerSlotStateChangedPacket p_307480_, CallbackInfo ci) {
		if (!this.player.isSpectator() && p_307480_.containerId() == this.player.containerMenu.containerId) {
			AbstractContainerMenu abstractcontainermenu = this.player.containerMenu;
			if (abstractcontainermenu instanceof TFCrafterMenu craftermenu) {
				Container container = craftermenu.getContainer();
				if (container instanceof TFCrafterBlockEntity crafterblockentity) {
					crafterblockentity.setSlotState(p_307480_.slotId(), p_307480_.newState());
				}
			}
		}
	}
}
