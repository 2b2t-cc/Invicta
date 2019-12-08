package com.invicta.api.util;


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
	
	/**
	 * Loads the classloader with the given class types
	 * @param clazzes Class types to initialise
	 * @return This class loader
	 */
	@SafeVarargs
	public final SimpleClassLoader<T> build(Class<? extends T>... clazzes)
	{
		this.clazzes = clazzes;
		return this;
	}
	
	/**
	 * Initialises all the classes in this class loader
	 * @param successfulInitialisationCallback Called for each class upon successful initialisation
	 * @param unsuccessfulInitialisationCallback Called for each class upon unsuccessful initialisation
	 * @param throwableOnErrorSupplier Retrieves a throwable to throw upon unsuccessful initialisation
	 * @return This class loader
	 */
	public SimpleClassLoader<T> initialise(
		Consumer<T> successfulInitialisationCallback,
		Consumer<Class<? extends T>> unsuccessfulInitialisationCallback,
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
				unsuccessfulInitialisationCallback.accept(clazz);
				throwableOnErrorSupplier.apply(clazz, e).printStackTrace();
			}
		}
		
		return this;
	}
}
