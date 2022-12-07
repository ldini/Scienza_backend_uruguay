package ar.com.scienza.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.PasswordRecoveryRequestDto;
import ar.com.scienza.dto.PasswordRecoveryResponseDto;
import ar.com.scienza.dto.UserResponseDto;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.entity.Usuario;
import ar.com.scienza.enumerator.RolEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.AfiliadoRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.repository.UsuarioRepository;
import ar.com.scienza.service.IPasswordRecoveryService;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class PasswordRecoveryService implements IPasswordRecoveryService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SesionRepository sesionRepository;


	@Override
	public PasswordRecoveryResponseDto recoverPassword(PasswordRecoveryRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		PasswordRecoveryResponseDto response = new PasswordRecoveryResponseDto();
		
		Afiliado afiliado = null;
		Sesion sesion = null;
		UserResponseDto userResponse = null;
	
		try 
		{
			afiliado = afiliadoRepository.findBycedulaIdentidadAndSapId(request.getcedulaIdentidad(), request.getSapId());
			if(afiliado == null) {
				throw new ServiceException("Número de Usuario o DNI incorrecto");
			}
			
			Usuario usuario = afiliado.getUsuario();
			usuario.setPassword(request.getPassword());
			usuario.updateOnRepository(usuarioRepository);
			
			sesion = new Sesion();
			sesion.setToken(UUID.randomUUID().toString().toUpperCase());
			sesion.setTipoDispositivo(deviceType);
			sesion.setRol(RolEnum.AFILIADO.getCodigo());
			sesion.setAfiliado(afiliado);
			sesion.createOnRepository(sesionRepository);

			userResponse = new UserResponseDto();
			userResponse.setAvatar(afiliado.getAvatar());
			userResponse.setNombre(afiliado.getNombre());
			userResponse.setApellido(afiliado.getApellido());
			userResponse.setcedulaIdentidad(afiliado.getcedulaIdentidad());
			userResponse.setSapId(afiliado.getSapId());
			userResponse.setVerificarPerfil(afiliado.getVerificarPerfil());
			userResponse.setPermisos(afiliado.getUsuario().getRol().getGrantsToString(afiliado));
			
			response.setToken(sesion.getToken());
			response.setRol(sesion.getRol());
			response.setUsuario(userResponse);
			
			for(Afiliado afiliadoVinculado : afiliado.getAfiliadosVinculados())
			{
				userResponse = new UserResponseDto();
				userResponse.setAvatar(afiliadoVinculado.getAvatar());
				userResponse.setNombre(afiliadoVinculado.getNombre());
				userResponse.setApellido(afiliadoVinculado.getApellido());
				userResponse.setcedulaIdentidad(afiliadoVinculado.getcedulaIdentidad());
				userResponse.setSapId(afiliadoVinculado.getSapId());
				userResponse.setVerificarPerfil(afiliadoVinculado.getVerificarPerfil());
				userResponse.setPermisos(usuario.getRol().getGrantsToString(afiliadoVinculado));
				response.getOtrosUsuarios().add(userResponse);
			}
		}
		catch (ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error en la recuperación de contraseña");
		}
		
		return response;
	}
}
