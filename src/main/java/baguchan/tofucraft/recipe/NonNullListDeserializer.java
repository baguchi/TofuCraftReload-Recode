package baguchan.tofucraft.recipe;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class NonNullListDeserializer implements JsonDeserializer<NonNullList<?>> {
	private static final NonNullListDeserializer INSTANCE = new NonNullListDeserializer();

	private NonNullListDeserializer() {
	}

	@Override
	public NonNullList<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Type valueType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];

		NonNullList<Object> list = NonNullList.create();
		for (JsonElement item : json.getAsJsonArray()) {
			list.add(context.deserialize(item, valueType));
		}
		return list;
	}

	public static NonNullListDeserializer getInstance() {
		return INSTANCE;
	}

}
