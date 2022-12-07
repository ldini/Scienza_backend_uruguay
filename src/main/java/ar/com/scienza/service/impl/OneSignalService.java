package ar.com.scienza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.OneSignalRequestDto;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.service.IOneSignalService;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class OneSignalService implements IOneSignalService {

	@Autowired
	private SesionRepository sesionRepository;


	@Override
	public void updatePlayerId(OneSignalRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{
			List<Sesion> oldSessions = sesionRepository.findAllByPlayerId(request.getPlayerId());
			for(Sesion session : oldSessions)
			{
				session.setPlayerId(null);
				session.updateOnRepository(sesionRepository);
			}
			
			Sesion sesion = sesionRepository.findByToken(token);		
			sesion.setPlayerId(request.getPlayerId());
			sesion.updateOnRepository(sesionRepository);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al actualizar playerId");
		}	
	}
}
