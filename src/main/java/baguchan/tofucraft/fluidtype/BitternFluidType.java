package baguchan.tofucraft.fluidtype;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class BitternFluidType extends FluidType {
	public BitternFluidType(Properties properties) {
		super(properties);
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = new ResourceLocation(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_FLOW = new ResourceLocation(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation(TofuCraftReload.MODID, "textures/block/bittern_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}
		});
	}
}
