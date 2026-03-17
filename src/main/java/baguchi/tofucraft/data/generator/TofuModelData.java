package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.generator.models.TofuBlockModels;
import baguchi.tofucraft.data.generator.models.TofuItemModels;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelDispatcher;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Holder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TofuModelData extends ModelProvider {
	private final PackOutput.PathProvider blockStatePathProvider;
	private final PackOutput.PathProvider itemInfoPathProvider;
	private final PackOutput.PathProvider modelPathProvider;

	public TofuModelData(PackOutput packOutput) {
		super(packOutput, TofuCraftReload.MODID);
		this.blockStatePathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
		this.itemInfoPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
		this.modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
	}

	public CompletableFuture<?> run(CachedOutput output) {
		ItemInfoCollector itemModelOutput = new ItemInfoCollector(this::getKnownItems) {
			@Override
			public void finalizeAndValidate() { //todo temporary
				try {
					super.finalizeAndValidate();
				} catch (IllegalStateException ignore) {

				}
			}
		};
		BlockModelDefinitionGeneratorCollector blockModelOutput = new BlockModelDefinitionGeneratorCollector(this::getKnownBlocks) {
			@Override
			public void validate() { //todo temporary
				try {
					super.validate();
				} catch (IllegalStateException ignore) {

				}
			}
		};
		SimpleModelCollector modelOutput = new SimpleModelCollector();
		this.registerModels(new TofuBlockModels(blockModelOutput, itemModelOutput, modelOutput), new TofuItemModels(itemModelOutput, modelOutput));
		blockModelOutput.validate();
		itemModelOutput.finalizeAndValidate();
		return CompletableFuture.allOf(blockModelOutput.save(output, this.blockStatePathProvider), modelOutput.save(output, this.modelPathProvider), itemModelOutput.save(output, this.itemInfoPathProvider));
	}


	static class ItemInfoCollector implements ItemModelOutput {
		private final Map<Item, ClientItem> itemInfos;
		private final Map<Item, Item> copies;
		private final Supplier<Stream<? extends Holder<Item>>> knownItems;

		public ItemInfoCollector(Supplier<Stream<? extends Holder<Item>>> knownItems) {
			this.itemInfos = new HashMap();
			this.copies = new HashMap();
			this.knownItems = knownItems;
		}


		@Override
		public void accept(Item p_456234_, ItemModel.Unbaked p_454728_, ClientItem.Properties p_455827_) {
			this.register(p_456234_, new ClientItem(p_454728_, p_455827_));
		}

		public void register(Item p_388205_, ClientItem p_388233_) {
			ClientItem clientitem = (ClientItem) this.itemInfos.put(p_388205_, p_388233_);
			if (clientitem != null) {
				throw new IllegalStateException("Duplicate item model definition for " + String.valueOf(p_388205_));
			}
		}

		public void copy(Item p_386920_, Item p_386789_) {
			this.copies.put(p_386789_, p_386920_);
		}

		public void finalizeAndValidate() {
			(this.knownItems.get()).map(Holder::value).forEach((p_388426_) -> {
				if (!this.copies.containsKey(p_388426_) && p_388426_ instanceof BlockItem blockitem) {
					if (!this.itemInfos.containsKey(blockitem)) {
						Identifier resourcelocation = ModelLocationUtils.getModelLocation(blockitem.getBlock());
						this.accept(blockitem, ItemModelUtils.plainModel(resourcelocation));
					}
				}

			});
			this.copies.forEach((p_386494_, p_386575_) -> {
				ClientItem clientitem = (ClientItem) this.itemInfos.get(p_386575_);
				if (clientitem == null) {
					String var10002 = String.valueOf(p_386575_);
					throw new IllegalStateException("Missing donor: " + var10002 + " -> " + String.valueOf(p_386494_));
				} else {
					this.register(p_386494_, clientitem);
				}
			});
			List<Identifier> list = (this.knownItems.get()).filter((p_388636_) -> !this.itemInfos.containsKey(p_388636_.value())).map((p_388278_) -> ((ResourceKey) p_388278_.unwrapKey().orElseThrow()).identifier()).toList();
			if (!list.isEmpty()) {
				throw new IllegalStateException("Missing item model definitions for: " + String.valueOf(list));
			}
		}

		public CompletableFuture<?> save(CachedOutput p_387552_, PackOutput.PathProvider p_388501_) {
			return DataProvider.saveAll(p_387552_, ClientItem.CODEC, (p_388594_) -> p_388501_.json(p_388594_.builtInRegistryHolder().key().identifier()), this.itemInfos);
		}
	}


	static class SimpleModelCollector implements BiConsumer<Identifier, ModelInstance> {
		private final Map<Identifier, ModelInstance> models = new HashMap();

		SimpleModelCollector() {
		}

		public void accept(Identifier p_388633_, ModelInstance p_388119_) {
			Supplier<JsonElement> supplier = (Supplier) this.models.put(p_388633_, p_388119_);
			if (supplier != null) {
				throw new IllegalStateException("Duplicate model definition for " + String.valueOf(p_388633_));
			}
		}

		public CompletableFuture<?> save(CachedOutput p_386795_, PackOutput.PathProvider p_388673_) {
			Objects.requireNonNull(p_388673_);
			return saveAll(p_386795_, p_388673_::json, this.models);
		}

		static <T> CompletableFuture<?> saveAll(CachedOutput p_387084_, Function<T, Path> p_386455_, Map<T, ? extends Supplier<JsonElement>> p_386585_) {
			return DataProvider.saveAll(p_387084_, Supplier::get, p_386455_, p_386585_);
		}
	}


	static class BlockModelDefinitionGeneratorCollector implements Consumer<BlockModelDefinitionGenerator> {
		private final Map<Block, BlockModelDefinitionGenerator> generators;
		private final Supplier<Stream<? extends Holder<Block>>> knownBlocks;

		public BlockModelDefinitionGeneratorCollector(Supplier<Stream<? extends Holder<Block>>> knownBlocks) {
			this.generators = new HashMap();
			this.knownBlocks = knownBlocks;
		}


		public void accept(BlockModelDefinitionGenerator p_405192_) {
			Block block = p_405192_.block();
			BlockModelDefinitionGenerator blockmodeldefinitiongenerator = this.generators.put(block, p_405192_);
			if (blockmodeldefinitiongenerator != null) {
				throw new IllegalStateException("Duplicate blockstate definition for " + block);
			}
		}

		public void validate() {
			Stream<? extends Holder<Block>> stream = knownBlocks.get();
			List<Identifier> list = stream.filter(p_386843_ -> !this.generators.containsKey(p_386843_.value()))
					.map(p_386823_ -> p_386823_.unwrapKey().orElseThrow().identifier())
					.toList();
			if (!list.isEmpty()) {
				throw new IllegalStateException("Missing blockstate definitions for: " + list);
			}
		}

		public CompletableFuture<?> save(CachedOutput p_388014_, PackOutput.PathProvider p_388192_) {
			Map<Block, BlockStateModelDispatcher> map = Maps.transformValues(this.generators, BlockModelDefinitionGenerator::create);
			Function<Block, Path> function = p_387598_ -> p_388192_.json(p_387598_.builtInRegistryHolder().key().identifier());
			return DataProvider.saveAll(p_388014_, BlockStateModelDispatcher.CODEC, function, map);
		}
	}
}