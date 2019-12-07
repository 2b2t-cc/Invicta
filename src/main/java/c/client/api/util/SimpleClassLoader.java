package c.client.api.util;


import java.util.Set;
import java.util.HashSet;
import java.util.function.BiFunction;
import java.util.function.Consumer;
/**
 * @author cookiedragon234 09/Oct/2019
 * Updated by cookiedragon234 on 07/Dec/2019 adding support for generics
 */
public class SimpleClassLoader<T>
{
	public SimpleClassLoader()
	{
	}
	
	private Class<? extends T>[] clazzes;
	private Set<Class<? extends T>> erroredClasses;
	
	@SafeVarargs
	public final SimpleClassLoader<T> build(Class<? extends T>... clazzes)
	{
		this.clazzes = clazzes;
		this.erroredClasses = new HashSet<>(clazzes.length);
		return this;
	}
	
	public SimpleClassLoader<T> initialise(
		Consumer<T> successfulInitialisationCallback,
		Consumer<Class<? extends T>> unsucessfullInitialisationCallback,
		BiFunction<Class<? extends T>, Throwable, Throwable> throwableOnErrorSupplier
	)
	{
		for(Class<? extends T> clazz: clazzes)
		{
			try
			{
				successfulInitialisationCallback.accept(clazz.newInstance());
			}
			catch(Exception e)
			{
				uncessfullInitialisationCallback.accept(clazz);
				throwableOnErrorSupplier.apply(clazz, e).printStackTrace();
			}
		}
		
		return this;
	}
	
	public Set<Class<? extends T>> getErroredClasses()
	{
		return erroredClasses;
	}
}
