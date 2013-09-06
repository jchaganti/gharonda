package org.jchlabs.gharonda.server.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jchlabs.gharonda.notifier.schedule.DailyEmailSchedulerDataProviderImpl;
import org.jchlabs.gharonda.notifier.schedule.EmailExecutor;
import org.jchlabs.gharonda.notifier.schedule.WeeklyEmailSchedulerDataProviderImpl;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;


public class EmailNotifierServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DAILY_EMAIL_NOTIFIER = "Daily Email Notifier";
	private static final String WEEKLY_EMAIL_NOTIFIER = "Weekly Email Notifier";
	private static final String GROUP_NAME = "Gharonda Email Notifier";

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("Scheduling Job ..");
		DailyEmailSchedulerDataProviderImpl dailyEmailScheduler = new DailyEmailSchedulerDataProviderImpl(GROUP_NAME,
				EmailExecutor.class, DAILY_EMAIL_NOTIFIER);
		WeeklyEmailSchedulerDataProviderImpl weeklyEmailScheduler = new WeeklyEmailSchedulerDataProviderImpl(
				GROUP_NAME, EmailExecutor.class, WEEKLY_EMAIL_NOTIFIER);

		try {
			SchedulerFactory schedFact = new StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			CronTrigger trigger = new CronTrigger(DAILY_EMAIL_NOTIFIER, GROUP_NAME);
			trigger.setCronExpression(dailyEmailScheduler.getCronExpressionString());
			sched.scheduleJob(dailyEmailScheduler.getJobDetail(), trigger);

			trigger = new CronTrigger(WEEKLY_EMAIL_NOTIFIER, GROUP_NAME);
			trigger.setCronExpression(weeklyEmailScheduler.getCronExpressionString());
			sched.scheduleJob(weeklyEmailScheduler.getJobDetail(), trigger);

			System.out.println("Jobs scheduled now ..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
