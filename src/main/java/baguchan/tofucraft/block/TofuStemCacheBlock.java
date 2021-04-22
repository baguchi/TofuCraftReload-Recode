package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class TofuStemCacheBlock extends HorizontalBlock {
	public static final BooleanProperty ZUNDAMA = BooleanProperty.func_177716_a("zundama");

	public TofuStemCacheBlock(Properties builder) {
		super(builder);
		func_180632_j((BlockState) ((BlockState) this.field_176227_L.func_177621_b()).setValue(ZUNDAMA, Boolean.valueOf(true)));
	}

	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		boolean hasZunda = ((Boolean) state.func_177229_b((Property) ZUNDAMA)).booleanValue();
		if (hasZunda) {
			ItemStack salt = new ItemStack(TofuItems.ZUNDAMA, 1);
			if (worldIn instanceof World) {
				float f = 0.7F;
				double d0 = (worldIn.func_201674_k().nextFloat() * f) + (1.0F - f) * 0.5D;
				double d1 = (worldIn.func_201674_k().nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
				double d2 = (worldIn.func_201674_k().nextFloat() * f) + (1.0F - f) * 0.5D;
				ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, salt);
				itemEntity.func_174867_a(10);
				worldIn.addFreshEntity(itemEntity);
			}
			worldIn.setBlock(pos, state.setValue(ZUNDAMA, Boolean.valueOf(false)), 3);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
		builder.func_206894_a(new Property[]{(Property) HorizontalBlock.field_185512_D, ZUNDAMA});
	}
}
