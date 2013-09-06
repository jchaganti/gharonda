package org.jchlabs.gharonda.notifier.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jchlabs.gharonda.common.NotifierProfileComparatorConstants;
import org.jchlabs.gharonda.domain.model.BaseEmailfrequencies;
import org.jchlabs.gharonda.domain.model.BaseProperties;
import org.jchlabs.gharonda.domain.model.BitwiseAndSearchCriteria;
import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.model.InValuesNumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.InValuesNumberSearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Notifierprofiles;
import org.jchlabs.gharonda.domain.model.NumberRangeSearchCriteria;
import org.jchlabs.gharonda.domain.model.NumberRangeSearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.StringSearchCriteria;
import org.jchlabs.gharonda.domain.model.StringSearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.PropertiesDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO;
import org.jchlabs.gharonda.search.SearchService;


public abstract class AbstractEmailJobContextDataProviderImpl implements JobContextDataProviderIFace {
	protected static EmailfrequenciesDAO emailDAO = org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO.getInstance();
	protected static PropertiesDAO pDAO = org.jchlabs.gharonda.domain.pom.dao.PropertiesDAO.getInstance();
	protected static UsersDAO uDAO = org.jchlabs.gharonda.domain.pom.dao.UsersDAO.getInstance();

	private EmailPostJobFireCBImpl cb = null;

	public Object getJobData() {
		// JobData is key/value pair where key is the email id and
		// value is the email content.
		// TODO Can defintely use a better framework. Just a temp Content for
		// now
		// TODO Caching the search criterias of the user
		Emailfrequencies freq = getEmailFrequency();
		Map<String, String> jobData = new HashMap<String, String>();
		try {
			if (freq != null) {
				byte[] _ids = freq.getUserIds();
				if (_ids != null) {
					String[] userIds = (new String(_ids)).split(",");
					List<Properties> properties = getProperties(freq);
					List<Object> pObjs = new ArrayList<Object>(properties);
					for (String userId : userIds) {
						Users user = uDAO.get(Integer.parseInt(userId));
						List<SearchCriteriaIFace> cList = generateSearchCriteriaForUser(user);
						SearchService service = new SearchService(Properties.class,
								(org.jchlabs.gharonda.domain.pom.dao.PropertiesDAO) pDAO);
						List<Object> validProperties = service.search(pObjs, cList);
						String emailContent = generateEmailContent(validProperties);
						jobData.put(user.getEmail(), emailContent);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobData;
	}

	public Object getPostJobFireData() {
		return getEmailFrequency();
	}

	public PostJobFireCBIFace getPostJobFireDataCB() {
		if (cb == null) {
			cb = new EmailPostJobFireCBImpl();
		}

		return cb;
	}

	protected Emailfrequencies getEmailFrequency() {
		Integer id = getFrequencyId();
		Emailfrequencies freq = null;
		if (id != null) {
			List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
			NumberSearchCriteriaIFace c = new NumberSearchCriteria(BaseEmailfrequencies.PROP_FREQUENCY_TYPE, id);
			cList.add(c);
			SearchService service = new SearchService(Emailfrequencies.class,
					(org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO) emailDAO);
			List<Object> frequencies = service.search(cList);
			if (frequencies.size() > 0) {
				freq = (Emailfrequencies) frequencies.get(0);
			}
		}
		return freq;
	}

	abstract protected Integer getFrequencyId();

	private void addToAmenitySearchCriteria(String amenityStr, Integer amenity, List<SearchCriteriaIFace> list) {
		if (amenity != null && amenity.intValue() > 0) {
			list.add(new BitwiseAndSearchCriteria(amenityStr, amenity.intValue()));
		}

	}

	private void addToInValuesSearchCriteriaList(String criteriaName, String criteriaStr,
			List<SearchCriteriaIFace> cList) {
		if (criteriaStr != null) {
			Integer[] criteriaValues = toIntegerArray(criteriaStr);
			InValuesNumberSearchCriteriaIFace c = new InValuesNumberSearchCriteria(criteriaName, criteriaValues);
			cList.add(c);
		}
	}

	private void addToNumberSearchCriteriaList(String criteriaName, Integer lVal, Integer rVal, Integer comparator,
			List<SearchCriteriaIFace> cList) {
		if (NotifierProfileComparatorConstants.EXACTLY.equals(comparator) && lVal != null) {
			NumberSearchCriteriaIFace c = new NumberSearchCriteria(criteriaName, lVal);
			cList.add(c);
		} else if (NotifierProfileComparatorConstants.GREATER_OR_EQUAL.equals(comparator)) {
			NumberRangeSearchCriteriaIFace c = new NumberRangeSearchCriteria(criteriaName, true, lVal, null);
			cList.add(c);
		} else if (NotifierProfileComparatorConstants.LESSER_OR_EQUAL.equals(comparator)) {
			NumberRangeSearchCriteriaIFace c = new NumberRangeSearchCriteria(criteriaName, true, null, lVal);
			cList.add(c);
		} else if (NotifierProfileComparatorConstants.BETWEEN.equals(comparator)) {
			NumberRangeSearchCriteriaIFace c = new NumberRangeSearchCriteria(criteriaName, true, lVal, rVal);
			cList.add(c);
		}
	}

	private void addToSearchCriteriaList(String criteriaName, String criteriaStr, List<SearchCriteriaIFace> cList) {
		if (criteriaStr != null && !criteriaStr.isEmpty()) {
			StringSearchCriteriaIFace c = new StringSearchCriteria(criteriaName, criteriaStr);
			cList.add(c);
		}
	}

	private String generateEmailContent(List<Object> validProperties) {
		String content = null;
		if (validProperties.size() > 0) {
			StringBuffer sb = new StringBuffer(1024);
			sb.append("The following new properties have come up \n");
			for (Object o : validProperties) {
				Properties p = (Properties) o;
				sb.append("The property with id = " + p.getId() + "\n");
			}
			content = sb.toString();
		}
		return content;
	}

	private List<SearchCriteriaIFace> generateSearchCriteriaForUser(Users user) {
		/*
		 * RVal/LVal - Sft, RoomNo, BuildDate, Price LIKE - Suburb, State, City ("a%c") IN - Ptype, Amenities
		 * ("1,2,3,4") Comparators - A to B (A <= x <= B) A exactly (x = A) A and less (x <= A) A and greater (x >= A)
		 */
		Notifierprofiles profile = user.getNotifierprofiles();
		List<SearchCriteriaIFace> cList = null;
		if (profile != null) {
			cList = new ArrayList<SearchCriteriaIFace>();
			// Add Property Types
			// addToInValuesSearchCriteriaList(BaseProperties.PROP_P_TYPE,
			// profile
			// .getPType(), cList);
			addToNumberSearchCriteriaList(BaseProperties.PROP_P_TYPE, profile.getPType(), null,
					NotifierProfileComparatorConstants.EXACTLY, cList);

			addToAmenitySearchCriteria("amenity1", profile.getAmenity1(), cList);
			addToAmenitySearchCriteria("amenity2", profile.getAmenity2(), cList);
			addToAmenitySearchCriteria("amenity3", profile.getAmenity3(), cList);
			addToAmenitySearchCriteria("amenity4", profile.getAmenity4(), cList);

			// Add Suburb
			addToSearchCriteriaList(BaseProperties.PROP_SUBURB, profile.getSuburb(), cList);

			// Add State
			addToSearchCriteriaList(BaseProperties.PROP_STATE, profile.getState(), cList);

			// Add City
			addToSearchCriteriaList(BaseProperties.PROP_CITY, profile.getCity(), cList);

			// Add Sft
			addToNumberSearchCriteriaList(BaseProperties.PROP_SQRFT, profile.getSftLVal(), profile.getSftRVal(),
					profile.getSftComparator(), cList);

			// Add BedRooms
			addToNumberSearchCriteriaList(BaseProperties.PROP_BED_ROOMS, profile.getRoomNoLVal(),
					profile.getRoomNoRVal(), profile.getRoomNoComparator(), cList);

			// Add BuildDate
			/*
			 * addToNumberSearchCriteriaList(BaseProperties.PROP_BUILD_DATE, profile.getBuildDateLVal(),
			 * profile.getBuildDateRVal(), profile.getBuildDateComparator(), cList);
			 */

			// Add Price
			addToNumberSearchCriteriaList(BaseProperties.PROP_PRICE, profile.getBuildDateLVal(),
					profile.getBuildDateRVal(), profile.getBuildDateComparator(), cList);
		} else {
			System.out.println("Invalid profile");
		}
		return cList;
	}

	private List<Properties> getProperties(Emailfrequencies freq) {
		List<Properties> result = new ArrayList<Properties>();
		byte[] _ids = freq.getPropertyIds();
		if (_ids != null) {
			String[] propertyIds = (new String(_ids)).split(",");
			for (String propertyId : propertyIds) {
				Integer id = Integer.parseInt(propertyId);
				Properties p = pDAO.get(id);
				result.add(p);
			}
		}
		return result;
	}

	private Integer[] toIntegerArray(String _in) {
		String[] in = _in.split(",");
		Integer[] out = new Integer[in.length];
		int i = 0;
		for (String s : in) {
			out[i++] = Integer.parseInt(s);
		}
		return out;
	}

}
