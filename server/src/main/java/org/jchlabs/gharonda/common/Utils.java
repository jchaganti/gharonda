package org.jchlabs.gharonda.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jchlabs.gharonda.domain.model.BaseProperties;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;


public class Utils {
	private static Set<String> attributesOfInterest = new HashSet<String>();

	static {
		attributesOfInterest.add(BaseProperties.PROP_STATE);
		attributesOfInterest.add(BaseProperties.PROP_CITY);
		attributesOfInterest.add(BaseProperties.PROP_SUBURB);
		attributesOfInterest.add(BaseProperties.PROP_IS_FEATURED);
	}

	public static boolean removeCriteria(String attrName, List<SearchCriteriaIFace> cList) {
		boolean removed = false;
		if (cList != null) {
			Iterator<SearchCriteriaIFace> it = cList.iterator();
			while (it.hasNext()) {
				SearchCriteriaIFace sc = it.next();
				if (sc.getSearchAttributeName().equals(attrName)) {
					it.remove();
					removed = true;
					break;
				}
			}
		}
		return removed;
	}

	public static void adjustCriteria(List<SearchCriteriaIFace> cList) {
		if (cList != null) {
			Iterator<SearchCriteriaIFace> it = cList.iterator();
			while (it.hasNext()) {
				SearchCriteriaIFace sc = it.next();
				if (!attributesOfInterest.contains(sc.getSearchAttributeName())) {
					it.remove();
				}
			}
		}
	}

	public static byte[] removeId(byte[] in, Integer id) {
		byte[] out = null;
		if (in != null) {
			String[] idArr = (new String(in)).split(",");
			List<String> idList = new ArrayList<String>(Arrays.asList(idArr));
			if (idList.remove(id.toString()) && idList.size() > 0) {
				String idStr = idList.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").trim();
				out = idStr.getBytes();
			}
		}
		return out;
	}

	public static byte[] addId(byte[] in, Integer id) {
		String idStr = null;
		if (in != null) {
			idStr = new String(in);
			String[] idArr = (new String(in)).split(",");
			List<String> idList = new ArrayList<String>(Arrays.asList(idArr));
			if (idList.contains(id.toString())) {
				return null;
			} else {
				idList.add(id.toString());
				idStr = idList.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").trim();
			}
		} else {
			idStr = id.toString();
		}
		return idStr.getBytes();
	}
}
