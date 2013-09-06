package org.jchlabs.gharonda.domain.model;

import org.jchlabs.gharonda.shared.rpc.Utils;

public class NumberSearchCriteria extends BaseSearchCriteria implements NumberSearchCriteriaIFace {

	private static final long serialVersionUID = 1L;
	private Integer searchNumber;

	public NumberSearchCriteria() {
		super();
	}

	public NumberSearchCriteria(String searchAttributeName, Integer searchNumber) {
		super(searchAttributeName);
		this.searchNumber = searchNumber;
	}

	public Integer getSearchNumber() {
		return searchNumber;
	}

	public void setSearchNumber(Integer searchNumber) {
		this.searchNumber = searchNumber;
	}

	public String toString() {
		return Utils.format("CriteriaType=NumberSearchCriteria", super.toString(),
				"searchNumber=" + searchNumber.toString());
	}
}
