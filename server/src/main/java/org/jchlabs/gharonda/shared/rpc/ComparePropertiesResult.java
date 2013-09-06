package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Result;

public class ComparePropertiesResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -1788523843461596092L;
	private List<List<PropertiesDTO>> compareProperties;

	public ComparePropertiesResult() {

	}

	public ComparePropertiesResult(List<List<PropertiesDTO>> compareProperties) {
		super();
		this.compareProperties = compareProperties;
	}

	public List<List<PropertiesDTO>> getCompareProperties() {
		return compareProperties;
	}

	public void setCompareProperties(List<List<PropertiesDTO>> compareProperties) {
		this.compareProperties = compareProperties;
	}

}
