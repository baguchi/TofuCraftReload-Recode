package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FluidTagGenerator extends FluidTagsProvider {
	public FluidTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, TofuCraftReload.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		this.tag(TofuTags.Fluids.SOYMILK).add(TofuFluids.SOYMILK.get(), TofuFluids.SOYMILK_HELL.get(), TofuFluids.SOYMILK_SOUL.get())
				.add(TofuFluids.SOYMILK_FLOW.get(), TofuFluids.SOYMILK_HELL_FLOW.get(), TofuFluids.SOYMILK_SOUL_FLOW.get());
	}
}