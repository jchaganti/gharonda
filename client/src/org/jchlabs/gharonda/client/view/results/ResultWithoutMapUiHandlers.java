package org.jchlabs.gharonda.client.view.results;

import org.jchlabs.gharonda.client.util.SearchProfile;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ResultWithoutMapUiHandlers extends UiHandlers {

	void handleSearch(SearchProfile listSearchProfile);

	void handleViewMap();
}
