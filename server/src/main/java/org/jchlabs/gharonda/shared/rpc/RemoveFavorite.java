package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import com.gwtplatform.dispatch.shared.Action;

public class RemoveFavorite implements Action<RemoveFavoriteResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private List<Integer> pids;

	public RemoveFavorite() {
	}

	public RemoveFavorite(List<Integer> pids) {
		super();
		this.pids = pids;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "RemoveFavorite";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	public List<Integer> getPids() {
		return pids;
	}

	public void setPids(List<Integer> pids) {
		this.pids = pids;
	}

}
