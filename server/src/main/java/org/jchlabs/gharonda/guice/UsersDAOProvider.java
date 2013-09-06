package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.UsersDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class UsersDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO get() {
		return UsersDAO.getInstance();
	}

}
