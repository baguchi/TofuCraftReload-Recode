package baguchan.tofucraft.client;

import bagu_chan.bagus_lib.client.render.book.Book;
import bagu_chan.bagus_lib.client.render.book.component.BookComponentDefinition;
import bagu_chan.bagus_lib.client.render.book.component.DisplayBookComponent;
import bagu_chan.bagus_lib.client.render.book.component.IndexBookComponent;
import bagu_chan.bagus_lib.client.render.book.component.TextBookComponent;
import bagu_chan.bagus_lib.client.render.screen.BookScreen;
import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class ClientProxy {
	private static final ResourceLocation ALT_FONT = ResourceLocation.withDefaultNamespace("alt");
	private static final Style ALT_STYLE = Style.EMPTY.withFont(ALT_FONT);

	public static final ClientProxy PROXY = new ClientProxy();
	public static BlockEntity refrencedTileEntity;

	private static IndexBookComponent buildIndex() {
		Component dummy = Component.empty();

		return new IndexBookComponent(Component.translatable("tofucraft.tofu_crafters_book.title"), List.of(
				//new IndexBookComponent.IndexItem(Component.translatable(TofuItems.TOFU_CRAFTERS_BOOK.get().getDescriptionId()), TofuCraftReload.prefix("introduction_display"), false),
				//new IndexBookComponent.IndexItem(Component.translatable(TofuItems.TOFU_CRAFTERS_BOOK.get().getDescriptionId()), TofuCraftReload.prefix("introduction_display2"), false),
				new IndexBookComponent.IndexItem(Component.translatable("tofucraft.tofu_crafters_book.before_the_tofu.title"), TofuCraftReload.prefix("before_the_tofu"), true),
				new IndexBookComponent.IndexItem(Component.translatable(TofuItems.BITTERN_BOTTLE.get().getDescriptionId()), TofuCraftReload.prefix("bittern"), true),
				new IndexBookComponent.IndexItem(Component.translatable("tofucraft.tofu_crafters_book.once_tofu_made.title"), TofuCraftReload.prefix("once_tofu_made"), true),
				new IndexBookComponent.IndexItem(Component.translatable("tofucraft.tofu_crafters_book.nether_tofu.title"), TofuCraftReload.prefix("nether_tofu"), true),
				new IndexBookComponent.IndexItem(Component.translatable(TofuItems.SOYMILK.get().getDescriptionId()), TofuCraftReload.prefix("soymilk"), true),
				new IndexBookComponent.IndexItem(Component.translatable("tofucraft.tofu_crafters_book.tofu_world.title"), TofuCraftReload.prefix("tofu_stick"), true),
				new IndexBookComponent.IndexItem(Component.translatable(TofuItems.BOTTLE_SOYSAUSE.get().getDescriptionId()), TofuCraftReload.prefix("soy_sauce"), true),
				new IndexBookComponent.IndexItem(Component.translatable("tofucraft.tofu_crafters_book.tf_force.title"), TofuCraftReload.prefix("tf_force"), true),
				new IndexBookComponent.IndexItem(Component.translatable(TofuBlocks.TF_STORAGE.get().getDescriptionId()), TofuCraftReload.prefix("tf_storage"), true),
				new IndexBookComponent.IndexItem(Component.translatable(TofuBlocks.ANTENNA_BASIC.get().getDescriptionId()), TofuCraftReload.prefix("antenna_basic"), true)
		)
				, 105, 125);
	}

	public static void handleOpenPageTest(Player player) {
		if (player.level().isClientSide() && player == Minecraft.getInstance().player) {
			IndexBookComponent index = buildIndex();
			TextBookComponent introduction = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.introduction"), false, 100, 158);
			TextBookComponent beforeTheTofu = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.before_the_tofu"), true, 100, 158);
			DisplayBookComponent bittern = (new DisplayBookComponent(121, 158)).textDisplay(TofuItems.BITTERN_BOTTLE.get().getDescription(), 52, 75, 1.0F).itemDisplay(TofuItems.BITTERN_BOTTLE.get().getDefaultInstance(), 46, 20);
			TextBookComponent about_bittern = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.about_bittern"), true, 100, 158);
			TextBookComponent onceTofuMade = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.once_tofu_made"), true, 100, 158);
			DisplayBookComponent nether_tofu = (new DisplayBookComponent(121, 158)).textDisplay(TofuItems.TOFUHELL.get().getDescription(), 52, 16, 1.0F).itemDisplay(TofuItems.TOFUHELL.get().getDefaultInstance(), 46, 26)
					.textDisplay(TofuItems.TOFUSOUL.get().getDescription(), 52, 56, 1.0F).itemDisplay(TofuItems.TOFUSOUL.get().getDefaultInstance(), 46, 66);

			TextBookComponent aboutAnotherTofu = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.about_another_tofu"), false, 100, 158);
			DisplayBookComponent soymilk = (new DisplayBookComponent(121, 158)).textDisplay(TofuItems.SOYMILK.get().getDescription(), 52, 16, 1.0F).itemDisplay(TofuItems.SOYMILK.get().getDefaultInstance(), 46, 26);
			TextBookComponent soymilk_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.soymilk.desc"), true, 100, 158);
			DisplayBookComponent tofu_stick = (new DisplayBookComponent(121, 158)).textDisplay(Component.literal("TofuStick").setStyle(ALT_STYLE).withStyle(ChatFormatting.GRAY), 52, 22, 1.0F).textDisplay(TofuItems.TOFUSTICK.get().getDescription(), 52, 26, 1.0F).itemDisplay(TofuItems.TOFUSTICK.get().getDefaultInstance(), 46, 30);
			TextBookComponent tofu_stick_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.tofu_stick.desc"), true, 100, 158);

			DisplayBookComponent soysauce = (new DisplayBookComponent(121, 158)).textDisplay(Component.translatable("tofucraft.tofu_crafters_book.soysauce"), 52, 26, 1.0F).itemDisplay(TofuItems.BOTTLE_SOYSAUSE.get().getDefaultInstance(), 46, 30);
			TextBookComponent soysauce_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.soysauce.desc"), true, 100, 158);
			TextBookComponent soysauce_desc2 = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.soysauce.desc2"), true, 100, 158);


			TextBookComponent tf_force_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.tf_force.desc"), true, 100, 158);
			DisplayBookComponent tf_storage = (new DisplayBookComponent(121, 158)).textDisplay(TofuBlocks.TF_STORAGE.toStack().getDisplayName(), 52, 26, 1.0F).itemDisplay(TofuBlocks.TF_STORAGE.toStack().getItem().getDefaultInstance(), 46, 30);
			TextBookComponent tf_storage_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.tf_storage.desc"), true, 100, 158);
			DisplayBookComponent antenna_basic = (new DisplayBookComponent(121, 158)).textDisplay(TofuBlocks.ANTENNA_BASIC.toStack().getDisplayName(), 52, 26, 1.0F).itemDisplay(TofuBlocks.ANTENNA_BASIC.toStack().getItem().getDefaultInstance(), 46, 30);
			TextBookComponent antenna_basic_desc = new TextBookComponent(Component.translatable("tofucraft.tofu_crafters_book.antenna_basic.desc"), true, 100, 158);


			List<BookComponentDefinition> list = Lists.newArrayList(new BookComponentDefinition(index, TofuCraftReload.prefix("index"), 15, 10, 10, 10)
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
					, new BookComponentDefinition(tofu_stick_desc, TofuCraftReload.prefix("tofu_stick_desc"), 15, 10, 10, 10));


			list.add(new BookComponentDefinition(soysauce, TofuCraftReload.prefix("soysauce"), 15, 10, 10, 10));
			list.add(new BookComponentDefinition(soysauce_desc, TofuCraftReload.prefix("soysauce_desc"), 15, 10, 10, 10));
			list.add(new BookComponentDefinition(soysauce_desc2, TofuCraftReload.prefix("soysauce_desc2"), 15, 10, 10, 10));
			list.add(new BookComponentDefinition(tf_force_desc, TofuCraftReload.prefix("tf_force"), 15, 10, 10, 10));
			list.add(new BookComponentDefinition(tf_storage, TofuCraftReload.prefix("tf_storage"), 15, 10, 10, 10));
			list.add(new BookComponentDefinition(tf_storage_desc, TofuCraftReload.prefix("tf_storage_desc"), 15, 10, 10, 10));
			list.add(new BookComponentDefinition(antenna_basic, TofuCraftReload.prefix("antenna_basic"), 15, 10, 10, 10));
			list.add(new BookComponentDefinition(antenna_basic_desc, TofuCraftReload.prefix("antenna_basic_desc"), 15, 10, 10, 10));


			Book book = new Book(list, 256, 182, 23, 13, 12, 27, TofuCraftReload.prefix("textures/gui/screen/book/book.png"), TofuCraftReload.prefix("textures/gui/screen/book/book_back.png"), TofuCraftReload.prefix("textures/gui/screen/book/book_back.png"), Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_backward.png"), Identifier.withDefaultNamespace("textures/gui/sprites/widget/page_forward.png"));
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
