package baguchan.tofucraft.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class TofuToolMaterials {
	public static final ToolMaterial KINU = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1, 0.1F, 0.0F, 2, TofuTags.Items.TOFU_TOOL_MATERIAL);
	public static final ToolMaterial MOMEN = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1, 0.25F, 0.25F, 5, TofuTags.Items.TOFU_TOOL_MATERIAL);
	public static final ToolMaterial SOLID = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 126, 5.0F, 1.0F, 12, TofuTags.Items.TOFU_SOLID_TOOL_MATERIAL);
	public static final ToolMaterial METAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 245, 6.0F, 2.0F, 16, TofuTags.Items.TOFU_METAL_TOOL_MATERIAL);
	public static final ToolMaterial TOFUDIAMOND = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2224, 8.5F, 5.0F, 18, TofuTags.Items.TOFU_DIAMOND_TOOL_MATERIAL);
}
