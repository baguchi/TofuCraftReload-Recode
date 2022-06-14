package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.biome.modifier.SoybeanBiomeModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuBiomeModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, TofuCraftReload.MODID);

	public static final RegistryObject<Codec<SoybeanBiomeModifier>> SOYBEAN_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("soybean_modifier", () -> Codec.unit(SoybeanBiomeModifier.INSTANCE));

}