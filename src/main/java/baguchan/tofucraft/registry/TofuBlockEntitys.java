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
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuBlockEntitys {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TofuCraftReload.MODID);

	public static final Supplier<BlockEntityType<TofuBedBlockEntity>> TOFUBED = BLOCK_ENTITIES.register("tofubed", () -> register("tofucraft:tofubed", BlockEntityType.Builder.of(TofuBedBlockEntity::new, TofuBlocks.TOFUBED.get())));
	public static final Supplier<BlockEntityType<TofuChestBlockEntity>> TOFUCHEST = BLOCK_ENTITIES.register("tofuchest", () -> register("tofucraft:tofuchest", BlockEntityType.Builder.of(TofuChestBlockEntity::new, TofuBlocks.TOFUCHEST.get())));
	public static final Supplier<BlockEntityType<SaltFurnaceBlockEntity>> SALT_FURNACE = BLOCK_ENTITIES.register("salt_furnace", () -> register("tofucraft:salt_furnace", BlockEntityType.Builder.of(SaltFurnaceBlockEntity::new, TofuBlocks.SALT_FURNACE.get())));
	public static final Supplier<BlockEntityType<FoodPlateBlockEntity>> FOODPLATE = BLOCK_ENTITIES.register("foodplate", () -> register("tofucraft:foodplate", BlockEntityType.Builder.of(FoodPlateBlockEntity::new, TofuBlocks.FOODPLATE.get())));
	public static final Supplier<BlockEntityType<TofunianStatueBlockEntity>> TOFUNIAN_STATUE = BLOCK_ENTITIES.register("tofunian_statue", () -> register("tofucraft:tofunian_statue", BlockEntityType.Builder.of(TofunianStatueBlockEntity::new, TofuBlocks.TOFUNIAN_STATUE.get())));

	public static final Supplier<BlockEntityType<TFStorageBlockEntity>> TF_STORAGE = BLOCK_ENTITIES.register("tf_storage", () -> register("tofucraft:tf_storage", BlockEntityType.Builder.of(TFStorageBlockEntity::new, TofuBlocks.TF_STORAGE.get())));
	public static final Supplier<BlockEntityType<TFCrafterBlockEntity>> TF_CRAFTER = BLOCK_ENTITIES.register("tf_crafter", () -> register("tofucraft:tf_crafter", BlockEntityType.Builder.of(TFCrafterBlockEntity::new, TofuBlocks.TF_CRAFTER.get())));
	public static final Supplier<BlockEntityType<TFOvenBlockEntity>> TF_OVEN = BLOCK_ENTITIES.register("tf_oven", () -> register("tofucraft:tf_oven", BlockEntityType.Builder.of(TFOvenBlockEntity::new, TofuBlocks.TF_OVEN.get())));
	public static final Supplier<BlockEntityType<TFCollectorBlockEntity>> TF_COLLECTOR = BLOCK_ENTITIES.register("tf_collector", () -> register("tofucraft:tf_collector", BlockEntityType.Builder.of(TFCollectorBlockEntity::new, TofuBlocks.TF_COLLECTOR.get())));


	private static <T extends BlockEntity> BlockEntityType<T> register(String p_200966_0_, BlockEntityType.Builder<T> p_200966_1_) {
		Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, p_200966_0_);
		return p_200966_1_.build(type);
	}
}