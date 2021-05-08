package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.tileentity.SaltFurnaceTileEntity;
import baguchan.tofucraft.tileentity.TofuBedTileEntity;
import baguchan.tofucraft.tileentity.TofuChestTileEntity;
import com.mojang.datafixers.types.Type;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuTileEntitys {
	public static final TileEntityType<SaltFurnaceTileEntity> SALT_FURNACE = register("tofucraft:salt_furnace", TileEntityType.Builder.of(SaltFurnaceTileEntity::new, TofuBlocks.SALT_FURNACE));

	public static final TileEntityType<TofuBedTileEntity> TOFUBED = register("tofucraft:tofubed", TileEntityType.Builder.of(TofuBedTileEntity::new, TofuBlocks.TOFUBED));

	public static final TileEntityType<TofuChestTileEntity> TOFUCHEST = register("tofucraft:tofuchest", TileEntityType.Builder.of(TofuChestTileEntity::new, TofuBlocks.TOFUCHEST));

	private static <T extends net.minecraft.tileentity.TileEntity> TileEntityType<T> register(String p_200966_0_, TileEntityType.Builder<T> p_200966_1_) {
		Type<?> type = Util.fetchChoiceType(TypeReferences.BLOCK_ENTITY, p_200966_0_);
		return p_200966_1_.build(type);
	}

	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<TileEntityType<?>> registry) {
		registry.getRegistry().register(SALT_FURNACE.setRegistryName("salt_furnace"));
		registry.getRegistry().register(TOFUBED.setRegistryName("tofubed"));
		registry.getRegistry().register(TOFUCHEST.setRegistryName("tofuchest"));
	}
}
