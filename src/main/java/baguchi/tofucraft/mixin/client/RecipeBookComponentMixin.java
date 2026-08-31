package baguchi.tofucraft.mixin.client;

import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import baguchi.tofucraft.client.screen.TfCraftingTableScreen;
import baguchi.tofucraft.inventory.TFCraftingTableMenu;
import baguchi.tofucraft.network.TFCraftingTableSavedRecipePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookComponent.class)
public class RecipeBookComponentMixin {

	@Shadow
	protected Minecraft minecraft;
	@Shadow
	private @Nullable RecipeDisplayId lastPlacedRecipe;

	@Inject(method = "tryPlaceRecipe", at = @At(value = "HEAD"))
	public void handlePlaceRecipeWithFakeSlot(RecipeCollection p_366703_, RecipeDisplayId recipe, boolean p_446681_, CallbackInfoReturnable<Boolean> cir) {
		if (!recipe.equals(this.lastPlacedRecipe)) {

			AbstractContainerMenu abstractcontainermenu = this.minecraft.player.containerMenu;
			if (abstractcontainermenu instanceof TFCraftingTableMenu craftermenu) {
				if (craftermenu.blockEntity instanceof TFCraftingTableBlockEntity tfCraftingTableBlockEntity) {
					ClientPacketDistributor.sendToServer(new TFCraftingTableSavedRecipePacket(craftermenu.blockEntity.getBlockPos(), recipe));
					if (this.minecraft.screen instanceof TfCraftingTableScreen screen) {
						screen.setNeedRefresh();
					}
				}
			}
		}
	}
}
