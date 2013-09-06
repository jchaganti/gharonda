package org.jchlabs.gharonda.search;

import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Users;


public interface SearchServiceIFace {

	/*
	 * Search from the DB for a given SearchCriteria
	 */
	public List<Object> search(List<SearchCriteriaIFace> cList);

	/*
	 * Search from the list of Objects for a given SearchCriteria
	 */
	public List<Object> search(List<Object> list, List<SearchCriteriaIFace> cList);

	/*
	 * Search from the DB for a given SearchCriteria and for a given user
	 */
	public List<Object> search(List<SearchCriteriaIFace> cList, Users user);

	/*
	 * Search from the list of Objects for a given SearchCriteria
	 */
	public List<Object> search(List<Object> list, List<SearchCriteriaIFace> cList, Users user);

}
