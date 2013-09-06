package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the favorites table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="favorites"
 */

public abstract class BaseFavorites implements Serializable {

	public static String REF = "Favorites";
	public static String PROP_ID = "Id";
	public static String PROP_USER_ID = "UserId";
	public static String PROP_PID = "Pid";

	// constructors
	public BaseFavorites() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseFavorites(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer userId;
	private java.lang.Integer pid;

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
	 * Return the value associated with the column: userId
	 */
	public java.lang.Integer getUserId() {
		return userId;
	}

	/**
	 * Set the value related to the column: userId
	 * @param userId the userId value
	 */
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	/**
	 * Return the value associated with the column: pid
	 */
	public java.lang.Integer getPid() {
		return pid;
	}

	/**
	 * Set the value related to the column: pid
	 * @param pid the pid value
	 */
	public void setPid(java.lang.Integer pid) {
		this.pid = pid;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Favorites))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Favorites favorites = (org.jchlabs.gharonda.domain.model.Favorites) obj;
			if (null == this.getId() || null == favorites.getId())
				return false;
			else
				return (this.getId().equals(favorites.getId()));
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