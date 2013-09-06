package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.model.NumberRangeSearchCriteria;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.GetUserProperties;
import org.jchlabs.gharonda.shared.rpc.GetUserPropertiesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle logout requests
 * 
 */
public class GetUserPropertiesHandler extends AbstractSessionHandler<GetUserProperties, GetUserPropertiesResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;

	@Inject
	public GetUserPropertiesHandler(Log logger, Provider<HttpSession> provider,
			Provider<ContentholderDAO> contentholderDAOProvider) {
		super(logger, provider);
		this.contentholderDAOProvider = contentholderDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hupa.server.handler.AbstractSessionHandler#executeInternal (org.apache.hupa.shared.rpc.Session,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public GetUserPropertiesResult executeInternal(GetUserProperties action, ExecutionContext arg1)
			throws ActionException {
		Users user = getUser();
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		SearchService service = new SearchService(Contentholder.class,
				(org.jchlabs.gharonda.domain.pom.dao.ContentholderDAO) cDAO);
		List<SearchCriteriaIFace> criteriaList = new ArrayList<SearchCriteriaIFace>();
		NumberRangeSearchCriteria criteria = new NumberRangeSearchCriteria("WipState", true, 1, 2);
		// NumberRangeSearchCriteria criteria = new NumberRangeSearchCriteria("WipState", true, 1, 3);
		criteriaList.add(criteria);
		List<Object> oList = service.search(criteriaList, user);
		List<PropertiesDTO> pList = new ArrayList<PropertiesDTO>();

		for (Object o : oList) {
			Properties p = (Properties) o;
			PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(p, PropertiesDTO.class);
			pList.add(pDTO);
		}
		return new GetUserPropertiesResult(pList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<GetUserProperties> getActionType() {
		return GetUserProperties.class;
	}

	@Override
	public void undo(GetUserProperties action, GetUserPropertiesResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
