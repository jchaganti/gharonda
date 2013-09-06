package org.jchlabs.gharonda.client.util;

import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;


public class SearchProfile {

	List<SearchCriteriaIFace> searchTextCriteria;
	List<SearchCriteriaIFace> selListCriteria;
	FetchProfile fetchProfile;
	/*
	 * resultsSizeLimit is the results limit size that we need to return to the client. FetchProfile.resultSize is the
	 * the size used by Hibernate for incremental fetch.
	 */
	int resultsSizeLimit = -1;

	public SearchProfile(FetchProfile fetchProfile, List<SearchCriteriaIFace> searchTextCriteria,
			List<SearchCriteriaIFace> selListCriteria) {
		this(fetchProfile, searchTextCriteria, selListCriteria, -1);
	}

	public SearchProfile(FetchProfile fetchProfile, List<SearchCriteriaIFace> searchTextCriteria,
			List<SearchCriteriaIFace> selListCriteria, int resultsSize) {
		super();
		this.fetchProfile = fetchProfile;
		this.searchTextCriteria = searchTextCriteria;
		this.selListCriteria = selListCriteria;
		this.resultsSizeLimit = resultsSize;
	}

	public List<SearchCriteriaIFace> getSearchTextCriteria() {
		return searchTextCriteria;
	}

	public void setSearchTextCriteria(List<SearchCriteriaIFace> searchTextCriteria) {
		this.searchTextCriteria = searchTextCriteria;
	}

	public List<SearchCriteriaIFace> getSelListCriteria() {
		return selListCriteria;
	}

	public void setSelListCriteria(List<SearchCriteriaIFace> selListCriteria) {
		this.selListCriteria = selListCriteria;
	}

	public FetchProfile getFetchProfile() {
		return fetchProfile;
	}

	public void setFetchProfile(FetchProfile fetchProfile) {
		this.fetchProfile = fetchProfile;
	}

	public int getResultsSizeLimit() {
		return resultsSizeLimit;
	}

	public void setResultsSizeLimit(int resultsSize) {
		this.resultsSizeLimit = resultsSize;
	}

}
