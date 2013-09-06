package org.jchlabs.gharonda.domain.model;

import org.jchlabs.gharonda.shared.rpc.Utils;

public class BitwiseAndSearchCriteria extends BaseSearchCriteria implements NumberSearchCriteriaIFace {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer searchNumber;

	public BitwiseAndSearchCriteria() {
		super();
	}

	public BitwiseAndSearchCriteria(String searchAttributeName, Integer searchNumber) {
		super(searchAttributeName);
		this.searchNumber = searchNumber;
	}

	public Integer getSearchNumber() {
		return searchNumber;
	}

	public String toString() {
		return Utils.format("CriteriaType=BitwiseAndSearchCriteria", super.toString(),
				"searchNumber=" + searchNumber.toString());
	}
}
