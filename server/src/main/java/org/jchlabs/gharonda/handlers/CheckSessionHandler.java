package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.CheckSession;
import org.jchlabs.gharonda.shared.rpc.CheckSessionResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for asking the server if the session is valid
 */
public class CheckSessionHandler implements ActionHandler<CheckSession, CheckSessionResult> {

	protected final Provider<HttpSession> sessionProvider;
	protected final Log logger;

	@Inject
	public CheckSessionHandler(Log logger, Provider<HttpSession> provider) {
		this.sessionProvider = provider;
		this.logger = logger;
	}

	public CheckSessionResult execute(CheckSession arg0, ExecutionContext arg1) throws ActionException {
		CheckSessionResult ret = new CheckSessionResult();
		try {
			Users user = (Users) sessionProvider.get().getAttribute("user");
			if (user != null)
				ret.setUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public Class<CheckSession> getActionType() {
		return CheckSession.class;
	}

	@Override
	public void undo(CheckSession action, CheckSessionResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

}
