package org.jchlabs.gharonda.client.view;

import java.util.List;

import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public abstract class AbstractAppView<V extends UiHandlers> extends ViewWithUiHandlers<V> {

	private HTMLPanel featuredListing;

	public AbstractAppView() {
		super();
	}

	public void loadFeaturedListings(List<PropertiesDTO> featuredProperties, int reqSize) {
		if (featuredListing != null) {
			featuredListing.clear();
			List<PropertiesDTO> list = featuredProperties.size() < reqSize ? featuredProperties : featuredProperties
					.subList(0, reqSize);
			String html = PropertyOptions.getFeaturedListingsHTML(getDivName(), getColumns(), getWidth(), list);
			HTMLPanel flPanel = new HTMLPanel(html);
			featuredListing.add(flPanel, "featuredListing");
		}

	}

	protected int getColumns() {
		return 1;
	}

	protected int getWidth() {
		return 177;
	}

	protected String getDivName() {
		return "featured_inner1";
	}

	protected void setFeaturedistingPanel(HTMLPanel featuredListing) {
		this.featuredListing = featuredListing;
		featuredListing.getElement().setId("featuredListing");
	}

	protected void setWindowTitle(String title) {
		if (Document.get() != null) {
			Document.get().setTitle(title);
		}
	}

}
