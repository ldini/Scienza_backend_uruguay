package ar.com.scienza.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ar.com.scienza.dto.*;
import ar.com.scienza.remote.ws.dto.*;
import ar.com.scienza.remote.ws.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Entrega;
import ar.com.scienza.entity.EntregaMaterial;
import ar.com.scienza.entity.Pedido;
import ar.com.scienza.entity.PedidoMaterial;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.entity.Traza;
import ar.com.scienza.enumerator.MedioDePagoEnum;
import ar.com.scienza.enumerator.SAPEstadoEntregaEnum;
import ar.com.scienza.enumerator.SAPEstadoPedidoEnum;
import ar.com.scienza.enumerator.SAPTipoPedidoEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.EntregaMaterialRepository;
import ar.com.scienza.repository.EntregaRepository;
import ar.com.scienza.repository.PedidoMaterialRepository;
import ar.com.scienza.repository.PedidoRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.repository.TrazaRepository;
import ar.com.scienza.service.IOrderService;
import ar.com.scienza.utils.BigDecimalUtil;
import ar.com.scienza.utils.EnumeratorUtil;
import ar.com.scienza.utils.IntegerUtil;


@Service
@Transactional(rollbackFor = ServiceException.class)
public class OrderService implements IOrderService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMaterialRepository pedidoMaterialRepository;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaMaterialRepository entregaMaterialRepository;

    @Autowired
    private TrazaRepository trazaRepository;

    @Autowired
    private OrderIndexRemoteService orderIndexRemoteService;

    @Autowired
    private OrderDetailRemoteService orderDetailRemoteService;

/*    @Autowired
    private DeliveryLocationRemoteService deliveryLocationRemoteService;*/

    @Autowired
    private CoordinationPropRemoteService coordinationPropRemoteService;

    @Autowired
    private CoordinationConfirmRemoteService coordinationConfirmRemoteService;

    @Autowired
	private PharmacySearchRemoteService pharmacySearchRemoteService;
    

	@Override
	public List<OrderResultResponseDto> getOrderList(Integer orderId, String token, Long sapId, String deviceType, String version) throws ServiceException {

		List<OrderResultResponseDto> responseList = new ArrayList<OrderResultResponseDto>();
		Integer pedidosCount = 0;
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			DTSCIENZAINDPEDIDOSUY remoteIndexRequest = new DTSCIENZAINDPEDIDOSUY();
			if(orderId == null) {
				remoteIndexRequest.setPACIENTEID(sapId.toString());
				remoteIndexRequest.setPEDIDOSCANTIDAD("10");
			}
			else {
				remoteIndexRequest.setPACIENTEID(sapId.toString());
				remoteIndexRequest.setPEDIDODESDE(orderId.toString());
				remoteIndexRequest.setORDEN("BACKWARD");
				remoteIndexRequest.setPEDIDOSCANTIDAD("10");
			}
			
			DTSCIENZAINDPEDIDOSUYResponse remoteIndexResponse = orderIndexRemoteService.getOrders(remoteIndexRequest);
			for(DTSCIENZAINDPEDIDOSUYResponse.COLECCIONDEPEDIDOS colPedido : remoteIndexResponse.getCOLECCIONDEPEDIDOS())
			{
				Pedido pedido = pedidoRepository.findByAfiliadoIdAndNumeroPedido(afiliado.getId(), Integer.valueOf(colPedido.getPEDIDONUMERO()));
				
				if(pedido == null)
				{
					DTSCIENZAPEDENTUY remoteDetailRequest = new DTSCIENZAPEDENTUY();
					remoteDetailRequest.setPEDIDONUMERO(colPedido.getPEDIDONUMERO());

					DTSCIENZAPEDENTResponseUY remoteDetailResponse = orderDetailRemoteService.getOrder(remoteDetailRequest);
					SAPTipoPedidoEnum tipoPedido = SAPTipoPedidoEnum.valueSAP(remoteDetailResponse.getCLASIFICACION());
					
					SAPEstadoPedidoEnum estadoPedido = SAPEstadoPedidoEnum.valueSAP(remoteDetailResponse.getESTADO());
					if(SAPEstadoPedidoEnum.isValid(estadoPedido)) 
					{
						if(SAPTipoPedidoEnum.HISTORICO.getCodigo().equals(tipoPedido.getCodigo())) 
						{
							pedido = this.saveOrder(afiliado, remoteDetailResponse);
							responseList.add(this.getOrderResultResponse(afiliado, pedido, token, deviceType, version));
						}
						else
						{
							responseList.add(this.getOrderResultResponse(afiliado, remoteDetailResponse, token, deviceType, version));
						}
						pedidosCount++;
					}
				}
				else
				{
					responseList.add(this.getOrderResultResponse(afiliado, pedido, token, deviceType, version));
					pedidosCount++;
				}
				
				if(pedidosCount.intValue() == 5)
					break;
			}
		}
        catch(ServiceException e) {
        	ScienzaLogger.logError(e);
        	throw e;
        }
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener pedidos del paciente");
		}
		
		return responseList;
	}


	@Override
	public OrderResponseDto getOrder(Integer orderNumber, String token, Long sapId, String deviceType, String version) throws ServiceException {

		OrderResponseDto response = null;
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			Pedido pedido = pedidoRepository.findByAfiliadoIdAndNumeroPedido(afiliado.getId(), orderNumber);
			
			if(pedido == null)
			{
				DTSCIENZAPEDENTUY remoteDetailRequest = new DTSCIENZAPEDENTUY();
				remoteDetailRequest.setPEDIDONUMERO(orderNumber.toString());


				DTSCIENZAPEDENTResponseUY remoteDetailResponse = orderDetailRemoteService.getOrder(remoteDetailRequest);

				while (remoteDetailResponse.getESTADO().equals("NO MOSTRAR")) {

					String nroPedidoSucesor = remoteDetailResponse.getPEDIDOSUCESOR();
					remoteDetailRequest.setPEDIDONUMERO(nroPedidoSucesor);
					remoteDetailResponse = orderDetailRemoteService.getOrder(remoteDetailRequest);

				}

				SAPTipoPedidoEnum tipoPedido = SAPTipoPedidoEnum.valueSAP(remoteDetailResponse.getCLASIFICACION());

				if(SAPTipoPedidoEnum.HISTORICO.getCodigo().equals(tipoPedido.getCodigo())) 
				{
					pedido = this.saveOrder(afiliado, remoteDetailResponse);
					response = this.getOrderResponse(afiliado, pedido, token, deviceType, version);
				}
				else
				{
					response = this.getOrderResponse(afiliado, remoteDetailResponse, token, deviceType, version, true);
				}
			}
			else
			{
				response = this.getOrderResponse(afiliado, pedido, token, deviceType, version);
			}
			
			// ORDEN DE PEDIDOS
			Collections.sort(response.getEntregas(), new Comparator<OrderDeliveryResponseDto>() {
                @Override
                public int compare(OrderDeliveryResponseDto entrega1, OrderDeliveryResponseDto entrega2) {
                	return entrega1.getNumeroEntrega().compareTo(entrega2.getNumeroEntrega()) * -1;
                }
            });
		}
        catch(ServiceException e) {
        	ScienzaLogger.logError(e);
        	throw e;
        }
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener el pedido del paciente");
		}
		
		return response;
	}

	@Override
	public DeliveryLocationResponseDto getDeliveryLocation(Integer deliveryNumber, String token, Long sapId, String deviceType) throws ServiceException {
		return null;
	}


	/*@SuppressWarnings("deprecation")
	@Override
	public DeliveryLocationResponseDto getDeliveryLocation(Integer deliveryNumber, String token, Long sapId, String deviceType) throws ServiceException {

		DeliveryLocationResponseDto response = new DeliveryLocationResponseDto();
		
		try 
		{
			GetStatus remoteLocationRequest = new GetStatus();
			remoteLocationRequest.setCompany("SC");
			remoteLocationRequest.setCode(deliveryNumber.toString());
			
			GetStatusResponse remoteLocationResponse = deliveryLocationRemoteService.locateDelivery(remoteLocationRequest);
			if(!remoteLocationResponse.getGetStatusResult().isRespuestaOk())
				throw new Exception();
			
			ar.com.scienza.remote.ws.dto.Entrega remoteEntregaResult = remoteLocationResponse.getGetStatusResult().getResultado().getEntrega().get(0);
			
			String tiempoArriboMsg = "-";
			SimpleDateFormat sdflt = new SimpleDateFormat("HH:mm:ss");
			if(remoteEntregaResult.getTiempoDeArribo() != null) 
			{
				Date tiempoArribo = sdflt.parse(remoteEntregaResult.getTiempoDeArribo());
				tiempoArriboMsg = "";
				if(tiempoArribo.getHours() == 0 && tiempoArribo.getMinutes() == 0) {
					tiempoArriboMsg += "0 min.";
				}
				else {
					if(tiempoArribo.getHours() > 0)
						tiempoArriboMsg += String.valueOf(tiempoArribo.getHours()) + " horas ";
					if(tiempoArribo.getMinutes() > 0)
						tiempoArriboMsg += String.valueOf(tiempoArribo.getMinutes()) + " min.";
				}
			}
			
			response.setNumeroPedido(IntegerUtil.valueOf(remoteEntregaResult.getCodigoPedido()));
			response.setNumeroEntrega(IntegerUtil.valueOf(remoteEntregaResult.getCodigoEntrega()));
			response.setDistribuidor(remoteEntregaResult.getEmpleado());
			response.setPatente(remoteEntregaResult.getPatente());;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener el ubicación de la entrega");
		}
		
		return response;
	}*/


    /**
     * Obtiene un resultado de pedido historico
     *
     * @param pedido
     * @return
     */
    private OrderResultResponseDto getOrderResultResponse(Afiliado afiliado, Pedido pedido, String token, String deviceType, String version) throws Exception {

        SimpleDateFormat mbDateTime = new SimpleDateFormat("dd/MM/yyyy");

        SAPTipoPedidoEnum tipoPedido = EnumeratorUtil.valueOf(SAPTipoPedidoEnum.class, pedido.getClasificacion());
        SAPEstadoPedidoEnum estadoPedido = EnumeratorUtil.valueOf(SAPEstadoPedidoEnum.class, pedido.getEstado());

        OrderResultResponseDto response = new OrderResultResponseDto();
        response.setPedidoId(pedido.getNumeroPedido());
        response.setTipo(tipoPedido.getCodigo());
        response.setEstado(estadoPedido.getDescripcion());
        response.setCodigoEstado(estadoPedido.getCodigo());
        response.setNumeroPedido(pedido.getNumeroPedido());
        response.setCoordinable(false);
        response.setAllowUploads(false);
        response.setFechaEntrega((pedido.getFechaPedido() != null) ? mbDateTime.format(pedido.getFechaPedido()) : null);

        if (Arrays.asList(SAPEstadoPedidoEnum.REVISION.getCodigo(), SAPEstadoPedidoEnum.AUDITORIA.getCodigo()).contains(response.getCodigoEstado())) {
            response.setParcial(false);
        } else {
            response.setParcial(this.getOrderResponse(afiliado, pedido, token, deviceType, version).getParcial());
        }

        return response;
    }


    /**
     * Obtiene un resultado de pedido
     * //	 * @param pedido
     *
     * @return
     */
    private OrderResultResponseDto getOrderResultResponse(Afiliado afiliado, DTSCIENZAPEDENTResponseUY remoteResponse, String token, String deviceType, String version) throws Exception {

        SimpleDateFormat mbDateTime = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat spDateTime = new SimpleDateFormat("yyyyMMdd");

        SAPTipoPedidoEnum tipoPedido = SAPTipoPedidoEnum.valueSAP(remoteResponse.getCLASIFICACION());
        SAPEstadoPedidoEnum estadoPedido = SAPEstadoPedidoEnum.valueSAP(remoteResponse.getESTADO());
        if(SAPEstadoPedidoEnum.PARA_COORDINAR.equals(estadoPedido) && !this.isCoordinable(remoteResponse.getCOORDINACIONMOBILE())) {
        	estadoPedido = SAPEstadoPedidoEnum.PARA_COORDINAR_BLOQUEADO;
        }
        if(SAPEstadoPedidoEnum.PARA_COORDINAR.equals(estadoPedido) && "".equals(Strings.nullToEmpty(version)) && !"WEB".equals(deviceType)) {
        	estadoPedido = SAPEstadoPedidoEnum.PARA_COORDINAR_VIEJO;
        }

        OrderResultResponseDto response = new OrderResultResponseDto();
        response.setPedidoId(IntegerUtil.valueOf(remoteResponse.getPEDIDONUMERO()));
        response.setTipo(tipoPedido.getCodigo());
        response.setEstado(estadoPedido.getDescripcion());
        response.setCodigoEstado(estadoPedido.getCodigo());
        response.setNumeroPedido(IntegerUtil.valueOf(remoteResponse.getPEDIDONUMERO()));
        response.setCoordinable(this.isCoordinable(remoteResponse.getCOORDINACIONMOBILE()));
        //response.setAllowUploads(this.isEnvioReceta(remoteResponse.getENVIORECETA()));
        response.setAllowUploads(false);
        response.setFechaEntrega((remoteResponse.getPEDIDOFECHA() != null) ? mbDateTime.format(spDateTime.parse(remoteResponse.getPEDIDOFECHA())) : null);

		if(Arrays.asList(SAPEstadoPedidoEnum.REVISION.getCodigo(), SAPEstadoPedidoEnum.AUDITORIA.getCodigo()).contains(response.getCodigoEstado())) {
			response.setParcial(false);
		} 
		else {
			response.setParcial(this.getOrderResponse(afiliado, remoteResponse, token, deviceType, version, false).getParcial());
		}
		
		return response;
    }

	
	/**
	 * Obtiene un detalle de pedido historico
	 * @param pedido
	 * @return
	 */
	private OrderResponseDto getOrderResponse(Afiliado afiliado, Pedido pedido, String token, String deviceType, String version) throws Exception {
		
		SimpleDateFormat mbDate = new SimpleDateFormat("dd/MM/yyyy");

		SAPTipoPedidoEnum tipoPedido = EnumeratorUtil.valueOf(SAPTipoPedidoEnum.class, pedido.getClasificacion());
		SAPEstadoPedidoEnum estadoPedido = EnumeratorUtil.valueOf(SAPEstadoPedidoEnum.class, pedido.getEstado());
		
		OrderResponseDto response = new OrderResponseDto();
		response.setPedidoId(pedido.getNumeroPedido());
		response.setTipo(tipoPedido.getCodigo());
		response.setEstado(estadoPedido.getDescripcion());
		response.setCodigoEstado(estadoPedido.getCodigo());
		response.setNumeroPedido(pedido.getNumeroPedido());
		response.setFechaPedido(mbDate.format(pedido.getFechaPedido()));
		response.setImporteCoberturaParcial(pedido.getImporteCoberturaParcial());
		response.setCoordinable(false);
		response.setAllowUploads(false);
		
		for(Entrega entrega : pedido.getEntregaList()) 
		{
			SAPEstadoEntregaEnum estadoEntrega = EnumeratorUtil.valueOf(SAPEstadoEntregaEnum.class, entrega.getEstado());
			if(!SAPEstadoEntregaEnum.isValid(estadoEntrega)) {
				continue;
			}

			OrderDeliveryResponseDto deliveryResponse = new OrderDeliveryResponseDto();

			deliveryResponse.setNumeroEntrega(entrega.getNumeroEntrega());
			deliveryResponse.setDomicilio(pedido.getDomicilio());
			deliveryResponse.setFarmacia(pedido.getFarmacia());
			deliveryResponse.setFechaEntrega(entrega.getTextoFechaEntrega());
			deliveryResponse.setTurnoEntrega(entrega.getTextoTurno());
			deliveryResponse.setEstado(estadoEntrega.getDescripcion());
			deliveryResponse.setCodigoEstado(estadoEntrega.getCodigo());
			if(entrega.getMedioDePago() != null) {
				deliveryResponse.setMedioDePago(EnumeratorUtil.valueOf(MedioDePagoEnum.class, entrega.getMedioDePago()).getDescripcion());
				deliveryResponse.setImporteAbonado(entrega.getImporteAbonado());
			}
			deliveryResponse.setVerUbicacion("SCIENZA".equals(Strings.nullToEmpty(entrega.getOperadorLogistico()).toUpperCase()));
			response.getEntregas().add(deliveryResponse);
			
			for(EntregaMaterial entregaMaterial : entrega.getEntregaMaterialList())
			{
				OrderDeliveryDrugResponseDto deliveryDrugResponse = new OrderDeliveryDrugResponseDto();
				deliveryDrugResponse.setSapId(entregaMaterial.getSapId().intValue());
				deliveryDrugResponse.setNombre(entregaMaterial.getNombre());
				deliveryDrugResponse.setCantidad(entregaMaterial.getCantidad());
				deliveryDrugResponse.setMonodroga(entregaMaterial.getMonodroga());
				deliveryDrugResponse.setLaboratorio(entregaMaterial.getLaboratorio());
				deliveryDrugResponse.setLote(entregaMaterial.getLote());
				deliveryDrugResponse.setVencimiento((entregaMaterial.getVencimiento() != null) ? mbDate.format(entregaMaterial.getVencimiento()) : null);
				
				for(Traza traza : entregaMaterial.getTrazaList())
					deliveryDrugResponse.getTrazas().add(traza.getCodigo());
				
				deliveryResponse.getMedicamentos().add(deliveryDrugResponse);
			}
		}

		this.recalculatePendingDrugs(response);
		
		if(Arrays.asList(SAPEstadoPedidoEnum.REVISION.getCodigo(), SAPEstadoPedidoEnum.AUDITORIA.getCodigo()).contains(response.getCodigoEstado())) {
			response.setParcial(false);
		} 
		else {
			response.setParcial(response.getMedicamentos().size() > 0 || response.getEntregas().size() > 1);
		}
		
		return response;
	}

	
	/**
	 * Obtiene un detalle de pedido
	 * @param
	 * @return
	 */
	private OrderResponseDto getOrderResponse(Afiliado afiliado, DTSCIENZAPEDENTResponseUY remoteResponse, String token, String deviceType, String version, Boolean searchCoordination) throws Exception {

		SimpleDateFormat sapDate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat mbDate = new SimpleDateFormat("dd/MM/yyyy");

		SAPTipoPedidoEnum tipoPedido = SAPTipoPedidoEnum.valueSAP(remoteResponse.getCLASIFICACION());
		SAPEstadoPedidoEnum estadoPedido = SAPEstadoPedidoEnum.valueSAP(remoteResponse.getESTADO());
        if(SAPEstadoPedidoEnum.PARA_COORDINAR.equals(estadoPedido) && !this.isCoordinable(remoteResponse.getCOORDINACIONMOBILE())) {
        	estadoPedido = SAPEstadoPedidoEnum.PARA_COORDINAR_BLOQUEADO;
        }
        if(SAPEstadoPedidoEnum.PARA_COORDINAR.equals(estadoPedido) && "".equals(Strings.nullToEmpty(version)) && !"WEB".equals(deviceType)) {
        	estadoPedido = SAPEstadoPedidoEnum.PARA_COORDINAR_VIEJO;
        }
		
		OrderResponseDto response = new OrderResponseDto();
		response.setPedidoId(IntegerUtil.valueOf(remoteResponse.getPEDIDONUMERO()));
		response.setTipo(tipoPedido.getCodigo());
		response.setEstado(estadoPedido.getDescripcion());
		response.setCodigoEstado(estadoPedido.getCodigo());
		response.setNumeroPedido(IntegerUtil.valueOf(remoteResponse.getPEDIDONUMERO()));
		response.setFechaPedido((remoteResponse.getPEDIDOFECHA() != null) ? mbDate.format(sapDate.parse(remoteResponse.getPEDIDOFECHA())) : null);
		//response.setImporteCoberturaParcial(BigDecimalUtil.valueOf(remoteResponse.getIMPORTEAFIPED()));
		response.setCoordinable(this.isCoordinable(remoteResponse.getCOORDINACIONMOBILE()));
        //response.setAllowUploads(this.isEnvioReceta(remoteResponse.getENVIORECETA()));
		
		for(DTSCIENZAPEDENTResponseUY.COLECCIONPOSICIONESPEDIDO colPosicion : remoteResponse.getCOLECCIONPOSICIONESPEDIDO())
		{
			OrderDrugResponseDto drugResponse = new OrderDrugResponseDto();
			drugResponse.setSapId(IntegerUtil.valueOf(colPosicion.getMATERIALID()));
			drugResponse.setNombre(colPosicion.getMATERIALNOMBRE());
			drugResponse.setCantidad(IntegerUtil.valueOf(colPosicion.getCANTIDAD()));
			drugResponse.setLaboratorio(colPosicion.getLABORATORIO());
			response.getMedicamentos().add(drugResponse);
		}
		
		if(searchCoordination && this.isCoordinable(remoteResponse.getCOORDINACIONMOBILE())) {
			CoordinationResponseDto coordinationResponse = this.getCoordination(afiliado.getSapId(), Integer.valueOf(remoteResponse.getPEDIDONUMERO()), token, deviceType);
			response.getMedicamentosDeEntrega().addAll(coordinationResponse.getDrugs());
		}

		for(DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS colEntrega : remoteResponse.getCOLECCIONENTREGAS())
		{
			SAPEstadoEntregaEnum estadoEntrega = SAPEstadoEntregaEnum.valueSAP(colEntrega.getESTADOENVIO());
			if(!SAPEstadoEntregaEnum.isValid(estadoEntrega))
				continue;
			
			OrderDeliveryResponseDto deliveryResponse = new OrderDeliveryResponseDto();
			deliveryResponse.setNumeroEntrega(IntegerUtil.valueOf(colEntrega.getENTREGA()));
			deliveryResponse.setDomicilio(Strings.nullToEmpty(remoteResponse.getENTREGADOMICILIO()));
			deliveryResponse.setFarmacia(Strings.nullToEmpty(remoteResponse.getENTREGAFARMACIA()));
			deliveryResponse.setFechaEntrega(Strings.nullToEmpty(colEntrega.getTXTFECHAENTREGA()));
			deliveryResponse.setTurnoEntrega(Strings.nullToEmpty(colEntrega.getTXTTURNO()));
			deliveryResponse.setEstado(estadoEntrega.getDescripcion());
			deliveryResponse.setCodigoEstado(estadoEntrega.getCodigo());

			//NUEVO DESARROLLO (si el pedido esta en distribucion)

			if(estadoEntrega.equals(SAPEstadoEntregaEnum.EN_DISTRIBUCION)) {
				DeliveryLocationResponseDto deliveryLocation = new DeliveryLocationResponseDto();
				deliveryLocation.setDistribuidor(colEntrega.getDISTRIBUIDOR());
				deliveryLocation.setPatente(colEntrega.getPATENTE());

				deliveryResponse.setUbicacionEntrega(deliveryLocation);
			}
			//deliveryResponse.setMedioDePago((colEntrega.getMEDIOPAGOENT() != null) ? MedioDePagoEnum.fromSAP(colEntrega.getMEDIOPAGOENT()).getDescripcion() : null);
			//deliveryResponse.setImporteAbonado(BigDecimalUtil.valueOf(colEntrega.getIMPORTEAFIENT()));
			deliveryResponse.setVerUbicacion("SCIENZA".equals(Strings.nullToEmpty(colEntrega.getOPERADORLOGISTICO()).toUpperCase()));
			response.getEntregas().add(deliveryResponse);
			
			for(DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA colEntregaPos : colEntrega.getCOLECCIONPOSICIONESENTREGA())
			{
				OrderDeliveryDrugResponseDto deliveryDrugResponse = new OrderDeliveryDrugResponseDto();
				deliveryDrugResponse.setSapId(IntegerUtil.valueOf(colEntregaPos.getMATERIALID()));
				deliveryDrugResponse.setNombre(colEntregaPos.getMATERIALNOMBRE());
				deliveryDrugResponse.setCantidad(IntegerUtil.valueOf(colEntregaPos.getCANTIDAD()));
				deliveryDrugResponse.setMonodroga(colEntregaPos.getMONODROGA());
				deliveryDrugResponse.setLaboratorio(colEntregaPos.getLABORATORIO());
				deliveryDrugResponse.setLote(colEntregaPos.getLOTE());
				deliveryDrugResponse.setVencimiento((colEntregaPos.getVENCIMIENTO() != null) ? mbDate.format(sapDate.parse(colEntregaPos.getVENCIMIENTO())) : null);
				
				for(DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA.COLECCIONTRAZAS colTraza : colEntregaPos.getCOLECCIONTRAZAS())
					deliveryDrugResponse.getTrazas().add(colTraza.getTRAZA());
				
				deliveryResponse.getMedicamentos().add(deliveryDrugResponse);
			}
		}
		
		this.recalculatePendingDrugs(response);
		
		if(Arrays.asList(SAPEstadoPedidoEnum.REVISION.getCodigo(), SAPEstadoPedidoEnum.AUDITORIA.getCodigo()).contains(response.getCodigoEstado())) {
			response.setParcial(false);
		} 
		else {
			response.setParcial(response.getMedicamentos().size() > 0 || response.getEntregas().size() > 1);
		}
		
		if(SAPEstadoPedidoEnum.PARA_COORDINAR_VIEJO.equals(estadoPedido)) {
			for(DTSCIENZAPEDENTResponseUY.COLECCIONPOSICIONESPEDIDO colPosicion : remoteResponse.getCOLECCIONPOSICIONESPEDIDO())
			{
				OrderDrugResponseDto drugResponse = new OrderDrugResponseDto();
				drugResponse.setSapId(IntegerUtil.valueOf(colPosicion.getMATERIALID()));
				drugResponse.setNombre(colPosicion.getMATERIALNOMBRE());
				drugResponse.setCantidad(IntegerUtil.valueOf(colPosicion.getCANTIDAD()));
				drugResponse.setLaboratorio(colPosicion.getLABORATORIO());
				response.getMedicamentos().add(drugResponse);
			}
        }
		
		return response;
	}

	
	/**
	 * Obtiene un detalle de pedido
	 * @param
	 * @return
	 */
	private Pedido saveOrder(Afiliado afiliado, DTSCIENZAPEDENTResponseUY remoteResponse) throws Exception {

		SimpleDateFormat sapDate = new SimpleDateFormat("yyyyMMdd");

		SAPTipoPedidoEnum tipoPedido = SAPTipoPedidoEnum.valueSAP(remoteResponse.getCLASIFICACION());
		SAPEstadoPedidoEnum estadoPedido = SAPEstadoPedidoEnum.valueSAP(remoteResponse.getESTADO());
		
		Pedido pedido = new Pedido();
		pedido.setAfiliado(afiliado);
		pedido.setNumeroPedido(IntegerUtil.valueOf(remoteResponse.getPEDIDONUMERO()));
		pedido.setFechaPedido((remoteResponse.getPEDIDOFECHA() != null) ? sapDate.parse(remoteResponse.getPEDIDOFECHA()) : null);
		pedido.setCanal(remoteResponse.getCANAL());
		pedido.setSector(remoteResponse.getSECTOR());
		pedido.setClasificacion(tipoPedido.getCodigo());
		pedido.setEstado(estadoPedido.getCodigo());
		pedido.setDomicilio(remoteResponse.getENTREGADOMICILIO());
		pedido.setFarmacia(remoteResponse.getENTREGAFARMACIA());
		//pedido.setImporteCoberturaParcial(BigDecimalUtil.valueOf(remoteResponse.getIMPORTEAFIPED()));
		//pedido.setMedioDePago((remoteResponse.getMEDIOPAGOPED() != null) ? MedioDePagoEnum.fromSAP(remoteResponse.getMEDIOPAGOPED()).getCodigo() : null);
		pedido.createOnRepository(pedidoRepository);
		
		for(DTSCIENZAPEDENTResponseUY.COLECCIONPOSICIONESPEDIDO colPosicion : remoteResponse.getCOLECCIONPOSICIONESPEDIDO())
		{
			PedidoMaterial pedidoMaterial = new PedidoMaterial();
			pedidoMaterial.setPedido(pedido);
			pedidoMaterial.setSapId(Long.valueOf(colPosicion.getMATERIALID()));
			pedidoMaterial.setNombre(colPosicion.getMATERIALNOMBRE());
			pedidoMaterial.setCantidad(IntegerUtil.valueOf(colPosicion.getCANTIDAD()));
			pedidoMaterial.setMonodroga(colPosicion.getMONODROGA());
			pedidoMaterial.setLaboratorio(colPosicion.getLABORATORIO());
			pedidoMaterial.createOnRepository(pedidoMaterialRepository);
			pedido.getMaterialList().add(pedidoMaterial);
		}

		for(DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS colEntrega : remoteResponse.getCOLECCIONENTREGAS())
		{
			SAPEstadoEntregaEnum estadoEntrega = SAPEstadoEntregaEnum.valueSAP(colEntrega.getESTADOENVIO());
			
			Entrega entrega = new Entrega();
			entrega.setPedido(pedido);
			entrega.setNumeroEntrega(IntegerUtil.valueOf(colEntrega.getENTREGA()));
			entrega.setNumeroRemito(colEntrega.getREMITO());
			entrega.setTextoFechaEntrega(Strings.nullToEmpty(colEntrega.getTXTFECHAENTREGA()));
			entrega.setTextoTurno(Strings.nullToEmpty(colEntrega.getTXTTURNO()));

			entrega.setOperadorLogistico(colEntrega.getOPERADORLOGISTICO());
			entrega.setEstado(estadoEntrega.getCodigo());
			//entrega.setLetraDestinatario(colEntrega.getLETRA());
			entrega.createOnRepository(entregaRepository);
			pedido.getEntregaList().add(entrega);
			
			for(DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA colEntregaPos : colEntrega.getCOLECCIONPOSICIONESENTREGA())
			{
				EntregaMaterial entregaMaterial = new EntregaMaterial();
				entregaMaterial.setEntrega(entrega);
				entregaMaterial.setSapId(Long.valueOf(colEntregaPos.getMATERIALID()));
				entregaMaterial.setNombre(colEntregaPos.getMATERIALNOMBRE());
				entregaMaterial.setCantidad(IntegerUtil.valueOf(colEntregaPos.getCANTIDAD()));
				entregaMaterial.setMonodroga(colEntregaPos.getMONODROGA());
				entregaMaterial.setLaboratorio(colEntregaPos.getLABORATORIO());
				entregaMaterial.setLote(colEntregaPos.getLOTE());
				entregaMaterial.setVencimiento((colEntregaPos.getVENCIMIENTO() != null) ? sapDate.parse(colEntregaPos.getVENCIMIENTO()) : null);
				entregaMaterial.createOnRepository(entregaMaterialRepository);
				entrega.getEntregaMaterialList().add(entregaMaterial);
				
				for(DTSCIENZAPEDENTResponseUY.COLECCIONENTREGAS.COLECCIONPOSICIONESENTREGA.COLECCIONTRAZAS colTraza : colEntregaPos.getCOLECCIONTRAZAS()) {
					Traza traza = new Traza();
					traza.setEntregaMaterial(entregaMaterial);
					traza.setCodigo(colTraza.getTRAZA());
					traza.createOnRepository(trazaRepository);
					entregaMaterial.getTrazaList().add(traza);
				}
			}
		}
		
		return pedido;
	}


    /**
     * Calcula los materiales pendientes de entrega
     *
     * @param orderResponse
     */
    private void recalculatePendingDrugs(OrderResponseDto orderResponse) {

        for (OrderDrugResponseDto drugResponse : new ArrayList<OrderDrugResponseDto>(orderResponse.getMedicamentos())) {
            Integer cantidadEntregada = 0;

            for (OrderDeliveryResponseDto deliveryResponse : orderResponse.getEntregas())
                for (OrderDeliveryDrugResponseDto deliveryDrugResponse : deliveryResponse.getMedicamentos())
                    if (deliveryDrugResponse.getSapId().compareTo(drugResponse.getSapId()) == 0)
                        cantidadEntregada += deliveryDrugResponse.getCantidad();

            for (CoordinationDrugResponseDto coordinationDrugResponse : orderResponse.getMedicamentosDeEntrega())
                if (coordinationDrugResponse.getSapId().compareTo(drugResponse.getSapId()) == 0)
                    cantidadEntregada += coordinationDrugResponse.getCantidad();

            if (cantidadEntregada.compareTo(drugResponse.getCantidad()) >= 0)
                orderResponse.getMedicamentos().remove(drugResponse);
            else
                drugResponse.setCantidad(drugResponse.getCantidad() - cantidadEntregada);
        }
    }

    
    @Override
    public CoordinationResponseDto getCoordination(Long sapId, Integer orderId, String token, String deviceType) throws ServiceException {

        CoordinationResponseDto response = new CoordinationResponseDto();
        
        try 
        {
            Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
            if (afiliado == null) {
                throw new Exception();
            }

            DTSCIENZAPROCOORDINACION remoteCoordinationRequest = new DTSCIENZAPROCOORDINACION();
            remoteCoordinationRequest.setIDAFILIADO(sapId.toString());
            remoteCoordinationRequest.setNUMEROPEDIDO(orderId.toString());

            DTSCIENZAPROCOORDINACIONResponse remoteCoordinationResponse = coordinationPropRemoteService.getCoordination(remoteCoordinationRequest);
            if(remoteCoordinationResponse.getSTATUSPROPUESTA().equals("NO OK"))
            	throw new ServiceException(remoteCoordinationResponse.getTEXTOSTATUSPROPUESTA());
            
            remoteCoordinationResponse.getNUMEROPEDIDO();
            response.setProposalId(Integer.valueOf(remoteCoordinationResponse.getIDPROPUESTA()));
            response.setPayment(remoteCoordinationResponse.getIMPORTEAABONAR());
            
            ArrayList<CoordinationDrugResponseDto> drugList = new ArrayList<>();
            for (DTSCIENZAPROCOORDINACIONResponse.POSICIONES posicion : remoteCoordinationResponse.getPOSICIONES()){
                CoordinationDrugResponseDto drug = new CoordinationDrugResponseDto();
                drug.setSapId(Integer.valueOf(posicion.getIDMATERIAL()));
                drug.setPosicion(posicion.getPOSICION().intValue());
                drug.setDescripcion(posicion.getDESCRIPCION());
                drug.setCantidad(posicion.getCANTIDADAENTREGAR().intValue());
                drug.setLaboratorio(posicion.getLABORATORIO());
                drugList.add(drug);
            }
            response.setDrugs(drugList);

            ArrayList<CoordinationAddressResponseDto> proposalList = new ArrayList<>();
            for (DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS domicilio : remoteCoordinationResponse.getDOMICILIOS()){
                CoordinationAddressResponseDto proposal = new CoordinationAddressResponseDto();
                proposal.setType(domicilio.getTIPODOMICILIO().toUpperCase());
                proposal.setAddressText(domicilio.getTIPODOMICILIO());
                proposal.setAddressCode(domicilio.getIDDESTINATARIO().intValue());
                proposal.setPharmacy(domicilio.getNOMBREDEDISPONE());
                proposal.setAddress(domicilio.getDIRECCION());

                ArrayList<CoordinationDateResponseDateDto> dateList = new ArrayList<>();
                for (DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS.FECHAS fecha : domicilio.getFECHAS()){
                    CoordinationDateResponseDateDto date = new CoordinationDateResponseDateDto();
                    String deliverDate  = new SimpleDateFormat("yyyMMdd").format(fecha.getFECHAENTREGA().toGregorianCalendar().getTime());
                    date.setDate(deliverDate);
                    date.setDateText(fecha.getTEXTOFECHA());
                    date.setShift(fecha.getTURNO());
                    date.setShiftText(fecha.getTEXTOTURNO());
                    dateList.add(date);
                }
                proposal.setDateList(dateList);

                if (remoteCoordinationResponse.getIMPORTEAABONAR().compareTo(new BigDecimal(0)) > 0) {
                    ArrayList<CoordinationPaymentMethodResponseDto> paymentMethods = new ArrayList<>();
                    if (remoteCoordinationResponse.getIMPORTEAABONAR().compareTo(remoteCoordinationResponse.getLIMITEPAGOEFECTIVO()) <= 0) {
                        paymentMethods.add(new CoordinationPaymentMethodResponseDto(MedioDePagoEnum.EFECTIVO));
                    }
                    paymentMethods.add(new CoordinationPaymentMethodResponseDto(MedioDePagoEnum.TARJETA_CREDITO));
                    paymentMethods.add(new CoordinationPaymentMethodResponseDto(MedioDePagoEnum.TARJETA_DEBITO));
                    response.setPaymentMethodList(paymentMethods);
                }

                proposalList.add(proposal);
            }
            response.setProposalList(proposalList);
        }
        catch(ServiceException e) {
        	ScienzaLogger.logError(e);
        	throw e;
        }
        catch (Exception e) {
        	ScienzaLogger.logError(e);
            throw new ServiceException("Error al obtener la propuesta de coordinación");
        }

        return response;
    }


    @Override
    public void coordinateDelivery(CoordinationRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

        try 
        {
            Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
            if (afiliado == null) {
                throw new Exception();
            }

            DTSCIENZAPROCOORDINACION remoteCoordinationRequest = new DTSCIENZAPROCOORDINACION();
            remoteCoordinationRequest.setIDAFILIADO(sapId.toString());
            remoteCoordinationRequest.setNUMEROPEDIDO(request.getOrderId().toString());
            DTSCIENZAPROCOORDINACIONResponse remoteCoordinationResponse = coordinationPropRemoteService.getCoordination(remoteCoordinationRequest);

            DTSCIENZACONFIRCOORDINACION confirmRequest = new DTSCIENZACONFIRCOORDINACION();
            confirmRequest.setIDAFILIADO(sapId.toString());
            confirmRequest.setNUMEROPEDIDO(request.getOrderId().toString());
            confirmRequest.setIDPROPUESTA(request.getProposalId().toString());
            confirmRequest.setMARCAPROPUESTAACEPTADA((request.getAccepted()) ? "SI" : (!request.getChangePharmacy()) ? "NO" : "CAMBIAR DISPONE");
            confirmRequest.setCOMENTARIOSCANAL(request.getRejectComment());

            if(request.getAccepted())
            {
	            // Fecha, horario y lugar
	            confirmRequest.setDESTINATARIOMERCADERIA(request.getAddressCode().toString());
	            for (DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS address: remoteCoordinationResponse.getDOMICILIOS()){
	                if (address.getIDDESTINATARIO().compareTo(new BigInteger(request.getAddressCode().toString())) == 0){
	                    for (DTSCIENZAPROCOORDINACIONResponse.DOMICILIOS.FECHAS date : address.getFECHAS()){
	                    	String deliveryDate = new SimpleDateFormat("yyyyMMdd").format(date.getFECHAENTREGA().toGregorianCalendar().getTime());
	                        if (deliveryDate.equals(request.getDateCode()) && date.getTURNO().trim().equals(request.getShiftCode())) {
	                            confirmRequest.setFECHAENTREGA(date.getFECHAENTREGA());
	                            confirmRequest.setTURNO(date.getTURNO());
	                            confirmRequest.setVENCIMIENTO(date.getVENCIMIENTO());
	                        }
	                    }
	                }
	            }
	
	            // TODO: Para la 2da entrega, se agregaría la parte de pago
	            confirmRequest.setIMPORTEABONADO(remoteCoordinationResponse.getIMPORTEAABONAR());
	            confirmRequest.setMEDIOPAGO(request.getPaymentCode());
	//            remoteCoordinationConfirmationRequest.setNUMEROCOMPROBANTEPAGO(0);
	//            remoteCoordinationConfirmationRequest.setLOTE("");
	//            remoteCoordinationConfirmationRequest.setFECHAPAGO("");
	//            remoteCoordinationConfirmationRequest.setTIPOTARJETA("VACIO");
	//            remoteCoordinationConfirmationRequest.setNUMEROTARJETA("");
	//            remoteCoordinationConfirmationRequest.setMESVENCIMIENTO(0);
	//            remoteCoordinationConfirmationRequest.setANIOVENCIMIENTO(0);
	//            remoteCoordinationConfirmationRequest.setMARCATARJETA("VACIO");
	//            remoteCoordinationConfirmationRequest.setCUOTAS(0);
	            
	            //Validación de datos. Si no hubo match es porque no se encontro la propuesta
	            if(confirmRequest.getFECHAENTREGA() == null || confirmRequest.getTURNO() == null)
	            	throw new Exception();
            }
            
            DTSCIENZACONFIRCOORDINACIONResponse confirmResponse = this.coordinationConfirmRemoteService.confirmCoordination(confirmRequest);
            if(!confirmResponse.getSTATUS().equals("OK")) {
            	throw new ServiceException(confirmResponse.getMENSAJE());
            }
            
			/* Elimino el registro en CACHE */
			DTSCIENZAPEDENTUY remoteDetailRequest = new DTSCIENZAPEDENTUY();
			remoteDetailRequest.setPEDIDONUMERO(request.getOrderId().toString());
			orderDetailRemoteService.evictOrder(remoteDetailRequest);
        } 
        catch(ServiceException e) {
        	ScienzaLogger.logError(e);
        	throw e;
        }
        catch (Exception e) {
            ScienzaLogger.logError(e);
            throw new ServiceException("Error al coordinar la propuesta ingresada.");
        }
    }

	@Override
	public List<PharmacyResponseDto>getPharmacySearch(Integer idOrder, String token, Long sapId, String deviceType) throws ServiceException {

    	List<PharmacyResponseDto> response = new ArrayList<PharmacyResponseDto>();

    	try
		{
			DTSCIENZABusquedaDispone pharmacySearchRequest = new DTSCIENZABusquedaDispone();
			pharmacySearchRequest.setIDAFILIADO(sapId.toString());
			pharmacySearchRequest.setNUMEROPEDIDO(idOrder.toString());

			DTSCIENZABusquedaDisponeResponse pharmacySearchRemoteResponse = pharmacySearchRemoteService.getPharmacySearch(pharmacySearchRequest);

			for (DTSCIENZABusquedaDisponeResponse.Dispones.Dispone remotePharmacy: pharmacySearchRemoteResponse.getDispones().getDispone()){

				PharmacyResponseDto responsePharmacy = new PharmacyResponseDto();

				responsePharmacy.setAddress(remotePharmacy.getDIRECCION());
				responsePharmacy.setIdPharmacy(remotePharmacy.getIDDISPONE());

				if (remotePharmacy.getDISPONEPREFERIDO().equals("SI")){
					responsePharmacy.setPreferedPharmacy(true);
				} else{
					responsePharmacy.setPreferedPharmacy(false);
				}

				//responsePharmacy.setLatitude(remotePharmacy.getLATITUD());
				//responsePharmacy.setLongitude(remotePharmacy.getLONGITUD());
				responsePharmacy.setName(remotePharmacy.getNOMBRE());

				List<String> texts = new ArrayList<>();
				if (remotePharmacy.getTEXTOS() != null){

					List<String> remotePharmacyTexts = remotePharmacy.getTEXTOS().getTEXTO();
					for (String remoteText: remotePharmacyTexts){
						texts.add(remoteText);
					}
				}
				responsePharmacy.setTexts(texts);

				response.add(responsePharmacy);

			}

		}catch (Exception ex) {
			ScienzaLogger.logError(ex);
			throw new ServiceException("Error al buscar otras farmacias.");
		}

    	return response;

	}


	private boolean isCoordinable(String value) {
    	return value != null && "SI".equals(value.toUpperCase().trim());
    }
    
    private boolean isEnvioReceta(String value) {
    	return value != null && "SI".equals(value.toUpperCase().trim());
    }


	@Override
	public List<OrderResultResponseDto> getOrderList(Afiliado afiliado, Sesion sesion) {
		try {
			return this.getOrderList(null, sesion.getToken(), afiliado.getSapId(), sesion.getTipoDispositivo(), "2.0.0");
		} catch (ServiceException e) {
			return new ArrayList<OrderResultResponseDto>();
		}
	}


	@Override
	public OrderResponseDto getOrder(Integer orderId, Afiliado afiliado, Sesion sesion) {
		try {
			return this.getOrder(orderId, sesion.getToken(), afiliado.getSapId(), sesion.getTipoDispositivo(), "2.0.0");
		} catch (ServiceException e) {
			return null;
		}
	}
}
