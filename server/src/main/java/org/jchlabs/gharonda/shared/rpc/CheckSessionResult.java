package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.dispatch.shared.Result;

public class CheckSessionResult implements Result {

	private static final long serialVersionUID = -4785233314922498952L;
	boolean valid = false;

	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public boolean isValid() {
		return user != null;
	}
}
