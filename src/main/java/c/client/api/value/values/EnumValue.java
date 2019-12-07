package c.client.api.value.values;

import c.client.api.value.Value;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cookiedragon234 06/Dec/2019
 */
@SuppressWarnings("unchecked")
public class EnumValue<T extends Enum> extends Value<Enum>
{
	protected final Class<? extends Enum> enumType;
	protected final Set<? extends Enum> options;
	
	public EnumValue(String name, T defaultValue)
	{
		super(
			name,
			defaultValue
		);
		
		this.enumType = defaultValue.getClass();
		options = Arrays.stream(defaultValue.getClass().getEnumConstants())
			.collect(Collectors.toCollection(HashSet::new));
	}
	
	@Override
	public JsonObject addToObject(JsonObject jsonObject)
	{
		return null;
	}
	
	@Override
	public void retrieveFromObject(JsonObject jsonObject)
	{
	
	}
}
