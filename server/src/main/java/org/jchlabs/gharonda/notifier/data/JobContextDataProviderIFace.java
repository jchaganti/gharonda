package org.jchlabs.gharonda.notifier.data;

public interface JobContextDataProviderIFace {

	public Object getJobData();

	public Object getPostJobFireData();

	public PostJobFireCBIFace getPostJobFireDataCB();

}
