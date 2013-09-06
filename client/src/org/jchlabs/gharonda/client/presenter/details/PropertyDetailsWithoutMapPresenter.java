package org.jchlabs.gharonda.client.presenter.details;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.details.PropertyDetailsWithoutMapUiHandlers;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class PropertyDetailsWithoutMapPresenter
		extends
		BasePropertyDetailsPresenter<PropertyDetailsWithoutMapPresenter.MyView, PropertyDetailsWithoutMapPresenter.MyProxy>
		implements PropertyDetailsWithoutMapUiHandlers {
	/**
	 * {@link PropertyDetailsWithoutMapPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.propertyDetailsWOMapPage)
	public interface MyProxy extends BasePropertyDetailsPresenter.MyProxy<PropertyDetailsWithoutMapPresenter> {

	}

	/**
	 * {@link PropertyDetailsWithoutMapPresenter}'s view.
	 */
	public interface MyView extends BasePropertyDetailsPresenter.MyView,
			HasUiHandlers<PropertyDetailsWithoutMapUiHandlers> {

	}

	@Inject
	public PropertyDetailsWithoutMapPresenter(final EventBus eventBus, final PlaceManager placeManager,
			final MyView view, final MyProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, placeManager, view, proxy, dispatcher);
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		String pidStr = request.getParameter("pid", "-1");
		return PropertyOptions.details + " ID #" + pidStr;
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.details;
	}
}
