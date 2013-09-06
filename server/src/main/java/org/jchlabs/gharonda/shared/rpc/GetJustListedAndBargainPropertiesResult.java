package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.dispatch.shared.Result;

public class GetJustListedAndBargainPropertiesResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = -1788523843461596092L;
	private List<PropertiesDTO> bargains;
	private List<PropertiesDTO> justListed;

	public GetJustListedAndBargainPropertiesResult(List<PropertiesDTO> bargains, List<PropertiesDTO> justListed) {
		super();
		this.setBargains(bargains);
		this.setJustListed(justListed);
	}

	public GetJustListedAndBargainPropertiesResult() {

	}

	public void setBargains(List<PropertiesDTO> bargains) {
		this.bargains = bargains;
	}

	public List<PropertiesDTO> getBargains() {
		return bargains;
	}

	public void setJustListed(List<PropertiesDTO> justListed) {
		this.justListed = justListed;
	}

	public List<PropertiesDTO> getJustListed() {
		return justListed;
	}

}
