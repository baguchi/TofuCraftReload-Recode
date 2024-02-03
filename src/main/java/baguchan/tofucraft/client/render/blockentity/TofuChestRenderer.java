package baguchan.tofucraft.client.render.blockentity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.EnumMap;
import java.util.Map;

public class TofuChestRenderer<T extends BlockEntity & LidBlockEntity> extends ChestRenderer<T> {
	public static final Map<Block, EnumMap<ChestType, Material>> MATERIALS;

	static {
		ImmutableMap.Builder<Block, EnumMap<ChestType, Material>> builder = ImmutableMap.builder();

		builder.put(TofuBlocks.TOFUCHEST.get(), chestMaterial("tofuchest"));

		MATERIALS = builder.build();
	}
	public TofuChestRenderer(BlockEntityRendererProvider.Context p_173607_) {
		super(p_173607_);
	}

	@Override
	protected Material getMaterial(T blockEntity, ChestType chestType) {
		EnumMap<ChestType, Material> b = MATERIALS.get(blockEntity.getBlockState().getBlock());

		if (b == null) return super.getMaterial(blockEntity, chestType);

		Material material = b.get(chestType);

		return material != null ? material : super.getMaterial(blockEntity, chestType);
	}

	private static EnumMap<ChestType, Material> chestMaterial(String type) {
		EnumMap<ChestType, Material> map = new EnumMap<>(ChestType.class);

		map.put(ChestType.SINGLE, new Material(Sheets.CHEST_SHEET, TofuCraftReload.prefix("entity/chest/" + type)));
		map.put(ChestType.LEFT, new Material(Sheets.CHEST_SHEET, TofuCraftReload.prefix("entity/chest/" + type + "_left")));
		map.put(ChestType.RIGHT, new Material(Sheets.CHEST_SHEET, TofuCraftReload.prefix("entity/chest/" + type + "_right")));

		return map;
	}
}
