package org.jchlabs.gharonda.client.view.results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jchlabs.gharonda.client.presenter.results.ResultWithMapPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.widgets.AutoCompleteTextBox;
import org.jchlabs.gharonda.client.view.widgets.BaseResultsViewWithSorting;
import org.jchlabs.gharonda.client.view.widgets.CompletionItems;
import org.jchlabs.gharonda.client.view.widgets.PropertiesTableModel;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gen2.table.client.CellRenderer;
import com.google.gwt.gen2.table.client.ColumnDefinition;
import com.google.gwt.gen2.table.client.PagingOptions;
import com.google.gwt.gen2.table.client.AbstractScrollTable.ScrollPolicy;
import com.google.gwt.gen2.table.client.TableDefinition.AbstractCellView;
import com.google.gwt.gen2.table.event.client.PageChangeEvent;
import com.google.gwt.gen2.table.event.client.PageChangeHandler;
import com.google.gwt.gen2.table.event.client.RowHighlightEvent;
import com.google.gwt.gen2.table.event.client.RowHighlightHandler;
import com.google.gwt.gen2.table.event.client.RowUnhighlightEvent;
import com.google.gwt.gen2.table.event.client.RowUnhighlightHandler;
import com.google.gwt.gen2.table.event.client.TableEvent.Row;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;

public class ResultWithMapView extends BaseResultsViewWithSorting<ResultWithMapUiHandlers> implements MyView {
	protected class PropertyCellRenderer implements CellRenderer<PropertiesDTO, Integer> {

		@Override
		public void renderRowValue(PropertiesDTO rowValue, ColumnDefinition<PropertiesDTO, Integer> columnDef,
				AbstractCellView<PropertiesDTO> view) {

			Marker m = new Marker(LatLng.newInstance(rowValue.getLat().doubleValue(), rowValue.getLng().doubleValue()),
					blueOptions);

			m.addMarkerMouseOverHandler(markerOnMouseHandler);
			m.addMarkerMouseOutHandler(new MarkerMouseOutHandler() {

				@Override
				public void onMouseOut(MarkerMouseOutEvent event) {
					Marker m = ((Marker) event.getSource());
					m.setImage("images/map_icon_blue_35x22.gif");
					// map.closeInfoWindow();

				}

			});
			map.addOverlay(m);
			pidToOverLaysMap.put(rowValue.getId(), m);
			overLayToRowIndex.put(m, view.getRowIndex());
			String pType = rowValue.getPType() == 0 ? PropertyOptions.pTypeNotGiven : PropertyOptions.P_TYPES
					.get(rowValue.getPType() - 1);

			String priceStr = PropertyOptions.getFormattedPrice(rowValue.getPrice(), rowValue.getCurrency());

			String addrLine1 = PropertyOptions.getFormattedAddrLine(rowValue.getStreetName(), rowValue.getAddrNum(),
					null, " No:");
			addrLine1 = (addrLine1.isEmpty()) ? PropertyOptions.addressNotGiven : addrLine1;

			String addrLine2 = PropertyOptions
					.getFormattedAddrLine(rowValue.getSuburb(), rowValue.getCity(), null, " ");
			addrLine2 = (addrLine2.isEmpty()) ? PropertyOptions.addressNotGiven : addrLine2;

			String url = "images/img1_listingproperty.jpg";
			Set<Contentitems> items = rowValue.getContentitems();
			for (final Contentitems item : items) {
				final String imageNum = item.getName();
				if (imageNum != null) {
					url = (item.getData() + imageNum + "_tn2.jpg");
					break;
				}
			}
			String detailsUrl = PropertyOptions.getPropertyDetailPageUrl(rowValue.getId());

			view.setHTML("<div class=\"listing_txt\" onClick=\'javascript:window.open(\"" + detailsUrl
					+ "\"); return false;\'>	"
					+ "<img width=\"73\" height=\"59\" border=\"0\" align=\"left\" alt=\"\"		" + "src=\"" + url
					+ "\" />	" + "<span style=\"font-weight:bold\">" + pType + "</span>	<br />"
					+ "<span class=\"price\" style=\"font-weight:bold\">" + priceStr + "</span> <br />" + addrLine1
					+ "<br />	" + addrLine2
					// + "<a href=\"#\">		"
					// +
					// "<img width=\"21\" height=\"12\" border=\"0\" alt=\"\"			src=\"images/imgnext_comparelisting.gif\" />	"
					// + "</a>"
					+ "</div><span class=\"seperator3\" />");

		}

	}

	interface ResultWithMapViewUiBinder extends UiBinder<Widget, ResultWithMapView> {

	}

	private class MapCenterSetter implements AutoCompleteTextBox.SelectionCompleteHandler {

		@Override
		public boolean onComplete(String selection) {
			boolean result = false;
			LatLng center = ResultsHelper.LOC_TO_LATLNG.get(selection);
			if (center != null) {
				map.setCenter(center);
				result = true;
			} else {
				result = false;
			}
			return result;
		}
	}

	private class MonitorSearchBoxSelectionCompleteTimer extends Timer {
		@Override
		public void run() {
			if (searchText.isSelectionCompleted()) {
				searchText.setSelectionCompleted(false);
				MonitorSearchBoxSelectionCompleteTimer.this.cancel();
				updateMap();

			}
		}
	}

	private class PropertyMarkerMouseOverHandler implements MarkerMouseOverHandler {

		@Override
		public void onMouseOver(MarkerMouseOverEvent event) {

			Marker m = ((Marker) event.getSource());
			m.setImage("images/map_icon_red_35x22.gif");
			PropertiesDTO p = propertiesGrid.getRowValue(overLayToRowIndex.get(m));
			InfoWindow w = map.getInfoWindow();
			w.setMaximizeEnabled(true);
			HTML h = new HTML(getPropertyHTML(p));
			w.open(m.getLatLng(), new InfoWindowContent(h));

		}

	}

	private class PropertyPageChangeHandler implements PageChangeHandler {

		@Override
		public void onPageChange(PageChangeEvent event) {
			map.clearOverlays();
		}

	}

	@UiField
	HTMLPanel contentPanel;
	@UiField
	ListBox sftTypesList;
	@UiField
	Anchor showList;

	@UiField
	HTMLPanel mapPanel;
	private MapWidget map;

	@UiField
	HTMLPanel searchPanel;

	AutoCompleteTextBox searchText;

	private final Widget widget;

	private static final ResultWithMapViewUiBinder uiBinder = GWT.create(ResultWithMapViewUiBinder.class);;

	private static final FetchProfile fetchProfile = PropertyOptions.DEFAULT_MAP_SEARCH_PROFILE;
	private static final SearchProfile searchProfile = new SearchProfile(fetchProfile, null, null);
	protected static final int GET_IMAGE_DATE_INTERVAL = 500;
	private static MarkerOptions blueOptions = MarkerOptions.newInstance(Icon
			.newInstance("images/map_icon_blue_35x22.gif"));

	private static MarkerOptions redOptions = MarkerOptions.newInstance(Icon
			.newInstance("images/map_icon_red_35x22.gif"));

	private PropertyMarkerMouseOverHandler markerOnMouseHandler = new PropertyMarkerMouseOverHandler();

	private static Map<Integer, Overlay> pidToOverLaysMap = new HashMap<Integer, Overlay>();

	private static Map<Overlay, Integer> overLayToRowIndex = new HashMap<Overlay, Integer>();

	private MonitorSearchBoxSelectionCompleteTimer timer;

	@Inject
	public ResultWithMapView(final PropertiesTableModel mTableModel, final EventBus eventBus) {
		widget = uiBinder.createAndBindUi(this);
		propertiesGridPanel.getElement().setId("propertiesGridPanel");
		tableModel = mTableModel;
		propertiesGrid = createScrollTable();
		propertiesGridPanel.add(propertiesGrid, "propertiesGridPanel");
		propertiesGrid.setHeight("510px");
		propertiesGrid.setScrollPolicy(ScrollPolicy.BOTH);
		propertiesGrid.addPageChangeHandler(new PropertyPageChangeHandler());
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
		sftTypesList.addItem(PropertyOptions.area);
		for (String sftType : PropertyOptions.SQFT_TYPES) {
			sftTypesList.addItem(sftType);
		}

		for (String sortType : PropertyOptions.SORT_TYPES) {
			sortTypesList.addItem(sortType);
		}

		this.showList.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
		mapPanel.getElement().setId("mapPanel");
		// Open a map centered on Istanbul, Turkey
		LatLng defaultLatLng = LatLng.newInstance(40.98249, 29.00528);
		map = new MapWidget(defaultLatLng, 2);
		map.setSize("629px", "595px");

		// Add some controls
		// map.addControl(new OverviewMapControl());
		map.addControl(new LargeMapControl());
		map.addControl(new MapTypeControl());
		map.addControl(new ScaleControl());
		map.setDoubleClickZoom(true);
		map.setContinuousZoom(true);
		map.setZoomLevel(14);
		mapPanel.add(map, "mapPanel");
		map.setInfoWindowEnabled(true);

		searchPanel.getElement().setId("searchPanel");

		searchText = new AutoCompleteTextBox(new CompletionItems() {
			@Override
			public String[] getCompletionItems(String match) {
				List<String> strList = new ArrayList<String>();

				for (Map.Entry<String, LatLng> entry : ResultsHelper.LOC_TO_LATLNG.entrySet()) {
					if (entry.getKey().toLowerCase().matches(match.toLowerCase() + ".*")) {
						strList.add(entry.getKey());
					}
				}
				String[] res = new String[strList.size()];
				return strList.toArray(res);
			}

		}, new MapCenterSetter());
		searchText.setStyleName("text_field7");
		searchPanel.add(searchText, "searchPanel");

		searchText.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (searchText.getValue().trim().equals(PropertyOptions.selCitySuburb)) {
					searchText.setValue("");
				}

			}

		});
		searchText.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				timer = new MonitorSearchBoxSelectionCompleteTimer();
				timer.scheduleRepeating(GET_IMAGE_DATE_INTERVAL);
			}
		});
		map.addMapDragEndHandler(new MapDragEndHandler() {
			@Override
			public void onDragEnd(MapDragEndEvent event) {
				map.clearOverlays();
				updateMap();

			}
		});

		map.addMapZoomEndHandler(new MapZoomEndHandler() {
			@Override
			public void onZoomEnd(MapZoomEndEvent event) {
				map.clearOverlays();
				updateMap();
			}
		});

		propertiesGrid.getDataTable().addRowHighlightHandler(new RowHighlightHandler() {

			@Override
			public void onRowHighlight(RowHighlightEvent event) {
				Row row = event.getValue();
				if (row.getRowIndex() != -1) {
					PropertiesDTO p = propertiesGrid.getRowValue(row.getRowIndex());
					Marker m = (Marker) pidToOverLaysMap.get(p.getId());
					m.setImage("images/map_icon_red_35x22.gif");
				}
			}

		});

		propertiesGrid.getDataTable().addRowUnhighlightHandler(new RowUnhighlightHandler() {

			@Override
			public void onRowUnhighlight(RowUnhighlightEvent event) {
				Row row = event.getValue();
				if (row.getRowIndex() != -1) {
					PropertiesDTO p = propertiesGrid.getRowValue(row.getRowIndex());
					Marker m = (Marker) pidToOverLaysMap.get(p.getId());
					m.setImage("images/map_icon_blue_35x22.gif");
				}
			}

		});

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public SearchProfile getSearchProfile() {
		searchProfile.setSelListCriteria(getSearchSelectionsCriteriaList());
		PropertyOptions.updateFetchProfileForSort(sortTypesList.getSelectedIndex(), searchProfile.getFetchProfile());
		return searchProfile;

	}

	@UiHandler("showList")
	public void listSelected(ClickEvent event) {
		getUiHandlers().handleViewList();
	}

	@UiHandler({ "sortTypesList", "pTypesList", "sftTypesList", "priceTypesList" })
	public void sortTypeSelected(ChangeEvent event) {
		updateMap();
	}

	@Override
	public void initUI(int mode) {
		searchText.setValue(PropertyOptions.selCitySuburb);
		Element el = DOM.getElementById("searchHeader");
		if (mode == 1) {
			el.setInnerHTML(PropertyOptions.forSaleHeader);
			setWindowTitle("Haritada Sahibinden Satılık Emlak Arayın - Gharonda.com");
		} else {
			el.setInnerHTML(PropertyOptions.forRentHeader);
			setWindowTitle("Haritada Sahibinden Kiralık Emlak Arayın - Gharonda.com");
		}

	}

	@Override
	protected CellRenderer<PropertiesDTO, Integer> getCellRenderer() {
		return new PropertyCellRenderer();
	}

	@Override
	protected int getPageSize() {
		return CommonConstants.DEFAULT_MAP_PAGE_SIZE;
	}

	private String getPropertyHTML(PropertiesDTO property) {

		String priceStr = PropertyOptions.getFormattedPrice(property.getPrice(), property.getCurrency());
		String bedRooms = property.getBedRooms() == 0 ? "0" : PropertyOptions.BEDROOM_TYPES
				.get(property.getBedRooms() - 1);

		String sft = property.getSqrft().toString();

		String pricePerSfStr = PropertyOptions.UNKNOWN + " ";

		if (property.getSqrft() > 0) {
			Integer p = (property.getPrice() / property.getSqrft());
			pricePerSfStr = p.toString();
		}

		String line1 = bedRooms + " " + sft + "m2";
		String line2 = pricePerSfStr + " $/m2";
		String pType = property.getPType() == 0 ? PropertyOptions.UNKNOWN_PROP : PropertyOptions.P_TYPES.get(property
				.getPType() - 1);
		String url = "images/img1_listingproperty.jpg";
		Set<Contentitems> items = property.getContentitems();
		for (final Contentitems item : items) {
			final String imageNum = item.getName();
			if (imageNum != null) {
				url = (item.getData() + imageNum + "_tn2.jpg");
				break;
			}
		}
		String detailsUrl = PropertyOptions.getPropertyDetailPageUrl(property.getId());
		String html = "<div class=\"listing_txt\">	<img width=\"73\" height=\"59\" border=\"0\" align=\"left\" "
				+ "src=\""
				+ url
				+ "\""
				+ "alt=\"\"/>	"
				+ "<span style=\"font-weight: bold;font-size:12px\" class=\"red_txt\">"
				+ priceStr
				+ "</span> "
				+ "<br/>"
				+ "<span style=\"font-weight: bold;\" >"
				+ line1
				+ "</span> "
				+ "<br/>"
				+ "<span style=\"font-weight: bold;\" >"
				+ line2
				+ "</span> "
				+ "<br/>"
				+ "<span style=\"font-weight: bold;\" >"
				+ pType
				+ "<a href=\"#\" onClick=\'javascript:window.open(\""
				+ detailsUrl + "\"); return false;\'>" + PropertyOptions.moreDetails + "</a>" + "</span>" + "</div>";
		return html;
	}

	private List<SearchCriteriaIFace> getSearchSelectionsCriteriaList() {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();

		LatLngBounds bounds = map.getBounds();
		LatLng neBound = bounds.getNorthEast();
		LatLng swBound = bounds.getSouthWest();
		double minLat = swBound.getLatitude();
		double maxLat = neBound.getLatitude();

		double minLng = swBound.getLongitude();
		double maxLng = neBound.getLongitude();

		cList.add(PropertyOptions.getLatRangeSearchCriteria(minLat, maxLat));

		cList.add(PropertyOptions.getLngRangeSearchCriteria(minLng, maxLng));

		if (priceTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPriceSearchCriteria(priceTypesList.getSelectedIndex()));
		}

		if (pTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPropertyTypeSearchCriteria(pTypesList.getSelectedIndex()));
		}

		if (sftTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedSftSearchCriteria(sftTypesList.getSelectedIndex()));
		}

		return cList;
	}

	private void updateMap() {
		SearchProfile mapProfile = getSearchProfile();
		mapProfile.setResultsSizeLimit(-1);
		getUiHandlers().handleMapBoundsChanged(mapProfile);
	}
}
