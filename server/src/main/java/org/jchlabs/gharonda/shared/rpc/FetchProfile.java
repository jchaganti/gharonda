package org.jchlabs.gharonda.shared.rpc;

import java.io.Serializable;

public class FetchProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FetchProfile() {

	}

	private int resultsSize = 0;
	private int firstResult = 0;
	private boolean sortDir = true;
	private String sortAttr = null;

	public FetchProfile(int resultsSize, int fetchStart, String sortAttr, boolean sortDir) {
		super();
		this.resultsSize = resultsSize;
		this.firstResult = fetchStart;
		this.sortAttr = sortAttr;
		this.sortDir = sortDir;
	}

	public int getResultsSize() {
		return resultsSize;
	}

	public void setResultsSize(int fetchSize) {
		this.resultsSize = fetchSize;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int fetchStart) {
		this.firstResult = fetchStart;
	}

	public boolean isSortDir() {
		return sortDir;
	}

	public void setSortDir(boolean sortDir) {
		this.sortDir = sortDir;
	}

	public String getSortAttr() {
		return sortAttr;
	}

	public void setSortAttr(String sortAttr) {
		this.sortAttr = sortAttr;
	}

}
