package org.jchlabs.gharonda.client.view.account;

import java.util.List;
import java.util.Map;

import org.jchlabs.gharonda.client.presenter.account.MyNotifierProfilePresenter.MyView;
import org.jchlabs.gharonda.client.util.AutoCmpleteTextBoxHelper2;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.Notifierprofiles;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.inject.Inject;

public class MyNotifierProfileView extends AbstractAppView<MyNotifierProfileUiHandlers> implements MyView {

	interface MyNotifierProfileViewUiBinder extends UiBinder<Widget, MyNotifierProfileView> {

	}

	private static final MyNotifierProfileViewUiBinder uiBinder = GWT.create(MyNotifierProfileViewUiBinder.class);;

	@UiField
	HTMLPanel contentPanel;

	@UiField
	Anchor createProfile;
	@UiField
	TextBox state;
	@UiField
	TextBox city;
	@UiField
	TextBox suburb;
	@UiField
	ListBox pTypesList;
	@UiField
	ListBox freqTypesList;
	@UiField
	ListBox unSelAmenitiesList;
	@UiField
	ListBox selAmenitiesList;
	@UiField
	TextBox sftLVal;
	@UiField
	ListBox sftComparator;
	@UiField
	TextBox sftRVal;
	@UiField
	TextBox priceLVal;
	@UiField
	ListBox priceComparator;
	@UiField
	TextBox priceRVal;
	@UiField
	TextBox roomNoLVal;
	@UiField
	ListBox roomNoComparator;
	@UiField
	TextBox roomNoRVal;
	@UiField
	HTMLPanel featuredListing;
	@UiField
	HTMLPanel statePanel;
	@UiField
	HTMLPanel suburbPanel;
	@UiField
	HTMLPanel cityPanel;
	SuggestBox suburbBox;
	SuggestBox cityBox;
	SuggestBox stateBox;
	private static final String ONE = "1";
	public final Widget widget;
	private Users user;

	@Inject
	public MyNotifierProfileView() {
		widget = uiBinder.createAndBindUi(this);
		pTypesList.addItem(PropertyOptions.selType);
		for (String type : PropertyOptions.P_TYPES) {
			pTypesList.addItem(type);
		}
		for (String type : PropertyOptions.EMAIL_FREQ_TYPES) {
			freqTypesList.addItem(type);
		}
		for (String type : PropertyOptions.COMPARATOR_TYPES) {
			sftComparator.addItem(type);
			priceComparator.addItem(type);
			roomNoComparator.addItem(type);
		}
		contentPanel.getElement().setId("content");
		setFeaturedistingPanel(featuredListing);
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
		stateBox.setStyleName("email_field");
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
		cityBox.setStyleName("email_field");
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
		suburbBox.setStyleName("email_field");
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

		freqTypesList.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (freqTypesList.getSelectedIndex() == 0) {
					reset();
				}

			}

		});
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void showUserNotifierProfile(Users user) {
		setWindowTitle("Arayışlarım - Gharonda.com");
		if (user != null && user.getNotifierprofiles() != null) {
			this.user = user;
			Notifierprofiles profile = user.getNotifierprofiles();
			if (profile.getFrequency() != null && profile.getFrequency().intValue() > 0) {
				freqTypesList.setSelectedIndex(profile.getFrequency());
			} else {
				freqTypesList.setSelectedIndex(0);
				reset();
				return;
			}
			suburb.setValue(profile.getSuburb());

			city.setValue(profile.getCity());

			state.setValue(profile.getState());

			if (profile.getPType() != null && profile.getPType().intValue() >= 0) {
				pTypesList.setSelectedIndex(profile.getPType());
			} else {
				pTypesList.setSelectedIndex(0);
			}

			if (profile.getSftLVal() != null) {
				sftLVal.setValue(profile.getSftLVal().toString());
			}
			if (profile.getSftComparator() != null && profile.getSftComparator().intValue() >= 0) {
				sftComparator.setSelectedIndex(profile.getSftComparator());
			}

			if (profile.getSftRVal() != null) {
				sftRVal.setValue(profile.getSftRVal().toString());
			}
			if (profile.getPriceLVal() != null) {
				priceLVal.setValue(profile.getPriceLVal().toString());
			}
			if (profile.getPriceComparator() != null && profile.getPriceComparator().intValue() >= 0) {
				priceComparator.setSelectedIndex(profile.getPriceComparator());
			}
			if (profile.getPriceRVal() != null) {
				priceRVal.setValue(profile.getPriceRVal().toString());
			}
			if (profile.getRoomNoLVal() != null) {
				roomNoLVal.setValue(profile.getRoomNoLVal().toString());
			}
			if (profile.getRoomNoComparator() != null && profile.getPriceComparator().intValue() >= 0) {
				roomNoComparator.setSelectedIndex(profile.getRoomNoComparator());
			}
			if (profile.getRoomNoRVal() != null) {
				roomNoRVal.setValue(profile.getRoomNoRVal().toString());
			}

			int amenityIndx = 0;
			selAmenitiesList.clear();
			unSelAmenitiesList.clear();
			for (String amenity : PropertyOptions.AMENITIES) {
				if (amenityIndx > 0) {
					int amenityBitVal = PropertyOptions.getAmenityBitVal(amenityIndx);
					int amenitySlotNum = (amenityIndx - 1) / CommonConstants.AMENITIES_BITS_SIZE;
					Integer curAmenityVal = null;
					if (amenitySlotNum == 0) {
						curAmenityVal = profile.getAmenity1();
					} else if (amenitySlotNum == 1) {
						curAmenityVal = profile.getAmenity2();
					} else if (amenitySlotNum == 2) {
						curAmenityVal = profile.getAmenity3();
					} else if (amenitySlotNum == 3) {
						curAmenityVal = profile.getAmenity4();
					}
					ListBox curList = (curAmenityVal != null && (curAmenityVal.intValue() & amenityBitVal) > 0) ? selAmenitiesList
							: unSelAmenitiesList;
					curList.addItem(amenity, new Integer(amenityIndx).toString());

				}

				amenityIndx++;
			}
		}
	}

	@UiHandler({ "myProfile", "myFavorites", "myListings" })
	void onMyAccountClicked(ClickEvent event) {
		getUiHandlers().handleMyAccountClicked();
	}

	@UiHandler("createProfile")
	void onSignInClicked(ClickEvent event) {
		if (updateUserNotifierProfile()) {
			getUiHandlers().handleSaveUserNotifierProfile(user);
		}
	}

	@UiHandler("toLeft")
	void onToLeftClicked(ClickEvent event) {
		moveToLeft();
	}

	@UiHandler("toRight")
	void onToRightClicked(ClickEvent event) {
		moveToRight();
	}

	private int computeIndex(ListBox amentiesList, int amenityIndex) {
		int count = amentiesList.getItemCount();
		int i = 0;
		for (i = 0; i < count; i++) {
			String amenityIndxStr = amentiesList.getValue(i);
			int curAmenityIndex = Integer.parseInt(amenityIndxStr);
			if (amenityIndex < curAmenityIndex) {
				break;
			}
		}
		return i;
	}

	private void moveToLeft() {
		int count = selAmenitiesList.getItemCount();
		for (int i = 0; i < count; i++) {
			if (selAmenitiesList.isItemSelected(i)) {
				String amenityIndxStr = selAmenitiesList.getValue(i);
				int amenityIndx = Integer.parseInt(amenityIndxStr);
				int insertIndex = computeIndex(unSelAmenitiesList, amenityIndx);
				unSelAmenitiesList.insertItem(selAmenitiesList.getItemText(i), amenityIndxStr, insertIndex);
			}
		}

		for (int j = count - 1; j >= 0; j--) {
			if (selAmenitiesList.isItemSelected(j)) {
				selAmenitiesList.removeItem(j);
			}
		}
	}

	private void moveToRight() {
		int count = unSelAmenitiesList.getItemCount();
		for (int i = 0; i < count; i++) {
			if (unSelAmenitiesList.isItemSelected(i)) {
				String amenityIndxStr = unSelAmenitiesList.getValue(i);
				int amenityIndx = Integer.parseInt(amenityIndxStr);
				int insertIndex = computeIndex(selAmenitiesList, amenityIndx);
				selAmenitiesList.insertItem(unSelAmenitiesList.getItemText(i), amenityIndxStr, insertIndex);
			}
		}
		for (int j = count - 1; j >= 0; j--) {
			if (unSelAmenitiesList.isItemSelected(j)) {
				unSelAmenitiesList.removeItem(j);
			}
		}
	}

	private void setAmenities(Notifierprofiles np) {
		int amenity1 = 0, amenity2 = 0, amenity3 = 0, amenity4 = 0;
		int count = selAmenitiesList.getItemCount();
		for (int i = 0; i < count; i++) {
			String amenityIndxStr = selAmenitiesList.getValue(i);
			int amenityIndx = Integer.parseInt(amenityIndxStr);
			int amenitySlotNum = (amenityIndx - 1) / CommonConstants.AMENITIES_BITS_SIZE;
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
		np.setAmenity1(amenity1);
		np.setAmenity2(amenity2);
		np.setAmenity3(amenity3);
		np.setAmenity4(amenity4);
	}

	private boolean updateUserNotifierProfile() {
		boolean result = false;
		if (user != null) {
			Notifierprofiles np = user.getNotifierprofiles();
			if (np == null) {
				np = new Notifierprofiles();
			}
			np.setFrequency(freqTypesList.getSelectedIndex());
			if (freqTypesList.getSelectedIndex() == 0) {
				// Window.alert(PropertyOptions.selCorrectFreq);
				// returning since user wants to stop Notification.
				return true;
			}
			np.setState(state.getValue());
			np.setCity(city.getValue());
			np.setSuburb(suburb.getValue());

			np.setPType(pTypesList.getSelectedIndex());
			if (selAmenitiesList.getItemCount() > 16) {
				Window.alert(PropertyOptions.selMax16Amenities);
				return false;
			}
			setAmenities(np);

			np.setPriceLVal(null);
			np.setPriceComparator(null);
			np.setPriceRVal(null);
			if (priceLVal.getValue() != null) {
				if (PropertyOptions.isValidNumber(priceLVal.getValue())) {
					np.setPriceLVal(Integer.parseInt(priceLVal.getValue()));
					if (priceComparator.getSelectedIndex() == 0) {
						Window.alert(PropertyOptions.properPriceComparator);
						return false;
					}
					np.setPriceComparator(priceComparator.getSelectedIndex());
					if (priceComparator.getSelectedIndex() == 2) {
						if (priceRVal.getValue() != null) {
							if (PropertyOptions.isValidNumber(priceRVal.getValue())) {
								np.setPriceRVal(Integer.parseInt(priceRVal.getValue()));
							} else {
								Window.alert(PropertyOptions.properPriceOnRight);
								return false;
							}
						}
					}
				} else {
					Window.alert(PropertyOptions.properPrice);
					return false;
				}

			}

			np.setSftLVal(null);
			np.setSftComparator(null);
			np.setSftRVal(null);
			if (sftLVal.getValue() != null) {
				if (PropertyOptions.isValidNumber(sftLVal.getValue())) {
					np.setSftLVal(Integer.parseInt(sftLVal.getValue()));
					if (sftComparator.getSelectedIndex() == 0) {
						Window.alert(PropertyOptions.properSftComparator);
						return false;
					}
					np.setSftComparator(sftComparator.getSelectedIndex());
					if (sftComparator.getSelectedIndex() == 2) {
						if (sftRVal.getValue() != null) {
							if (PropertyOptions.isValidNumber(sftRVal.getValue())) {
								np.setSftRVal(Integer.parseInt(sftRVal.getValue()));
							} else {
								Window.alert(PropertyOptions.properSftOnRight);
								return false;
							}
						}
					}
				} else {
					Window.alert(PropertyOptions.properSft);
				}

			}

			np.setRoomNoLVal(null);
			np.setRoomNoComparator(null);
			np.setRoomNoRVal(null);
			if (roomNoLVal.getValue() != null) {
				if (PropertyOptions.isValidNumber(roomNoLVal.getValue())) {
					np.setRoomNoLVal(Integer.parseInt(roomNoLVal.getValue()));
					if (sftComparator.getSelectedIndex() == 0) {
						Window.alert(PropertyOptions.properRoomNoComparator);
						return false;
					}
					np.setRoomNoComparator(roomNoComparator.getSelectedIndex());
					if (roomNoComparator.getSelectedIndex() == 2) {
						if (roomNoRVal.getValue() != null) {
							if (PropertyOptions.isValidNumber(roomNoRVal.getValue())) {
								np.setRoomNoRVal(Integer.parseInt(roomNoRVal.getValue()));
							} else {
								Window.alert(PropertyOptions.properRoomNoOnRight);
								return false;
							}
						}
					}
				} else {
					Window.alert(PropertyOptions.properRoomNo);
				}
			}
			user.setNotifierprofiles(np);
			result = true;
		}
		return result;
	}

	private void reset() {
		state.setValue("");
		city.setValue("");
		suburb.setValue("");
		pTypesList.setSelectedIndex(0);
		int amenityIndx = 0;
		selAmenitiesList.clear();
		unSelAmenitiesList.clear();
		for (String amenity : PropertyOptions.AMENITIES) {
			if (amenityIndx > 0) {
				unSelAmenitiesList.addItem(amenity, new Integer(amenityIndx).toString());
			}
			amenityIndx++;
		}
		sftLVal.setValue("");
		sftComparator.setSelectedIndex(0);
		sftRVal.setValue("");
		priceLVal.setValue("");
		priceComparator.setSelectedIndex(0);
		priceRVal.setValue("");
		roomNoLVal.setValue("");
		roomNoComparator.setSelectedIndex(0);
		roomNoRVal.setValue("");
	}

}
