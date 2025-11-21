package baguchi.tofucraft.data.resources.registries;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.entity.TofunianVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;

public class TofunianVariants {
	public static final ResourceKey<Registry<TofunianVariant>> TOFUNIAN_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "tofunian_variant"));

	public static final ResourceKey<TofunianVariant> PLAIN = createKey("plain");
	public static final ResourceKey<TofunianVariant> ZUNDA = createKey("zunda");
	public static final ResourceKey<TofunianVariant> STRAWBERRY = createKey("strawberry");
	public static final ResourceKey<TofunianVariant> EGG = createKey("egg");
	public static final ResourceKey<TofunianVariant> MISO = createKey("miso");
	public static final ResourceKey<TofunianVariant> DEFAULT = PLAIN;

	private static ResourceKey<TofunianVariant> createKey(String name) {
		return ResourceKey.create(TOFUNIAN_VARIANT_REGISTRY_KEY, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
	}

	static void register(BootstrapContext<TofunianVariant> context, ResourceKey<TofunianVariant> key, String name) {
		Identifier resourcelocation = TofuCraftReload.prefix("entity/tofunian/variant/" + name);
		context.register(key, new TofunianVariant(resourcelocation));
	}

	public static Holder<TofunianVariant> getRandomVariant(RegistryAccess p_332694_, RandomSource randomSource) {
		Registry<TofunianVariant> registry = p_332694_.lookupOrThrow(TOFUNIAN_VARIANT_REGISTRY_KEY);
		return registry.getRandom(randomSource).orElse(registry.getOrThrow(DEFAULT));
	}

	public static void bootstrap(BootstrapContext<TofunianVariant> context) {
		register(context, PLAIN, "tofunian_plain");
		register(context, ZUNDA, "tofunian_zunda");
		register(context, STRAWBERRY, "tofunian_strawberry");
		register(context, EGG, "tofunian_egg");
		register(context, MISO, "tofunian_miso");
	}
}