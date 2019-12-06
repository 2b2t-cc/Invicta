package c.client.api.value.values;

import c.client.api.value.Value;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.lwjgl.input.Keyboard;

/**
 * @author cookiedragon234 06/Dec/2019
 */
public class KeyValue extends NumberValue<Integer>
{
	public KeyValue(String name)
	{
		this(name, Keyboard.KEY_NONE);
	}
	
	public KeyValue(String name, String defaultValue)
	{
		this(name, Keyboard.getKeyIndex(defaultValue));
	}
	
	public KeyValue(String name, int defaultValue)
	{
		super(name, defaultValue);
	}
	
	@Override
	public JsonObject addToObject(JsonObject jsonObject)
	{
		jsonObject.addProperty(this.getName(), Keyboard.getKeyName(this.getValue().intValue()));
		return jsonObject;
	}
	
	@Override
	public void retrieveFromObject(JsonObject jsonObject)
	{
		if(jsonObject.has(this.getName()))
		{
			JsonElement element = jsonObject.get(this.getName());
			
			try
			{
				this.setValue(element.getAsNumber());
			}
			catch(Exception ignored){}
		}
	}
}
