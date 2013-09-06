package org.jchlabs.gharonda.domain.model;

import org.jchlabs.gharonda.shared.rpc.Utils;

public class StringSearchCriteria extends BaseSearchCriteria implements StringSearchCriteriaIFace {

	private String searchString;

	public StringSearchCriteria() {
		super();
	}

	public StringSearchCriteria(String searchAttributeName, String searchString) {
		super(searchAttributeName);
		this.searchString = searchString;
	}

	public String getSearchString() {
		return searchString;
	}

	public String toString() {
		return Utils.format("CriteriaType=StringSearchCriteria", super.toString(), "searchString=" + searchString);
	}
}
