package org.jchlabs.gharonda.client.presenter.account;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.MyAccountSelectedEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.account.MyNotifierProfileUiHandlers;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.CheckSession;
import org.jchlabs.gharonda.shared.rpc.CheckSessionResult;
import org.jchlabs.gharonda.shared.rpc.ModifyUser;
import org.jchlabs.gharonda.shared.rpc.ModifyUserResult;
import org.jchlabs.gharonda.shared.rpc.ModifyUserType;
import org.jchlabs.gharonda.shared.rpc.RegistrationStatus;

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

public class MyNotifierProfilePresenter extends
		AbstractAppPresenter<MyNotifierProfilePresenter.MyView, MyNotifierProfilePresenter.MyProxy> implements
		MyNotifierProfileUiHandlers {
	/**
	 * {@link MyNotifierProfilePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.myNotifierProfilePage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<MyNotifierProfilePresenter> {

	}

	/**
	 * {@link MyNotifierProfilePresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<MyNotifierProfileUiHandlers> {
		void showUserNotifierProfile(Users user);
	}

	private final DispatchAsync dispatcher;

	@Inject
	public MyNotifierProfilePresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleMyAccountClicked() {
		MyAccountSelectedEvent.fire(MyNotifierProfilePresenter.this);
	}

	@Override
	public void handleSaveUserNotifierProfile(Users user) {
		ModifyUser action = new ModifyUser(user, ModifyUserType.MOD_NOTIFIER_PROFILE, null, null, null);
		dispatcher.execute(action, new AsyncCallback<ModifyUserResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.userProfileModificationFailed);
			}

			@Override
			public void onSuccess(ModifyUserResult result) {
				if (result.getStatus().equals(RegistrationStatus.SUCCESS)) {
					Window.alert(PropertyOptions.userProfileModificationSuccessful);

				} else {
					Window.alert(PropertyOptions.userProfileModificationFailed);
				}
			}
		});
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.myNotifierProfile;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.myNotifierProfile;
	}

	@Override
	protected void onReset() {
		super.onReset();
		showUserNotifierProfile();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void showUserNotifierProfile() {
		CheckSession action = new CheckSession();
		dispatcher.execute(action, new AsyncCallback<CheckSessionResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.userNotFound);
			}

			@Override
			public void onSuccess(CheckSessionResult result) {
				getView().showUserNotifierProfile(result.getUser());
				showFeaturedProperties();
			}
		});

	}

	@Override
	protected int getFeaturedListingsSize() {
		return 3;
	}
}
