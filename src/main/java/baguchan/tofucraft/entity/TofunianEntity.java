package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.ai.CropHarvestGoal;
import baguchan.tofucraft.entity.ai.DoSleepingGoal;
import baguchan.tofucraft.entity.ai.FindJobBlockGoal;
import baguchan.tofucraft.entity.ai.MakeFoodGoal;
import baguchan.tofucraft.entity.ai.RestockGoal;
import baguchan.tofucraft.entity.ai.ShareItemGoal;
import baguchan.tofucraft.entity.ai.SleepOnBedGoal;
import baguchan.tofucraft.entity.ai.TofunianLoveGoal;
import baguchan.tofucraft.entity.ai.WakeUpGoal;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTrades;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtCustomerGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookAtWithoutMovingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
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

public class TofunianEntity extends AbstractTofunianEntity implements IReputationTracking {
	private static final DataParameter<String> ROLE = EntityDataManager.func_187226_a(TofunianEntity.class, DataSerializers.field_187194_d);

	public static final Map<Item, Integer> FOOD_POINTS = (Map<Item, Integer>) ImmutableMap.of(TofuItems.SOYMILK, Integer.valueOf(5), TofuItems.TOFUCOOKIE, Integer.valueOf(3), TofuItems.TOFUGRILLED, Integer.valueOf(2));

	private static final Set<Item> WANTED_ITEMS = (Set<Item>) ImmutableSet.of(TofuItems.SOYMILK, TofuItems.TOFUCOOKIE, TofuItems.TOFUGRILLED, TofuItems.SEEDS_SOYBEANS);

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
		super((EntityType) type, worldIn);
		((GroundPathNavigator) func_70661_as()).func_179688_b(true);
		func_98053_h(true);
	}

	protected void func_184651_r() {
		super.func_184651_r();
		this.field_70714_bg.func_75776_a(0, (Goal) new WakeUpGoal((CreatureEntity) this));
		this.field_70714_bg.func_75776_a(0, (Goal) new DoSleepingGoal((CreatureEntity) this));
		this.field_70714_bg.func_75776_a(0, (Goal) new SwimGoal((MobEntity) this));
		this.field_70714_bg.func_75776_a(1, (Goal) new TradeWithPlayerGoal(this));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, ZombieEntity.class, 8.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, AbstractIllagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, RavagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, ZoglinEntity.class, 10.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new LookAtCustomerGoal(this));
		this.field_70714_bg.func_75776_a(2, (Goal) new SleepOnBedGoal(this, 1.0D, 8));
		this.field_70714_bg.func_75776_a(3, (Goal) new TofunianLoveGoal(this, 1.0D));
		this.field_70714_bg.func_75776_a(4, (Goal) new FindJobBlockGoal(this, 1.0D, 8));
		this.field_70714_bg.func_75776_a(4, (Goal) new RestockGoal(this, 1.149999976158142D, 6));
		this.field_70714_bg.func_75776_a(4, (Goal) new MakeFoodGoal(this, 1.100000023841858D, 6));
		this.field_70714_bg.func_75776_a(4, (Goal) new CropHarvestGoal(this, 1.0D));
		this.field_70714_bg.func_75776_a(5, (Goal) new ShareItemGoal(this, 1.0D));
		this.field_70714_bg.func_75776_a(8, (Goal) new WaterAvoidingRandomWalkingGoal((CreatureEntity) this, 1.0D));
		this.field_70714_bg.func_75776_a(9, (Goal) new LookAtWithoutMovingGoal((MobEntity) this, PlayerEntity.class, 3.0F, 1.0F));
		this.field_70714_bg.func_75776_a(10, (Goal) new LookAtGoal((MobEntity) this, MobEntity.class, 8.0F));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().func_233815_a_(Attributes.field_233821_d_, 0.23999999463558197D).func_233815_a_(Attributes.field_233819_b_, 20.0D);
	}

	protected void func_70088_a() {
		super.func_70088_a();
		this.field_70180_af.func_187214_a(ROLE, Roles.TOFUNIAN.name());
	}

	public void setRole(Roles role) {
		this.field_70180_af.func_187227_b(ROLE, role.name());
	}

	public Roles getRole() {
		return Roles.get((String) this.field_70180_af.func_187225_a(ROLE));
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
		setTofunainHome((BlockPos) null);
		return super.changeDimension(server, teleporter);
	}

	protected void func_70619_bc() {
		if (!func_213716_dX() && this.timeUntilReset > 0) {
			this.timeUntilReset--;
			if (this.timeUntilReset <= 0) {
				if (this.leveledUp) {
					increaseMerchantCareer();
					this.leveledUp = false;
				}
				func_195064_c(new EffectInstance(Effects.field_76428_l, 200, 0));
			}
		}
		if (this.previousCustomer != null && func_130014_f_() instanceof ServerWorld) {
			((ServerWorld) func_130014_f_()).func_217489_a(IReputationType.field_221033_e, (Entity) this.previousCustomer, this);
			func_130014_f_().func_72960_a((Entity) this, (byte) 14);
			this.previousCustomer = null;
		}
		if (getRole() == Roles.TOFUNIAN && func_213716_dX())
			func_213750_eg();
		super.func_70619_bc();
	}

	public void onReputationEvent(IReputationType type, Entity target) {
		if (type == IReputationType.field_221029_a) {
			this.gossip.func_220916_a(target.func_110124_au(), GossipType.MAJOR_POSITIVE, 20);
			this.gossip.func_220916_a(target.func_110124_au(), GossipType.MINOR_POSITIVE, 25);
		} else if (type == IReputationType.field_221033_e) {
			this.gossip.func_220916_a(target.func_110124_au(), GossipType.TRADING, 2);
		} else if (type == IReputationType.field_221031_c) {
			this.gossip.func_220916_a(target.func_110124_au(), GossipType.MINOR_NEGATIVE, 25);
		} else if (type == IReputationType.field_221032_d) {
			this.gossip.func_220916_a(target.func_110124_au(), GossipType.MAJOR_NEGATIVE, 25);
		}
	}

	protected void func_213713_b(MerchantOffer offer) {
		int i = 3 + this.field_70146_Z.nextInt(4);
		this.xp += offer.func_222210_n();
		this.previousCustomer = func_70931_l_();
		if (canLevelUp()) {
			this.timeUntilReset = 40;
			this.leveledUp = true;
			i += 5;
		}
		if (offer.func_222221_q())
			func_130014_f_().func_217376_c((Entity) new ExperienceOrbEntity(func_130014_f_(), func_226277_ct_(), func_226278_cu_() + 0.5D, func_226281_cx_(), i));
	}

	public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.func_184586_b(p_230254_2_);
		if (itemstack.func_77973_b() != TofuItems.TOFUNIAN_SPAWNEGG && func_70089_S() && !func_213716_dX() && !func_70631_g_()) {
			if (func_70631_g_()) {
				shakeHead();
				return ActionResultType.func_233537_a_(func_130014_f_().isClientSide());
			}
			boolean flag = func_213706_dY().isEmpty();
			if (p_230254_2_ == Hand.MAIN_HAND) {
				if (flag && !func_130014_f_().isClientSide())
					shakeHead();
				p_230254_1_.func_195066_a(Stats.field_188074_H);
			}
			if (flag)
				return ActionResultType.func_233537_a_(func_130014_f_().isClientSide());
			if (!func_130014_f_().isClientSide() && !this.field_213724_bz.isEmpty())
				displayMerchantGui(p_230254_1_);
			return ActionResultType.func_233537_a_(func_130014_f_().isClientSide());
		}
		return ActionResultType.func_233537_a_(func_130014_f_().isClientSide());
	}

	private void displayMerchantGui(PlayerEntity player) {
		recalculateSpecialPricesFor(player);
		func_70932_a_(player);
		func_213707_a(player, func_145748_c_(), this.tofunianLevel);
	}

	public void func_70932_a_(@Nullable PlayerEntity player) {
		boolean flag = (func_70931_l_() != null && player == null);
		super.func_70932_a_(player);
		if (flag)
			func_213750_eg();
	}

	protected void func_213750_eg() {
		super.func_213750_eg();
		resetSpecialPrices();
	}

	private void resetSpecialPrices() {
		for (MerchantOffer merchantoffer : func_213706_dY())
			merchantoffer.func_222220_k();
	}

	public boolean func_223340_ej() {
		return true;
	}

	public void restock() {
		calculateDemandOfOffers();
		for (MerchantOffer merchantoffer : func_213706_dY())
			merchantoffer.func_222203_h();
		this.lastRestock = func_130014_f_().func_82737_E();
		this.restocksToday++;
	}

	private boolean allowedToRestock() {
		return (this.restocksToday == 0 || (this.restocksToday < 2 && func_130014_f_().func_82737_E() > this.lastRestock + 2400L));
	}

	public boolean canResetStock() {
		int m;
		long i = this.lastRestock + 12000L;
		long j = func_130014_f_().func_82737_E();
		boolean flag = (j > i);
		long k = func_130014_f_().func_72820_D();
		if (this.lastRestockDayTime > 0L) {
			long l = this.lastRestockDayTime / 24000L;
			long i1 = k / 24000L;
			m = flag | ((i1 > l) ? 1 : 0);
		}
		this.lastRestockDayTime = k;
		if (m != 0) {
			this.lastRestock = j;
			func_223718_eH();
		}
		return (allowedToRestock() && hasUsedOffer());
	}

	private void func_223718_eH() {
		resetOffersAndAdjustForDemand();
		this.restocksToday = 0;
	}

	private void resetOffersAndAdjustForDemand() {
		int i = 2 - this.restocksToday;
		if (i > 0)
			for (MerchantOffer merchantoffer : func_213706_dY())
				merchantoffer.func_222203_h();
		for (int j = 0; j < i; j++)
			calculateDemandOfOffers();
	}

	private boolean hasUsedOffer() {
		for (MerchantOffer merchantoffer : func_213706_dY()) {
			if (merchantoffer.func_226654_r_())
				return true;
		}
		return false;
	}

	private void calculateDemandOfOffers() {
		for (MerchantOffer merchantoffer : func_213706_dY())
			merchantoffer.func_222222_e();
	}

	private void recalculateSpecialPricesFor(PlayerEntity playerIn) {
		int i = getPlayerReputation(playerIn);
		if (i != 0)
			for (MerchantOffer merchantoffer : func_213706_dY())
				merchantoffer.func_222207_a(-MathHelper.func_76141_d(i * merchantoffer.func_222211_m()));
	}

	public void setOffers(MerchantOffers offersIn) {
		this.field_213724_bz = offersIn;
	}

	private boolean canLevelUp() {
		int i = this.tofunianLevel;
		return (VillagerData.func_221128_d(i) && this.xp >= VillagerData.func_221127_c(i));
	}

	private void increaseMerchantCareer() {
		setTofunainLevel(this.tofunianLevel + 1);
		func_213712_ef();
	}

	public void setTofunainLevel(int level) {
		this.tofunianLevel = level;
	}

	public int getTofunainLevel() {
		return this.tofunianLevel;
	}

	public int getPlayerReputation(PlayerEntity player) {
		return this.gossip.func_220921_a(player.func_110124_au(), gossipType -> true);
	}

	public void func_213281_b(CompoundNBT compound) {
		super.func_213281_b(compound);
		compound.func_74774_a("FoodLevel", this.foodLevel);
		compound.func_218657_a("Gossips", (INBT) this.gossip.func_234058_a_((DynamicOps) NBTDynamicOps.field_210820_a).getValue());
		compound.func_74768_a("Xp", this.xp);
		compound.func_74768_a("Level", this.tofunianLevel);
		compound.func_74772_a("LastRestock", this.lastRestock);
		compound.func_74772_a("LastGossipDecay", this.lastGossipDecay);
		compound.func_74768_a("RestocksToday", this.restocksToday);
		if (this.tofunainHome != null)
			compound.func_218657_a("TofunianHome", (INBT) NBTUtil.func_186859_a(this.tofunainHome));
		if (this.tofunainJobBlock != null)
			compound.func_218657_a("TofunianJobBlock", (INBT) NBTUtil.func_186859_a(this.tofunainJobBlock));
		compound.func_74778_a("Roles", getRole().name());
	}

	public void func_70037_a(CompoundNBT compound) {
		super.func_70037_a(compound);
		if (compound.func_150297_b("Offers", 10))
			this.field_213724_bz = new MerchantOffers(compound.func_74775_l("Offers"));
		if (compound.func_150297_b("FoodLevel", 1))
			this.foodLevel = compound.func_74771_c("FoodLevel");
		ListNBT listnbt = compound.func_150295_c("Gossips", 10);
		this.gossip.func_234057_a_(new Dynamic((DynamicOps) NBTDynamicOps.field_210820_a, listnbt));
		if (compound.func_150297_b("Xp", 3))
			this.xp = compound.func_74762_e("Xp");
		if (compound.func_74764_b("Level"))
			this.tofunianLevel = compound.func_74762_e("Level");
		this.lastGossipDecay = compound.func_74763_f("LastGossipDecay");
		this.lastRestock = compound.func_74763_f("LastRestock");
		this.restocksToday = compound.func_74762_e("RestocksToday");
		if (compound.func_74764_b("TofunianHome"))
			this.tofunainHome = NBTUtil.func_186861_c(compound.func_74775_l("TofunianHome"));
		if (compound.func_74764_b("TofunianJobBlock"))
			this.tofunainJobBlock = NBTUtil.func_186861_c(compound.func_74775_l("TofunianJobBlock"));
		if (compound.func_74764_b("Roles"))
			setRole(Roles.get(compound.func_74779_i("Roles")));
		func_98053_h(true);
	}

	public int func_213708_dV() {
		return this.xp;
	}

	protected void func_175445_a(ItemEntity p_175445_1_) {
		ItemStack itemstack = p_175445_1_.func_92059_d();
		if (func_230293_i_(itemstack)) {
			Inventory inventory = func_213715_ed();
			boolean flag = inventory.func_233541_b_(itemstack);
			if (!flag)
				return;
			func_233630_a_(p_175445_1_);
			func_71001_a((Entity) p_175445_1_, itemstack.func_190916_E());
			ItemStack itemstack1 = inventory.func_174894_a(itemstack);
			if (itemstack1.func_190926_b()) {
				p_175445_1_.func_70106_y();
			} else {
				itemstack.func_190920_e(itemstack1.func_190916_E());
			}
		}
	}

	public boolean func_213743_em() {
		return (this.foodLevel + countFoodPointsInInventory() >= 12 && func_70874_b() == 0);
	}

	private boolean hungry() {
		return (this.foodLevel < 12);
	}

	private void eatUntilFull() {
		if (hungry() && countFoodPointsInInventory() != 0)
			for (int i = 0; i < func_213715_ed().func_70302_i_(); i++) {
				ItemStack itemstack = func_213715_ed().func_70301_a(i);
				if (!itemstack.func_190926_b()) {
					Integer integer = FOOD_POINTS.get(itemstack.func_77973_b());
					if (integer != null) {
						int j = itemstack.func_190916_E();
						for (int k = j; k > 0; k--) {
							this.foodLevel = (byte) (this.foodLevel + integer.intValue());
							func_213715_ed().func_70298_a(i, 1);
							if (!hungry())
								return;
						}
					}
				}
			}
	}

	public boolean func_230293_i_(ItemStack p_230293_1_) {
		Item item = p_230293_1_.func_77973_b();
		return (WANTED_ITEMS.contains(item) && func_213715_ed().func_233541_b_(p_230293_1_));
	}

	public boolean hasExcessFood() {
		return (countFoodPointsInInventory() >= 32);
	}

	public boolean wantsMoreFood() {
		return (countFoodPointsInInventory() < 24);
	}

	public boolean hasFarmSeeds() {
		return func_213715_ed().func_213902_a((Set) ImmutableSet.of(TofuItems.SEEDS_SOYBEANS));
	}

	private int countFoodPointsInInventory() {
		Inventory inventory = func_213715_ed();
		return FOOD_POINTS.entrySet().stream().mapToInt(p_226553_1_ -> inventory.func_213901_a((Item) p_226553_1_.getKey()) * ((Integer) p_226553_1_.getValue()).intValue())

				.sum();
	}

	public void cookingFood() {
		for (int i = 0; i < func_213715_ed().func_70302_i_(); i++) {
			ItemStack itemstack = func_213715_ed().func_70301_a(i);
			if (!itemstack.func_190926_b() &&
					itemstack.func_77973_b() == TofuItems.SEEDS_SOYBEANS) {
				func_213715_ed().func_70298_a(i, 1);
				cookResult();
			}
		}
	}

	private void cookResult() {
		func_213715_ed().func_174894_a(new ItemStack((IItemProvider) TofuItems.TOFUGRILLED));
	}

	protected void func_213712_ef() {
		Int2ObjectMap<VillagerTrades.ITrade[]> int2objectmap = (Int2ObjectMap<VillagerTrades.ITrade[]>) TofuTrades.TOFUNIAN_TRADE.get(getRole());
		if (int2objectmap != null && !int2objectmap.isEmpty()) {
			VillagerTrades.ITrade[] avillagertrades$itrade = (VillagerTrades.ITrade[]) int2objectmap.get(this.tofunianLevel);
			if (avillagertrades$itrade != null) {
				MerchantOffers merchantoffers = func_213706_dY();
				func_213717_a(merchantoffers, avillagertrades$itrade, 2);
			}
		}
	}

	public void func_70636_d() {
		func_82168_bl();
		super.func_70636_d();
	}

	public void func_70071_h_() {
		super.func_70071_h_();
		tickGossip();
	}

	private void tickGossip() {
		long i = func_130014_f_().func_82737_E();
		if (this.lastGossipDecay == 0L) {
			this.lastGossipDecay = i;
		} else if (i >= this.lastGossipDecay + 24000L) {
			this.gossip.func_223538_b();
			this.lastGossipDecay = i;
		}
	}

	public GossipManager getGossip() {
		return this.gossip;
	}

	public void setGossips(INBT gossip) {
		this.gossip.func_234057_a_(new Dynamic((DynamicOps) NBTDynamicOps.field_210820_a, gossip));
	}

	public void func_70604_c(@Nullable LivingEntity p_70604_1_) {
		if (p_70604_1_ != null && this.level instanceof ServerWorld) {
			((ServerWorld) this.level).func_217489_a(IReputationType.field_221031_c, (Entity) p_70604_1_, this);
			if (func_70089_S() && p_70604_1_ instanceof PlayerEntity)
				this.level.func_72960_a((Entity) this, (byte) 13);
		}
		super.func_70604_c(p_70604_1_);
	}

	public void func_70645_a(DamageSource cause) {
		super.func_70645_a(cause);
	}

	@OnlyIn(Dist.CLIENT)
	public void func_70103_a(byte p_70103_1_) {
		if (p_70103_1_ == 12) {
			func_213718_a((IParticleData) ParticleTypes.field_197633_z);
		} else if (p_70103_1_ == 13) {
			func_213718_a((IParticleData) ParticleTypes.field_197609_b);
		} else if (p_70103_1_ == 14) {
			func_213718_a((IParticleData) ParticleTypes.field_197632_y);
		} else if (p_70103_1_ == 42) {
			func_213718_a((IParticleData) ParticleTypes.field_218422_X);
		} else {
			super.func_70103_a(p_70103_1_);
		}
	}

	@Nullable
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return (AgeableEntity) TofuEntityTypes.TOFUNIAN.func_200721_a((World) p_241840_1_);
	}

	public void func_213739_a(IReputationType p_213739_1_, Entity p_213739_2_) {
		if (p_213739_1_ == IReputationType.field_221029_a) {
			this.gossip.func_220916_a(p_213739_2_.func_110124_au(), GossipType.MAJOR_POSITIVE, 20);
			this.gossip.func_220916_a(p_213739_2_.func_110124_au(), GossipType.MINOR_POSITIVE, 25);
		} else if (p_213739_1_ == IReputationType.field_221033_e) {
			this.gossip.func_220916_a(p_213739_2_.func_110124_au(), GossipType.TRADING, 2);
		} else if (p_213739_1_ == IReputationType.field_221031_c) {
			this.gossip.func_220916_a(p_213739_2_.func_110124_au(), GossipType.MINOR_NEGATIVE, 25);
		} else if (p_213739_1_ == IReputationType.field_221032_d) {
			this.gossip.func_220916_a(p_213739_2_.func_110124_au(), GossipType.MAJOR_NEGATIVE, 25);
		}
	}

	public enum Roles implements IExtensibleEnum {
		TOFUCOOK, TOFUSMITH, SOYWORKER, TOFUNIAN;

		private static final Map<String, Roles> lookup;

		static {
			lookup = (Map<String, Roles>) Arrays.<Roles>stream(values()).collect(Collectors.toMap(Enum::name, p_220362_0_ -> p_220362_0_));
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
