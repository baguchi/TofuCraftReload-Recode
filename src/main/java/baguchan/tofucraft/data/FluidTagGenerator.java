package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class FluidTagGenerator extends FluidTagsProvider {
	public FluidTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, ExistingFileHelper exFileHelper) {
		super(p_256095_, p_256572_, TofuCraftReload.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_255894_) {
		this.tag(TofuTags.Fluids.SOYMILK).add(TofuFluids.SOYMILK.get(), TofuFluids.SOYMILK_HELL.get(), TofuFluids.SOYMILK_SOUL.get())
				.add(TofuFluids.SOYMILK_FLOW.get(), TofuFluids.SOYMILK_HELL_FLOW.get(), TofuFluids.SOYMILK_SOUL_FLOW.get());
	}
}