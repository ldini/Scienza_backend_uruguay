package ar.com.scienza.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class EnumeratorUtil {

	public static <T extends Enum<T>> T valueOf(Class<T> enumeration, String codigo) {

		if(codigo == null)
			return null;
		
		try 
		{
			Method method = enumeration.getMethod("getCodigo");
			
			for(T enumValue : enumeration.getEnumConstants()) {
						
					if(method.invoke(enumValue).toString().equals(codigo))
					
					    return enumValue;
		    }
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	
	public static <T extends Enum<T>> T valueDescriptionOf(Class<T> enumeration, String descripcion) {

		if(descripcion == null)
			return null;
		
		try 
		{
			Method method = enumeration.getMethod("getDescripcion");
			
			for(T enumValue : enumeration.getEnumConstants()) {
						
					if(method.invoke(enumValue).toString().equals(descripcion))
					
					    return enumValue;
		    }
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
}
