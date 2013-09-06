package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;

import com.gwtplatform.dispatch.shared.Action;

public class GetSearchProperties implements Action<GetSearchPropertiesResult> {

	private static final long serialVersionUID = 2255166545722718095L;
	private List<SearchCriteriaIFace> searchTextList;
	private List<SearchCriteriaIFace> searchSelectionList;
	private FetchProfile fetchProfile;
	boolean initLoading = false;
	int preDecidedResultsSize = -1;

	public GetSearchProperties() {
	}

	public GetSearchProperties(List<SearchCriteriaIFace> searchSelectionList, List<SearchCriteriaIFace> searchTextList) {
		this(null, searchSelectionList, searchTextList, false, -1);
	}

	public GetSearchProperties(FetchProfile fetchProfile, List<SearchCriteriaIFace> searchSelectionList,
			List<SearchCriteriaIFace> searchTextList, boolean initLoading, int preDecidedResultsSize) {
		super();
		this.fetchProfile = fetchProfile;
		this.searchSelectionList = searchSelectionList;
		this.searchTextList = searchTextList;
		this.initLoading = initLoading;
		this.preDecidedResultsSize = preDecidedResultsSize;
	}

	public List<SearchCriteriaIFace> getSearchTextList() {
		return searchTextList;
	}

	public List<SearchCriteriaIFace> getSearchSelectionList() {
		return searchSelectionList;
	}

	public boolean isInitLoading() {
		return initLoading;
	}

	public FetchProfile getFetchProfile() {
		return fetchProfile;
	}

	public int getPreDecidedResultsSize() {
		return preDecidedResultsSize;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetSearchProperties";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
