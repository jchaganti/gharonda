package org.jchlabs.gharonda.domain.pom.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;


import org.hibernate.criterion.Order;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseContentitemsDAO extends org.jchlabs.gharonda.domain.pom.dao._RootDAO {

	public BaseContentitemsDAO() {
	}

	public BaseContentitemsDAO(Session session) {
		super(session);
	}

	// query name references

	public static ContentitemsDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static ContentitemsDAO getInstance() {
		if (null == instance)
			instance = new org.jchlabs.gharonda.domain.pom.dao.ContentitemsDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return org.jchlabs.gharonda.domain.model.Contentitems.class;
	}

	public Order getDefaultOrder() {
		return Order.asc("Name");
	}

	/**
	 * Cast the object as a com.sahibidenev.domain.model.Contentitems
	 */
	public org.jchlabs.gharonda.domain.model.Contentitems cast(Object object) {
		return (org.jchlabs.gharonda.domain.model.Contentitems) object;
	}

	public org.jchlabs.gharonda.domain.model.Contentitems get(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Contentitems) get(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Contentitems get(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Contentitems) get(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Contentitems load(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Contentitems) load(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Contentitems load(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Contentitems) load(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Contentitems loadInitialize(java.lang.Integer key, Session s) {
		org.jchlabs.gharonda.domain.model.Contentitems obj = load(key, s);
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		}
		return obj;
	}

	/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Contentitems> findAll() {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Contentitems> findAll(Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter. Use the session given.
	 * @param s the Session
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Contentitems> findAll(Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.)
	 * @param contentitems a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Contentitems contentitems) {
		return (java.lang.Integer) super.save(contentitems);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.) Use the Session given.
	 * @param contentitems a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Contentitems contentitems, Session s) {
		return (java.lang.Integer) save((Object) contentitems, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param contentitems a transient instance containing new or updated state
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Contentitems contentitems) {
		saveOrUpdate((Object) contentitems);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. Use the Session given.
	 * @param contentitems a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Contentitems contentitems, Session s) {
		saveOrUpdate((Object) contentitems, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param contentitems a transient instance containing updated state
	 */
	public void update(org.jchlabs.gharonda.domain.model.Contentitems contentitems) {
		update((Object) contentitems);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session. Use the Session given.
	 * @param contentitems a transient instance containing updated state
	 * @param the Session
	 */
	public void update(org.jchlabs.gharonda.domain.model.Contentitems contentitems, Session s) {
		update((Object) contentitems, s);
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
	 * @param contentitems the instance to be removed
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Contentitems contentitems) {
		delete((Object) contentitems);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. Use the Session
	 * given.
	 * @param contentitems the instance to be removed
	 * @param s the Session
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Contentitems contentitems, Session s) {
		delete((Object) contentitems, s);
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
	public void refresh(org.jchlabs.gharonda.domain.model.Contentitems contentitems, Session s) {
		refresh((Object) contentitems, s);
	}

}