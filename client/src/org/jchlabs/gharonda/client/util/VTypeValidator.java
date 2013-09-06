package org.jchlabs.gharonda.client.util;

public class VTypeValidator {

	private VType type;

	public VTypeValidator(VType type) {
		this.type = type;
	}

	public boolean validate(String value) {
		boolean res = true;
		if (!value.matches(type.regex)) {
			res = false;
		}
		return res;
	}

}
