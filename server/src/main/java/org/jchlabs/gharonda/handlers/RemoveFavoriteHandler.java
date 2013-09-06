package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.Favorites;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.FavoritesDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.RemoveFavorite;
import org.jchlabs.gharonda.shared.rpc.RemoveFavoriteResult;
import org.jchlabs.gharonda.shared.rpc.Status;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle RemoveFavorite requests
 * 
 */
public class RemoveFavoriteHandler extends AbstractSessionHandler<RemoveFavorite, RemoveFavoriteResult> {

	private Provider<FavoritesDAO> favoritesDAOProvider;

	@Inject
	public RemoveFavoriteHandler(Log logger, Provider<HttpSession> provider, Provider<FavoritesDAO> favoritesDAOProvider) {
		super(logger, provider);
		this.favoritesDAOProvider = favoritesDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hupa.server.handler.AbstractSessionHandler#executeInternal (org.apache.hupa.shared.rpc.Session,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public RemoveFavoriteResult executeInternal(RemoveFavorite action, ExecutionContext arg1) throws ActionException {
		Users user = getUser();
		RemoveFavoriteResult result = null;
		if (user == null) {
			result = new RemoveFavoriteResult(Status.FAIL);
		} else {
			List<Integer> pids = action.getPids();
			if (pids != null) {
				FavoritesDAO fDAO = favoritesDAOProvider.get();
				List<SearchCriteriaIFace> criteriaList = new ArrayList<SearchCriteriaIFace>();
				NumberSearchCriteria uidSrchCriteria = new NumberSearchCriteria("UserId", user.getId());
				criteriaList.add(uidSrchCriteria);
				SearchService service = new SearchService(Favorites.class,
						(org.jchlabs.gharonda.domain.pom.dao.FavoritesDAO) fDAO);
				List<Object> favorites = service.search(criteriaList);
				if (favorites.size() > 0) {
					for (Object o : favorites) {
						Favorites f = (Favorites) o;
						Integer pid = f.getPid();
						if (pids.contains(pid)) {
							fDAO.delete(f);
						}
					}
					result = new RemoveFavoriteResult(Status.SUCCESS);
				} else {
					result = new RemoveFavoriteResult(Status.FAIL);
				}
			} else {
				result = new RemoveFavoriteResult(Status.FAIL);
			}

		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<RemoveFavorite> getActionType() {
		return RemoveFavorite.class;
	}

	@Override
	public void undo(RemoveFavorite action, RemoveFavoriteResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
