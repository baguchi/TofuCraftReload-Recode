package baguchi.tofucraft.mixin.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Gui.class)
public interface GuiAccessor {
	@Accessor("random")
	RandomSource tofucraft$getRandom();

	@Accessor("lastHealthTime")
	long tofucraft$getLastHealthTime();

	@Accessor("healthBlinkTime")
	long tofucraft$getHealthBlinkTime();
}