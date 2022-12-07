package ar.com.scienza.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

public class CalendarUtil {

	/**
	 * Obtiene los anios de forma descendiente
	 * 
	 * @return
	 */
	public static List<Integer> getYearDesc() {
		
		Calendar now = Calendar.getInstance();
		
		return getYearDesc(now.get(Calendar.YEAR)-10);
	}
	
	
	/**
	 * Obtiene los anios de forma descendiente desde un a�o determinado
	 * 
	 * @return
	 */
	public static List<Integer> getYearDesc(int fromYear) {
		
		List<Integer> anios = new ArrayList<Integer>();
		
		Calendar now = Calendar.getInstance(); 
		
		for(int i = now.get(Calendar.YEAR); i >= fromYear; i--) {
			anios.add(i);
		} 
		
		return anios;
	}

	
	/**
	 * Obtiene la edad de una persona a partir de la fecha de nacimiento
	 * 
	 * @param fechaNacimiento
	 * @return
	 */
	public static int getAge(Date fechaNacimiento) {

	    Calendar today = Calendar.getInstance();
	    Calendar birthDate = Calendar.getInstance();

	    int age = 0;

	    birthDate.setTime(fechaNacimiento);
	    if (birthDate.after(today)) {
	        throw new IllegalArgumentException("Can't be born in the future");
	    }

	    age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

	    // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year   
	    if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
	            (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
	        age--;

	     // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
	    }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
	              (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
	        age--;
	    }

	    return age;
	}

	
	/**
	 * Obtiene la fecha a mostrar en un chat
	 * 
	 * @param fechaNacimiento
	 * @return
	 */
	public static String getChatDate(Date date) {
		
		if(date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date yesterday = calendar.getTime();
		
		SimpleDateFormat sdf;
		
		if(DateUtils.isSameDay(today, date)) {
			sdf = new SimpleDateFormat("HH:mm");
		}
		else if(DateUtils.isSameDay(yesterday, date)) {
			sdf = new SimpleDateFormat("'Ayer' HH:mm");
		}
		else {
			sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		}
		
		return sdf.format(date);
	}


	/**
	 * Determina si la hora actual es fuera de horario
	 * @return
	 */
	public static boolean outOfWork() {
		
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return true;
		}
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(!(hour >= 9 && hour < 18)) {
			return true;
		}
		
		return false;
	}
}
