package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;

import java.util.Random;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TofuCowEntity extends CowEntity {
	public TofuCowEntity(EntityType<? extends CowEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().func_233815_a_(Attributes.field_233818_a_, 10.0D).func_233815_a_(Attributes.field_233821_d_, 0.20000000298023224D);
	}

	public static boolean canTofuCowSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		return (worldIn.getBlockState(pos.func_177977_b()).func_203425_a(TofuBlocks.TOFU_TERRAIN) && worldIn.func_226659_b_(pos, 0) > 8);
	}

	public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.func_184586_b(p_230254_2_);
		if (itemstack.func_77973_b() == Items.field_151133_ar && !func_70631_g_()) {
			p_230254_1_.playSound(SoundEvents.field_187564_an, 1.0F, 1.0F);
			ItemStack itemstack1 = DrinkHelper.func_242398_a(itemstack, p_230254_1_, TofuItems.BUCKET_SOYMILK.func_190903_i());
			p_230254_1_.func_184611_a(p_230254_2_, itemstack1);
			return ActionResultType.func_233537_a_(this.level.field_72995_K);
		}
		return super.func_230254_b_(p_230254_1_, p_230254_2_);
	}

	public TofuCowEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return (TofuCowEntity) TofuEntityTypes.TOFUCOW.func_200721_a((World) p_241840_1_);
	}
}
