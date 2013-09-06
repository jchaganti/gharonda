package org.jchlabs.gharonda.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.gwtplatform.mvp.client.HasEventBus;

public class MyAccountSelectedEvent extends GwtEvent<MyAccountSelectedEventHandler> {
	public MyAccountSelectedEvent() {

	}

	private static final Type<MyAccountSelectedEventHandler> TYPE = new Type<MyAccountSelectedEventHandler>();

	public static Type<MyAccountSelectedEventHandler> getType() {
		return TYPE;
	}

	public static void fire(HasEventBus source) {
		source.fireEvent(new MyAccountSelectedEvent());
	}

	@Override
	protected void dispatch(MyAccountSelectedEventHandler handler) {
		handler.onMyAccountSelectedEvent(this);
	}

	@Override
	public Type<MyAccountSelectedEventHandler> getAssociatedType() {
		return getType();
	}
}
