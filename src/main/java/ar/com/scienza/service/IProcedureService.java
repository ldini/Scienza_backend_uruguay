package ar.com.scienza.service;

import ar.com.scienza.dto.*;
import ar.com.scienza.exception.ServiceException;

import java.util.List;

public interface IProcedureService {

    List<ProcedureResultResponseDto> getProcedureList(String token, Long sapId, String deviceType) throws ServiceException;

    ProcedureResponseDto getProcedure(Integer tramiteId, String token, Long sapId, String deviceType) throws ServiceException;

    ProcedureCreateResponseDto createProcedure(ProcedureCreateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
}
