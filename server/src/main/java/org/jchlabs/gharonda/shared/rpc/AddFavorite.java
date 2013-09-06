package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class AddFavorite implements Action<AddFavoriteResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private Integer pid;

	public AddFavorite() {
	}

	public AddFavorite(Integer pid) {
		super();
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "AddFavorite";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

}
