package org.jchlabs.gharonda.notifier.schedule;

import org.jchlabs.gharonda.notifier.data.JobContextDataProviderIFace;
import org.jchlabs.gharonda.notifier.data.WeeklyEmailJobContextDataProviderImpl;
import org.quartz.Job;


public class WeeklyEmailSchedulerDataProviderImpl extends AbstractEmailSchedulerDataProviderImpl {

	public WeeklyEmailSchedulerDataProviderImpl(String group, Class<? extends Job> jobClass, String name) {
		super(group, jobClass, name);
	}

	@Override
	public JobContextDataProviderIFace getJobContextDataProvider() {
		if (ctxDataProvider == null) {
			ctxDataProvider = new WeeklyEmailJobContextDataProviderImpl();
		}
		return ctxDataProvider;
	}

	@Override
	public String getCronExpressionString() {
		return "0 00 02 ? * SUN";
		// return "00 * * * * ? *";
	}

}
