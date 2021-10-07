package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchan.tofucraft.blockentity.TofuBedBlockEntity;
import baguchan.tofucraft.blockentity.TofuChestBlockEntity;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuBlockEntitys {
	public static final BlockEntityType<TofuBedBlockEntity> TOFUBED = register("tofucraft:tofubed", BlockEntityType.Builder.of(TofuBedBlockEntity::new, TofuBlocks.TOFUBED));
	public static final BlockEntityType<TofuChestBlockEntity> TOFUCHEST = register("tofucraft:tofuchest", BlockEntityType.Builder.of(TofuChestBlockEntity::new, TofuBlocks.TOFUCHEST));
	public static final BlockEntityType<SaltFurnaceBlockEntity> SALT_FURNACE = register("tofucraft:salt_furnace", BlockEntityType.Builder.of(SaltFurnaceBlockEntity::new, TofuBlocks.SALT_FURNACE));


	private static <T extends BlockEntity> BlockEntityType<T> register(String p_200966_0_, BlockEntityType.Builder<T> p_200966_1_) {
		Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, p_200966_0_);
		return p_200966_1_.build(type);
	}

	@SubscribeEvent
	public static void registerBlockEntityType(RegistryEvent.Register<BlockEntityType<?>> registry) {
		registry.getRegistry().register(TOFUBED.setRegistryName("tofubed"));
		registry.getRegistry().register(TOFUCHEST.setRegistryName("tofuchest"));

		registry.getRegistry().register(SALT_FURNACE.setRegistryName("salf_furnace"));
	}
}