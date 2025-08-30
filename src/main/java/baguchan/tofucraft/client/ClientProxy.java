package baguchan.tofucraft.client;

import bagu_chan.bagus_lib.client.render.book.Book;
import bagu_chan.bagus_lib.client.render.book.component.BookComponentDefinition;
import bagu_chan.bagus_lib.client.render.book.component.DisplayBookComponent;
import bagu_chan.bagus_lib.client.render.book.component.TextBookComponent;
import bagu_chan.bagus_lib.client.render.screen.BookScreen;
import baguchan.tofucraft.CommonProxy;
import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {
	public static BlockEntity refrencedTileEntity;

	private static final ResourceLocation ALT_FONT = new ResourceLocation("alt");
	private static final Style ALT_STYLE = Style.EMPTY.withFont(ALT_FONT);

	public static final ClientProxy PROXY = new ClientProxy();

	public static void handleOpenPageTest(Player player) {
		if (player.level().isClientSide() && player == Minecraft.getInstance().player) {
			Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);

			DisplayBookComponent title = (new DisplayBookComponent(121, 158)).entityDisplay(TofuEntityTypes.TOFUNIAN.get(), 52, 75, 0, 180, 40, quaternionf).textDisplay(Component.translatable("tofucraft.tofu_crafters_book.author"), 52, 20, 1.0F);
			TextBookComponent introduction = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.introduction"), false, 100, 158);
			TextBookComponent beforeTheTofu = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.before_the_tofu"), true, 100, 158);
			DisplayBookComponent bittern = (new DisplayBookComponent(121, 158)).textDisplay(TofuItems.BITTERN_BOTTLE.get().asItem().getDescription(), 52, 75, 1.0F).itemDisplay(TofuItems.BITTERN_BOTTLE.get().getDefaultInstance(), 46, 20);
			TextBookComponent about_bittern = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.about_bittern"), true, 100, 158);
			TextBookComponent onceTofuMade = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.once_tofu_made"), true, 100, 158);
			DisplayBookComponent nether_tofu = (new DisplayBookComponent(121, 158)).textDisplay(TofuItems.TOFUHELL.get().asItem().getDescription(), 52, 16, 1.0F).itemDisplay(TofuItems.TOFUHELL.get().getDefaultInstance(), 46, 26)
					.textDisplay(TofuItems.TOFUSOUL.get().asItem().getDescription(), 52, 56, 1.0F).itemDisplay(TofuItems.TOFUSOUL.get().getDefaultInstance(), 46, 66);

			TextBookComponent aboutAnotherTofu = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.about_another_tofu"), false, 100, 158);
			DisplayBookComponent soymilk = (new DisplayBookComponent(121, 158)).textDisplay(TofuItems.SOYMILK.get().asItem().getDescription(), 52, 16, 1.0F).itemDisplay(TofuItems.SOYMILK.get().getDefaultInstance(), 46, 26);
			TextBookComponent soymilk_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.soymilk.desc"), true, 100, 158);
			DisplayBookComponent tofu_stick = (new DisplayBookComponent(121, 158)).textDisplay(Component.literal("TofuStick").setStyle(ALT_STYLE).withStyle(ChatFormatting.GRAY), 52, 22, 1.0F).textDisplay(TofuItems.TOFUSTICK.get().asItem().getDescription(), 52, 26, 1.0F).itemDisplay(TofuItems.TOFUSTICK.get().getDefaultInstance(), 46, 30);
			TextBookComponent tofu_stick_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.tofu_stick.desc"), true, 100, 158);


			Book book = new Book(Lists.newArrayList(new BookComponentDefinition(title, TofuCraftReload.prefix("title"), 15, 10, 10, 10)
					, new BookComponentDefinition(introduction, TofuCraftReload.prefix("introduction"), 15, 10, 10, 10)
					, new BookComponentDefinition(beforeTheTofu, TofuCraftReload.prefix("before_the_tofu"), 15, 10, 10, 10)
					, new BookComponentDefinition(bittern, TofuCraftReload.prefix("bittern"), 15, 10, 10, 10)
					, new BookComponentDefinition(about_bittern, TofuCraftReload.prefix("about_bittern"), 15, 10, 10, 10)
					, new BookComponentDefinition(onceTofuMade, TofuCraftReload.prefix("once_tofu_made"), 15, 10, 10, 10)
					, new BookComponentDefinition(nether_tofu, TofuCraftReload.prefix("nether_tofu"), 15, 10, 10, 10)
					, new BookComponentDefinition(aboutAnotherTofu, TofuCraftReload.prefix("about_another_tofu"), 15, 10, 10, 10)
					, new BookComponentDefinition(soymilk, TofuCraftReload.prefix("soymilk"), 15, 10, 10, 10)
					, new BookComponentDefinition(soymilk_desc, TofuCraftReload.prefix("soymilk_desc"), 15, 10, 10, 10)
					, new BookComponentDefinition(tofu_stick, TofuCraftReload.prefix("tofu_stick"), 15, 10, 10, 10)
					, new BookComponentDefinition(tofu_stick_desc, TofuCraftReload.prefix("tofu_stick_desc"), 15, 10, 10, 10)), 256, 182, 23, 13, 12, 27, TofuCraftReload.prefix("textures/gui/screen/book/book.png"), TofuCraftReload.prefix("textures/gui/screen/book/book_back.png"), TofuCraftReload.prefix("textures/gui/screen/book/book_back.png"), new ResourceLocation(TofuCraftReload.MODID, "textures/gui/screen/book/book_button_backward.png"), new ResourceLocation(TofuCraftReload.MODID, "textures/gui/screen/book/book_button_forward.png"));
			Minecraft.getInstance().setScreen(new BookScreen(book));
		}

	}


	@OnlyIn(Dist.CLIENT)
	public BlockEntity getRefrencedTE() {
		return refrencedTileEntity;
	}

	@OnlyIn(Dist.CLIENT)
	public void setRefrencedTE(BlockEntity te) {
		refrencedTileEntity = te;
	}
}
