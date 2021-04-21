package baguchan.tofucraft.registry;

import baguchan.tofucraft.tileentity.SaltFurnaceTileEntity;
import baguchan.tofucraft.tileentity.TofuBedTileEntity;
import baguchan.tofucraft.tileentity.TofuChestTileEntity;
import com.mojang.datafixers.types.Type;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
public class TofuTileEntitys {
	public static final TileEntityType<SaltFurnaceTileEntity> SALT_FURNACE = register("tofucraft:salt_furnace", TileEntityType.Builder.func_223042_a(SaltFurnaceTileEntity::new, new Block[]{TofuBlocks.SALT_FURNACE}));

	public static final TileEntityType<TofuBedTileEntity> TOFUBED = register("tofucraft:tofubed", TileEntityType.Builder.func_223042_a(TofuBedTileEntity::new, new Block[]{TofuBlocks.TOFUBED}));

	public static final TileEntityType<TofuChestTileEntity> TOFUCHEST = register("tofucraft:tofuchest", TileEntityType.Builder.func_223042_a(TofuChestTileEntity::new, new Block[]{TofuBlocks.TOFUCHEST}));

	private static <T extends net.minecraft.tileentity.TileEntity> TileEntityType<T> register(String p_200966_0_, TileEntityType.Builder<T> p_200966_1_) {
		Type<?> type = Util.func_240976_a_(TypeReferences.field_211294_j, p_200966_0_);
		return p_200966_1_.func_206865_a(type);
	}

	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<TileEntityType<?>> registry) {
		registry.getRegistry().register(SALT_FURNACE.setRegistryName("salt_furnace"));
		registry.getRegistry().register(TOFUBED.setRegistryName("tofubed"));
		registry.getRegistry().register(TOFUCHEST.setRegistryName("tofuchest"));
	}
}
