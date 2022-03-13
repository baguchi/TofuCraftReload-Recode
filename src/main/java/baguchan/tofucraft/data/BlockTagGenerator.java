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
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(TofuBlocks.KINUTOFU.get(), TofuBlocks.MOMENTOFU.get(), TofuBlocks.HELLTOFU.get(), TofuBlocks.SOULTOFU.get()
				, TofuBlocks.GRILLEDTOFU.get(), TofuBlocks.ZUNDATOFU.get()
				, TofuBlocks.TOFUSTAIR_KINU.get(), TofuBlocks.TOFUSTAIR_MOMEN.get(), TofuBlocks.TOFUSTAIR_ZUNDA.get()
				, TofuBlocks.TOFUSLAB_KINU.get(), TofuBlocks.TOFUSLAB_MOMEN.get(), TofuBlocks.TOFUSLAB_ZUNDA.get()
				, TofuBlocks.TOFULADDER_KINU.get(), TofuBlocks.TOFULADDER_MOMEN.get()
				, TofuBlocks.TOFUFENCE_KINU.get(), TofuBlocks.TOFUFENCE_MOMEN.get()
				, TofuBlocks.TOFU_TERRAIN.get(), TofuBlocks.ORE_TOFU_DIAMOND.get()
				, TofuBlocks.EGGTOFU.get(), TofuBlocks.TOFUSTAIR_EGG.get(), TofuBlocks.TOFUSLAB_EGG.get()
				, TofuBlocks.MISOTOFU.get(), TofuBlocks.TOFUSTAIR_MISO.get(), TofuBlocks.TOFUSLAB_MISO.get());
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TofuBlocks.ISHITOFU.get(), TofuBlocks.ISHITOFU_BRICK.get(), TofuBlocks.ISHITOFU_SMOOTH_BRICK.get(), TofuBlocks.ISHITOFU_CHISELED_BRICK.get()
				, TofuBlocks.METALTOFU.get(), TofuBlocks.DIAMONDTOFU.get()
				, TofuBlocks.HELLTOFU_BRICK.get(), TofuBlocks.HELLTOFU_SMOOTH_BRICK.get()
				, TofuBlocks.SOULTOFU_BRICK.get(), TofuBlocks.SOULTOFU_SMOOTH_BRICK.get()
				, TofuBlocks.TOFUSTAIR_ISHI.get(), TofuBlocks.TOFUSTAIR_METAL.get(), TofuBlocks.TOFUSTAIR_ISHIBRICK.get(), TofuBlocks.TOFUSTAIR_HELLBRICK.get(), TofuBlocks.TOFUSTAIR_SOULBRICK.get()
				, TofuBlocks.TOFUSLAB_ISHI.get(), TofuBlocks.TOFUSLAB_METAL.get(), TofuBlocks.TOFUSLAB_ISHIBRICK.get(), TofuBlocks.TOFUSLAB_HELLBRICK.get(), TofuBlocks.TOFUSLAB_SOULBRICK.get()
				, TofuBlocks.TOFULADDER_ISHI.get(), TofuBlocks.TOFULADDER_ISHIBRICK.get(), TofuBlocks.TOFULADDER_METAL.get()
				, TofuBlocks.TOFUFENCE_ISHI.get(), TofuBlocks.TOFUFENCE_METAL.get()
				, TofuBlocks.TOFUCHEST.get()
				, TofuBlocks.TOFU_BEDROCK.get()
				, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get()
				, TofuBlocks.TOFUSLATE.get()
				, TofuBlocks.SALT_FURNACE.get()
				, TofuBlocks.TF_STORAGE.get()
				, TofuBlocks.ANTENNA_BASIC.get());
		tag(BlockTags.MINEABLE_WITH_AXE).add(TofuBlocks.SALTPAN.get()
				, TofuBlocks.LEEK_GREEN_STEM.get(), TofuBlocks.LEEK_STEM.get()
				, TofuBlocks.TOFU_STEM.get(), TofuBlocks.TOFU_STEM_PLANKS.get()
				, TofuBlocks.MORIJIO.get()
				, TofuBlocks.BARREL_MISO.get());

		tag(BlockTags.NEEDS_STONE_TOOL)
				.add(TofuBlocks.METALTOFU.get(), TofuBlocks.TOFUSTAIR_METAL.get(), TofuBlocks.TOFUSLAB_METAL.get(), TofuBlocks.TOFULADDER_METAL.get(), TofuBlocks.TOFUFENCE_METAL.get());
		tag(BlockTags.NEEDS_IRON_TOOL)
				.add(TofuBlocks.DIAMONDTOFU.get(), TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		tag(BlockTags.PIGLIN_REPELLENTS).add(TofuBlocks.SOYBEAN_SOUL.get());

		tag(TofuTags.Blocks.SOFT_TOFU).add(TofuBlocks.KINUTOFU.get(), TofuBlocks.MOMENTOFU.get(), TofuBlocks.HELLTOFU.get(), TofuBlocks.SOULTOFU.get()
				, TofuBlocks.GRILLEDTOFU.get(), TofuBlocks.ZUNDATOFU.get());
		tag(BlockTags.BEDS).add(TofuBlocks.TOFUBED.get());
		tag(BlockTags.WALLS).add(TofuBlocks.TOFUFENCE_KINU.get(), TofuBlocks.TOFUFENCE_MOMEN.get(), TofuBlocks.TOFUFENCE_ISHI.get(), TofuBlocks.TOFUFENCE_METAL.get());
		tag(BlockTags.CLIMBABLE).add(TofuBlocks.TOFULADDER_KINU.get(), TofuBlocks.TOFULADDER_MOMEN.get(), TofuBlocks.TOFULADDER_ISHI.get(), TofuBlocks.TOFULADDER_ISHIBRICK.get(), TofuBlocks.TOFULADDER_METAL.get());
		tag(BlockTags.WITHER_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.get());
		tag(BlockTags.DRAGON_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.get());
	}
}