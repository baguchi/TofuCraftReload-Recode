package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.TofuChestBlockEntity;
import baguchi.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class TofuChestRenderer<T extends BlockEntity & LidBlockEntity> extends ChestRenderer<T> {
	public static final Map<Block, EnumMap<ChestType, SpriteId>> MATERIALS;

	static {
		ImmutableMap.Builder<Block, EnumMap<ChestType, SpriteId>> builder = ImmutableMap.builder();

		builder.put(TofuBlocks.TOFUCHEST.get(), chestSpriteId("tofuchest"));

		MATERIALS = builder.build();
	}
	public TofuChestRenderer(BlockEntityRendererProvider.Context p_173607_) {
		super(p_173607_);
	}

	@Override
	protected @Nullable SpriteId getCustomSprite(T blockEntity, ChestRenderState renderState) {
		EnumMap<ChestType, SpriteId> b = MATERIALS.get(TofuBlocks.TOFUCHEST.get());
		if (blockEntity instanceof TofuChestBlockEntity) {
			return chestSpriteId("tofuchest").get(renderState.type);
		}
		return null;
	}

	private static EnumMap<ChestType, SpriteId> chestSpriteId(String type) {
		EnumMap<ChestType, SpriteId> map = new EnumMap<>(ChestType.class);

		map.put(ChestType.SINGLE, new SpriteId(Sheets.CHEST_SHEET, TofuCraftReload.prefix("entity/chest/" + type)));
		map.put(ChestType.LEFT, new SpriteId(Sheets.CHEST_SHEET, TofuCraftReload.prefix("entity/chest/" + type + "_left")));
		map.put(ChestType.RIGHT, new SpriteId(Sheets.CHEST_SHEET, TofuCraftReload.prefix("entity/chest/" + type + "_right")));

		return map;
	}

	private static EnumMap<ChestType, SpriteId> singleSpriteId(String type) {
		EnumMap<ChestType, SpriteId> map = new EnumMap<>(ChestType.class);

		map.put(ChestType.SINGLE, new SpriteId(Sheets.CHEST_SHEET, TofuCraftReload.prefix("entity/chest/" + type)));

		return map;
	}
}
