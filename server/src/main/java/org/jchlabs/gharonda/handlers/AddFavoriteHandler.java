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
import org.jchlabs.gharonda.shared.rpc.AddFavorite;
import org.jchlabs.gharonda.shared.rpc.AddFavoriteResult;
import org.jchlabs.gharonda.shared.rpc.Status;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle AddFavorite requests
 * 
 */
public class AddFavoriteHandler extends AbstractSessionHandler<AddFavorite, AddFavoriteResult> {

	private Provider<FavoritesDAO> favoritesDAOProvider;

	@Inject
	public AddFavoriteHandler(Log logger, Provider<HttpSession> provider, Provider<FavoritesDAO> favoritesDAOProvider) {
		super(logger, provider);
		this.favoritesDAOProvider = favoritesDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hupa.server.handler.AbstractSessionHandler#executeInternal (org.apache.hupa.shared.rpc.Session,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public AddFavoriteResult executeInternal(AddFavorite action, ExecutionContext arg1) throws ActionException {
		Users user = getUser();
		AddFavoriteResult result = null;
		if (user == null) {
			result = new AddFavoriteResult(Status.FAIL);
		} else {
			Integer pid = action.getPid();
			FavoritesDAO fDAO = favoritesDAOProvider.get();
			List<SearchCriteriaIFace> criteriaList = new ArrayList<SearchCriteriaIFace>();
			NumberSearchCriteria uidSrchCriteria = new NumberSearchCriteria("UserId", user.getId());
			criteriaList.add(uidSrchCriteria);
			NumberSearchCriteria pidSrchCriteria = new NumberSearchCriteria("Pid", pid);
			criteriaList.add(pidSrchCriteria);
			SearchService service = new SearchService(Favorites.class,
					(org.jchlabs.gharonda.domain.pom.dao.FavoritesDAO) fDAO);
			List<Object> favorites = service.search(criteriaList);
			if (favorites.size() > 0) {
				result = new AddFavoriteResult(Status.ALREADY_EXISTS);
			} else {
				Favorites favorite = new Favorites();
				favorite.setUserId(user.getId());
				favorite.setPid(pid);
				fDAO.save(favorite);
				result = new AddFavoriteResult(Status.SUCCESS);
			}

		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<AddFavorite> getActionType() {
		return AddFavorite.class;
	}

	@Override
	public void undo(AddFavorite action, AddFavoriteResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}
}
