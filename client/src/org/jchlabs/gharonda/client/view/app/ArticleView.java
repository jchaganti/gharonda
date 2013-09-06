package org.jchlabs.gharonda.client.view.app;

import org.jchlabs.gharonda.client.presenter.app.ArticlePresenter.MyView;
import org.jchlabs.gharonda.client.view.AbstractAppView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;

public class ArticleView extends AbstractAppView<UiHandlers> implements MyView {
	interface ArticleViewUiBinder extends UiBinder<Widget, ArticleView> {
	}

	@UiField
	HTMLPanel contentPanel;
	@UiField
	HTMLPanel articlePanel;
	@UiField
	HTMLPanel featuredListing;
	private static ArticleViewUiBinder uiBinder = GWT.create(ArticleViewUiBinder.class);

	private final Widget widget;

	public ArticleView() {
		super();
		widget = uiBinder.createAndBindUi(this);
		contentPanel.getElement().setId("content");
		articlePanel.getElement().setId("articlePanel");
		setFeaturedistingPanel(featuredListing);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void loadArticle(String html) {
		setWindowTitle("Bilgi Bankası - Gharonda.com");
		articlePanel.clear();
		articlePanel.add(new HTML(html), "articlePanel");
	}
}
