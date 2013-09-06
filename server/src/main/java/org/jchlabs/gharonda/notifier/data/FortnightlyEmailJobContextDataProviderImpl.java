package org.jchlabs.gharonda.notifier.data;

public class FortnightlyEmailJobContextDataProviderImpl extends AbstractEmailJobContextDataProviderImpl {

	@Override
	protected Integer getFrequencyId() {
		return 3;
	}

}
