package org.jchlabs.gharonda.client.view.app;

import org.jchlabs.gharonda.client.util.PostLogInCB;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import com.gwtplatform.mvp.client.UiHandlers;

public abstract class BaseLoginRegisterView extends PopupViewImpl {

	protected AppUiHandlers uiHandlers;
	private PostLogInCB postLogInCB;

	protected BaseLoginRegisterView(EventBus eventBus) {
		super(eventBus);
	}

	public PostLogInCB getPostLogInCB() {
		return postLogInCB;
	}

	public void setPostLogInCB(PostLogInCB postLogInCB) {
		this.postLogInCB = postLogInCB;
	}

	public void setUiHandlers(UiHandlers uiHandlers) {
		this.uiHandlers = (AppUiHandlers) uiHandlers;

	}

	@UiHandler("close")
	void close(ClickEvent event) {
		uiHandlers.handleLoginRegisterClose();
	}

	@UiHandler("login")
	void loginClicked(ClickEvent event) {
		uiHandlers.showLoginDialog();
	}

	@UiHandler("register")
	void registerClicked(ClickEvent event) {
		uiHandlers.showRegisterDialog();
	}

}
