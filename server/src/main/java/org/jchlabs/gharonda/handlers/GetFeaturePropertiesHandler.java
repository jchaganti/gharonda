package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.common.ContentholderWIPState;
import org.jchlabs.gharonda.common.Utils;
import org.jchlabs.gharonda.domain.model.BaseProperties;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.pom.dao.iface.PropertiesDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingType;
import org.jchlabs.gharonda.shared.rpc.GetFeaturedProperties;
import org.jchlabs.gharonda.shared.rpc.GetFeaturedPropertiesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetFeaturePropertiesHandler implements ActionHandler<GetFeaturedProperties, GetFeaturedPropertiesResult> {

	private Provider<PropertiesDAO> propertiesDAOProvider;

	@Inject
	public GetFeaturePropertiesHandler(Log logger, Provider<HttpSession> provider,
			Provider<PropertiesDAO> propertiesDAOProvider) {
		this.propertiesDAOProvider = propertiesDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<GetFeaturedProperties> getActionType() {
		return GetFeaturedProperties.class;
	}

	@Override
	public GetFeaturedPropertiesResult execute(GetFeaturedProperties action, ExecutionContext arg1)
			throws ActionException {
		// ContentholderDAO cDAO = contentholderDAOProvider.get();
		PropertiesDAO pDAO = propertiesDAOProvider.get();
		SearchService service = new SearchService(Properties.class,
				(org.jchlabs.gharonda.domain.pom.dao.PropertiesDAO) pDAO, action.getFetchProfile());
		List<Object> oList = null;
		List<Object> rList = null;
		List<SearchCriteriaIFace> andSC = action.getANDSearchCriteria();
		if (andSC == null) {
			andSC = new ArrayList<SearchCriteriaIFace>();
		}
		andSC.add(new NumberSearchCriteria(BaseProperties.PROP_ISACTIVE, 1));
		andSC.add(new NumberSearchCriteria(BaseProperties.PROP_IS_FEATURED, 1));
		andSC.add(new NumberSearchCriteria(BaseProperties.PROP_WIP_STATE, ContentholderWIPState.CHECKED_IN.value()));

		if (FeaturedListingType.SEARCH.equals(action.getFeaturedListingType())) {
			if (action.getORSearchCriteria() != null) {
				// From Basic Search
				service.setORSearchCriteriaList(action.getORSearchCriteria());
				oList = service.search(andSC);
				if (oList == null || oList.size() < action.getFetchProfile().getResultsSize()) {
					rList = getRandomFeaturedListings(oList, service, action.getFetchProfile().getResultsSize());
				}
			} else {
				Utils.adjustCriteria(andSC);
				service.setORSearchCriteriaList(action.getORSearchCriteria());
				oList = service.search(andSC);
				if (oList == null || oList.size() < action.getFetchProfile().getResultsSize()) {
					if (Utils.removeCriteria(BaseProperties.PROP_SUBURB, andSC)) {
						oList = service.search(andSC);
						if (oList == null || oList.size() < action.getFetchProfile().getResultsSize()
								&& Utils.removeCriteria(BaseProperties.PROP_CITY, andSC)) {
							oList = service.search(andSC);
							if (oList == null || oList.size() < action.getFetchProfile().getResultsSize()) {
								rList = getRandomFeaturedListings(oList, service, action.getFetchProfile()
										.getResultsSize());

							}
						}
					}
				}
			}

		} else {
			if (action.getORSearchCriteria() != null) {
				service.setORSearchCriteriaList(action.getORSearchCriteria());
			}

			if (andSC != null) {
				oList = service.search(andSC);
			}

			if (oList == null || oList.size() < action.getFetchProfile().getResultsSize()) {
				rList = getRandomFeaturedListings(oList, service, action.getFetchProfile().getResultsSize());
			}
		}

		List<PropertiesDTO> opList = null;
		if (oList != null) {
			opList = new ArrayList<PropertiesDTO>();
			for (Object o : oList) {
				Properties p = (Properties) o;
				PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(p, PropertiesDTO.class);
				opList.add(pDTO);
			}
		}

		if (rList != null) {
			if (opList == null) {
				opList = new ArrayList<PropertiesDTO>();
			}
			int size = opList.size();
			for (Object o : rList) {
				Properties p = (Properties) o;
				PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(p, PropertiesDTO.class);
				opList.add(size++, pDTO);
			}
		}

		return new GetFeaturedPropertiesResult(opList);
	}

	private List<Object> getRandomFeaturedListings(List<Object> oList, SearchService service, int reqSize) {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		SearchCriteriaIFace c1 = new NumberSearchCriteria(BaseProperties.PROP_ISACTIVE, 1);
		cList.add(c1);
		SearchCriteriaIFace c2 = new NumberSearchCriteria(BaseProperties.PROP_IS_FEATURED, 1);
		cList.add(c2);
		SearchCriteriaIFace c3 = new NumberSearchCriteria(BaseProperties.PROP_WIP_STATE,
				ContentholderWIPState.CHECKED_IN.value());
		cList.add(c3);
		List<Object> rList = service.search(cList);
		if (oList != null) {
			rList.removeAll(oList);
		}
		return rList;
	}

	@Override
	public void undo(GetFeaturedProperties action, GetFeaturedPropertiesResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
