package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.FoodPlateBlockEntity;
import baguchi.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchi.tofucraft.blockentity.TofuBedBlockEntity;
import baguchi.tofucraft.blockentity.TofuChestBlockEntity;
import baguchi.tofucraft.blockentity.TofunianStatueBlockEntity;
import baguchi.tofucraft.blockentity.tfenergy.TFCollectorBlockEntity;
import baguchi.tofucraft.blockentity.tfenergy.TFCrafterBlockEntity;
import baguchi.tofucraft.blockentity.tfenergy.TFOvenBlockEntity;
import baguchi.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class TofuBlockEntitys {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TofuCraftReload.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TofuBedBlockEntity>> TOFUBED = register("tofubed", TofuBedBlockEntity::new, TofuBlocks.TOFUBED);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TofuChestBlockEntity>> TOFUCHEST = register("tofuchest", TofuChestBlockEntity::new, TofuBlocks.TOFUCHEST);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends SaltFurnaceBlockEntity>> SALT_FURNACE = register("salt_furnace", SaltFurnaceBlockEntity::new, TofuBlocks.SALT_FURNACE);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends FoodPlateBlockEntity>> FOODPLATE = register("foodplate", FoodPlateBlockEntity::new, TofuBlocks.FOODPLATE);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TofunianStatueBlockEntity>> TOFUNIAN_STATUE = register("tofunian_statue", TofunianStatueBlockEntity::new, TofuBlocks.TOFUNIAN_STATUE);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFStorageBlockEntity>> TF_STORAGE = register("tf_storage", TFStorageBlockEntity::new, TofuBlocks.TF_STORAGE);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFCrafterBlockEntity>> TF_CRAFTER = register("tf_crafter", TFCrafterBlockEntity::new, TofuBlocks.TF_CRAFTER);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFOvenBlockEntity>> TF_OVEN = register("tf_oven", TFOvenBlockEntity::new, TofuBlocks.TF_OVEN);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends TFCollectorBlockEntity>> TF_COLLECTOR = register("tf_collector", TFCollectorBlockEntity::new, TofuBlocks.TF_COLLECTOR);


	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<? extends T>> register(
			String p_58957_, BlockEntityType.BlockEntitySupplier<? extends T> p_362578_, Holder<Block>... p_364748_
	) {

		Util.fetchChoiceType(References.BLOCK_ENTITY, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_58957_).toString());
		return BLOCK_ENTITIES.register(p_58957_, () -> new BlockEntityType<>(p_362578_, Set.copyOf(Arrays.stream(Objects.requireNonNull(p_364748_)).map(Holder::value).toList())));
	}
}