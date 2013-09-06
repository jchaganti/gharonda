package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Result;

public class GetFeaturedPropertiesResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -1788523843461596092L;
	private List<PropertiesDTO> featuredProperties;

	public GetFeaturedPropertiesResult(List<PropertiesDTO> actualFeaturedProperties) {
		this.featuredProperties = actualFeaturedProperties;
	}

	public GetFeaturedPropertiesResult() {

	}

	public List<PropertiesDTO> getFeaturedProperties() {
		return featuredProperties;
	}

	public void setFeaturedProperties(List<PropertiesDTO> featuredProperties) {
		this.featuredProperties = featuredProperties;
	}

}
