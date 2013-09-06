package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Result;

public class DeletePropertyResult implements Result {

	private static final long serialVersionUID = -5785233314922498922L;

	private Status status;

	public DeletePropertyResult() {
	}

	public DeletePropertyResult(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
