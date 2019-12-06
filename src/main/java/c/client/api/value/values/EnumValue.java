package c.client.api.value.values;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cookiedragon234 06/Dec/2019
 */
@SuppressWarnings("unchecked")
public class EnumValue<T extends Enum> extends SelectableStringValue
{
	protected final Class<? extends Enum> enumType;
	
	public EnumValue(String name, T defaultValue)
	{
		super(
			name,
			defaultValue.name(),
			Arrays.stream(defaultValue.getClass().getEnumConstants())
				.map(Enum::toString)
				.collect(Collectors.toCollection(HashSet::new))
		);
		
		this.enumType = defaultValue.getClass();
	}
	
	public void setValue(T value)
	{
		this.setValue(value.toString());
	}
	
	public T getEnumVal()
	{
		String val = this.getValue();
		
		return Objects.requireNonNull(
			(T)
				Arrays.stream(enumType.getEnumConstants())
					.filter(enuM -> enuM.toString().equals(val))
					.findFirst().orElseThrow(RuntimeException::new)
		);
	}
}
