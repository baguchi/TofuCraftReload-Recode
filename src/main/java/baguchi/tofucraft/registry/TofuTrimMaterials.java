package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class TofuTrimMaterials {

	public static final ResourceKey<TrimMaterial> TOFU_METAL = registerKey("tofu_metal");
	public static final ResourceKey<TrimMaterial> TOFU_DIAMOND = registerKey("tofu_diamond");
	public static final ResourceKey<TrimMaterial> ZUNDA_RUBY = registerKey("zunda_ruby");

	private static ResourceKey<TrimMaterial> registerKey(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
	}

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, TOFU_METAL, Style.EMPTY.withColor(0xAAB9C2), TofuCraftReload.prefix("tofu_metal"));
		register(context, TOFU_DIAMOND, Style.EMPTY.withColor(0x6CBEEB), TofuCraftReload.prefix("tofu_diamond"));
		register(context, ZUNDA_RUBY, Style.EMPTY.withColor(0x39650D), TofuCraftReload.prefix("zunda_ruby"));

	}


	private static void register(BootstrapContext<TrimMaterial> p_371763_, ResourceKey<TrimMaterial> p_371867_, Style p_371730_, Identifier p_399962_) {
		Component component = Component.translatable(Util.makeDescriptionId("trim_material", p_371867_.identifier())).withStyle(p_371730_);
		p_371763_.register(p_371867_, new TrimMaterial(p_399962_, component));
	}


}