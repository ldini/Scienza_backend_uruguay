package ar.com.scienza.utils;

import ar.com.scienza.entity.Afiliado;

public class ParserUtil {

	/**
	 * Procesa un mensaje modificando los valores que pueden reemplazarse
	 * 
	 * @param value
	 * @param charancters
	 * @return
	 */
	public static String parseMessage(Afiliado afiliado, String message) {
		
		String newMessage = message;
		
		newMessage = newMessage.replace("${afiliado.nombre}", afiliado.getNombre());
		newMessage = newMessage.replace("${afiliado.apellido}", afiliado.getApellido());

		return newMessage;
	}
}
