package org.jchlabs.gharonda.client.util;

import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingProfileIF;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingType;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;


public class SearchFeaturedListing implements FeaturedListingProfileIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<SearchCriteriaIFace> orSC;
	List<SearchCriteriaIFace> andSC;
	FetchProfile fp;

	public SearchFeaturedListing() {

	}

	public SearchFeaturedListing(List<SearchCriteriaIFace> sc, List<SearchCriteriaIFace> sc1, FetchProfile fp) {
		this.orSC = sc;
		this.andSC = sc1;
		this.fp = fp;
	}

	@Override
	public FeaturedListingType getFeaturedListingType() {
		return FeaturedListingType.SEARCH;
	}

	@Override
	public List<SearchCriteriaIFace> getANDSearchCriteria() {
		return orSC;
	}

	@Override
	public List<SearchCriteriaIFace> getORSearchCriteria() {
		return andSC;
	}

	@Override
	public FetchProfile getFetchProfile() {
		return fp;
	}
}
