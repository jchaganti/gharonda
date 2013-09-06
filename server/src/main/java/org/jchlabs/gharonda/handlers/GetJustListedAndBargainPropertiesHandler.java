package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.common.ContentholderWIPState;
import org.jchlabs.gharonda.domain.model.BaseProperties;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;
import org.jchlabs.gharonda.shared.rpc.GetJustListedAndBargainProperties;
import org.jchlabs.gharonda.shared.rpc.GetJustListedAndBargainPropertiesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetJustListedAndBargainPropertiesHandler implements
		ActionHandler<GetJustListedAndBargainProperties, GetJustListedAndBargainPropertiesResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private FetchProfile fetchProfile;
	private List<SearchCriteriaIFace> jlSC;
	private List<SearchCriteriaIFace> bSC;
	private SearchService service;

	@Inject
	public GetJustListedAndBargainPropertiesHandler(Log logger, Provider<HttpSession> provider,
			Provider<ContentholderDAO> contentholderDAOProvider) {
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.fetchProfile = new FetchProfile(5, 0, BaseProperties.PROP_TIME_STAMP, false);

		jlSC = new ArrayList<SearchCriteriaIFace>();
		SearchCriteriaIFace c1 = new NumberSearchCriteria(BaseProperties.PROP_ISACTIVE, 1);
		SearchCriteriaIFace c2 = new NumberSearchCriteria(BaseProperties.PROP_WIP_STATE,
				ContentholderWIPState.CHECKED_IN.value());
		jlSC.add(c1);
		jlSC.add(c2);

		bSC = new ArrayList<SearchCriteriaIFace>();
		bSC.add(c1);
		bSC.add(c2);
		SearchCriteriaIFace c3 = new NumberSearchCriteria(BaseProperties.PROP_IS_BARGAIN, 1);
		bSC.add(c3);
		ContentholderDAO cDAO = this.contentholderDAOProvider.get();
		service = new SearchService(Contentholder.class, (org.jchlabs.gharonda.domain.pom.dao.ContentholderDAO) cDAO,
				this.fetchProfile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<GetJustListedAndBargainProperties> getActionType() {
		return GetJustListedAndBargainProperties.class;
	}

	@Override
	public GetJustListedAndBargainPropertiesResult execute(GetJustListedAndBargainProperties action,
			ExecutionContext arg1) throws ActionException {
		List<Object> ojlList = service.search(jlSC);
		List<Object> obList = service.search(bSC);
		;

		if (obList == null || obList.size() < 5) {
			obList = addRandomListings(obList, service);
		}

		List<PropertiesDTO> pbList = null;
		if (obList != null) {
			pbList = new ArrayList<PropertiesDTO>();
			for (Object o : obList) {
				Properties p = (Properties) o;
				PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(p, PropertiesDTO.class);
				pbList.add(pDTO);
			}
		}

		List<PropertiesDTO> pjlList = null;
		if (ojlList != null) {
			pjlList = new ArrayList<PropertiesDTO>();
			for (Object o : ojlList) {
				Properties p = (Properties) o;
				PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(p, PropertiesDTO.class);
				pjlList.add(pDTO);
			}
		}

		return new GetJustListedAndBargainPropertiesResult(pbList, pjlList);
	}

	private List<Object> addRandomListings(List<Object> oList, SearchService service) {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		SearchCriteriaIFace c1 = new NumberSearchCriteria(BaseProperties.PROP_ISACTIVE, 1);
		SearchCriteriaIFace c2 = new NumberSearchCriteria(BaseProperties.PROP_WIP_STATE,
				ContentholderWIPState.CHECKED_IN.value());
		cList.add(c1);
		cList.add(c2);
		if (oList != null) {
			service.getFetchProfile().setResultsSize(5 - oList.size());
		}
		List<Object> rList = service.search(cList);
		if (oList != null) {
			service.getFetchProfile().setResultsSize(5);
			for (Object o : rList) {
				oList.add(o);
			}
		} else {
			oList = rList;
		}
		return oList;
	}

	@Override
	public void undo(GetJustListedAndBargainProperties action, GetJustListedAndBargainPropertiesResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}
}
