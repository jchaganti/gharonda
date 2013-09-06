package org.jchlabs.gharonda.client.view.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;
import org.jchlabs.gharonda.shared.rpc.GetSearchProperties;
import org.jchlabs.gharonda.shared.rpc.GetSearchPropertiesResult;
import org.jchlabs.gharonda.shared.rpc.GetUserFavorites;
import org.jchlabs.gharonda.shared.rpc.GetUserPropertiesResult;

import com.google.gwt.dev.protobuf.Message;
import com.google.gwt.gen2.table.client.MutableTableModel;
import com.google.gwt.gen2.table.client.TableModel;
import com.google.gwt.gen2.table.client.TableModelHelper;
import com.google.gwt.gen2.table.client.TableModelHelper.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;

public class PropertiesTableModel extends MutableTableModel<PropertiesDTO> {

	private DispatchAsync dispatcher;
	private SearchProfile searchProfile;

	// we keep a map so we can index by id
	private Map<Integer, PropertiesDTO> map;

	@Inject
	public PropertiesTableModel(DispatchAsync dispatcher) {
		super();
		this.dispatcher = dispatcher;
	}

	/**
	 * Fetch a {@link Message} by its id.
	 * 
	 * @param id
	 * @return
	 */
	public PropertiesDTO getMessageById(Integer id) {
		return map.get(id);
	}

	public SearchProfile getSearchProfile() {
		return searchProfile;
	}

	@Override
	public void requestRows(final Request request, final TableModel.Callback<PropertiesDTO> callback) {
		if (searchProfile != null) {
			FetchProfile fetchProfile = searchProfile.getFetchProfile();
			fetchProfile.setResultsSize(request.getNumRows());
			fetchProfile.setFirstResult(request.getStartRow());
			GetSearchProperties action = new GetSearchProperties(fetchProfile, searchProfile.getSelListCriteria(),
					searchProfile.getSearchTextCriteria(), false, searchProfile.getResultsSizeLimit());
			dispatcher.execute(action, new AsyncCallback<GetSearchPropertiesResult>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(final GetSearchPropertiesResult result) {

					TableModelHelper.Response<PropertiesDTO> response = new TableModelHelper.Response<PropertiesDTO>() {
						@Override
						public Iterator<PropertiesDTO> getRowValues() {
							if (result != null && result.getSearchProperties() != null) {
								return result.getSearchProperties().iterator();
							} else {
								return new ArrayList<PropertiesDTO>().iterator();
							}
						}
					};
					callback.onRowsReady(request, response);
				}
			});
		} else {
			GetUserFavorites action = new GetUserFavorites();
			dispatcher.execute(action, new AsyncCallback<GetUserPropertiesResult>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(final GetUserPropertiesResult result) {
					TableModelHelper.Response<PropertiesDTO> response = new TableModelHelper.Response<PropertiesDTO>() {
						@Override
						public Iterator<PropertiesDTO> getRowValues() {
							if (result != null && result.getUserProperties() != null) {
								return result.getUserProperties().iterator();
							} else {
								return new ArrayList<PropertiesDTO>().iterator();
							}
						}
					};
					callback.onRowsReady(request, response);
				}
			});

		}

	}

	/**
	 * Set the data on the model. Overwrites prior data.
	 * 
	 * @param list
	 */
	public void setData(List<PropertiesDTO> list) {
		// toss the list, index by id in a map.
		map = new HashMap<Integer, PropertiesDTO>(list.size());
		for (PropertiesDTO m : list) {
			map.put(m.getId(), m);
		}
	}

	public void setSearchProfile(SearchProfile searchProfile) {
		this.searchProfile = searchProfile;
	}

	@Override
	protected boolean onRowInserted(int beforeRow) {
		return true;
	}

	@Override
	protected boolean onRowRemoved(int row) {
		return true;
	}

	@Override
	protected boolean onSetRowValue(int row, PropertiesDTO rowValue) {
		return true;
	}

}
