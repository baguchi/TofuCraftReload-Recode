package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.ai.*;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTrades;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.IReputationTracking;
import net.minecraft.entity.merchant.IReputationType;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.*;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.GossipManager;
import net.minecraft.village.GossipType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TofunianEntity extends AbstractTofunianEntity implements IReputationTracking {
	private static final DataParameter<String> ROLE = EntityDataManager.defineId(TofunianEntity.class, DataSerializers.STRING);

	public static final Map<Item, Integer> FOOD_POINTS = ImmutableMap.of(TofuItems.SOYMILK, Integer.valueOf(5), TofuItems.TOFUCOOKIE, Integer.valueOf(3), TofuItems.TOFUGRILLED, Integer.valueOf(2));

	private static final Set<Item> WANTED_ITEMS = ImmutableSet.of(TofuItems.SOYMILK, TofuItems.TOFUCOOKIE, TofuItems.TOFUGRILLED, TofuItems.SEEDS_SOYBEANS);

	private byte foodLevel;

	@Nullable
	private BlockPos tofunainHome;

	@Nullable
	private BlockPos tofunainJobBlock;

	private final GossipManager gossip = new GossipManager();

	private long lastGossipDecay;

	private long lastRestock;

	private int restocksToday;

	private long lastRestockDayTime;

	private int timeUntilReset;

	private boolean leveledUp;

	@Nullable
	private PlayerEntity previousCustomer;

	private int xp;

	private int tofunianLevel = 1;

	public TofunianEntity(EntityType<? extends TofunianEntity> type, World worldIn) {
		super(type, worldIn);
		((GroundPathNavigator) getNavigation()).setCanOpenDoors(true);
		setCanPickUpLoot(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new WakeUpGoal(this));
		this.goalSelector.addGoal(0, new DoSleepingGoal(this));
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, ZombieEntity.class, 8.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, AbstractIllagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, RavagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, ZoglinEntity.class, 10.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
		this.goalSelector.addGoal(2, new SleepOnBedGoal(this, 1.0D, 8));
		this.goalSelector.addGoal(3, new TofunianLoveGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FindJobBlockGoal(this, 1.0D, 8));
		this.goalSelector.addGoal(4, new RestockGoal(this, 1.149999976158142D, 6));
		this.goalSelector.addGoal(4, new MakeFoodGoal(this, 1.100000023841858D, 6));
		this.goalSelector.addGoal(4, new CropHarvestGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new ShareItemGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.23999999463558197D).add(Attributes.MAX_HEALTH, 20.0D);
	}

	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return TofuEntityTypes.TOFUNIAN.create(p_241840_1_);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ROLE, Roles.TOFUNIAN.name());
	}

	public void setRole(Roles role) {
		this.entityData.set(ROLE, role.name());
	}

	public Roles getRole() {
		return Roles.get(this.entityData.get(ROLE));
	}

	public void setTofunainHome(@Nullable BlockPos pos) {
		this.tofunainHome = pos;
	}

	@Nullable
	public BlockPos getTofunainHome() {
		return this.tofunainHome;
	}

	public void setTofunainJobBlock(@Nullable BlockPos tofunainJobBlock) {
		this.tofunainJobBlock = tofunainJobBlock;
	}

	@Nullable
	public BlockPos getTofunainJobBlock() {
		return this.tofunainJobBlock;
	}

	@Nullable
	public Entity changeDimension(ServerWorld server, ITeleporter teleporter) {
		setTofunainHome(null);
		setTofunainJobBlock(null);
		return super.changeDimension(server, teleporter);
	}

	protected void customServerAiStep() {
		if (!isTrading() && this.timeUntilReset > 0) {
			this.timeUntilReset--;
			if (this.timeUntilReset <= 0) {
				if (this.leveledUp) {
					increaseMerchantCareer();
					this.leveledUp = false;
				}
				addEffect(new EffectInstance(Effects.REGENERATION, 200, 0));
			}
		}
		if (this.previousCustomer != null && getLevel() instanceof ServerWorld) {
			((ServerWorld) getLevel()).onReputationEvent(IReputationType.TRADE, this.previousCustomer, this);
			getLevel().broadcastEntityEvent(this, (byte) 14);
			this.previousCustomer = null;
		}
		if (getRole() == Roles.TOFUNIAN && isTrading())
			stopTrading();
		super.customServerAiStep();
	}


	public void onReputationEventFrom(IReputationType p_213739_1_, Entity p_213739_2_) {
		if (p_213739_1_ == IReputationType.ZOMBIE_VILLAGER_CURED) {
			this.gossip.add(p_213739_2_.getUUID(), GossipType.MAJOR_POSITIVE, 20);
			this.gossip.add(p_213739_2_.getUUID(), GossipType.MINOR_POSITIVE, 25);
		} else if (p_213739_1_ == IReputationType.TRADE) {
			this.gossip.add(p_213739_2_.getUUID(), GossipType.TRADING, 2);
		} else if (p_213739_1_ == IReputationType.VILLAGER_HURT) {
			this.gossip.add(p_213739_2_.getUUID(), GossipType.MINOR_NEGATIVE, 25);
		} else if (p_213739_1_ == IReputationType.VILLAGER_KILLED) {
			this.gossip.add(p_213739_2_.getUUID(), GossipType.MAJOR_NEGATIVE, 25);
		}
	}

	protected void rewardTradeXp(MerchantOffer offer) {
		int i = 3 + this.random.nextInt(4);
		this.xp += offer.getXp();
		this.previousCustomer = this.getTradingPlayer();
		if (canLevelUp()) {
			this.timeUntilReset = 40;
			this.leveledUp = true;
			i += 5;
		}
		if (offer.shouldRewardExp())
			getLevel().addFreshEntity(new ExperienceOrbEntity(getLevel(), getX(), getY() + 0.5D, getZ(), i));
	}

	public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
		if (itemstack.getItem() != TofuItems.TOFUNIAN_SPAWNEGG && isAlive() && !isTrading() && !isBaby()) {
			if (isBaby()) {
				shakeHead();
				return ActionResultType.sidedSuccess(getLevel().isClientSide());
			}
			boolean flag = this.getOffers().isEmpty();
			if (p_230254_2_ == Hand.MAIN_HAND) {
				if (flag && !getLevel().isClientSide())
					shakeHead();
				p_230254_1_.awardStat(Stats.TRADED_WITH_VILLAGER);
			}
			if (flag)
				return ActionResultType.sidedSuccess(getLevel().isClientSide());
			if (!getLevel().isClientSide() && !this.offers.isEmpty())
				displayMerchantGui(p_230254_1_);
			return ActionResultType.sidedSuccess(getLevel().isClientSide());
		}
		return ActionResultType.sidedSuccess(getLevel().isClientSide());
	}

	private void displayMerchantGui(PlayerEntity player) {
		recalculateSpecialPricesFor(player);
		setTradingPlayer(player);
		openTradingScreen(player, getDisplayName(), this.tofunianLevel);
	}

	public void setTradingPlayer(@Nullable PlayerEntity player) {
		boolean flag = (getTradingPlayer() != null && player == null);
		super.setTradingPlayer(player);
		if (flag)
			stopTrading();
	}

	protected void stopTrading() {
		super.stopTrading();
		resetSpecialPrices();
	}

	private void resetSpecialPrices() {
		for (MerchantOffer merchantoffer : this.getOffers())
			merchantoffer.resetSpecialPriceDiff();
	}

	public boolean canRestock() {
		return true;
	}

	public void restock() {
		calculateDemandOfOffers();
		for (MerchantOffer merchantoffer : this.getOffers())
			merchantoffer.resetUses();
		this.lastRestock = getLevel().getGameTime();
		this.restocksToday++;
	}

	private boolean allowedToRestock() {
		return (this.restocksToday == 0 || (this.restocksToday < 2 && getLevel().getGameTime() > this.lastRestock + 2400L));
	}

	public boolean canResetStock() {
		long i = this.lastRestock + 12000L;
		long j = this.level.getGameTime();
		boolean flag = j > i;
		long k = this.level.getDayTime();
		if (this.lastRestockDayTime > 0L) {
			long l = this.lastRestockDayTime / 24000L;
			long i1 = k / 24000L;
			flag |= i1 > l;
		}

		this.lastRestockDayTime = k;
		if (flag) {
			this.lastRestock = j;
			this.resetNumberOfRestocks();
		}

		return this.allowedToRestock() && this.hasUsedOffer();
	}

	private void resetNumberOfRestocks() {
		resetOffersAndAdjustForDemand();
		this.restocksToday = 0;
	}

	private void resetOffersAndAdjustForDemand() {
		int i = 2 - this.restocksToday;
		if (i > 0)
			for (MerchantOffer merchantoffer : this.getOffers())
				merchantoffer.resetUses();
		for (int j = 0; j < i; j++)
			calculateDemandOfOffers();
	}

	private boolean hasUsedOffer() {
		for (MerchantOffer merchantoffer : this.getOffers()) {
			if (merchantoffer.needsRestock())
				return true;
		}
		return false;
	}

	private void calculateDemandOfOffers() {
		for (MerchantOffer merchantoffer : this.getOffers()) {
			merchantoffer.updateDemand();
		}
	}

	private void recalculateSpecialPricesFor(PlayerEntity playerIn) {
		int i = getPlayerReputation(playerIn);
		if (i != 0)
			for (MerchantOffer merchantoffer : this.getOffers())
				merchantoffer.addToSpecialPriceDiff(-MathHelper.floor((float) i * merchantoffer.getPriceMultiplier()));
	}

	public void setOffers(MerchantOffers offersIn) {
		this.offers = offersIn;
	}

	private boolean canLevelUp() {
		int i = this.tofunianLevel;
		return VillagerData.canLevelUp(i) && this.tofunianLevel >= VillagerData.getMaxXpPerLevel(i);
	}

	private void increaseMerchantCareer() {
		setTofunainLevel(this.tofunianLevel + 1);
		updateTrades();
	}

	public void setTofunainLevel(int level) {
		this.tofunianLevel = level;
	}

	public int getTofunainLevel() {
		return this.tofunianLevel;
	}

	public int getPlayerReputation(PlayerEntity player) {
		return this.gossip.getReputation(player.getUUID(), gossipType -> true);
	}

	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("FoodLevel", this.foodLevel);
		compound.put("Gossips", (INBT) this.gossip.store((DynamicOps) NBTDynamicOps.INSTANCE).getValue());
		compound.putInt("Xp", this.xp);
		compound.putInt("Level", this.tofunianLevel);
		compound.putLong("LastRestock", this.lastRestock);
		compound.putLong("LastGossipDecay", this.lastGossipDecay);
		compound.putInt("RestocksToday", this.restocksToday);
		if (this.tofunainHome != null)
			compound.put("TofunianHome", NBTUtil.writeBlockPos(this.tofunainHome));
		if (this.tofunainJobBlock != null)
			compound.put("TofunianJobBlock", NBTUtil.writeBlockPos(this.tofunainJobBlock));
		compound.putString("Roles", getRole().name());
	}

	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Offers", 10))
			this.offers = new MerchantOffers(compound.getCompound("Offers"));
		if (compound.contains("FoodLevel", 1))
			this.foodLevel = compound.getByte("FoodLevel");
		ListNBT listnbt = compound.getList("Gossips", 10);
		this.gossip.update(new Dynamic<>(NBTDynamicOps.INSTANCE, listnbt));
		if (compound.contains("Xp", 3))
			this.xp = compound.getInt("Xp");
		if (compound.contains("Level"))
			this.tofunianLevel = compound.getInt("Level");
		this.lastGossipDecay = compound.getLong("LastGossipDecay");
		this.lastRestock = compound.getLong("LastRestock");
		this.restocksToday = compound.getInt("RestocksToday");
		if (compound.contains("TofunianHome"))
			this.tofunainHome = NBTUtil.readBlockPos(compound.getCompound("TofunianHome"));
		if (compound.contains("TofunianJobBlock"))
			this.tofunainJobBlock = NBTUtil.readBlockPos(compound.getCompound("TofunianJobBlock"));
		if (compound.contains("Roles"))
			setRole(Roles.get(compound.getString("Roles")));
		setCanPickUpLoot(true);
	}

	public int getVillagerXp() {
		return this.xp;
	}

	protected void pickUpItem(ItemEntity p_175445_1_) {
		ItemStack itemstack = p_175445_1_.getItem();
		if (wantsToPickUp(itemstack)) {
			Inventory inventory = getInventory();
			boolean flag = inventory.canAddItem(itemstack);
			if (!flag)
				return;
			this.onItemPickup(p_175445_1_);
			this.take(p_175445_1_, itemstack.getCount());
			ItemStack itemstack1 = inventory.addItem(itemstack);
			if (itemstack1.isEmpty()) {
				p_175445_1_.remove();
			} else {
				itemstack.setCount(itemstack1.getCount());
			}
		}
	}

	public boolean func_213743_em() {
		return (this.foodLevel + countFoodPointsInInventory() >= 12 && getAge() == 0);
	}

	private boolean hungry() {
		return (this.foodLevel < 12);
	}

	private void eatUntilFull() {
		if (hungry() && countFoodPointsInInventory() != 0)
			for (int i = 0; i < getInventory().getContainerSize(); i++) {
				ItemStack itemstack = getInventory().getItem(i);
				if (!itemstack.isEmpty()) {
					Integer integer = FOOD_POINTS.get(itemstack.getItem());
					if (integer != null) {
						int j = itemstack.getCount();
						for (int k = j; k > 0; k--) {
							this.foodLevel = (byte) (this.foodLevel + integer.intValue());
							getInventory().removeItem(i, 1);
							if (!hungry())
								return;
						}
					}
				}
			}
	}

	public boolean wantsToPickUp(ItemStack p_230293_1_) {
		Item item = p_230293_1_.getItem();
		return (WANTED_ITEMS.contains(item) && getInventory().canAddItem(p_230293_1_));
	}

	public boolean hasExcessFood() {
		return (countFoodPointsInInventory() >= 32);
	}

	public boolean wantsMoreFood() {
		return (countFoodPointsInInventory() < 24);
	}

	public boolean hasFarmSeeds() {
		return getInventory().hasAnyOf(ImmutableSet.of(TofuItems.SEEDS_SOYBEANS));
	}

	private int countFoodPointsInInventory() {
		Inventory inventory = getInventory();
		return FOOD_POINTS.entrySet().stream().mapToInt(p_226553_1_ -> inventory.countItem(p_226553_1_.getKey()) * p_226553_1_.getValue().intValue())

				.sum();
	}

	public void cookingFood() {
		for (int i = 0; i < getInventory().getContainerSize(); i++) {
			ItemStack itemstack = getInventory().getItem(i);
			if (!itemstack.isEmpty() &&
					itemstack.getItem() == TofuItems.SEEDS_SOYBEANS) {
				getInventory().removeItem(i, 1);
				cookResult();
			}
		}
	}

	private void cookResult() {
		getInventory().addItem(new ItemStack(TofuItems.TOFUGRILLED));
	}

	protected void updateTrades() {
		Int2ObjectMap<VillagerTrades.ITrade[]> int2objectmap = TofuTrades.TOFUNIAN_TRADE.get(getRole());
		if (int2objectmap != null && !int2objectmap.isEmpty()) {
			VillagerTrades.ITrade[] avillagertrades$itrade = int2objectmap.get(this.tofunianLevel);
			if (avillagertrades$itrade != null) {
				MerchantOffers merchantoffers = this.getOffers();
				addOffersFromItemListings(merchantoffers, avillagertrades$itrade, 2);
			}
		}
	}


	public void tick() {
		super.tick();
		tickGossip();
	}

	private void tickGossip() {
		long i = getLevel().getGameTime();
		if (this.lastGossipDecay == 0L) {
			this.lastGossipDecay = i;
		} else if (i >= this.lastGossipDecay + 24000L) {
			this.gossip.decay();
			this.lastGossipDecay = i;
		}
	}

	public GossipManager getGossip() {
		return this.gossip;
	}

	public void setGossips(INBT gossip) {
		this.gossip.update(new Dynamic(NBTDynamicOps.INSTANCE, gossip));
	}

	public void setLastHurtByMob(@Nullable LivingEntity p_70604_1_) {
		if (p_70604_1_ != null && this.level instanceof ServerWorld) {
			((ServerWorld) this.level).onReputationEvent(IReputationType.VILLAGER_HURT, p_70604_1_, this);
			if (this.isAlive() && p_70604_1_ instanceof PlayerEntity) {
				this.level.broadcastEntityEvent(this, (byte) 13);
			}
		}

		super.setLastHurtByMob(p_70604_1_);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 12) {
			this.addParticlesAroundSelf(ParticleTypes.HEART);
		} else if (p_70103_1_ == 13) {
			this.addParticlesAroundSelf(ParticleTypes.ANGRY_VILLAGER);
		} else if (p_70103_1_ == 14) {
			this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
		} else if (p_70103_1_ == 42) {
			this.addParticlesAroundSelf(ParticleTypes.SPLASH);
		} else {
			super.handleEntityEvent(p_70103_1_);
		}

	}

	public enum Roles implements IExtensibleEnum {
		TOFUCOOK, TOFUSMITH, SOYWORKER, TOFUNIAN;

		private static final Map<String, Roles> lookup;

		static {
			lookup = Arrays.stream(values()).collect(Collectors.toMap(Enum::name, p_220362_0_ -> p_220362_0_));
		}

		public static Roles create(String name) {
			throw new IllegalStateException("Enum not extended");
		}

		public static Roles get(String nameIn) {
			for (Roles role : values()) {
				if (role.name().equals(nameIn))
					return role;
			}
			return TOFUNIAN;
		}
	}
}
