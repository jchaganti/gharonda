package org.jchlabs.gharonda.client.presenter.app;

import org.jchlabs.gharonda.client.util.PostLogInCB;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.UiHandlers;

public class RegisterPresenterWidget extends PresenterWidget<RegisterPresenterWidget.MyView> {
	public interface MyView extends PopupView, HasUiHandlers<UiHandlers> {
		void initUI();

		void setPostLogInCB(PostLogInCB cb);
	}

	@Inject
	public RegisterPresenterWidget(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getView().initUI();
	}
}
