package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuItems;

import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class TofuSlimeEntity extends SlimeEntity {
	public TofuSlimeEntity(EntityType<? extends TofuSlimeEntity> p_i48552_1_, World p_i48552_2_) {
		super(p_i48552_1_, p_i48552_2_);
	}

	public static boolean checkTofuSlimeSpawnRules(EntityType<TofuSlimeEntity> p_223366_0_, IServerWorld p_223366_1_, SpawnReason p_223366_2_, BlockPos p_223366_3_, Random p_223366_4_) {
		if (p_223366_1_.func_175659_aa() != Difficulty.PEACEFUL) {
			if (!(p_223366_1_ instanceof net.minecraft.world.ISeedReader))
				return false;
			if (p_223366_4_.nextInt(10) == 0)
				return MonsterEntity.func_223323_a(p_223366_1_, p_223366_3_, p_223366_4_);
		}
		return false;
	}

	protected IParticleData func_195404_m() {
		return (IParticleData) new ItemParticleData(ParticleTypes.field_197591_B, new ItemStack((IItemProvider) TofuItems.TOFUKINU));
	}
}
