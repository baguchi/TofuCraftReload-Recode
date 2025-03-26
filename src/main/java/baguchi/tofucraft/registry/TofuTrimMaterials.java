package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.resources.TofuMaterialAssets;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class TofuTrimMaterials {

	public static final ResourceKey<TrimMaterial> TOFU_METAL = registerKey("tofu_metal");
	public static final ResourceKey<TrimMaterial> TOFU_DIAMOND = registerKey("tofu_diamond");
	public static final ResourceKey<TrimMaterial> ZUNDA_RUBY = registerKey("zunda_ruby");

	private static ResourceKey<TrimMaterial> registerKey(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, name));
	}

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, TOFU_METAL, Style.EMPTY.withColor(0xAAB9C2), TofuMaterialAssets.TOFU_METAL);
		register(context, TOFU_DIAMOND, Style.EMPTY.withColor(0x6CBEEB), TofuMaterialAssets.TOFU_DIAMOND);
		register(context, ZUNDA_RUBY, Style.EMPTY.withColor(0x39650D), TofuMaterialAssets.ZUNDA);

	}


	private static void register(BootstrapContext<TrimMaterial> p_371763_, ResourceKey<TrimMaterial> p_371867_, Style p_371730_, MaterialAssetGroup p_399962_) {
		Component component = Component.translatable(Util.makeDescriptionId("trim_material", p_371867_.location())).withStyle(p_371730_);
		p_371763_.register(p_371867_, new TrimMaterial(p_399962_, component));
	}


}