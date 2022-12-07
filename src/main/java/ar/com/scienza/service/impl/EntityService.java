package ar.com.scienza.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.com.scienza.entity.*;
import ar.com.scienza.repository.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.CategoryTypeResponseDto;
import ar.com.scienza.dto.EntityResponseDto;
import ar.com.scienza.dto.QueryTypeResponseDto;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IEntityService;
import ar.com.scienza.transformer.EntityTRF;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class EntityService implements IEntityService {

	@Autowired
	private CompaniaCelularRepository companiaCelularRepository;

	@Autowired
	private SeguroMedicoRepository seguroMedicoRepository;

	@Autowired
	private AfiliadoRepository afiliadoRepository;

	@Autowired
	private TipoConsultaRepository tipoConsultaRepository;

	@Autowired
	private TipoDocumentacionRepository tipoDocumentacionRepository;
	
	@PersistenceContext 
	private EntityManager entityManager;


	@Override
	public List<EntityResponseDto> getCellCompanyList(String token, Long sapId, String deviceType) throws ServiceException {

		List<EntityResponseDto> responseList = new ArrayList<EntityResponseDto>();
		
		try 
		{
			List<CompaniaCelular> companiaCelularList = companiaCelularRepository.findAll();
			for(CompaniaCelular companiaCelular : companiaCelularList)
			{
				EntityResponseDto response = new EntityResponseDto();
				response.setCodigo(companiaCelular.getCodigo());
				response.setDescripcion(companiaCelular.getDescripcion());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener Compañias Celulares");
		}
		
		return responseList;
	}


	@Override
	public List<EntityResponseDto> getHealthInsuranceList(String token, Long sapId, String deviceType) throws ServiceException {

		List<EntityResponseDto> responseList = new ArrayList<EntityResponseDto>();
		
		try 
		{
			List<SeguroMedico> seguroMedicoList = seguroMedicoRepository.findAllByFechaDeleteIsNullOrderByDescripcionAsc();
			for(SeguroMedico seguroMedico : seguroMedicoList)
			{
				EntityResponseDto response = new EntityResponseDto();
				response.setCodigo(seguroMedico.getSapId().toString());
				response.setDescripcion(seguroMedico.getDescripcion());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener lista de Institución Medica");
		}
		
		return responseList;
	}


	@Override
	public List<EntityResponseDto> getLocationList(String token, Long sapId, String deviceType) throws ServiceException {

		List<EntityResponseDto> responseList = new ArrayList<EntityResponseDto>();
		
		try 
		{
			List<EntityTRF> entityTRFList = afiliadoRepository.findAllProvincias();
			entityTRFList.addAll(afiliadoRepository.findAllLocalidades());
			
			for(EntityTRF entityTRF : entityTRFList)
			{
				EntityResponseDto response = new EntityResponseDto();
				response.setCodigo(entityTRF.getCodigo());
				response.setDescripcion(entityTRF.getDescripcion());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener lista de departamentos");
		}
		
		return responseList;
	}


	@Override
	public List<QueryTypeResponseDto> getQueryTypeList(String token, Long sapId, String deviceType) throws ServiceException {

		List<QueryTypeResponseDto> responseList = new ArrayList<QueryTypeResponseDto>();
		
		entityManager.unwrap(Session.class).enableFilter("tipo_categoria_filter");
		
		try 
		{
			List<TipoConsulta> tipoConsultaList = tipoConsultaRepository.findAllByFechaDeleteIsNullOrderByIdAsc();
			for(TipoConsulta tipoConsulta : tipoConsultaList)
			{
				QueryTypeResponseDto response = new QueryTypeResponseDto();
				response.setId(tipoConsulta.getId());
				response.setNombre(tipoConsulta.getDescripcion());
				
				for(TipoCategoria tipoCategoria : tipoConsulta.getTipoCategoriaList()) 
				{
					CategoryTypeResponseDto categoryResponse = new CategoryTypeResponseDto();
					categoryResponse.setId(tipoCategoria.getId());
					categoryResponse.setNombre(tipoCategoria.getDescripcion());
					response.getTipoCategoriaList().add(categoryResponse);
				}

				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener tipos de categoria");
		}
		
		return responseList;
	}

	@Override
	public List<EntityResponseDto> getDocumentationTypeList(String token, Long sapId, String deviceType) throws ServiceException {
		List<EntityResponseDto> responseList = new ArrayList<EntityResponseDto>();

		try
		{
			List<TipoDocumentacion> tipoConsultaList = tipoDocumentacionRepository.findAllByFechaDeleteIsNullOrderByIdAsc();

			for(TipoDocumentacion tipo : tipoConsultaList)
			{
				EntityResponseDto response = new EntityResponseDto();
				response.setCodigo(tipo.getCodigo());
				response.setDescripcion(tipo.getDescripcion());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener lista de departamentos");
		}

		return responseList;
	}
}
