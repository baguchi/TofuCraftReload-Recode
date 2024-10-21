package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.recipe.OvenRecipeBookComponent;
import baguchan.tofucraft.inventory.TFOvenMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.FurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TFOvenScreen extends AbstractRecipeBookScreen<TFOvenMenu> implements RecipeUpdateListener {
	private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.smeltable");
	private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
			new RecipeBookComponent.TabInfo(SearchRecipeBookCategory.FURNACE),
			new RecipeBookComponent.TabInfo(Items.PORKCHOP, RecipeBookCategories.FURNACE_FOOD),
			new RecipeBookComponent.TabInfo(Items.STONE, RecipeBookCategories.FURNACE_BLOCKS),
			new RecipeBookComponent.TabInfo(Items.LAVA_BUCKET, Items.EMERALD, RecipeBookCategories.FURNACE_MISC)
	);


	private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_oven.png");
	private boolean widthTooNarrow;

	public TFOvenScreen(
			baguchan.tofucraft.inventory.TFOvenMenu p_97825_,
			Inventory p_97827_,
			Component p_97828_
	) {
		super(p_97825_, new OvenRecipeBookComponent(p_97825_, FILTER_NAME, TABS), p_97827_, p_97828_);
	}

	@Override
	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	protected ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(this.leftPos + 20, this.height / 2 - 49);
	}

	@Override
	protected void renderBg(GuiGraphics p_282928_, float p_281631_, int p_281252_, int p_281891_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_282928_.blit(RenderType::guiTextured, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
		int k = 14;
		int l = Mth.ceil(this.menu.getTFForce() * 16.0F);
		p_282928_.blit(RenderType::guiTextured, texture, i + 72, j + 35, 176, 39, 24, l, 256, 256);


		int j1 = Mth.ceil(this.menu.getProgress() * 28.0F);
		p_282928_.blit(RenderType::guiTextured, texture, i + 67, j + 15, 176, 14, j1, 17, 256, 256);
	}
}
