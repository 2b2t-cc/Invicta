package c.client.api.value.values;

import org.lwjgl.input.Keyboard;
import c.client.api.util.keys.Key;

import javax.annotation.Nullable;

/**
 * @author cookiedragon234 06/Dec/2019
 */
public class KeyValue extends EnumValue<Key>
{
	public KeyValue(String name)
	{
		this(name, Keyboard.KEY_NONE);
	}
	
	public KeyValue(String name, String defaultValue)
	{
		this(name, Key.fromName(defaultValue));
	}
	
	public KeyValue(String name, int defaultValue)
	{
		this(name, Key.fromCode(defaultValue));
	}
	
	public KeyValue(String name, Key defaultKey)
	{
		super(name, defaultKey);
	}
	
	public boolean isKeyDown()
	{
		return this.getEnumVal().isKeyDown();
	}
	
	public boolean hasChangedState()
	{
		return this.getEnumVal().hasChangedState();
	}
	
	@Nullable
	public Boolean hasBeenPressed()
	{
		return this.getEnumVal().hasBeenPressed();
	}
}
