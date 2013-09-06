package org.jchlabs.gharonda.shared.rpc;

import java.io.Serializable;
import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;


public interface FeaturedListingProfileIF extends Serializable {

	List<SearchCriteriaIFace> getANDSearchCriteria();

	List<SearchCriteriaIFace> getORSearchCriteria();

	FeaturedListingType getFeaturedListingType();

	FetchProfile getFetchProfile();
}
