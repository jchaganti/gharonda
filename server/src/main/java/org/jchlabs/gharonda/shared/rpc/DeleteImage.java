package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class DeleteImage implements Action<DeleteImageResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private Integer pid;

	private String imageNum;

	public DeleteImage() {
	}

	public DeleteImage(String imageNum, Integer pid) {
		super();
		this.imageNum = imageNum;
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}

	public String getImageNum() {
		return imageNum;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "DeleteImage";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
