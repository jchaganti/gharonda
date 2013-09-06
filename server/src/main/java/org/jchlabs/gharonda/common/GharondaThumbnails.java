package org.jchlabs.gharonda.common;

public class GharondaThumbnails implements AppThumbnailsIFace {

	private String rootPath;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public GharondaThumbnails() {

	}

	public GharondaThumbnails(String rootPath) {
		super();
		this.rootPath = rootPath;
	}

	public ThumbnailParams[] getAppThumbnailParams() {
		ThumbnailParams[] arr = new ThumbnailParams[2];
		arr[0] = new ThumbnailParams("tn1", 300, 400);
		arr[1] = new ThumbnailParams("tn2", 85, 123);
		return arr;

	}

}
