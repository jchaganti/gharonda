package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseServiceproviderdetailsDAO;


public class ServiceproviderdetailsDAO extends BaseServiceproviderdetailsDAO implements
		org.jchlabs.gharonda.domain.pom.dao.iface.ServiceproviderdetailsDAO {

	public ServiceproviderdetailsDAO() {
	}

	public ServiceproviderdetailsDAO(Session session) {
		super(session);
	}

}