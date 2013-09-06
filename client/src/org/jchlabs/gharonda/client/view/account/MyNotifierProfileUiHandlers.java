package org.jchlabs.gharonda.client.view.account;

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.mvp.client.UiHandlers;

public interface MyNotifierProfileUiHandlers extends UiHandlers {
	void handleSaveUserNotifierProfile(Users user);

	void handleMyAccountClicked();
}
