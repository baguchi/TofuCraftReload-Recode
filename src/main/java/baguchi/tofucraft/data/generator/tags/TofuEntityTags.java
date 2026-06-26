package baguchi.tofucraft.data.generator.tags;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityTypes;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class TofuEntityTags extends EntityTypeTagsProvider {
	public TofuEntityTags(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_) {
		super(p_256095_, p_256572_, TofuCraftReload.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_255894_) {
		this.tag(EntityTypeTags.ARROWS).add(TofuEntityTypes.ZUNDA_ARROW.getKey());
		this.tag(Tags.EntityTypes.BOSSES).add(TofuEntityTypes.SHUDOFUSPIDER.getKey());
		this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(TofuEntityTypes.ZUNDAMITE.getKey());

		this.tag(TofuTags.EntityTypes.FUKUMAME).add(TofuEntityTypes.FUKUMAME.getKey()).add(TofuEntityTypes.SOUL_FUKUMAME.getKey()).add(TofuEntityTypes.NETHER_FUKUMAME.getKey());

		this.tag(EntityTypeTags.FROG_FOOD).add(TofuEntityTypes.TOFUSLIME.getKey(), TofuEntityTypes.OAGE_CUBE.getKey());
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(TofuEntityTypes.SHUDOFUSPIDER.getKey(), TofuEntityTypes.TOFU_GANDLEM.getKey(), TofuEntityTypes.TOFU_GOLEM.getKey(), TofuEntityTypes.OAGE_CUBE.getKey());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(TofuEntityTypes.SHUDOFUSPIDER.getKey(), TofuEntityTypes.TOFU_GANDLEM.getKey());
		this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(TofuEntityTypes.SHUDOFUSPIDER.getKey(), TofuEntityTypes.TOFU_GANDLEM.getKey(), TofuEntityTypes.TOFU_GOLEM.getKey()).add(TofuEntityTypes.TOFUFISH.getKey());
		this.tag(EntityTypeTags.ARTHROPOD).add(TofuEntityTypes.SHUDOFUSPIDER.getKey()).add(TofuEntityTypes.TOFUSPIDER.getKey()).add(TofuEntityTypes.ZUNDAMITE.getKey());
		this.tag(EntityTypeTags.AQUATIC).add(TofuEntityTypes.TOFUFISH.getKey());
		this.tag(TofuTags.EntityTypes.EXTRA_DAMAGE_ZUNDA).addTag(EntityTypeTags.UNDEAD).add(EntityTypes.ENDER_DRAGON.builtInRegistryHolder().key()).add(EntityTypes.ENDERMAN.builtInRegistryHolder().key())
				.add(EntityTypes.ENDERMITE.builtInRegistryHolder().key()).add(EntityTypes.SHULKER.builtInRegistryHolder().key()).add(EntityTypes.END_CRYSTAL.builtInRegistryHolder().key());
		this.tag(TofuTags.EntityTypes.WALKABLE_WITHOUT_TRIGGER).addTag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS);
		this.tag(EntityTypeTags.CAN_EQUIP_SADDLE)
				.add(
						TofuEntityTypes.TOFUPIG.getKey()
				);

		this.tag(EntityTypeTags.NON_CONTROLLING_RIDER).add(TofuEntityTypes.TOFUSLIME.getKey(), TofuEntityTypes.OAGE_CUBE.getKey());

	}
}