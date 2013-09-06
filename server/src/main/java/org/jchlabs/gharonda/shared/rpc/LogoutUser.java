package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class LogoutUser implements Action<LogoutUserResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	public LogoutUser() {
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "LogoutUser";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
