package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the neightbourhood table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="neightbourhood"
 */

public abstract class BaseNeightbourhood implements Serializable {

	public static String REF = "Neightbourhood";
	public static String PROP_LNG = "Lng";
	public static String PROP_CATEGORY = "Category";
	public static String PROP_SUB_CATEGORY = "SubCategory";
	public static String PROP_LAT = "Lat";
	public static String PROP_ID = "Id";

	// constructors
	public BaseNeightbourhood() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseNeightbourhood(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Double lat;
	private java.lang.Double lng;
	private java.lang.Integer category;
	private java.lang.String subCategory;

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
	 * Return the value associated with the column: category
	 */
	public java.lang.Integer getCategory() {
		return category;
	}

	/**
	 * Set the value related to the column: category
	 * @param category the category value
	 */
	public void setCategory(java.lang.Integer category) {
		this.category = category;
	}

	/**
	 * Return the value associated with the column: subCategory
	 */
	public java.lang.String getSubCategory() {
		return subCategory;
	}

	/**
	 * Set the value related to the column: subCategory
	 * @param subCategory the subCategory value
	 */
	public void setSubCategory(java.lang.String subCategory) {
		this.subCategory = subCategory;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Neightbourhood))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood = (org.jchlabs.gharonda.domain.model.Neightbourhood) obj;
			if (null == this.getId() || null == neightbourhood.getId())
				return false;
			else
				return (this.getId().equals(neightbourhood.getId()));
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