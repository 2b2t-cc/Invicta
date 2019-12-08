package com.invicta.api;

import com.google.gson.JsonObject;

/**
 * Used to serialize a class to/from a json representation
 * @author cookiedragon234 06/Dec/2019
 */
public interface ISerializable
{
	JsonObject addToObject(JsonObject jsonObject);
	void retrieveFromObject(JsonObject jsonObject);
}
