package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
	public EntityTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, ExistingFileHelper exFileHelper) {
		super(p_256095_, p_256572_, TofuCraftReload.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_255894_) {
		this.tag(EntityTypeTags.ARROWS).add(TofuEntityTypes.ZUNDA_ARROW.get());
		this.tag(Tags.EntityTypes.BOSSES).add(TofuEntityTypes.SHUDOFUSPIDER.get());
		this.tag(EntityTypeTags.FROG_FOOD).add(TofuEntityTypes.TOFUSLIME.get());
	}
}