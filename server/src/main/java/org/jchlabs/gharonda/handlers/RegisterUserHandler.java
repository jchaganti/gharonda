package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.Notifierprofiles;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Serviceproviderdetails;
import org.jchlabs.gharonda.domain.model.StringEqualSearchCriteria;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.NotifierprofilesDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ServiceproviderdetailsDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.RegisterUser;
import org.jchlabs.gharonda.shared.rpc.RegisterUserResult;
import org.jchlabs.gharonda.shared.rpc.RegistrationStatus;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for login a user via email and password
 * 
 */
public class RegisterUserHandler implements ActionHandler<RegisterUser, RegisterUserResult> {

	private final Log logger;
	private final Provider<HttpSession> sessionProvider;
	private final Provider<UsersDAO> uDAOProvider;
	private final Provider<ServiceproviderdetailsDAO> serviceProviderDAOProvider;
	private final Provider<NotifierprofilesDAO> notifierprofilesDAOProvider;

	@Inject
	public RegisterUserHandler(Log logger, Provider<HttpSession> sessionProvider, Provider<UsersDAO> uDAOProvider,
			Provider<ServiceproviderdetailsDAO> serviceProvider,
			Provider<NotifierprofilesDAO> notifierprofilesDAOProvider) {

		this.logger = logger;
		this.sessionProvider = sessionProvider;
		this.uDAOProvider = uDAOProvider;
		this.serviceProviderDAOProvider = serviceProvider;
		this.notifierprofilesDAOProvider = notifierprofilesDAOProvider;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#execute(net.customware.gwt.dispatch.shared.Action,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public RegisterUserResult execute(RegisterUser action, ExecutionContext context) throws ActionException {
		Users user = action.getUser();
		assert (user != null);
		try {
			System.out.println("The user email name from client =" + user.getEmail());
			UsersDAO uDAO = uDAOProvider.get();
			List<SearchCriteriaIFace> criteriaList = new ArrayList<SearchCriteriaIFace>();
			StringEqualSearchCriteria emailSrchCriteria = new StringEqualSearchCriteria("Email", user.getEmail());
			criteriaList.add(emailSrchCriteria);
			SearchService service = new SearchService(Users.class, (org.jchlabs.gharonda.domain.pom.dao.UsersDAO) uDAO);
			List<Object> users = service.search(criteriaList);
			Users _user = null;
			if (users.size() > 0) {
				_user = (Users) users.get(0);
				logger.debug("The user with email has been already registered " + _user.getEmail());
			}
			RegistrationStatus status = RegistrationStatus.FAIL;
			if (_user == null) {
				if (user.getServiceproviderdetails() != null) {
					Serviceproviderdetails serviceProviderDetails = user.getServiceproviderdetails();
					ServiceproviderdetailsDAO sDAO = serviceProviderDAOProvider.get();
					sDAO.save(serviceProviderDetails);
				}
				NotifierprofilesDAO nDAO = notifierprofilesDAOProvider.get();
				Notifierprofiles n = new Notifierprofiles();
				nDAO.save(n);
				user.setNotifierprofiles(n);
				Integer uid = uDAO.save(user);
				if (uid != null) {
					// store the session id for later usage
					HttpSession session = sessionProvider.get();
					session.setAttribute("user", user);
					status = RegistrationStatus.SUCCESS;
				} else {
					user = null;
					System.out.println("The usercould not be saved");
				}
			} else {
				status = RegistrationStatus.ALREADY_EXISTS;
			}

			return new RegisterUserResult(user, status);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unable to authenticate user " + user);
			throw new ActionException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<RegisterUser> getActionType() {
		return RegisterUser.class;
	}

	@Override
	public void undo(RegisterUser action, RegisterUserResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}
}
