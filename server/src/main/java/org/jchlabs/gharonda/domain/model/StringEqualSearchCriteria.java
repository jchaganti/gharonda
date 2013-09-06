package org.jchlabs.gharonda.domain.model;

import org.jchlabs.gharonda.shared.rpc.Utils;

public class StringEqualSearchCriteria extends StringSearchCriteria {

	private static final long serialVersionUID = 1L;

	public StringEqualSearchCriteria() {
		super();
	}

	public StringEqualSearchCriteria(String searchAttributeName, String searchAttributeString) {
		super(searchAttributeName, searchAttributeString);
	}

	public String toString() {
		return Utils.format("CriteriaType=StringEqualSearchCriteria", super.toString(),
				"searchString=" + super.getSearchString());
	}
}
