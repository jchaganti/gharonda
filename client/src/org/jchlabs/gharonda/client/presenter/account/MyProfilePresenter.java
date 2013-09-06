package org.jchlabs.gharonda.client.presenter.account;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.MyAccountSelectedEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.account.MyProfileUiHandlers;
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

public class MyProfilePresenter extends AbstractAppPresenter<MyProfilePresenter.MyView, MyProfilePresenter.MyProxy>
		implements MyProfileUiHandlers {
	/*
	 * {@link MyProfilePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.myProfilePage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<MyProfilePresenter> {

	}

	/**
	 * {@link MyListingsPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<MyProfileUiHandlers> {
		void showUser(Users user);
	}

	private DispatchAsync dispatcher;
	private Users user = null;

	@Inject
	public MyProfilePresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleMyAccountClicked() {
		MyAccountSelectedEvent.fire(MyProfilePresenter.this);

	}

	@Override
	public void handleUpdateEmail(String newEmail) {
		ModifyUser action = new ModifyUser(user, ModifyUserType.MOD_EMAIL, null, null, newEmail);
		dispatcher.execute(action, new AsyncCallback<ModifyUserResult>() {
			@Override
			public void onFailure(Throwable caught) {
				com.google.gwt.user.client.Window.alert(PropertyOptions.emailModificationFailed);
			}

			@Override
			public void onSuccess(ModifyUserResult result) {
				if (result.getStatus().equals(RegistrationStatus.SUCCESS)) {

					Window.alert(PropertyOptions.emailModificationSuccessful);

				} else if (result.getStatus().equals(RegistrationStatus.ALREADY_EXISTS)) {
					Window.alert(PropertyOptions.userWithNewMailAlreadyRegistered);
				}
				user = result.getUser();
			}
		});
	}

	@Override
	public void handleUpdatePassWord(String oldPassWd, String newPassWd) {
		ModifyUser action = new ModifyUser(user, ModifyUserType.MOD_PASSWD, oldPassWd, newPassWd, null);
		dispatcher.execute(action, new AsyncCallback<ModifyUserResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.passwdModificationFailed);
			}

			@Override
			public void onSuccess(ModifyUserResult result) {
				if (result.getStatus().equals(RegistrationStatus.SUCCESS)) {
					Window.alert(PropertyOptions.passwordModificationSuccessful);
				} else if (result.getStatus().equals(RegistrationStatus.PASSWDS_NOT_MATCH)) {
					Window.alert(PropertyOptions.oldPasswordIncorrect);
				}
				user = result.getUser();
			}
		});
	}

	@Override
	public void handleUpdateUser(Users modifiedUser) {
		ModifyUser action = new ModifyUser(modifiedUser, ModifyUserType.MOD_DETAILS, null, null, null);
		dispatcher.execute(action, new AsyncCallback<ModifyUserResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.userProfileModificationFailed);
			}

			@Override
			public void onSuccess(ModifyUserResult result) {
				if (result.getStatus().equals(RegistrationStatus.SUCCESS)) {
					user = result.getUser();
					Window.alert(PropertyOptions.userProfileModificationSuccessful);

				} else {
					Window.alert(PropertyOptions.userProfileModificationFailed);
				}

			}
		});
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.myProfile;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.myProfile;
	}

	@Override
	protected void onReset() {
		super.onReset();
		showUser();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void showUser() {
		CheckSession action = new CheckSession();
		dispatcher.execute(action, new AsyncCallback<CheckSessionResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.userNotFound);
			}

			@Override
			public void onSuccess(CheckSessionResult result) {
				user = result.getUser();
				getView().showUser(user);

			}
		});

	}

	@Override
	protected int getFeaturedListingsSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
