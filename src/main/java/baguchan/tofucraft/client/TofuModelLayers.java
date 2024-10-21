package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuModelLayers {
	public static final ModelLayerLocation TOFUNIAN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofunian"), "main");
	public static final ModelLayerLocation TRAVELER_TOFUNIAN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "traveler_tofunian"), "main");
	public static final ModelLayerLocation TOFUSPIDER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofuspider"), "main");
	public static final ModelLayerLocation TOFUFISH = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofufish"), "main");
	public static final ModelLayerLocation TOFU_GOLEM = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_golem"), "main");

	public static final ModelLayerLocation TOFU_GANDLEM = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_gandlem"), "main");

	public static final ModelLayerLocation SHUDOFUSPIDER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "shudofuspider"), "main");

	public static final ModelLayerLocation FUKUMAME_THOWER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "fukumame_thower"), "main");

	public static final ModelLayerLocation SOYBALL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "soyball"), "main");

}
