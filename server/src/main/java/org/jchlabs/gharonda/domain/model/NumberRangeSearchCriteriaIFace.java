package org.jchlabs.gharonda.domain.model;

public interface NumberRangeSearchCriteriaIFace extends SearchCriteriaIFace {
	/*
	 * This returns the Left value of the Number Range.
	 */
	public Integer getLSearchNumber();

	/*
	 * This returns the Right value of the Number Range.
	 */
	public Integer getRSearchNumber();

	/*
	 * Indicates whether the Left and Right values are inclusive in the range.
	 */
	public boolean getIsInclusive();

}
