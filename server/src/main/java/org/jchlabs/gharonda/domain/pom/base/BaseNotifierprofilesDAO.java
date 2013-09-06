package org.jchlabs.gharonda.domain.pom.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;


import org.hibernate.criterion.Order;
import org.jchlabs.gharonda.domain.pom.dao.iface.NotifierprofilesDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseNotifierprofilesDAO extends org.jchlabs.gharonda.domain.pom.dao._RootDAO {

	public BaseNotifierprofilesDAO() {
	}

	public BaseNotifierprofilesDAO(Session session) {
		super(session);
	}

	// query name references

	public static NotifierprofilesDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static NotifierprofilesDAO getInstance() {
		if (null == instance)
			instance = new org.jchlabs.gharonda.domain.pom.dao.NotifierprofilesDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return org.jchlabs.gharonda.domain.model.Notifierprofiles.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	/**
	 * Cast the object as a com.sahibidenev.domain.model.Notifierprofiles
	 */
	public org.jchlabs.gharonda.domain.model.Notifierprofiles cast(Object object) {
		return (org.jchlabs.gharonda.domain.model.Notifierprofiles) object;
	}

	public org.jchlabs.gharonda.domain.model.Notifierprofiles get(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Notifierprofiles) get(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Notifierprofiles get(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Notifierprofiles) get(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Notifierprofiles load(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Notifierprofiles) load(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Notifierprofiles load(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Notifierprofiles) load(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Notifierprofiles loadInitialize(java.lang.Integer key, Session s) {
		org.jchlabs.gharonda.domain.model.Notifierprofiles obj = load(key, s);
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		}
		return obj;
	}

	/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Notifierprofiles> findAll() {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Notifierprofiles> findAll(Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter. Use the session given.
	 * @param s the Session
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Notifierprofiles> findAll(Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.)
	 * @param notifierprofiles a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles) {
		return (java.lang.Integer) super.save(notifierprofiles);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.) Use the Session given.
	 * @param notifierprofiles a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles, Session s) {
		return (java.lang.Integer) save((Object) notifierprofiles, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param notifierprofiles a transient instance containing new or updated state
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles) {
		saveOrUpdate((Object) notifierprofiles);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. Use the Session given.
	 * @param notifierprofiles a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles, Session s) {
		saveOrUpdate((Object) notifierprofiles, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param notifierprofiles a transient instance containing updated state
	 */
	public void update(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles) {
		update((Object) notifierprofiles);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session. Use the Session given.
	 * @param notifierprofiles a transient instance containing updated state
	 * @param the Session
	 */
	public void update(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles, Session s) {
		update((Object) notifierprofiles, s);
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
	 * @param notifierprofiles the instance to be removed
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles) {
		delete((Object) notifierprofiles);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. Use the Session
	 * given.
	 * @param notifierprofiles the instance to be removed
	 * @param s the Session
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles, Session s) {
		delete((Object) notifierprofiles, s);
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
	public void refresh(org.jchlabs.gharonda.domain.model.Notifierprofiles notifierprofiles, Session s) {
		refresh((Object) notifierprofiles, s);
	}

}