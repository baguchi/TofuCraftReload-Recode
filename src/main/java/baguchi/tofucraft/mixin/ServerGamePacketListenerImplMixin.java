package baguchi.tofucraft.mixin;

import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import baguchi.tofucraft.inventory.TFCraftingTableMenu;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket;
import net.minecraft.recipebook.PlaceRecipeHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

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
					List<ItemStack> itemStacks = tfCraftingTableBlockEntity.getFakeInventory();
					ContextMap contextmap = SlotDisplayContext.fromLevel(player.level());

					switch (recipemanager$serverdisplayinfo.display().display()) {
						case ShapedCraftingRecipeDisplay shapedcraftingrecipedisplay:
							PlaceRecipeHelper.placeRecipe(
									3,
									3,
									shapedcraftingrecipedisplay.width(),
									shapedcraftingrecipedisplay.height(),
									shapedcraftingrecipedisplay.ingredients(),
									(p_380786_, p_380787_, p_380788_, p_380789_) -> {
										itemStacks.set(p_380787_, p_380786_.resolveForFirstStack(contextmap));
									}
							);
							break;
						case ShapelessCraftingRecipeDisplay shapelesscraftingrecipedisplay:
							label15:
							{
								List<Slot> list = craftermenu.getInputGridSlots();
								int i = Math.min(shapelesscraftingrecipedisplay.ingredients().size(), list.size());

								for (int j = 0; j < i; j++) {
									itemStacks.set(j, shapelesscraftingrecipedisplay.ingredients().get(j).resolveForFirstStack(contextmap));
								}
								break label15;
							}
						default:
					}
					tfCraftingTableBlockEntity.setChanged();
				}
			}
		}
	}
}
