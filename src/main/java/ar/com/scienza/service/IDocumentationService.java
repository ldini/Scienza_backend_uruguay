package ar.com.scienza.service;

import ar.com.scienza.dto.*;
import ar.com.scienza.enumerator.TipoDocumentacionEnum;
import ar.com.scienza.exception.ServiceException;

import java.util.List;


public interface IDocumentationService {

/*
	public DocumentationResultResponseDto getDocumentationList(TipoDocumentacionEnum type, String token, Long sapId, String deviceType) throws ServiceException;
*/
/*
	public DocumentationImageResponseDto createImageDocumentation(DocumentationImageAddRequestDto request, TipoDocumentacionEnum type, String token, Long sapId, String deviceType) throws ServiceException;

	public DocumentationFileResponseDto createFileDocumentation(DocumentationFileAddRequestDto request, TipoDocumentacionEnum type, String token, Long sapId, String deviceType) throws ServiceException;*/

	public List<DocumentationResultRespDto> getDocumentationList(TipoDocumentacionEnum type, String token, Long sapId, String deviceType) throws ServiceException;

	DocumentationResponseDto getDocumentation(Integer documentationId, String token, Long sapId, String deviceType) throws ServiceException;

	DocumentationCreateResponseDto createDocumentation(DocumentationCreateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
}
