package ar.com.scienza.repository;

import java.util.List;

import ar.com.scienza.transformer.ReportActionsAffiliateTRF;
import ar.com.scienza.transformer.ReportUniverseAffiliateTRF;

public interface AfiliadoRepositoryCustom {

	List<ReportUniverseAffiliateTRF> getUniverseAffiliateList();

	List<ReportActionsAffiliateTRF> getActionsAffiliateList(String startDate, String finishDate);
	
}
