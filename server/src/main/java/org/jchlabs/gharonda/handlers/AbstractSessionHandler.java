package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.exceptions.InvalidSessionException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.dispatch.shared.Result;

/**
 * Abstract class which take care of checking if the session is still valid before executing the handler
 * 
 */
public abstract class AbstractSessionHandler<A extends Action<R>, R extends Result> implements ActionHandler<A, R> {

	protected final Provider<HttpSession> sessionProvider;
	protected final Log logger;

	@Inject
	public AbstractSessionHandler(Log logger, Provider<HttpSession> sessionProvider) {
		this.sessionProvider = sessionProvider;

		this.logger = logger;
	}

	/**
	 * Execute executeInternal method
	 */
	public R execute(A action, ExecutionContext context) throws ActionException {
		return executeInternal(action, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#execute(net.customware.gwt.dispatch.shared.Action,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	protected abstract R executeInternal(A action, ExecutionContext context) throws ActionException;

	/**
	 * Return the User stored in session with the given id
	 * 
	 * @param sessionId
	 * @return user
	 * @throws ActionException
	 */
	protected Users getUser() throws ActionException {
		Users user = (Users) sessionProvider.get().getAttribute("user");
		if (user == null) {
			throw new InvalidSessionException("User not found in session with id " + sessionProvider.get().getId());
		} else {
			return user;
		}
	}
}
