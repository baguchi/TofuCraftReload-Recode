package baguchi.tofucraft.client.render.item.properties;

import baguchi.tofucraft.registry.TofuDataComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;


public record TFProperty() implements ConditionalItemModelProperty {
	public static final MapCodec<TFProperty> MAP_CODEC = MapCodec.unit(new TFProperty());

	@Override
	public boolean get(ItemStack p_388323_, @Nullable ClientLevel p_388659_, @Nullable LivingEntity p_386950_, int p_387664_, ItemDisplayContext p_389573_) {
		return p_388323_.has(TofuDataComponents.TF_ENERGY_DATA) && p_388323_.get(TofuDataComponents.TF_ENERGY_DATA).storeTF() > 0;
	}

	@Override
	public MapCodec<TFProperty> type() {
		return MAP_CODEC;
	}
}
