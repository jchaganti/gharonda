package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class GetJustListedAndBargainProperties implements Action<GetJustListedAndBargainPropertiesResult> {

	private static final long serialVersionUID = 2255166545722718095L;

	public GetJustListedAndBargainProperties() {
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetJustListedAndBargainProperties";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
