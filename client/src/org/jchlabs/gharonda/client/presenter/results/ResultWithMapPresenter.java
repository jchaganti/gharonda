package org.jchlabs.gharonda.client.presenter.results;

import java.util.List;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.ResultsWithMapSelectedEvent;
import org.jchlabs.gharonda.client.events.ResultsWithMapSelectedEventHandler;
import org.jchlabs.gharonda.client.events.ResultsWithoutMapSelectedEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.results.ResultWithMapUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.GetSearchProperties;
import org.jchlabs.gharonda.shared.rpc.GetSearchPropertiesResult;

import com.google.gwt.user.client.Window;
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

public class ResultWithMapPresenter extends
		AbstractAppPresenter<ResultWithMapPresenter.MyView, ResultWithMapPresenter.MyProxy> implements
		ResultsWithMapSelectedEventHandler, ResultWithMapUiHandlers {
	/**
	 * {@link ResultWithMapPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.resultsWMapPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<ResultWithMapPresenter> {

	}

	/**
	 * {@link ResultWithMapPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<ResultWithMapUiHandlers> {

		SearchProfile getSearchProfile();

		void loadResults(List<PropertiesDTO> searchProperties, int realCount, SearchProfile mapSearchProfile);

		void initUI(int mode);
	}

	private static final String MODE = "mode";

	private final DispatchAsync dispatcher;
	private SearchProfile listSearchProfile;
	private SearchProfile mapSearchProfile;
	private int mode;
	private final PlaceManager placeManager;

	@Inject
	public ResultWithMapPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleMapBoundsChanged(SearchProfile mapSearchProfile) {
		this.mapSearchProfile = mapSearchProfile;
		displayResults();
	}

	@Override
	public void handleViewList() {
		ResultsWithoutMapSelectedEvent.fire(this, listSearchProfile, mapSearchProfile);
		placeManager.revealRelativePlace(
				new PlaceRequest(NameTokens.resultsWOMapPage).with("mode", new Integer(mode).toString()), 1);

	}

	@ProxyEvent
	@Override
	public void onResultsWithMapSelected(ResultsWithMapSelectedEvent event) {
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
			heading = PropertyOptions.searchResultsMapSale;
		} else if (mode == 2) {
			heading = PropertyOptions.searchResultsMapRent;
		}
		return heading;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		String heading = "";
		String modeStr = request.getParameter(MODE, "-1");
		int _mode = Integer.parseInt(modeStr);
		if (_mode == 1) {
			heading = PropertyOptions.searchResultsMapSale;
		} else if (_mode == 2) {
			heading = PropertyOptions.searchResultsMapRent;
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
		if (mapSearchProfile == null) {
			mapSearchProfile = getView().getSearchProfile();
			mapSearchProfile.setResultsSizeLimit(20);
		}

		List<SearchCriteriaIFace> slCriteria = mapSearchProfile.getSelListCriteria();
		if (slCriteria.size() > 0) {
			PropertyOptions.adjustForPModeSearchCriteria(slCriteria, mode);
			GetSearchProperties action = new GetSearchProperties(mapSearchProfile.getFetchProfile(),
					mapSearchProfile.getSelListCriteria(), null, true, mapSearchProfile.getResultsSizeLimit());
			dispatcher.execute(action, new AsyncCallback<GetSearchPropertiesResult>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(GetSearchPropertiesResult result) {
					getView().initUI(mode);
					getView().loadResults(result.getSearchProperties(), result.getRealCount(), mapSearchProfile);

				}
			});

		} else {
			Window.alert(PropertyOptions.improperSearchCriteria);
		}
	}

	@Override
	protected int getFeaturedListingsSize() {
		return 0;
	}
}
