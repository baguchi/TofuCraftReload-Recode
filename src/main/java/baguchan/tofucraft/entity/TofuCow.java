package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Random;

public class TofuCow extends Cow {
	public TofuCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224D);
	}

	public static boolean checkTofuAnimalSpawnRules(EntityType<? extends Animal> p_27578_, LevelAccessor p_27579_, MobSpawnType p_27580_, BlockPos p_27581_, Random p_27582_) {
		return p_27579_.getBlockState(p_27581_.below()).is(TofuBlocks.TOFU_TERRAIN) && p_27579_.getRawBrightness(p_27581_, 0) > 8;
	}

	public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
		ItemStack var3 = p_28298_.getItemInHand(p_28299_);
		if (var3.is(Items.BUCKET) && !this.isBaby()) {
			p_28298_.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
			ItemStack var4 = ItemUtils.createFilledResult(var3, p_28298_, TofuItems.BUCKET_SOYMILK.getDefaultInstance());
			p_28298_.setItemInHand(p_28299_, var4);
			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else {
			return super.mobInteract(p_28298_, p_28299_);
		}
	}

	public boolean isFood(ItemStack p_27600_) {
		return p_27600_.is(TofuItems.LEEK);
	}
}
