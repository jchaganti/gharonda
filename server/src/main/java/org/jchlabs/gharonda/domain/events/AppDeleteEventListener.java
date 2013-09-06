package org.jchlabs.gharonda.domain.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.event.DeleteEvent;
import org.hibernate.event.DeleteEventListener;
import org.jchlabs.gharonda.common.Utils;
import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.model.Notifierprofiles;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO;
import org.jchlabs.gharonda.search.SearchService;


public class AppDeleteEventListener implements DeleteEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static EmailfrequenciesDAO emailDAO = org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO.getInstance();
	private static UsersDAO uDAO = org.jchlabs.gharonda.domain.pom.dao.UsersDAO.getInstance();

	public void onDelete(DeleteEvent arg0) throws HibernateException {
		Object obj = arg0.getObject();
		if (obj instanceof Users) {
			postDeleteUser((Users) obj, null);
		} else if (obj instanceof Properties) {
			postDeleteProperties((Properties) obj);
		} else if (obj instanceof Notifierprofiles) {
			postDeleteNotifierProfile((Notifierprofiles) obj);
		}

	}

	public void onDelete(DeleteEvent arg0, Set arg1) throws HibernateException {
		onDelete(arg0);

	}

	private void postDeleteUser(Users u, Integer freqId) {
		if (freqId == null) {
			freqId = u.getNotifierprofiles().getFrequency();
		}
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		NumberSearchCriteria criteria = new NumberSearchCriteria("Frequency", freqId);
		cList.add(criteria);
		SearchService service = new SearchService(Emailfrequencies.class,
				(org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO) emailDAO);
		List<Object> freqList = service.search(cList);
		Emailfrequencies frequency = null;
		if (freqList != null && freqList.size() > 0) {
			frequency = (Emailfrequencies) freqList.get(0);
		}
		if (frequency != null) {
			byte[] bytes = frequency.getUserIds();
			bytes = Utils.removeId(bytes, u.getId());
			frequency.setUserIds(bytes);
			emailDAO.saveOrUpdate(frequency);
		}
	}

	private void postDeleteProperties(Properties p) {
		List<Emailfrequencies> frequencies = emailDAO.findAll();
		for (Emailfrequencies frequency : frequencies) {
			byte[] bytes = frequency.getPropertyIds();
			bytes = Utils.removeId(bytes, p.getId());
			frequency.setPropertyIds(bytes);
			emailDAO.saveOrUpdate(frequency);
		}
	}

	private void postDeleteNotifierProfile(Notifierprofiles profile) {
		Users user = findUser(profile);
		postDeleteUser(user, profile.getFrequency());

	}

	public static Users findUser(Notifierprofiles profile) {
		Integer profileId = profile.getId();
		Users foundUser = null;
		List<Users> users = uDAO.findAll();
		for (Users user : users) {
			if (user.getNotifierprofiles().getId().intValue() == profileId.intValue()) {
				foundUser = user;
				break;
			}
		}
		return foundUser;
	}
}
