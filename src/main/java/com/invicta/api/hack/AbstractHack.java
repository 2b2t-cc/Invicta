package com.invicta.api.hack;

import com.invicta.api.util.keys.Key;
import com.invicta.api.value.Value;
import com.invicta.api.value.values.BooleanValue;
import com.invicta.api.value.values.KeyValue;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

/**
 * @author cookiedragon234 06/Dec/2019
 */
public abstract class AbstractHack
{
	protected final String name;
	protected final String description;
	protected final HackCategory category;
	private Value<Boolean> enabled = new BooleanValue("Enabled", false);
	private Value<Key> keyBind = new KeyValue("Bind");
	
	public AbstractHack()
	{
		Hack annotation = this.getClass().getAnnotation(Hack.class);
		Objects.requireNonNull(annotation, 					"Annotation was missing");
		Objects.requireNonNull(annotation.name(), 			"Annotation name was missing");
		Objects.requireNonNull(annotation.description(),	"Annotation description was missing");
		Objects.requireNonNull(annotation.category(), 		"Annotation category was missing");
		
		this.name = annotation.name();
		this.description = annotation.description();
		this.category = annotation.category();
		
		enabled.addCallback((oldVal, newVal) ->
		{
			if(newVal)
				onEnabled();
			else
				onDisabled();
		});
	}
	
	protected void onEnabled(){}
	protected void onDisabled(){}
	
	/**
	 * @return A user friendly name for this hack
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Used by guis etc to show extra metadata with the name
	 * @return The name with optional added metadata
	 */
	public String getNameWithMetadata()
	{
		return this.name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public HackCategory getCategory()
	{
		return this.category;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface Hack
	{
		@Nonnull String name();
		@Nonnull String description();
		@Nonnull HackCategory category();
	}
}
