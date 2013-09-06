package org.jchlabs.gharonda.domain.model;

public class BaseSearchCriteria implements SearchCriteriaIFace {

	private static final long serialVersionUID = 1L;
	protected String searchAttributeName;

	public BaseSearchCriteria() {

	}

	public BaseSearchCriteria(String searchAttributeName) {
		super();
		this.searchAttributeName = searchAttributeName;
	}

	public String getSearchAttributeName() {
		return searchAttributeName;
	}

	public String toString() {
		return "AttributeName=" + searchAttributeName;
	}
}
