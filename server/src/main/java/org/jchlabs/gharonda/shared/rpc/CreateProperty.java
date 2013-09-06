package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class CreateProperty implements Action<CreatePropertyResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "CreateProperty";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
