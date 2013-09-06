package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the emailfrequencies table. Do not modify this class because it will
 * be overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="emailfrequencies"
 */

public abstract class BaseEmailfrequencies implements Serializable {

	public static String REF = "Emailfrequencies";
	public static String PROP_USER_IDS = "UserIds";
	public static String PROP_PROPERTY_IDS = "PropertyIds";
	public static String PROP_FREQUENCY_TYPE = "FrequencyType";
	public static String PROP_ID = "Id";

	// constructors
	public BaseEmailfrequencies() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseEmailfrequencies(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer frequencyType;
	private byte[] propertyIds;
	private byte[] userIds;

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
	 * Return the value associated with the column: frequencyType
	 */
	public java.lang.Integer getFrequencyType() {
		return frequencyType;
	}

	/**
	 * Set the value related to the column: frequencyType
	 * @param frequencyType the frequencyType value
	 */
	public void setFrequencyType(java.lang.Integer frequencyType) {
		this.frequencyType = frequencyType;
	}

	/**
	 * Return the value associated with the column: propertyIds
	 */
	public byte[] getPropertyIds() {
		return propertyIds;
	}

	/**
	 * Set the value related to the column: propertyIds
	 * @param propertyIds the propertyIds value
	 */
	public void setPropertyIds(byte[] propertyIds) {
		this.propertyIds = propertyIds;
	}

	/**
	 * Return the value associated with the column: userIds
	 */
	public byte[] getUserIds() {
		return userIds;
	}

	/**
	 * Set the value related to the column: userIds
	 * @param userIds the userIds value
	 */
	public void setUserIds(byte[] userIds) {
		this.userIds = userIds;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Emailfrequencies))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Emailfrequencies emailfrequencies = (org.jchlabs.gharonda.domain.model.Emailfrequencies) obj;
			if (null == this.getId() || null == emailfrequencies.getId())
				return false;
			else
				return (this.getId().equals(emailfrequencies.getId()));
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