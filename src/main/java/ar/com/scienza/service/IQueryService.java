package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.*;
import ar.com.scienza.exception.ServiceException;


public interface IQueryService {

	QueryResponseDto getQuery(Integer consultaId, String token, Long sapId, String deviceType) throws ServiceException;
	
	List<QueryResultResponseDto> getQueryList(String token, Long sapId, String deviceType) throws ServiceException;
	
	QueryCreateResponseDto createQuery(QueryCreateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	void deleteQuery(QueryDeleteRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	QueryBatchDeleteResponseDto deleteQueryBatch(QueryBatchDeleteRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	AdminQueryResultResponseDto getPendingQueryList(String token, Long sapId, String deviceType) throws ServiceException;
	
	List<AdminQueryResultDetailResponseDto> getHistoryQueryList(Integer queryId, Long affiliateSapId, String firstName , String lastName , String token, Long sapId, String deviceType) throws ServiceException;

	void takeQuery(AdminQueryTakeRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
	AdminQueryResponseDto getAdminQuery(Integer consultaId, String token, Long sapId, String deviceType) throws ServiceException;

	void closeQuery(AdminQueryCloseRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

}
