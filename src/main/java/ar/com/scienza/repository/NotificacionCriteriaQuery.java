package ar.com.scienza.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.CharMatcher;

import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.CompaniaCelular;
import ar.com.scienza.entity.SeguroMedico;


@Component
public class NotificacionCriteriaQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private AfiliadoRepository afiliadoRepository;
	
	
	public List<Afiliado> findAllMessageSenderByFilters(
		List<String> namingList, 
		List<String> healthInsuranceList,
		List<String> identificationList,
		List<String> genderList,
		String birthdate,
		List<String> locationProvinceList,
		List<String> phoneList,
		List<String> cellCompanyList,
		List<String> emailList		
	) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Afiliado> query = builder.createQuery(Afiliado.class);
		
		Root<Afiliado> root = query.from(Afiliado.class);
		Join<Afiliado, SeguroMedico> joinSM = root.join("seguroMedico", JoinType.INNER);
		Join<Afiliado, CompaniaCelular> joinCC = root.join("companiaCelular", JoinType.LEFT);
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
				
		if(namingList.size() > 0) 
		{
			List<Predicate> predicateNamingList = new ArrayList<Predicate>();
			
			Expression<String> expFirstName = builder.concat(root.<String>get("nombre"), " ");
			expFirstName = builder.concat(expFirstName, root.<String>get("apellido"));
			
			Expression<String> expLastName = builder.concat(root.<String>get("apellido"), " ");
			expLastName = builder.concat(expLastName, root.<String>get("nombre"));
			
			for(String naming : namingList)
			{
				predicateNamingList.add(
					builder.or(
						builder.like(expFirstName, this.normalizeValue(naming)),
						builder.like(expLastName, this.normalizeValue(naming)),
						builder.equal(root.<String>get("apodo"), naming.trim().toUpperCase())
					)
				);
			}
			
			predicateList.add(
				builder.or(predicateNamingList.toArray(new Predicate[predicateNamingList.size()]))
			);
		}
		
		if(healthInsuranceList.size() > 0) 
		{
			predicateList.add(
				joinSM.get("sapId").in(healthInsuranceList)
			);
		}

		if(identificationList.size() > 0)
		{
			predicateList.add(
				root.get("cedulaIdentidad").in(identificationList)
			);
		}

		if(genderList.size() > 0) 
		{
			predicateList.add(
				root.get("sexo").in(genderList)
			);
		}
		
		if(birthdate != null)
		{
			Expression<String> birthdatePattern = builder.literal("%d/%m/%a");
			Expression<String> birthdateExp = builder.function("DATE_FORMAT", String.class, root.<String>get("fechaNacimiento"), birthdatePattern);
			
			predicateList.add(
				builder.equal(birthdateExp, birthdate)
			);
		}

		List<String> provinceList = new ArrayList<String>();
		List<String> locationList = new ArrayList<String>();
		
		for(String locationProvince : locationProvinceList)
		{
			if(locationProvince.startsWith("P-"))
				provinceList.add(afiliadoRepository.findOne(Integer.valueOf(locationProvince.replace("P-", ""))).getProvincia());
			if(locationProvince.startsWith("L-"))
				locationList.add(afiliadoRepository.findOne(Integer.valueOf(locationProvince.replace("L-", ""))).getLocalidad());
		}
		
		if(provinceList.size() > 0 && locationList.size() > 0) 
		{
			predicateList.add(
				builder.or(
					root.get("provincia").in(provinceList),
					root.get("localidad").in(locationList)
				)
			);
		}
		else if(provinceList.size() > 0) 
		{
			predicateList.add(
				root.get("provincia").in(provinceList)
			);
		}
		else if(locationList.size() > 0) 
		{
			predicateList.add(
				root.get("localidad").in(locationList)
			);
		}
		
		if(phoneList.size() > 0) 
		{
			List<Predicate> predicatePhoneList = new ArrayList<Predicate>();
			
			Expression<String> expPersonalPhone = builder.function("EXTRACT_NUMBERS", String.class, root.<String>get("telefonoParticular"));
			Expression<String> expWorkPhone = builder.function("EXTRACT_NUMBERS", String.class, root.<String>get("telefonoLaboral"));
			Expression<String> expCellPhone = builder.function("EXTRACT_NUMBERS", String.class, root.<String>get("telefonoCelular"));
			
			for(String phone : phoneList)
			{
				String phoneNormalized = this.normalizeValue(CharMatcher.DIGIT.retainFrom(phone));
				predicatePhoneList.add(
					builder.or(
						builder.like(expPersonalPhone, phoneNormalized),
						builder.like(expWorkPhone, phoneNormalized),
						builder.like(expCellPhone, phoneNormalized)
					)				
				);
			}
			
			predicateList.add(
				builder.or(predicatePhoneList.toArray(new Predicate[predicatePhoneList.size()]))
			);
		}
		
		if(cellCompanyList.size() > 0) 
		{
			predicateList.add(
				joinCC.get("codigo").in(cellCompanyList)
			);
		}

		if(emailList.size() > 0) 
		{
			predicateList.add(
				root.get("email").in(emailList)
			);
		}

		// Dynamic conditions
		query.select(root);
		query.where(builder.and(predicateList.toArray(new Predicate[predicateList.size()])));
		
		// Ordering
		query.orderBy(builder.asc(root.get("apellido")), builder.asc(root.get("nombre")));
		
		return entityManager.createQuery(query).getResultList();
	}
	
	
	/**
	 * Elimina espacios innecesarios
	 * agrega los % para poder utilizar LIKE
	 * @param value
	 * @return
	 */
	private String normalizeValue(String value) {
		
		return "%" + value.trim().replaceAll(" +", "% %").replace(",", "") + "%";
	}
}
