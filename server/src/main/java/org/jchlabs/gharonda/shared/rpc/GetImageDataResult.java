package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Result;

public class GetImageDataResult implements Result {

	private static final long serialVersionUID = -5785233314922498922L;

	private String imageData;

	public GetImageDataResult() {
	}

	public GetImageDataResult(String imageData) {
		super();
		this.imageData = imageData;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

}
