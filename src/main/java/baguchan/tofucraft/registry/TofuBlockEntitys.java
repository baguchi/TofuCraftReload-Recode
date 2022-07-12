package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchan.tofucraft.blockentity.TofuBedBlockEntity;
import baguchan.tofucraft.blockentity.TofuChestBlockEntity;
import baguchan.tofucraft.blockentity.tfenergy.TFAggregatorBlockEntity;
import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuBlockEntitys {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TofuCraftReload.MODID);

	public static final RegistryObject<BlockEntityType<TofuBedBlockEntity>> TOFUBED = BLOCK_ENTITIES.register("tofubed", () -> register("tofucraft:tofubed", BlockEntityType.Builder.of(TofuBedBlockEntity::new, TofuBlocks.TOFUBED.get())));
	public static final RegistryObject<BlockEntityType<TofuChestBlockEntity>> TOFUCHEST = BLOCK_ENTITIES.register("tofuchest", () -> register("tofucraft:tofuchest", BlockEntityType.Builder.of(TofuChestBlockEntity::new, TofuBlocks.TOFUCHEST.get())));
	public static final RegistryObject<BlockEntityType<SaltFurnaceBlockEntity>> SALT_FURNACE = BLOCK_ENTITIES.register("salt_furnace", () -> register("tofucraft:salt_furnace", BlockEntityType.Builder.of(SaltFurnaceBlockEntity::new, TofuBlocks.SALT_FURNACE.get())));
	public static final RegistryObject<BlockEntityType<TFStorageBlockEntity>> TF_STORAGE = BLOCK_ENTITIES.register("tf_storage", () -> register("tofucraft:tf_storage", BlockEntityType.Builder.of(TFStorageBlockEntity::new, TofuBlocks.TF_STORAGE.get())));
	public static final RegistryObject<BlockEntityType<TFAggregatorBlockEntity>> TF_AGGREGATOR = BLOCK_ENTITIES.register("tf_aggregator", () -> register("tofucraft:tf_storage", BlockEntityType.Builder.of(TFAggregatorBlockEntity::new, TofuBlocks.TF_AGGREGATOR.get())));


	private static <T extends BlockEntity> BlockEntityType<T> register(String p_200966_0_, BlockEntityType.Builder<T> p_200966_1_) {
		Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, p_200966_0_);
		return p_200966_1_.build(type);
	}
}