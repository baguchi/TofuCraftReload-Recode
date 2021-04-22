package baguchan.tofucraft.world;

import baguchan.tofucraft.entity.TravelerTofunianEntity;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
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
		if (this.world.func_82736_K().func_223586_b(GameRules.field_223601_d) && --this.field_221248_c <= 0) {
			this.field_221248_c = 1200;
			TravelerTofunianWorldData worldinfo = TravelerTofunianWorldData.get(this.world);
			this.field_221249_d -= 1200;
			worldinfo.setTofunianSpawnDelay(this.field_221249_d);
			if (this.field_221249_d <= 0) {
				this.field_221249_d = 24000;
				if (this.world.func_82736_K().func_223586_b(GameRules.field_223601_d)) {
					int i = this.field_221250_e;
					this.field_221250_e = MathHelper.clamp(this.field_221250_e + 25, 25, 75);
					worldinfo.setTofunianSpawnChance(this.field_221250_e);
					if (this.random.nextInt(100) <= i && func_221245_b())
						this.field_221250_e = 25;
				}
			}
		}
	}

	private boolean func_221245_b() {
		ServerPlayerEntity serverPlayerEntity = this.world.func_217472_l_();
		if (serverPlayerEntity == null)
			return true;
		if (this.random.nextInt(10) != 0)
			return false;
		BlockPos blockpos = new BlockPos(serverPlayerEntity.func_213303_ch());
		PointOfInterestManager pointofinterestmanager = this.world.func_217443_B();
		Optional<BlockPos> optional = pointofinterestmanager.func_219127_a(PointOfInterestType.field_221070_r.func_221045_c(), p_221241_0_ -> true, blockpos, 48, PointOfInterestManager.Status.ANY);
		BlockPos blockpos1 = optional.orElse(blockpos);
		BlockPos blockpos2 = func_221244_a(blockpos1, 48);
		if (blockpos2 != null && func_226559_a_(blockpos2)) {
			if (this.world.func_242406_i(blockpos2).equals(Optional.of(Biomes.field_185440_P)))
				return false;
			TravelerTofunianEntity entityTravelerTofunian = (TravelerTofunianEntity) TofuEntityTypes.TRAVELER_TOFUNIAN.func_220342_a(this.world, (CompoundNBT) null, (ITextComponent) null, (PlayerEntity) null, blockpos2, SpawnReason.EVENT, false, false);
			if (entityTravelerTofunian != null) {
				TravelerTofunianWorldData worldinfo = TravelerTofunianWorldData.get(this.world);
				worldinfo.setTravelerTofunianID(entityTravelerTofunian.func_110124_au());
				entityTravelerTofunian.setDespawnDelay(48000);
				entityTravelerTofunian.setWanderTarget(blockpos1);
				entityTravelerTofunian.func_213390_a(blockpos1, 16);
				return true;
			}
		}
		return false;
	}

	@Nullable
	private BlockPos func_221244_a(BlockPos p_221244_1_, int p_221244_2_) {
		BlockPos blockpos = null;
		for (int i = 0; i < 10; i++) {
			int j = p_221244_1_.getX() + this.random.nextInt(p_221244_2_ * 2) - p_221244_2_;
			int k = p_221244_1_.getZ() + this.random.nextInt(p_221244_2_ * 2) - p_221244_2_;
			int l = this.world.func_201676_a(Heightmap.Type.WORLD_SURFACE, j, k);
			BlockPos blockpos1 = new BlockPos(j, l, k);
			if (WorldEntitySpawner.canSpawnAtBody(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, (IWorldReader) this.world, blockpos1, EntityType.field_220351_aK)) {
				blockpos = blockpos1;
				break;
			}
		}
		return blockpos;
	}

	private boolean func_226559_a_(BlockPos p_226559_1_) {
		BlockPos blockpos;
		Iterator<BlockPos> var2 = BlockPos.func_218278_a(p_226559_1_, p_226559_1_.func_177982_a(1, 2, 1)).iterator();
		do {
			if (!var2.hasNext())
				return true;
			blockpos = var2.next();
		} while (this.world.getBlockState(blockpos).func_196952_d((IBlockReader) this.world, blockpos).func_197766_b());
		return false;
	}
}
