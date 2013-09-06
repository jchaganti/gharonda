package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseImagesDAO;


public class ImagesDAO extends BaseImagesDAO implements org.jchlabs.gharonda.domain.pom.dao.iface.ImagesDAO {

	public ImagesDAO() {
	}

	public ImagesDAO(Session session) {
		super(session);
	}

}