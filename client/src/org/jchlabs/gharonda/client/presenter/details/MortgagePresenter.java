package org.jchlabs.gharonda.client.presenter.details;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.util.PropertyOptions;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class MortgagePresenter extends
		BasePropertyDetailsPresenter<MortgagePresenter.MyView, MortgagePresenter.MyProxy> {
	/**
	 * {@link MortgagePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.propertyDetailsMortgagePage)
	public interface MyProxy extends BasePropertyDetailsPresenter.MyProxy<MortgagePresenter> {

	}

	/**
	 * {@link MortgagePresenter}'s view.
	 */
	public interface MyView extends BasePropertyDetailsPresenter.MyView {
	}

	@Inject
	public MortgagePresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, placeManager, view, proxy, dispatcher);
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.mortgage;
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.mortgage;
	}
}
