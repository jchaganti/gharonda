package org.jchlabs.gharonda.client.view.account;

import com.gwtplatform.mvp.client.UiHandlers;

public interface MyListingsUiHandlers extends UiHandlers {
	void handleDeleteProperty(Integer id);

	void handleModifyProperty(Integer id);

	void handleMyAccountClicked();
}
