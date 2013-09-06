package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Result;

public class SavePropertyResult implements Result {

	private static final long serialVersionUID = -5785233314922498922L;

	private PropertiesDTO property;

	public SavePropertyResult() {
	}

	public SavePropertyResult(PropertiesDTO property) {
		super();
		this.property = property;
	}

	public PropertiesDTO getProperties() {
		return property;
	}

	public void setProperties(PropertiesDTO property) {
		this.property = property;
	}

}
