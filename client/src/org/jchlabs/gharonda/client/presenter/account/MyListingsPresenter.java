package org.jchlabs.gharonda.client.presenter.account;

import java.util.List;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.MyAccountSelectedEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.account.MyListingsUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.CreateProperty;
import org.jchlabs.gharonda.shared.rpc.CreatePropertyResult;
import org.jchlabs.gharonda.shared.rpc.DeleteProperty;
import org.jchlabs.gharonda.shared.rpc.DeletePropertyResult;
import org.jchlabs.gharonda.shared.rpc.GetUserProperties;
import org.jchlabs.gharonda.shared.rpc.GetUserPropertiesResult;
import org.jchlabs.gharonda.shared.rpc.ModifyProperty;
import org.jchlabs.gharonda.shared.rpc.ModifyPropertyResult;
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

public class MyListingsPresenter extends AbstractAppPresenter<MyListingsPresenter.MyView, MyListingsPresenter.MyProxy>
		implements MyListingsUiHandlers {
	/**
	 * {@link MyListingsPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.myListingsPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<MyListingsPresenter> {

	}

	/**
	 * {@link MyListingsPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<MyListingsUiHandlers> {
		void displayProperties(List<PropertiesDTO> list);
	}

	private final DispatchAsync dispatcher;

	private final PlaceManager placeManager;

	@Inject
	public MyListingsPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleDeleteProperty(Integer pid) {
		if (pid != null) {
			dispatcher.execute(new DeleteProperty(pid), new AsyncCallback<DeletePropertyResult>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Property could not be deleted");
				}

				@Override
				public void onSuccess(DeletePropertyResult result) {
					if (result.getStatus() == Status.SUCCESS) {
						showUserProperties();
					}
				}
			});
		}

	}

	@Override
	public void handleModifyProperty(Integer pid) {
		if (pid == null) {
			dispatcher.execute(new CreateProperty(), new AsyncCallback<CreatePropertyResult>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("New property could not be created");
				}

				@Override
				public void onSuccess(CreatePropertyResult result) {
					if (result.getProperty().getId() != null) {
						placeManager.revealRelativePlace(new PlaceRequest(NameTokens.addPropertyPage).with("pid",
								result.getProperty().getId().toString()));
					}
				}
			});
		} else {
			dispatcher.execute(new ModifyProperty(pid), new AsyncCallback<ModifyPropertyResult>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Property could not be checked out");

				}

				@Override
				public void onSuccess(ModifyPropertyResult result) {
					if (result.getProperty().getId() != null) {
						placeManager.revealRelativePlace(new PlaceRequest(NameTokens.addPropertyPage).with("pid",
								result.getProperty().getId().toString()));
					}
				}
			});
		}

	}

	@Override
	public void handleMyAccountClicked() {
		MyAccountSelectedEvent.fire(MyListingsPresenter.this);
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.myListings;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.myListings;
	}

	@Override
	protected void onReset() {
		super.onReset();
		showUserProperties();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void showUserProperties() {
		GetUserProperties action = new GetUserProperties();
		dispatcher.execute(action, new AsyncCallback<GetUserPropertiesResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.userPropsNotFound);
			}

			@Override
			public void onSuccess(GetUserPropertiesResult result) {
				getView().displayProperties(result.getUserProperties());

			}
		});

	}

	@Override
	protected int getFeaturedListingsSize() {
		return 0;
	}
}
