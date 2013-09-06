package org.jchlabs.gharonda.client.view.account;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

public interface MyFavoritesUiHandlers extends UiHandlers {
	void handleMyAccountClicked();

	void handleRemoveFromFavoritesClicked(List<Integer> pids);
}
