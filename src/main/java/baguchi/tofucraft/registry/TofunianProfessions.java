package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.TofunianProfession;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.Optional;
import java.util.Set;

import static net.minecraft.resources.ResourceKey.createRegistryKey;


@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofunianProfessions {
	public static final ResourceKey<Registry<TofunianProfession>> TOFUNIAN_PROFESSION_REGISTRY_KEY = createRegistryKey(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "tofunian_profession"));

	public static final DeferredRegister<TofunianProfession> TOFUNIAN_PROFESSION = DeferredRegister.create(TOFUNIAN_PROFESSION_REGISTRY_KEY, TofuCraftReload.MODID);

	public static final DeferredHolder<TofunianProfession, TofunianProfession> NONE = TOFUNIAN_PROFESSION.register("none", () -> new TofunianProfession(Optional.empty()));
	public static final DeferredHolder<TofunianProfession, TofunianProfession> SOY_WORKER = register("soy_worker", TofuTags.Blocks.TOFUNIAN_SOY_WORKER);
	public static final DeferredHolder<TofunianProfession, TofunianProfession> FARMER = register("farmer", TofuTags.Blocks.TOFUNIAN_FARMER);
	public static final DeferredHolder<TofunianProfession, TofunianProfession> SMITH = register("smith", TofuTags.Blocks.TOFUNIAN_SMITH);

	public static final Registry<TofunianProfession> TOFUNIAN_PROFESSION_REGISTRY = new RegistryBuilder<>(TOFUNIAN_PROFESSION_REGISTRY_KEY)
			// If you want to enable integer id syncing, for networking.
			// These should only be used in networking contexts, for example in packets or purely networking-related NBT data.
			.sync(true)
			// The default key. Similar to minecraft:air for blocks. This is optional.
			.defaultKey(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "none"))
			// Effectively limits the max count. Generally discouraged, but may make sense in settings such as networking.
			// Build the registry.
			.create();

	@SubscribeEvent
	public static void onNewRegistry(NewRegistryEvent event) {
		event.register(TOFUNIAN_PROFESSION_REGISTRY);
	}

	public static Registry<TofunianProfession> getRegistry() {
		return TOFUNIAN_PROFESSION_REGISTRY;
	}

	private static DeferredHolder<TofunianProfession, TofunianProfession> register(String name, TagKey<Block> jobSite) {
		return TOFUNIAN_PROFESSION.register(name, () -> new TofunianProfession(Optional.of(jobSite)));
	}


	private static Set<BlockState> getBlockStates(Block p_218074_) {
		return ImmutableSet.copyOf(p_218074_.getStateDefinition().getPossibleStates());
	}
}