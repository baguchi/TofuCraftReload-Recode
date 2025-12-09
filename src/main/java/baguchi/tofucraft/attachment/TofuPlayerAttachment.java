package baguchi.tofucraft.attachment;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.TofuLearning;
import baguchi.tofucraft.network.AddLearningPacket;
import com.google.common.collect.Lists;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;
import java.util.Optional;

public class TofuPlayerAttachment implements ValueIOSerializable {
	private final List<Holder<TofuLearning>> learning = Lists.newArrayList();
	private boolean sync;

	public void addLearning(Holder<TofuLearning> learning, Player player) {
		this.learning.add(learning);
		if (!player.level().isClientSide()) {
			PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, new AddLearningPacket(player, learning.unwrap().left().get().identifier(), true));
		}
	}

	public void trackDiscoveries(Player player, AdvancementHolder advancement) {
		if (player instanceof ServerPlayer serverPlayer) {
			RegistryAccess registryAccess = serverPlayer.registryAccess();
			this.trackLearningEntries(serverPlayer, registryAccess, advancement);
			if (this.sync) {

				this.sync = false;
			}
		}
	}

	private void trackLearningEntries(ServerPlayer serverPlayer, RegistryAccess registryAccess, AdvancementHolder advancement) {
		Registry<TofuLearning> learnings = registryAccess.lookupOrThrow(TofuLearning.REGISTRY_KEY);
		for (Holder.Reference<TofuLearning> entry : learnings.listElements().toList()) {
			if (entry.value().learning_advancement().isPresent() && advancement.id().equals(entry.value().learning_advancement().get()) && !this.learning.contains(entry)) {
				addLearning(entry, serverPlayer);
				this.sync = true;
			}
		}
	}

	public List<Holder<TofuLearning>> getLearning() {
		return learning;
	}

	@Override
	public void serialize(ValueOutput output) {
		ListTag listnbt = new ListTag();

		CompoundTag tag = new CompoundTag();
		for (int i = 0; i < learning.size(); i++) {
			CompoundTag compoundTag = new CompoundTag();
			Identifier resourceLocation = TofuCraftReload.registryAccess().lookupOrThrow(TofuLearning.REGISTRY_KEY).getKey(learning.get(i).value());
			if (resourceLocation != null) {
				compoundTag.putString("Learn", resourceLocation.toString());
			}
			listnbt.add(compoundTag);
		}

		tag.put("LearnData", listnbt);
		output.store("Learned", CompoundTag.CODEC, tag);
	}

	@Override
	public void deserialize(ValueInput nbt) {
		CompoundTag compoundTag = nbt.read("Learned", CompoundTag.CODEC).orElse(null);
		ListTag list = compoundTag.getListOrEmpty("LearnData");
		learning.clear();
		for (int i = 0; i < list.size(); ++i) {
			CompoundTag compoundnbt = list.getCompoundOrEmpty(i);

			if (compoundnbt.contains("Learn")) {
				Optional<Holder.Reference<TofuLearning>> learn = TofuCraftReload.registryAccess().lookupOrThrow(TofuLearning.REGISTRY_KEY).get(Identifier.parse(compoundnbt.getStringOr("Learn", "")));
				//check mob enchant is not null
				learn.ifPresent(learning::add);
			}
		}
	}
}