package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class GetProperty implements Action<GetPropertyResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private Integer pid;
	private boolean userData;

	public GetProperty() {
	}

	public GetProperty(Integer pid, boolean userData) {
		super();
		this.pid = pid;
		this.userData = userData;
	}

	public GetProperty(Integer pid) {
		this(pid, false);
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public boolean isUserData() {
		return userData;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetProperty";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
