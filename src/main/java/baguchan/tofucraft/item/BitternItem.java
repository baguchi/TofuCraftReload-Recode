package baguchan.tofucraft.item;

import baguchan.tofucraft.api.BitternRecipes;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BitternItem extends Item {
	public BitternItem(Properties group) {
		super(group);
	}

	public ActionResultType func_195939_a(ItemUseContext p_195939_1_) {
		return ActionResultType.PASS;
	}

	public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.func_184586_b(handIn);
		BlockRayTraceResult blockraytraceresult = func_219968_a(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
		BlockRayTraceResult blockraytraceresult1 = blockraytraceresult.func_237485_a_(blockraytraceresult.func_216350_a());
		if (blockraytraceresult.func_216346_c() == RayTraceResult.Type.MISS)
			return new ActionResult(ActionResultType.PASS, itemstack);
		if (blockraytraceresult.func_216346_c() == RayTraceResult.Type.BLOCK) {
			FluidState fluidState = worldIn.func_204610_c(blockraytraceresult1.func_216350_a());
			Map.Entry<Fluid, Block> result = BitternRecipes.getResult(fluidState.func_206886_c().getFluid());
			if (result != null) {
				worldIn.func_180501_a(blockraytraceresult1.func_216350_a(), ((Block) result.getValue()).func_176223_P(), 11);
				worldIn.func_175669_a(2001, blockraytraceresult1.func_216350_a(), Block.func_196246_j(worldIn.getBlockState(blockraytraceresult1.func_216350_a())));
				if (!playerIn.field_71075_bZ.field_75098_d)
					itemstack.func_190918_g(1);
				ItemStack itemstack2 = new ItemStack((IItemProvider) Items.field_151069_bo);
				if (itemstack.func_190926_b()) {
					playerIn.func_184611_a(handIn, itemstack2);
				} else if (!playerIn.field_71075_bZ.field_75098_d &&
						!playerIn.field_71071_by.func_70441_a(itemstack2)) {
					playerIn.func_71019_a(itemstack2, false);
				}
				return new ActionResult(ActionResultType.SUCCESS, playerIn.func_184586_b(handIn));
			}
		}
		return new ActionResult(ActionResultType.FAIL, itemstack);
	}
}
