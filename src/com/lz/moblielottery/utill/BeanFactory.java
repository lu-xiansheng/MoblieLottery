package com.lz.moblielottery.utill;

import java.io.IOException;
import java.util.Properties;

import android.util.Property;

public class BeanFactory {
	protected static Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError();		
		}
		
	}
	public static<T> T getImpl(Class<T> clazz) {
		String simplename = clazz.getSimpleName(); 
	
		String property = properties.getProperty(simplename);
		try {
			T  t = (T) Class.forName(property).newInstance();
			return t;
		} catch (Exception e) {  
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
}
