package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.JukeboxSong;

public interface TofuJukeboxSongs {
	ResourceKey<JukeboxSong> GREEN_BRANCH = create("green_branch");

	private static ResourceKey<JukeboxSong> create(String p_350505_) {
		return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_350505_));
	}
}
