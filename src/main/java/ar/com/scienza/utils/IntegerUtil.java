package ar.com.scienza.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;


public class IntegerUtil {

	/**
	 * Normaliza los decimales de un decimal
	 * 
	 * @param value
	 * @return
	 * @throws ParseException 
	 */
	public static Integer valueOf(String value) throws ParseException {
		
		if(value == null || value.length() == 0)
			return null;
		
		try
		{
			return Integer.valueOf(value);
		}
		catch(NumberFormatException e) 
		{
			// Parseo por decimales
			String valueAux = value.replaceAll(",","");
			
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator('.');
			String pattern = "#0.0#";
			
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);

			// parse the string
			return decimalFormat.parse(valueAux).intValue();
		}
	}
}
