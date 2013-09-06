package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Result;

public class DeleteImageResult implements Result {

	private static final long serialVersionUID = -5785233314922498922L;

	private Status status;

	public Status getStatus() {
		return status;
	}

	public DeleteImageResult() {
	}

	public DeleteImageResult(Status status) {
		this.status = status;
	}

}
