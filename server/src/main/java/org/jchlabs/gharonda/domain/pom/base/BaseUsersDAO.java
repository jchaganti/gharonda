package org.jchlabs.gharonda.domain.pom.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;


import org.hibernate.criterion.Order;
import org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseUsersDAO extends org.jchlabs.gharonda.domain.pom.dao._RootDAO {

	public BaseUsersDAO() {
	}

	public BaseUsersDAO(Session session) {
		super(session);
	}

	// query name references

	public static UsersDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static UsersDAO getInstance() {
		if (null == instance)
			instance = new org.jchlabs.gharonda.domain.pom.dao.UsersDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return org.jchlabs.gharonda.domain.model.Users.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	/**
	 * Cast the object as a com.sahibidenev.domain.model.Users
	 */
	public org.jchlabs.gharonda.domain.model.Users cast(Object object) {
		return (org.jchlabs.gharonda.domain.model.Users) object;
	}

	public org.jchlabs.gharonda.domain.model.Users get(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Users) get(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Users get(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Users) get(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Users load(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Users) load(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Users load(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Users) load(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Users loadInitialize(java.lang.Integer key, Session s) {
		org.jchlabs.gharonda.domain.model.Users obj = load(key, s);
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		}
		return obj;
	}

	/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Users> findAll() {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Users> findAll(Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter. Use the session given.
	 * @param s the Session
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Users> findAll(Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.)
	 * @param users a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Users users) {
		return (java.lang.Integer) super.save(users);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.) Use the Session given.
	 * @param users a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Users users, Session s) {
		return (java.lang.Integer) save((Object) users, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param users a transient instance containing new or updated state
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Users users) {
		saveOrUpdate((Object) users);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. Use the Session given.
	 * @param users a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Users users, Session s) {
		saveOrUpdate((Object) users, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param users a transient instance containing updated state
	 */
	public void update(org.jchlabs.gharonda.domain.model.Users users) {
		update((Object) users);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session. Use the Session given.
	 * @param users a transient instance containing updated state
	 * @param the Session
	 */
	public void update(org.jchlabs.gharonda.domain.model.Users users, Session s) {
		update((Object) users, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Integer id) {
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. Use the Session
	 * given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.Integer id, Session s) {
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 * @param users the instance to be removed
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Users users) {
		delete((Object) users);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. Use the Session
	 * given.
	 * @param users the instance to be removed
	 * @param s the Session
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Users users, Session s) {
		delete((Object) users, s);
	}

	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special
	 * circumstances. For example
	 * <ul>
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh(org.jchlabs.gharonda.domain.model.Users users, Session s) {
		refresh((Object) users, s);
	}

}