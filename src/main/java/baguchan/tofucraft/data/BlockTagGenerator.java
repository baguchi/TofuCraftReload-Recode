package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGenerator extends BlockTagsProvider {
	public BlockTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, TofuCraftReload.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(TofuBlocks.KINUTOFU, TofuBlocks.MOMENTOFU, TofuBlocks.HELLTOFU, TofuBlocks.SOULTOFU
				, TofuBlocks.GRILLEDTOFU, TofuBlocks.ZUNDATOFU
				, TofuBlocks.TOFUSTAIR_KINU, TofuBlocks.TOFUSTAIR_MOMEN, TofuBlocks.TOFUSTAIR_ZUNDA
				, TofuBlocks.TOFUSLAB_KINU, TofuBlocks.TOFUSLAB_MOMEN, TofuBlocks.TOFUSLAB_ZUNDA
				, TofuBlocks.TOFULADDER_KINU, TofuBlocks.TOFULADDER_MOMEN
				, TofuBlocks.TOFU_TERRAIN, TofuBlocks.ORE_TOFU_DIAMOND);
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TofuBlocks.ISHITOFU, TofuBlocks.ISHITOFU_BRICK, TofuBlocks.ISHITOFU_SMOOTH_BRICK, TofuBlocks.ISHITOFU_CHISELED_BRICK
				, TofuBlocks.METALTOFU, TofuBlocks.DIAMONDTOFU
				, TofuBlocks.HELLTOFU_BRICK, TofuBlocks.HELLTOFU_SMOOTH_BRICK
				, TofuBlocks.SOULTOFU_BRICK, TofuBlocks.SOULTOFU_SMOOTH_BRICK
				, TofuBlocks.TOFUSTAIR_ISHI, TofuBlocks.TOFUSTAIR_METAL, TofuBlocks.TOFUSTAIR_ISHIBRICK, TofuBlocks.TOFUSTAIR_HELLBRICK, TofuBlocks.TOFUSTAIR_SOULBRICK
				, TofuBlocks.TOFUSLAB_ISHI, TofuBlocks.TOFUSLAB_METAL, TofuBlocks.TOFUSLAB_ISHIBRICK, TofuBlocks.TOFUSLAB_HELLBRICK, TofuBlocks.TOFUSLAB_SOULBRICK
				, TofuBlocks.TOFULADDER_ISHI, TofuBlocks.TOFULADDER_ISHIBRICK, TofuBlocks.TOFULADDER_METAL
				, TofuBlocks.TOFUCHEST
				, TofuBlocks.TOFU_BEDROCK);

		tag(BlockTags.NEEDS_STONE_TOOL)
				.add(TofuBlocks.METALTOFU);
		tag(BlockTags.NEEDS_IRON_TOOL)
				.add(TofuBlocks.DIAMONDTOFU);
		tag(BlockTags.PIGLIN_REPELLENTS).add(TofuBlocks.SOYBEAN_SOUL);

		tag(TofuTags.Blocks.SOFT_TOFU).add(TofuBlocks.KINUTOFU, TofuBlocks.MOMENTOFU, TofuBlocks.HELLTOFU, TofuBlocks.SOULTOFU
				, TofuBlocks.GRILLEDTOFU, TofuBlocks.ZUNDATOFU);
		tag(BlockTags.BEDS).add(TofuBlocks.TOFUBED);
		tag(BlockTags.CLIMBABLE).add(TofuBlocks.TOFULADDER_KINU, TofuBlocks.TOFULADDER_MOMEN, TofuBlocks.TOFULADDER_ISHI, TofuBlocks.TOFULADDER_ISHIBRICK, TofuBlocks.TOFULADDER_METAL);
	}
}