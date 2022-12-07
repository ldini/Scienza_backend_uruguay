package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.FrequentQuestionResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IFrequentQuestionlService {

	public List<FrequentQuestionResponseDto> getFrequentQuestionList(String token, Long sapId, String deviceType) throws ServiceException;
	
}
