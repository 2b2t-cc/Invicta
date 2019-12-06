package c.client.api;

import com.google.gson.JsonObject;

/**
 * @author cookiedragon234 06/Dec/2019
 */
public interface ISerializable
{
	JsonObject addToObject(JsonObject jsonObject);
	void retrieveFromObject(JsonObject jsonObject);
}
