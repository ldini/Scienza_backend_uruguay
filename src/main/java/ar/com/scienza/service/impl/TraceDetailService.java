package ar.com.scienza.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.TraceDetailDestinationResponseDto;
import ar.com.scienza.dto.TraceDetailOriginResponseDto;
import ar.com.scienza.dto.TraceDetailResponseDto;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.enumerator.SAPResultadoEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.remote.ws.dto.DTSCIENZACONSULTATRAZASUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZACONSULTATRAZASUYResponse;
import ar.com.scienza.remote.ws.dto.DTSCIENZACONSULTATRAZASUYResponse.DATOS;
import ar.com.scienza.remote.ws.service.TraceDetailRemoteService;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.service.ITraceDetailService;
import ar.com.scienza.utils.IntegerUtil;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class TraceDetailService implements ITraceDetailService {

	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private TraceDetailRemoteService traceDetailRemoteService;


	@Override
	public TraceDetailResponseDto getTraceDetail(String code, String qRcode, String token, Long sapId, String deviceType) throws ServiceException {

		TraceDetailResponseDto response = null;
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			DTSCIENZACONSULTATRAZASUY remoteDetailRequest = new DTSCIENZACONSULTATRAZASUY();
			remoteDetailRequest.setCODIGOTRAZABILIDAD((code == null || code.isEmpty()) ? "+d2" + qRcode.replaceAll(" ", "\"") : code);
			
			DTSCIENZACONSULTATRAZASUYResponse remoteDetailResponse = traceDetailRemoteService.getTraceDetail(remoteDetailRequest);
			if(SAPResultadoEnum.STATUS_ERROR.getCodigo().equals(remoteDetailResponse.getSTATUS())) {
				throw new Exception();
			}
			
			SimpleDateFormat sapDate = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sapDateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			SimpleDateFormat mbDate = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat mbDateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			
			// RESPUESTA
			DATOS remoteDataResponse = remoteDetailResponse.getDATOS();
			response = new TraceDetailResponseDto();
			response.setProducto(remoteDataResponse.getPRODUCTO());
			response.setLaboratorio(remoteDataResponse.getLABORATORIO());
			response.setLote(remoteDataResponse.getLOTE());
			response.setVencimiento((remoteDataResponse.getVENCIMIENTO() != null) ? mbDate.format(sapDate.parse(remoteDataResponse.getVENCIMIENTO())) : null);
			response.setPaisOrigen(remoteDataResponse.getPAISORIGEN());
			
			DATOS.INFOORIGEN remoteOriginResponse = remoteDataResponse.getINFOORIGEN();
			DATOS.INFODESTINO remoteDestinationResponse = remoteDataResponse.getINFODESTINO();
			
			TraceDetailOriginResponseDto originResponse = new TraceDetailOriginResponseDto();
			originResponse.setComprobante(remoteOriginResponse.getCOMPROBANTE());
			originResponse.setFechaHora((remoteOriginResponse.getFECHAHORA() != null) ? mbDateTime.format(sapDateTime.parse(remoteOriginResponse.getFECHAHORA())) : null);
			response.setOrigen(originResponse);
			
			TraceDetailDestinationResponseDto destinationResponse = new TraceDetailDestinationResponseDto();
			destinationResponse.setDestino(remoteDestinationResponse.getDESTINO());
			destinationResponse.setNumeroPedido(IntegerUtil.valueOf(remoteDestinationResponse.getNROPEDIDO()));
			destinationResponse.setRemito(remoteDestinationResponse.getREMITO());
			destinationResponse.setFecha((remoteDestinationResponse.getFECHAENTREGA() != null) ? mbDate.format(sapDate.parse(remoteDestinationResponse.getFECHAENTREGA())) : null);
			destinationResponse.setCliente(remoteDestinationResponse.getCLIENTE());
			response.setDestino(destinationResponse);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener el detalle de traza.");
		}
		
		return response;
	}
}
