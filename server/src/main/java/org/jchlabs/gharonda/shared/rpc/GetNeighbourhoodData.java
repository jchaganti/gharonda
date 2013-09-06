package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;

import com.gwtplatform.dispatch.shared.Action;

public class GetNeighbourhoodData implements Action<GetNeighbourhoodDataResult> {

	private static final long serialVersionUID = 2255166545722718094L;
	private List<SearchCriteriaIFace> neighbourhoodSelectionList;
	private List<SearchCriteriaIFace> mapBounds;

	public GetNeighbourhoodData(List<SearchCriteriaIFace> neighbourhoodSelectionList,
			List<SearchCriteriaIFace> mapBounds) {
		super();
		this.neighbourhoodSelectionList = neighbourhoodSelectionList;
		this.mapBounds = mapBounds;
	}

	public GetNeighbourhoodData() {
	}

	public List<SearchCriteriaIFace> getNeighbourhoodSelectionList() {
		return neighbourhoodSelectionList;
	}

	public void setNeighbourhoodSelectionList(List<SearchCriteriaIFace> neighbourhoodSelectionList) {
		this.neighbourhoodSelectionList = neighbourhoodSelectionList;
	}

	public List<SearchCriteriaIFace> getMapBounds() {
		return mapBounds;
	}

	public void setMapBounds(List<SearchCriteriaIFace> mapBounds) {
		this.mapBounds = mapBounds;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetNeighbourhoodData";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
