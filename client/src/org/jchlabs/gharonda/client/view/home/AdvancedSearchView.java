package org.jchlabs.gharonda.client.view.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jchlabs.gharonda.client.presenter.home.AdvancedSearchPresenter.MyView;
import org.jchlabs.gharonda.client.util.AutoCmpleteTextBoxHelper2;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.inject.Inject;

public class AdvancedSearchView extends AbstractAppView<AdvancedSearchUiHandlers> implements MyView {
	interface AdvancedSearchViewUiBinder extends UiBinder<Widget, AdvancedSearchView> {

	}

	private static final AdvancedSearchViewUiBinder uiBinder = GWT.create(AdvancedSearchViewUiBinder.class);
	@UiField
	HTMLPanel contentPanel;
	@UiField
	ListBox pTypesList;
	@UiField
	ListBox priceTypesList;
	@UiField
	ListBox sftTypesList;
	@UiField
	ListBox bedRoomsList;
	@UiField
	ListBox heatTypesList;
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
	CheckBox woodwork;

	@UiField
	CheckBox alarm;

	@UiField
	CheckBox utilityRoom;

	@UiField
	CheckBox steelDoor;

	@UiField
	CheckBox showerCabin;

	@UiField
	CheckBox wallPaper;

	@UiField
	CheckBox parentsBathroom;

	@UiField
	CheckBox thermopane;

	@UiField
	CheckBox jacuzzi;

	@UiField
	CheckBox crownMolding;

	@UiField
	CheckBox laminateFloor;

	@UiField
	CheckBox vinylFloor;

	@UiField
	CheckBox shade;

	@UiField
	CheckBox hardwoodFloor;

	@UiField
	CheckBox vcWindow;

	@UiField
	CheckBox security;

	@UiField
	CheckBox hydrophore;

	@UiField
	CheckBox insulations;

	@UiField
	CheckBox generator;

	@UiField
	CheckBox siding;

	@UiField
	CheckBox housingComplex;

	@UiField
	CheckBox sportFacilitiy;

	@UiField
	CheckBox urgent;

	@UiField
	CheckBox bargain;

	@UiField
	Anchor advSearch;
	@UiField
	Anchor advSearchTop;
	@UiField
	CheckBox adsl;
	@UiField
	CheckBox builtInOwen;
	@UiField
	CheckBox balcony;
	@UiField
	CheckBox barbeque;
	@UiField
	CheckBox homeAppliance;
	@UiField
	CheckBox painted;
	@UiField
	CheckBox dishWasher;
	@UiField
	CheckBox refrigerator;
	@UiField
	CheckBox washingMachine;
	@UiField
	CheckBox wifi;
	@UiField
	CheckBox owen;
	@UiField
	CheckBox builtInCloset;
	@UiField
	CheckBox videoIntercom;
	@UiField
	CheckBox hiltonBathroom;
	@UiField
	CheckBox cellar;
	@UiField
	CheckBox airConditioning;
	@UiField
	CheckBox furniture;
	@UiField
	CheckBox sauna;
	@UiField
	CheckBox cookstove;
	@UiField
	CheckBox fireplace;
	@UiField
	CheckBox patioTerrace;
	@UiField
	CheckBox elevator;
	@UiField
	CheckBox cableSatellite;
	@UiField
	CheckBox privateGarage;
	@UiField
	CheckBox doormen;
	@UiField
	CheckBox kindergarden;
	@UiField
	CheckBox parkingLot;
	@UiField
	CheckBox pressureWaterTank;
	@UiField
	CheckBox tennisCourt;
	@UiField
	CheckBox fireExit;
	@UiField
	CheckBox openAirSwimmingPool;
	@UiField
	CheckBox indoorSwimmingPool;
	@UiField
	CheckBox closeToAirport;
	@UiField
	CheckBox closeToSubwayStation;
	@UiField
	CheckBox nearMetrobus;
	@UiField
	CheckBox closetoMinibusRoad;
	@UiField
	CheckBox closeToBusStation;
	@UiField
	CheckBox closeToTEM;

	@UiField
	CheckBox solarEnergy;
	List<CheckBox> chkBoxs = new ArrayList<CheckBox>();
	List<ListBox> listBoxs = new ArrayList<ListBox>();
	List<TextBox> textBoxs = new ArrayList<TextBox>();

	@UiField
	HTMLPanel featuredListing;;

	private static final FetchProfile fetchProfile = PropertyOptions.DEFAULT_LIST_SEARCH_PROFILE;
	private static final SearchProfile searchProfile = new SearchProfile(fetchProfile, null, null);
	private final Widget widget;

	@Inject
	public AdvancedSearchView() {
		widget = uiBinder.createAndBindUi(this);
		// rb1.setHTML("Sale");
		// rb2.setHTML("Rent");
		this.contentPanel.getElement().setId("content");
		pTypesList.addItem(PropertyOptions.anyType);
		for (String type : PropertyOptions.P_TYPES) {
			pTypesList.addItem(type);
		}

		heatTypesList.addItem(PropertyOptions.anyHeat);
		for (String heatType : PropertyOptions.HEAT_TYPES) {
			heatTypesList.addItem(heatType);
		}

		priceTypesList.addItem(PropertyOptions.price0);
		for (String priceType : PropertyOptions.PRICE_TYPES) {
			priceTypesList.addItem(priceType);
		}

		sftTypesList.addItem(PropertyOptions.area);
		for (String sftType : PropertyOptions.SQFT_TYPES) {
			sftTypesList.addItem(sftType);
		}

		bedRoomsList.addItem(PropertyOptions.anyRoomNo);
		for (String bedRoomType : PropertyOptions.BEDROOM_TYPES) {
			bedRoomsList.addItem(bedRoomType);
		}

		this.woodwork.setFormValue("1");
		chkBoxs.add(this.woodwork);
		this.alarm.setFormValue("2");
		chkBoxs.add(this.alarm);
		this.utilityRoom.setFormValue("3");
		chkBoxs.add(this.utilityRoom);
		this.steelDoor.setFormValue("4");
		chkBoxs.add(this.steelDoor);
		this.showerCabin.setFormValue("5");
		chkBoxs.add(this.showerCabin);
		this.wallPaper.setFormValue("6");
		chkBoxs.add(this.wallPaper);
		this.parentsBathroom.setFormValue("7");
		chkBoxs.add(this.parentsBathroom);
		this.thermopane.setFormValue("8");
		chkBoxs.add(this.thermopane);
		this.jacuzzi.setFormValue("9");
		chkBoxs.add(this.jacuzzi);
		this.crownMolding.setFormValue("10");
		chkBoxs.add(this.crownMolding);
		this.laminateFloor.setFormValue("11");
		chkBoxs.add(this.laminateFloor);
		this.vinylFloor.setFormValue("12");
		chkBoxs.add(this.vinylFloor);
		this.shade.setFormValue("13");
		chkBoxs.add(this.shade);
		this.hardwoodFloor.setFormValue("14");
		chkBoxs.add(this.hardwoodFloor);
		this.vcWindow.setFormValue("15");
		chkBoxs.add(this.vcWindow);
		this.security.setFormValue("16");
		chkBoxs.add(this.security);
		this.hydrophore.setFormValue("17");
		chkBoxs.add(this.hydrophore);
		this.insulations.setFormValue("18");
		chkBoxs.add(this.insulations);
		this.generator.setFormValue("19");
		chkBoxs.add(this.generator);
		this.siding.setFormValue("20");
		chkBoxs.add(this.siding);
		this.housingComplex.setFormValue("21");
		chkBoxs.add(this.housingComplex);
		this.sportFacilitiy.setFormValue("22");
		chkBoxs.add(this.sportFacilitiy);
		this.urgent.setFormValue("23");
		chkBoxs.add(this.urgent);
		this.bargain.setFormValue("24");
		chkBoxs.add(this.bargain);
		this.adsl.setFormValue("25");
		chkBoxs.add(this.adsl);
		this.builtInOwen.setFormValue("26");
		chkBoxs.add(this.builtInOwen);
		this.balcony.setFormValue("27");
		chkBoxs.add(this.balcony);
		this.barbeque.setFormValue("28");
		chkBoxs.add(this.barbeque);
		this.homeAppliance.setFormValue("29");
		chkBoxs.add(this.homeAppliance);
		this.painted.setFormValue("30");
		chkBoxs.add(this.painted);
		this.dishWasher.setFormValue("31");
		chkBoxs.add(this.dishWasher);
		this.refrigerator.setFormValue("32");
		chkBoxs.add(this.refrigerator);
		this.washingMachine.setFormValue("33");
		chkBoxs.add(this.washingMachine);
		this.wifi.setFormValue("34");
		chkBoxs.add(this.wifi);
		this.owen.setFormValue("35");
		chkBoxs.add(this.owen);
		this.builtInCloset.setFormValue("36");
		chkBoxs.add(this.builtInCloset);
		this.videoIntercom.setFormValue("37");
		chkBoxs.add(this.videoIntercom);
		this.hiltonBathroom.setFormValue("38");
		chkBoxs.add(this.hiltonBathroom);
		this.cellar.setFormValue("39");
		chkBoxs.add(this.cellar);
		this.airConditioning.setFormValue("40");
		chkBoxs.add(this.airConditioning);
		this.furniture.setFormValue("41");
		chkBoxs.add(this.furniture);
		this.sauna.setFormValue("42");
		chkBoxs.add(this.sauna);
		this.cookstove.setFormValue("43");
		chkBoxs.add(this.cookstove);
		this.fireplace.setFormValue("44");
		chkBoxs.add(this.fireplace);
		this.patioTerrace.setFormValue("45");
		chkBoxs.add(this.patioTerrace);
		this.elevator.setFormValue("46");
		chkBoxs.add(this.elevator);
		this.cableSatellite.setFormValue("47");
		chkBoxs.add(this.cableSatellite);
		this.privateGarage.setFormValue("48");
		chkBoxs.add(this.privateGarage);
		this.doormen.setFormValue("49");
		chkBoxs.add(this.doormen);
		this.kindergarden.setFormValue("50");
		chkBoxs.add(this.kindergarden);
		this.parkingLot.setFormValue("51");
		chkBoxs.add(this.parkingLot);
		this.pressureWaterTank.setFormValue("52");
		chkBoxs.add(this.pressureWaterTank);
		this.tennisCourt.setFormValue("53");
		chkBoxs.add(this.tennisCourt);
		this.fireExit.setFormValue("54");
		chkBoxs.add(this.fireExit);
		this.openAirSwimmingPool.setFormValue("55");
		chkBoxs.add(this.openAirSwimmingPool);
		this.indoorSwimmingPool.setFormValue("56");
		chkBoxs.add(this.indoorSwimmingPool);
		this.closeToAirport.setFormValue("57");
		chkBoxs.add(this.closeToAirport);
		this.closeToSubwayStation.setFormValue("58");
		chkBoxs.add(this.closeToSubwayStation);
		this.nearMetrobus.setFormValue("59");
		chkBoxs.add(this.nearMetrobus);
		this.closetoMinibusRoad.setFormValue("60");
		chkBoxs.add(this.closetoMinibusRoad);
		this.closeToBusStation.setFormValue("61");
		chkBoxs.add(this.closeToBusStation);
		this.closeToTEM.setFormValue("62");
		chkBoxs.add(this.closeToTEM);
		this.solarEnergy.setFormValue("63");
		chkBoxs.add(this.solarEnergy);
		statePanel.getElement().setId("state");
		MultiWordSuggestOracle stateOracle = new MultiWordSuggestOracle("");

		stateOracle.addAll(AutoCmpleteTextBoxHelper2.stateCityMap.keySet());
		/*
		 * stateOracle .setDefaultSuggestionsFromText(AutoCmpleteTextBoxHelper2.stateCityMap .keySet());
		 */
		stateBox = new SuggestBox(stateOracle, state);
		stateBox.showSuggestionList();
		stateBox.setAnimationEnabled(true);
		stateBox.setAutoSelectEnabled(false);
		stateBox.setStyleName("text_field2");
		statePanel.add(stateBox, "state");
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
		cityBox.setStyleName("text_field2");
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
		suburbBox.setStyleName("text_field2");
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

	@Override
	public Widget asWidget() {
		setWindowTitle("Detaylı Arama - Gharonda.com");
		return widget;
	}

	@UiHandler({ "advSearch", "advSearchTop" })
	public void onAdvSearchClicked(ClickEvent event) {
		List<SearchCriteriaIFace> selListCriteria = getSearchSelectionsCriteriaList();
		if (selListCriteria.size() > 0) {
			searchProfile.setSelListCriteria(getSearchSelectionsCriteriaList());
			getUiHandlers().handleAdvSearch(searchProfile, getPMode());
		}
	}

	private int getPMode() {
		boolean el1Chk = DOM.getElementById("saleRB").getPropertyBoolean("checked");
		boolean el2Chk = DOM.getElementById("rentRB").getPropertyBoolean("checked");
		return (el1Chk) ? 1 : (el2Chk) ? 2 : 0;
	}

	private List<SearchCriteriaIFace> getSearchSelectionsCriteriaList() {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();

		if (getPMode() == 0) {
			Window.alert("Please select For Sale or For Rent");
			return cList;
		}

		if (priceTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPriceSearchCriteria(priceTypesList.getSelectedIndex()));
		}

		if (sftTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedSftSearchCriteria(sftTypesList.getSelectedIndex()));
		}

		if (pTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPropertyTypeSearchCriteria(pTypesList.getSelectedIndex()));
		}

		if (bedRoomsList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedBedRoomsSearchCriteria(bedRoomsList.getSelectedIndex()));
		}

		if (heatTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedHeatSearchCriteria(heatTypesList.getSelectedIndex()));
		}

		if (state.getValue() != null && !state.getValue().trim().isEmpty()) {
			cList.add(PropertyOptions.getStateSearchCriteria(state.getValue()));
		}

		if (city.getValue() != null && !city.getValue().trim().isEmpty()) {
			cList.add(PropertyOptions.getCitySearchCriteria(city.getValue()));
		}

		if (suburb.getValue() != null && !suburb.getValue().trim().isEmpty()) {
			cList.add(PropertyOptions.getSuburbSearchCriteria(suburb.getValue()));
		}

		if (!setAmenities(cList)) {
			Window.alert(PropertyOptions.noOfAmenitiesMorethan16);
			cList = new ArrayList<SearchCriteriaIFace>();
		}

		return cList;
	}

	private boolean setAmenities(List<SearchCriteriaIFace> list) {
		List<Integer> chkdAmenities = new ArrayList<Integer>();
		int numChkd = 1;
		for (CheckBox box : chkBoxs) {
			boolean checked = box.getValue();
			if (checked) {
				numChkd++;
				if (numChkd == CommonConstants.ALLOWED_AMENITIES_SIZE) {
					return false;
				}
				chkdAmenities.add(Integer.parseInt(box.getFormValue()));
			}
		}
		int amenity1 = 0, amenity2 = 0, amenity3 = 0, amenity4 = 0;
		for (Integer amenityIndx : chkdAmenities) {
			int amenitySlotNum = amenityIndx / CommonConstants.AMENITIES_BITS_SIZE;
			if (amenitySlotNum == 0) {
				amenity1 = amenity1 | PropertyOptions.getAmenityBitVal(amenityIndx);
			} else if (amenitySlotNum == 1) {
				amenity2 = amenity2 | PropertyOptions.getAmenityBitVal(amenityIndx);
			} else if (amenitySlotNum == 2) {
				amenity3 = amenity3 | PropertyOptions.getAmenityBitVal(amenityIndx);
			} else if (amenitySlotNum == 3) {
				amenity4 = amenity4 | PropertyOptions.getAmenityBitVal(amenityIndx);
			}
		}
		if (amenity1 > 0) {
			list.add(PropertyOptions.getAmenitiesSearchCriteria("amenity1", amenity1));
		}
		if (amenity2 > 0) {
			list.add(PropertyOptions.getAmenitiesSearchCriteria("amenity2", amenity2));
		}
		if (amenity3 > 0) {
			list.add(PropertyOptions.getAmenitiesSearchCriteria("amenity3", amenity3));
		}
		if (amenity4 > 0) {
			list.add(PropertyOptions.getAmenitiesSearchCriteria("amenity4", amenity4));
		}

		return true;
	}
}
