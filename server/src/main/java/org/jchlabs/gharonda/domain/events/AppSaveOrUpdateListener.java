package org.jchlabs.gharonda.domain.events;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.SaveOrUpdateEvent;
import org.jchlabs.gharonda.common.Utils;
import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.model.Notifierprofiles;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;
import org.jchlabs.gharonda.search.SearchService;


public class AppSaveOrUpdateListener implements PostInsertEventListener, PostUpdateEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static EmailfrequenciesDAO emailDAO = org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO.getInstance();

	public void onSaveOrUpdate(SaveOrUpdateEvent arg0) throws HibernateException {
		Object obj = arg0.getObject();
		if (obj instanceof Notifierprofiles) {
			postSaveOrUpdateNotifierProfile((Notifierprofiles) obj);
		} else if (obj instanceof Properties) {
			postSaveOrUpdateproperties((Properties) obj);
		}
	}

	private void postSaveOrUpdateproperties(Properties property) {
		List<Emailfrequencies> frequencies = emailDAO.findAll();
		for (Emailfrequencies frequency : frequencies) {
			byte[] bytes = frequency.getPropertyIds();
			bytes = property.getIsactive().equals(1) ? Utils.addId(bytes, property.getId()) : Utils.removeId(bytes,
					property.getId());
			if (bytes != null) {
				frequency.setPropertyIds(bytes);
				emailDAO.saveOrUpdate(frequency);
			}
		}
	}

	private void postSaveOrUpdateNotifierProfile(Notifierprofiles profile) {
		Integer freqId = profile.getFrequency();
		Users user = AppDeleteEventListener.findUser(profile);
		if (freqId == null) {
			freqId = user.getNotifierprofiles().getFrequency();
		}
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		NumberSearchCriteria criteria = new NumberSearchCriteria("FrequencyType", freqId);
		cList.add(criteria);
		SearchService service = new SearchService(Emailfrequencies.class,
				(org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO) emailDAO);
		List<Object> freqList = service.search(cList);
		Emailfrequencies frequency = null;
		if (freqList != null && freqList.size() > 0) {
			frequency = (Emailfrequencies) freqList.get(0);
		} else {
			frequency = new Emailfrequencies();
		}

		if (frequency != null) {
			byte[] bytes = frequency.getUserIds();
			bytes = Utils.addId(bytes, user.getId());
			if (bytes != null) {
				frequency.setUserIds(bytes);
				emailDAO.saveOrUpdate(frequency);
			}
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent arg0) {
		Object obj = arg0.getEntity();
		if (obj instanceof Notifierprofiles) {
			postSaveOrUpdateNotifierProfile((Notifierprofiles) obj);
		} else if (obj instanceof Properties) {
			postSaveOrUpdateproperties((Properties) obj);
		}

	}

	@Override
	public void onPostInsert(PostInsertEvent arg0) {
		Object obj = arg0.getEntity();
		if (obj instanceof Notifierprofiles) {
			postSaveOrUpdateNotifierProfile((Notifierprofiles) obj);
		} else if (obj instanceof Properties) {
			postSaveOrUpdateproperties((Properties) obj);
		}

	}
}
