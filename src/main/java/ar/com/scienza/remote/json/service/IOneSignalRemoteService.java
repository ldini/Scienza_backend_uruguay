package ar.com.scienza.remote.json.service;

import java.util.List;

import ar.com.scienza.entity.Sesion;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.remote.exception.RemoteServiceException;


public interface IOneSignalRemoteService {

	public void sendNotification(List<Sesion> sesionList, String title, String message, TipoNotificacionEnum type, Integer elementId, Long sapId) throws RemoteServiceException;

}
