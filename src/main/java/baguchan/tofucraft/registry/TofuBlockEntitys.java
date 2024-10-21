package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.FoodPlateBlockEntity;
import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchan.tofucraft.blockentity.TofuBedBlockEntity;
import baguchan.tofucraft.blockentity.TofuChestBlockEntity;
import baguchan.tofucraft.blockentity.TofunianStatueBlockEntity;
import baguchan.tofucraft.blockentity.tfenergy.TFCollectorBlockEntity;
import baguchan.tofucraft.blockentity.tfenergy.TFCrafterBlockEntity;
import baguchan.tofucraft.blockentity.tfenergy.TFOvenBlockEntity;
import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class TofuBlockEntitys {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TofuCraftReload.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TofuBedBlockEntity>> TOFUBED = register("tofubed", TofuBedBlockEntity::new, TofuBlocks.TOFUBED.get());
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TofuChestBlockEntity>> TOFUCHEST = register("tofuchest", TofuChestBlockEntity::new, TofuBlocks.TOFUCHEST.get());
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends SaltFurnaceBlockEntity>> SALT_FURNACE = register("salt_furnace", SaltFurnaceBlockEntity::new, TofuBlocks.SALT_FURNACE.get());
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends FoodPlateBlockEntity>> FOODPLATE = register("foodplate", FoodPlateBlockEntity::new, TofuBlocks.FOODPLATE.get());
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TofunianStatueBlockEntity>> TOFUNIAN_STATUE = register("tofunian_statue", TofunianStatueBlockEntity::new, TofuBlocks.TOFUNIAN_STATUE.get());

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFStorageBlockEntity>> TF_STORAGE = register("tf_storage", TFStorageBlockEntity::new, TofuBlocks.TF_STORAGE.get());
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFCrafterBlockEntity>> TF_CRAFTER = register("tf_crafter", TFCrafterBlockEntity::new, TofuBlocks.TF_CRAFTER.get());
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFOvenBlockEntity>> TF_OVEN = register("tf_oven", TFOvenBlockEntity::new, TofuBlocks.TF_OVEN.get());
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFCollectorBlockEntity>> TF_COLLECTOR = register("tf_collector", TFCollectorBlockEntity::new, TofuBlocks.TF_COLLECTOR.get());


	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends T>> register(
			String p_58957_, BlockEntityType.BlockEntitySupplier<? extends T> p_362578_, Block... p_364748_
	) {

		Util.fetchChoiceType(References.BLOCK_ENTITY, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_58957_).toString());
		return BLOCK_ENTITIES.register(p_58957_, () -> new BlockEntityType<>(p_362578_, Set.of(p_364748_)));
	}
}