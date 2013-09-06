package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.dispatch.shared.Result;

public class LogoutUserResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -1788523843461596092L;
	private Users user;

	public LogoutUserResult(Users user) {
		this.user = user;
	}

	@SuppressWarnings("unused")
	private LogoutUserResult() {
	}

	public Users getUser() {
		return user;
	}
}
