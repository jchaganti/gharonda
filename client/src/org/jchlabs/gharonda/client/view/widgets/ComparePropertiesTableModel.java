package org.jchlabs.gharonda.client.view.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.CompareProperties;
import org.jchlabs.gharonda.shared.rpc.ComparePropertiesResult;

import com.google.gwt.dev.protobuf.Message;
import com.google.gwt.gen2.table.client.MutableTableModel;
import com.google.gwt.gen2.table.client.TableModel;
import com.google.gwt.gen2.table.client.TableModelHelper;
import com.google.gwt.gen2.table.client.TableModelHelper.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;

public class ComparePropertiesTableModel extends MutableTableModel<List<PropertiesDTO>> {

	private EventBus eventBus;
	private DispatchAsync dispatcher;
	private List<List<Integer>> pList;

	@Inject
	public ComparePropertiesTableModel(DispatchAsync dispatcher, EventBus eventBus) {
		super();
		this.dispatcher = dispatcher;
		this.eventBus = eventBus;
	}

	// we keep a map so we can index by id
	private Map<Integer, List<PropertiesDTO>> map;

	/**
	 * Set the data on the model. Overwrites prior data.
	 * 
	 * @param list
	 */
	public void setData(List<List<PropertiesDTO>> list) {
		// toss the list, index by id in a map.
		map = new HashMap<Integer, List<PropertiesDTO>>(list.size());
		for (List<PropertiesDTO> m : list) {
			map.put(m.get(0).getId(), m);
		}
	}

	/**
	 * Fetch a {@link Message} by its id.
	 * 
	 * @param id
	 * @return
	 */
	public List<PropertiesDTO> getMessageById(Integer id) {
		return map.get(id);
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
	protected boolean onSetRowValue(int row, List<PropertiesDTO> rowValue) {
		return true;
	}

	@Override
	public void requestRows(final Request request, final TableModel.Callback<List<PropertiesDTO>> callback) {

		CompareProperties action = new CompareProperties(pList, request.getNumRows(), request.getStartRow());
		// Window.alert("numRows = " + request.getNumRows() + " startRow=" +
		// request.getStartRow());
		dispatcher.execute(action, new AsyncCallback<ComparePropertiesResult>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(final ComparePropertiesResult result) {

				TableModelHelper.Response<List<PropertiesDTO>> response = new TableModelHelper.Response<List<PropertiesDTO>>() {
					@Override
					public Iterator<List<PropertiesDTO>> getRowValues() {
						if (result != null && result.getCompareProperties() != null) {
							return result.getCompareProperties().iterator();
						} else {
							return new ArrayList<List<PropertiesDTO>>().iterator();
						}
					}
				};
				callback.onRowsReady(request, response);
			}
		});

	}

	public List<List<Integer>> getpList() {
		return pList;
	}

	public void setpList(List<List<Integer>> pList) {
		this.pList = pList;
	}

}
