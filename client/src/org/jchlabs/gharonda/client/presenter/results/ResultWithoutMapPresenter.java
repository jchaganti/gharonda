package org.jchlabs.gharonda.client.presenter.results;

import java.util.List;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.ResultsWithMapSelectedEvent;
import org.jchlabs.gharonda.client.events.ResultsWithoutMapSelectedEvent;
import org.jchlabs.gharonda.client.events.ResultsWithoutMapSelectedEventHandler;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchFeaturedListing;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.results.ResultWithoutMapUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingProfileIF;
import org.jchlabs.gharonda.shared.rpc.GetFeaturedProperties;
import org.jchlabs.gharonda.shared.rpc.GetSearchProperties;
import org.jchlabs.gharonda.shared.rpc.GetSearchPropertiesResult;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class ResultWithoutMapPresenter extends
		AbstractAppPresenter<ResultWithoutMapPresenter.MyView, ResultWithoutMapPresenter.MyProxy> implements
		ResultsWithoutMapSelectedEventHandler, ResultWithoutMapUiHandlers {
	/**
	 * {@link ResultWithoutMapPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.resultsWOMapPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<ResultWithoutMapPresenter> {

	}

	/**
	 * {@link ResultWithoutMapPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<ResultWithoutMapUiHandlers> {

		SearchProfile getDefaultSearchProfile();

		void loadResults(List<PropertiesDTO> searchProperties, int realCount, SearchProfile listSearchProfile);

		void initUI(int mode);

	}

	private static final String MODE = "mode";

	private final DispatchAsync dispatcher;
	private SearchProfile listSearchProfile;
	private SearchProfile mapSearchProfile;
	private int mode;
	private final PlaceManager placeManager;

	@Inject
	public ResultWithoutMapPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleSearch(SearchProfile listSearchProfile) {
		this.listSearchProfile = listSearchProfile;
		displayResults();
	}

	@Override
	public void handleViewMap() {
		ResultsWithMapSelectedEvent.fire(this, listSearchProfile, mapSearchProfile);
		placeManager.revealRelativePlace(new PlaceRequest(NameTokens.resultsWMapPage).with("mode",
				new Integer(mode).toString()));

	}

	@ProxyEvent
	@Override
	public void onResultsWithoutMapSelected(ResultsWithoutMapSelectedEvent event) {
		// this.featuredProperties = event.getFeaturedProperties();
		this.listSearchProfile = event.getListSearchProfile();
		this.mapSearchProfile = event.getMapSearchProfile();
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String modeStr = request.getParameter(MODE, "-1");
		mode = Integer.parseInt(modeStr);
	}

	@Override
	public String getBreadCrumbHeading() {
		String heading = "";
		if (mode == 1) {
			heading = PropertyOptions.searchResultsSale;
		} else if (mode == 2) {
			heading = PropertyOptions.searchResultsRent;
		}
		return heading;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		String heading = "";
		String modeStr = request.getParameter(MODE, "-1");
		int _mode = Integer.parseInt(modeStr);
		if (_mode == 1) {
			heading = PropertyOptions.searchResultsSale;
		} else if (_mode == 2) {
			heading = PropertyOptions.searchResultsRent;
		}
		return heading;
	}

	@Override
	protected void onReset() {
		super.onReset();
		displayResults();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void displayResults() {
		if (listSearchProfile == null) {
			listSearchProfile = getView().getDefaultSearchProfile();
			listSearchProfile.setResultsSizeLimit(20);
		}
		List<SearchCriteriaIFace> slCriteria = listSearchProfile.getSelListCriteria();
		PropertyOptions.adjustForPModeSearchCriteria(slCriteria, mode);
		GetSearchProperties action = new GetSearchProperties(listSearchProfile.getFetchProfile(), slCriteria,
				listSearchProfile.getSearchTextCriteria(), true, listSearchProfile.getResultsSizeLimit());
		dispatcher.execute(action, new AsyncCallback<GetSearchPropertiesResult>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(GetSearchPropertiesResult result) {
				getView().initUI(mode);
				getView().loadResults(result.getSearchProperties(), result.getRealCount(), listSearchProfile);

				showFeaturedProperties(getFeaturedPropertiesAction(listSearchProfile));
			}
		});
	}

	private GetFeaturedProperties getFeaturedPropertiesAction(SearchProfile listProfile) {

		FeaturedListingProfileIF flp = new SearchFeaturedListing(listProfile.getSelListCriteria(),
				listProfile.getSearchTextCriteria(), PropertyOptions.DEFAULT_FEATURED_SEARCH_PROFILE);
		GetFeaturedProperties action = new GetFeaturedProperties(flp.getANDSearchCriteria(), flp.getORSearchCriteria(),
				flp.getFeaturedListingType(), flp.getFetchProfile());
		;
		return action;

	}

	@Override
	protected int getFeaturedListingsSize() {
		return 10;
	}
}
