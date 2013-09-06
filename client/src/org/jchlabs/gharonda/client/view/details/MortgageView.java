package org.jchlabs.gharonda.client.view.details;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.details.MortgagePresenter.MyView;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.Users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MortgageView extends AbstractAppView<PropertyDetailsWithoutMapUiHandlers> implements MyView {
	interface MortgageViewUiBinder extends UiBinder<Widget, MortgageView> {

	}

	private static final MortgageViewUiBinder uiBinder = GWT.create(MortgageViewUiBinder.class);
	@UiField
	HTMLPanel contentPanel;
	@UiField
	HTMLPanel featuredListing;

	private final Widget widget;

	@Inject
	public MortgageView() {
		widget = uiBinder.createAndBindUi(this);
		this.contentPanel.getElement().setId("content");
		setFeaturedistingPanel(featuredListing);

	};

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void showProperty(PropertiesDTO property, Users user) {
		setWindowTitle("Krediye Başvur - Gharonda.com");
		return;

	}

	@UiHandler("details")
	void onDetailsClicked(ClickEvent event) {
		getUiHandlers().handleTabClicked(NameTokens.propertyDetailsWOMapPage);
	}

	@UiHandler("showOnMap")
	void onShowOnMapInClicked(ClickEvent event) {
		getUiHandlers().handleTabClicked(NameTokens.propertyDetailsWMapPage);
	}

}
