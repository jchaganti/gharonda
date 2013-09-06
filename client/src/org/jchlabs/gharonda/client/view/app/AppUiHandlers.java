package org.jchlabs.gharonda.client.view.app;

import org.jchlabs.gharonda.client.util.PostLogInCB;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.mvp.client.UiHandlers;

public interface AppUiHandlers extends UiHandlers {
	void showLoginDialog();

	void showRegisterDialog();

	void handleLogin(String email, String passwd, PostLogInCB cb);

	void handleRegister(Users user, PostLogInCB cb);

	void handleLoginRegisterClose();

	void handleLogout();

	void handleBuyOrSell(SearchProfile listSearchProfile, SearchProfile mapSearchProfile);

	void handleMyAccount(String historyToken);

	void forwardPage(String historyToken);

	void handleRegisterNow(String myListingsPage);

}
