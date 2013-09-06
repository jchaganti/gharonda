package org.jchlabs.gharonda.client.presenter.home;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.ResultsWithoutMapSelectedEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.home.AdvancedSearchUiHandlers;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class AdvancedSearchPresenter extends
		AbstractAppPresenter<AdvancedSearchPresenter.MyView, AdvancedSearchPresenter.MyProxy> implements
		AdvancedSearchUiHandlers {
	/**
	 * {@link AdvancedSearchPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.advSearchPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<AdvancedSearchPresenter> {

	}

	/**
	 * {@link AdvancedSearchPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<AdvancedSearchUiHandlers> {

	}

	private final PlaceManager placeManager;

	@Inject
	public AdvancedSearchPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleAdvSearch(SearchProfile listSearchProfile, int mode) {
		ResultsWithoutMapSelectedEvent.fire(this, listSearchProfile, null);
		placeManager.revealRelativePlace(
				new PlaceRequest(NameTokens.resultsWOMapPage).with("mode", new Integer(mode).toString()), 0);
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.advSearch;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.advSearch;
	}

	@Override
	protected void onReset() {
		super.onReset();
		showFeaturedProperties();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	@Override
	protected int getFeaturedListingsSize() {
		return 4;
	}
}
