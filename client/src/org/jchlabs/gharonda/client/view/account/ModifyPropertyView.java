package org.jchlabs.gharonda.client.view.account;

import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.IUploadStatus.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jchlabs.gharonda.client.presenter.account.ModifyPropertyPresenter.MyView;
import org.jchlabs.gharonda.client.util.AutoCmpleteTextBoxHelper2;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.client.view.results.ResultsHelper;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.DeleteImage;
import org.jchlabs.gharonda.shared.rpc.DeleteImageResult;
import org.jchlabs.gharonda.shared.rpc.GetImageData;
import org.jchlabs.gharonda.shared.rpc.GetImageDataResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.maps.client.MapPane;
import com.google.gwt.maps.client.MapPaneType;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapMouseMoveHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;

public class ModifyPropertyView extends AbstractAppView<ModifyPropertyUiHandlers> implements MyView {

	interface ModifyPropertyViewUiBinder extends UiBinder<Widget, ModifyPropertyView> {

	}

	private class GetImageDataTimer extends Timer {
		private String imageNum;
		private Integer TRIALS = 0;

		public GetImageDataTimer(String imageNum) {
			this.imageNum = imageNum;
		}

		@Override
		public void run() {
			TRIALS++;
			dispatcher.execute(new GetImageData(imageNum, pDTO.getId()), new AsyncCallback<GetImageDataResult>() {
				public void onFailure(Throwable caught) {
					GetImageDataTimer.this.cancel();
				}

				public void onSuccess(GetImageDataResult result) {
					if (result != null && result.getImageData() != null) {
						setImage(imageNum, result.getImageData());

					} else {
						if (TRIALS > 20) {
							GetImageDataTimer.this.cancel();
						}
					}

				}
			});
		}

	};

	private static final ModifyPropertyViewUiBinder uiBinder = GWT.create(ModifyPropertyViewUiBinder.class);
	private static final String IMAGE_PANEL_SUFFIX = "_p";

	@UiField
	HTMLPanel contentPanel;
	@UiField
	Anchor saveTop;
	@UiField
	Anchor cancelTop;
	@UiField
	Anchor saveBottom;
	@UiField
	Anchor cancelBottom;
	@UiField
	Anchor image1Delete;
	@UiField
	Anchor image2Delete;
	@UiField
	Anchor image3Delete;
	@UiField
	Anchor image4Delete;
	@UiField
	Anchor image5Delete;
	@UiField
	Anchor image6Delete;
	@UiField
	Anchor image7Delete;
	@UiField
	Anchor image8Delete;
	@UiField
	Anchor image9Delete;

	@UiField
	Anchor image10Delete;
	@UiField
	HTMLPanel image1;
	@UiField
	HTMLPanel image2;
	@UiField
	HTMLPanel image3;
	@UiField
	HTMLPanel image4;
	@UiField
	HTMLPanel image5;
	@UiField
	HTMLPanel image6;
	@UiField
	HTMLPanel image7;
	@UiField
	HTMLPanel image8;
	@UiField
	HTMLPanel image9;

	@UiField
	HTMLPanel image10;
	@UiField
	TextBox addrNum;
	@UiField
	TextBox streetName;
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
	TextBox postCode;
	@UiField
	TextBox title;
	@UiField
	TextArea description;
	@UiField
	HTMLPanel mapPanel;
	@UiField
	HTMLPanel uploadPanel;
	@UiField
	ListBox pTypesList;
	@UiField
	ListBox heatTypesList;
	@UiField
	ListBox viewTypesList;
	@UiField
	TextBox price;
	@UiField
	ListBox currencyTypesList;
	@UiField
	TextBox sqrft;
	@UiField
	TextBox buildDate;
	@UiField
	ListBox bedRoomsList;
	@UiField
	TextBox bathRooms;
	@UiField
	ListBox homeLoanList;

	@UiField
	ListBox floorList;
	@UiField
	RadioButton rb1;
	@UiField
	RadioButton rb2;

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
	Button sendButton;
	private PropertiesDTO pDTO;
	private SingleUploader fileUploadField;
	private MapWidget map;
	private Marker marker;
	private MapPane mapPane;
	private Widget w = new HTML("<div class='tooltip'>İşaretlemek istediğiniz konuma tıklayınız</div>");

	private static Map<String, HTMLPanel> htmlMap = new HashMap<String, HTMLPanel>();

	private static final Map<TextBox, String> fieldToMsgMap = new HashMap<TextBox, String>();

	private static final String[] extns = { "gif", "jpg", "jpeg", "png" };
	List<CheckBox> chkBoxs = new ArrayList<CheckBox>();
	public final Widget widget;
	private final DispatchAsync dispatcher;
	private boolean isImageUploaded = false;
	private static int GET_IMAGE_DATE_INTERVAL = 500;

	private GetImageDataTimer timer;

	private Map<TextBox, String> textBoxesTobeValidated = new HashMap<TextBox, String>();
	private Map<ListBox, String> listBoxesTobeValidated = new HashMap<ListBox, String>();
	private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		public void onFinish(IUploader uploader) {

			if (uploader.getStatus() == Status.SUCCESS && isImageUploaded) {
				String imageNum = getCurrentValidImageNum();
				timer = new GetImageDataTimer(imageNum);
				timer.scheduleRepeating(GET_IMAGE_DATE_INTERVAL);
			}
		}

	};

	private IUploader.OnStartUploaderHandler onStartUploaderHandler = new IUploader.OnStartUploaderHandler() {
		public void onStart(IUploader uploader) {
			String imageNum = getCurrentValidImageNum();
			if (imageNum != null) {
				uploader.setServletPath(GWT.getModuleBaseURL() + "servlet.gupld?pid=" + pDTO.getId() + "&imageNum="
						+ imageNum);
				isImageUploaded = true;
			} else {
				Window.alert(PropertyOptions.tenImagesUploaded);
				uploader.cancel();
			}

		}
	};

	@Inject
	public ModifyPropertyView(final DispatchAsync dispatcher) {
		widget = uiBinder.createAndBindUi(this);
		this.dispatcher = dispatcher;
		this.contentPanel.getElement().setId("content");
		rb1.setText(PropertyOptions.sale);
		rb2.setText(PropertyOptions.rent);
		fileUploadField = new SingleUploader();
		fileUploadField.setValidExtensions(extns);
		fileUploadField.setAutoSubmit(false);
		this.uploadPanel.getElement().setId("uploadPanel");
		this.uploadPanel.add(fileUploadField, "uploadPanel");

		pTypesList.addItem(PropertyOptions.selType);
		for (String type : PropertyOptions.P_TYPES) {
			pTypesList.addItem(type);
		}

		heatTypesList.addItem(PropertyOptions.selHeat);
		for (String heatType : PropertyOptions.HEAT_TYPES) {
			heatTypesList.addItem(heatType);
		}

		viewTypesList.addItem(PropertyOptions.selView);
		for (String view : PropertyOptions.VIEW_TYPES) {
			viewTypesList.addItem(view);
		}

		for (String currencyType : PropertyOptions.CURRENCY_TYPES) {
			currencyTypesList.addItem(currencyType);
		}

		bedRoomsList.addItem(PropertyOptions.selRoomNo);
		for (String bedRoomType : PropertyOptions.BEDROOM_TYPES) {
			bedRoomsList.addItem(bedRoomType);
		}

		homeLoanList.addItem(PropertyOptions.selHomeLoan);
		for (String homeLoanOption : PropertyOptions.YES_NO_OPTIONS) {
			homeLoanList.addItem(homeLoanOption);
		}

		floorList.addItem(PropertyOptions.selFloor);
		for (String floorType : PropertyOptions.FLOORS_TYPES) {
			floorList.addItem(floorType);
		}

		mapPanel.getElement().setId("mapPanel");
		LatLng defaultLatLng = LatLng.newInstance(41.079578, 29.011889);
		// Open a map centered on Istanbul, Turkey

		map = new MapWidget(defaultLatLng, 2);
		map.setSize("939px", "283px");

		// Add some controls
		// map.addControl(new OverviewMapControl());
		map.addControl(new LargeMapControl());
		map.addControl(new MapTypeControl());
		map.addControl(new ScaleControl());
		map.setDoubleClickZoom(true);
		map.setContinuousZoom(true);
		map.setZoomLevel(14);
		map.setInfoWindowEnabled(true);
		map.setCurrentMapType(MapType.getHybridMap());
		setMarker(defaultLatLng);
		map.addMapClickHandler(new MapClickHandler() {

			@Override
			public void onClick(MapClickEvent event) {
				map.clearOverlays();
				marker.setLatLng(event.getLatLng());
				map.addOverlay(marker);

			}

		});
		mapPane = map.getPane(MapPaneType.FLOAT_PANE);
		mapPane.add(w);
		mapPane.setVisible(false);
		mapPanel.add(map, "mapPanel");

		map.addMapMouseMoveHandler(new MapMouseMoveHandler() {

			@Override
			public void onMouseMove(MapMouseMoveEvent event) {
				Point p = map.convertLatLngToContainerPixel(event.getLatLng());
				mapPane.setWidgetPosition(w, p.getX() + 10, p.getY() + 10);
				mapPane.setVisible(true);
			}

		});
		initHtmlMaps();
		initFieldToMsgMap();
		initAmenitiesList();
		statePanel.getElement().setId("state");
		MultiWordSuggestOracle stateOracle = new MultiWordSuggestOracle(" ");

		stateOracle.addAll(AutoCmpleteTextBoxHelper2.stateCityMap.keySet());
		stateOracle.setDefaultSuggestionsFromText(AutoCmpleteTextBoxHelper2.stateCityMap.keySet());
		stateBox = new SuggestBox(stateOracle, state);
		stateBox.showSuggestionList();
		stateBox.setAnimationEnabled(true);
		stateBox.setAutoSelectEnabled(false);
		stateBox.setStyleName("text_field1");
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
		cityBox.setStyleName("text_field9");
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
		suburbBox.setStyleName("text_field9");
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
				handleCityValueChange();

			}

		});
		fileUploadField.addOnFinishUploadHandler(onFinishUploaderHandler);
		fileUploadField.addOnStartUploadHandler(onStartUploaderHandler);
		initInputValuesToBeValidated();
	}

	@Override
	public Widget asWidget() {

		return widget;
	}

	@UiHandler({ "cancelTop", "cancelBottom" })
	public void cancelClicked(ClickEvent event) {
		getUiHandlers().handleCancelProperty();
	}

	@UiHandler({ "saveTop", "saveBottom" })
	public void saveClicked(ClickEvent event) {
		if (updatePropertyDTO()) {
			getUiHandlers().handleSaveProperty(pDTO);
		}
	}

	@Override
	public void showProperty(PropertiesDTO pDTO) {
		setWindowTitle("Yeni İlan Girişi - Gharonda.com");
		this.pDTO = pDTO;

		LatLng point = null;
		if (pDTO.getLat() == null || pDTO.getLng() == null) {
			point = LatLng.newInstance(40.98249, 29.00528);
		} else {
			point = LatLng.newInstance(pDTO.getLat(), pDTO.getLng());
		}

		marker.setLatLng(point);
		map.setCenter(point);
		Integer mode = pDTO.getPMode();
		if (mode == null) {
			mode = 1;
		}
		if (mode.intValue() == 1) {
			rb1.setValue(true);
		} else {
			rb2.setValue(true);
		}

		if (pDTO.getPType() != null && pDTO.getPType().intValue() >= 0) {
			pTypesList.setSelectedIndex(pDTO.getPType());
		} else {
			pTypesList.setSelectedIndex(0);
		}

		addrNum.setValue(pDTO.getAddrNum());
		streetName.setValue(pDTO.getStreetName());
		suburb.setValue(pDTO.getSuburb());
		city.setValue(pDTO.getCity());
		postCode.setValue(pDTO.getPostCode());
		state.setValue(pDTO.getState());
		title.setValue(pDTO.getTitle());

		if (pDTO.getBedRooms() != null && pDTO.getBedRooms().intValue() >= 0) {
			bedRoomsList.setSelectedIndex(pDTO.getBedRooms());
		} else {
			bedRoomsList.setSelectedIndex(0);
		}

		if (pDTO.getBathRooms() != null) {
			bathRooms.setValue(pDTO.getBathRooms().toString());
		} else {
			bathRooms.setValue("");
		}

		buildDate.setValue(pDTO.getBuildDate());
		if (pDTO.getView() != null && pDTO.getView().intValue() >= 0) {
			viewTypesList.setSelectedIndex(pDTO.getView());
		} else {
			viewTypesList.setSelectedIndex(0);
		}
		if (pDTO.getHeat() != null && pDTO.getHeat().intValue() >= 0) {
			heatTypesList.setSelectedIndex(pDTO.getHeat());
		} else {
			heatTypesList.setSelectedIndex(0);
		}

		if (pDTO.getHomeLoan() != null && pDTO.getHomeLoan().intValue() >= 0) {
			homeLoanList.setSelectedIndex(pDTO.getHomeLoan());
		} else {
			homeLoanList.setSelectedIndex(0);
		}

		if (pDTO.getFloor() != null && pDTO.getFloor().intValue() >= 0) {
			floorList.setSelectedIndex(pDTO.getFloor());
		} else {
			floorList.setSelectedIndex(0);
		}

		description.setValue(pDTO.getDescription());

		if (pDTO.getPrice() != null) {
			price.setValue(pDTO.getPrice().toString());
		} else {
			price.setValue("");
		}

		if (pDTO.getCurrency() != null && pDTO.getCurrency().intValue() >= 0) {
			currencyTypesList.setSelectedIndex(pDTO.getCurrency());
		} else {
			currencyTypesList.setSelectedIndex(0);
		}

		if (pDTO.getSqrft() != null) {
			sqrft.setValue(pDTO.getSqrft().toString());
		} else {
			sqrft.setValue("");
		}

		for (CheckBox box : chkBoxs) {
			int amenityIndx = Integer.parseInt(box.getFormValue());
			int amenityBitVal = PropertyOptions.getAmenityBitVal(amenityIndx);
			int amenitySlotNum = (amenityIndx - 1) / CommonConstants.AMENITIES_BITS_SIZE;
			int curAmenityVal = 0;
			if (amenitySlotNum == 0) {
				curAmenityVal = pDTO.getAmenity1();
			} else if (amenitySlotNum == 1) {
				curAmenityVal = pDTO.getAmenity2();
			} else if (amenitySlotNum == 2) {
				curAmenityVal = pDTO.getAmenity3();
			} else if (amenitySlotNum == 3) {
				curAmenityVal = pDTO.getAmenity4();
			}

			if ((curAmenityVal & amenityBitVal) > 0) {
				box.setValue(true);
			} else {
				box.setValue(false);
			}
		}
		Set<Contentitems> items = pDTO.getContentitems();
		List<String> imagesWithContent = new ArrayList<String>();

		for (Map.Entry<String, HTMLPanel> entry : htmlMap.entrySet()) {
			String imageNum = entry.getKey();
			HTMLPanel panel = htmlMap.get(imageNum);
			panel.clear();
		}
		for (Contentitems item : items) {
			String imageNum = item.getName();
			if (imageNum != null) {
				imagesWithContent.add(imageNum);
				HTMLPanel panel = htmlMap.get(imageNum);
				panel.add(createHTML(imageNum, item.getData() + imageNum + "_tn2.jpg"), imageNum + IMAGE_PANEL_SUFFIX);
			}
		}

		for (Map.Entry<String, HTMLPanel> entry : htmlMap.entrySet()) {
			String imageNum = entry.getKey();
			if (!imagesWithContent.contains(imageNum)) {
				HTMLPanel panel = htmlMap.get(imageNum);
				panel.add(createHTML(imageNum), imageNum + IMAGE_PANEL_SUFFIX);
			}
		}

	}

	protected void setImage(String imageNum, String imageData) {
		com.google.gwt.user.client.Element image1 = DOM.getElementById(imageNum);
		image1.setAttribute("src", imageData + "_tn2.jpg");
		isImageUploaded = false;
		timer.cancel();

	}

	@UiHandler("image10Delete")
	void onImageDelete10Clicked(ClickEvent event) {
		handleImageDelete("image10");

	}

	@UiHandler("image1Delete")
	void onImageDelete1Clicked(ClickEvent event) {
		handleImageDelete("image1");

	}

	@UiHandler("image2Delete")
	void onImageDelete2Clicked(ClickEvent event) {
		handleImageDelete("image2");

	}

	@UiHandler("image3Delete")
	void onImageDelete3Clicked(ClickEvent event) {
		handleImageDelete("image3");

	}

	@UiHandler("image4Delete")
	void onImageDelete4Clicked(ClickEvent event) {
		handleImageDelete("image4");

	}

	@UiHandler("image5Delete")
	void onImageDelete5Clicked(ClickEvent event) {
		handleImageDelete("image5");

	}

	@UiHandler("image6Delete")
	void onImageDelete6Clicked(ClickEvent event) {
		handleImageDelete("image6");

	}

	@UiHandler("image7Delete")
	void onImageDelete7Clicked(ClickEvent event) {
		handleImageDelete("image7");

	}

	@UiHandler("image8Delete")
	void onImageDelete8Clicked(ClickEvent event) {
		handleImageDelete("image8");

	}

	@UiHandler("image9Delete")
	void onImageDelete9Clicked(ClickEvent event) {
		handleImageDelete("image9");

	}

	private HTML createHTML(String id) {
		return createHTML(id, "images/img1_sell_condo.gif");
	}

	private HTML createHTML(String id, String imgSrc) {
		return new HTML("<img id=\"" + id + "\" width=\"80\" height=\"65\" border=\"0\" alt=\"\" src=\"" + imgSrc
				+ "\"/>");
	}

	private String getCurrentValidImageNum() {
		String result = null;
		for (int i = 1; i <= 10; i++) {
			String curImageNum = "image" + i;
			com.google.gwt.user.client.Element image1 = DOM.getElementById(curImageNum);
			if (image1.getAttribute("src").indexOf("img1_sell_condo.gif") > 0) {
				result = curImageNum;
				break;
			}
		}
		return result;
	}

	private AsyncCallback<DeleteImageResult> getImageDeleteCallBack(final String imageNum) {
		return new AsyncCallback<DeleteImageResult>() {
			public void onFailure(Throwable caught) {
				Window.alert(PropertyOptions.imageCouldNotBeDeleted);
			}

			public void onSuccess(DeleteImageResult result) {
				if (result.getStatus().name().equals(Status.SUCCESS.name())) {
					com.google.gwt.user.client.Element image1 = DOM.getElementById(imageNum);
					image1.setAttribute("src", "images/img1_sell_condo.gif");
				} else {
					Window.alert(PropertyOptions.imageCouldNotBeDeleted);
				}
			}
		};
	}

	private int getPMode() {
		boolean el1Chk = rb1.getValue();
		boolean el2Chk = rb2.getValue();
		return (el1Chk) ? 1 : (el2Chk) ? 2 : 0;
	}

	private void handleImageDelete(String imageNum) {
		dispatcher.execute(new DeleteImage(imageNum, pDTO.getId()), getImageDeleteCallBack(imageNum));

	}

	private void initAmenitiesList() {
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
	}

	private void initFieldToMsgMap() {
		fieldToMsgMap.put(price, PropertyOptions.onlyNumbersInPrice);
		fieldToMsgMap.put(sqrft, PropertyOptions.onlyNumbersInSqFt);
		fieldToMsgMap.put(bathRooms, PropertyOptions.onlyNumbersInBaths);

	}

	private void initHtmlMaps() {
		image1.getElement().setId("image1" + IMAGE_PANEL_SUFFIX);
		image2.getElement().setId("image2" + IMAGE_PANEL_SUFFIX);
		image3.getElement().setId("image3" + IMAGE_PANEL_SUFFIX);
		image4.getElement().setId("image4" + IMAGE_PANEL_SUFFIX);
		image5.getElement().setId("image5" + IMAGE_PANEL_SUFFIX);
		image6.getElement().setId("image6" + IMAGE_PANEL_SUFFIX);
		image7.getElement().setId("image7" + IMAGE_PANEL_SUFFIX);
		image8.getElement().setId("image8" + IMAGE_PANEL_SUFFIX);
		image9.getElement().setId("image9" + IMAGE_PANEL_SUFFIX);
		image10.getElement().setId("image10" + IMAGE_PANEL_SUFFIX);
		htmlMap.put("image1", image1);
		htmlMap.put("image2", image2);
		htmlMap.put("image3", image3);
		htmlMap.put("image4", image4);
		htmlMap.put("image5", image5);
		htmlMap.put("image6", image6);
		htmlMap.put("image7", image7);
		htmlMap.put("image8", image8);
		htmlMap.put("image9", image9);
		htmlMap.put("image10", image10);

	}

	private void initInputValuesToBeValidated() {
		textBoxesTobeValidated.put(streetName, PropertyOptions.streetName);
		textBoxesTobeValidated.put(addrNum, PropertyOptions.streetNo);
		textBoxesTobeValidated.put(postCode, PropertyOptions.POST_CODE_STR);
		textBoxesTobeValidated.put(state, PropertyOptions.STATE_STR1);
		textBoxesTobeValidated.put(city, PropertyOptions.CITY_STR1);
		textBoxesTobeValidated.put(suburb, PropertyOptions.SUBURB_STR1);
		textBoxesTobeValidated.put(price, PropertyOptions.price);
		textBoxesTobeValidated.put(sqrft, PropertyOptions.area);
		textBoxesTobeValidated.put(buildDate, PropertyOptions.YEAR_BUILT);
		textBoxesTobeValidated.put(bathRooms, PropertyOptions.baths);
		listBoxesTobeValidated.put(pTypesList, PropertyOptions.PROP_TYPE);
		listBoxesTobeValidated.put(bedRoomsList, PropertyOptions.ROOM_NO);
		listBoxesTobeValidated.put(heatTypesList, PropertyOptions.HEAT_STR);
		listBoxesTobeValidated.put(viewTypesList, PropertyOptions.VIEW);
		listBoxesTobeValidated.put(homeLoanList, PropertyOptions.HOME_LOAN);
		listBoxesTobeValidated.put(floorList, PropertyOptions.FLOOR);
		// listBoxesTobeValidated.put(currencyTypesList, PropertyOptions.currency);
	}

	private boolean setAmenities(PropertiesDTO p) {
		List<Integer> chkdAmenities = new ArrayList<Integer>();
		int numChkd = 0;
		for (CheckBox box : chkBoxs) {
			boolean checked = box.getValue();
			if (checked) {
				numChkd++;
				if (numChkd > CommonConstants.ALLOWED_AMENITIES_SIZE) {
					return false;
				}
				chkdAmenities.add(Integer.parseInt(box.getFormValue()));
			}
		}
		int amenity1 = 0, amenity2 = 0, amenity3 = 0, amenity4 = 0;
		for (Integer amenityIndx : chkdAmenities) {
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
		pDTO.setAmenity1(amenity1);
		pDTO.setAmenity2(amenity2);
		pDTO.setAmenity3(amenity3);
		pDTO.setAmenity4(amenity4);
		return true;
	}

	private boolean updatePropertyDTO() {
		int mode = getPMode();
		if (mode == 0) {
			Window.alert("Please select if it is for rent or sale");
			return false;
		} else {
			pDTO.setPMode(mode);
		}
		String msg = validateInputs();
		if (msg != null) {
			Window.alert(msg);
			return false;
		}

		for (Map.Entry<TextBox, String> entry : fieldToMsgMap.entrySet()) {
			TextBox tb = entry.getKey();
			if (!PropertyOptions.isValidNumber(tb.getValue())) {
				Window.alert(entry.getValue());
				return false;
			}
		}

		if (!setAmenities(pDTO)) {
			Window.alert(PropertyOptions.noOfAmenitiesMorethan16);
			return false;
		}

		pDTO.setLat(marker.getLatLng().getLatitude());
		pDTO.setLng(marker.getLatLng().getLongitude());

		pDTO.setPType(pTypesList.getSelectedIndex());
		pDTO.setAddrNum(addrNum.getValue());
		pDTO.setStreetName(streetName.getValue());
		pDTO.setSuburb(suburb.getValue());
		pDTO.setCity(city.getValue());
		pDTO.setPostCode(postCode.getValue());
		pDTO.setState(state.getValue());
		pDTO.setTitle(title.getValue());

		pDTO.setBedRooms(bedRoomsList.getSelectedIndex());

		int value = bathRooms.getValue() == null ? 0 : Integer.parseInt(bathRooms.getValue());
		pDTO.setBathRooms(value);

		pDTO.setView(viewTypesList.getSelectedIndex());

		pDTO.setBuildDate(buildDate.getValue());

		pDTO.setHomeLoan(homeLoanList.getSelectedIndex());
		pDTO.setFloor(floorList.getSelectedIndex());
		pDTO.setDescription(description.getValue());

		value = price.getValue() == null ? 0 : Integer.parseInt(price.getValue());
		pDTO.setPrice(value);

		pDTO.setCurrency(currencyTypesList.getSelectedIndex());
		value = sqrft.getValue() == null ? 0 : Integer.parseInt(sqrft.getValue());

		pDTO.setSqrft(value);

		pDTO.setHeat(heatTypesList.getSelectedIndex());

		return true;
	}

	private String validateInputs() {
		List<String> inValidInputs = new ArrayList<String>();
		for (Entry<ListBox, String> entry : listBoxesTobeValidated.entrySet()) {
			if (entry.getKey().getSelectedIndex() == 0) {
				inValidInputs.add(entry.getValue());
			}
		}
		for (Entry<TextBox, String> entry : textBoxesTobeValidated.entrySet()) {
			if (entry.getKey().getValue().isEmpty()) {
				inValidInputs.add(entry.getValue());
			}
		}
		return (inValidInputs.size() > 0) ? PropertyOptions.enterProperVals + inValidInputs.toString() : null;
	}

	private void handleCityValueChange() {
		try {
			if (!city.getValue().isEmpty()) {
				if (!state.getValue().isEmpty()) {
					String srch = city.getValue().trim() + ", " + state.getValue().trim();
					LatLng point = ResultsHelper.LOC_TO_LATLNG.get(srch);
					if (point != null) {
						map.clearOverlays();
						map.setCenter(point);
						setMarker(point);
					}
				}
			}
		} catch (Exception e) {

		}

	}

	private void setMarker(LatLng point) {
		MarkerOptions options = MarkerOptions.newInstance();
		options.setDraggable(true);
		marker = new Marker(point, options);
		marker.setDraggingEnabled(true);
		map.addOverlay(marker);
	}

}
