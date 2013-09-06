package org.jchlabs.gharonda.client.view.details;

import com.gwtplatform.mvp.client.UiHandlers;

public interface PropertyDetailsWithoutMapUiHandlers extends UiHandlers {
	void handleAddToFavorite();

	void handleTabClicked(String token);
}
