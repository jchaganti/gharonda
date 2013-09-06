package org.jchlabs.gharonda.notifier.data;

public class WeeklyEmailJobContextDataProviderImpl extends AbstractEmailJobContextDataProviderImpl {

	@Override
	protected Integer getFrequencyId() {
		return 2;
	}

}
