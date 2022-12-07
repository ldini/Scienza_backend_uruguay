package ar.com.scienza.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.TemplateRequestDto;
import ar.com.scienza.dto.TemplateResponseDto;
import ar.com.scienza.dto.TemplateResultResponseDto;
import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.Template;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.repository.TemplateRepository;
import ar.com.scienza.service.ITemplateService;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class TemplateService implements ITemplateService {

	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	private SesionRepository sesionRepository;


	@Override
	public List<TemplateResultResponseDto> getTemplateList(String token, Long sapId, String deviceType) throws ServiceException {

		List<TemplateResultResponseDto> responseList = new ArrayList<TemplateResultResponseDto>();
		
		try 
		{
			List<Template> templateList = templateRepository.findAllByFechaDeleteIsNullOrderByTituloAsc();
			for(Template template : templateList)
			{
				TemplateResultResponseDto response = new TemplateResultResponseDto();
				response.setTemplateId(template.getId());
				response.setTitulo(template.getTitulo());
				response.setDescripcion(template.getDescripcion());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener templates de mensaje");
		}
		
		return responseList;
	}


	@Override
	public TemplateResponseDto getTemplate(Integer templateId, String token, Long sapId, String deviceType) throws ServiceException {

		TemplateResponseDto response = new TemplateResponseDto();
		
		try 
		{
			Template template = templateRepository.findOne(templateId);
			response.setTemplateId(template.getId());
			response.setTitulo(template.getTitulo());
			response.setDescripcion(template.getDescripcion());
			response.setContenido(template.getContenido());
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener template de mensaje");
		}
		
		return response;
	}


	@Override
	public void createTemplate(TemplateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();
			
			Template template = new Template();
			template.setAdministrador(administrador);
			template.setTitulo(request.getTitulo());
			template.setDescripcion(request.getDescripcion());
			template.setContenido(request.getContenido());
			template.createOnRepository(templateRepository);			
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al crear template de mensaje");
		}
	}


	@Override
	public void updateTemplate(TemplateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();
			
			Template template = templateRepository.findOne(request.getTemplateId());
			template.setAdministrador(administrador);
			template.setTitulo(request.getTitulo());
			template.setDescripcion(request.getDescripcion());
			template.setContenido(request.getContenido());
			template.updateOnRepository(templateRepository);			
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al modificar template de mensaje");
		}
	}


	@Override
	public void deleteTemplate(TemplateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();
			
			Template template = templateRepository.findOne(request.getTemplateId());
			template.setAdministrador(administrador);
			template.deleteOnRepository(templateRepository);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al eliminar template de mensaje");
		}
	}
}
