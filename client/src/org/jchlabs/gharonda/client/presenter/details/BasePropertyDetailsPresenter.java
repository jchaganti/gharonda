package org.jchlabs.gharonda.client.presenter.details;

import org.jchlabs.gharonda.client.events.ShowLoginEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PostLogInCB;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.details.PropertyDetailsWithoutMapUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.AddFavorite;
import org.jchlabs.gharonda.shared.rpc.AddFavoriteResult;
import org.jchlabs.gharonda.shared.rpc.GetProperty;
import org.jchlabs.gharonda.shared.rpc.GetPropertyResult;
import org.jchlabs.gharonda.shared.rpc.Status;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class BasePropertyDetailsPresenter<V extends BasePropertyDetailsPresenter.MyView, P extends BasePropertyDetailsPresenter.MyProxy<?>>
		extends AbstractAppPresenter<V, P> implements PropertyDetailsWithoutMapUiHandlers {

	public interface MyProxy<Q extends AbstractAppPresenter<?, ?>> extends AbstractAppPresenter.MyProxy<Q> {

	}

	/**
	 * {@link BasePropertyDetailsPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<PropertyDetailsWithoutMapUiHandlers> {
		void showProperty(PropertiesDTO property, Users user);
	}

	private class AddToFavoriteInvocation implements PostLogInCB {
		@Override
		public void invoke() {
			AddFavorite action = new AddFavorite(pid);
			dispatcher.execute(action, new AsyncCallback<AddFavoriteResult>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(AddFavoriteResult result) {
					if (result.getStatus().equals(Status.SUCCESS)) {
						Window.alert(PropertyOptions.pAddedToFavs);
					} else if (result.getStatus().equals(Status.ALREADY_EXISTS)) {
						Window.alert(PropertyOptions.pAlreadyAddedToFavs);
					} else {
						Window.alert(PropertyOptions.pNotAddedToFavs);
					}
				}
			});
		}
	}

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	private int pid;

	@Inject
	public BasePropertyDetailsPresenter(final EventBus eventBus, final PlaceManager placeManager, final V view,
			final P proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleAddToFavorite() {
		ShowLoginEvent.fire(this, new AddToFavoriteInvocation());

	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String pidStr = request.getParameter("pid", "-1");
		pid = Integer.parseInt(pidStr);
	}

	@Override
	protected void onReset() {
		super.onReset();
		showProperty();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void showProperty() {

		if (pid != -1) {
			GetProperty action = new GetProperty(pid, true);
			dispatcher.execute(action, new AsyncCallback<GetPropertyResult>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(PropertyOptions.userPropsNotFound);
				}

				@Override
				public void onSuccess(GetPropertyResult result) {
					if (result != null) {
						getView().showProperty(result.getProperty(), result.getUser());
						showFeaturedProperties();
					} else {
						Window.alert(PropertyOptions.pidNotFound(pid));

					}
				}
			});
		}
	}

	@Override
	protected int getFeaturedListingsSize() {
		return 3;
	}

	@Override
	public void handleTabClicked(String token) {
		placeManager.revealRelativePlace(new PlaceRequest(token).with("pid", new Integer(pid).toString()), -1);

	}

}
