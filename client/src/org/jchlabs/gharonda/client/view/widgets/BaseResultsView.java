package org.jchlabs.gharonda.client.view.widgets;

import java.util.List;

import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.gen2.table.client.AbstractColumnDefinition;
import com.google.gwt.gen2.table.client.CachedTableModel;
import com.google.gwt.gen2.table.client.CellRenderer;
import com.google.gwt.gen2.table.client.DefaultRowRenderer;
import com.google.gwt.gen2.table.client.DefaultTableDefinition;
import com.google.gwt.gen2.table.client.FixedWidthGridBulkRenderer;
import com.google.gwt.gen2.table.client.PagingScrollTable;
import com.google.gwt.gen2.table.client.ScrollTable;
import com.google.gwt.gen2.table.client.TableDefinition;
import com.google.gwt.gen2.table.client.AbstractScrollTable.ScrollPolicy;
import com.google.gwt.gen2.table.client.AbstractScrollTable.SortPolicy;
import com.google.gwt.gen2.table.client.SelectionGrid.SelectionPolicy;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.UiHandlers;

public abstract class BaseResultsView<H extends UiHandlers> extends AbstractAppView<H> {

	protected final class IdColumnDefinition extends AbstractColumnDefinition<PropertiesDTO, Integer> {
		@Override
		public Integer getCellValue(PropertiesDTO rowValue) {
			return rowValue.getId();
		}

		@Override
		public void setCellValue(PropertiesDTO rowValue, Integer cellValue) {
		}
	}

	@UiField
	public HTMLPanel propertiesGridPanel;
	@UiField
	public HTMLPanel paging;

	protected PagingScrollTable<PropertiesDTO> propertiesGrid;
	protected CachedTableModel<PropertiesDTO> cachedTableModel = null;

	protected DefaultTableDefinition<PropertiesDTO> tableDefinition = null;

	protected PropertiesTableModel tableModel = null;

	public void loadResults(List<PropertiesDTO> list, int realCount, SearchProfile profile) {
		tableModel.setSearchProfile(profile);
		tableModel.setData(list);
		// reset the table model row count
		tableModel.setRowCount(realCount);
		// clear the cache
		cachedTableModel.clearCache();
		// reset the cached model row count
		cachedTableModel.setRowCount(realCount);
		// force to page zero with a reload
		propertiesGrid.gotoPage(0, true);

	}

	protected PagingScrollTable<PropertiesDTO> createScrollTable() {

		// add it to cached table model
		cachedTableModel = createCachedTableModel(tableModel);

		// create the table definition
		TableDefinition<PropertiesDTO> tableDef = createTableDefinition();

		// create the paging scroll table
		PagingScrollTable<PropertiesDTO> pagingScrollTable = new PagingScrollTable<PropertiesDTO>(cachedTableModel,
				tableDef);
		pagingScrollTable.setPageSize(getPageSize());
		pagingScrollTable.setEmptyTableWidget(new HTML(PropertyOptions.NO_SEARCH_RESULTS_STR));
		pagingScrollTable.getDataTable().setSelectionPolicy(SelectionPolicy.ONE_ROW);

		// setup the bulk renderer
		FixedWidthGridBulkRenderer<PropertiesDTO> bulkRenderer = new FixedWidthGridBulkRenderer<PropertiesDTO>(
				pagingScrollTable.getDataTable(), pagingScrollTable);
		pagingScrollTable.setBulkRenderer(bulkRenderer);

		// setup the formatting
		pagingScrollTable.setCellPadding(3);
		pagingScrollTable.setCellSpacing(0);
		pagingScrollTable.setResizePolicy(ScrollTable.ResizePolicy.FILL_WIDTH);

		pagingScrollTable.setSortPolicy(SortPolicy.SINGLE_CELL);
		pagingScrollTable.setScrollPolicy(ScrollPolicy.DISABLED);
		return pagingScrollTable;
	}

	abstract protected CellRenderer<PropertiesDTO, Integer> getCellRenderer();

	abstract protected int getPageSize();

	private CachedTableModel<PropertiesDTO> createCachedTableModel(PropertiesTableModel tableModel) {
		CachedTableModel<PropertiesDTO> tm = new CachedTableModel<PropertiesDTO>(tableModel);
		tm.setPreCachedRowCount(getPageSize() * 3);
		tm.setPostCachedRowCount(getPageSize() * 3);
		tm.setRowCount(1000);
		return tm;
	}

	private DefaultTableDefinition<PropertiesDTO> createTableDefinition() {
		tableDefinition = new DefaultTableDefinition<PropertiesDTO>();

		tableDefinition.setRowRenderer(new DefaultRowRenderer<PropertiesDTO>());

		{
			IdColumnDefinition columnDef = new IdColumnDefinition();
			columnDef.setColumnSortable(false);
			columnDef.setColumnTruncatable(false);
			columnDef.setPreferredColumnWidth(35);
			columnDef.setCellRenderer(getCellRenderer());
			columnDef.setHeaderTruncatable(false);
			tableDefinition.addColumnDefinition(columnDef);
		}
		// text

		return tableDefinition;
	}

}
