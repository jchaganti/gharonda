package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.StringEqualSearchCriteria;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.LoginUser;
import org.jchlabs.gharonda.shared.rpc.LoginUserResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for login a user via email and password
 * 
 */
public class LoginUserHandler implements ActionHandler<LoginUser, LoginUserResult> {

	private final Log logger;
	private final Provider<HttpSession> sessionProvider;
	private final Provider<UsersDAO> uDAOProvider;

	@Inject
	public LoginUserHandler(Log logger, Provider<HttpSession> sessionProvider, Provider<UsersDAO> uDAOProvider) {

		this.logger = logger;
		this.sessionProvider = sessionProvider;
		this.uDAOProvider = uDAOProvider;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#execute(net.customware.gwt.dispatch.shared.Action,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public LoginUserResult execute(LoginUser action, ExecutionContext context) throws ActionException {
		String email = action.getEmail();
		String password = action.getPassword();
		try {

			// construct a new user
			UsersDAO uDAO = uDAOProvider.get();
			List<SearchCriteriaIFace> criteriaList = new ArrayList<SearchCriteriaIFace>();
			StringEqualSearchCriteria emailSrchCriteria = new StringEqualSearchCriteria("Email", email);
			criteriaList.add(emailSrchCriteria);
			SearchService service = new SearchService(Users.class, (org.jchlabs.gharonda.domain.pom.dao.UsersDAO) uDAO);
			List<Object> users = service.search(criteriaList);
			Users user = null;
			if (users.size() == 1) {
				user = (Users) users.get(0);
				if (!user.getPasswd().equals(password)) {
					user = null;
				}
			}
			// store the session id for later usage
			HttpSession session = sessionProvider.get();
			session.setAttribute("user", user);
			return new LoginUserResult(user);

		} catch (Exception e) {
			logger.error("Unable to authenticate user " + email);
			throw new ActionException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<LoginUser> getActionType() {
		return LoginUser.class;
	}

	@Override
	public void undo(LoginUser action, LoginUserResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}
}
