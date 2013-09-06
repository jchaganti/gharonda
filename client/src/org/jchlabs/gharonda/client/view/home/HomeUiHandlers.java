package org.jchlabs.gharonda.client.view.home;

import org.jchlabs.gharonda.client.util.SearchProfile;

import com.gwtplatform.mvp.client.UiHandlers;

public interface HomeUiHandlers extends UiHandlers {

	void handleBasicSearch(SearchProfile listSearchProfile, SearchProfile mapSearchProfile, int mode);

	void handleNotifier();

	void handleAdvSearch();
}
