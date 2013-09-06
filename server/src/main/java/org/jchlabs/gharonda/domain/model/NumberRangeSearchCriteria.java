package org.jchlabs.gharonda.domain.model;

import org.jchlabs.gharonda.shared.rpc.Utils;

public class NumberRangeSearchCriteria extends BaseSearchCriteria implements NumberRangeSearchCriteriaIFace {

	private static final long serialVersionUID = 1L;
	private Integer lSearchNumber;
	private Integer rSearchNumber;
	private boolean inclusive;

	public NumberRangeSearchCriteria() {
		super();
	}

	public NumberRangeSearchCriteria(String searchAttributeName, boolean inclusive, Integer searchNumber1,
			Integer searchNumber2) {
		super(searchAttributeName);
		this.inclusive = inclusive;
		lSearchNumber = searchNumber1;
		rSearchNumber = searchNumber2;
	}

	public Integer getLSearchNumber() {
		return lSearchNumber;
	}

	public Integer getRSearchNumber() {
		return rSearchNumber;
	}

	public boolean getIsInclusive() {
		return inclusive;
	}

	public String toString() {
		return Utils.format("CriteriaType=NumberRangeSearchCriteria", super.toString(), "lSearchNumber="
				+ lSearchNumber, "rSearchNumber=" + rSearchNumber);
	}

}
