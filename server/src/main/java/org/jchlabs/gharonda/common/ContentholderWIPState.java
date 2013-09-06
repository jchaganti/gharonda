package org.jchlabs.gharonda.common;

public enum ContentholderWIPState {
	NEW(1), CHECKED_IN(2), WORKING_CPY(3);

	private final Integer state;

	ContentholderWIPState(Integer state) {
		this.state = state;
	}

	public Integer value() {
		return state;
	}
}
