package org.jchlabs.gharonda.notifier.schedule;

import java.text.ParseException;

import org.jchlabs.gharonda.notifier.data.JobContextDataProviderIFace;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;


public abstract class AbstractEmailSchedulerDataProviderImpl implements SchedulerDataProviderIFace {

	private String name;
	private String group;
	private Class<? extends Job> jobClass;

	protected JobContextDataProviderIFace ctxDataProvider;

	public AbstractEmailSchedulerDataProviderImpl(String group, Class<? extends Job> jobClass, String name) {
		this.group = group;
		this.jobClass = jobClass;
		this.name = name;
	}

	public JobDetail getJobDetail() {
		JobDetail jobDetail = new JobDetail(name, group, EmailExecutor.class);
		jobDetail.getJobDataMap().put("data", getJobContextDataProvider());
		return jobDetail;
	}

	public Trigger getTrigger() {
		CronTrigger trigger = new CronTrigger(name, group);
		try {
			trigger.setCronExpression(getCronExpressionString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return trigger;
	}

	abstract public JobContextDataProviderIFace getJobContextDataProvider();

	abstract public String getCronExpressionString();

}
