package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.*;
import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.exception.ServiceException;


public interface INotificationService {

	/**
	 * Permite generar un mensaje de notificación
	 * @param administrador
	 * @param afiliadoList
	 * @param title
	 * @param pushMessage
	 * @param message
	 * @param type
	 * @throws ServiceException
	 */
	public void createNotification(
		Administrador administrador, 
		List<Afiliado> afiliadoList, 
		String title, 
		String pushMessage, 
		String message, 
		TipoNotificacionEnum type,
		Integer entityId
	) throws ServiceException;
	

	/**
	 * Obtiene la lista de notificaciones
	 * @param token
	 * @param sapId
	 * @param deviceType
	 * @return
	 * @throws ServiceException
	 */
	public List<NotificationResultResponseDto> getNotificationList(String token, Long sapId, String deviceType) throws ServiceException;
	
	
	/**
	 * Devuelve el mensaje completo. Marca lectura en caso de ser la primera vez
	 * @param notificationId
	 * @param token
	 * @param sapId
	 * @param deviceType
	 * @return
	 * @throws ServiceException
	 */
	public NotificationResponseDto getNotification(Integer notificationId, String token, Long sapId, String deviceType, String version) throws ServiceException;

	public void deleteNotification(NotificationDeleteRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
	/**
	 * Busca los destinatarios de un mensaje determinado
	 * @param request
	 * @param token
	 * @param sapId
	 * @param deviceType
	 * @return
	 * @throws ServiceException
	 */
	public List<NotificationMessageResultResponseDto> getMessageSenderList(
		NotificationMessageFilterRequestDto request, 
		String token, 
		Long sapId, 
		String deviceType
	) throws ServiceException;

	
	/**
	 * Envia las notificaciones a los destinatarios
	 * @param request
	 * @param token
	 * @param sapId
	 * @param deviceType
	 * @throws ServiceException
	 */
	public void sendNotification(NotificationMessageSendRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	List<NotificationMessageSentListDto> getSentNotificationsHistory(String token, Long sapId, String deviceType) throws ServiceException;

    NotificationMessageSentDto getSentNotificationDetail(Integer messageId, String token, Long sapId, String deviceType, String version) throws ServiceException;
}
