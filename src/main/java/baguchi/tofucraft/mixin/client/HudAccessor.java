package baguchi.tofucraft.mixin.client;

import net.minecraft.client.gui.Hud;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Hud.class)
public interface HudAccessor {
	@Accessor("random")
	RandomSource tofucraft$getRandom();

	@Accessor("lastHealthTime")
	long tofucraft$getLastHealthTime();

	@Accessor("healthBlinkTime")
	long tofucraft$getHealthBlinkTime();
}