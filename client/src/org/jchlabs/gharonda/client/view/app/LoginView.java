package org.jchlabs.gharonda.client.view.app;

import org.jchlabs.gharonda.client.presenter.app.LoginPresenterWidget.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;

public class LoginView extends BaseLoginRegisterView implements MyView {

	interface LoginViewUiBinder extends UiBinder<PopupPanel, LoginView> {
	}

	@UiField
	Anchor signMeIn;
	@UiField
	TextBox email;
	@UiField
	PasswordTextBox passwd;
	@UiField
	Anchor close;
	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

	private final PopupPanel widget;

	@Inject
	protected LoginView(EventBus eventBus) {
		super(eventBus);
		widget = uiBinder.createAndBindUi(this);

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiHandler("email")
	void onEmail(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			logIn();
		}
	}

	@UiHandler("passwd")
	void onPasswd(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			logIn();
		}
	}

	@UiHandler("policy")
	void onPolicyClicked(ClickEvent event) {
		Window.open(PropertyOptions.getPrivacyPageUrl(), "", "");
	}

	@UiHandler("signMeIn")
	void onSignMeInClicked(ClickEvent event) {
		logIn();
	}

	private void logIn() {
		if (email.getValue().isEmpty()) {
			Window.alert(PropertyOptions.enterEmailAddress);
			return;
		}
		if (passwd.getValue().isEmpty()) {
			Window.alert(PropertyOptions.enterPasswd);
			return;
		}
		uiHandlers.handleLogin(email.getValue(), passwd.getValue(), getPostLogInCB());
	}

}
