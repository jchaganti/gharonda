package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.Neightbourhood;

import com.gwtplatform.dispatch.shared.Result;

public class GetNeighbourhoodDataResult implements Result {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private List<Neightbourhood> neighbourhood;

	public GetNeighbourhoodDataResult(List<Neightbourhood> neighbourhood) {
		super();
		this.neighbourhood = neighbourhood;
	}

	public GetNeighbourhoodDataResult() {

	}

	public List<Neightbourhood> getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(List<Neightbourhood> neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

}
