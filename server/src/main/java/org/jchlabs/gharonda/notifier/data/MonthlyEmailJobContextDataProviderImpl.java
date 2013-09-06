package org.jchlabs.gharonda.notifier.data;

public class MonthlyEmailJobContextDataProviderImpl extends AbstractEmailJobContextDataProviderImpl {

	@Override
	protected Integer getFrequencyId() {
		return 4;
	}

}
