package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class ModifyProperty implements Action<ModifyPropertyResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private Integer pid;

	public ModifyProperty() {
	}

	public ModifyProperty(Integer pid) {
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
		return Action.DEFAULT_SERVICE_NAME + "ModifyProperty";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
