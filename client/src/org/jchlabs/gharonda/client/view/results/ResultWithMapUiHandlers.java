package org.jchlabs.gharonda.client.view.results;

import org.jchlabs.gharonda.client.util.SearchProfile;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ResultWithMapUiHandlers extends UiHandlers {

	void handleMapBoundsChanged(SearchProfile mapProfile);

	void handleViewList();
}
