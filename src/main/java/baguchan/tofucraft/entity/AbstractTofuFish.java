package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.path.SoymilkPathNavigation;
import baguchan.tofucraft.registry.TofuFluidTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;

public abstract class AbstractTofuFish extends AbstractFish {
	private boolean wasTouchingSoymilk;

	public AbstractTofuFish(EntityType<? extends AbstractFish> p_27461_, Level p_27462_) {
		super(p_27461_, p_27462_);
	}

	protected PathNavigation createNavigation(Level p_27480_) {
		return new SoymilkPathNavigation(this, p_27480_);
	}

	public void travel(Vec3 p_27490_) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.01F, p_27490_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
		} else {
			super.travel(p_27490_);
		}

	}

	@Override
	public void tick() {
		super.tick();
		if (this.isInFluidType(TofuFluidTypes.SOYMILK.get()) || (this.isInFluidType(TofuFluidTypes.SOYMILK_HELL.get()) || (this.isInFluidType(TofuFluidTypes.SOYMILK_SOUL.get())))) {
			this.wasTouchingSoymilk = true;
		} else {
			this.wasTouchingSoymilk = false;
		}
	}

	//override isInWater because most method using this
	@Override
	public boolean isInWater() {
		return super.isInWater() || wasTouchingSoymilk;
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return type != TofuFluidTypes.SOYMILK.get() && type != TofuFluidTypes.SOYMILK_HELL.get() && type != TofuFluidTypes.SOYMILK_SOUL.get() && type != NeoForgeMod.WATER_TYPE.value() && super.canDrownInFluidType(type);
	}
}
