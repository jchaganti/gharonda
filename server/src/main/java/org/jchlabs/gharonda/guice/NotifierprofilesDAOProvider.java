package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.NotifierprofilesDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class NotifierprofilesDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.NotifierprofilesDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.NotifierprofilesDAO get() {
		return NotifierprofilesDAO.getInstance();
	}

}
