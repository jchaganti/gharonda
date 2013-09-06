package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Result;

public class ModifyPropertyResult implements Result {

	private static final long serialVersionUID = -5785233314922498922L;

	private PropertiesDTO property;

	public ModifyPropertyResult() {
	}

	public ModifyPropertyResult(PropertiesDTO property) {
		super();
		this.property = property;
	}

	public PropertiesDTO getProperty() {
		return property;
	}

	public void setProperty(PropertiesDTO property) {
		this.property = property;
	}

}
