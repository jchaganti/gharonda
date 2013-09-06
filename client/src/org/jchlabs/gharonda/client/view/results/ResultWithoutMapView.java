package org.jchlabs.gharonda.client.view.results;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jchlabs.gharonda.client.presenter.results.ResultWithoutMapPresenter.MyView;
import org.jchlabs.gharonda.client.util.AutoCmpleteTextBoxHelper2;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.widgets.BaseResultsViewWithSorting;
import org.jchlabs.gharonda.client.view.widgets.PropertiesTableModel;
import org.jchlabs.gharonda.client.view.widgets.PropertyCellRenderer;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.gen2.table.client.CellRenderer;
import com.google.gwt.gen2.table.client.PagingOptions;
import com.google.gwt.gen2.table.client.AbstractScrollTable.ScrollPolicy;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.inject.Inject;

public class ResultWithoutMapView extends BaseResultsViewWithSorting<ResultWithoutMapUiHandlers> implements MyView {
	interface ResultWithoutMapViewUiBinder extends UiBinder<Widget, ResultWithoutMapView> {
	}

	@UiField
	HTMLPanel contentPanel;

	@UiField
	ListBox bedRoomsList;

	@UiField
	TextBox suburb;
	SuggestBox suburbBox;
	@UiField
	HTMLPanel suburbPanel;
	@UiField
	TextBox city;
	SuggestBox cityBox;
	@UiField
	HTMLPanel cityPanel;
	@UiField
	TextBox state;
	@UiField
	HTMLPanel statePanel;
	SuggestBox stateBox;
	@UiField
	Anchor search;
	@UiField
	Image showMap;
	@UiField
	Image compare;
	@UiField
	Image compareBtm;
	@UiField
	HTMLPanel featuredListing;

	private static ResultWithoutMapViewUiBinder uiBinder = GWT.create(ResultWithoutMapViewUiBinder.class);

	private final Widget widget;
	private static final FetchProfile fetchProfile = PropertyOptions.DEFAULT_LIST_SEARCH_PROFILE;
	private static final SearchProfile searchProfile = new SearchProfile(fetchProfile, null, null);
	private List<Integer> compareListing = new LinkedList<Integer>();

	@Inject
	public ResultWithoutMapView(final PropertiesTableModel mTableModel) {
		widget = uiBinder.createAndBindUi(this);
		propertiesGridPanel.getElement().setId("propertiesGridPanel");
		tableModel = mTableModel;
		propertiesGrid = createScrollTable();
		propertiesGridPanel.add(propertiesGrid, "propertiesGridPanel");
		propertiesGrid.setHeight("1550px");
		propertiesGrid.setScrollPolicy(ScrollPolicy.BOTH);
		PagingOptions pagingOptions = new PagingOptions(propertiesGrid);
		paging.getElement().setId("paging");
		paging.add(pagingOptions, "paging");
		this.contentPanel.getElement().setId("content");
		pTypesList.addItem(PropertyOptions.anyType);
		for (String type : PropertyOptions.P_TYPES) {
			pTypesList.addItem(type);
		}

		priceTypesList.addItem(PropertyOptions.price0);
		for (String priceType : PropertyOptions.PRICE_TYPES) {
			priceTypesList.addItem(priceType);
		}

		bedRoomsList.addItem(PropertyOptions.anyRoomNo);
		for (String bedRoomType : PropertyOptions.BEDROOM_TYPES) {
			bedRoomsList.addItem(bedRoomType);
		}

		for (String sortType : PropertyOptions.SORT_TYPES) {
			sortTypesList.addItem(sortType);
		}
		this.showMap.getElement().getStyle().setTextDecoration(TextDecoration.NONE);

		this.registerJSFuncs();
		statePanel.getElement().setId("state");
		MultiWordSuggestOracle stateOracle = new MultiWordSuggestOracle(" ");

		stateOracle.addAll(AutoCmpleteTextBoxHelper2.stateCityMap.keySet());
		// stateOracle
		// .setDefaultSuggestionsFromText(AutoCmpleteTextBoxHelper2.stateCityMap
		// .keySet());
		stateBox = new SuggestBox(stateOracle, state);
		stateBox.showSuggestionList();
		stateBox.setAnimationEnabled(true);
		stateBox.setAutoSelectEnabled(false);
		stateBox.setStyleName("text_field14");
		statePanel.add(stateBox, "state");
		state.setTitle("STATE");
		stateBox.getTextBox().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				stateBox.showSuggestionList();
			}

		});

		cityPanel.getElement().setId("city");

		final MultiWordSuggestOracle cityOracle = new MultiWordSuggestOracle("");
		cityBox = new SuggestBox(cityOracle, city);
		cityBox.setAnimationEnabled(true);
		cityBox.setAutoSelectEnabled(false);
		cityBox.setStyleName("text_field14");
		cityPanel.add(cityBox, "city");
		city.setEnabled(false);
		cityBox.getTextBox().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cityBox.showSuggestionList();

			}

		});

		suburbPanel.getElement().setId("suburb");

		final MultiWordSuggestOracle suburbOracle = new MultiWordSuggestOracle("");

		suburbBox = new SuggestBox(suburbOracle, suburb);
		suburbBox.setAnimationEnabled(true);
		suburbBox.setAutoSelectEnabled(false);
		suburbBox.setStyleName("text_field15");
		suburbPanel.add(suburbBox, "suburb");
		suburb.setEnabled(false);
		suburbBox.getTextBox().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				suburbBox.showSuggestionList();

			}

		});
		stateBox.addSelectionHandler(new SelectionHandler<Suggestion>() {

			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				cityOracle.clear();
				suburbOracle.clear();
				city.setEnabled(true);
				Map<String, List<String>> citySuburbMap = AutoCmpleteTextBoxHelper2.stateCityMap.get(stateBox
						.getValue());
				cityOracle.addAll(citySuburbMap.keySet());
				cityOracle.setDefaultSuggestionsFromText(citySuburbMap.keySet());
			}

		});

		cityBox.addSelectionHandler(new SelectionHandler<Suggestion>() {

			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				suburbOracle.clear();
				suburb.setEnabled(true);
				Map<String, List<String>> citySuburbMap = AutoCmpleteTextBoxHelper2.stateCityMap.get(stateBox
						.getValue());

				String selCity = cityBox.getValue();

				for (Map.Entry<String, List<String>> cityToSuburbMap : citySuburbMap.entrySet()) {
					if (cityToSuburbMap.getKey().equals(selCity)) {
						suburbOracle.addAll(cityToSuburbMap.getValue());
						suburbOracle.setDefaultSuggestionsFromText(cityToSuburbMap.getValue());
						break;
					}
				}

			}

		});
		setFeaturedistingPanel(featuredListing);

	}

	public void addToCompareListingList(String id) {
		Element elem = DOM.getElementById("cb_" + id);
		if (elem instanceof InputElement) {
			InputElement in = (InputElement) elem;
			if (in.isChecked() && !compareListing.contains(Integer.parseInt(id))) {
				compareListing.add(Integer.parseInt(id));
			} else if (compareListing.contains(Integer.parseInt(id))) {
				compareListing.remove(new Integer(Integer.parseInt(id)));
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

	public List<Integer> getCompareListingNew() {
		List<Integer> compareListingCopy = new LinkedList<Integer>();
		for (Integer i : compareListing) {
			compareListingCopy.add(i);
		}
		compareListing = new LinkedList<Integer>();
		return compareListingCopy;
	}

	@Override
	public SearchProfile getDefaultSearchProfile() {
		updateSearchProfile();
		return searchProfile;
	}

	@Override
	public void initUI(int mode) {
		Element el = DOM.getElementById("searchHeader");
		if (mode == 1) {
			el.setInnerHTML(PropertyOptions.forSaleHeader);
			setWindowTitle("Sahibinden Satılık Arama Sonuçları - Gharonda.com");
		} else {
			el.setInnerHTML(PropertyOptions.forRentHeader);
			setWindowTitle("Sahibinden Kiralık Arama Sonuçları - Gharonda.com");
		}

		if (state.getValue().isEmpty() || state.getValue().equals(PropertyOptions.STATE_STR)) {
			state.setValue(PropertyOptions.STATE_STR);
		}

		if (city.getValue().isEmpty() || city.getValue().equals(PropertyOptions.CITY_STR)) {
			city.setValue(PropertyOptions.CITY_STR);
		}

		if (suburb.getValue().isEmpty() || suburb.getValue().equals(PropertyOptions.SUBURB_STR)) {
			suburb.setValue(PropertyOptions.SUBURB_STR);
		}

	}

	@UiHandler("city")
	public void onCityClicked(ClickEvent event) {
		if (city.getValue().equals(PropertyOptions.CITY_STR)) {
			city.setValue("");
		}
	}

	@UiHandler({ "compare", "compareBtm" })
	public void onCompareClicked(ClickEvent event) {
		List<Integer> comparisons = getCompareListingNew();
		if (comparisons.size() < 2) {
			Window.alert(PropertyOptions.comparisonsSize);
			return;
		}
		String clUrl = PropertyOptions.getCompareListingPageUrl();
		PropertyOptions.setCookieCL(PropertyOptions.COMPARE_LISTING_COOKIE, comparisons);
		Window.open(clUrl, "", "");
	}

	@UiHandler("search")
	public void onSearchClicked(ClickEvent event) {
		updateSearchProfile();
		getUiHandlers().handleSearch(searchProfile);
	}

	@UiHandler("showMap")
	public void onShowMapClicked(ClickEvent event) {
		getUiHandlers().handleViewMap();
	}

	@UiHandler("sortTypesList")
	public void onSortTypeSelected(ChangeEvent event) {
		updateSearchProfile();
		getUiHandlers().handleSearch(searchProfile);
	}

	@UiHandler("state")
	public void onStateClicked(ClickEvent event) {
		if (state.getValue().equals(PropertyOptions.STATE_STR)) {
			state.setValue("");
		}
	}

	@UiHandler("suburb")
	public void onSuburbClicked(ClickEvent event) {
		if (suburb.getValue().equals(PropertyOptions.SUBURB_STR)) {
			suburb.setValue("");
		}
	}

	@Override
	protected CellRenderer<PropertiesDTO, Integer> getCellRenderer() {
		return new PropertyCellRenderer();
	}

	@Override
	protected int getPageSize() {
		return CommonConstants.DEFAULT_LIST_PAGE_SIZE;
	}

	private List<SearchCriteriaIFace> getSearchSelectionsCriteriaList() {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();

		if (priceTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPriceSearchCriteria(priceTypesList.getSelectedIndex()));
		}

		if (pTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPropertyTypeSearchCriteria(pTypesList.getSelectedIndex()));
		}

		if (bedRoomsList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedBedRoomsSearchCriteria(bedRoomsList.getSelectedIndex()));
		}

		if (state.getValue() != null && !state.getValue().trim().isEmpty()
				&& !PropertyOptions.STATE_STR.equals(state.getValue())) {
			cList.add(PropertyOptions.getStateSearchCriteria(state.getValue()));
		}

		if (city.getValue() != null && !city.getValue().trim().isEmpty()
				&& !PropertyOptions.CITY_STR.equals(city.getValue())) {
			cList.add(PropertyOptions.getCitySearchCriteria(city.getValue()));
		}

		if (suburb.getValue() != null && !suburb.getValue().trim().isEmpty()
				&& !PropertyOptions.SUBURB_STR.equals(suburb.getValue())) {
			cList.add(PropertyOptions.getSuburbSearchCriteria(suburb.getValue()));
		}

		return cList;
	}

	private native void registerJSFuncs() /*-{
											var parent = this;
											$wnd.addToFavorites_1 = function(propId) {
											parent.@com.Gharonda.client.view.results.ResultWithoutMapView::fireAddToFavouriteEvent(Ljava/lang/String;)(propId);			
											};
											$wnd.addToCompareListing = function(pid) {
											//window.alert(" addToCompareListing typeof pid = " + typeof pid);
											parent.@com.Gharonda.client.view.results.ResultWithoutMapView::addToCompareListingList(Ljava/lang/String;)(pid);			
											};

											}-*/;

	private void updateSearchProfile() {
		List<SearchCriteriaIFace> selListCriteria = getSearchSelectionsCriteriaList();
		searchProfile.setSelListCriteria(selListCriteria);
		if (selListCriteria.size() > 0) {
			searchProfile.setResultsSizeLimit(-1);
		}
		searchProfile.setFetchProfile(new FetchProfile(CommonConstants.DEFAULT_LIST_PAGE_SIZE,
				PropertyOptions.DEFAULT_FETCH_START, PropertyOptions.DEFAULT_SORT_ATTR, false));
		PropertyOptions.updateFetchProfileForSort(sortTypesList.getSelectedIndex(), searchProfile.getFetchProfile());
	}
}
