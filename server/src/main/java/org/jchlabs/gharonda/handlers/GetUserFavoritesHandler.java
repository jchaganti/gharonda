package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.domain.model.Favorites;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.FavoritesDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.GetUserFavorites;
import org.jchlabs.gharonda.shared.rpc.GetUserPropertiesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle logout requests
 * 
 */
public class GetUserFavoritesHandler extends AbstractSessionHandler<GetUserFavorites, GetUserPropertiesResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<FavoritesDAO> favoritesDAOProvider;

	@Inject
	public GetUserFavoritesHandler(Log logger, Provider<HttpSession> provider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<FavoritesDAO> fDAOProvider) {
		super(logger, provider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.favoritesDAOProvider = fDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hupa.server.handler.AbstractSessionHandler#executeInternal (org.apache.hupa.shared.rpc.Session,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public GetUserPropertiesResult executeInternal(GetUserFavorites action, ExecutionContext arg1)
			throws ActionException {
		Users user = getUser();
		ContentholderDAO cDAO = contentholderDAOProvider.get();

		FavoritesDAO fDAO = favoritesDAOProvider.get();
		List<SearchCriteriaIFace> criteriaList = new ArrayList<SearchCriteriaIFace>();
		NumberSearchCriteria uidSrchCriteria = new NumberSearchCriteria("UserId", user.getId());
		criteriaList.add(uidSrchCriteria);
		SearchService service = new SearchService(Favorites.class, (org.jchlabs.gharonda.domain.pom.dao.FavoritesDAO) fDAO);
		List<Object> favorites = service.search(criteriaList);
		List<PropertiesDTO> pList = new ArrayList<PropertiesDTO>();
		for (Object o : favorites) {
			Favorites f = (Favorites) o;
			Integer pid = f.getPid();
			try {
				PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map((Properties) cDAO.get(pid),
						PropertiesDTO.class);
				pList.add(pDTO);
			} catch (Exception e) {

			}

		}
		return new GetUserPropertiesResult(pList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<GetUserFavorites> getActionType() {
		return GetUserFavorites.class;
	}

	@Override
	public void undo(GetUserFavorites action, GetUserPropertiesResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
