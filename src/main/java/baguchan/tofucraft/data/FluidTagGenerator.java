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
		tag(FluidTags.WATER).add(TofuFluids.SOYMILK.get(), TofuFluids.SOYMILK_FLOW.get()
				, TofuFluids.SOYMILK_HELL.get(), TofuFluids.SOYMILK_HELL_FLOW.get()
				, TofuFluids.SOYMILK_SOUL.get(), TofuFluids.SOYMILK_SOUL_FLOW.get()
				, TofuFluids.BITTERN.get(), TofuFluids.BITTERN_FLOW.get());
	}
}