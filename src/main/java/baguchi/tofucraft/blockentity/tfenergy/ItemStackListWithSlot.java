package baguchi.tofucraft.blockentity.tfenergy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record ItemStackListWithSlot(int slot, List<ItemStack> stack) {
	public static final Codec<ItemStackListWithSlot> CODEC = RecordCodecBuilder.create((p_381569_) -> p_381569_.group(ExtraCodecs.UNSIGNED_BYTE.fieldOf("Slot").orElse(0).forGetter(ItemStackListWithSlot::slot), ItemStack.MAP_CODEC.codec().listOf().fieldOf("ItemStacks").forGetter(ItemStackListWithSlot::stack)).apply(p_381569_, ItemStackListWithSlot::new));

	public boolean isValidInContainer(int p_422158_) {
		return this.slot >= 0 && this.slot < p_422158_;
	}
}
