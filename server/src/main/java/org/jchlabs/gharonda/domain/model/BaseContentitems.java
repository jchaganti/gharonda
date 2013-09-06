package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the contentitems table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="contentitems"
 */

public abstract class BaseContentitems implements Serializable {

	public static String REF = "Contentitems";
	public static String PROP_DATA = "Data";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";

	// constructors
	public BaseContentitems() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseContentitems(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String data;

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
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Return the value associated with the column: data
	 */
	public java.lang.String getData() {
		return data;
	}

	/**
	 * Set the value related to the column: data
	 * @param data the data value
	 */
	public void setData(java.lang.String data) {
		this.data = data;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Contentitems))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Contentitems contentitems = (org.jchlabs.gharonda.domain.model.Contentitems) obj;
			if (null == this.getId() || null == contentitems.getId())
				return false;
			else
				return (this.getId().equals(contentitems.getId()));
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