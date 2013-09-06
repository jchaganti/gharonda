package org.jchlabs.gharonda.client.view.home;

import org.jchlabs.gharonda.client.util.SearchProfile;

import com.gwtplatform.mvp.client.UiHandlers;

public interface AdvancedSearchUiHandlers extends UiHandlers {
	void handleAdvSearch(SearchProfile profile, int mode);
}
