package org.jchlabs.gharonda.client.presenter.app;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.app.ContactUsUiHandlers;

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

public class ContactUsPresenter extends AbstractAppPresenter<ContactUsPresenter.MyView, ContactUsPresenter.MyProxy>
		implements ContactUsUiHandlers {
	/**
	 * {@link ContactUsPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.contactUsPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<ContactUsPresenter> {

	}

	/**
	 * {@link ContactUsPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<ContactUsUiHandlers> {

	}

	@Inject
	public ContactUsPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		getView().setUiHandlers(this);
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.contactUs;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.contactUs;
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
		return 2;
	}

}
