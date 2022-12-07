package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.EntityResponseDto;
import ar.com.scienza.dto.QueryTypeResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IEntityService {

	public List<EntityResponseDto> getCellCompanyList(String token, Long sapId, String deviceType) throws ServiceException;
	
	public List<EntityResponseDto> getHealthInsuranceList(String token, Long sapId, String deviceType) throws ServiceException;
	
	public List<EntityResponseDto> getLocationList(String token, Long sapId, String deviceType) throws ServiceException;

	public List<EntityResponseDto> getDocumentationTypeList(String token, Long sapId, String deviceType) throws ServiceException;

	public List<QueryTypeResponseDto> getQueryTypeList(String token, Long sapId, String deviceType) throws ServiceException;

}
