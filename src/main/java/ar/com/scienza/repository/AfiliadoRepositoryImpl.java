package ar.com.scienza.repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import ar.com.scienza.enumerator.AccionAfiliadoEnum;
import ar.com.scienza.transformer.ReportActionsAffiliateTRF;
import ar.com.scienza.transformer.ReportUniverseAffiliateTRF;
import ar.com.scienza.utils.EnumeratorUtil;

public class AfiliadoRepositoryImpl implements AfiliadoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportUniverseAffiliateTRF> getUniverseAffiliateList() {
		StoredProcedureQuery spQuery = em.createNamedStoredProcedureQuery("getUniversoAfiliados");

		List<Object[]> results = spQuery.getResultList();
		List<ReportUniverseAffiliateTRF> list = new ArrayList<ReportUniverseAffiliateTRF>();
		
		for(Object[] object : results) {
			
			ReportUniverseAffiliateTRF dto = new ReportUniverseAffiliateTRF();
			
			if(object[0] != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(((Timestamp) object[0]).getTime());
				dto.setFechaAlta(cal.getTime());
			}
			dto.setSapId((object[1] != null) ? Integer.valueOf(object[1].toString()) : null);
			dto.setNombre((object[2] != null) ? object[2].toString() : null);
			dto.setApellido((object[3] != null) ? object[3].toString() : null);
			dto.setcedulaIdentidad((object[4] != null) ? (object[4].toString()) : null);
			dto.setNumeroAfiliado((object[5] != null) ? object[5].toString() : null);
			dto.setSeguroMedico((object[6] != null) ? object[6].toString() : null);
			dto.setEmail((object[7] != null) ? object[7].toString() : null);
			if(object[8] != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(((Timestamp) object[8]).getTime());
				dto.setFechaUltimaTransaccion(cal.getTime());
			}
			list.add(dto);
		}
		
		return list;
	}

	@Override
	public List<ReportActionsAffiliateTRF> getActionsAffiliateList(String startDate, String finishDate) {

		StoredProcedureQuery spQuery = em.createNamedStoredProcedureQuery("getAccionesAfiliados");

		spQuery.setParameter("fecha_inicio", startDate);
		spQuery.setParameter("fecha_fin", finishDate + " 23:59:59");

		List<Object[]> results = spQuery.getResultList();
		List<ReportActionsAffiliateTRF> list = new ArrayList<ReportActionsAffiliateTRF>();


		for (Object[] object : results){

			ReportActionsAffiliateTRF trf = new ReportActionsAffiliateTRF();

			if(object[0] != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(((Timestamp) object[0]).getTime());
				trf.setFechaAccion(cal.getTime());
			}
			String accion = (object[1] != null) ? object[1].toString() : null;
			if (accion != null){
				AccionAfiliadoEnum accionEnum = EnumeratorUtil.valueOf(AccionAfiliadoEnum.class, accion);
				accion = accionEnum.getDescripcion();
			}
			trf.setAccion(accion);
			trf.setTipoDispositivo((object[2] != null) ? object[2].toString() : null);
			trf.setSapId((object[3] != null) ? Integer.valueOf(object[3].toString()) : null);
			trf.setSeguroMedico((object[4] != null) ? object[4].toString() : null);
			list.add(trf);

		}

		return list;
	}
}
