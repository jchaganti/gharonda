package org.jchlabs.gharonda.client.view.account;

import java.util.LinkedList;
import java.util.List;

import org.jchlabs.gharonda.client.presenter.account.MyFavoritesPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.widgets.BaseResultsView;
import org.jchlabs.gharonda.client.view.widgets.PropertiesTableModel;
import org.jchlabs.gharonda.client.view.widgets.PropertyCellRenderer;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.gen2.table.client.CellRenderer;
import com.google.gwt.gen2.table.client.PagingOptions;
import com.google.gwt.gen2.table.client.AbstractScrollTable.ScrollPolicy;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;

public class MyFavoritesView extends BaseResultsView<MyFavoritesUiHandlers> implements MyView {

	interface MyFavoritesViewUiBinder extends UiBinder<Widget, MyFavoritesView> {

	}

	private static final MyFavoritesViewUiBinder uiBinder = GWT.create(MyFavoritesViewUiBinder.class);;

	private EventBus eventbus;

	@UiField
	HTMLPanel contentPanel;

	private List<Integer> selections = new LinkedList<Integer>();
	public final Widget widget;

	@Inject
	public MyFavoritesView(final PropertiesTableModel mTableModel, final EventBus eventbus) {
		widget = uiBinder.createAndBindUi(this);
		this.contentPanel.getElement().setId("content");
		this.eventbus = eventbus;
		propertiesGridPanel.getElement().setId("propertiesGridPanel");
		tableModel = mTableModel;
		propertiesGrid = createScrollTable();
		propertiesGridPanel.add(propertiesGrid, "propertiesGridPanel");
		propertiesGrid.setHeight("470px");
		propertiesGrid.setScrollPolicy(ScrollPolicy.BOTH);
		PagingOptions pagingOptions = new PagingOptions(propertiesGrid);
		paging.getElement().setId("paging");
		paging.add(pagingOptions, "paging");
		this.contentPanel.getElement().setId("content");
		// this.registerAddToFavorites();
		registerJSFuncs();
	}

	public void loadResults(List<PropertiesDTO> list, int realCount, SearchProfile profile) {
		setWindowTitle("Favori Listem - Gharonda.com");
		super.loadResults(list, realCount, profile);
	}

	public void addToSelections(String id) {
		Element elem = DOM.getElementById("cb_" + id);
		if (elem instanceof InputElement) {
			InputElement in = (InputElement) elem;
			if (in.isChecked() && !selections.contains(Integer.parseInt(id))) {
				selections.add(Integer.parseInt(id));
			} else if (selections.contains(Integer.parseInt(id))) {
				selections.remove(new Integer(Integer.parseInt(id)));
			}
		}
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public void fireAddToFavouriteEvent(String id) {
		// eventbus.fireEvent(new AddFavoriteEvent(Integer.parseInt(id)));
	}

	@UiHandler({ "compare", "compareBtm" })
	public void onCompareClicked(ClickEvent event) {
		List<Integer> comparisons = getSelections();
		if (comparisons.size() < 2) {
			Window.alert(PropertyOptions.comparisonsSize);
			return;
		}
		String clUrl = PropertyOptions.getCompareListingPageUrl();
		PropertyOptions.setCookieCL(PropertyOptions.COMPARE_LISTING_COOKIE, comparisons);
		Window.open(clUrl, "", "");
	}

	@UiHandler({ "removeFav", "removeFavBtm" })
	public void onRemoveFromFavoritesClicked(ClickEvent event) {
		List<Integer> pids = getSelections();
		if (pids.size() > 0) {
			getUiHandlers().handleRemoveFromFavoritesClicked(pids);
		} else {
			Window.alert("Make at least one selection for removing from Favorites");
		}

	}

	public native void registerAddToFavorites() /*-{
												var parent = this;
												$wnd.addToFavorites = function(pid) {
												parent.@com.Gharonda.client.view.results.ResultWithoutMapView::fireAddToFavouriteEvent(Ljava/lang/String;)(pid);			
												};
												}-*/;

	public native void registerJSFuncs() /*-{
											var parent = this;
											$wnd.addToCompareListingMyFav = function(pid) {
											//window.alert(" addToCompareListing typeof pid = " + typeof pid);
											parent.@com.Gharonda.client.view.account.MyFavoritesView::addToSelections(Ljava/lang/String;)(pid);			
											};
											}-*/;

	@Override
	protected CellRenderer<PropertiesDTO, Integer> getCellRenderer() {
		return new PropertyCellRenderer("MyFav");
	}

	@Override
	protected int getPageSize() {
		return 3;
	}

	@UiHandler({ "myProfile", "myListings", "myNotifierProfile" })
	void onMyAccountClicked(ClickEvent event) {
		getUiHandlers().handleMyAccountClicked();
	}

	private List<Integer> getSelections() {
		List<Integer> selectionsCopy = new LinkedList<Integer>();
		for (Integer i : selections) {
			selectionsCopy.add(i);
		}
		selections = new LinkedList<Integer>();
		return selectionsCopy;
	}
}
