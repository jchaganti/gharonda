package org.jchlabs.gharonda.domain.model;

import java.util.Arrays;

import org.jchlabs.gharonda.shared.rpc.Utils;


public class InValuesNumberSearchCriteria extends BaseSearchCriteria implements InValuesNumberSearchCriteriaIFace {

	private static final long serialVersionUID = 1L;
	private Integer[] inValues;

	public InValuesNumberSearchCriteria() {
		super();
	}

	public InValuesNumberSearchCriteria(String searchAttributeName, Integer[] _inValues) {
		super(searchAttributeName);
		this.inValues = _inValues;
	}

	public Integer[] getInValuesNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return Utils.format("CriteriaType=InValuesNumberSearchCriteria", super.toString(),
				"inValues=" + Arrays.toString(inValues));
	}
}
