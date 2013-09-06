package org.jchlabs.gharonda.domain.pom.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;


import org.hibernate.criterion.Order;
import org.jchlabs.gharonda.domain.pom.dao.iface.NeightbourhoodDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseNeightbourhoodDAO extends org.jchlabs.gharonda.domain.pom.dao._RootDAO {

	public BaseNeightbourhoodDAO() {
	}

	public BaseNeightbourhoodDAO(Session session) {
		super(session);
	}

	// query name references

	public static NeightbourhoodDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static NeightbourhoodDAO getInstance() {
		if (null == instance)
			instance = new org.jchlabs.gharonda.domain.pom.dao.NeightbourhoodDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return org.jchlabs.gharonda.domain.model.Neightbourhood.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	/**
	 * Cast the object as a com.sahibidenev.domain.model.Neightbourhood
	 */
	public org.jchlabs.gharonda.domain.model.Neightbourhood cast(Object object) {
		return (org.jchlabs.gharonda.domain.model.Neightbourhood) object;
	}

	public org.jchlabs.gharonda.domain.model.Neightbourhood get(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Neightbourhood) get(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Neightbourhood get(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Neightbourhood) get(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Neightbourhood load(java.lang.Integer key) {
		return (org.jchlabs.gharonda.domain.model.Neightbourhood) load(getReferenceClass(), key);
	}

	public org.jchlabs.gharonda.domain.model.Neightbourhood load(java.lang.Integer key, Session s) {
		return (org.jchlabs.gharonda.domain.model.Neightbourhood) load(getReferenceClass(), key, s);
	}

	public org.jchlabs.gharonda.domain.model.Neightbourhood loadInitialize(java.lang.Integer key, Session s) {
		org.jchlabs.gharonda.domain.model.Neightbourhood obj = load(key, s);
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		}
		return obj;
	}

	/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Neightbourhood> findAll() {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Neightbourhood> findAll(Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter. Use the session given.
	 * @param s the Session
	 */
	public java.util.List<org.jchlabs.gharonda.domain.model.Neightbourhood> findAll(Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.)
	 * @param neightbourhood a transient instance of a persistent class
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood) {
		return (java.lang.Integer) super.save(neightbourhood);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.) Use the Session given.
	 * @param neightbourhood a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood, Session s) {
		return (java.lang.Integer) save((Object) neightbourhood, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * @param neightbourhood a transient instance containing new or updated state
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood) {
		saveOrUpdate((Object) neightbourhood);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. Use the Session given.
	 * @param neightbourhood a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood, Session s) {
		saveOrUpdate((Object) neightbourhood, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param neightbourhood a transient instance containing updated state
	 */
	public void update(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood) {
		update((Object) neightbourhood);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session. Use the Session given.
	 * @param neightbourhood a transient instance containing updated state
	 * @param the Session
	 */
	public void update(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood, Session s) {
		update((Object) neightbourhood, s);
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
	 * @param neightbourhood the instance to be removed
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood) {
		delete((Object) neightbourhood);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. Use the Session
	 * given.
	 * @param neightbourhood the instance to be removed
	 * @param s the Session
	 */
	public void delete(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood, Session s) {
		delete((Object) neightbourhood, s);
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
	public void refresh(org.jchlabs.gharonda.domain.model.Neightbourhood neightbourhood, Session s) {
		refresh((Object) neightbourhood, s);
	}

}