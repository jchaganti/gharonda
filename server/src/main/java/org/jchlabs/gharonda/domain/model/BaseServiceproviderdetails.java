package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the serviceproviderdetails table. Do not modify this class because it
 * will be overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="serviceproviderdetails"
 */

public abstract class BaseServiceproviderdetails implements Serializable {

	public static String REF = "Serviceproviderdetails";
	public static String PROP_ADDR_LINE1 = "AddrLine1";
	public static String PROP_ADDR_LINE2 = "AddrLine2";
	public static String PROP_STATE = "State";
	public static String PROP_WEBSITE = "Website";
	public static String PROP_BUSINESS_TYPE = "BusinessType";
	public static String PROP_SUBURB = "Suburb";
	public static String PROP_ID = "Id";
	public static String PROP_ZIP = "Zip";
	public static String PROP_COMPANY_NAME = "CompanyName";
	public static String PROP_CITY = "City";

	// constructors
	public BaseServiceproviderdetails() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseServiceproviderdetails(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseServiceproviderdetails(java.lang.Integer id, java.lang.Integer businessType) {

		this.setId(id);
		this.setBusinessType(businessType);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer businessType;
	private java.lang.String companyName;
	private java.lang.String addrLine1;
	private java.lang.String addrLine2;
	private java.lang.String city;
	private java.lang.String zip;
	private java.lang.String website;
	private java.lang.String suburb;
	private java.lang.String state;

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
	 * Return the value associated with the column: businessType
	 */
	public java.lang.Integer getBusinessType() {
		return businessType;
	}

	/**
	 * Set the value related to the column: businessType
	 * @param businessType the businessType value
	 */
	public void setBusinessType(java.lang.Integer businessType) {
		this.businessType = businessType;
	}

	/**
	 * Return the value associated with the column: company_name
	 */
	public java.lang.String getCompanyName() {
		return companyName;
	}

	/**
	 * Set the value related to the column: company_name
	 * @param companyName the company_name value
	 */
	public void setCompanyName(java.lang.String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Return the value associated with the column: addr_line1
	 */
	public java.lang.String getAddrLine1() {
		return addrLine1;
	}

	/**
	 * Set the value related to the column: addr_line1
	 * @param addrLine1 the addr_line1 value
	 */
	public void setAddrLine1(java.lang.String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	/**
	 * Return the value associated with the column: addr_line2
	 */
	public java.lang.String getAddrLine2() {
		return addrLine2;
	}

	/**
	 * Set the value related to the column: addr_line2
	 * @param addrLine2 the addr_line2 value
	 */
	public void setAddrLine2(java.lang.String addrLine2) {
		this.addrLine2 = addrLine2;
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
	 * Return the value associated with the column: zip
	 */
	public java.lang.String getZip() {
		return zip;
	}

	/**
	 * Set the value related to the column: zip
	 * @param zip the zip value
	 */
	public void setZip(java.lang.String zip) {
		this.zip = zip;
	}

	/**
	 * Return the value associated with the column: website
	 */
	public java.lang.String getWebsite() {
		return website;
	}

	/**
	 * Set the value related to the column: website
	 * @param website the website value
	 */
	public void setWebsite(java.lang.String website) {
		this.website = website;
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

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Serviceproviderdetails))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Serviceproviderdetails serviceproviderdetails = (org.jchlabs.gharonda.domain.model.Serviceproviderdetails) obj;
			if (null == this.getId() || null == serviceproviderdetails.getId())
				return false;
			else
				return (this.getId().equals(serviceproviderdetails.getId()));
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