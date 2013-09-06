package org.jchlabs.gharonda.client.events;

import java.util.List;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.event.shared.GwtEvent;
import com.gwtplatform.mvp.client.HasEventBus;

public class FeaturedPropertiesReceivedEvent extends GwtEvent<FeaturedPropertiesReceivedEventHandler> {

	private List<PropertiesDTO> featuredProperties;

	public FeaturedPropertiesReceivedEvent(List<PropertiesDTO> featuredProperties) {
		super();
		this.featuredProperties = featuredProperties;
	}

	private static final Type<FeaturedPropertiesReceivedEventHandler> TYPE = new Type<FeaturedPropertiesReceivedEventHandler>();

	public static Type<FeaturedPropertiesReceivedEventHandler> getType() {
		return TYPE;
	}

	public static void fire(HasEventBus source, List<PropertiesDTO> featuredProperties) {
		source.fireEvent(new FeaturedPropertiesReceivedEvent(featuredProperties));
	}

	public List<PropertiesDTO> getFeaturedProperties() {
		return featuredProperties;
	}

	public void setFeaturedProperties(List<PropertiesDTO> featuredProperties) {
		this.featuredProperties = featuredProperties;
	}

	public FeaturedPropertiesReceivedEvent() {
	}

	@Override
	protected void dispatch(FeaturedPropertiesReceivedEventHandler handler) {
		handler.onFeaturedPropertiesReceivedEvent(this);
	}

	@Override
	public Type<FeaturedPropertiesReceivedEventHandler> getAssociatedType() {
		return getType();
	}

}
