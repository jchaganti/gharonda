package org.jchlabs.gharonda.client.util;

public enum VType {

	ALPHABET("^[a-zA-Z_]+$", "Alphabet"), ALPHANUMERIC("^[a-zA-Z0-9_]+$", "Alphanumeric"), NUMERIC("^[+0-9]+$",
			"Numeric"), EMAIL("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$", "Email");
	String regex;
	String name;

	VType(String regex, String name) {
		this.regex = regex;
		this.name = name;
	}
}
