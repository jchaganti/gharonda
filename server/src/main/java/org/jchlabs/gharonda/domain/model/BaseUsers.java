package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the users table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="users"
 */

public abstract class BaseUsers implements Serializable {

	public static String REF = "Users";
	public static String PROP_PHONE = "Phone";
	public static String PROP_CELL = "Cell";
	public static String PROP_EMAIL = "Email";
	public static String PROP_SERVICEPROVIDERDETAILS = "Serviceproviderdetails";
	public static String PROP_NOTIFIERPROFILES = "Notifierprofiles";
	public static String PROP_FIRST_NAME = "FirstName";
	public static String PROP_ID = "Id";
	public static String PROP_LAST_NAME = "LastName";
	public static String PROP_PASSWD = "Passwd";
	public static String PROP_EMAIL_UPDATES = "EmailUpdates";

	// constructors
	public BaseUsers() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUsers(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseUsers(java.lang.Integer id, java.lang.String email, java.lang.String passwd, java.lang.String firstName,
			java.lang.String lastName) {

		this.setId(id);
		this.setEmail(email);
		this.setPasswd(passwd);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String email;
	private java.lang.String passwd;
	private java.lang.String firstName;
	private java.lang.String lastName;
	private java.lang.Integer emailUpdates;
	private java.lang.String phone;
	private java.lang.String cell;

	// many to one
	private org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles;
	private org.jchlabs.gharonda.domain.model.Serviceproviderdetails serviceproviderdetails;

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
	 * Return the value associated with the column: email
	 */
	public java.lang.String getEmail() {
		return email;
	}

	/**
	 * Set the value related to the column: email
	 * @param email the email value
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * Return the value associated with the column: passwd
	 */
	public java.lang.String getPasswd() {
		return passwd;
	}

	/**
	 * Set the value related to the column: passwd
	 * @param passwd the passwd value
	 */
	public void setPasswd(java.lang.String passwd) {
		this.passwd = passwd;
	}

	/**
	 * Return the value associated with the column: firstname
	 */
	public java.lang.String getFirstName() {
		return firstName;
	}

	/**
	 * Set the value related to the column: firstname
	 * @param firstName the firstname value
	 */
	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Return the value associated with the column: lastname
	 */
	public java.lang.String getLastName() {
		return lastName;
	}

	/**
	 * Set the value related to the column: lastname
	 * @param lastName the lastname value
	 */
	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Return the value associated with the column: emailUpdates
	 */
	public java.lang.Integer getEmailUpdates() {
		return emailUpdates;
	}

	/**
	 * Set the value related to the column: emailUpdates
	 * @param emailUpdates the emailUpdates value
	 */
	public void setEmailUpdates(java.lang.Integer emailUpdates) {
		this.emailUpdates = emailUpdates;
	}

	/**
	 * Return the value associated with the column: phone
	 */
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * Set the value related to the column: phone
	 * @param phone the phone value
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	 * Return the value associated with the column: cell
	 */
	public java.lang.String getCell() {
		return cell;
	}

	/**
	 * Set the value related to the column: cell
	 * @param cell the cell value
	 */
	public void setCell(java.lang.String cell) {
		this.cell = cell;
	}

	/**
	 * Return the value associated with the column: notifierProfiles_id
	 */
	public org.jchlabs.gharonda.domain.model.Notifierprofiles getNotifierprofiles() {
		return notifierprofiles;
	}

	/**
	 * Set the value related to the column: notifierProfiles_id
	 * @param notifierprofiles the notifierProfiles_id value
	 */
	public void setNotifierprofiles(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles) {
		this.notifierprofiles = notifierprofiles;
	}

	/**
	 * Return the value associated with the column: serviceProviderDetails_id
	 */
	public org.jchlabs.gharonda.domain.model.Serviceproviderdetails getServiceproviderdetails() {
		return serviceproviderdetails;
	}

	/**
	 * Set the value related to the column: serviceProviderDetails_id
	 * @param serviceproviderdetails the serviceProviderDetails_id value
	 */
	public void setServiceproviderdetails(org.jchlabs.gharonda.domain.model.Serviceproviderdetails serviceproviderdetails) {
		this.serviceproviderdetails = serviceproviderdetails;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Users))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Users users = (org.jchlabs.gharonda.domain.model.Users) obj;
			if (null == this.getId() || null == users.getId())
				return false;
			else
				return (this.getId().equals(users.getId()));
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