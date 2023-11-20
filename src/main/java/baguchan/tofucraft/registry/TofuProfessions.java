package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TofuProfessions {
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, TofuCraftReload.MODID);

	public static final Supplier<VillagerProfession> TOFU_CRAFTSMAN = register("tofu_craftsman", TofuPoiTypes.TOFU_CRAFTSMAN, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LEATHERWORKER);


	private static Supplier<VillagerProfession> register(String p_219648_, ResourceKey<PoiType> p_219649_, ImmutableSet<Item> p_219650_, ImmutableSet<Block> p_219651_, @Nullable SoundEvent p_219652_) {
		return register(p_219648_, (p_238234_) -> {
			return p_238234_.is(p_219649_);
		}, (p_238237_) -> {
			return p_238237_.is(p_219649_);
		}, p_219650_, p_219651_, p_219652_);
	}

	private static Supplier<VillagerProfession> register(String p_219659_, Predicate<Holder<PoiType>> p_219660_, Predicate<Holder<PoiType>> p_219661_, ImmutableSet<Item> p_219662_, ImmutableSet<Block> p_219663_, @Nullable SoundEvent p_219664_) {
		return PROFESSIONS.register(p_219659_, () -> new VillagerProfession(p_219659_, p_219660_, p_219661_, p_219662_, p_219663_, p_219664_));
	}
}
