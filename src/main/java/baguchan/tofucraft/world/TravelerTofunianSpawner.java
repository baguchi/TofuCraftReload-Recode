package baguchan.tofucraft.world;

import baguchan.tofucraft.entity.TravelerTofunianEntity;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.GameRules;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

public class TravelerTofunianSpawner {
	private final Random random = new Random();

	private final ServerWorld world;

	private int field_221248_c;

	private int field_221249_d;

	private int field_221250_e;

	public TravelerTofunianSpawner(ServerWorld p_i50177_1_) {
		this.world = p_i50177_1_;
		this.field_221248_c = 1200;
		TravelerTofunianWorldData worldinfo = TravelerTofunianWorldData.get(p_i50177_1_);
		this.field_221249_d = worldinfo.getTofunianSpawnDelay();
		this.field_221250_e = worldinfo.getTofunianSpawnChance();
		if (this.field_221249_d == 0 && this.field_221250_e == 0) {
			this.field_221249_d = 24000;
			worldinfo.setTofunianSpawnDelay(this.field_221249_d);
			this.field_221250_e = 25;
			worldinfo.setTofunianSpawnChance(this.field_221250_e);
		}
	}

	public void tick() {
		if (this.world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING) && --this.field_221248_c <= 0) {
			this.field_221248_c = 1200;
			TravelerTofunianWorldData worldinfo = TravelerTofunianWorldData.get(world);
			this.field_221249_d -= 1200;
			worldinfo.setTofunianSpawnDelay(this.field_221249_d);
			if (this.field_221249_d <= 0) {
				this.field_221249_d = 24000;
				if (this.world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
					int i = this.field_221250_e;
					this.field_221250_e = MathHelper.clamp(this.field_221250_e + 25, 25, 75);
					worldinfo.setTofunianSpawnChance(this.field_221250_e);
					if (this.random.nextInt(100) <= i && this.func_221245_b()) {
						this.field_221250_e = 25;
					}
				}
			}
		}

	}

	private boolean func_221245_b() {
		PlayerEntity playerentity = this.world.getRandomPlayer();
		if (playerentity == null) {
			return true;
		} else if (this.random.nextInt(10) != 0) {
			return false;
		} else {
			BlockPos blockpos = new BlockPos(playerentity.position());
			PointOfInterestManager pointofinterestmanager = this.world.getPoiManager();
			Optional<BlockPos> optional = pointofinterestmanager.find(PointOfInterestType.MEETING.getPredicate(), (p_221241_0_) -> {
				return true;
			}, blockpos, 48, PointOfInterestManager.Status.ANY);
			BlockPos blockpos1 = (BlockPos)optional.orElse(blockpos);
			BlockPos blockpos2 = this.func_221244_a(blockpos1, 48);
			if (blockpos2 != null && this.func_226559_a_(blockpos2)) {
				if (world.getBiomeName(blockpos2).equals(Optional.of(Biomes.THE_VOID))) {
					return false;
				}

				TravelerTofunianEntity entityPlagueDoctor = TofuEntityTypes.TRAVELER_TOFUNIAN.spawn(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, blockpos2, SpawnReason.EVENT, false, false);
				if (entityPlagueDoctor != null) {
					TravelerTofunianWorldData worldinfo = TravelerTofunianWorldData.get(world);

					worldinfo.setTravelerTofunianID(entityPlagueDoctor.getUUID());
					entityPlagueDoctor.setDespawnDelay(48000);
					entityPlagueDoctor.setWanderTarget(blockpos1);
					entityPlagueDoctor.restrictTo(blockpos1, 16);
					return true;
				}
			}

			return false;
		}
	}


	@Nullable
	private BlockPos func_221244_a(BlockPos p_221244_1_, int p_221244_2_) {
		BlockPos blockpos = null;

		for(int i = 0; i < 10; ++i) {
			int j = p_221244_1_.getX() + this.random.nextInt(p_221244_2_ * 2) - p_221244_2_;
			int k = p_221244_1_.getZ() + this.random.nextInt(p_221244_2_ * 2) - p_221244_2_;
			int l = this.world.getHeight(Heightmap.Type.WORLD_SURFACE, j, k);
			BlockPos blockpos1 = new BlockPos(j, l, k);
			if (WorldEntitySpawner.canSpawnAtBody(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, this.world, blockpos1, EntityType.WANDERING_TRADER)) {
				blockpos = blockpos1;
				break;
			}
		}

		return blockpos;
	}

	private boolean func_226559_a_(BlockPos p_226559_1_) {
		Iterator var2 = BlockPos.betweenClosed(p_226559_1_, p_226559_1_.offset(1, 2, 1)).iterator();

		BlockPos blockpos;
		do {
			if (!var2.hasNext()) {
				return true;
			}

			blockpos = (BlockPos)var2.next();
		} while(this.world.getBlockState(blockpos).getCollisionShape(this.world, blockpos).isEmpty());

		return false;
	}
}
