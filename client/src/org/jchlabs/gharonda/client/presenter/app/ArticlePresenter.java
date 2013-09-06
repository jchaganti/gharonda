package org.jchlabs.gharonda.client.presenter.app;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class ArticlePresenter extends AbstractAppPresenter<ArticlePresenter.MyView, ArticlePresenter.MyProxy> {
	/**
	 * {@link ArticlePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.articlePage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<ArticlePresenter> {
	}

	/**
	 * {@link ArticlePresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView {
		void loadArticle(String html);
	}

	private static final String NUM = "num";
	private static final String URL = "url";
	private int num;
	private String url;

	@Inject
	public ArticlePresenter(final DispatchAsync dispatcher, final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(dispatcher, eventBus, view, proxy);
		registerJSFuncs();
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		url = request.getParameter(URL, "buy");
		String numStr = request.getParameter(NUM, "-1");
		num = Integer.parseInt(numStr);
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.articles;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.articles;
	}

	public native void registerJSFuncs() /*-{
											var parent = this;
											$wnd.loadURLIntoDiv = function(url, size) {
											parent.@com.Gharonda.client.presenter.app.ArticlePresenter::doGet(Ljava/lang/String;I)(url, size);			
											};
											}-*/;

	@Override
	protected void onReset() {
		super.onReset();
		doGet(url, num);

	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void doGet(String url, final int size) {
		url = GWT.getModuleBaseURL() + "articles/" + url;
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try {
			Request response = builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("onError");
				}

				public void onResponseReceived(Request request, Response response) {
					getView().loadArticle(response.getText());
					showFeaturedProperties();
				}
			});
		} catch (RequestException e) {
			// Code omitted for clarity
		}
	}

	@Override
	protected int getFeaturedListingsSize() {
		return num;
	}
}
