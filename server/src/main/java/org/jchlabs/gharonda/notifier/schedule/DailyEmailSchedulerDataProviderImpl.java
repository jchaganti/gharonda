package org.jchlabs.gharonda.notifier.schedule;

import org.jchlabs.gharonda.notifier.data.DailyEmailJobContextDataProviderImpl;
import org.jchlabs.gharonda.notifier.data.JobContextDataProviderIFace;
import org.quartz.Job;


public class DailyEmailSchedulerDataProviderImpl extends AbstractEmailSchedulerDataProviderImpl {

	public DailyEmailSchedulerDataProviderImpl(String group, Class<? extends Job> jobClass, String name) {
		super(group, jobClass, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JobContextDataProviderIFace getJobContextDataProvider() {
		if (ctxDataProvider == null) {
			ctxDataProvider = new DailyEmailJobContextDataProviderImpl();
		}
		return ctxDataProvider;
	}

	@Override
	public String getCronExpressionString() {
		return "0 00 01 * * ? *";
		// return "30 * * * * ? *";
	}

}
