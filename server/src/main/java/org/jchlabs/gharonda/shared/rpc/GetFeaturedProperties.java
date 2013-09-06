package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;

import com.gwtplatform.dispatch.shared.Action;

public class GetFeaturedProperties implements Action<GetFeaturedPropertiesResult> {

	private static final long serialVersionUID = 2255166545722718095L;
	private List<SearchCriteriaIFace> ANDSearchCriteria;
	private List<SearchCriteriaIFace> ORSearchCriteria;
	private FeaturedListingType featuredListingType;
	private FetchProfile fetchProfile;

	public GetFeaturedProperties() {
	}

	public GetFeaturedProperties(List<SearchCriteriaIFace> searchCriteria, List<SearchCriteriaIFace> searchCriteria2,
			FeaturedListingType featuredListingType, FetchProfile fetchProfile) {

		ANDSearchCriteria = searchCriteria;
		ORSearchCriteria = searchCriteria2;
		this.featuredListingType = featuredListingType;
		this.fetchProfile = fetchProfile;
	}

	public List<SearchCriteriaIFace> getANDSearchCriteria() {
		return ANDSearchCriteria;
	}

	public List<SearchCriteriaIFace> getORSearchCriteria() {
		return ORSearchCriteria;
	}

	public FeaturedListingType getFeaturedListingType() {
		return featuredListingType;
	}

	public FetchProfile getFetchProfile() {
		return fetchProfile;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetFeaturedProperties";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
