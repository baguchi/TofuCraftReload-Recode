package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.treedecorators.SproutTopDecorator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuTreeDecoratorType {
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPE = DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE, TofuCraftReload.MODID);
	public static final Supplier<TreeDecoratorType<SproutTopDecorator>> SPROUT_TOP = TREE_DECORATOR_TYPE.register("sprout_top", () -> new TreeDecoratorType<>(SproutTopDecorator.CODEC));

}
