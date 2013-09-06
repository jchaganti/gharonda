package org.jchlabs.gharonda.client.presenter.account;

import java.util.List;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.MyAccountSelectedEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.account.MyFavoritesUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.GetUserFavorites;
import org.jchlabs.gharonda.shared.rpc.GetUserPropertiesResult;
import org.jchlabs.gharonda.shared.rpc.RemoveFavorite;
import org.jchlabs.gharonda.shared.rpc.RemoveFavoriteResult;
import org.jchlabs.gharonda.shared.rpc.Status;

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

public class MyFavoritesPresenter extends
		AbstractAppPresenter<MyFavoritesPresenter.MyView, MyFavoritesPresenter.MyProxy> implements
		MyFavoritesUiHandlers {
	/*
	 * {@link MyFavoritesPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.myFavoritesPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<MyFavoritesPresenter> {

	}

	/**
	 * {@link MyListingsPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<MyFavoritesUiHandlers> {

		void loadResults(List<PropertiesDTO> userProperties, int size, SearchProfile profile);

	}

	private DispatchAsync dispatcher;

	@Inject
	public MyFavoritesPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleMyAccountClicked() {
		MyAccountSelectedEvent.fire(MyFavoritesPresenter.this);
	}

	@Override
	public void handleRemoveFromFavoritesClicked(List<Integer> pids) {

		RemoveFavorite action = new RemoveFavorite(pids);
		dispatcher.execute(action, new AsyncCallback<RemoveFavoriteResult>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(RemoveFavoriteResult result) {
				if (result.getStatus().equals(Status.SUCCESS)) {
					showFavorites();
				} else {
					Window.alert("Remove From Favorites failed");
				}
			}
		});

	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.myFavorites;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.myFavorites;
	}

	@Override
	protected int getFeaturedListingsSize() {
		return 0;
	}

	@Override
	protected void onReset() {
		super.onReset();
		showFavorites();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void showFavorites() {

		GetUserFavorites action = new GetUserFavorites();
		dispatcher.execute(action, new AsyncCallback<GetUserPropertiesResult>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(GetUserPropertiesResult result) {
				getView().loadResults(result.getUserProperties(), result.getUserProperties().size(), null);
			}
		});
	}
}
