package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.advancements.ChiliDistractionTrigger;
import baguchi.tofucraft.advancements.MyTofuChildTrigger;
import baguchi.tofucraft.advancements.NarrowEscapeTrigger;
import baguchi.tofucraft.advancements.NightmaresEchoTrigger;
import baguchi.tofucraft.advancements.TofuPigPopTrigger;
import baguchi.tofucraft.advancements.TooColdTrigger;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDimensions;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuStructures;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.predicates.BlockPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.triggers.ChangeDimensionTrigger;
import net.minecraft.advancements.triggers.ConsumeItemTrigger;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.SingleRegistryBootstrap;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.List;
import java.util.Optional;

public class TofuAdvancementGenerator extends AdvancementSubProvider {

	public static SingleRegistryBootstrap<Advancement> create() {
		return new AdvancementProvider(
				List.of(
						TofuAdvancementGenerator::new
				)
		);
	}

	protected TofuAdvancementGenerator(BootstrapContext<Advancement> output) {
		super(output);
	}

		@SuppressWarnings("unused")
		@Override
		public void generate() {
			HolderLookup<Item> items = this.output.holderLookup(Registries.ITEM).get();
			HolderLookup<Block> blocks = this.output.holderLookup(Registries.BLOCK).get();

			HolderLookup<Structure> structures = this.output.holderLookup(Registries.STRUCTURE).get();

			AdvancementHolder root = Advancement.Builder.advancement()
					.display(new DisplayInfo(new ItemStackTemplate(TofuItems.SEEDS_SOYBEANS.get()),
							Component.translatable("advancements.tofucraft.root.title"),
							Component.translatable("advancements.tofucraft.root.desc"),
							Optional.of(new ClientAsset.ResourceTexture(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "gui/advancements/backgrounds/blocktofukinu"))),
							AdvancementType.TASK, true, true, false))
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.SEEDS_SOYBEANS.get()))
					.save(output, "tofucraft:root");
			AdvancementHolder make_saltpan = Advancement.Builder.advancement()
					.parent(root)
					.display(TofuBlocks.SALTPAN.get().asItem(),
							Component.translatable("advancements.tofucraft.make_saltpan.title"),
							Component.translatable("advancements.tofucraft.make_saltpan.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.SALTPAN.get()))
					.save(output, "tofucraft:make_saltpan");
			AdvancementHolder bitter_better = Advancement.Builder.advancement()
					.parent(make_saltpan)
					.display(TofuBlocks.SALT_FURNACE.get().asItem(),
							Component.translatable("advancements.tofucraft.bitter_better.title"),
							Component.translatable("advancements.tofucraft.bitter_better.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.SALT_FURNACE.get()))
					.save(output, "tofucraft:bitter_better");
			AdvancementHolder bittern_bottle = Advancement.Builder.advancement()
					.parent(make_saltpan)
					.display(TofuItems.BITTERN_BOTTLE.get(),
							Component.translatable("advancements.tofucraft.bittern_bottle.title"),
							Component.translatable("advancements.tofucraft.bittern_bottle.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.BITTERN_BOTTLE.get()))
					.save(output, "tofucraft:bittern_bottle");
			AdvancementHolder make_tofu = Advancement.Builder.advancement()
					.parent(bittern_bottle)
					.display(TofuItems.TOFU_KINU.get(),
							Component.translatable("advancements.tofucraft.make_tofu.title"),
							Component.translatable("advancements.tofucraft.make_tofu.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("do_the_tofu", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location()
									.setBlock(
											BlockPredicate.Builder.block()
													.of(
															blocks,
															TofuBlocks.SOYMILK.get()
													)
									),
							ItemPredicate.Builder.item().of(items, TofuItems.BITTERN_BOTTLE.get())))
					.save(output, "tofucraft:make_tofu");

			AdvancementHolder eat_tofu_block = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuBlocks.GRILLED_TOFU.get().asItem(),
							Component.translatable("advancements.tofucraft.eat_tofu_block.title"),
							Component.translatable("advancements.tofucraft.eat_tofu_block.desc"),
							AdvancementType.CHALLENGE, true, true, true)
					.addCriterion("has_item", ConsumeItemTrigger.TriggerInstance.usedItem(items, TofuBlocks.GRILLED_TOFU.get()))
					.rewards(AdvancementRewards.Builder.experience(50))
					.save(output, "tofucraft:eat_tofu_block");

			AdvancementHolder ancient_chili = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.CHILI.get(),
							Component.translatable("advancements.tofucraft.ancient_chili.title"),
							Component.translatable("advancements.tofucraft.ancient_chili.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.CHILI.get()))
					.save(output, "tofucraft:ancient_chili");
			AdvancementHolder narrow_escape = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.TOFU_KINU_BOOTS.get(),
							Component.translatable("advancements.tofucraft.narrow_escape.title"),
							Component.translatable("advancements.tofucraft.narrow_escape.desc"),
							AdvancementType.CHALLENGE, true, true, false)
					.rewards(AdvancementRewards.Builder.experience(100))
					.addCriterion("has_item", NarrowEscapeTrigger.get())
					.save(output, "tofucraft:narrow_escape");


			AdvancementHolder sniffed_revenge = Advancement.Builder.advancement()
					.parent(ancient_chili)
					.display(TofuItems.CHILI.get(),
							Component.translatable("advancements.tofucraft.sniffed_revenge.title"),
							Component.translatable("advancements.tofucraft.sniffed_revenge.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", ChiliDistractionTrigger.get())
					.save(output, "tofucraft:sniffed_revenge");


			AdvancementHolder nether_soybeans = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.SEEDS_SOYBEANS_NETHER.get(),
							Component.translatable("advancements.tofucraft.nether_soybeans.title"),
							Component.translatable("advancements.tofucraft.nether_soybeans.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.SEEDS_SOYBEANS_NETHER.get()))
					.save(output, "tofucraft:nether_soybeans");

			AdvancementHolder very_strange_soybeans = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.SEEDS_SOYBEANS_SOUL.get(),
							Component.translatable("advancements.tofucraft.very_strange_soybeans.title"),
							Component.translatable("advancements.tofucraft.very_strange_soybeans.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.SEEDS_SOYBEANS_SOUL.get()))
					.save(output, "tofucraft:very_strange_soybeans");

			AdvancementHolder too_cold = Advancement.Builder.advancement()
					.parent(very_strange_soybeans)
					.display(TofuItems.SOUL_HIYAYAKKO_GLASS.get(),
							Component.translatable("advancements.tofucraft.too_cold.title"),
							Component.translatable("advancements.tofucraft.too_cold.desc"),
							AdvancementType.CHALLENGE, true, true, false)
					.addCriterion("has_cold", TooColdTrigger.get())
					.rewards(AdvancementRewards.Builder.experience(200))
					.save(output, "tofucraft:too_cold");


			AdvancementHolder harder_tofu = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuBlocks.METALTOFU.get().asItem(),
							Component.translatable("advancements.tofucraft.harder_tofu.title"),
							Component.translatable("advancements.tofucraft.harder_tofu.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.METALTOFU.get()))
					.save(output, "tofucraft:harder_tofu");

			AdvancementHolder fermentation_basics = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(TofuItems.KOUJI_BASE.get(),
							Component.translatable("advancements.tofucraft.fermentation_basics.title"),
							Component.translatable("advancements.tofucraft.fermentation_basics.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.KOUJI_BASE.get()))
					.save(output, "tofucraft:fermentation_basics");

			AdvancementHolder miso = Advancement.Builder.advancement()
					.parent(fermentation_basics)
					.display(TofuItems.MISO.get(),
							Component.translatable("advancements.tofucraft.miso.title"),
							Component.translatable("advancements.tofucraft.miso.desc"),
							AdvancementType.GOAL, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.MISO.get()))
					.save(output, "tofucraft:miso");

			AdvancementHolder tofu_stick = Advancement.Builder.advancement()
					.parent(make_tofu)
					.display(new ItemStackTemplate(TofuItems.TOFUSTICK.get()),
							Component.translatable("advancements.tofucraft.tofustick.title"),
							Component.translatable("advancements.tofucraft.tofustick.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.TOFUSTICK.get()))
					.save(output, "tofucraft:tofustick");

			// Tofu World Start

			AdvancementHolder tofu_world = Advancement.Builder.advancement()
					.parent(tofu_stick)
					.display(new ItemStackTemplate(TofuBlocks.TOFU_TERRAIN.get().asItem()),
							Component.translatable("advancements.tofucraft.tofuworld.title"),
							Component.translatable("advancements.tofucraft.tofuworld.desc"),
							AdvancementType.GOAL, true, true, false)
					.addCriterion("has_item", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(TofuDimensions.tofu_world))
					.save(output, "tofucraft:tofuworld");


			AdvancementHolder tofu_industry_key = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(new ItemStackTemplate(TofuItems.TOFUGEM.get()),
							Component.translatable("advancements.tofucraft.tofu_industry_key.title"),
							Component.translatable("advancements.tofucraft.tofu_industry_key.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("trigger", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.TOFUGEM.get()))
					.save(output, "tofucraft:tofu_industry_key");
			AdvancementHolder tofu_industry = Advancement.Builder.advancement()
					.parent(tofu_industry_key)
					.display(new ItemStackTemplate(TofuBlocks.TF_STORAGE.get().asItem()),
							Component.translatable("advancements.tofucraft.tofu_industry.title"),
							Component.translatable("advancements.tofucraft.tofu_industry.desc"),
							AdvancementType.GOAL, true, true, false)
					.addCriterion("trigger", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.TF_STORAGE.get()))
					.save(output, "tofucraft:tofu_industry");
			AdvancementHolder tofu_of_creative = Advancement.Builder.advancement()
					.parent(tofu_industry)
					.display(new ItemStackTemplate(TofuBlocks.TF_CRAFTING_TABLE.get().asItem()),
							Component.translatable("advancements.tofucraft.tofu_of_creative.title"),
							Component.translatable("advancements.tofucraft.tofu_of_creative.desc"),
							AdvancementType.GOAL, true, true, false)
					.addCriterion("trigger", InventoryChangeTrigger.TriggerInstance.hasItems(TofuBlocks.TF_CRAFTING_TABLE.get()))
					.save(output, "tofucraft:tofu_of_creative");



			AdvancementHolder my_tofu_child = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(new ItemStackTemplate(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get()),
							Component.translatable("advancements.tofucraft.my_tofu_child.title"),
							Component.translatable("advancements.tofucraft.my_tofu_child.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("trigger", MyTofuChildTrigger.get())
					.save(output, "tofucraft:my_tofu_child");
			AdvancementHolder find_tofu_castle = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(new ItemStackTemplate(TofuBlocks.ISHITOFU_BRICK.get().asItem()),
							Component.translatable("advancements.tofucraft.find_tofu_castle.title"),
							Component.translatable("advancements.tofucraft.find_tofu_castle.desc"),
							AdvancementType.GOAL, true, true, false)
					.addCriterion("has_item", PlayerTrigger.TriggerInstance.located(
							LocationPredicate.Builder.inStructure(structures.getOrThrow(TofuStructures.TOFU_CASTLE))
					))
					.save(output, "tofucraft:find_tofu_castle");
			AdvancementHolder zunda_legends = Advancement.Builder.advancement()
					.rewards(AdvancementRewards.Builder.experience(100))
					.parent(find_tofu_castle)
					.display(new ItemStackTemplate(TofuItems.ZUNDA_BOW.get()),
							Component.translatable("advancements.tofucraft.zunda_legends.title"),
							Component.translatable("advancements.tofucraft.zunda_legends.desc"),
							AdvancementType.CHALLENGE, true, true, false)
					.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(TofuItems.ZUNDA_BOW.get()))
					.save(output, "tofucraft:zunda_legends");


			AdvancementHolder yearn_for_tofu_diamond = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(
							new ItemStackTemplate(TofuItems.TOFU_DIAMOND.get()),
							Component.translatable("advancements.tofucraft.yearn_for_tofu_diamond.title"),
							Component.translatable("advancements.tofucraft.yearn_for_tofu_diamond.desc"),
							AdvancementType.GOAL,
							true,
							true,
							false
					)
					.addCriterion(
							"tofu_diamond",
							InventoryChangeTrigger.TriggerInstance.hasItems(
									TofuItems.TOFU_DIAMOND.get()
							)
					)
					.save(output, "tofucraft:yearn_for_tofu_diamond");


			AdvancementHolder more_shiny_gear = Advancement.Builder.advancement()
					.parent(yearn_for_tofu_diamond)
					.display(
							new ItemStackTemplate(TofuItems.TOFU_DIAMOND_CHESTPLATE.get()),
							Component.translatable("advancements.tofucraft.more_shiny_gear.title"),
							Component.translatable("advancements.tofucraft.more_shiny_gear.desc"),
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
					.save(output, "tofucraft:tofu_diamond_armor");


			AdvancementHolder nightmare_echo = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(
							new ItemStackTemplate(Items.ECHO_SHARD),
							Component.translatable("advancements.tofucraft.nightmares_echo.title"),
							Component.translatable("advancements.tofucraft.nightmares_echo.desc"),
							AdvancementType.TASK,
							true,
							true,
							false
					)
					.addCriterion(
							"echo",
							NightmaresEchoTrigger.get()
					)
					.save(output, "tofucraft:nightmares_echo");

			AdvancementHolder tofupig_pop = Advancement.Builder.advancement()
					.parent(tofu_world)
					.display(new ItemStackTemplate(TofuItems.ZUNDAMUSHROOM_ON_A_STICK.get()),
							Component.translatable("advancements.tofucraft.tofupig_pop.title"),
							Component.translatable("advancements.tofucraft.tofupig_pop.desc"),
							AdvancementType.TASK, true, true, false)
					.addCriterion("trigger", TofuPigPopTrigger.get())
					.save(output, "tofucraft:tofupig_pop");
		}
}
