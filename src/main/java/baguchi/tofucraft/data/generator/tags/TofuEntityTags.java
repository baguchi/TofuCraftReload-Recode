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
		this.tag(EntityTypeTags.ARROWS).add(TofuEntityTypes.ZUNDA_ARROW.key());
		this.tag(Tags.EntityTypes.BOSSES).add(TofuEntityTypes.SHUDOFUSPIDER.key());
		this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(TofuEntityTypes.ZUNDAMITE.key());

		this.tag(TofuTags.EntityTypes.FUKUMAME).add(TofuEntityTypes.FUKUMAME.key()).add(TofuEntityTypes.SOUL_FUKUMAME.key()).add(TofuEntityTypes.NETHER_FUKUMAME.key());

		this.tag(EntityTypeTags.FROG_FOOD).add(TofuEntityTypes.TOFU_SLIME.key(), TofuEntityTypes.OAGE_CUBE.key());
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(TofuEntityTypes.SHUDOFUSPIDER.key(), TofuEntityTypes.TOFU_GANDLEM.key(), TofuEntityTypes.TOFU_GOLEM.key(), TofuEntityTypes.OAGE_CUBE.key());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(TofuEntityTypes.SHUDOFUSPIDER.key(), TofuEntityTypes.TOFU_GANDLEM.key());
		this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(TofuEntityTypes.SHUDOFUSPIDER.key(), TofuEntityTypes.TOFU_GANDLEM.key(), TofuEntityTypes.TOFU_GOLEM.key()).add(TofuEntityTypes.TOFU_FISH.key());
		this.tag(EntityTypeTags.ARTHROPOD).add(TofuEntityTypes.SHUDOFUSPIDER.key()).add(TofuEntityTypes.TOFU_SPIDER.key()).add(TofuEntityTypes.ZUNDAMITE.key());
		this.tag(EntityTypeTags.AQUATIC).add(TofuEntityTypes.TOFU_FISH.key());
		this.tag(TofuTags.EntityTypes.EXTRA_DAMAGE_ZUNDA).addTag(EntityTypeTags.UNDEAD).add(EntityTypes.ENDER_DRAGON.builtInRegistryHolder().key()).add(EntityTypes.ENDERMAN.builtInRegistryHolder().key())
				.add(EntityTypes.ENDERMITE.builtInRegistryHolder().key()).add(EntityTypes.SHULKER.builtInRegistryHolder().key()).add(EntityTypes.END_CRYSTAL.builtInRegistryHolder().key());
		this.tag(TofuTags.EntityTypes.WALKABLE_WITHOUT_TRIGGER).addTag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS);
		this.tag(EntityTypeTags.CAN_EQUIP_SADDLE)
				.add(
						TofuEntityTypes.TOFU_PIG.key()
				);

		this.tag(EntityTypeTags.NON_CONTROLLING_RIDER).add(TofuEntityTypes.TOFU_SLIME.key(), TofuEntityTypes.OAGE_CUBE.key());

	}
}