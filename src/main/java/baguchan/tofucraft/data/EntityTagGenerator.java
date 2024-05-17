package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
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
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(TofuEntityTypes.SHUDOFUSPIDER.get(), TofuEntityTypes.TOFU_GANDLEM.get(), TofuEntityTypes.TOFU_GOLEM.get());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(TofuEntityTypes.SHUDOFUSPIDER.get(), TofuEntityTypes.TOFU_GANDLEM.get());
		this.tag(TofuTags.EntityTypes.EXTRA_DAMAGE_ZUNDA).add(EntityType.ENDER_DRAGON).add(EntityType.ENDERMAN).add(EntityType.ENDERMITE).add(EntityType.END_CRYSTAL);
	}
}