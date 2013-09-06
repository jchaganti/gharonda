package org.jchlabs.gharonda.client.view.account;

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.mvp.client.UiHandlers;

public interface MyProfileUiHandlers extends UiHandlers {
	void handleUpdateUser(Users user);

	void handleUpdatePassWord(String oldPasswd, String newPasswd);

	void handleUpdateEmail(String newEmail);

	void handleMyAccountClicked();
}
