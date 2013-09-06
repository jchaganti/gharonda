package org.jchlabs.gharonda.client.events;

import org.jchlabs.gharonda.client.util.SearchProfile;

import com.google.gwt.event.shared.GwtEvent;
import com.gwtplatform.mvp.client.HasEventBus;

public class ResultsWithoutMapSelectedEvent extends GwtEvent<ResultsWithoutMapSelectedEventHandler> {
	private SearchProfile listSearchProfile;
	private SearchProfile mapSearchProfile;

	public ResultsWithoutMapSelectedEvent(SearchProfile listSearchProfile, SearchProfile mapSearchProfile) {
		this.listSearchProfile = listSearchProfile;
		this.mapSearchProfile = mapSearchProfile;
	}

	private static Type<ResultsWithoutMapSelectedEventHandler> TYPE = new Type<ResultsWithoutMapSelectedEventHandler>();

	public static Type<ResultsWithoutMapSelectedEventHandler> getType() {
		return TYPE;
	}

	public static void fire(HasEventBus source, SearchProfile listSearchProfile, SearchProfile mapSearchProfile) {
		source.fireEvent(new ResultsWithoutMapSelectedEvent(listSearchProfile, mapSearchProfile));
	}

	@Override
	protected void dispatch(ResultsWithoutMapSelectedEventHandler handler) {
		handler.onResultsWithoutMapSelected(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ResultsWithoutMapSelectedEventHandler> getAssociatedType() {
		return getType();
	}

	public SearchProfile getListSearchProfile() {
		return listSearchProfile;
	}

	public SearchProfile getMapSearchProfile() {
		return mapSearchProfile;
	}

}
