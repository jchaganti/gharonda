package org.jchlabs.gharonda.client.view.account;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.jchlabs.gharonda.client.presenter.account.MyListingsPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gen2.table.client.FixedWidthFlexTable;
import com.google.gwt.gen2.table.client.FixedWidthGrid;
import com.google.gwt.gen2.table.client.ScrollTable;
import com.google.gwt.gen2.table.client.SelectionGrid;
import com.google.gwt.gen2.table.client.SortableGrid;
import com.google.gwt.gen2.table.client.AbstractScrollTable.ScrollPolicy;
import com.google.gwt.gen2.table.client.AbstractScrollTable.SortPolicy;
import com.google.gwt.gen2.table.client.SelectionGrid.SelectionGridRowFormatter;
import com.google.gwt.gen2.table.client.SortableGrid.ColumnSorterCallback;
import com.google.gwt.gen2.table.client.TableModelHelper.ColumnSortList;
import com.google.gwt.gen2.table.event.client.RowHighlightEvent;
import com.google.gwt.gen2.table.event.client.RowHighlightHandler;
import com.google.gwt.gen2.table.event.client.RowUnhighlightEvent;
import com.google.gwt.gen2.table.event.client.RowUnhighlightHandler;
import com.google.gwt.gen2.table.event.client.TableEvent.Row;
import com.google.gwt.gen2.table.override.client.FlexTable.FlexCellFormatter;
import com.google.gwt.gen2.table.override.client.HTMLTable.CellFormatter;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MyListingsView extends AbstractAppView<MyListingsUiHandlers> implements MyView {

	private static final MyListingsViewUiBinder uiBinder = GWT.create(MyListingsViewUiBinder.class);

	interface MyListingsViewUiBinder extends UiBinder<Widget, MyListingsView> {

	};

	@UiField
	Anchor refresh;

	@UiField
	Anchor submitAd;

	@UiField
	Anchor activateAd;

	@UiField
	HTMLPanel propertiesGridPanel;

	@UiField
	HTMLPanel contentPanel;

	ScrollTable propertiesGrid;

	List<PropertiesDTO> curList;
	private final Widget widget;

	@Inject
	public MyListingsView() {
		widget = uiBinder.createAndBindUi(this);
		this.contentPanel.getElement().setId("content");
		this.propertiesGridPanel.getElement().setId("propertiesGridPanel");
		this.propertiesGridPanel.setVisible(true);
		FixedWidthGrid dataTable = createDataTable();
		propertiesGrid = new ScrollTable(dataTable, createHeader());
		propertiesGrid.setSortPolicy(SortPolicy.SINGLE_CELL);
		propertiesGrid.setResizePolicy(ScrollTable.ResizePolicy.FILL_WIDTH);
		propertiesGrid.setScrollPolicy(ScrollPolicy.DISABLED);
		propertiesGrid.setHeight("300px");
		propertiesGrid.setMaximumColumnWidth(0, 20);
		propertiesGrid.setMaximumColumnWidth(1, 50);
		propertiesGrid.setMaximumColumnWidth(2, 50);
		propertiesGrid.setMaximumColumnWidth(3, 100);
		propertiesGrid.setMaximumColumnWidth(5, 110);
		propertiesGrid.setMaximumColumnWidth(6, 110);
		propertiesGrid.setMaximumColumnWidth(7, 80);
		propertiesGrid.setColumnSortable(0, false);
		propertiesGrid.setColumnSortable(1, false);
		propertiesGrid.setColumnSortable(2, false);
		propertiesGrid.setColumnSortable(3, true);
		propertiesGrid.setColumnSortable(4, true);
		propertiesGrid.setColumnSortable(5, true);
		propertiesGrid.setColumnSortable(6, true);
		propertiesGrid.setColumnSortable(7, true);
		this.propertiesGridPanel.add(propertiesGrid, "propertiesGridPanel");
	}

	private FixedWidthFlexTable createHeader() {
		FixedWidthFlexTable headerTable = new FixedWidthFlexTable();
		FlexCellFormatter formatter = headerTable.getFlexCellFormatter();
		headerTable.getElement().getStyle().setBackgroundColor("#005a9b");

		headerTable.setHTML(0, 0, null);
		formatter.setAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

		headerTable.setHTML(0, 1, null);
		formatter.setAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

		headerTable.setHTML(0, 2, null);
		formatter.setAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

		headerTable.setHTML(0, 3, "<span class=\"white_txt\">" + PropertyOptions.propertyID + "</span>");
		formatter.setAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

		headerTable.setHTML(0, 4, "<span class=\"white_txt\">" + PropertyOptions.title + "</span>");
		formatter.setAlignment(0, 4, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

		headerTable.setHTML(0, 5, "<span class=\"white_txt\">" + PropertyOptions.price + "</span>");
		formatter.setAlignment(0, 5, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

		headerTable.setHTML(0, 6, "<span class=\"white_txt\">" + PropertyOptions.modified + "</span>");
		formatter.setAlignment(0, 6, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

		headerTable.setHTML(0, 7, "<span class=\"white_txt\">" + PropertyOptions.status + "</span>");
		formatter.setAlignment(0, 7, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
		headerTable.setVisible(true);
		return headerTable;
	}

	private FixedWidthGrid createDataTable() {
		// Create a new table
		final FixedWidthGrid dataTable = new FixedWidthGrid();

		// Set some options in the data table
		dataTable.setSelectionPolicy(SelectionGrid.SelectionPolicy.ONE_ROW);

		dataTable.setSelectionEnabled(true);
		dataTable.setVisible(true);
		SortableGrid.ColumnSorter sorter = new SortableGrid.ColumnSorter() {

			@Override
			public void onSortColumn(SortableGrid grid, ColumnSortList sortList, ColumnSorterCallback callback) {
				final int column = sortList.getPrimaryColumn();
				final Integer[] originalOrder = new Integer[grid.getRowCount()];
				for (int i = 0; i < originalOrder.length; i++) {
					originalOrder[i] = i;
				}
				Arrays.sort(originalOrder, new Comparator<Integer>() {
					public int compare(Integer first, Integer second) {
						PropertiesDTO p1 = curList.get(first);
						PropertiesDTO p2 = curList.get(second);
						switch (column) {
						case 3:
							return p1.getId().compareTo(p2.getId());
						case 4:
							return p1.getTitle().compareTo(p2.getTitle());
						case 5:
							return p1.getPrice().compareTo(p2.getPrice());
						case 6:
							return p1.getTimeStamp().compareTo(p2.getTimeStamp());
						case 7: {
							if (p1.getIsactive() != null && p2.getIsactive() != null) {
								return p1.getIsactive().compareTo(p2.getIsactive());
							} else if (p1.getIsactive() == null && p2.getIsactive() != null) {
								return -1;
							} else if (p2.getIsactive() == null && p1.getIsactive() != null) {
								return 1;
							} else {
								return 0;
							}
						}

						default:
							return 1;
						}
					}
				});

				int[] resultOrder = new int[originalOrder.length];
				for (int i = 0; i < originalOrder.length; i++) {
					if (sortList.isPrimaryAscending()) {
						resultOrder[i] = originalOrder[i];
					} else {
						resultOrder[resultOrder.length - i - 1] = originalOrder[i];
					}
				}

				Element[] trEls = new Element[originalOrder.length];
				for (int i = 0; i < originalOrder.length; i++) {
					PropertiesDTO p = curList.get(resultOrder[i]);
					Element trEl = DOM.getParent(DOM.getParent(DOM.getElementById("pid_" + p.getId())));
					trEls[i] = trEl;
					if (i % 2 == 1) {
						trEl.getStyle().setBackgroundColor("#fcfef0");
					} else {
						trEl.getStyle().setBackgroundColor("#ffffff");
					}
				}
				callback.onSortingComplete(trEls);

			}

		};
		dataTable.setColumnSorter(sorter);
		dataTable.addRowHighlightHandler(new RowHighlightHandler() {

			@Override
			public void onRowHighlight(RowHighlightEvent event) {
				if (event.getValue().getRowIndex() != -1) {
					Row row = event.getValue();
					FixedWidthGrid.FixedWidthGridRowFormatter formatter = (FixedWidthGrid.FixedWidthGridRowFormatter) dataTable
							.getRowFormatter();
					formatter.getElement(row.getRowIndex()).getStyle().setBackgroundColor("#ABCDEF");
				}
			}

		});

		dataTable.addRowUnhighlightHandler(new RowUnhighlightHandler() {

			@Override
			public void onRowUnhighlight(RowUnhighlightEvent event) {

				if (event.getValue().getRowIndex() != -1) {
					Row row = event.getValue();
					FixedWidthGrid.FixedWidthGridRowFormatter formatter = (FixedWidthGrid.FixedWidthGridRowFormatter) dataTable
							.getRowFormatter();
					if (row.getRowIndex() % 2 == 1) {
						formatter.getElement(row.getRowIndex()).getStyle().setBackgroundColor("#FCFEF0");
					} else {
						formatter.getElement(row.getRowIndex()).getStyle().setBackgroundColor("#FFFFFF");
					}
				}

			}

		});
		// Return the data table
		return dataTable;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiHandler("submitAd")
	void onSignInClicked(ClickEvent event) {
		getUiHandlers().handleModifyProperty(null);
	}

	@UiHandler({ "myProfile", "myFavorites", "myNotifierProfile" })
	void onMyAccountClicked(ClickEvent event) {
		getUiHandlers().handleMyAccountClicked();
	}

	@Override
	public void displayProperties(List<PropertiesDTO> list) {
		setWindowTitle("İlanlarım - Gharonda.com");
		curList = list;
		FixedWidthGrid dataTable = propertiesGrid.getDataTable();
		dataTable.resize(list.size(), 8);
		int i = 0;
		for (PropertiesDTO p : list) {
			SelectionGridRowFormatter formatter = dataTable.getSelectionGridRowFormatter();
			if (i % 2 == 1) {
				formatter.getElement(i).getStyle().setBackgroundColor("#fcfef0");
			}
			CellFormatter cellFormatter = dataTable.getCellFormatter();
			dataTable.setWidget(i, 0, createCheckBox(p.getId()));

			dataTable.setWidget(i, 1, createModifyAnchor(p.getId()));
			cellFormatter.setAlignment(i, 1, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

			dataTable.setWidget(i, 2, createDeleteAnchor(p.getId()));
			cellFormatter.setAlignment(i, 2, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
			String locale = PropertyOptions.getCurrentLocale();
			String detailsUrl = "javascript:window.open(\""
					+ PropertyOptions.getPropertyDetailPageUrl(p.getId(), locale) + "\");return false;";
			dataTable.setHTML(i, 3, "# <span style=\"font-weight:bold;color:#000;\" id=" + "pid_" + p.getId() + ">"
					+ "<a style=\"color:#000;\" onClick='" + detailsUrl + "'>" + p.getId() + "</a>" + " </span>");
			cellFormatter.setAlignment(i, 3, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

			dataTable.setHTML(i, 4, "<span   id=" + "title_" + p.getId() + ">" + "<a style=\"color:#000;\" onClick='"
					+ detailsUrl + "'>" + p.getTitle() + "</a>" + " </span>");
			cellFormatter.setAlignment(i, 4, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

			Integer price = (p.getPrice() == null) ? 0 : p.getPrice();
			NumberFormat nf = NumberFormat.getCurrencyFormat(PropertyOptions.currencyMap.get(p.getCurrency()));

			String value = nf.format(price);
			value = (p.getCurrency() < 2) ? value.replaceFirst("TL", "") + " TL" : value;

			dataTable.setHTML(i, 5, value);
			cellFormatter.setAlignment(i, 5, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

			dataTable.setHTML(i, 6, PropertyOptions.getFormattedDate(p.getTimeStamp()));

			cellFormatter.setAlignment(i, 6, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

			// String isActive = (i%2 == 0) ?
			// "<img width=\"22\" height=\"23\" src=\"images/Red-x.GIF\"/>"
			// : "<img width=\"22\" height=\"23\" src=\"images/tick2.gif\"/>";
			String isActive = (p.getIsactive() == null || p.getIsactive() == 0) ? "<img width=\"22\" height=\"23\" src=\"images/Red-x.GIF\"/>"
					: "<img width=\"22\" height=\"23\" src=\"images/tick2.gif\"/>";

			cellFormatter.setAlignment(i, 7, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
			dataTable.setHTML(i, 7, isActive);
			i++;
		}
		propertiesGrid.redraw();
	}

	private Widget createCheckBox(Integer id) {
		CheckBox cb = new CheckBox();
		cb.setFormValue(id.toString());

		return cb;
	}

	private Widget createModifyAnchor(final Integer pid) {
		Anchor anchor = new Anchor(PropertyOptions.modify);
		anchor.getElement().getStyle().setProperty("color", "#000");
		anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				getUiHandlers().handleModifyProperty(pid);
			}

		});
		return anchor;
	}

	private Widget createDeleteAnchor(final Integer pid) {
		Anchor anchor = new Anchor(PropertyOptions.delete);
		anchor.getElement().getStyle().setProperty("color", "#000");
		anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getUiHandlers().handleDeleteProperty(pid);
			}

		});
		return anchor;
	}
}
