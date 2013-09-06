package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the contentholder table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="contentholder"
 */

public abstract class BaseContentholder implements Serializable {

	public static String REF = "Contentholder";
	public static String PROP_USER = "User";
	public static String PROP_NAME = "Name";
	public static String PROP_TIME_STAMP = "TimeStamp";
	public static String PROP_RESERVED_INT = "ReservedInt";
	public static String PROP_CREATED = "Created";
	public static String PROP_OTHER_SIDE_ID = "OtherSideId";
	public static String PROP_ID = "Id";
	public static String PROP_WIP_STATE = "WipState";
	public static String PROP_RESERVED_STR = "ReservedStr";

	// constructors
	public BaseContentholder() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseContentholder(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Long created;
	private java.lang.Long timeStamp;
	private java.lang.String name;
	private java.lang.Integer wipState;
	private java.lang.Integer otherSideId;
	private java.lang.Integer reservedInt;
	private java.lang.String reservedStr;

	// many to one
	private org.jchlabs.gharonda.domain.model.Users user;

	// collections
	private java.util.Set<org.jchlabs.gharonda.domain.model.Contentitems> contentitems;

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
	 * Return the value associated with the column: created
	 */
	public java.lang.Long getCreated() {
		return created;
	}

	/**
	 * Set the value related to the column: created
	 * @param created the created value
	 */
	public void setCreated(java.lang.Long created) {
		this.created = created;
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
	 * Return the value associated with the column: wipState
	 */
	public java.lang.Integer getWipState() {
		return wipState;
	}

	/**
	 * Set the value related to the column: wipState
	 * @param wipState the wipState value
	 */
	public void setWipState(java.lang.Integer wipState) {
		this.wipState = wipState;
	}

	/**
	 * Return the value associated with the column: otherSideId
	 */
	public java.lang.Integer getOtherSideId() {
		return otherSideId;
	}

	/**
	 * Set the value related to the column: otherSideId
	 * @param otherSideId the otherSideId value
	 */
	public void setOtherSideId(java.lang.Integer otherSideId) {
		this.otherSideId = otherSideId;
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

	/**
	 * Return the value associated with the column: userId
	 */
	public org.jchlabs.gharonda.domain.model.Users getUser() {
		return user;
	}

	/**
	 * Set the value related to the column: userId
	 * @param user the userId value
	 */
	public void setUser(org.jchlabs.gharonda.domain.model.Users user) {
		this.user = user;
	}

	/**
	 * Return the value associated with the column: contentitems
	 */
	public java.util.Set<org.jchlabs.gharonda.domain.model.Contentitems> getContentitems() {
		return contentitems;
	}

	/**
	 * Set the value related to the column: contentitems
	 * @param contentitems the contentitems value
	 */
	public void setContentitems(java.util.Set<org.jchlabs.gharonda.domain.model.Contentitems> contentitems) {
		this.contentitems = contentitems;
	}

	public void addTocontentitems(org.jchlabs.gharonda.domain.model.Contentitems contentitems) {
		if (null == getContentitems())
			setContentitems(new java.util.TreeSet<org.jchlabs.gharonda.domain.model.Contentitems>());
		getContentitems().add(contentitems);
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Contentholder))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Contentholder contentholder = (org.jchlabs.gharonda.domain.model.Contentholder) obj;
			if (null == this.getId() || null == contentholder.getId())
				return false;
			else
				return (this.getId().equals(contentholder.getId()));
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