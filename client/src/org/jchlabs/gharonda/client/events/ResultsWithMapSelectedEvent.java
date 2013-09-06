package org.jchlabs.gharonda.client.events;

import org.jchlabs.gharonda.client.util.SearchProfile;

import com.google.gwt.event.shared.GwtEvent;
import com.gwtplatform.mvp.client.HasEventBus;

public class ResultsWithMapSelectedEvent extends GwtEvent<ResultsWithMapSelectedEventHandler> {
	private SearchProfile listSearchProfile;
	private SearchProfile mapSearchProfile;

	public ResultsWithMapSelectedEvent(SearchProfile listSearchProfile, SearchProfile mapSearchProfile) {
		this.listSearchProfile = listSearchProfile;
		this.mapSearchProfile = mapSearchProfile;
	}

	private static Type<ResultsWithMapSelectedEventHandler> TYPE = new Type<ResultsWithMapSelectedEventHandler>();

	public static Type<ResultsWithMapSelectedEventHandler> getType() {
		return TYPE;
	}

	public static void fire(HasEventBus source, SearchProfile listSearchProfile, SearchProfile mapSearchProfile) {
		source.fireEvent(new ResultsWithMapSelectedEvent(listSearchProfile, mapSearchProfile));
	}

	protected void dispatch(ResultsWithMapSelectedEventHandler handler) {
		handler.onResultsWithMapSelected(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ResultsWithMapSelectedEventHandler> getAssociatedType() {
		return getType();
	}

	public SearchProfile getListSearchProfile() {
		return listSearchProfile;
	}

	public SearchProfile getMapSearchProfile() {
		return mapSearchProfile;
	}

}
