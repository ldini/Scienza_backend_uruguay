package ar.com.scienza.utils;

import java.util.List;

public class StringUtil {

	/**
	 * Reduce una palabra determinada a la cantiada de caracteres
	 * 
	 * @param value
	 * @param charancters
	 * @return
	 */
	public static String reduceValue(String value, int charancters) {
		
		String newValue = new String();
		int count = 0;
		
		for(String subValue : value.split("\\s+")) 
		{
			if(subValue.length() < (charancters-count)) {
				newValue += subValue + " ";
				count += subValue.length() + 1;
			}
			else {
				newValue += subValue.substring(0, (charancters-count-2)) + "..";
				break;
			}
		}
		
		return newValue.trim();
	}
	
	
	/**
	 * Une los valores separando por coma y agrega un conector Y al ultimo elemento
	 * 
	 * @param values
	 * @return
	 */
	public static String joinValues(List<String> values) {
		
		String message = null;
		
		switch (values.size()) 
		{
			case 1:
				message = values.get(0);
				break;
			
			case 2:
				message = values.get(0) + " y " + values.get(1);
				break;

			default:
				int valuesSize = values.size();
				String value = values.get(0);
				for(String valueAux : values.subList(1, valuesSize-2)) {
					value += ", " + valueAux;
				}
				value += " y " + values.get(valuesSize-1);
				message = value;
				break;
		}
		
		return message;
	}
	
	
	/**
	 * Filtra solo numeros de un string
	 * @param input
	 * @return
	 */
	public static String stripNonDigits(final CharSequence input){
		final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 0; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c > 47 && c < 58){
	            sb.append(c);
	        }
	    }
	    return sb.toString();
	}
}
