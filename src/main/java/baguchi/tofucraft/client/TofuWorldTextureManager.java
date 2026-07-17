package baguchi.tofucraft.client;

import baguchi.tofucraft.client.render.dimension.TofuWorldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.jspecify.annotations.Nullable;

public class TofuWorldTextureManager implements ResourceManagerReloadListener {
	public static final TofuWorldTextureManager INSTANCE = new TofuWorldTextureManager();


	private @Nullable TofuWorldRenderer tofuWorldRenderer;

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		tofuWorldRenderer = new TofuWorldRenderer(Minecraft.getInstance().getAtlasManager(), Minecraft.getInstance().gameRenderer.mainRenderTarget());
	}

	public @Nullable TofuWorldRenderer getTofuWorldRenderer() {
		return tofuWorldRenderer;
	}
}
