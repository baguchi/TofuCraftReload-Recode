package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;

import java.util.concurrent.CompletableFuture;

public class FluidTagGenerator extends FluidTagsProvider {
	public FluidTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_) {
		super(p_256095_, p_256572_, TofuCraftReload.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_255894_) {
		this.tag(TofuTags.Fluids.SOYMILK).add(TofuFluids.SOYMILK.key(), TofuFluids.SOYMILK_HELL.key(), TofuFluids.SOYMILK_SOUL.key())
				.add(TofuFluids.SOYMILK_FLOW.key(), TofuFluids.SOYMILK_HELL_FLOW.key(), TofuFluids.SOYMILK_SOUL_FLOW.key());
		this.tag(TofuTags.Fluids.DOUBANJIANG).add(TofuFluids.DOUBANJIANG.key())
				.add(TofuFluids.DOUBANJIANG_FLOW.key());
		this.tag(TofuTags.Fluids.WATER_LIKE).add(TofuFluids.BITTERN.key())
				.add(TofuFluids.BITTERN_FLOW.key());
	}
}