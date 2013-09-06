package org.jchlabs.gharonda.shared.rpc;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Action;

public class SaveProperty implements Action<SavePropertyResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private PropertiesDTO property;

	public SaveProperty() {
	}

	public SaveProperty(PropertiesDTO property) {
		super();
		this.property = property;
	}

	public PropertiesDTO getProperties() {
		return property;
	}

	public void setProperties(PropertiesDTO property) {
		this.property = property;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "SaveProperty";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
