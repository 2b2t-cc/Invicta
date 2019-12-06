package c.client.api.value;

/**
 * @author cookiedragon234 06/Dec/2019
 */
public abstract class Value<T>
{
	private final String name;
	private final T defaultVal;
	private T value;
	
	public Value(String name, T defaultVal)
	{
		this.name = name;
		this.value = this.defaultVal = defaultVal;
	}
	
	public String getName()
	{
		return name;
	}
	
	public T getDefaultVal()
	{
		return defaultVal;
	}
	
	public T getValue()
	{
		return value;
	}
	
	public void setValue(T value)
	{
		this.value = value;
	}
}
