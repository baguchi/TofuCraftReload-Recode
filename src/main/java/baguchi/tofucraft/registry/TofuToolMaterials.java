package baguchi.tofucraft.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Supplier;

public interface TofuToolMaterials {
	TofuToolMaterial KINU = new TofuToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1, 0.1F, 0.0F, 2, TofuItems.TOFUKINU);
	TofuToolMaterial MOMEN = new TofuToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1, 0.25F, 0.25F, 5, TofuItems.TOFUMOMEN);
	TofuToolMaterial SOLID = new TofuToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 126, 5.0F, 1.0F, 12, TofuItems.TOFUISHI);
	TofuToolMaterial METAL = new TofuToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 245, 6.0F, 2.0F, 16, TofuItems.TOFUMETAL);
	TofuToolMaterial TOFUDIAMOND = new TofuToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1624, 8.5F, 5.0F, 18, TofuItems.TOFUDIAMOND);

	public record TofuToolMaterial(
			TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus,
			int enchantmentValue, Supplier<Item> repairItems
	) {

		private Item.Properties applyCommonProperties(Item.Properties p_363001_) {
			return p_363001_.durability(this.durability).repairable(this.repairItems.get()).enchantable(this.enchantmentValue);
		}

		public Item.Properties applyToolProperties(Item.Properties p_361405_, TagKey<Block> p_360697_, float p_363434_, float p_364177_) {
			HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
			return this.applyCommonProperties(p_361405_)
					.component(
							DataComponents.TOOL,
							new Tool(
									List.of(
											Tool.Rule.deniesDrops(holdergetter.getOrThrow(this.incorrectBlocksForDrops)),
											Tool.Rule.minesAndDrops(holdergetter.getOrThrow(p_360697_), this.speed)
									),
									1.0F,
									1
							)
					)
					.attributes(this.createToolAttributes(p_363434_, p_364177_));
		}

		private ItemAttributeModifiers createToolAttributes(float p_360296_, float p_360629_) {
			return ItemAttributeModifiers.builder()
					.add(
							Attributes.ATTACK_DAMAGE,
							new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (double) (p_360296_ + this.attackDamageBonus), AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.MAINHAND
					)
					.add(
							Attributes.ATTACK_SPEED,
							new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, (double) p_360629_, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.MAINHAND
					)
					.build();
		}

		public Item.Properties applySwordProperties(Item.Properties p_363768_, float p_361044_, float p_361067_) {
			HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
			return this.applyCommonProperties(p_363768_)
					.component(
							DataComponents.TOOL,
							new Tool(
									List.of(
											Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
											Tool.Rule.overrideSpeed(holdergetter.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
									),
									1.0F,
									2
							)
					)
					.attributes(this.createSwordAttributes(p_361044_, p_361067_));
		}

		private ItemAttributeModifiers createSwordAttributes(float p_364643_, float p_363683_) {
			return ItemAttributeModifiers.builder()
					.add(
							Attributes.ATTACK_DAMAGE,
							new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (double) (p_364643_ + this.attackDamageBonus), AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.MAINHAND
					)
					.add(
							Attributes.ATTACK_SPEED,
							new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, (double) p_363683_, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.MAINHAND
					)
					.build();
		}
	}
}
