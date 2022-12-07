package ar.com.scienza.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class BigDecimalUtil {

	/**
	 * Normaliza los decimales de un decimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal normalize(BigDecimal value) {
		
		if(value == null)
			return null;
		
		BigDecimal newValue = value.stripTrailingZeros();
		
		if(newValue.scale() < 0)
			newValue = newValue.setScale(0);
		
		return newValue;
	}

	/**
	 * Normaliza los decimales de un decimal
	 * 
	 * @param value
	 * @return
	 * @throws ParseException 
	 */
	public static BigDecimal valueOf(String value) throws ParseException {
		
		if(value == null)
			return null;
		
		String valueAux = value.replaceAll(",","");
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		String pattern = "#0.0#";
		
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		// parse the string
		BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(valueAux);	
		bigDecimal = normalize(bigDecimal);
		
		if(bigDecimal.compareTo(BigDecimal.ZERO) == 0)
			return null;
		else
			return bigDecimal;
	}
}
