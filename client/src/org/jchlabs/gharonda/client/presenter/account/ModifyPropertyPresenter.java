package org.jchlabs.gharonda.client.presenter.account;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.events.MyAccountSelectedEvent;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.account.ModifyPropertyUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.CancelProperty;
import org.jchlabs.gharonda.shared.rpc.CancelPropertyResult;
import org.jchlabs.gharonda.shared.rpc.GetProperty;
import org.jchlabs.gharonda.shared.rpc.GetPropertyResult;
import org.jchlabs.gharonda.shared.rpc.SaveProperty;
import org.jchlabs.gharonda.shared.rpc.SavePropertyResult;

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

public class ModifyPropertyPresenter extends
		AbstractAppPresenter<ModifyPropertyPresenter.MyView, ModifyPropertyPresenter.MyProxy> implements
		ModifyPropertyUiHandlers {
	/**
	 * {@link ModifyPropertyPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.addPropertyPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<ModifyPropertyPresenter> {

	}

	/**
	 * {@link ModifyPropertyPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<ModifyPropertyUiHandlers> {
		void showProperty(PropertiesDTO pDTO);
	}

	private final DispatchAsync dispatcher;

	private final PlaceManager placeManager;
	private Integer pid;

	@Inject
	public ModifyPropertyPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	public void handleCancelProperty() {
		dispatcher.execute(new CancelProperty(pid), new AsyncCallback<CancelPropertyResult>() {
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.cancelSaveFailed);
			}

			public void onSuccess(CancelPropertyResult result) {
				if (result != null) {
					showMyAccount();

				} else {
					Window.alert(PropertyOptions.cancelSaveFailed);
				}

			}
		});

	}

	@Override
	public void handleSaveProperty(PropertiesDTO property) {
		dispatcher.execute(new SaveProperty(property), new AsyncCallback<SavePropertyResult>() {
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.propertySaveFailed);
			}

			public void onSuccess(SavePropertyResult result) {
				if (result != null) {
					showMyAccount();

				} else {
					Window.alert(PropertyOptions.propertySaveFailed);
				}

			}
		});

	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String pidStr = request.getParameter("pid", "-1");
		pid = Integer.parseInt(pidStr);
	}

	public String getBreadCrumbHeading() {
		return PropertyOptions.submitYourProperty;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.submitYourProperty;
	}

	@Override
	protected int getFeaturedListingsSize() {
		return 0;
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

	private void showMyAccount() {
		MyAccountSelectedEvent.fire(ModifyPropertyPresenter.this);
		placeManager.revealRelativePlace(new PlaceRequest(NameTokens.myListingsPage), 1);
	}

	private void showProperty() {
		if (pid != -1) {
			GetProperty action = new GetProperty(pid, false);
			dispatcher.execute(action, new AsyncCallback<GetPropertyResult>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(PropertyOptions.userPropsNotFound);
				}

				@Override
				public void onSuccess(GetPropertyResult result) {
					if (result != null) {
						getView().showProperty(result.getProperty());
					} else {
						Window.alert(PropertyOptions.pidNotFound(pid));
					}
				}
			});
		}
	}

}
