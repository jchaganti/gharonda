package org.jchlabs.gharonda.notifier.schedule;

import org.jchlabs.gharonda.notifier.data.FortnightlyEmailJobContextDataProviderImpl;
import org.jchlabs.gharonda.notifier.data.JobContextDataProviderIFace;
import org.quartz.Job;


public class FortnightlyEmailSchedulerDataProviderImpl extends AbstractEmailSchedulerDataProviderImpl {

	public FortnightlyEmailSchedulerDataProviderImpl(String group, Class<Job> jobClass, String name) {
		super(group, jobClass, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JobContextDataProviderIFace getJobContextDataProvider() {
		if (ctxDataProvider == null) {
			ctxDataProvider = new FortnightlyEmailJobContextDataProviderImpl();
		}
		return ctxDataProvider;
	}

	@Override
	public String getCronExpressionString() {
		return "0 00 03 15 * ?";
	}

}
