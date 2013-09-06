package org.jchlabs.gharonda.notifier.schedule;

public interface SchedulerSvcIFace {

	public void register(SchedulerDataProviderIFace dataProvider);

	public void start();
}
