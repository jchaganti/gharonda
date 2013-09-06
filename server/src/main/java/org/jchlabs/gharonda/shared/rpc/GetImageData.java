package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class GetImageData implements Action<GetImageDataResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private Integer pid;
	private String imageNum;

	public GetImageData() {
	}

	public GetImageData(String imageNum, Integer pid) {
		super();
		this.imageNum = imageNum;
		this.pid = pid;
	}

	public String getImageNum() {
		return imageNum;
	}

	public void setImageNum(String imageNum) {
		this.imageNum = imageNum;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetImageData";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
