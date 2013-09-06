package org.jchlabs.gharonda.client.util;

public class LinkData {

	public String label;
	public String url;
	public int numFeatured;

	public LinkData(String label, int numFeatured, String url) {
		this.label = label;
		this.numFeatured = numFeatured;
		this.url = url;
	}
}
