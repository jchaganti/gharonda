package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;

public abstract class _RootDAO extends org.jchlabs.gharonda.domain.pom.base._BaseRootDAO {

	public _RootDAO() {
	}

	public _RootDAO(Session session) {
		setSession(session);
	}

	/*
	 * If you are using lazy loading, uncomment this Somewhere, you should call RootDAO.closeCurrentThreadSessions();
	 * public void closeSession (Session session) { // do nothing here because the session will be closed later }
	 */

	/*
	 * If you are pulling the SessionFactory from a JNDI tree, uncomment this protected SessionFactory
	 * getSessionFactory(String configFile) { // If you have a single session factory, ignore the configFile parameter
	 * // Otherwise, you can set a meta attribute under the class node called "config-file" which // will be passed in
	 * here so you can tell what session factory an individual mapping file // belongs to return (SessionFactory) new
	 * InitialContext().lookup("java:/{SessionFactoryName}"); }
	 */
}