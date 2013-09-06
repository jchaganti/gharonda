package org.jchlabs.gharonda.domain.model;

import org.jchlabs.gharonda.shared.rpc.Utils;

public class DoubleRangeSearchCriteria extends BaseSearchCriteria implements DoubleRangeSearchCriteriaIFace {

	private static final long serialVersionUID = 1L;
	private Double lSearchDouble;
	private Double rSearchDouble;

	public DoubleRangeSearchCriteria() {
		super();
	}

	public DoubleRangeSearchCriteria(String attrName, Double searchDouble, Double searchDouble2) {
		super(attrName);
		lSearchDouble = searchDouble;
		rSearchDouble = searchDouble2;
	}

	@Override
	public Double getLSearchDouble() {
		return lSearchDouble;
	}

	@Override
	public Double getRSearchDouble() {
		return rSearchDouble;
	}

	public String toString() {
		return Utils.format("CriteriaType=DoubleRangeSearchCriteria", super.toString(), "lSearchDouble="
				+ lSearchDouble.toString(), "rSearchDouble=" + rSearchDouble.toString());
	}
}
