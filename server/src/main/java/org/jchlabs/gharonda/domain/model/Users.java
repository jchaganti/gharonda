package org.jchlabs.gharonda.domain.model;

public final class Users extends BaseUsers {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Users() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Users(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Users(java.lang.Integer id, java.lang.String email, java.lang.String passwd, java.lang.String firstName,
			java.lang.String lastName) {

		super(id, email, passwd, firstName, lastName);
	}

	/* [CONSTRUCTOR MARKER END] */

}