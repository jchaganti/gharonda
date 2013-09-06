package org.jchlabs.gharonda.client.presenter.app;

import org.jchlabs.gharonda.client.events.ResultsWithoutMapSelectedEvent;
import org.jchlabs.gharonda.client.events.ShowLoginEvent;
import org.jchlabs.gharonda.client.events.ShowLoginEventHandler;
import org.jchlabs.gharonda.client.presenter.HasBreadCrumbData;
import org.jchlabs.gharonda.client.util.PostLogInCB;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.app.AppUiHandlers;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.CheckSession;
import org.jchlabs.gharonda.shared.rpc.CheckSessionResult;
import org.jchlabs.gharonda.shared.rpc.LoginUser;
import org.jchlabs.gharonda.shared.rpc.LoginUserResult;
import org.jchlabs.gharonda.shared.rpc.LogoutUser;
import org.jchlabs.gharonda.shared.rpc.LogoutUserResult;
import org.jchlabs.gharonda.shared.rpc.RegisterUser;
import org.jchlabs.gharonda.shared.rpc.RegisterUserResult;
import org.jchlabs.gharonda.shared.rpc.RegistrationStatus;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.gwtplatform.mvp.client.proxy.SetPlaceTitleHandler;

public class AppPresenter extends Presenter<AppPresenter.MyView, AppPresenter.MyProxy> implements AppUiHandlers {

	@ProxyCodeSplit
	public interface MyProxy extends Proxy<AppPresenter> {
	}

	public interface MyView extends View, HasUiHandlers<AppUiHandlers> {
		void blur();

		void initBreadcrumbs(int breadcrumbSize, String heading);

		void setBreadcrumbs(int index, String title);

		void showFlag(String url);

		void showUserInfo(Users user);

		void unBlur();

		void hideBreadCrumbs();
	}

	private class MyAccountInvocation implements PostLogInCB {
		private String historyToken;

		public MyAccountInvocation(String historyToken) {
			this.historyToken = historyToken;
		}

		@Override
		public void invoke() {
			placeManager.revealRelativePlace(new PlaceRequest(historyToken), 1);
		}
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	private final DispatchAsync dispatcher;
	private final LoginPresenterWidget loginDlog;
	private final PlaceManager placeManager;
	private final RegisterPresenterWidget registerDlog;
	private HasBreadCrumbData breadCrumbData;

	private Users user;

	@Inject
	public AppPresenter(EventBus eventBus, MyView view, MyProxy proxy, final PlaceManager placeManager,
			final LoginPresenterWidget loginPresenter, final RegisterPresenterWidget registerPresenter,
			final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.loginDlog = loginPresenter;
		this.registerDlog = registerPresenter;
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
		loginPresenter.getView().setUiHandlers(this);
		registerPresenter.getView().setUiHandlers(this);
	}

	@Override
	public void forwardPage(String historyToken) {
		placeManager.revealRelativePlace(new PlaceRequest(historyToken));
	}

	@Override
	public void handleBuyOrSell(SearchProfile listSearchProfile, SearchProfile mapSearchProfile) {
		ResultsWithoutMapSelectedEvent.fire(this, listSearchProfile, mapSearchProfile);
	}

	@Override
	public void handleLogin(String email, String passwd, final PostLogInCB cb) {
		dispatcher.execute(new LoginUser(email, passwd), new AsyncCallback<LoginUserResult>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(LoginUserResult result) {
				loginDlog.getView().hide();
				getView().unBlur();
				getView().showUserInfo(result.getUser());
				user = result.getUser();
				if (cb != null) {
					cb.invoke();
					loginDlog.getView().setPostLogInCB(null);
				}
			}
		});

	}

	@Override
	public void handleLoginRegisterClose() {
		registerDlog.getView().hide();
		loginDlog.getView().hide();
		getView().unBlur();
	}

	@Override
	public void handleLogout() {
		dispatcher.execute(new LogoutUser(), new AsyncCallback<LogoutUserResult>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("User could not be logged out");
			}

			@Override
			public void onSuccess(LogoutUserResult result) {
				getView().showUserInfo(null);
				user = null;
			}
		});

	}

	@Override
	public void handleMyAccount(String historyToken) {
		MyAccountInvocation cb = new MyAccountInvocation(historyToken);
		handlePostLogInCBUsingLogInView(cb);
	}

	@Override
	public void handleRegister(Users _user, final PostLogInCB cb) {
		if (_user != null) {
			RegisterUser action = new RegisterUser(_user);
			dispatcher.execute(action, new AsyncCallback<RegisterUserResult>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(PropertyOptions.userCouldNotbeRegistered);
				}

				@Override
				public void onSuccess(RegisterUserResult result) {
					if (result.getStatus().equals(RegistrationStatus.SUCCESS)) {
						registerDlog.getView().hide();
						getView().unBlur();
						getView().showUserInfo(result.getUser());
						user = result.getUser();
						if (cb != null) {
							cb.invoke();
							registerDlog.getView().setPostLogInCB(null);
						}

					} else if (result.getStatus().equals(RegistrationStatus.ALREADY_EXISTS)) {
						Window.alert(PropertyOptions.userWithMailIdExists);
					} else {
						Window.alert(PropertyOptions.registrationFailed);
					}

				}
			});
		}
	}

	@Override
	public void handleRegisterNow(String historyToken) {
		MyAccountInvocation cb = new MyAccountInvocation(historyToken);
		handlePostLogInCBUsingRegisterView(cb);

	}

	@Override
	public void showLoginDialog() {
		registerDlog.getView().hide();
		getView().blur();
		addToPopupSlot(loginDlog);
	}

	@Override
	public void showRegisterDialog() {
		loginDlog.getView().hide();
		getView().blur();
		addToPopupSlot(registerDlog);
	}

	@Override
	public void setInSlot(Object slot, PresenterWidget<?> content) {
		if (content instanceof HasBreadCrumbData) {
			breadCrumbData = (HasBreadCrumbData) content;
		}
		super.setInSlot(slot, content);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(ShowLoginEvent.getType(), new ShowLoginEventHandler() {
			@Override
			public void onShowLoginEvent(ShowLoginEvent evt) {
				Object data = evt.getData();
				if (data instanceof String) {
					handleMyAccount((String) data);
				} else if (data instanceof PostLogInCB) {
					handlePostLogInCBUsingLogInView((PostLogInCB) data);
				}

			}
		});
	}

	@Override
	protected void onReset() {
		super.onReset();
		checkSession();
		showFlag();
		showBreadCrumbs();
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	private void checkSession() {
		dispatcher.execute(new CheckSession(), new AsyncCallback<CheckSessionResult>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(CheckSessionResult result) {
				user = result.getUser();
				getView().showUserInfo(result.getUser());
			}
		});
	}

	private void handlePostLogInCBUsingLogInView(PostLogInCB cb) {
		if (user != null) {
			cb.invoke();
		} else {
			loginDlog.getView().setPostLogInCB(cb);
			registerDlog.getView().setPostLogInCB(cb);
			showLoginDialog();
		}

	}

	private void handlePostLogInCBUsingRegisterView(PostLogInCB cb) {
		if (user != null) {
			cb.invoke();
		} else {
			registerDlog.getView().setPostLogInCB(cb);
			loginDlog.getView().setPostLogInCB(cb);
			showRegisterDialog();
		}

	}

	private void showBreadCrumbs() {
		if (breadCrumbData != null && breadCrumbData.hasBreadCrumb()) {
			int size = placeManager.getHierarchyDepth();
			getView().initBreadcrumbs(size, breadCrumbData.getBreadCrumbHeading());
			for (int i = 0; i < size; ++i) {
				final int index = i;
				placeManager.getTitle(i, new SetPlaceTitleHandler() {
					@Override
					public void onSetPlaceTitle(String title) {
						getView().setBreadcrumbs(index, title);
					}
				});
			}
		} else {
			getView().hideBreadCrumbs();
		}

	}

	private void showFlag() {
		String locale = Window.Location.getParameter("locale");
		if (locale != null) {
			locale = locale.replaceAll("#", "");
		}
		if (locale == null || "tr".equals(locale)) {
			getView().showFlag("images/usa.gif");

		} else {
			getView().showFlag("images/turkey.gif");
		}
	}

}
