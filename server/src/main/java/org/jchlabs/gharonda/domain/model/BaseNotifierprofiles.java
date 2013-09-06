package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the notifierprofiles table. Do not modify this class because it will
 * be overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="notifierprofiles"
 */

public abstract class BaseNotifierprofiles implements Serializable {

	public static String REF = "Notifierprofiles";
	public static String PROP_P_TYPE = "PType";
	public static String PROP_BUILD_DATE_R_VAL = "BuildDateRVal";
	public static String PROP_SFT_R_VAL = "SftRVal";
	public static String PROP_PRICE_L_VAL = "PriceLVal";
	public static String PROP_PRICE_R_VAL = "PriceRVal";
	public static String PROP_ROOM_NO_R_VAL = "RoomNoRVal";
	public static String PROP_PRICE_COMPARATOR = "PriceComparator";
	public static String PROP_ROOM_NO_L_VAL = "RoomNoLVal";
	public static String PROP_SFT_L_VAL = "SftLVal";
	public static String PROP_BUILD_DATE_L_VAL = "BuildDateLVal";
	public static String PROP_CITY = "City";
	public static String PROP_AMENITY3 = "Amenity3";
	public static String PROP_TIME_STAMP = "TimeStamp";
	public static String PROP_AMENITY4 = "Amenity4";
	public static String PROP_AMENITY1 = "Amenity1";
	public static String PROP_SFT_COMPARATOR = "SftComparator";
	public static String PROP_AMENITY2 = "Amenity2";
	public static String PROP_STATE = "State";
	public static String PROP_SUBURB = "Suburb";
	public static String PROP_ID = "Id";
	public static String PROP_FREQUENCY = "Frequency";
	public static String PROP_ROOM_NO_COMPARATOR = "RoomNoComparator";
	public static String PROP_BUILD_DATE_COMPARATOR = "BuildDateComparator";

	// constructors
	public BaseNotifierprofiles() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseNotifierprofiles(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Long timeStamp;
	private java.lang.String state;
	private java.lang.String city;
	private java.lang.String suburb;
	private java.lang.Integer pType;
	private java.lang.Integer sftLVal;
	private java.lang.Integer sftComparator;
	private java.lang.Integer sftRVal;
	private java.lang.Integer priceLVal;
	private java.lang.Integer priceComparator;
	private java.lang.Integer priceRVal;
	private java.lang.Integer roomNoLVal;
	private java.lang.Integer roomNoComparator;
	private java.lang.Integer roomNoRVal;
	private java.lang.Integer buildDateLVal;
	private java.lang.Integer buildDateComparator;
	private java.lang.Integer buildDateRVal;
	private java.lang.Integer amenity1;
	private java.lang.Integer amenity2;
	private java.lang.Integer amenity3;
	private java.lang.Integer amenity4;
	private java.lang.Integer frequency;

	/**
	 * Return the unique identifier of this class
	 * @hibernate.id generator-class="increment" column="id"
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: timeStamp
	 */
	public java.lang.Long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Set the value related to the column: timeStamp
	 * @param timeStamp the timeStamp value
	 */
	public void setTimeStamp(java.lang.Long timeStamp) {
		this.timeStamp = timeStamp;
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
	 * Return the value associated with the column: sftLVal
	 */
	public java.lang.Integer getSftLVal() {
		return sftLVal;
	}

	/**
	 * Set the value related to the column: sftLVal
	 * @param sftLVal the sftLVal value
	 */
	public void setSftLVal(java.lang.Integer sftLVal) {
		this.sftLVal = sftLVal;
	}

	/**
	 * Return the value associated with the column: sftComparator
	 */
	public java.lang.Integer getSftComparator() {
		return sftComparator;
	}

	/**
	 * Set the value related to the column: sftComparator
	 * @param sftComparator the sftComparator value
	 */
	public void setSftComparator(java.lang.Integer sftComparator) {
		this.sftComparator = sftComparator;
	}

	/**
	 * Return the value associated with the column: sftRVal
	 */
	public java.lang.Integer getSftRVal() {
		return sftRVal;
	}

	/**
	 * Set the value related to the column: sftRVal
	 * @param sftRVal the sftRVal value
	 */
	public void setSftRVal(java.lang.Integer sftRVal) {
		this.sftRVal = sftRVal;
	}

	/**
	 * Return the value associated with the column: priceLVal
	 */
	public java.lang.Integer getPriceLVal() {
		return priceLVal;
	}

	/**
	 * Set the value related to the column: priceLVal
	 * @param priceLVal the priceLVal value
	 */
	public void setPriceLVal(java.lang.Integer priceLVal) {
		this.priceLVal = priceLVal;
	}

	/**
	 * Return the value associated with the column: priceComparator
	 */
	public java.lang.Integer getPriceComparator() {
		return priceComparator;
	}

	/**
	 * Set the value related to the column: priceComparator
	 * @param priceComparator the priceComparator value
	 */
	public void setPriceComparator(java.lang.Integer priceComparator) {
		this.priceComparator = priceComparator;
	}

	/**
	 * Return the value associated with the column: priceRVal
	 */
	public java.lang.Integer getPriceRVal() {
		return priceRVal;
	}

	/**
	 * Set the value related to the column: priceRVal
	 * @param priceRVal the priceRVal value
	 */
	public void setPriceRVal(java.lang.Integer priceRVal) {
		this.priceRVal = priceRVal;
	}

	/**
	 * Return the value associated with the column: roomNoLVal
	 */
	public java.lang.Integer getRoomNoLVal() {
		return roomNoLVal;
	}

	/**
	 * Set the value related to the column: roomNoLVal
	 * @param roomNoLVal the roomNoLVal value
	 */
	public void setRoomNoLVal(java.lang.Integer roomNoLVal) {
		this.roomNoLVal = roomNoLVal;
	}

	/**
	 * Return the value associated with the column: roomNoComparator
	 */
	public java.lang.Integer getRoomNoComparator() {
		return roomNoComparator;
	}

	/**
	 * Set the value related to the column: roomNoComparator
	 * @param roomNoComparator the roomNoComparator value
	 */
	public void setRoomNoComparator(java.lang.Integer roomNoComparator) {
		this.roomNoComparator = roomNoComparator;
	}

	/**
	 * Return the value associated with the column: roomNoRVal
	 */
	public java.lang.Integer getRoomNoRVal() {
		return roomNoRVal;
	}

	/**
	 * Set the value related to the column: roomNoRVal
	 * @param roomNoRVal the roomNoRVal value
	 */
	public void setRoomNoRVal(java.lang.Integer roomNoRVal) {
		this.roomNoRVal = roomNoRVal;
	}

	/**
	 * Return the value associated with the column: buildDateLVal
	 */
	public java.lang.Integer getBuildDateLVal() {
		return buildDateLVal;
	}

	/**
	 * Set the value related to the column: buildDateLVal
	 * @param buildDateLVal the buildDateLVal value
	 */
	public void setBuildDateLVal(java.lang.Integer buildDateLVal) {
		this.buildDateLVal = buildDateLVal;
	}

	/**
	 * Return the value associated with the column: buildDateComparator
	 */
	public java.lang.Integer getBuildDateComparator() {
		return buildDateComparator;
	}

	/**
	 * Set the value related to the column: buildDateComparator
	 * @param buildDateComparator the buildDateComparator value
	 */
	public void setBuildDateComparator(java.lang.Integer buildDateComparator) {
		this.buildDateComparator = buildDateComparator;
	}

	/**
	 * Return the value associated with the column: buildDateRVal
	 */
	public java.lang.Integer getBuildDateRVal() {
		return buildDateRVal;
	}

	/**
	 * Set the value related to the column: buildDateRVal
	 * @param buildDateRVal the buildDateRVal value
	 */
	public void setBuildDateRVal(java.lang.Integer buildDateRVal) {
		this.buildDateRVal = buildDateRVal;
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
	 * Return the value associated with the column: frequency
	 */
	public java.lang.Integer getFrequency() {
		return frequency;
	}

	/**
	 * Set the value related to the column: frequency
	 * @param frequency the frequency value
	 */
	public void setFrequency(java.lang.Integer frequency) {
		this.frequency = frequency;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Notifierprofiles))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles = (org.jchlabs.gharonda.domain.model.Notifierprofiles) obj;
			if (null == this.getId() || null == notifierprofiles.getId())
				return false;
			else
				return (this.getId().equals(notifierprofiles.getId()));
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