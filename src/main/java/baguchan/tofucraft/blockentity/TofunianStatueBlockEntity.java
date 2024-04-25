package baguchan.tofucraft.blockentity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.TofunianStatueBlock;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuParticleTypes;
import baguchan.tofucraft.registry.TofuTags;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class TofunianStatueBlockEntity extends SyncedBlockEntity {
	public static final ResourceKey<LootTable> LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, new ResourceLocation(TofuCraftReload.MODID, "gameplay/tofunian_statue"));
	private int processTick;
	private long cooldown;
	private float happyScale;
	private long happyTime;

	public TofunianStatueBlockEntity(BlockPos pos, BlockState state) {
		super(TofuBlockEntitys.TOFUNIAN_STATUE.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState blockstate, TofunianStatueBlockEntity statue) {
		if (!level.isClientSide() && statue.cooldown <= level.getGameTime()) {
			BlockPos platePos = pos.relative(blockstate.getValue(TofunianStatueBlock.FACING));
			BlockEntity blockEntity = level.getBlockEntity(platePos);
			if (blockEntity instanceof FoodPlateBlockEntity foodPlate) {
				if (foodPlate.getStoredItem().is(TofuTags.Items.STATUE_HAPPY)) {
					if (++statue.processTick > 200) {
						foodPlate.removeItem();
						LootTable loottable = level.getServer().reloadableRegistries().getLootTable(LOOT_TABLE);

						LootParams lootparams = (new LootParams.Builder((ServerLevel) level)).withLuck(statue.happyScale).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(platePos)).create(LootContextParamSets.CHEST);
						ObjectArrayList<ItemStack> objectarraylist = loottable.getRandomItems(lootparams);
						foodPlate.addAllItem(objectarraylist.get(0));
						statue.processTick = 0;
						statue.cooldown = level.getGameTime() + 12000 + level.random.nextInt(12000);
						statue.happyTime = statue.getCooldown() + 24000;
						statue.happyScale = Mth.clamp(statue.happyScale + 1F, 0F, 5F);
						level.playSound(null, platePos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS);
					} else {
						if (level.random.nextInt(4) == 0) {
							((ServerLevel) level).sendParticles(TofuParticleTypes.TOFU_PORTAL.get(), platePos.getX() + 0.5F, platePos.getY() + 0.1F, platePos.getZ() + 0.5F, 1, 0D, 0D, 0D, 1.0F);
						}
					}
				} else {
					if (statue.happyTime < level.getGameTime()) {
						statue.happyTime = 12000 + level.getGameTime();
						statue.happyScale = Mth.clamp(statue.happyScale - 0.25F, 0F, 5F);
					}
					statue.processTick = 0;
				}
			}
		}
	}

	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
		super.loadAdditional(compound, provider);
		this.setProcessTick(compound.getInt("ProcessTick"));
		this.setCooldown(compound.getLong("CooldownAt"));
		this.setHappyScale(compound.getFloat("HappyScale"));
		this.setHappyTime(compound.getLong("HappyTimeAt"));
	}

	@Override
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
		super.saveAdditional(compound, provider);
		compound.putInt("ProcessTick", getProcessTick());
		compound.putLong("CooldownAt", getCooldown());
		compound.putFloat("HappyScale", getHappyScale());
		compound.putLong("HappyTimeAt", getHappyTime());
	}

	public int getProcessTick() {
		return processTick;
	}

	public void setProcessTick(int process) {
		this.processTick = process;
	}

	public void setHappyScale(float happyScale) {
		this.happyScale = happyScale;
	}

	public float getHappyScale() {
		return happyScale;
	}

	public void setHappyTime(long happyTime) {
		this.happyTime = happyTime;
	}

	public long getHappyTime() {
		return happyTime;
	}

	public long getCooldown() {
		return cooldown;
	}

	public void setCooldown(long cooldown) {
		this.cooldown = cooldown;
	}
}
