package baguchi.tofucraft.entity.tofunian;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuSounds;
import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractTofunian extends AgeableMob implements InventoryCarrier, Npc, Merchant {

	private static final EntityDimensions BABY_DIMENSIONS = TofuEntityTypes.TOFUNIAN.get().getDimensions().scale(0.5F).withEyeHeight(0.3F);

	private static final EntityDataAccessor<Integer> DATA_UNHAPPY_COUNTER = SynchedEntityData.defineId(AbstractTofunian.class, EntityDataSerializers.INT);
	@Nullable
	private Player tradingPlayer;
	@Nullable
	protected MerchantOffers offers;
	private final SimpleContainer inventory = new SimpleContainer(8);
	protected int idleAnimationTimeout;
	public final AnimationState idleAnimationState = new AnimationState();

	public AbstractTofunian(EntityType<? extends AbstractTofunian> type, Level worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(PathType.FIRE_IN_NEIGHBOR, 16.0F);
		this.setPathfindingMalus(PathType.FIRE, -1.0F);
	}

	@Override
	public void tick() {
		super.tick();
		if (getUnhappyCounter() > 0) {
			setUnhappyCounter(getUnhappyCounter() - 1);
		}
		if (this.level().isClientSide()) {
			this.setupAnimationStates();
		}
	}

	public void setupAnimationStates() {
		if (!this.isSleeping()) {
			if (this.idleAnimationTimeout <= 0) {
				this.idleAnimationTimeout = this.random.nextInt(40) + 80;
				this.idleAnimationState.start(this.tickCount);
			} else {
				--this.idleAnimationTimeout;
			}
		}
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return TofuSounds.TOFUNIAN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource p_21239_) {
		return TofuSounds.TOFUNIAN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return TofuSounds.TOFUNIAN_DEATH.get();
	}

	protected SoundEvent getTradeUpdatedSound(boolean p_213721_1_) {
		return p_213721_1_ ? TofuSounds.TOFUNIAN_YES.get() : TofuSounds.TOFUNIAN_NO.get();
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
		return TofuSounds.TOFUNIAN_YES.get();
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_35282_, DifficultyInstance p_35283_, EntitySpawnReason p_35284_, @Nullable SpawnGroupData p_35285_) {
		if (p_35285_ == null) {
			p_35285_ = new AgeableMob.AgeableMobGroupData(false);
		}

		return super.finalizeSpawn(p_35282_, p_35283_, p_35284_, p_35285_);
	}

	public int getUnhappyCounter() {
		return this.entityData.get(DATA_UNHAPPY_COUNTER);
	}

	public void setUnhappyCounter(int p_35320_) {
		this.entityData.set(DATA_UNHAPPY_COUNTER, p_35320_);
	}

	public int getVillagerXp() {
		return 0;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_UNHAPPY_COUNTER, 0);
	}

	public void setTradingPlayer(@Nullable Player p_35314_) {
		this.tradingPlayer = p_35314_;
	}

	@Nullable
	public Player getTradingPlayer() {
		return this.tradingPlayer;
	}

	public boolean isTrading() {
		return this.tradingPlayer != null;
	}

	@Override
	public MerchantOffers getOffers() {
		if (this.level() instanceof ServerLevel serverLevel) {
			if (this.offers == null) {
				this.offers = new MerchantOffers();
				this.updateTrades(serverLevel);
			}

			return this.offers;
		} else {
			throw new IllegalStateException("Cannot load Villager offers on the client");
		}
	}

	@Override
	public void overrideOffers(@Nullable MerchantOffers p_35276_) {
	}

	@Override
	public void overrideXp(int p_35322_) {
	}

	@Override
	public void notifyTrade(MerchantOffer p_35274_) {
		p_35274_.increaseUses();
		this.ambientSoundTime = -this.getAmbientSoundInterval();
		this.rewardTradeXp(p_35274_);
		if (this.tradingPlayer instanceof ServerPlayer) {
			//CriteriaTriggers.TRADE.trigger((ServerPlayer)this.tradingPlayer, this, p_35274_.getResult());
		}
	}

	protected abstract void rewardTradeXp(MerchantOffer p_35299_);

	@Override
	public boolean showProgressBar() {
		return true;
	}

	@Override
	public void notifyTradeUpdated(ItemStack p_35316_) {
		if (!this.level().isClientSide() && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
			this.ambientSoundTime = -this.getAmbientSoundInterval();
			this.playSound(this.getTradeUpdatedSound(!p_35316_.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
		}

	}

	@Override
	public void addAdditionalSaveData(ValueOutput p_35301_) {
		super.addAdditionalSaveData(p_35301_);
		if (!this.level().isClientSide()) {
			MerchantOffers merchantoffers = this.getOffers();
			if (!merchantoffers.isEmpty()) {
				p_35301_.store("Offers", MerchantOffers.CODEC, merchantoffers);
			}
		}

		this.writeInventoryToTag(p_35301_);
	}

	@Override
	public void readAdditionalSaveData(ValueInput p_35290_) {
		super.readAdditionalSaveData(p_35290_);
		this.offers = p_35290_.read("Offers", MerchantOffers.CODEC).orElse(null);


		this.readInventoryFromTag(p_35290_);
	}

	@Override
	protected EntityDimensions getDefaultDimensions(Pose p_316700_) {
		return super.getDefaultDimensions(p_316700_);
	}



	protected void stopTrading() {
		this.setTradingPlayer((Player) null);
	}

	@Override
	public void die(DamageSource p_35270_) {
		super.die(p_35270_);
		this.stopTrading();
	}

	protected void addParticlesAroundSelf(ParticleOptions p_35288_) {
		for (int i = 0; i < 5; ++i) {
			double d0 = this.random.nextGaussian() * 0.02D;
			double d1 = this.random.nextGaussian() * 0.02D;
			double d2 = this.random.nextGaussian() * 0.02D;
			this.level().addParticle(p_35288_, this.getRandomX(1.0D), this.getRandomY() + 1.0D, this.getRandomZ(1.0D), d0, d1, d2);
		}

	}

	@Override
	public boolean canBeLeashed() {
		return false;
	}

	public SimpleContainer getInventory() {
		return this.inventory;
	}

	public SlotAccess getSlot(int p_149995_) {
		int i = p_149995_ - 300;
		return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forListElement(this.inventory.getItems(), i) : super.getSlot(p_149995_);
	}

	protected abstract void updateTrades(ServerLevel serverLevel);

	protected void addOffersFromTradeSet(ServerLevel level, MerchantOffers offers, ResourceKey<TradeSet> resourceKey) {
		Optional<TradeSet> tradeSetOpt = this.registryAccess().lookupOrThrow(Registries.TRADE_SET).getOptional(resourceKey);
		if (tradeSetOpt.isEmpty()) {
			TofuCraftReload.LOGGER.debug("Missing expected trade set {}", resourceKey);
		} else {
			TradeSet tradeSet = tradeSetOpt.get();
			LootContext lootContext = new LootContext.Builder(
					new LootParams.Builder(level)
							.withParameter(LootContextParams.ORIGIN, this.position())
							.withParameter(LootContextParams.THIS_ENTITY, this)
							.withParameter(LootContextParams.ADDITIONAL_COST_COMPONENT_ALLOWED, Unit.INSTANCE)
							.create(LootContextParamSets.VILLAGER_TRADE)
			)
					.create(tradeSet.randomSequence());
			int numberOfOffers = tradeSet.calculateNumberOfTrades(lootContext);
			if (tradeSet.allowDuplicates()) {
				addOffersFromItemListings(lootContext, offers, tradeSet.getTrades(), numberOfOffers);
			} else {
				addOffersFromItemListingsWithoutDuplicates(lootContext, offers, tradeSet.getTrades(), numberOfOffers);
			}
		}
	}

	private static void addOffersFromItemListings(
			LootContext lootContext, MerchantOffers merchantOffers, HolderSet<VillagerTrade> potentialOffers, int numberOfOffers
	) {
		int offersFound = 0;

		while (offersFound < numberOfOffers) {
			Optional<Holder<VillagerTrade>> villagerTrade = potentialOffers.getRandomElement(lootContext.getRandom());
			if (villagerTrade.isEmpty()) {
				break;
			}

			MerchantOffer offer = villagerTrade.get().value().getOffer(lootContext);
			if (offer != null) {
				merchantOffers.add(offer);
				offersFound++;
			}
		}
	}

	private static void addOffersFromItemListingsWithoutDuplicates(
			LootContext lootContext, MerchantOffers merchantOffers, HolderSet<VillagerTrade> potentialOffers, int numberOfOffers
	) {
		List<Holder<VillagerTrade>> leftoverOffers = Lists.newArrayList(potentialOffers);
		int offersFound = 0;

		while (offersFound < numberOfOffers && !leftoverOffers.isEmpty()) {
			Holder<VillagerTrade> villagerTrade = leftoverOffers.remove(lootContext.getRandom().nextInt(leftoverOffers.size()));
			MerchantOffer offer = villagerTrade.value().getOffer(lootContext);
			if (offer != null) {
				merchantOffers.add(offer);
				offersFound++;
			}
		}
	}

	public Vec3 getRopeHoldPosition(float p_35318_) {
		float f = Mth.lerp(p_35318_, this.yBodyRotO, this.yBodyRot) * ((float) Math.PI / 180F);
		Vec3 vec3 = new Vec3(0.0D, this.getBoundingBox().getYsize() - 1.0D, 0.2D);
		return this.getPosition(p_35318_).add(vec3.yRot(-f));
	}

	public boolean isClientSide() {
		return this.level().isClientSide();
	}

	@Override
	protected boolean considersEntityAsAlly(Entity p_360600_) {
		if (super.considersEntityAsAlly(p_360600_)) {
			return true;
		} else {
			if (p_360600_.getType() == TofuEntityTypes.TOFU_GOLEM) {
				return this.getTeam() == null && p_360600_.getTeam() == null;
			}

			return !(p_360600_ instanceof AbstractTofunian) ? false : this.getTeam() == null && p_360600_.getTeam() == null;
		}
	}

	@Override
	public boolean stillValid(Player p_383034_) {
		return this.getTradingPlayer() == p_383034_ && this.isAlive() && p_383034_.isWithinEntityInteractionRange(this, 4.0);
	}
}
