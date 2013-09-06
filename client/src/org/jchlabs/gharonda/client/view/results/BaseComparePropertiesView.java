package org.jchlabs.gharonda.client.view.results;

import java.util.List;

import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.client.view.widgets.CompareListingCellRenderer;
import org.jchlabs.gharonda.client.view.widgets.ComparePropertiesTableModel;
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

public abstract class BaseComparePropertiesView extends AbstractAppView<CompareListingUiHandlers> {

	protected final class IdColumnDefinition extends AbstractColumnDefinition<List<PropertiesDTO>, Integer> {
		@Override
		public Integer getCellValue(List<PropertiesDTO> rowValue) {
			return new Integer(1);
		}

		@Override
		public void setCellValue(List<PropertiesDTO> rowValue, Integer cellValue) {
		}
	}

	@UiField
	public HTMLPanel propertiesGridPanel;
	@UiField
	public HTMLPanel paging;

	protected PagingScrollTable<List<PropertiesDTO>> propertiesGrid;
	protected CachedTableModel<List<PropertiesDTO>> cachedTableModel = null;

	protected DefaultTableDefinition<List<PropertiesDTO>> tableDefinition = null;

	protected ComparePropertiesTableModel tableModel = null;

	public void showCompareProperties(List<List<Integer>> list, List<List<PropertiesDTO>> pList, int realCount) {
		tableModel.setpList(list);
		tableModel.setData(pList);
		// reset the table model row count
		tableModel.setRowCount(realCount);
		// clear the cache
		cachedTableModel.clearCache();
		// reset the cached model row count
		cachedTableModel.setRowCount(realCount);
		// force to page zero with a reload
		propertiesGrid.gotoPage(0, true);

	}

	protected PagingScrollTable<List<PropertiesDTO>> createScrollTable() {

		// add it to cached table model
		cachedTableModel = createCachedTableModel(tableModel);

		// create the table definition
		TableDefinition<List<PropertiesDTO>> tableDef = createTableDefinition();

		// create the paging scroll table
		PagingScrollTable<List<PropertiesDTO>> pagingScrollTable = new PagingScrollTable<List<PropertiesDTO>>(
				cachedTableModel, tableDef);
		pagingScrollTable.setPageSize(getPageSize());
		pagingScrollTable.setEmptyTableWidget(new HTML("There is no data to display"));
		pagingScrollTable.getDataTable().setSelectionPolicy(SelectionPolicy.ONE_ROW);

		// setup the bulk renderer
		FixedWidthGridBulkRenderer<List<PropertiesDTO>> bulkRenderer = new FixedWidthGridBulkRenderer<List<PropertiesDTO>>(
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

	protected CellRenderer<List<PropertiesDTO>, Integer> getCellRenderer() {
		return new CompareListingCellRenderer();
	}

	protected int getPageSize() {
		return 1;
	}

	private CachedTableModel<List<PropertiesDTO>> createCachedTableModel(ComparePropertiesTableModel tableModel) {
		CachedTableModel<List<PropertiesDTO>> tm = new CachedTableModel<List<PropertiesDTO>>(tableModel);
		tm.setPreCachedRowCount(getPageSize() * 3);
		tm.setPostCachedRowCount(getPageSize() * 3);
		tm.setRowCount(10);
		return tm;
	}

	private DefaultTableDefinition<List<PropertiesDTO>> createTableDefinition() {
		tableDefinition = new DefaultTableDefinition<List<PropertiesDTO>>();

		tableDefinition.setRowRenderer(new DefaultRowRenderer<List<PropertiesDTO>>());

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
