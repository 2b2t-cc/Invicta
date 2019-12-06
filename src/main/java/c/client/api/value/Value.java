package c.client.api.value;

import c.client.api.ISerializable;
import com.google.gson.JsonObject;

/**
 * @author cookiedragon234 06/Dec/2019
 */
public abstract class Value<T> implements ISerializable
{
	protected final String name;
	protected final T defaultVal;
	protected T value;
	
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
	
	@Override
	public String toString()
	{
		return String.format("%s:%s:%s", this.name, this.value.getClass().getName(), this.value);
	}
}
