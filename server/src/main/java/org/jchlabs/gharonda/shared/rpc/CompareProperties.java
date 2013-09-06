package org.jchlabs.gharonda.shared.rpc;

import java.util.List;

import com.gwtplatform.dispatch.shared.Action;

public class CompareProperties implements Action<ComparePropertiesResult> {

	private static final long serialVersionUID = 2255166545722718094L;

	private List<List<Integer>> pids;
	private int numRows;
	private int firstResult = 0;

	public CompareProperties() {
	}

	public CompareProperties(List<List<Integer>> pids, int numRows, int firstResult) {
		super();
		this.pids = pids;
		this.numRows = numRows;
		this.firstResult = firstResult;
	}

	public List<List<Integer>> getPids() {
		return pids;
	}

	public void setPids(List<List<Integer>> pids) {
		this.pids = pids;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "CompareProperties";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
