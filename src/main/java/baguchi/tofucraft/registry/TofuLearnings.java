package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.TofuLearning;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuLearnings {
	public static final DeferredRegister<TofuLearning> LEARNING = DeferredRegister.create(TofuLearning.REGISTRY_KEY,
			TofuCraftReload.MODID);
	public static final Supplier<Registry<TofuLearning>> LEARNING_REGISTRY = LEARNING.getRegistry();

}
