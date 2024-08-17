package baguchan.tofucraft.data.generator;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.advancements.MyTofuChildTrigger;
import baguchan.tofucraft.advancements.NightmaresEchoTrigger;
import baguchan.tofucraft.advancements.TofuPigPopTrigger;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TofuAdvancementGenerator extends AdvancementProvider {
	/**
	 * Constructs an advancement provider using the generators to write the
	 * advancements to a file.
	 *
	 * @param output             the target directory of the data generator
	 * @param registries         a future of a lookup for registries and their objects
	 * @param existingFileHelper a helper used to find whether a file exists
	 * @param subProviders       the generators used to create the advancements
	 */
	public TofuAdvancementGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
		super(output, registries, existingFileHelper, List.of(new TofuAdvancements()));
	}


	public static class TofuAdvancements implements AdvancementGenerator {

		@SuppressWarnings("unused")
		@Override
		public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {

			AdvancementHolder root = Advancement.Builder.advancement()
					.display(TofuItems.SEEDS_SOYBEANS.get(),
							Component.translatable("advancements.tofucraft.root.title"),
							Component.translatable("advancements.tofucraft.root.desc"),
							ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/blocktofukinu.png"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.SEEDS_SOYBEANS.get()))
					.save(consumer, "tofucraft:root");
			AdvancementHolder make_saltpan = Advancement.Builder.advancement()
					.parent(root)
					.display(TofuBlocks.SALTPAN.get(),
							Component.translatable("advancements.tofucraft.make_saltpan.title"),
							Component.translatable("advancements.tofucraft.make_saltpan.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.SALTPAN.get()))
					.save(consumer, "tofucraft:make_saltpan");
			AdvancementHolder bitter_better = Advancement.Builder.advancement()
					.parent(make_saltpan)
					.display(TofuBlocks.SALT_FURNACE.get(),
							Component.translatable("advancements.tofucraft.bitter_better.title"),
							Component.translatable("advancements.tofucraft.bitter_better.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.SALT_FURNACE.get()))
					.save(consumer, "tofucraft:bitter_better");
			AdvancementHolder bittern_bottle = Advancement.Builder.advancement()
					.parent(make_saltpan)
					.display(TofuItems.BITTERN_BOTTLE.get(),
							Component.translatable("advancements.tofucraft.bittern_bottle.title"),
							Component.translatable("advancements.tofucraft.bittern_bottle.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.BITTERN.get()))
					.save(consumer, "tofucraft:bittern_bottle");
			AdvancementHolder make_tofu = Advancement.Builder.advancement()
					.parent(bittern_bottle)
					.display(TofuItems.TOFUKINU.get(),
							Component.translatable("advancements.tofucraft.make_tofu.title"),
							Component.translatable("advancements.tofucraft.make_tofu.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("do_the_tofu", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location()
									.setBlock(
											BlockPredicate.Builder.block()
													.of(
															TofuBlocks.SOYMILK.get()
													)
									),
							ItemPredicate.Builder.item().of(TofuItems.BITTERN_BOTTLE.get())))
					.save(consumer, "tofucraft:make_tofu");

			AdvancementHolder ancient_chili = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.CHILI.get(),
							Component.translatable("advancements.tofucraft.ancient_chili.title"),
							Component.translatable("advancements.tofucraft.ancient_chili.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.CHILI.get()))
					.save(consumer, "tofucraft:ancient_chili");


			AdvancementHolder sniffed_revenge = Advancement.Builder.advancement()
					.parent(ancient_chili)
					.display(TofuItems.CHILI.get(),
							Component.translatable("advancements.tofucraft.sniffed_revenge.title"),
							Component.translatable("advancements.tofucraft.sniffed_revenge.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.CHILI.get()))
					.save(consumer, "tofucraft:sniffed_revenge");


			AdvancementHolder nether_soybeans = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.SEEDS_SOYBEANS_NETHER.get(),
							Component.translatable("advancements.tofucraft.nether_soybeans.title"),
							Component.translatable("advancements.tofucraft.nether_soybeans.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
					.save(consumer, "tofucraft:nether_soybeans");

			AdvancementHolder very_strange_soybeans = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.SEEDS_SOYBEANS_SOUL.get(),
							Component.translatable("advancements.tofucraft.very_strange_soybeans.title"),
							Component.translatable("advancements.tofucraft.very_strange_soybeans.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
					.save(consumer, "tofucraft:very_strange_soybeans");

			AdvancementHolder harder_tofu = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuBlocks.METALTOFU.get(),
							Component.translatable("advancements.tofucraft.harder_tofu.title"),
							Component.translatable("advancements.tofucraft.harder_tofu.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.METALTOFU.get()))
					.save(consumer, "tofucraft:harder_tofu");

			AdvancementHolder fermentation_basics = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.KOUJI_BASE.get(),
							Component.translatable("advancements.tofucraft.fermentation_basics.title"),
							Component.translatable("advancements.tofucraft.fermentation_basics.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.KOUJI_BASE.get()))
					.save(consumer, "tofucraft:fermentation_basics");

			AdvancementHolder miso = Advancement.Builder.advancement()
					.parent(fermentation_basics)
					.display(TofuItems.MISO.get(),
							Component.translatable("advancements.tofucraft.miso.title"),
							Component.translatable("advancements.tofucraft.miso.desc"),
							null,
							AdvancementType.GOAL, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.MISO.get()))
					.save(consumer, "tofucraft:miso");

			AdvancementHolder tofu_stick = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.TOFUSTICK.get(),
							Component.translatable("advancements.tofucraft.tofustick.title"),
							Component.translatable("advancements.tofucraft.tofustick.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.TOFUSTICK.get()))
					.save(consumer, "tofucraft:tofustick");

			// Tofu World Start

			AdvancementHolder tofu_world = Advancement.Builder.advancement()
					.parent(tofu_stick)
					.display(TofuBlocks.TOFU_TERRAIN.get(),
							Component.translatable("advancements.tofucraft.tofuworld.title"),
							Component.translatable("advancements.tofucraft.tofuworld.desc"),
							null,
							AdvancementType.GOAL, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.TOFU_TERRAIN.get()))
					.save(consumer, "tofucraft:tofuworld");
			AdvancementHolder my_tofu_child = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get(),
							Component.translatable("advancements.tofucraft.my_tofu_child.title"),
							Component.translatable("advancements.tofucraft.my_tofu_child.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("trigger", MyTofuChildTrigger.get())
					.save(consumer, "tofucraft:my_tofu_child");
			AdvancementHolder find_tofu_castle = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(TofuBlocks.ISHITOFU_BRICK.get(),
							Component.translatable("advancements.tofucraft.find_tofu_castle.title"),
							Component.translatable("advancements.tofucraft.find_tofu_castle.desc"),
							null,
							AdvancementType.GOAL, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.ISHITOFU_BRICK.get()))
					.save(consumer, "tofucraft:find_tofu_castle");
			AdvancementHolder zunda_legends = Advancement.Builder.advancement()
					.rewards(AdvancementRewards.Builder.experience(100))
					.parent(find_tofu_castle)
					.display(TofuItems.ZUNDA_BOW.get(),
							Component.translatable("advancements.tofucraft.zunda_legends.title"),
							Component.translatable("advancements.tofucraft.zunda_legends.desc"),
							null,
							AdvancementType.CHALLENGE, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.ZUNDA_BOW.get()))
					.save(consumer, "tofucraft:zunda_legends");

			AdvancementHolder more_shiny_gear = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(
							TofuItems.TOFU_DIAMOND_CHESTPLATE.get(),
							Component.translatable("advancements.tofucraft.more_shiny_gear.title"),
							Component.translatable("advancements.tofucraft.more_shiny_gear.desc"),
							null,
							AdvancementType.CHALLENGE,
							true,
							true,
							false
					)
					.rewards(AdvancementRewards.Builder.experience(100))
					.addCriterion(
							"tofu_diamond_armor",
							InventoryChangeTrigger.TriggerInstance.hasItems(
									TofuItems.TOFU_DIAMOND_HELMET.get(), TofuItems.TOFU_DIAMOND_CHESTPLATE.get(), TofuItems.TOFU_DIAMOND_LEGGINGS.get(), TofuItems.TOFU_DIAMOND_BOOTS.get()
							)
					)
					.save(consumer, "tofucraft:tofu_diamond_armor");

			AdvancementHolder nightmare_echo = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(
							Items.ECHO_SHARD,
							Component.translatable("advancements.tofucraft.nightmares_echo.title"),
							Component.translatable("advancements.tofucraft.nightmares_echo.desc"),
							null,
							AdvancementType.TASK,
							true,
							true,
							false
					)
					.addCriterion(
							"echo",
							NightmaresEchoTrigger.get()
					)
					.save(consumer, "tofucraft:nightmares_echo");

			AdvancementHolder tofupig_pop = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(TofuItems.ZUNDAMUSHROOM_ON_A_STICK.get(),
							Component.translatable("advancements.tofucraft.tofupig_pop.title"),
							Component.translatable("advancements.tofucraft.tofupig_pop.desc"),
							null,
							AdvancementType.TASK, true, true, false)
					.addCriterion("trigger", TofuPigPopTrigger.get())
					.save(consumer, "tofucraft:tofupig_pop");
		}
	}
}
