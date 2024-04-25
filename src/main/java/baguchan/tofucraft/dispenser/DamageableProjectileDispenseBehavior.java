package baguchan.tofucraft.dispenser;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public abstract class DamageableProjectileDispenseBehavior extends ProjectileDispenseBehavior {
	private final ProjectileItem projectileItem;
	private final ProjectileItem.DispenseConfig dispenseConfig;

	public DamageableProjectileDispenseBehavior(Item p_338781_) {
		super(p_338781_);
		if (p_338781_ instanceof ProjectileItem projectileitem) {
			this.projectileItem = projectileitem;
			this.dispenseConfig = projectileitem.createDispenseConfig();
		} else {
			throw new IllegalArgumentException(p_338781_ + " not instance of " + ProjectileItem.class.getSimpleName());
		}
	}

	public ItemStack execute(BlockSource p_123366_, ItemStack p_123367_) {
		Level level = p_123366_.level();
		Position position = DispenserBlock.getDispensePosition(p_123366_);
		Direction direction = p_123366_.state().getValue(DispenserBlock.FACING);
		for (int i = 0; i < shootCount(); i++) {
			Projectile projectile = this.projectileItem.asProjectile(level, position, p_123367_, direction);
			projectile.shoot((double) direction.getStepX(), (double) ((float) direction.getStepY() + 0.1F), (double) direction.getStepZ(), this.dispenseConfig.power(),
					this.dispenseConfig.uncertainty());
			level.addFreshEntity(projectile);
		}
		p_123367_.hurtAndBreak(1, level.getRandom(), null, () -> p_123367_.setCount(0));
		return p_123367_;
	}

	protected int shootCount() {
		return 1;
	}
}
