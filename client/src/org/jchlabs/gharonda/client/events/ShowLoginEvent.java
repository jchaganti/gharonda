package org.jchlabs.gharonda.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.gwtplatform.mvp.client.HasEventBus;

public class ShowLoginEvent extends GwtEvent<ShowLoginEventHandler> {
	private Object data;

	public ShowLoginEvent(Object data) {
		this.data = data;
	}

	private static final Type<ShowLoginEventHandler> TYPE = new Type<ShowLoginEventHandler>();

	public static Type<ShowLoginEventHandler> getType() {
		return TYPE;
	}

	public static void fire(HasEventBus source, Object data) {
		source.fireEvent(new ShowLoginEvent(data));
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	protected void dispatch(ShowLoginEventHandler handler) {
		handler.onShowLoginEvent(this);
	}

	@Override
	public Type<ShowLoginEventHandler> getAssociatedType() {
		return getType();
	}
}
