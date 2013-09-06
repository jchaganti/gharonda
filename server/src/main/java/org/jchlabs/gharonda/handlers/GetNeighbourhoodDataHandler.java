package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.Neightbourhood;
import org.jchlabs.gharonda.domain.pom.dao.iface.NeightbourhoodDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.GetNeighbourhoodData;
import org.jchlabs.gharonda.shared.rpc.GetNeighbourhoodDataResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle logout requests
 * 
 */
public class GetNeighbourhoodDataHandler implements ActionHandler<GetNeighbourhoodData, GetNeighbourhoodDataResult> {

	private Provider<NeightbourhoodDAO> neighbourhoodDAOProvider;

	@Inject
	public GetNeighbourhoodDataHandler(Log logger, Provider<HttpSession> provider,
			Provider<NeightbourhoodDAO> neighbourhoodDAOProvider) {
		this.neighbourhoodDAOProvider = neighbourhoodDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<GetNeighbourhoodData> getActionType() {
		return GetNeighbourhoodData.class;
	}

	@Override
	public GetNeighbourhoodDataResult execute(GetNeighbourhoodData action, ExecutionContext arg1)
			throws ActionException {
		NeightbourhoodDAO nDAO = neighbourhoodDAOProvider.get();

		SearchService service = new SearchService(Neightbourhood.class,
				(org.jchlabs.gharonda.domain.pom.dao.NeightbourhoodDAO) nDAO, null);
		service.setORSearchCriteriaList(action.getNeighbourhoodSelectionList());

		List<Object> oList = service.search(action.getMapBounds());
		List<Neightbourhood> nList = new ArrayList<Neightbourhood>();
		for (Object o : oList) {
			nList.add((Neightbourhood) o);
		}
		System.out.println("The neighbourhood size =" + nList.size());
		return new GetNeighbourhoodDataResult(nList);
	}

	@Override
	public void undo(GetNeighbourhoodData action, GetNeighbourhoodDataResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
