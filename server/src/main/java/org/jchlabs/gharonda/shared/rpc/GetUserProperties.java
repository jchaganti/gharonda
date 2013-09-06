package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class GetUserProperties implements Action<GetUserPropertiesResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	public GetUserProperties() {
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetUserProperties";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
