package org.jchlabs.gharonda.domain.model;

public interface DoubleRangeSearchCriteriaIFace extends SearchCriteriaIFace {
	/*
	 * This returns the Left value of the Double Range.
	 */
	public Double getLSearchDouble();

	/*
	 * This returns the Right value of the Double Range.
	 */
	public Double getRSearchDouble();

}
