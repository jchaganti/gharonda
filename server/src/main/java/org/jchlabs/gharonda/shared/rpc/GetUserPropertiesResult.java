package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Result;

public class GetUserPropertiesResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -1788523843461596092L;
	private List<PropertiesDTO> userProperties;

	public GetUserPropertiesResult(List<PropertiesDTO> userProperties) {
		super();
		this.userProperties = userProperties;
	}

	public GetUserPropertiesResult() {

	}

	public List<PropertiesDTO> getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(List<PropertiesDTO> userProperties) {
		this.userProperties = userProperties;
	}

}
