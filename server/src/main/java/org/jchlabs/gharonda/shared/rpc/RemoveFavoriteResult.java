package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Result;

public class RemoveFavoriteResult implements Result {

	private static final long serialVersionUID = -5785233314922498922L;

	private Status status;

	public RemoveFavoriteResult() {
	}

	public RemoveFavoriteResult(Status status) {
		super();
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
