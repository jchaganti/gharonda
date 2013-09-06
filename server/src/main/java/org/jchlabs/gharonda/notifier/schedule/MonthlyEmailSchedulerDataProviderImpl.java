package org.jchlabs.gharonda.notifier.schedule;

import org.jchlabs.gharonda.notifier.data.JobContextDataProviderIFace;
import org.jchlabs.gharonda.notifier.data.MonthlyEmailJobContextDataProviderImpl;
import org.quartz.Job;


public class MonthlyEmailSchedulerDataProviderImpl extends AbstractEmailSchedulerDataProviderImpl {

	public MonthlyEmailSchedulerDataProviderImpl(String group, Class<Job> jobClass, String name) {
		super(group, jobClass, name);
	}

	@Override
	public JobContextDataProviderIFace getJobContextDataProvider() {
		if (ctxDataProvider == null) {
			ctxDataProvider = new MonthlyEmailJobContextDataProviderImpl();
		}
		return ctxDataProvider;
	}

	@Override
	public String getCronExpressionString() {
		return "0 00 04 L * ?";
	}

}
