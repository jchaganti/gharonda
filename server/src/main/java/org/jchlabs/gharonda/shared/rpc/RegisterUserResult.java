package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.dispatch.shared.Result;

public class RegisterUserResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -8740775403377441875L;
	private Users user;
	private RegistrationStatus status;

	public RegistrationStatus getStatus() {
		return status;
	}

	public void setStatus(RegistrationStatus status) {
		this.status = status;
	}

	public RegisterUserResult(Users user, RegistrationStatus status) {
		this.user = user;
		this.status = status;
	}

	@SuppressWarnings("unused")
	private RegisterUserResult() {
	}

	public Users getUser() {
		return user;
	}
}
