package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FluidTagGenerator extends FluidTagsProvider {
	public FluidTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, TofuCraftReload.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(FluidTags.WATER).add(TofuFluids.SOYMILK, TofuFluids.SOYMILK_FLOW
				, TofuFluids.SOYMILK_HELL, TofuFluids.SOYMILK_HELL_FLOW
				, TofuFluids.SOYMILK_SOUL, TofuFluids.SOYMILK_SOUL_FLOW
				, TofuFluids.BITTERN, TofuFluids.BITTERN_FLOW);
	}
}