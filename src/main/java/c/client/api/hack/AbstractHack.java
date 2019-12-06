package c.client.api.hack;

import c.client.api.util.keys.Key;
import c.client.api.value.Value;

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
	private Value<Boolean> enabled;
	private Value<Key> keyBind;
	
	public AbstractHack(String name)
	{
		Hack annotation = this.getClass().getAnnotation(Hack.class);
		Objects.requireNonNull(annotation, 					"Annotation was missing");
		Objects.requireNonNull(annotation.name(), 			"Annotation name was missing");
		Objects.requireNonNull(annotation.description(),	"Annotation description was missing");
		Objects.requireNonNull(annotation.category(), 		"Annotation category was missing");
		
		this.name = annotation.name();
		this.description = annotation.description();
		this.category = annotation.category();
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
