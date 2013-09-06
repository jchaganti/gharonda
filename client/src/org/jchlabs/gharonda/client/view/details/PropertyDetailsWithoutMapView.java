package org.jchlabs.gharonda.client.view.details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.details.PropertyDetailsWithoutMapPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.gen2.logging.shared.Log;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class PropertyDetailsWithoutMapView extends AbstractAppView<PropertyDetailsWithoutMapUiHandlers> implements
		MyView {
	interface PropertyDetailsWithoutMapViewUiBinder extends UiBinder<Widget, PropertyDetailsWithoutMapView> {

	}

	private static final PropertyDetailsWithoutMapViewUiBinder uiBinder = GWT
			.create(PropertyDetailsWithoutMapViewUiBinder.class);
	private static final String PROPERTY_NUM = PropertyOptions.propNum;
	private static final String ROOMS = PropertyOptions.rooms;
	private static final String BATH = PropertyOptions.baths;
	private static final String SQ_FT = " m2";

	private static final String PER_SFT_PRICE = PropertyOptions.pricePerSft;

	@UiField
	HTMLPanel contentPanel;

	@UiField
	HTML topDesc;
	@UiField
	HTML pid;

	@UiField
	HTML price;
	@UiField
	HTML rooms;
	@UiField
	HTML address1;
	@UiField
	HTML address2;
	@UiField
	HTML created;
	@UiField
	HTML modified;
	@UiField
	HTML priceperSft;
	@UiField
	HTML floor;
	@UiField
	HTML heat;
	@UiField
	HTML view;
	@UiField
	HTML buildDate;

	@UiField
	HTML homeLoan;
	@UiField
	HTML name;
	@UiField
	HTML phone;
	@UiField
	HTML cell;

	@UiField
	HTML companyName;

	@UiField
	HTML desc;

	@UiField
	HTML amenity1;

	@UiField
	HTML amenity2;
	@UiField
	HTML amenity3;
	@UiField
	HTML amenity4;
	@UiField
	HTML amenity5;
	@UiField
	HTML amenity6;
	@UiField
	HTML amenity7;
	@UiField
	HTML amenity8;
	@UiField
	HTML amenity9;
	@UiField
	HTML amenity10;
	@UiField
	HTML amenity11;
	@UiField
	HTML amenity12;
	@UiField
	HTML amenity13;
	@UiField
	HTML amenity14;
	@UiField
	HTML amenity15;
	@UiField
	HTML amenity16;
	@UiField
	Image topImage;
	@UiField
	Image image1;
	@UiField
	Image image2;
	@UiField
	Image image3;
	@UiField
	Image image4;
	@UiField
	Image image5;
	@UiField
	Image image6;
	@UiField
	Image image7;
	@UiField
	Image image8;
	@UiField
	Image image9;
	@UiField
	Image image10;
	@UiField
	HTMLPanel featuredListing;

	private static Map<String, Image> imageMap = new HashMap<String, Image>();
	private static List<HTML> amenitiesHTML = new ArrayList<HTML>();
	private final Widget widget;

	@Inject
	public PropertyDetailsWithoutMapView() {
		widget = uiBinder.createAndBindUi(this);
		this.contentPanel.getElement().setId("content");
		init();
		setFeaturedistingPanel(featuredListing);

	}

	@Override
	public Widget asWidget() {
		return widget;
	};

	@Override
	public void showProperty(PropertiesDTO property, Users user) {
		Log.fine(property.toString());
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

		desc.setHTML(property.getDescription());

		List<Integer> amenityIds = PropertyOptions.getAmenityIdsForProperty(property);

		if (amenityIds.size() <= CommonConstants.ALLOWED_AMENITIES_SIZE) {
			int indx = 0;
			for (Integer amenityNo : amenityIds) {
				HTML htmlWidget = amenitiesHTML.get(indx++);
				htmlWidget.setHTML(PropertyOptions.AMENITIES.get(amenityNo));
			}
		}

		Set<Contentitems> items = property.getContentitems();
		String topImageUrl = "images/img1_propertydetail.jpg";
		for (final Contentitems item : items) {
			final String imageNum = item.getName();
			if (imageNum != null) {

				Image image = imageMap.get(imageNum);
				image.setUrl(item.getData() + imageNum + "_tn2.jpg");
				if ("image1".equals(imageNum)) {
					topImageUrl = item.getData() + imageNum + "_tn1.jpg";
				}
				image.addMouseOverHandler(new MouseOverHandler() {

					@Override
					public void onMouseOver(MouseOverEvent event) {
						topImage.setUrl(item.getData() + imageNum + "_tn1.jpg");

					}
				});
			}
		}
		topImage.setUrl(topImageUrl);
	}

	@UiHandler("addToFav")
	void onSignMeInClicked(ClickEvent event) {
		getUiHandlers().handleAddToFavorite();
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

	private void init() {
		imageMap.put("image1", image1);
		imageMap.put("image2", image2);
		imageMap.put("image3", image3);
		imageMap.put("image4", image4);
		imageMap.put("image5", image5);
		imageMap.put("image6", image6);
		imageMap.put("image7", image7);
		imageMap.put("image8", image8);
		imageMap.put("image9", image9);
		imageMap.put("image10", image10);
		amenitiesHTML.add(amenity1);
		amenitiesHTML.add(amenity2);
		amenitiesHTML.add(amenity3);
		amenitiesHTML.add(amenity4);
		amenitiesHTML.add(amenity5);
		amenitiesHTML.add(amenity6);
		amenitiesHTML.add(amenity7);
		amenitiesHTML.add(amenity8);
		amenitiesHTML.add(amenity9);
		amenitiesHTML.add(amenity10);
		amenitiesHTML.add(amenity11);
		amenitiesHTML.add(amenity12);
		amenitiesHTML.add(amenity13);
		amenitiesHTML.add(amenity14);
		amenitiesHTML.add(amenity15);
		amenitiesHTML.add(amenity16);

	}
}
