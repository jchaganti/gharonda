package org.jchlabs.gharonda.notifier.schedule;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class EmailSchedulerSvcImpl implements SchedulerSvcIFace {

	private List<SchedulerDataProviderIFace> schedulerDataProviders = new ArrayList<SchedulerDataProviderIFace>();
	private static Scheduler scheduler = null;

	static {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			scheduler = factory.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void register(SchedulerDataProviderIFace dataProvider) {
		if (dataProvider != null) {
			schedulerDataProviders.add(dataProvider);
		} else {
			System.out.println("The dataProvider cannot be null");
		}

	}

	public void start() {

		for (SchedulerDataProviderIFace schedulerDataProvider : schedulerDataProviders) {
			JobDetail jobDetail = schedulerDataProvider.getJobDetail();
			Trigger trigger = schedulerDataProvider.getTrigger();
			try {
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
