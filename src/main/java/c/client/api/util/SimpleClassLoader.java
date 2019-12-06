package c.client.api.util;

import net.minecraftforge.fml.common.FMLLog;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cookiedragon234 09/Oct/2019
 */
public class SimpleClassLoader
{
	public SimpleClassLoader()
	{
	}
	
	private Class[] clazzes;
	private Set<Class> erroredClasses;
	
	public SimpleClassLoader build(Class[] clazzes)
	{
		this.clazzes = clazzes;
		this.erroredClasses = new HashSet<>(clazzes.length);
		return this;
	}
	
	public SimpleClassLoader initialise()
	{
		for(Class clazz: clazzes)
		{
			try
			{
				clazz.newInstance();
			}
			catch(Exception e)
			{
				erroredClasses.add(clazz);
				FMLLog.log.info("Error initialising class " + clazz.getName());
				e.printStackTrace();
			}
		}
		
		return this;
	}
	
	public Set<Class> getErroredClasses()
	{
		return erroredClasses;
	}
}
