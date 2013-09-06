package org.jchlabs.gharonda.client.view.app;

import org.jchlabs.gharonda.client.presenter.app.ContactUsPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.AbstractAppView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ContactUsView extends AbstractAppView<ContactUsUiHandlers> implements MyView {

	interface ContactUsViewUiBinder extends UiBinder<Widget, ContactUsView> {

	}

	private static final ContactUsViewUiBinder uiBinder = GWT.create(ContactUsViewUiBinder.class);

	@UiField
	HTMLPanel contentPanel;
	@UiField
	HTMLPanel featuredListing;

	private final Widget widget;;

	@Inject
	public ContactUsView() {
		widget = uiBinder.createAndBindUi(this);
		contentPanel.getElement().setId("content");
		setFeaturedistingPanel(featuredListing);
	}

	@Override
	public Widget asWidget() {
		setWindowTitle("Contacts- Gharonda.com");
		return widget;
	}

	@UiHandler("send")
	public void onSendClicked(ClickEvent event) {
		Element el = DOM.getElementById("msg");
		el.setInnerHTML("<strong>" + PropertyOptions.thankYou + "</strong>");
		el.getStyle().setProperty("display", "");
	}
}
