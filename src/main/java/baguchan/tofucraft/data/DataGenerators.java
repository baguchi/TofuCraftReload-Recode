package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		//TODO remove this method once https://github.com/neoforged/NeoForge/pull/146 is merged
		addArmorTrims(existingFileHelper);
		event.getGenerator().addProvider(event.includeClient(), new BlockstateGenerator(packOutput, event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeClient(), new ItemModelGenerator(packOutput, event.getExistingFileHelper()));
		generator.addProvider(event.includeServer(), new CustomTagGenerator.BannerPatternTagGenerator(packOutput, lookupProvider, existingFileHelper));
		BlockTagsProvider blocktags = new BlockTagGenerator(packOutput, lookupProvider, event.getExistingFileHelper());
		event.getGenerator().addProvider(event.includeServer(), blocktags);
		event.getGenerator().addProvider(event.includeServer(), new ItemTagGenerator(packOutput, lookupProvider, blocktags.contentsGetter(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new EntityTagGenerator(packOutput, lookupProvider, event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new FluidTagGenerator(packOutput, lookupProvider, event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), TofuLootTableProvider.create(packOutput));
		event.getGenerator().addProvider(event.includeServer(), new CraftingGenerator(packOutput));
		event.getGenerator().addProvider(event.includeServer(), new RegistryDataGenerator(packOutput, lookupProvider));
	}

	private static void addArmorTrims(ExistingFileHelper existingFileHelper) {
		for (ItemModelGenerators.TrimModelData trim : ItemModelGenerators.GENERATED_TRIM_MODELS) {
			existingFileHelper.trackGenerated(new ResourceLocation("boots_trim_" + trim.name()), PackType.CLIENT_RESOURCES, ".png", "textures/trims/items");
			existingFileHelper.trackGenerated(new ResourceLocation("chestplate_trim_" + trim.name()), PackType.CLIENT_RESOURCES, ".png", "textures/trims/items");
			existingFileHelper.trackGenerated(new ResourceLocation("helmet_trim_" + trim.name()), PackType.CLIENT_RESOURCES, ".png", "textures/trims/items");
			existingFileHelper.trackGenerated(new ResourceLocation("leggings_trim_" + trim.name()), PackType.CLIENT_RESOURCES, ".png", "textures/trims/items");
		}
	}
}