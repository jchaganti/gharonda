package org.jchlabs.gharonda.notifier.data;

public class DailyEmailJobContextDataProviderImpl extends AbstractEmailJobContextDataProviderImpl {
	@Override
	protected Integer getFrequencyId() {
		return 1;
	}

}
