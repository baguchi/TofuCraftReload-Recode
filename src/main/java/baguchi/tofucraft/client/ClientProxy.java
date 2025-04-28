package baguchi.tofucraft.client;

import baguchi.bagus_lib.client.render.book.Book;
import baguchi.bagus_lib.client.render.book.component.BookComponentDefinition;
import baguchi.bagus_lib.client.render.book.component.DisplayBookComponent;
import baguchi.bagus_lib.client.render.book.component.TextBookComponent;
import baguchi.bagus_lib.client.render.screen.BookScreen;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

public class ClientProxy {

	public static final ClientProxy PROXY = new ClientProxy();
	public static BlockEntity refrencedTileEntity;

	@OnlyIn(Dist.CLIENT)
	public BlockEntity getRefrencedTE() {
		return refrencedTileEntity;
	}

	@OnlyIn(Dist.CLIENT)
	public void setRefrencedTE(BlockEntity te) {
		refrencedTileEntity = te;
	}

	public static void handleOpenPageTest(Player player) {
		if (player.level().isClientSide() && player == Minecraft.getInstance().player) {
			Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);

			DisplayBookComponent title = (new DisplayBookComponent(121, 158)).entityDisplay(TofuEntityTypes.TOFUNIAN.get(), 52, 75, 0, 180, 40, quaternionf).textDisplay(Component.translatable("tofucraft.tofu_crafters_book.author"), 52, 20, 1.0F);
			TextBookComponent introduction = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.introduction"), false, 100, 158);
			TextBookComponent beforeTheTofu = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.before_the_tofu"), true, 100, 158);
			DisplayBookComponent bittern = (new DisplayBookComponent(121, 158)).textDisplay(TofuItems.BITTERN_BOTTLE.get().asItem().getName(), 52, 75, 1.0F).itemDisplay(TofuItems.BITTERN_BOTTLE.get().getDefaultInstance(), 46, 20);
			TextBookComponent about_bittern = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.about_bittern"), true, 100, 158);

			Book book = new Book(Lists.newArrayList(new BookComponentDefinition(title, TofuCraftReload.prefix("title"), 10, 10, 10, 10)
					, new BookComponentDefinition(introduction, TofuCraftReload.prefix("introduction"), 10, 10, 10, 10)
					, new BookComponentDefinition(beforeTheTofu, TofuCraftReload.prefix("before_the_tofu"), 10, 10, 10, 10)
					, new BookComponentDefinition(bittern, TofuCraftReload.prefix("bittern"), 10, 10, 10, 10)
					, new BookComponentDefinition(about_bittern, TofuCraftReload.prefix("about_bittern"), 10, 10, 10, 10)), 256, 182, 23, 13, 12, 27, TofuCraftReload.prefix("textures/gui/screen/book/book.png"), TofuCraftReload.prefix("textures/gui/screen/book/book_back.png"), TofuCraftReload.prefix("textures/gui/screen/book/book_back.png"), ResourceLocation.withDefaultNamespace("textures/gui/sprites/widget/page_backward.png"), ResourceLocation.withDefaultNamespace("textures/gui/sprites/widget/page_forward.png"));
			Minecraft.getInstance().setScreen(new BookScreen(book));
		}

	}
}
