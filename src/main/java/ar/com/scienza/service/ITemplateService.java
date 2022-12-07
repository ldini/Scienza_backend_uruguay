package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.TemplateRequestDto;
import ar.com.scienza.dto.TemplateResponseDto;
import ar.com.scienza.dto.TemplateResultResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface ITemplateService {

	public List<TemplateResultResponseDto> getTemplateList(String token, Long sapId, String deviceType) throws ServiceException;
	
	public TemplateResponseDto getTemplate(Integer templateId, String token, Long sapId, String deviceType) throws ServiceException;
	
	public void createTemplate(TemplateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
	public void updateTemplate(TemplateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
	public void deleteTemplate(TemplateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
}
