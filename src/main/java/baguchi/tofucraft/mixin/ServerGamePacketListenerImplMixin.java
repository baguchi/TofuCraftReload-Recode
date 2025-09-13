package baguchi.tofucraft.mixin;

import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import baguchi.tofucraft.inventory.TFCraftingTableMenu;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeManager;
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

	@Inject(method = "handlePlaceRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.BEFORE))
	public void handlePlaceRecipeWithFakeSlot(ServerboundPlaceRecipePacket p_307480_, CallbackInfo ci, @Local RecipeManager.ServerDisplayInfo recipemanager$serverdisplayinfo) {
		if (!this.player.isSpectator() && p_307480_.containerId() == this.player.containerMenu.containerId) {
			AbstractContainerMenu abstractcontainermenu = this.player.containerMenu;
			if (abstractcontainermenu instanceof TFCraftingTableMenu craftermenu) {
				if (craftermenu.blockEntity instanceof TFCraftingTableBlockEntity tfCraftingTableBlockEntity) {
					tfCraftingTableBlockEntity.setRecipeDisplay(recipemanager$serverdisplayinfo.display().display());
					tfCraftingTableBlockEntity.setChanged();
				}
			}
		}
	}
}
