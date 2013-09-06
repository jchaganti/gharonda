package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.dispatch.shared.Result;

public class LoginUserResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -8740775403377441876L;
	private Users user;

	public LoginUserResult(Users user) {
		this.user = user;
	}

	@SuppressWarnings("unused")
	private LoginUserResult() {
	}

	public Users getUser() {
		return user;
	}
}
