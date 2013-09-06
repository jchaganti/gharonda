package org.jchlabs.gharonda.notifier.data;

import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;

public class EmailPostJobFireCBImpl implements PostJobFireCBIFace {

	protected static EmailfrequenciesDAO emailDAO = org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO.getInstance();

	public void callback(Object data) {
		if (data instanceof Emailfrequencies) {
			// Purge the PropertyIds
			Emailfrequencies freq = (Emailfrequencies) data;
			freq.setPropertyIds(null);
			emailDAO.saveOrUpdate(freq);

		}

	}

}
