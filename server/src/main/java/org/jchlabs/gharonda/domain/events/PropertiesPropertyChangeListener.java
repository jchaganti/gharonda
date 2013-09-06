package org.jchlabs.gharonda.domain.events;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.util.StringHelper;
import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;


public class PropertiesPropertyChangeListener implements PropertyChangeListener {
	private static EmailfrequenciesDAO emailDAO = org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO.getInstance();

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Isactive")) {
			Object obj = evt.getSource();
			if (obj instanceof Properties) {
				Properties p = (Properties) obj;
				Integer oldVal = (Integer) evt.getOldValue();
				Integer newVal = (Integer) evt.getNewValue();
				boolean activate = oldVal.intValue() == 0 && newVal.intValue() == 1;
				List<Emailfrequencies> frequencies = emailDAO.findAll();
				for (Emailfrequencies frequency : frequencies) {
					byte[] bytes = frequency.getPropertyIds();
					bytes = (activate) ? addProperty(bytes, p) : removeProperty(bytes, p);
					frequency.setPropertyIds(bytes);
					emailDAO.saveOrUpdate(frequency);
				}
			}
		}
	}

	private byte[] addProperty(byte[] in, Properties p) {
		String idStr = null;
		if (in != null) {
			idStr = new String(in);
			idStr += "," + p.getId().toString();
		} else {
			idStr = p.getId().toString();
		}

		return idStr.getBytes();
	}

	private byte[] removeProperty(byte[] in, Properties p) {
		String[] idArr = (new String(in)).split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idArr));
		idList.removeAll(Arrays.asList(p.getId().toString()));
		idArr = idList.toArray(idArr);
		byte[] out = null;
		if (idArr.length > 0) {
			String idStr = StringHelper.join(",", idArr);
			out = idStr.getBytes();
		}
		return out;
	}
}
