package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuModelLayers {
	public static final ModelLayerLocation TOFUNIAN = new ModelLayerLocation(new ResourceLocation(TofuCraftReload.MODID, "tofunian"), "tofunian");
	public static final ModelLayerLocation TOFUSPIDER = new ModelLayerLocation(new ResourceLocation(TofuCraftReload.MODID, "tofuspider"), "tofuspider");
	public static final ModelLayerLocation TOFUFISH = new ModelLayerLocation(new ResourceLocation(TofuCraftReload.MODID, "tofufish"), "tofufish");

	public static final ModelLayerLocation TOFU_GANDLEM = new ModelLayerLocation(new ResourceLocation(TofuCraftReload.MODID, "tofu_gandlem"), "tofu_gandlem");

	public static final ModelLayerLocation SHUDOFUSPIDER = new ModelLayerLocation(new ResourceLocation(TofuCraftReload.MODID, "shudofuspider"), "shudofuspider");

}
