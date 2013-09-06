package org.jchlabs.gharonda.notifier.schedule;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public interface SchedulerDataProviderIFace {

	public JobDetail getJobDetail();

	public Trigger getTrigger();

}
