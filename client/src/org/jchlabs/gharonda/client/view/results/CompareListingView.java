package org.jchlabs.gharonda.client.view.results;

import java.util.Arrays;
import java.util.List;

import org.jchlabs.gharonda.client.presenter.results.CompareListingPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.widgets.ComparePropertiesTableModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gen2.table.client.PagingOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;

public class CompareListingView extends BaseComparePropertiesView implements MyView {

	interface CompareListingViewUiBinder extends UiBinder<Widget, CompareListingView> {

	}

	@UiField
	HTMLPanel contentPanel;
	private final Widget widget;

	private static final CompareListingViewUiBinder uiBinder = GWT.create(CompareListingViewUiBinder.class);;

	@UiField
	HTMLPanel featuredListing;
	EventBus eventBus = null;

	@Inject
	public CompareListingView(final ComparePropertiesTableModel mTableModel, EventBus eventBus) {
		super();
		setWindowTitle("İlan Karşılaştırma - Gharonda.com");
		widget = uiBinder.createAndBindUi(this);
		this.eventBus = eventBus;
		propertiesGridPanel.getElement().setId("propertiesGridPanel");
		tableModel = mTableModel;
		propertiesGrid = createScrollTable();
		propertiesGridPanel.add(propertiesGrid, "propertiesGridPanel");
		propertiesGrid.setHeight("900px");
		// propertiesGrid.setScrollPolicy(ScrollPolicy.BOTH);
		PagingOptions pagingOptions = new PagingOptions(propertiesGrid);
		paging.getElement().setId("paging");
		paging.add(pagingOptions, "paging");
		this.contentPanel.getElement().setId("content");
		setFeaturedistingPanel(featuredListing);
		registerJSFuncs();

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	private void deleteFromCompareListingList(String id) {
		String ids = Cookies.getCookie(PropertyOptions.COMPARE_LISTING_COOKIE);

		String[] idsArr = ids.split(", ");
		List<String> list = Arrays.asList(idsArr);
		// Window.alert("idsList = " + list);
		if (list.contains(id)) {
			String[] newArr = new String[list.size() - 1];
			int i = 0;
			for (String s : list) {
				if (id.equals(s)) {
					continue;
				}
				newArr[i++] = s;
			}
			// Window.alert(Arrays.toString(newArr).replaceAll("\\[",
			// "").replaceAll("\\]", ""));
			Cookies.setCookie(PropertyOptions.COMPARE_LISTING_COOKIE, Arrays.toString(newArr).replaceAll("\\[", "")
					.replaceAll("\\]", ""));
			Window.Location.reload();
		}
	}

	private native void registerJSFuncs() /*-{
											var parent = this;
											$wnd.deleteCLProperty = function(pid) {
											parent.@com.Gharonda.client.view.results.CompareListingView::deleteFromCompareListingList(Ljava/lang/String;)(pid);
											}
											}-*/;
}
