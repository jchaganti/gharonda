package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.common.ContentholderWIPState;
import org.jchlabs.gharonda.domain.model.BaseProperties;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.PropertiesDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.GetSearchProperties;
import org.jchlabs.gharonda.shared.rpc.GetSearchPropertiesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle logout requests
 * 
 */
public class GetSearchPropertiesHandler implements ActionHandler<GetSearchProperties, GetSearchPropertiesResult> {

	private Provider<PropertiesDAO> propertiesDAOProvider;

	@Inject
	public GetSearchPropertiesHandler(Log logger, Provider<HttpSession> provider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<PropertiesDAO> propertiesDAOProvider) {
		this.propertiesDAOProvider = propertiesDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<GetSearchProperties> getActionType() {
		return GetSearchProperties.class;
	}

	@Override
	public GetSearchPropertiesResult execute(GetSearchProperties action, ExecutionContext arg1) throws ActionException {
		// ContentholderDAO cDAO = contentholderDAOProvider.get();
		PropertiesDAO pDAO = propertiesDAOProvider.get();
		SearchService service = new SearchService(Properties.class,
				(org.jchlabs.gharonda.domain.pom.dao.PropertiesDAO) pDAO, action.getFetchProfile());
		service.setORSearchCriteriaList(action.getSearchTextList());
		int realCount = -1;
		int fetchSize = action.getFetchProfile().getResultsSize();
		System.out.println("The fetch Size = " + fetchSize + " first result cursor at "
				+ action.getFetchProfile().getFirstResult());
		List<SearchCriteriaIFace> andSC = action.getSearchSelectionList();
		if (andSC == null) {
			andSC = new ArrayList<SearchCriteriaIFace>();
		}
		andSC.add(new NumberSearchCriteria(BaseProperties.PROP_ISACTIVE, 1));
		andSC.add(new NumberSearchCriteria(BaseProperties.PROP_WIP_STATE, ContentholderWIPState.CHECKED_IN.value()));

		System.out.println("The AND condition = " + andSC);
		List<Object> oList = null;
		if (action.isInitLoading()) {
			int fetchSize1 = action.getFetchProfile().getResultsSize();
			System.out.println("The fetch Size during initLoading = " + fetchSize1 + " first result cursor at "
					+ action.getFetchProfile().getFirstResult());
			if (action.getPreDecidedResultsSize() > 0) {
				action.getFetchProfile().setResultsSize(action.getPreDecidedResultsSize());
			} else {
				action.getFetchProfile().setResultsSize(0);
			}

			oList = service.search(andSC);
			realCount = oList.size();
			action.getFetchProfile().setResultsSize(fetchSize1);
		} else {
			oList = service.search(andSC);
		}

		List<PropertiesDTO> pList = new ArrayList<PropertiesDTO>();
		for (Object o : oList) {
			Properties p = (Properties) o;
			PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(p, PropertiesDTO.class);
			pList.add(pDTO);
		}
		System.out.println("The search properties size =" + pList.size() + " real count = " + realCount);
		return new GetSearchPropertiesResult(pList, realCount);
	}

	@Override
	public void undo(GetSearchProperties action, GetSearchPropertiesResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
