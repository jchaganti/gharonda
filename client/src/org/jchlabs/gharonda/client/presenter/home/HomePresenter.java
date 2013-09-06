package org.jchlabs.gharonda.client.presenter.home;

import java.util.List;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.MyAccountSelectedEvent;
import org.jchlabs.gharonda.client.events.ResultsWithoutMapSelectedEvent;
import org.jchlabs.gharonda.client.events.ShowLoginEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.util.VicinityFeaturedListing;
import org.jchlabs.gharonda.client.view.home.HomeUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingProfileIF;
import org.jchlabs.gharonda.shared.rpc.GetFeaturedProperties;
import org.jchlabs.gharonda.shared.rpc.GetJustListedAndBargainProperties;
import org.jchlabs.gharonda.shared.rpc.GetJustListedAndBargainPropertiesResult;

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.ClientLocation;
import com.google.gwt.gen2.logging.shared.Log;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

public class HomePresenter extends AbstractAppPresenter<HomePresenter.MyView, HomePresenter.MyProxy> implements
		HomeUiHandlers {
	/**
	 * {@link HomePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.homePage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<HomePresenter> {

	}

	/**
	 * {@link HomePresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<HomeUiHandlers> {
		void loadBargains(List<PropertiesDTO> props);

		void loadJustListed(List<PropertiesDTO> props);
	}

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	@Inject
	public HomePresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleAdvSearch() {
		MyAccountSelectedEvent.fire(this);
		placeManager.revealRelativePlace(new PlaceRequest(NameTokens.advSearchPage));
	}

	@Override
	public void handleBasicSearch(SearchProfile listSearchProfile, SearchProfile mapSearchProfile, int mode) {
		ResultsWithoutMapSelectedEvent.fire(this, listSearchProfile, mapSearchProfile);
		placeManager.revealRelativePlace(new PlaceRequest(NameTokens.resultsWOMapPage).with("mode",
				new Integer(mode).toString()));

	}

	@Override
	public void handleNotifier() {
		ShowLoginEvent.fire(this, NameTokens.myNotifierProfilePage);

	}

	public boolean hasBreadCrumb() {
		return false;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return "Gharonda.com";
	}

	@Override
	protected void onReset() {
		super.onReset();
		showJustListedAndBargains();
		showFeaturedProperties(getFeaturedPropertiesAction());

	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	@Override
	protected int getFeaturedListingsSize() {
		return 6;
	}

	private GetFeaturedProperties getFeaturedPropertiesAction() {
		AjaxLoader.init("ABQIAAAAky7fCd66rIzHNmVHMi7OVhTpH3CbXHjuCVmaTc5MkkU4wO1RRhSrxz3r9KS9dVua1Ymss9-flaUeUA");
		ClientLocation loc = AjaxLoader.getClientLocation();
		Double lat = null;
		Double lng = null;
		if (loc != null) {
			lat = loc.getLatitude();
			lng = loc.getLongitude();

			Log.fine("The user's lat = " + lat.toString() + "  lng = " + lng.toString());
		} else {
			Log.fine("The user's geocodes not found");
		}

		FeaturedListingProfileIF flp = new VicinityFeaturedListing(lat, lng, CommonConstants.BOUNDING_BOX_DISTANCE);
		GetFeaturedProperties action = new GetFeaturedProperties(flp.getANDSearchCriteria(), flp.getORSearchCriteria(),
				flp.getFeaturedListingType(), flp.getFetchProfile());
		return action;
	}

	private void showJustListedAndBargains() {
		GetJustListedAndBargainProperties action = new GetJustListedAndBargainProperties();
		dispatcher.execute(action, new AsyncCallback<GetJustListedAndBargainPropertiesResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Getting JustListed And BargainProperties failed");
			}

			@Override
			public void onSuccess(GetJustListedAndBargainPropertiesResult result) {
				getView().loadJustListed(result.getJustListed());
				getView().loadBargains(result.getBargains());
			}
		});

	}

}
