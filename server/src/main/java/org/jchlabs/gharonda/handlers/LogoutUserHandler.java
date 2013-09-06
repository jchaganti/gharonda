package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.LogoutUser;
import org.jchlabs.gharonda.shared.rpc.LogoutUserResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle logout requests
 * 
 */
public class LogoutUserHandler extends AbstractSessionHandler<LogoutUser, LogoutUserResult> {

	@Inject
	public LogoutUserHandler(Log logger, Provider<HttpSession> provider) {
		super(logger, provider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hupa.server.handler.AbstractSessionHandler#executeInternal(org.apache.hupa.shared.rpc.Session,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public LogoutUserResult executeInternal(LogoutUser action, ExecutionContext arg1) throws ActionException {
		Users user = getUser();
		// remove user attribute from session
		sessionProvider.get().removeAttribute("user");

		return new LogoutUserResult(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<LogoutUser> getActionType() {
		return LogoutUser.class;
	}

	@Override
	public void undo(LogoutUser action, LogoutUserResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}
}
