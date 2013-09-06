package org.jchlabs.gharonda.domain.model;

public interface StringSearchCriteriaIFace extends SearchCriteriaIFace {
	/*
	 * This returns the search string which may contain "*" for the wild card search.
	 */
	public String getSearchString();

}
