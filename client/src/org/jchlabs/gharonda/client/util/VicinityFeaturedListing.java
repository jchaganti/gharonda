package org.jchlabs.gharonda.client.util;

import java.util.ArrayList;
import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingProfileIF;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingType;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;


public class VicinityFeaturedListing implements FeaturedListingProfileIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double lng;
	private Double lat;
	private Double range;

	public VicinityFeaturedListing() {

	}

	public VicinityFeaturedListing(Double lat, Double lng, Double range) {
		this.lat = lat;
		this.lng = lng;
		this.range = range;
	}

	@Override
	public List<SearchCriteriaIFace> getANDSearchCriteria() {
		List<SearchCriteriaIFace> cList = null;
		if (lng != null && lat != null && range != null) {
			Double rangeDeg = .0025 * range;
			Double maxLat = lat + rangeDeg;
			Double minLat = lat - rangeDeg;
			Double maxLng = lng + rangeDeg;
			Double minLng = lng - rangeDeg;

			cList = new ArrayList<SearchCriteriaIFace>();
			cList.add(PropertyOptions.getLatRangeSearchCriteria(minLat.doubleValue(), maxLat.doubleValue()));
			cList.add(PropertyOptions.getLngRangeSearchCriteria(minLng.doubleValue(), maxLng.doubleValue()));
		}

		return cList;
	}

	@Override
	public FeaturedListingType getFeaturedListingType() {
		return FeaturedListingType.VICINITY;
	}

	@Override
	public List<SearchCriteriaIFace> getORSearchCriteria() {
		return null;
	}

	@Override
	public FetchProfile getFetchProfile() {
		return PropertyOptions.DEFAULT_FEATURED_VICINITY_PROFILE;
	}

}
