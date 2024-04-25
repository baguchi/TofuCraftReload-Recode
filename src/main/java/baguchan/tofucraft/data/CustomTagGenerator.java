package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class CustomTagGenerator {
	public static class BannerPatternTagGenerator extends BannerPatternTagsProvider {

		public static final TagKey<BannerPattern> TOFUNIAN_BANNER_PATTERN = create("pattern_item/tofunian");

		public BannerPatternTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
			super(output, provider, TofuCraftReload.MODID, existingFileHelper);
		}

		private static TagKey<BannerPattern> create(String name) {
			return TagKey.create(Registries.BANNER_PATTERN, TofuCraftReload.prefix(name));
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			//tag(TOFUNIAN_BANNER_PATTERN).add(TofuBannerPatterns.TOFUNIAN_KEY);
		}
	}
}
