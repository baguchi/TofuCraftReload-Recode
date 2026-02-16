package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.entity.TofunianProfession;
import baguchi.tofucraft.data.resources.TofunianTradeSets;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
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

	public static final DeferredHolder<TofunianProfession, TofunianProfession> NONE = TOFUNIAN_PROFESSION.register("none", () -> new TofunianProfession(Optional.empty(), new Int2ObjectOpenHashMap<>()));
	public static final DeferredHolder<TofunianProfession, TofunianProfession> SOY_WORKER = register("soy_worker", TofuTags.Blocks.TOFUNIAN_SOY_WORKER, new Int2ObjectOpenHashMap<>(Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, TofunianTradeSets.SOY_WORKER_LEVEL_1), Int2ObjectMap.entry(2, TofunianTradeSets.SOY_WORKER_LEVEL_2), Int2ObjectMap.entry(3, TofunianTradeSets.SOY_WORKER_LEVEL_3), Int2ObjectMap.entry(4, TofunianTradeSets.SOY_WORKER_LEVEL_4), Int2ObjectMap.entry(5, TofunianTradeSets.SOY_WORKER_LEVEL_5))));
	public static final DeferredHolder<TofunianProfession, TofunianProfession> FARMER = register("farmer", TofuTags.Blocks.TOFUNIAN_FARMER, new Int2ObjectOpenHashMap<>(Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, TofunianTradeSets.FARMER_LEVEL_1), Int2ObjectMap.entry(2, TofunianTradeSets.FARMER_LEVEL_2), Int2ObjectMap.entry(3, TofunianTradeSets.FARMER_LEVEL_3), Int2ObjectMap.entry(4, TofunianTradeSets.FARMER_LEVEL_4), Int2ObjectMap.entry(5, TofunianTradeSets.FARMER_LEVEL_5))));
	public static final DeferredHolder<TofunianProfession, TofunianProfession> SMITH = register("smith", TofuTags.Blocks.TOFUNIAN_SMITH, new Int2ObjectOpenHashMap<>(Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, TofunianTradeSets.SMITH_LEVEL_1), Int2ObjectMap.entry(2, TofunianTradeSets.SMITH_LEVEL_2), Int2ObjectMap.entry(3, TofunianTradeSets.SMITH_LEVEL_3), Int2ObjectMap.entry(4, TofunianTradeSets.SMITH_LEVEL_4), Int2ObjectMap.entry(5, TofunianTradeSets.SMITH_LEVEL_5))));
	public static final DeferredHolder<TofunianProfession, TofunianProfession> ENGINEER = register("engineer", TofuTags.Blocks.TOFUNIAN_ENGINEER, new Int2ObjectOpenHashMap<>(Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, TofunianTradeSets.ENGINEER_LEVEL_1), Int2ObjectMap.entry(2, TofunianTradeSets.ENGINEER_LEVEL_2), Int2ObjectMap.entry(3, TofunianTradeSets.ENGINEER_LEVEL_3), Int2ObjectMap.entry(4, TofunianTradeSets.ENGINEER_LEVEL_4), Int2ObjectMap.entry(5, TofunianTradeSets.ENGINEER_LEVEL_5))));

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

	private static DeferredHolder<TofunianProfession, TofunianProfession> register(String name, TagKey<Block> jobSite, Int2ObjectOpenHashMap<ResourceKey<TradeSet>> trades) {
		return TOFUNIAN_PROFESSION.register(name, () -> new TofunianProfession(Optional.of(jobSite), trades));
	}


	private static Set<BlockState> getBlockStates(Block p_218074_) {
		return ImmutableSet.copyOf(p_218074_.getStateDefinition().getPossibleStates());
	}
}