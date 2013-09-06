package org.jchlabs.gharonda.common;

public class ThumbnailParams {

	private String suffix;
	private Integer width;
	private Integer height;

	public ThumbnailParams(String extn, Integer height, Integer width) {
		this.suffix = extn;
		this.height = height;
		this.width = width;
	}

	public String getSuffix() {
		return suffix;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

}
