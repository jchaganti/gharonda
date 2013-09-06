package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.dispatch.shared.Result;

public class ModifyUserResult implements Result {

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

	public ModifyUserResult(Users user, RegistrationStatus status) {
		this.user = user;
		this.status = status;
	}

	@SuppressWarnings("unused")
	private ModifyUserResult() {
	}

	public Users getUser() {
		return user;
	}
}
