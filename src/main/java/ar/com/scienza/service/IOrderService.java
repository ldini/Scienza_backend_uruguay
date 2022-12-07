package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.*;
import ar.com.scienza.dto.CoordinationRequestDto;
import ar.com.scienza.dto.CoordinationResponseDto;
import ar.com.scienza.dto.DeliveryLocationResponseDto;
import ar.com.scienza.dto.OrderResponseDto;
import ar.com.scienza.dto.OrderResultResponseDto;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.exception.ServiceException;


public interface IOrderService {

	public List<OrderResultResponseDto> getOrderList(Integer orderId, String token, Long sapId, String deviceType, String version) throws ServiceException;
	
	public OrderResponseDto getOrder(Integer orderId, String token, Long sapId, String deviceType, String version) throws ServiceException;

	public DeliveryLocationResponseDto getDeliveryLocation(Integer deliveryNumber, String token, Long sapId, String deviceType) throws ServiceException;

	public CoordinationResponseDto getCoordination(Long sapId, Integer orderId, String token, String deviceType) throws ServiceException;
	
	public void coordinateDelivery(CoordinationRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public List<OrderResultResponseDto> getOrderList(Afiliado afiliado, Sesion sesion);

	public OrderResponseDto getOrder(Integer orderId, Afiliado afiliado, Sesion sesion);

    List<PharmacyResponseDto> getPharmacySearch(Integer deliveryNumber, String token, Long sapId, String deviceType) throws ServiceException;
}
