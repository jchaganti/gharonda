package org.jchlabs.gharonda.client.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.domain.model.BaseProperties;
import org.jchlabs.gharonda.domain.model.BitwiseAndSearchCriteria;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.model.DoubleRangeSearchCriteria;
import org.jchlabs.gharonda.domain.model.NumberRangeSearchCriteria;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.StringEqualSearchCriteria;
import org.jchlabs.gharonda.domain.model.StringSearchCriteria;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.CurrencyData;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;

public class PropertyOptions {

	private static final PropertyOptionsMessages messages = (PropertyOptionsMessages) GWT
			.create(PropertyOptionsMessages.class);
	public static final List<String> AMENITIES = new ArrayList<String>();
	public static final List<String> P_TYPES = new ArrayList<String>();
	public static final List<String> MODE_TYPES = new ArrayList<String>();
	public static final List<String> VIEW_TYPES = new ArrayList<String>();
	public static final List<String> HEAT_TYPES = new ArrayList<String>();
	public static final List<String> CURRENCY_TYPES = new ArrayList<String>();
	public static final List<String> BEDROOM_TYPES = new ArrayList<String>();
	public static final List<String> PRICE_TYPES = new ArrayList<String>();
	public static final List<String> SQFT_TYPES = new ArrayList<String>();
	public static final List<String> BUSINESS_TYPES = new ArrayList<String>();
	public static final List<String> YES_NO_OPTIONS = new ArrayList<String>();
	public static final List<String> FLOORS_TYPES = new ArrayList<String>();
	public static final List<String> EMAIL_FREQ_TYPES = new ArrayList<String>();
	public static final List<String> COMPARATOR_TYPES = new ArrayList<String>();
	public static final Map<Integer, CurrencyData> currencyMap = new HashMap<Integer, CurrencyData>();
	public static final Integer DEFAULT_FETCH_START = 0;
	public static final String DEFAULT_SORT_ATTR = BaseProperties.PROP_TIME_STAMP;
	public static final FetchProfile DEFAULT_LIST_SEARCH_PROFILE = new FetchProfile(
			CommonConstants.DEFAULT_LIST_PAGE_SIZE, DEFAULT_FETCH_START, DEFAULT_SORT_ATTR, false);
	public static final FetchProfile DEFAULT_MAP_SEARCH_PROFILE = new FetchProfile(
			CommonConstants.DEFAULT_MAP_PAGE_SIZE, DEFAULT_FETCH_START, DEFAULT_SORT_ATTR, false);
	public static final FetchProfile DEFAULT_FEATURED_SEARCH_PROFILE = new FetchProfile(
			CommonConstants.DEFAULT_FEATURED_SEARCH_SIZE, DEFAULT_FETCH_START, DEFAULT_SORT_ATTR, false);
	public static final FetchProfile DEFAULT_FEATURED_VICINITY_PROFILE = new FetchProfile(
			CommonConstants.DEFAULT_FEATURED_VICINITY_SIZE, DEFAULT_FETCH_START, DEFAULT_SORT_ATTR, false);
	public static final FetchProfile DEFAULT_SUGGESTBOX_SEARCH_PROFILE = new FetchProfile(
			CommonConstants.SUGGESTBOX_RESULTS_SIZE, DEFAULT_FETCH_START, DEFAULT_SORT_ATTR, false);
	public static final String INIT_STR = messages.basicSearchText();
	public static final List<String> SORT_TYPES = new ArrayList<String>();
	public static VTypeValidator numberValidator = new VTypeValidator(VType.NUMERIC);

	public static final String STATE_STR = messages.selState();
	public static final String CITY_STR = messages.selCity();
	public static final String SUBURB_STR = messages.selSuburb();
	public static final String NO_SEARCH_RESULTS_STR = messages.noSearchResults();
	public static final String UNKNOWN = messages.unknown();
	public static final String UNKNOWN_PROP = messages.unknownProperty();
	public static final String NOT_GIVEN = messages.notGiven();
	public static final String LOGIN_FAILED = messages.loginFailed();
	public static final String LOGIN_FAILED1 = messages.loginFailed1();

	public static final String STATE_STR1 = messages.state();
	public static final String CITY_STR1 = messages.city();
	public static final String SUBURB_STR1 = messages.suburb();
	public static final String POST_CODE_STR = messages.postCode();
	public static final String PROP_TYPE = messages.type();
	public static final String VIEW = messages.view();
	public static final String YEAR_BUILT = messages.yearBuilt();
	public static final String ROOM_NO = messages.roomNo();
	public static final String HEAT_STR = messages.heat();
	public static final String FLOOR = messages.floor();
	public static final String HOME_LOAN = messages.homeLoan();
	public static final String COLON = ":";
	public static final String anyType = messages.anyType();
	public static final String any = messages.any();
	public static final String welcome = messages.welcome();
	public static final String anyRoomNo = messages.anyRoomNo();
	public static final String selCitySuburb = messages.selCitySuburb();
	public static final String pTypeNotGiven = messages.pTypeNotGiven();
	public static final String addressNotGiven = messages.addressNotGiven();
	public static final String pListingWithoutMap = messages.pListingWithoutMap();
	public static final String pAddedToFavs = messages.pAddedToFavs();
	public static final String pAlreadyAddedToFavs = messages.pAlreadyAddedToFavs();
	public static final String pNotAddedToFavs = messages.pNotAddedToFavs();
	public static final String pListingWithMap = messages.pListingWithMap();
	public static final String improperSearchCriteria = messages.improperSearchCriteria();
	public static final String propNum = messages.propNum();
	public static final String rooms = messages.rooms();
	public static final String pricePerSft = messages.pricePerSft();
	public static final String baths = messages.baths();
	public static final String forSale = messages.forSale();
	public static final String userPropsNotFound = messages.userPropsNotFound();
	public static final String enterEmailAddress = messages.enterEmailAddress();
	public static final String enterOldPasswd = messages.enterOldPasswd();
	public static final String enterPasswd = messages.enterPasswd();
	public static final String enterFirstName = messages.enterFirstName();
	public static final String enterLastName = messages.enterLastName();
	public static final String enterPhone = messages.enterPhone();
	public static final String enterCell = messages.enterCell();
	public static final String enterCompanyName = messages.enterCompanyName();
	public static final String emailsNotMatch = messages.emailsNotMatch();
	public static final String oldNewEmailsSame = messages.oldNewEmailsSame();
	public static final String passwdsNotMatch = messages.passwdsNotMatch();
	public static final String selectBusinessType = messages.selectBusinessType();
	public static final String userNotFound = messages.userNotFound();
	public static final String userProfileModificationFailed = messages.userProfileModificationFailed();
	public static final String userProfileModificationSuccessful = messages.userProfileModificationSuccessful();
	public static final String passwdModificationFailed = messages.passwdModificationFailed();
	public static final String passwordModificationSuccessful = messages.passwordModificationSuccessful();
	public static final String oldPasswordIncorrect = messages.oldPasswordIncorrect();
	public static final String emailModificationFailed = messages.emailModificationFailed();
	public static final String emailModificationSuccessful = messages.emailModificationSuccessful();
	public static final String userWithNewMailAlreadyRegistered = messages.userWithNewMailAlreadyRegistered();
	public static final String modify = messages.modify();
	public static final String delete = messages.delete();
	public static final String propertyID = messages.propertyID();
	public static final String title = messages.title();
	public static final String price = messages.price();
	public static final String modified = messages.modified();
	public static final String status = messages.status();
	public static final String selProperCurrency = messages.selProperCurrency();
	public static final String onlyNumbersInPrice = messages.onlyNumbersInPrice();
	public static final String onlyNumbersInSqFt = messages.onlyNumbersInSqFt();
	public static final String onlyNumbersInBaths = messages.onlyNumbersInBaths();
	public static final String selFloor = messages.selFloor();
	public static final String selHomeLoan = messages.selHomeLoan();
	public static final String selRoomNo = messages.selRoomNo();
	public static final String selView = messages.selView();
	public static final String selHeat = messages.selHeat();
	public static final String selType = messages.selType();
	public static final String cancelSaveFailed = messages.cancelSaveFailed();
	public static final String propertySaveFailed = messages.propertySaveFailed();
	public static final String imageCouldNotBeDeleted = messages.imageCouldNotBeDeleted();
	public static final String tenImagesUploaded = messages.tenImagesUploaded();
	public static final String submitYourProperty = messages.submitYourProperty();
	public static final String enterSearchText = messages.enterSearchText();
	public static final String anyHeat = messages.anyHeat();
	public static final String noOfAmenitiesMorethan16 = messages.noOfAmenitiesMorethan16();
	public static final String agreeToTerms = messages.agreeToTerms();
	public static final String userCouldNotbeRegistered = messages.userCouldNotbeRegistered();
	public static final String userWithMailIdExists = messages.userWithMailIdExists();
	public static final String registrationFailed = messages.registrationFailed();
	public static final String moreDetails = messages.moreDetails();
	public static final String selCorrectFreq = messages.selCorrectFreq();
	public static final String selMax16Amenities = messages.selMax16Amenities();
	public static final String properPriceComparator = messages.properPriceComparator();
	public static final String properPriceOnRight = messages.properPriceOnRight();
	public static final String properPrice = messages.properPrice();
	public static final String properSftComparator = messages.properSftComparator();
	public static final String properSftOnRight = messages.properSftOnRight();
	public static final String properSft = messages.properSft();
	public static final String properRoomNoComparator = messages.properRoomNoComparator();
	public static final String properRoomNoOnRight = messages.properRoomNoOnRight();
	public static final String properRoomNo = messages.properRoomNo();
	public static final String notifierDesc = messages.notifierDesc();
	public static final String sale = messages.sale();
	public static final String rent = messages.rent();
	public static final String contactUs = messages.contactUs();
	public static final String registerNow = messages.registerNow();
	public static final String myAccount = messages.myAccount();
	public static final String forRentHeader = messages.forRentHeader();
	public static final String forSaleHeader = messages.forSaleHeader();
	public static final String propertyType = messages.propertyType();
	public static final String price0 = messages.price0();
	public static final String area = messages.area();
	public static final String comparisonsSize = messages.comparisonsSize();
	public static final String otherDetails = messages.otherDetails();
	public static final String description = messages.description();
	public static final String thankYou = messages.thankYou();
	public static final String streetName = messages.streetName();
	public static final String streetNo = messages.streetNo();
	public static final String currency = messages.currency();
	public static final String advSearch = messages.advSearch();
	public static final String myProfile = messages.myProfile();
	public static final String myListings = messages.myListings();
	public static final String myFavorites = messages.myFavorites();
	public static final String myNotifierProfile = messages.myNotifierProfile();
	public static final String compareListing = messages.compareListing();
	public static final String articles = messages.articles();
	public static final String details = messages.details();
	public static final String detailsMap = messages.detailsMap();
	public static final String mortgage = messages.mortgage();
	public static final String serviceProviders = messages.serviceProviders();
	public static final String serviceAppraisal = messages.serviceAppraisal();
	public static final String serviceInsurance = messages.serviceInsurance();
	public static final String serviceMoving = messages.serviceMoving();
	public static final String searchResultsSale = messages.searchResultsSale();
	public static final String searchResultsRent = messages.searchResultsRent();
	public static final String searchResultsMapSale = messages.searchResultsMapSale();
	public static final String searchResultsMapRent = messages.searchResultsMapRent();
	public static final String enterProperVals = messages.enterProperVals();

	public static final String noPropertyFound = messages.noPropertyFound();
	public static final String FEATURED_LISTING_COOKIE = "featuredLisitngs";
	public static final String COMPARE_LISTING_COOKIE = "compareListings";

	private static final Map<Integer, NumberRangeSearchCriteria> priceSrchMap = new HashMap<Integer, NumberRangeSearchCriteria>();
	private static final Map<Integer, NumberRangeSearchCriteria> sftSrchMap = new HashMap<Integer, NumberRangeSearchCriteria>();
	private static final String PRICE = BaseProperties.PROP_PRICE;
	private static final String SFT = BaseProperties.PROP_SQRFT;
	private static final String TYPE = BaseProperties.PROP_P_TYPE;
	private static final String BEDROOMS = BaseProperties.PROP_BED_ROOMS;
	private static final String HEAT = BaseProperties.PROP_HEAT;
	private static final String STATE = BaseProperties.PROP_STATE;
	private static final String CITY = BaseProperties.PROP_CITY;
	private static final String SUBURB = BaseProperties.PROP_SUBURB;
	private static final String ID = BaseProperties.PROP_ID;
	private static final String LAT = BaseProperties.PROP_LAT;
	private static final String LNG = BaseProperties.PROP_LNG;
	private static final String POST_CODE = BaseProperties.PROP_POST_CODE;
	private static final String FEATURED = BaseProperties.PROP_IS_FEATURED;
	private static final String ACTIVE = BaseProperties.PROP_ISACTIVE;
	private static final String P_MODE = BaseProperties.PROP_P_MODE;
	static {
		AMENITIES.add(messages.selAmenity());
		AMENITIES.add(messages.Woodwork());
		AMENITIES.add(messages.Alarm());
		AMENITIES.add(messages.UtilityRoom());
		AMENITIES.add(messages.SteelDoor());
		AMENITIES.add(messages.ShowerCabin());
		AMENITIES.add(messages.WallPaper());
		AMENITIES.add(messages.ParentsBathroom());
		AMENITIES.add(messages.Thermopane());
		AMENITIES.add(messages.Jacuzzi());
		AMENITIES.add(messages.CrownMolding());
		AMENITIES.add(messages.LaminateFloor());
		AMENITIES.add(messages.VinylFloor());
		AMENITIES.add(messages.Shade());
		AMENITIES.add(messages.HardwoodFloor());
		AMENITIES.add(messages.VCWindow());
		AMENITIES.add(messages.Security());
		AMENITIES.add(messages.Hydrophore());
		AMENITIES.add(messages.Insulations());
		AMENITIES.add(messages.Generator());
		AMENITIES.add(messages.Siding());
		AMENITIES.add(messages.HousingComplex());
		AMENITIES.add(messages.SportFacilitiy());
		AMENITIES.add(messages.Urgent());
		AMENITIES.add(messages.Bargain());
		AMENITIES.add(messages.ADSL());
		AMENITIES.add(messages.BuiltinOwen());
		AMENITIES.add(messages.Balcony());
		AMENITIES.add(messages.Barbeque());
		AMENITIES.add(messages.HomeAppliance());
		AMENITIES.add(messages.Painted());
		AMENITIES.add(messages.DishWasher());
		AMENITIES.add(messages.Refrigerator());
		AMENITIES.add(messages.WashingMachine());
		AMENITIES.add(messages.WIFI());
		AMENITIES.add(messages.Owen());
		AMENITIES.add(messages.BuiltinCloset());
		AMENITIES.add(messages.VideoIntercom());
		AMENITIES.add(messages.HiltonBathroom());
		AMENITIES.add(messages.Cellar());
		AMENITIES.add(messages.AirConditioning());
		AMENITIES.add(messages.Furniture());
		AMENITIES.add(messages.Sauna());
		AMENITIES.add(messages.Cookstove());
		AMENITIES.add(messages.Fireplace());
		AMENITIES.add(messages.PatioTerrace());
		AMENITIES.add(messages.Elevator());
		AMENITIES.add(messages.CableSatellite());
		AMENITIES.add(messages.PrivateGarage());
		AMENITIES.add(messages.Doormen());
		AMENITIES.add(messages.Kindergarden());
		AMENITIES.add(messages.ParkingLot());
		AMENITIES.add(messages.PressureWaterTank());
		AMENITIES.add(messages.TennisCourt());
		AMENITIES.add(messages.FireExit());
		AMENITIES.add(messages.OpenairSwimmingPool());
		AMENITIES.add(messages.IndoorSwimmingPool());
		AMENITIES.add(messages.ClosetoAirport());
		AMENITIES.add(messages.ClosetoSubwayStation());
		AMENITIES.add(messages.NearMetrobus());
		AMENITIES.add(messages.ClosetoMinibusRoad());
		AMENITIES.add(messages.ClosetoBusStation());
		AMENITIES.add(messages.ClosetoTEM());
		AMENITIES.add(messages.SolarEnergy());

		P_TYPES.add(messages.pType1());
		P_TYPES.add(messages.pType2());
		P_TYPES.add(messages.pType3());
		P_TYPES.add(messages.pType4());
		P_TYPES.add(messages.pType5());
		P_TYPES.add(messages.pType6());
		P_TYPES.add(messages.pType7());
		P_TYPES.add(messages.pType8());
		P_TYPES.add(messages.pType9());
		P_TYPES.add(messages.pType10());
		P_TYPES.add(messages.pType11());
		P_TYPES.add(messages.pType12());
		P_TYPES.add(messages.pType13());
		P_TYPES.add(messages.pType14());
		P_TYPES.add(messages.pType15());
		P_TYPES.add(messages.pType16());
		P_TYPES.add(messages.pType17());
		P_TYPES.add(messages.pType18());
		P_TYPES.add(messages.pType19());

		VIEW_TYPES.add(messages.view1());
		VIEW_TYPES.add(messages.view2());
		VIEW_TYPES.add(messages.view3());
		VIEW_TYPES.add(messages.view4());
		VIEW_TYPES.add(messages.view5());
		VIEW_TYPES.add(messages.view6());
		VIEW_TYPES.add(messages.view7());

		HEAT_TYPES.add(messages.heat1());
		HEAT_TYPES.add(messages.heat2());
		HEAT_TYPES.add(messages.heat3());
		HEAT_TYPES.add(messages.heat4());
		HEAT_TYPES.add(messages.heat5());
		HEAT_TYPES.add(messages.heat6());
		HEAT_TYPES.add(messages.heat7());
		HEAT_TYPES.add(messages.heat8());
		HEAT_TYPES.add(messages.heat9());
		HEAT_TYPES.add(messages.heat10());
		HEAT_TYPES.add(messages.heat11());
		HEAT_TYPES.add(messages.heat12());
		HEAT_TYPES.add(messages.heat13());

		CURRENCY_TYPES.add("TL");
		CURRENCY_TYPES.add("USD");
		CURRENCY_TYPES.add("GBP");
		CURRENCY_TYPES.add("EURO");

		BEDROOM_TYPES.add("1+1");
		BEDROOM_TYPES.add("2+1");
		BEDROOM_TYPES.add("3+1");
		BEDROOM_TYPES.add("4+1");
		BEDROOM_TYPES.add("2+2");
		BEDROOM_TYPES.add("3+2");
		BEDROOM_TYPES.add("4+2");
		BEDROOM_TYPES.add("5+1");
		BEDROOM_TYPES.add("5+2");
		BEDROOM_TYPES.add("5+3");
		BEDROOM_TYPES.add("6+1");
		BEDROOM_TYPES.add("6+2");
		BEDROOM_TYPES.add("6+3");
		BEDROOM_TYPES.add("7+1");
		BEDROOM_TYPES.add("7+2");
		BEDROOM_TYPES.add("7+3");
		BEDROOM_TYPES.add("8+2");
		BEDROOM_TYPES.add("8+4");
		BEDROOM_TYPES.add("Studio");

		SQFT_TYPES.add("0- 100m2");
		SQFT_TYPES.add("101m2 - 150m2");
		SQFT_TYPES.add("151m2 - 250m2");
		SQFT_TYPES.add(messages.sft1());

		PRICE_TYPES.add("0–100BinTL");
		PRICE_TYPES.add("100BinTL - 200BinTL");
		PRICE_TYPES.add("200BinTL - 500BinTL");
		PRICE_TYPES.add(messages.price4());

		SORT_TYPES.add(messages.sortType0());
		SORT_TYPES.add(messages.sortType1());
		SORT_TYPES.add(messages.sortType2());
		SORT_TYPES.add(messages.sortType3());
		SORT_TYPES.add(messages.sortType4());
		SORT_TYPES.add(messages.sortType5());
		SORT_TYPES.add(messages.sortType6());
		SORT_TYPES.add(messages.sortType7());
		SORT_TYPES.add(messages.sortType8());

		BUSINESS_TYPES.add(messages.businessType0());
		BUSINESS_TYPES.add(messages.businessType1());
		BUSINESS_TYPES.add(messages.businessType2());
		BUSINESS_TYPES.add(messages.businessType3());
		BUSINESS_TYPES.add(messages.businessType4());
		BUSINESS_TYPES.add(messages.businessType5());
		BUSINESS_TYPES.add(messages.businessType6());
		BUSINESS_TYPES.add(messages.businessType7());
		BUSINESS_TYPES.add(messages.businessType8());
		BUSINESS_TYPES.add(messages.businessType9());
		BUSINESS_TYPES.add(messages.businessType10());
		BUSINESS_TYPES.add(messages.businessType11());
		BUSINESS_TYPES.add(messages.businessType12());
		BUSINESS_TYPES.add(messages.businessType13());
		BUSINESS_TYPES.add(messages.businessType14());
		BUSINESS_TYPES.add(messages.businessType15());
		BUSINESS_TYPES.add(messages.businessType16());
		BUSINESS_TYPES.add(messages.businessType17());

		YES_NO_OPTIONS.add(messages.yes());
		YES_NO_OPTIONS.add(messages.no());

		FLOORS_TYPES.add("1");
		FLOORS_TYPES.add("2");
		FLOORS_TYPES.add("3");
		FLOORS_TYPES.add("4");
		FLOORS_TYPES.add("5");
		FLOORS_TYPES.add("6");
		FLOORS_TYPES.add("7");
		FLOORS_TYPES.add("8");
		FLOORS_TYPES.add("9");
		FLOORS_TYPES.add("10");
		FLOORS_TYPES.add("11");
		FLOORS_TYPES.add("12");
		FLOORS_TYPES.add("13");
		FLOORS_TYPES.add("14");
		FLOORS_TYPES.add("15+");
		FLOORS_TYPES.add(messages.floorType1());
		FLOORS_TYPES.add(messages.floorType2());
		FLOORS_TYPES.add(messages.floorType3());
		FLOORS_TYPES.add(messages.floorType4());

		EMAIL_FREQ_TYPES.add(messages.stopNotification());
		EMAIL_FREQ_TYPES.add(messages.daily());
		EMAIL_FREQ_TYPES.add(messages.weekly());
		// EMAIL_FREQ_TYPES.add(messages.fortnightly());
		// EMAIL_FREQ_TYPES.add(messages.monthly());

		COMPARATOR_TYPES.add(messages.select());
		COMPARATOR_TYPES.add(messages.exactly());
		COMPARATOR_TYPES.add(messages.to());
		COMPARATOR_TYPES.add(messages.andLess());
		COMPARATOR_TYPES.add(messages.andGreater());
		MODE_TYPES.add(messages.sale());
		MODE_TYPES.add(messages.rent());

		sftSrchMap.put(1, new NumberRangeSearchCriteria(SFT, true, 0, 100));
		sftSrchMap.put(2, new NumberRangeSearchCriteria(SFT, true, 101, 150));
		sftSrchMap.put(3, new NumberRangeSearchCriteria(SFT, true, 151, 250));
		sftSrchMap.put(4, new NumberRangeSearchCriteria(SFT, true, 251, null));

		priceSrchMap.put(1, new NumberRangeSearchCriteria(PRICE, true, 0, 100000));
		priceSrchMap.put(2, new NumberRangeSearchCriteria(PRICE, true, 100001, 200000));
		priceSrchMap.put(3, new NumberRangeSearchCriteria(PRICE, true, 200001, 500000));
		priceSrchMap.put(4, new NumberRangeSearchCriteria(PRICE, true, 500001, null));

		currencyMap.put(0, new TLCurrencyData());
		currencyMap.put(1, new TLCurrencyData());
		currencyMap.put(2, new USDCurrencyData());
		currencyMap.put(3, new GBPCurrencyData());
		currencyMap.put(4, new EuroCurrencyData());
	}

	public static SearchCriteriaIFace getSelectedPriceSearchCriteria(Integer i) {
		return priceSrchMap.get(i);
	}

	public static SearchCriteriaIFace getSelectedSftSearchCriteria(Integer i) {
		return sftSrchMap.get(i);
	}

	public static SearchCriteriaIFace getSelectedBedRoomsSearchCriteria(Integer i) {
		return new NumberSearchCriteria(BEDROOMS, i);
	}

	public static SearchCriteriaIFace getSelectedPropertyTypeSearchCriteria(Integer i) {
		return new NumberSearchCriteria(TYPE, i);
	}

	public static SearchCriteriaIFace getSelectedHeatSearchCriteria(Integer i) {
		return new NumberSearchCriteria(HEAT, i);
	}

	public static SearchCriteriaIFace getSuburbSearchCriteria(String s) {
		return new StringSearchCriteria(SUBURB, s);
	}

	public static SearchCriteriaIFace getStateSearchCriteria(String s) {
		return new StringSearchCriteria(STATE, s);
	}

	public static SearchCriteriaIFace getCitySearchCriteria(String s) {
		return new StringSearchCriteria(CITY, s);
	}

	public static SearchCriteriaIFace getAmenitiesSearchCriteria(String s, int num) {
		return new BitwiseAndSearchCriteria(s, num);
	}

	public static SearchCriteriaIFace getPropertyIdSearchCriteria(Integer i) {
		return new NumberSearchCriteria(ID, i);
	}

	public static SearchCriteriaIFace getIsActiveSearchCriteria() {
		return new NumberSearchCriteria(ACTIVE, 1);
	}

	public static SearchCriteriaIFace getPModeSearchCriteria(int mode) {
		return new NumberSearchCriteria(P_MODE, mode);
	}

	public static void adjustForPModeSearchCriteria(List<SearchCriteriaIFace> cList, int mode) {
		boolean found = false;
		for (SearchCriteriaIFace sc : cList) {
			if (P_MODE.equals(sc.getSearchAttributeName())) {
				if (sc instanceof NumberSearchCriteria) {
					NumberSearchCriteria nsc = (NumberSearchCriteria) sc;
					nsc.setSearchNumber(mode);
				}
				found = true;
				break;
			}
		}

		if (!found) {
			cList.add(getPModeSearchCriteria(mode));
		}
	}

	public static List<SearchCriteriaIFace> getPropertyIdSearchCriteriaForFeaturedListing(String[] ids) {
		List<SearchCriteriaIFace> list = null;
		if (ids != null && ids.length > 0) {
			list = new ArrayList<SearchCriteriaIFace>();
			for (String id : ids) {
				try {
					Integer idNum = Integer.parseInt(id);
					list.add(getPropertyIdSearchCriteria(idNum));
				} catch (Exception e) {

				}

			}
		}
		return list;
	}

	public static SearchCriteriaIFace getPostCodeSearchCriteria(String i) {
		return new StringSearchCriteria(POST_CODE, i);
	}

	public static Integer getAmenityBitVal(int i) {
		Integer indx = i % CommonConstants.AMENITIES_BITS_SIZE;
		indx = (indx == 0) ? indx = 15 : indx - 1;
		return 1 << indx;
	}

	public static SearchCriteriaIFace getLatRangeSearchCriteria(double l, double r) {
		return new DoubleRangeSearchCriteria(LAT, l, r);
	}

	public static SearchCriteriaIFace getLngRangeSearchCriteria(double l, double r) {
		return new DoubleRangeSearchCriteria(LNG, l, r);
	}

	public static SearchCriteriaIFace getFeaturedListingCriteria() {
		return new NumberSearchCriteria(FEATURED, 1);
	}

	public static void updateFetchProfileForSort(int selIndx, FetchProfile profile) {
		if (selIndx > 0) {
			switch (selIndx) {
			case 1: {
				profile.setSortAttr(DEFAULT_SORT_ATTR);
				profile.setSortDir(true);
				break;
			}
			case 2: {
				profile.setSortAttr(DEFAULT_SORT_ATTR);
				profile.setSortDir(false);
				break;
			}
			case 3: {
				profile.setSortAttr(PRICE);
				profile.setSortDir(true);
				break;
			}
			case 4: {
				profile.setSortAttr(PRICE);
				profile.setSortDir(false);
				break;
			}
			case 5: {
				profile.setSortAttr(SFT);
				profile.setSortDir(true);
				break;
			}
			case 6: {
				profile.setSortAttr(SFT);
				profile.setSortDir(false);
				break;
			}
			case 7: {
				profile.setSortAttr(BEDROOMS);
				profile.setSortDir(true);
				break;
			}
			case 8: {
				profile.setSortAttr(BEDROOMS);
				profile.setSortDir(false);
				break;
			}
			default:
				break;

			}
		}

	}

	public static String formatAddress(String[] tokens, String delim) {
		String line = "";
		for (String token : tokens) {
			if (token != null && !token.isEmpty() && !line.isEmpty()) {
				line += delim + token;
			} else if (token != null && !token.isEmpty()) {
				line = token;
			}
		}
		return line;
	}

	public static String getFormattedPrice(Integer price, Integer currency) {
		if (price == null) {
			price = 0;
		}
		NumberFormat nf = NumberFormat.getCurrencyFormat(PropertyOptions.currencyMap.get(currency));

		String priceStr = nf.format(price);
		priceStr = (currency < 2) ? priceStr.replaceFirst("TL", "") + " TL" : priceStr;

		return priceStr;
	}

	public static String getFormattedAddrLine(String token1, String token2, String token3, String delim) {
		String[] tokens = new String[3];
		tokens[0] = token1;
		tokens[1] = token2;
		tokens[2] = token3;
		return PropertyOptions.formatAddress(tokens, delim);
	}

	public static String getFormattedLine(String delim, String... intokens) {
		String[] tokens = new String[intokens.length];
		int i = 0;
		for (String tok : intokens) {
			tokens[i++] = tok;
		}
		return PropertyOptions.formatAddress(tokens, delim);
	}

	public static String getFormattedDate(Long timestamp) {
		Date d = new Date(timestamp.longValue());
		DateTimeFormat dtformat = DateTimeFormat.getFormat("dd/MM/yyyy");
		return dtformat.format(d);
	}

	public static String formatString(String in, int len) {
		String out = in;
		if (in.length() > len) {
			out = in.substring(0, len) + "...";
		} else {
			// pad it with empty space
			int padLen = len - in.length();
			StringBuffer pad = new StringBuffer();
			for (int i = 0; i < padLen; i++) {
				pad.append("&nbsp;");
			}
			out = in + pad.toString();
		}
		return out;
	}

	public static String pidNotFound(int id) {
		return messages.pidNotFound(id);
	}

	public static String amenityAlreadySelected(String amenity) {
		return messages.amenityAlreadySelected(amenity);
	}

	public static String getFeaturedListingsHTML(String divClass, int cols, int width, List<PropertiesDTO> list) {
		StringBuffer sb = new StringBuffer(1024);
		if (list != null && list.size() > 0) {
			sb.append("<div class=\"" + divClass + "\">");
			sb.append("<table width=\"" + width + "\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");
			sb.append("<tbody>");
			sb.append("<tr>");
			int j = 0, k = 0;
			int rows = list.size() / cols;
			String locale = getCurrentLocale();
			for (k = 0; k < rows; k++) {
				sb.append("<tr>");
				for (j = 0; j < cols; j++) {
					sb.append("<td valign=\"top\" align=\"center\">");
					sb.append("<div>");
					PropertiesDTO property = list.get(j + k);
					String detailsUrl = "javascript:window.open(\""
							+ getPropertyDetailPageUrl(property.getId(), locale) + "\");return false;";
					String pType = property.getPType() == 0 ? UNKNOWN_PROP : P_TYPES.get(property.getPType() - 1);
					sb.append(" <a  onClick='" + detailsUrl + "'><h1>" + pType + "</h1></a>");

					String bedRooms = property.getBedRooms() == 0 ? "0" : PropertyOptions.BEDROOM_TYPES.get(property
							.getBedRooms() - 1);
					String sft = PropertyOptions.UNKNOWN;
					if (property.getSqrft() != null) {
						sft = property.getSqrft().toString();
					}

					String roomsStr = bedRooms + " - " + sft + " m2";
					sb.append("<a onClick='" + detailsUrl + "'>" + roomsStr + "</a>");
					sb.append("<br/>");
					sb.append("<a  onClick='" + detailsUrl + "'> <span class=\"dark_red_txt_price\">");
					String priceStr = PropertyOptions.getFormattedPrice(property.getPrice(), property.getCurrency());
					sb.append("<strong>" + priceStr + "</strong>");
					sb.append("</span></a>");
					String url = getImageUrl(property, "_tn2", "images/img1_homesforsale.jpg");
					sb.append("<a  onClick='" + detailsUrl + "'>");
					sb.append("<img width=\"135\" height=\"101\" border=\"0\" alt=\"\" src=" + url + " />");
					sb.append("</a>");
					sb.append("</div>");
					sb.append("</td>");
					if (cols > 1 && j < cols - 1) {
						sb.append("<td class=\"seperator_veritical\" valign=\"top\" align=\"left\"> </td>");
					}
				}
				sb.append("</tr>");
				if (k < rows - 1) {
					addSeparator(sb, cols);
				}
			}

			sb.append("</tbody>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	public static String getImageUrl(PropertiesDTO rowValue, String extn, String default_image) {
		String url = default_image;
		Set<Contentitems> items = rowValue.getContentitems();
		if (items != null && items.size() > 0) {
			List<Contentitems> _items = sortContentItems(items);
			url = (_items.get(0).getData() + _items.get(0).getName() + extn + ".jpg");
		}
		return url;
	}

	public static List<Contentitems> sortContentItems(Set<Contentitems> itemsSet) {
		List<Contentitems> itemsList = new ArrayList<Contentitems>(itemsSet);
		Collections.sort(itemsList, new Comparator<Contentitems>() {

			@Override
			public int compare(Contentitems o1, Contentitems o2) {
				try {
					final int i1 = Integer.parseInt(o1.getName().split("image")[1]);
					final int i2 = Integer.parseInt(o2.getName().split("image")[1]);
					return i1 > i2 ? 1 : 0;
				} catch (Exception e) {
					return 0;
				}
			}

		});
		return itemsList;
	}

	private static void addSeparator(StringBuffer sb, int cols) {
		int seps = 1;
		if (cols > 1) {
			seps = 3;
		}
		sb.append("<tr>");
		for (int i = 0; i < seps; i++) {
			sb.append("<td valign=\"top\" align=\"left\">");
			sb.append("<span class=\"seperator1\" />");
			sb.append("</td>");
		}
		sb.append("</tr>");
	}

	public static List<PropertiesDTO> mergeList(List<PropertiesDTO> plist, List<PropertiesDTO> rlist, int reqSize) {
		List<PropertiesDTO> list = null;
		if (plist == null) {
			list = rlist;
		} else if (rlist == null) {
			list = plist;
		} else if (plist.size() < reqSize) {
			list = plist;
			for (int i = 0; i < reqSize - plist.size(); i++) {
				list.add(rlist.get(i));
			}
		}
		if (list.size() > reqSize) {
			list = list.subList(0, reqSize);
		}
		return list;
	}

	public static String getPropertyDetailPageUrl(Integer id) {
		String locale = getCurrentLocale();
		return getPropertyDetailPageUrl(id, locale);
	}

	public static String getCurrentLocale() {
		String locale = Window.Location.getParameter("locale");
		if (locale != null) {
			locale = locale.replaceAll("#", "");
		} else {
			locale = "tr";
		}
		return locale;
	}

	public static String getPropertyDetailPageUrl(Integer id, String locale) {
		String result = null;
		if (id != null) {
			// result = "App.html?locale=" + locale + "&id=" + id.toString();
			result = "Gharonda.html?locale=" + locale + "#" + NameTokens.propertyDetailsWOMapPage + ";pid="
					+ id.toString();
		}
		return result;
	}

	public static String getCompareListingPageUrl() {
		return "Gharonda.html?locale=" + getCurrentLocale() + "#" + NameTokens.compareListingPage;
	}

	public static String getAppraisalPageUrl() {
		return "Gharonda.html?locale=" + getCurrentLocale() + "#"
				+ "!article;url=Services////services_appraisal.html;num=2";
	}

	public static String getPrivacyPageUrl() {
		return "Gharonda.html?locale=" + getCurrentLocale() + "#" + NameTokens.homePage + "/"
				+ NameTokens.articlePage + ";num=2;url=About Us//privacy_policy.html";
	}

	public static String getJustListedAndBargainsHTML(String divClass, int width, List<PropertiesDTO> list) {
		StringBuffer sb = new StringBuffer(1024);
		if (list != null && list.size() > 0) {
			sb.append("<div class=\"" + divClass + "\">");
			sb.append("<table width=\"" + width + "\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");
			sb.append("<tbody>");
			sb.append("<tr>");
			int k = 0;
			int rows = list.size();
			String locale = getCurrentLocale();
			for (k = 0; k < rows; k++) {
				sb.append("<tr>");

				sb.append("<td valign=\"top\" align=\"left\">");
				sb.append("<div class=\"graybox_inner3\">");
				PropertiesDTO property = list.get(k);
				String detailsUrl = "javascript:window.open(\"" + getPropertyDetailPageUrl(property.getId(), locale)
						+ "\");return false;";
				sb.append("<a onClick='" + detailsUrl + "'>");
				String url = getImageUrl(property, "_tn1", "images/img1_index.jpg");
				sb.append("<img width=\"52\" height=\"43\" border=\"0\" alt=\"\" src=" + url + " />");
				sb.append("</a>");

				String pType = property.getPType() == 0 ? UNKNOWN_PROP : P_TYPES.get(property.getPType() - 1);
				sb.append(" <a  onClick='" + detailsUrl + "'>" + pType + "</a>");
				sb.append("<br/>");
				sb.append("<div class=\"graybox_sub_inner3\">");
				String addrLine = getFormattedLine(" ", property.getCity(), property.getState());

				sb.append("<a  onClick='" + detailsUrl + "'>" + addrLine + "</a>");
				sb.append("<br/>");
				sb.append("<a  onClick='" + detailsUrl + "'> <span>");
				String priceStr = PropertyOptions.getFormattedPrice(property.getPrice(), property.getCurrency());
				String bedRooms = property.getBedRooms() == 0 ? "0" : PropertyOptions.BEDROOM_TYPES.get(property
						.getBedRooms() - 1);

				String line3 = getFormattedLine(" ", bedRooms, priceStr);
				sb.append(line3);
				sb.append("</span></a>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</td>");

				sb.append("</tr>");
				if (k < rows - 1) {
					addSeparator(sb, 1);
				}
			}

			sb.append("</tbody>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	public static void setCookie(String name, List<PropertiesDTO> list) {
		String[] ids = new String[list.size()];
		int i = 0;
		for (PropertiesDTO p : list) {
			ids[i++] = p.getId().toString();
		}
		Cookies.setCookie(name, Arrays.toString(ids).replaceAll("\\[", "").replaceAll("\\]", ""));
	}

	public static void setCookieCL(String name, List<Integer> list) {
		Cookies.setCookie(name, list.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
	}

	public static boolean isValidNumber(String in) {
		return (in == null || numberValidator.validate(in));
	}

	public static List<Integer> getAmenityIdsForProperty(PropertiesDTO p) {
		List<Integer> amenities = new ArrayList<Integer>();
		addToAmenitiesList(amenities, p.getAmenity1(), 0);
		addToAmenitiesList(amenities, p.getAmenity2(), 1);
		addToAmenitiesList(amenities, p.getAmenity3(), 2);
		addToAmenitiesList(amenities, p.getAmenity4(), 3);
		return amenities;
	}

	public static boolean isValidPhone(TextBox area, TextBox phone) {
		String areaVal = area.getValue();
		String phoneVal = phone.getValue();
		return areaVal.length() == 3 && isValidNumber(areaVal) && phoneVal.length() == 7 && isValidNumber(phoneVal);
	}

	private static List<Integer> addToAmenitiesList(List<Integer> in, Integer amenityVal, int amenitySlot) {
		if (amenityVal > 0) {
			List<Integer> amenities = new ArrayList<Integer>();
			int startingNum = amenitySlot * CommonConstants.AMENITIES_BITS_SIZE;
			for (int i = 1; i <= CommonConstants.AMENITIES_BITS_SIZE; i++) {
				if ((amenityVal & getAmenityBitVal(i)) > 0) {
					amenities.add(startingNum + i);
				}
			}
			in.addAll(amenities);
		}
		return in;
	}

	public static String getFormattedPTypeNMode(String pType, String pMode) {
		return messages.pTypeNMode(pType, pMode);
	}
}
