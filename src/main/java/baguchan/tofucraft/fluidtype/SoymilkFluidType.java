package baguchan.tofucraft.fluidtype;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.IFluidTypeRenderProperties;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SoymilkFluidType extends FluidType {
	public SoymilkFluidType(FluidType.Properties properties) {
		super(properties);
	}

	@Override
	public void initializeClient(Consumer<IFluidTypeRenderProperties> consumer) {
		consumer.accept(new IFluidTypeRenderProperties() {
			private static final ResourceLocation TEXTURE_STILL = new ResourceLocation(TofuCraftReload.MODID, "block/soymilk");
			private static final ResourceLocation TEXTURE_FLOW = new ResourceLocation(TofuCraftReload.MODID, "block/soymilk_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation(TofuCraftReload.MODID, "textures/block/soymilk_overlay.png");

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
