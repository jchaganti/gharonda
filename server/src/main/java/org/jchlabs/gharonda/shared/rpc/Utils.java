package org.jchlabs.gharonda.shared.rpc;

public class Utils {
	public static final String SEPARATOR = "::";

	public static String format(String... strings) {
		String result = "";
		int i = 0;
		for (String str : strings) {
			result += str;
			if (i < strings.length - 1) {
				result += SEPARATOR;
			}
			i++;
		}
		return result;
	}
}
