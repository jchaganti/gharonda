package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.shared.rpc.GetProperty;
import org.jchlabs.gharonda.shared.rpc.GetPropertyResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetPropertyHandler extends AbstractSessionHandler<GetProperty, GetPropertyResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;

	@Inject
	public GetPropertyHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<ContentitemsDAO> itemDAOProvider) {
		super(logger, sessionProvider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
	}

	@Override
	protected GetPropertyResult executeInternal(GetProperty action, ExecutionContext context) throws ActionException {
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		ContentitemsDAO itemDAO = itemDAOProvider.get();
		if (logger.isDebugEnabled()) {
			logger.debug("The id = " + action.getPid());
		}
		Integer id = action.getPid();

		System.out.println("the id in GetProperty =" + id);
		Properties property = (Properties) cDAO.get(id);
		Users user = null;
		if (action.isUserData()) {
			user = property.getUser();
		}
		PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(property, PropertiesDTO.class);
		return new GetPropertyResult(pDTO, user);
	}

	@Override
	public Class<GetProperty> getActionType() {
		return GetProperty.class;
	}

	@Override
	public void undo(GetProperty action, GetPropertyResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

}
