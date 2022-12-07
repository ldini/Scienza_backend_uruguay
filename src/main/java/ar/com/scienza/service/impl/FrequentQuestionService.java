package ar.com.scienza.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.FrequentQuestionDetailResponseDto;
import ar.com.scienza.dto.FrequentQuestionResponseDto;
import ar.com.scienza.entity.PreguntaFrecuente;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.PreguntaFrecuenteRepository;
import ar.com.scienza.service.IFrequentQuestionlService;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class FrequentQuestionService implements IFrequentQuestionlService {

	@Autowired
	private PreguntaFrecuenteRepository preguntaFrecuenteRepository;


	@Override
	public List<FrequentQuestionResponseDto> getFrequentQuestionList(String token, Long sapId, String deviceType) throws ServiceException {

		List<FrequentQuestionResponseDto> responseList = new ArrayList<FrequentQuestionResponseDto>();
		
		try 
		{
			for(String categoria : preguntaFrecuenteRepository.findCategorias())
			{
				FrequentQuestionResponseDto response = new FrequentQuestionResponseDto();
				response.setCategoria(categoria);
				responseList.add(response);
				
				for(PreguntaFrecuente preguntaFrecuente : preguntaFrecuenteRepository.findAllByCategoriaAndFechaDeleteIsNull(categoria))
				{
					FrequentQuestionDetailResponseDto responseDetail = new FrequentQuestionDetailResponseDto();
					responseDetail.setPregunta(preguntaFrecuente.getPregunta());
					responseDetail.setRespuesta(preguntaFrecuente.getRespuesta());
					response.getPreguntaList().add(responseDetail);
				}
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener preguntas frecuentes");
		}
		
		return responseList;
	}
}
