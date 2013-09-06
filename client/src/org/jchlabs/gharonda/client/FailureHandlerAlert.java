package org.jchlabs.gharonda.client;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;

public class FailureHandlerAlert implements ProxyFailureHandler {
	@Inject
	public FailureHandlerAlert() {

	}

	@Override
	public void onFailedGetPresenter(Throwable caught) {
		Window.alert("Failed to load code");
	}

}
