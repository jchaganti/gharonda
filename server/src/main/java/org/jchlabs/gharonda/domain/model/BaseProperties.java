package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the properties table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="properties"
 */

public abstract class BaseProperties extends org.jchlabs.gharonda.domain.model.Contentholder implements Serializable {

	public static String REF = "Properties";
	public static String PROP_P_TYPE = "PType";
	public static String PROP_SQRFT = "Sqrft";
	public static String PROP_PLAN_TYPE = "PlanType";
	public static String PROP_RESERVED_STR = "ReservedStr";
	public static String PROP_ADDR_NUM = "AddrNum";
	public static String PROP_VIEW = "View";
	public static String PROP_RESERVED_INT = "ReservedInt";
	public static String PROP_IS_E_BOOK = "IsEBook";
	public static String PROP_CURRENCY = "Currency";
	public static String PROP_HOME_LOAN = "HomeLoan";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_BED_ROOMS = "BedRooms";
	public static String PROP_ISACTIVE = "Isactive";
	public static String PROP_LAT = "Lat";
	public static String PROP_IS_FEATURED = "IsFeatured";
	public static String PROP_TITLE = "Title";
	public static String PROP_CITY = "City";
	public static String PROP_FLOOR = "Floor";
	public static String PROP_P_MODE = "PMode";
	public static String PROP_AMENITY3 = "Amenity3";
	public static String PROP_LNG = "Lng";
	public static String PROP_BUILD_DATE = "BuildDate";
	public static String PROP_AMENITY4 = "Amenity4";
	public static String PROP_IS_YARD_SIGN = "IsYardSign";
	public static String PROP_IS_BARGAIN = "IsBargain";
	public static String PROP_AMENITY1 = "Amenity1";
	public static String PROP_AMENITY2 = "Amenity2";
	public static String PROP_STATE = "State";
	public static String PROP_HEAT = "Heat";
	public static String PROP_STREET_NAME = "StreetName";
	public static String PROP_POST_CODE = "PostCode";
	public static String PROP_BATH_ROOMS = "BathRooms";
	public static String PROP_PRICE = "Price";
	public static String PROP_SUBURB = "Suburb";
	public static String PROP_ID = "Id";

	// constructors
	public BaseProperties() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProperties(java.lang.Integer id) {
		super(id);
	}

	private int hashCode = Integer.MIN_VALUE;

	// fields
	private java.lang.Double lat;
	private java.lang.Double lng;
	private java.lang.Integer pType;
	private java.lang.Integer pMode;
	private java.lang.String addrNum;
	private java.lang.String streetName;
	private java.lang.String suburb;
	private java.lang.String city;
	private java.lang.String postCode;
	private java.lang.String state;
	private java.lang.String title;
	private java.lang.Integer bedRooms;
	private java.lang.Integer heat;
	private java.lang.Integer bathRooms;
	private java.lang.Integer view;
	private java.lang.String buildDate;
	private java.lang.Integer homeLoan;
	private java.lang.Integer floor;
	private java.lang.String description;
	private java.lang.Integer price;
	private java.lang.Integer sqrft;
	private java.lang.Integer currency;
	private java.lang.Integer isactive;
	private java.lang.Integer planType;
	private java.lang.Integer isFeatured;
	private java.lang.Integer isBargain;
	private java.lang.Integer isYardSign;
	private java.lang.Integer isEBook;
	private java.lang.Integer amenity1;
	private java.lang.Integer amenity2;
	private java.lang.Integer amenity3;
	private java.lang.Integer amenity4;
	private java.lang.Integer reservedInt;
	private java.lang.String reservedStr;

	/**
	 * Return the value associated with the column: lat
	 */
	public java.lang.Double getLat() {
		return lat;
	}

	/**
	 * Set the value related to the column: lat
	 * @param lat the lat value
	 */
	public void setLat(java.lang.Double lat) {
		this.lat = lat;
	}

	/**
	 * Return the value associated with the column: lng
	 */
	public java.lang.Double getLng() {
		return lng;
	}

	/**
	 * Set the value related to the column: lng
	 * @param lng the lng value
	 */
	public void setLng(java.lang.Double lng) {
		this.lng = lng;
	}

	/**
	 * Return the value associated with the column: pType
	 */
	public java.lang.Integer getPType() {
		return pType;
	}

	/**
	 * Set the value related to the column: pType
	 * @param pType the pType value
	 */
	public void setPType(java.lang.Integer pType) {
		this.pType = pType;
	}

	/**
	 * Return the value associated with the column: pMode
	 */
	public java.lang.Integer getPMode() {
		return pMode;
	}

	/**
	 * Set the value related to the column: pMode
	 * @param pMode the pMode value
	 */
	public void setPMode(java.lang.Integer pMode) {
		this.pMode = pMode;
	}

	/**
	 * Return the value associated with the column: addrNum
	 */
	public java.lang.String getAddrNum() {
		return addrNum;
	}

	/**
	 * Set the value related to the column: addrNum
	 * @param addrNum the addrNum value
	 */
	public void setAddrNum(java.lang.String addrNum) {
		this.addrNum = addrNum;
	}

	/**
	 * Return the value associated with the column: streetName
	 */
	public java.lang.String getStreetName() {
		return streetName;
	}

	/**
	 * Set the value related to the column: streetName
	 * @param streetName the streetName value
	 */
	public void setStreetName(java.lang.String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Return the value associated with the column: suburb
	 */
	public java.lang.String getSuburb() {
		return suburb;
	}

	/**
	 * Set the value related to the column: suburb
	 * @param suburb the suburb value
	 */
	public void setSuburb(java.lang.String suburb) {
		this.suburb = suburb;
	}

	/**
	 * Return the value associated with the column: city
	 */
	public java.lang.String getCity() {
		return city;
	}

	/**
	 * Set the value related to the column: city
	 * @param city the city value
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}

	/**
	 * Return the value associated with the column: postCode
	 */
	public java.lang.String getPostCode() {
		return postCode;
	}

	/**
	 * Set the value related to the column: postCode
	 * @param postCode the postCode value
	 */
	public void setPostCode(java.lang.String postCode) {
		this.postCode = postCode;
	}

	/**
	 * Return the value associated with the column: state
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * Set the value related to the column: state
	 * @param state the state value
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * Return the value associated with the column: title
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * Set the value related to the column: title
	 * @param title the title value
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * Return the value associated with the column: bedRooms
	 */
	public java.lang.Integer getBedRooms() {
		return bedRooms;
	}

	/**
	 * Set the value related to the column: bedRooms
	 * @param bedRooms the bedRooms value
	 */
	public void setBedRooms(java.lang.Integer bedRooms) {
		this.bedRooms = bedRooms;
	}

	/**
	 * Return the value associated with the column: heat
	 */
	public java.lang.Integer getHeat() {
		return heat;
	}

	/**
	 * Set the value related to the column: heat
	 * @param heat the heat value
	 */
	public void setHeat(java.lang.Integer heat) {
		this.heat = heat;
	}

	/**
	 * Return the value associated with the column: bathRooms
	 */
	public java.lang.Integer getBathRooms() {
		return bathRooms;
	}

	/**
	 * Set the value related to the column: bathRooms
	 * @param bathRooms the bathRooms value
	 */
	public void setBathRooms(java.lang.Integer bathRooms) {
		this.bathRooms = bathRooms;
	}

	/**
	 * Return the value associated with the column: view
	 */
	public java.lang.Integer getView() {
		return view;
	}

	/**
	 * Set the value related to the column: view
	 * @param view the view value
	 */
	public void setView(java.lang.Integer view) {
		this.view = view;
	}

	/**
	 * Return the value associated with the column: buildDate
	 */
	public java.lang.String getBuildDate() {
		return buildDate;
	}

	/**
	 * Set the value related to the column: buildDate
	 * @param buildDate the buildDate value
	 */
	public void setBuildDate(java.lang.String buildDate) {
		this.buildDate = buildDate;
	}

	/**
	 * Return the value associated with the column: homeLoan
	 */
	public java.lang.Integer getHomeLoan() {
		return homeLoan;
	}

	/**
	 * Set the value related to the column: homeLoan
	 * @param homeLoan the homeLoan value
	 */
	public void setHomeLoan(java.lang.Integer homeLoan) {
		this.homeLoan = homeLoan;
	}

	/**
	 * Return the value associated with the column: floor
	 */
	public java.lang.Integer getFloor() {
		return floor;
	}

	/**
	 * Set the value related to the column: floor
	 * @param floor the floor value
	 */
	public void setFloor(java.lang.Integer floor) {
		this.floor = floor;
	}

	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Return the value associated with the column: price
	 */
	public java.lang.Integer getPrice() {
		return price;
	}

	/**
	 * Set the value related to the column: price
	 * @param price the price value
	 */
	public void setPrice(java.lang.Integer price) {
		this.price = price;
	}

	/**
	 * Return the value associated with the column: sqrft
	 */
	public java.lang.Integer getSqrft() {
		return sqrft;
	}

	/**
	 * Set the value related to the column: sqrft
	 * @param sqrft the sqrft value
	 */
	public void setSqrft(java.lang.Integer sqrft) {
		this.sqrft = sqrft;
	}

	/**
	 * Return the value associated with the column: currency
	 */
	public java.lang.Integer getCurrency() {
		return currency;
	}

	/**
	 * Set the value related to the column: currency
	 * @param currency the currency value
	 */
	public void setCurrency(java.lang.Integer currency) {
		this.currency = currency;
	}

	/**
	 * Return the value associated with the column: isactive
	 */
	public java.lang.Integer getIsactive() {
		return isactive;
	}

	/**
	 * Set the value related to the column: isactive
	 * @param isactive the isactive value
	 */
	public void setIsactive(java.lang.Integer isactive) {
		this.isactive = isactive;
	}

	/**
	 * Return the value associated with the column: planType
	 */
	public java.lang.Integer getPlanType() {
		return planType;
	}

	/**
	 * Set the value related to the column: planType
	 * @param planType the planType value
	 */
	public void setPlanType(java.lang.Integer planType) {
		this.planType = planType;
	}

	/**
	 * Return the value associated with the column: isFeatured
	 */
	public java.lang.Integer getIsFeatured() {
		return isFeatured;
	}

	/**
	 * Set the value related to the column: isFeatured
	 * @param isFeatured the isFeatured value
	 */
	public void setIsFeatured(java.lang.Integer isFeatured) {
		this.isFeatured = isFeatured;
	}

	/**
	 * Return the value associated with the column: isBargain
	 */
	public java.lang.Integer getIsBargain() {
		return isBargain;
	}

	/**
	 * Set the value related to the column: isBargain
	 * @param isBargain the isBargain value
	 */
	public void setIsBargain(java.lang.Integer isBargain) {
		this.isBargain = isBargain;
	}

	/**
	 * Return the value associated with the column: isYardSign
	 */
	public java.lang.Integer getIsYardSign() {
		return isYardSign;
	}

	/**
	 * Set the value related to the column: isYardSign
	 * @param isYardSign the isYardSign value
	 */
	public void setIsYardSign(java.lang.Integer isYardSign) {
		this.isYardSign = isYardSign;
	}

	/**
	 * Return the value associated with the column: isEBook
	 */
	public java.lang.Integer getIsEBook() {
		return isEBook;
	}

	/**
	 * Set the value related to the column: isEBook
	 * @param isEBook the isEBook value
	 */
	public void setIsEBook(java.lang.Integer isEBook) {
		this.isEBook = isEBook;
	}

	/**
	 * Return the value associated with the column: amenity1
	 */
	public java.lang.Integer getAmenity1() {
		return amenity1;
	}

	/**
	 * Set the value related to the column: amenity1
	 * @param amenity1 the amenity1 value
	 */
	public void setAmenity1(java.lang.Integer amenity1) {
		this.amenity1 = amenity1;
	}

	/**
	 * Return the value associated with the column: amenity2
	 */
	public java.lang.Integer getAmenity2() {
		return amenity2;
	}

	/**
	 * Set the value related to the column: amenity2
	 * @param amenity2 the amenity2 value
	 */
	public void setAmenity2(java.lang.Integer amenity2) {
		this.amenity2 = amenity2;
	}

	/**
	 * Return the value associated with the column: amenity3
	 */
	public java.lang.Integer getAmenity3() {
		return amenity3;
	}

	/**
	 * Set the value related to the column: amenity3
	 * @param amenity3 the amenity3 value
	 */
	public void setAmenity3(java.lang.Integer amenity3) {
		this.amenity3 = amenity3;
	}

	/**
	 * Return the value associated with the column: amenity4
	 */
	public java.lang.Integer getAmenity4() {
		return amenity4;
	}

	/**
	 * Set the value related to the column: amenity4
	 * @param amenity4 the amenity4 value
	 */
	public void setAmenity4(java.lang.Integer amenity4) {
		this.amenity4 = amenity4;
	}

	/**
	 * Return the value associated with the column: reservedInt
	 */
	public java.lang.Integer getReservedInt() {
		return reservedInt;
	}

	/**
	 * Set the value related to the column: reservedInt
	 * @param reservedInt the reservedInt value
	 */
	public void setReservedInt(java.lang.Integer reservedInt) {
		this.reservedInt = reservedInt;
	}

	/**
	 * Return the value associated with the column: reservedStr
	 */
	public java.lang.String getReservedStr() {
		return reservedStr;
	}

	/**
	 * Set the value related to the column: reservedStr
	 * @param reservedStr the reservedStr value
	 */
	public void setReservedStr(java.lang.String reservedStr) {
		this.reservedStr = reservedStr;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Properties))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Properties properties = (org.jchlabs.gharonda.domain.model.Properties) obj;
			if (null == this.getId() || null == properties.getId())
				return false;
			else
				return (this.getId().equals(properties.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

}