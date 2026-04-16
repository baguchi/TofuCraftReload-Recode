package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.entity.sensor.FukumameThrowerSpecificSensor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuSensorTypes {
	public static final DeferredRegister<SensorType<?>> SENSOR_TYPE = DeferredRegister.create(BuiltInRegistries.SENSOR_TYPE, TofuCraftReload.MODID);

	public static final DeferredHolder<SensorType<?>, SensorType<FukumameThrowerSpecificSensor>> FUKUMAME_THROWER = SENSOR_TYPE.register("fukumame_thrower_sensor", () -> new SensorType(FukumameThrowerSpecificSensor::new));
}
