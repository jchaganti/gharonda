package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Result;

public class GetSearchPropertiesResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -1788523843461596092L;
	private List<PropertiesDTO> searchProperties;
	private int realCount;

	public GetSearchPropertiesResult(List<PropertiesDTO> searchProperties, int realCount) {
		super();
		this.searchProperties = searchProperties;
		this.realCount = realCount;
	}

	public GetSearchPropertiesResult() {

	}

	public List<PropertiesDTO> getSearchProperties() {
		return searchProperties;
	}

	public void setSearchProperties(List<PropertiesDTO> searchProperties) {
		this.searchProperties = searchProperties;
	}

	public int getRealCount() {
		return realCount;
	}

	public void setRealCount(int realCount) {
		this.realCount = realCount;
	}

}
