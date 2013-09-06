package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.dispatch.shared.Result;

public class GetPropertyResult implements Result {

	private static final long serialVersionUID = -5785233314922498922L;

	private PropertiesDTO property;
	private Users user;

	public GetPropertyResult() {
	}

	public GetPropertyResult(PropertiesDTO property) {
		this(property, null);
	}

	public GetPropertyResult(PropertiesDTO property, Users user) {
		super();
		this.property = property;
		this.setUser(user);
	}

	public PropertiesDTO getProperty() {
		return property;
	}

	public void setProperty(PropertiesDTO property) {
		this.property = property;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Users getUser() {
		return user;
	}
}
