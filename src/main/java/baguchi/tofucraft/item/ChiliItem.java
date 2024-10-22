package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuAdvancements;
import baguchi.tofucraft.registry.TofuEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.entity.EntityTypeTest;

import java.util.List;

public class ChiliItem extends Item {
	public ChiliItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (entity.tickCount % 20 == 0) {
			List<Warden> wardenList = entity.level().getEntities(EntityTypeTest.forClass(Warden.class), entity.getBoundingBox().inflate(6.0F), EntitySelector.NO_SPECTATORS);

			for (Warden warden : wardenList) {
				warden.addEffect(new MobEffectInstance(TofuEffects.COUGH, 600));
				if (entity.getOwner() instanceof ServerPlayer) {
					TofuAdvancements.CHILI_DISTRACTION.get().trigger((ServerPlayer) entity.getOwner());
				}
			}
		}

		return super.onEntityItemUpdate(stack, entity);
	}
}
