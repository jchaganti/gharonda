package org.jchlabs.gharonda.client.view.details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.details.PropertyDetailsWithMapPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.Neightbourhood;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.GetNeighbourhoodData;
import org.jchlabs.gharonda.shared.rpc.GetNeighbourhoodDataResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapPane;
import com.google.gwt.maps.client.MapPaneType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.search.client.LocalResult;
import com.google.gwt.search.client.LocalSearch;
import com.google.gwt.search.client.Result;
import com.google.gwt.search.client.ResultSetSize;
import com.google.gwt.search.client.SearchControl;
import com.google.gwt.search.client.SearchControlOptions;
import com.google.gwt.search.client.SearchResultsHandler;
import com.google.gwt.search.client.SearchUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;

public class PropertyDetailsWithMapView extends AbstractAppView<PropertyDetailsWithoutMapUiHandlers> implements MyView {
	interface PropertyDetailsWithMapViewUiBinder extends UiBinder<Widget, PropertyDetailsWithMapView> {

	}

	private class GetNeighbourhoodDataTimer extends Timer {
		@Override
		public void run() {
			if (!requestInProgress) {

				requestInProgress = true;
				if (neighbourhoodRequests.size() == 0) {
					requestInProgress = false;
					GetNeighbourhoodDataTimer.this.cancel();
					return;
				}
				final List<Integer> curNeighbourhoods = new ArrayList<Integer>();
				for (Integer i : neighbourhoodRequests) {
					curNeighbourhoods.add(i);
				}
				List<SearchCriteriaIFace> mapBounds = getMapBoundsSearchCriteria();
				List<SearchCriteriaIFace> neighbourhoodSelectionList = getNeighbourhoodSelectionList(curNeighbourhoods);
				dispatcher.execute(new GetNeighbourhoodData(neighbourhoodSelectionList, mapBounds),
						new AsyncCallback<GetNeighbourhoodDataResult>() {
							public void onFailure(Throwable caught) {
								requestInProgress = false;
								GetNeighbourhoodDataTimer.this.cancel();
							}

							public void onSuccess(GetNeighbourhoodDataResult result) {
								for (Integer i : curNeighbourhoods) {
									neighbourhoodRequests.remove(i);
								}
								displayResults(result.getNeighbourhood());
								requestInProgress = false;
								GetNeighbourhoodDataTimer.this.cancel();
							}
						});
			}

		}

	}

	private class GetLocalSearchTimer extends Timer {
		@Override
		public void run() {
			if (!requestInProgress) {
				requestInProgress = true;
				if (neighbourhoodRequests.size() == 0) {
					requestInProgress = false;
					GetLocalSearchTimer.this.cancel();
					return;
				}
				final List<Integer> curNeighbourhoods = new ArrayList<Integer>();
				for (Integer i : neighbourhoodRequests) {
					curNeighbourhoods.add(i);
				}
				List<SearchCriteriaIFace> mapBounds = getMapBoundsSearchCriteria();
				List<SearchCriteriaIFace> neighbourhoodSelectionList = getNeighbourhoodSelectionList(curNeighbourhoods);
				dispatcher.execute(new GetNeighbourhoodData(neighbourhoodSelectionList, mapBounds),
						new AsyncCallback<GetNeighbourhoodDataResult>() {
							public void onFailure(Throwable caught) {
								requestInProgress = false;
								GetLocalSearchTimer.this.cancel();
							}

							public void onSuccess(GetNeighbourhoodDataResult result) {
								for (Integer i : curNeighbourhoods) {
									neighbourhoodRequests.remove(i);
								}
								displayResults(result.getNeighbourhood());
								requestInProgress = false;
								GetLocalSearchTimer.this.cancel();
							}
						});
			}

		}

	}

	private static final String BATH = " " + PropertyOptions.baths;
	private static final String PER_SFT_PRICE = PropertyOptions.pricePerSft;
	private static final String PROPERTY_NUM = PropertyOptions.propNum;
	private static final String ROOMS = PropertyOptions.rooms;
	private static final String SQ_FT = " m2";
	private static final PropertyDetailsWithMapViewUiBinder uiBinder = GWT
			.create(PropertyDetailsWithMapViewUiBinder.class);
	private Map<Integer, MarkerOptions> categoryToMarkerOptions = new HashMap<Integer, MarkerOptions>();
	private Map<Integer, List<Marker>> categoryToMarkers = new HashMap<Integer, List<Marker>>();

	private DispatchAsync dispatcher;

	private EventBus eventBus;

	private MapWidget map;
	private List<Integer> neighbourhoodRequests = new ArrayList<Integer>();
	private boolean requestInProgress = false;

	private final Widget widget;
	@UiField
	HTML address1;
	@UiField
	HTML address2;
	@UiField
	HTML buildDate;
	@UiField
	CheckBox cb1; // Alışveriş
	@UiField
	CheckBox cb2; // Okul
	@UiField
	CheckBox cb3; // Hastane
	@UiField
	CheckBox cb4; // Eczane
	@UiField
	CheckBox cb5; // Kafe
	@UiField
	CheckBox cb6; // Restoran

	@UiField
	CheckBox cb7; // Banka ATM
	@UiField
	CheckBox cb8; // Benzin İstasyonu
	@UiField
	HTML cell;
	@UiField
	HTMLPanel contentPanel;

	@UiField
	HTML created;
	@UiField
	HTMLPanel featuredListing;
	@UiField
	HTML floor;
	@UiField
	HTML heat;
	@UiField
	HTML homeLoan;
	@UiField
	HTMLPanel mapPanel;
	@UiField
	HTML modified;

	@UiField
	HTML name;

	@UiField
	HTML phone;

	@UiField
	HTML pid;
	@UiField
	HTML price;
	@UiField
	HTML priceperSft;
	@UiField
	HTML rooms;
	@UiField
	HTML topDesc;
	@UiField
	HTML view;

	@UiField
	HTML companyName;
	private MapPane mapPane;
	private PropertiesDTO property;

	@Inject
	public PropertyDetailsWithMapView(final EventBus eventbus, final DispatchAsync dispatcher) {
		widget = uiBinder.createAndBindUi(this);
		this.contentPanel.getElement().setId("content");
		this.eventBus = eventbus;
		this.dispatcher = dispatcher;
		mapPanel.getElement().setId("mapPanel");
		map = new MapWidget();
		map.setSize("400px", "390px");
		mapPanel.add(map, "mapPanel");

		/*
		 * cb1.addClickHandler(getCBClickHandler(1, cb1));
		 * 
		 * cb2.addClickHandler(getCBClickHandler(2, cb2));
		 * 
		 * cb3.addClickHandler(getCBClickHandler(3, cb3));
		 * 
		 * cb4.addClickHandler(getCBClickHandler(4, cb4));
		 * 
		 * cb5.addClickHandler(getCBClickHandler(5, cb5));
		 * 
		 * cb6.addClickHandler(getCBClickHandler(6, cb6));
		 * 
		 * cb7.addClickHandler(getCBClickHandler(7, cb7));
		 * 
		 * cb8.addClickHandler(getCBClickHandler(8, cb8));
		 */
		cb1.addClickHandler(getCBClickHandler("Shopping", 1, cb1));
		cb2.addClickHandler(getCBClickHandler("School", 2, cb2));
		cb3.addClickHandler(getCBClickHandler("Hospital", 3, cb3));
		cb4.addClickHandler(getCBClickHandler("Pharmacy", 4, cb4));
		cb5.addClickHandler(getCBClickHandler("Cafe", 5, cb5));
		cb6.addClickHandler(getCBClickHandler("Restaurant", 6, cb6));
		cb7.addClickHandler(getCBClickHandler("Bank", 7, cb7));
		cb8.addClickHandler(getCBClickHandler("Gas", 8, cb8));

		categoryToMarkerOptions
				.put(new Integer(1), MarkerOptions.newInstance(Icon.newInstance("images/alisveris.png")));

		categoryToMarkerOptions.put(new Integer(2), MarkerOptions.newInstance(Icon.newInstance("images/okul.png")));

		categoryToMarkerOptions.put(new Integer(3), MarkerOptions.newInstance(Icon.newInstance("images/hastane.png")));

		categoryToMarkerOptions.put(new Integer(4), MarkerOptions.newInstance(Icon.newInstance("images/eczane.png")));

		categoryToMarkerOptions.put(new Integer(5), MarkerOptions.newInstance(Icon.newInstance("images/cafe.png")));

		categoryToMarkerOptions.put(new Integer(6), MarkerOptions.newInstance(Icon.newInstance("images/restoran.png")));

		categoryToMarkerOptions
				.put(new Integer(7), MarkerOptions.newInstance(Icon.newInstance("images/banka_atm.png")));

		categoryToMarkerOptions.put(new Integer(8),
				MarkerOptions.newInstance(Icon.newInstance("images/benzin_istasyonu.png")));

		map.setDraggable(true);
		map.setContinuousZoom(true);
		map.addControl(new ScaleControl());
		map.addControl(new LargeMapControl());
		map.setDoubleClickZoom(true);
		map.setZoomLevel(16);
		mapPane = map.getPane(MapPaneType.FLOAT_PANE);
		mapPane.setVisible(false);
		setFeaturedistingPanel(featuredListing);
		SearchUtils.loadSearchApi(new Runnable() {
			@Override
			public void run() {

			}
		});

		// map.addMapDragEndHandler(new MapDragEndHandler() {
		//
		// @Override
		// public void onDragEnd(MapDragEndEvent event) {
		//
		// //map.clearOverlays();
		// //categoryToMarkers = new HashMap<Integer, List<Marker>>();
		// cb1.setValue(false);
		// cb2.setValue(false);
		// cb3.setValue(false);
		// cb4.setValue(false);
		// cb5.setValue(false);
		// cb6.setValue(false);
		// cb7.setValue(false);
		// cb8.setValue(false);
		// }
		// });
		//
		// map.addMapZoomEndHandler(new MapZoomEndHandler() {
		// @Override
		// public void onZoomEnd(MapZoomEndEvent event) {
		// //map.clearOverlays();
		// //categoryToMarkers = new HashMap<Integer, List<Marker>>();
		// cb1.setValue(false);
		// cb2.setValue(false);
		// cb3.setValue(false);
		// cb4.setValue(false);
		// cb5.setValue(false);
		// cb6.setValue(false);
		// cb7.setValue(false);
		// cb8.setValue(false);
		// }
		// });

	}

	@Override
	public Widget asWidget() {
		return widget;
	};

	public List<SearchCriteriaIFace> getMapBoundsSearchCriteria() {
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

		return cList;
	}

	public List<SearchCriteriaIFace> getNeighbourhoodSelectionList(List<Integer> curNeighbourhoods) {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		for (Integer i : curNeighbourhoods) {
			cList.add(new NumberSearchCriteria(Neightbourhood.PROP_CATEGORY, i.intValue()));
		}
		return cList;
	}

	@Override
	public void showProperty(PropertiesDTO property, Users user) {

		this.property = property;
		pid.setHTML(PROPERTY_NUM + property.getId());
		String pType = property.getPType() == 0 ? PropertyOptions.UNKNOWN_PROP : PropertyOptions.P_TYPES.get(property
				.getPType() - 1);
		String pMode = property.getPMode().intValue() == 1 ? PropertyOptions.forSale : PropertyOptions.rent;
		String topDescStr = "   " + PropertyOptions.getFormattedPTypeNMode(pType, pMode)
				+ " - <span style=\"color:#516C72\"> " + property.getTitle() + "</span>";
		setWindowTitle(PropertyOptions.getFormattedPTypeNMode(pType, pMode) + " " + property.getTitle()
				+ " - Gharonda.com");
		topDesc.setHTML(topDescStr);
		String priceStr = PropertyOptions.getFormattedPrice(property.getPrice(), property.getCurrency());
		price.setHTML(priceStr);

		String bedRooms = property.getBedRooms() == 0 ? "0" : PropertyOptions.BEDROOM_TYPES
				.get(property.getBedRooms() - 1);
		String bathRooms = property.getBathRooms().toString();
		String sft = property.getSqrft().toString();

		String roomsStr = bedRooms + ", " + bathRooms + " " + BATH + ", " + sft + SQ_FT;

		rooms.setHTML(roomsStr);

		String addrLine1 = PropertyOptions.getFormattedAddrLine(property.getStreetName(), property.getAddrNum(), null,
				" No:");
		address1.setHTML(addrLine1);

		String addrLine2 = PropertyOptions.getFormattedAddrLine(property.getSuburb(), property.getCity(),
				property.getState(), " ");
		address2.setHTML(addrLine2);

		String selHeat = property.getHeat() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.HEAT_TYPES.get(property
				.getHeat() - 1);
		heat.setHTML(selHeat);

		String selView = property.getView() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.VIEW_TYPES.get(property
				.getView() - 1);
		view.setHTML(selView);

		buildDate.setHTML(property.getBuildDate());

		String selHomeLoan = property.getHomeLoan() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.YES_NO_OPTIONS
				.get(property.getHomeLoan() - 1);
		homeLoan.setHTML(selHomeLoan);

		String selFloor = property.getFloor() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.FLOORS_TYPES
				.get(property.getFloor() - 1);

		floor.setHTML(selFloor);

		String pricePerSfStr = PropertyOptions.UNKNOWN + " ";

		if (property.getSqrft() > 0) {
			Integer p = (property.getPrice() / property.getSqrft());
			pricePerSfStr = p.toString();
		}

		priceperSft.setHTML(pricePerSfStr);

		created.setHTML(PropertyOptions.getFormattedDate(property.getCreated()));
		modified.setHTML(PropertyOptions.getFormattedDate(property.getTimeStamp()));

		name.setHTML(user.getFirstName() + " " + user.getLastName());

		phone.setHTML(user.getPhone());

		cell.setHTML(user.getCell());
		if (user.getServiceproviderdetails() != null) {

			companyName.setHTML(user.getServiceproviderdetails().getCompanyName());
		}

		map.clearOverlays();
		LatLng center = LatLng.newInstance(property.getLat().doubleValue(), property.getLng().doubleValue());
		map.setCenter(center);
		Marker m = new Marker(center);
		map.addOverlay(m);

	}

	protected void loadResults(String query) {

	}

	@UiHandler("addToFav")
	void onSignMeInClicked(ClickEvent event) {
		getUiHandlers().handleAddToFavorite();
	}

	@UiHandler("details")
	void onDetailsClicked(ClickEvent event) {
		getUiHandlers().handleTabClicked(NameTokens.propertyDetailsWOMapPage);
	}

	@UiHandler("mortgage")
	void onMortgageInClicked(ClickEvent event) {
		getUiHandlers().handleTabClicked(NameTokens.propertyDetailsMortgagePage);
	}

	@UiHandler("showOnMap")
	void onShowOnMapInClicked(ClickEvent event) {
		getUiHandlers().handleTabClicked(NameTokens.propertyDetailsWMapPage);
	}

	@UiHandler("appraisal")
	void onAppraisalClicked(ClickEvent event) {
		Window.open(PropertyOptions.getAppraisalPageUrl(), "", "");
	}

	private void displayResults(List<Neightbourhood> neighbourhoodList) {

		for (Neightbourhood n : neighbourhoodList) {

			Marker m = new Marker(LatLng.newInstance(n.getLat().doubleValue(), n.getLng().doubleValue()),
					categoryToMarkerOptions.get(n.getCategory()));

			// m.setImage(categoryToIconsMap.get(n.getCategory()));
			// m.setImage("images/benzin_istasyonu.png");

			List<Marker> overlayList = null;// categoryToMarkers.get(n.getCategory
			// ());
			if (!categoryToMarkers.containsKey(n.getCategory())) {
				overlayList = new ArrayList<Marker>();
				categoryToMarkers.put(n.getCategory(), overlayList);

			} else {
				overlayList = categoryToMarkers.get(n.getCategory());
			}

			overlayList.add(m);
			map.addOverlay(m);
		}

	}

	private ClickHandler getCBClickHandler(final String query, final int category, final CheckBox cb) {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (cb.getValue()) {
					SearchControlOptions options = new SearchControlOptions();
					LocalSearch s = new LocalSearch();
					s.setResultSetSize(ResultSetSize.LARGE);
					s.setCenterPoint(property.getLat().doubleValue() + "," + property.getLng().doubleValue());
					options.add(s);
					SearchControl searchControl = new SearchControl(options);
					searchControl.addSearchResultsHandler(new SearchResultsHandler() {
						@Override
						public void onSearchResults(SearchResultsEvent event) {
							JsArray<? extends Result> results = event.getResults().cast();
							for (int i = 0; i < results.length(); i++) {
								final LocalResult res = (LocalResult) results.get(i);

								final Marker m = new Marker(LatLng.newInstance(res.getLat(), res.getLng()),
										categoryToMarkerOptions.get(category));
								String resHtml = "";
								boolean newLine = false;
								if (!res.getTitle().isEmpty()) {
									resHtml += res.getTitle();
									if (res.getPhoneNumbers() != null && res.getPhoneNumbers().length() > 0) {
										resHtml += " - ";
										for (int n = 0; n < res.getPhoneNumbers().length(); n++) {
											String num = res.getPhoneNumbers().get(n).getNumber();
											if (n != 0 && n < res.getPhoneNumbers().length() - 1) {
												resHtml += ", ";
											}
											resHtml += num;
										}
									}
									newLine = true;
								}
								if (!res.getStreetAddress().isEmpty()) {
									if (newLine) {
										resHtml += "<br/>";
									}
									resHtml += res.getStreetAddress();
									newLine = true;
								}

								final Widget w = new HTML("<div class='tooltip'>" + resHtml + "</div>");

								w.setVisible(false);
								// m.addMarkerClickHandler(new MarkerClickHandler() {
								//
								// @Override
								// public void onClick(
								// MarkerClickEvent event) {
								// mapPane.clear();
								// mapPane.add(w);
								// final Point p = map.convertLatLngToDivPixel(m.getLatLng());
								// mapPane.setWidgetPosition(w, p.getX() + 10, p.getY() + 10);
								//
								// if(!mapPane.isVisible()) {
								// mapPane.setVisible(true);
								// }
								//
								// if(!w.isVisible()) {
								// w.setVisible(true);
								// }
								//
								// }
								//
								// });
								m.addMarkerMouseOverHandler(new MarkerMouseOverHandler() {

									@Override
									public void onMouseOver(MarkerMouseOverEvent event) {
										mapPane.clear();
										mapPane.add(w);
										final Point p = map.convertLatLngToDivPixel(m.getLatLng());
										mapPane.setWidgetPosition(w, p.getX() + 20, p.getY() + 20);

										if (!mapPane.isVisible()) {
											mapPane.setVisible(true);
										}

										if (!w.isVisible()) {
											w.setVisible(true);
										}
									}

								});
								m.addMarkerMouseOutHandler(new MarkerMouseOutHandler() {

									@Override
									public void onMouseOut(MarkerMouseOutEvent event) {
										if (w.isVisible()) {
											w.setVisible(false);
										}
									}

								});
								List<Marker> overlayList = null;
								if (!categoryToMarkers.containsKey(category)) {
									overlayList = new ArrayList<Marker>();
									categoryToMarkers.put(category, overlayList);

								} else {
									overlayList = categoryToMarkers.get(category);
								}
								overlayList.add(m);
								map.addOverlay(m);
							}
						}
					});

					searchControl.execute(query);
				} else if (categoryToMarkers.containsKey(category) && cb.getValue()) {
					showResults(category);
				} else if (categoryToMarkers.containsKey(category) && !cb.getValue()) {
					hideResults(category);
				}

			}
		};
	}

	private ClickHandler getCBClickHandler(final int i, final CheckBox cb) {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleClick(new Integer(i), cb);
			}
		};
	}

	private void handleClick(Integer cbVal, CheckBox cb) {
		if (cb.getValue() && !categoryToMarkers.containsKey(cbVal)) {
			neighbourhoodRequests.add(cbVal);
			GetNeighbourhoodDataTimer timer = new GetNeighbourhoodDataTimer();
			timer.scheduleRepeating(500);
		} else if (categoryToMarkers.containsKey(cbVal) && cb.getValue()) {
			showResults(cbVal);
		} else if (categoryToMarkers.containsKey(cbVal) && !cb.getValue()) {
			hideResults(cbVal);
		}

	}

	private void hideResults(Integer category) {
		List<Marker> markers = categoryToMarkers.get(category);
		for (Marker m : markers) {
			m.setVisible(false);
		}

	}

	private void showResults(Integer category) {
		List<Marker> markers = categoryToMarkers.get(category);
		for (Marker m : markers) {
			m.setVisible(true);
		}
	}
}
