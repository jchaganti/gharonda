package org.jchlabs.gharonda.client.events;

import java.util.List;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class BaseAppEvent<H extends EventHandler> extends GwtEvent<H> {
	private List<PropertiesDTO> featuredProperties;

	public BaseAppEvent(List<PropertiesDTO> featuredListings) {
		this.featuredProperties = featuredListings;
	}

	@Override
	protected void dispatch(H handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<H> getAssociatedType() {
		return null;
	}

	public List<PropertiesDTO> getFeaturedProperties() {
		return featuredProperties;
	}

}
