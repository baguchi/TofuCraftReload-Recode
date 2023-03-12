package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
    public EntityTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator, TofuCraftReload.MODID, exFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(EntityTypeTags.ARROWS).add(TofuEntityTypes.ZUNDA_ARROW.get());
    }
}