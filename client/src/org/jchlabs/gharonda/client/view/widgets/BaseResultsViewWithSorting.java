package org.jchlabs.gharonda.client.view.widgets;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.gwtplatform.mvp.client.UiHandlers;

public abstract class BaseResultsViewWithSorting<H extends UiHandlers> extends BaseResultsView<H> {
	@UiField
	public ListBox pTypesList;
	@UiField
	public ListBox priceTypesList;

	@UiField
	public ListBox sortTypesList;

}
