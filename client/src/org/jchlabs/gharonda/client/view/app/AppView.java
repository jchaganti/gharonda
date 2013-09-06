package org.jchlabs.gharonda.client.view.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.domain.model.Users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class AppView extends ViewWithUiHandlers<AppUiHandlers> implements MyView {

	interface AppViewUiBinder extends UiBinder<Widget, AppView> {

	};

	private class LoadMenuItemCommand implements Command {

		private String nameToken;
		private Map<String, String> params;

		public LoadMenuItemCommand(String nameToken, Map<String, String> params) {
			this.nameToken = nameToken;
			this.params = params;
		}

		@Override
		public void execute() {
			PlaceRequest req = new PlaceRequest(nameToken);
			if (params != null) {
				for (Entry<String, String> entry : params.entrySet()) {
					req = req.with(new String(entry.getKey()), new String(entry.getValue()));
				}
			}
			placeManager.revealRelativePlace(req, 1);
		}
	}

	public final Widget widget;
	private static final String buyUrl = "01 Buying/Buying_01_ilk_evimi_aliyorum.html";
	private static final String sellUrl = "02 Selling/Selling_01_satis_oncesi_hazirlik.html";
	private static final String rentUrl = "03 Renting/Renting_01_kira_nedir.html";
	private static final String moveUrl = "04 Moving/Moving_01_nakliye_firmasina_sorular.html";
	private static final String appraisalUrl = "05 Appraisal/appraisal_01_emlak_degerlemesi_nedir.html";
	private static final String mortgageUrl = "06 Mortgage/mortgage_01_mortgage_nedir.html";
	private static final String creditReportUrl = "07 Credit Report/Credit_Report_01_kredi_raporu_nedir.html";
	private static final String lawsUrl = "08 Laws/Laws_01_imar_kanunu.html";
	private static final String legalUrl = "09 LegalTransactions/Legal_Transactions_01_aile_konutu_serhi.html";
	private static final String mortgageCalcSvcUrl = "Services//services_mortgage.html";
	private static final String mortgageSvcUrl = "Services//services_mortgage.html";// 2
	private static final String appraisalSvcUrl = "Services//services_appraisal.html";// 2
	private static final String insuranceSvcUrl = "Services//services_insurance.html";
	private static final String termsUrl = "10 RealEstateTerms/Real_Estate_Terms_10_Emlak_Terimleri.html";
	private static final String movingSvcUrl = "Services//services_moving.html";// 2

	private static final AppViewUiBinder uiBinder = GWT.create(AppViewUiBinder.class);
	@UiField
	MenuItem buyMenu;
	@UiField
	MenuItem sellMenu;
	@UiField
	MenuItem rentMenu;
	@UiField
	MenuItem moveMenu;
	@UiField
	MenuItem appraisalMenu;
	@UiField
	MenuItem mortgageMenu;
	@UiField
	MenuItem creditReportMenu;
	@UiField
	MenuItem lawsMenu;
	@UiField
	MenuItem legalMenu;
	@UiField
	MenuItem termsMenu;
	@UiField
	MenuItem saleMapMenu;
	@UiField
	MenuItem rentMapMenu;
	@UiField
	MenuItem mortgageCalcSvcMenu;
	@UiField
	MenuItem mortgageSvcMenu;
	@UiField
	MenuItem appraisalSvcMenu;
	@UiField
	MenuItem insuranceSvcMenu;
	@UiField
	MenuItem movingSvcMenu;
	@UiField
	Anchor signIn;
	@UiField
	Anchor signOut;
	@UiField
	Anchor register;
	@UiField
	HTMLPanel breadCrumbPanel;
	@UiField
	FlowPanel breadcrumbs;
	@UiField
	Image flag;
	@UiField
	HTMLPanel contentPanel;
	@UiField
	HTMLPanel mainContent;
	@UiField
	HTMLPanel blur;

	@UiField
	HTML welcome;
	@UiField
	HTML heading;
	@UiField
	MenuBar services;
	@UiField
	MenuBar library;
	private final PlaceManager placeManager;

	@Inject
	public AppView(PlaceManager placeManager) {
		this.placeManager = placeManager;
		widget = uiBinder.createAndBindUi(this);
		this.mainContent.getElement().setId("wrapper");
		this.contentPanel.getElement().setId("contentWrapper");
		this.signOut.setVisible(false);
		buyMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(buyUrl, 5)));
		sellMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(sellUrl, 3)));
		rentMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(rentUrl, 3)));
		moveMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(moveUrl, 4)));
		appraisalMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(appraisalUrl, 5)));

		mortgageMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(mortgageUrl, 4)));
		creditReportMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(creditReportUrl, 3)));
		lawsMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(lawsUrl, 20)));
		legalMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(legalUrl, 7)));
		termsMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(termsUrl, 15)));
		mortgageCalcSvcMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(mortgageCalcSvcUrl,
				2)));
		;
		mortgageSvcMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(mortgageSvcUrl, 2)));
		;
		appraisalSvcMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(appraisalSvcUrl, 2)));
		;
		insuranceSvcMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(insuranceSvcUrl, 2)));
		;
		movingSvcMenu.setCommand(new LoadMenuItemCommand(NameTokens.articlePage, getReqParams(movingSvcUrl, 2)));
		;
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("mode", new Integer(1).toString());
		saleMapMenu.setCommand(new LoadMenuItemCommand(NameTokens.resultsWMapPage, reqParams));
		reqParams = new HashMap<String, String>();
		reqParams.put("mode", new Integer(2).toString());
		rentMapMenu.setCommand(new LoadMenuItemCommand(NameTokens.resultsWMapPage, reqParams));
		blur.setVisible(false);
	}

	private Map<String, String> getReqParams(String url, int num) {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("url", url);
		reqParams.put("num", new Integer(num).toString());
		return reqParams;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void blur() {
		blur.setVisible(true);
	}

	@Override
	public void initBreadcrumbs(int breadcrumbSize, String _heading) {
		breadCrumbPanel.setVisible(true);
		breadcrumbs.clear();
		heading.setHTML("<h1>" + _heading + "</h1>");
		for (int i = 0; i < breadcrumbSize; ++i) {
			if (i > 0) {
				breadcrumbs.add(new InlineLabel(" > "));
			}
			breadcrumbs.add(new InlineHyperlink("Loading title...", placeManager.buildRelativeHistoryToken(i + 1)));
		}

	}

	@UiHandler("advSearch")
	public void onAdvSearchClicked(ClickEvent event) {
		getUiHandlers().forwardPage(NameTokens.advSearchPage);
	}

	@UiHandler("contactUs")
	public void onContactUsClicked(ClickEvent event) {
		getUiHandlers().forwardPage(NameTokens.contactUsPage);
	}

	@Override
	public void setBreadcrumbs(int index, String title) {
		InlineHyperlink hyperlink = (InlineHyperlink) breadcrumbs.getWidget(index * 2);
		if (title == null) {
			hyperlink.setHTML("Unknown title");
		} else {
			hyperlink.setHTML(title);
		}

	}

	@Override
	public void setContent(Object slot, Widget content) {
		if (slot == AppPresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public void showFlag(String url) {
		flag.setUrl(url);
	}

	@Override
	public void showUserInfo(Users user) {
		Element el = DOM.getElementById("myAccount");
		if (user != null) {
			welcome.setHTML(PropertyOptions.welcome + " " + user.getFirstName());
			welcome.setVisible(true);
			signOut.setVisible(true);
			signIn.setVisible(false);
			register.setVisible(false);
			el.setInnerHTML(PropertyOptions.myAccount);
		} else {
			welcome.setVisible(false);
			signOut.setVisible(false);
			signIn.setVisible(true);
			register.setVisible(true);
			el.setInnerHTML(PropertyOptions.registerNow);
		}
	}

	@Override
	public void unBlur() {
		blur.setVisible(false);
	}

	@UiHandler("buy")
	void onBuyClicked(ClickEvent event) {
		getUiHandlers().handleBuyOrSell(null, null);
	}

	@UiHandler("flag")
	void onFlagClicked(ClickEvent event) {
		String locale = Window.Location.getParameter("locale");
		if (locale != null) {
			locale = locale.replaceAll("#", "");
		}
		String newlocale = "tr";
		if (locale == null || "tr".equals(locale)) {
			newlocale = "en";
		}
		loadUrl("locale", newlocale);
	}

	@UiHandler("sell")
	void onSell(ClickEvent event) {
		getUiHandlers().handleMyAccount(NameTokens.myListingsPage);
	}

	@UiHandler("registerNow")
	void onMyAccount(ClickEvent event) {
		getUiHandlers().handleRegisterNow(NameTokens.myListingsPage);
	}

	@UiHandler({ "newPropNotifier", "newPropNotifierBottom" })
	void onNewPropNotifierClicked(ClickEvent event) {
		getUiHandlers().handleMyAccount(NameTokens.myNotifierProfilePage);
	}

	@UiHandler("register")
	void onRegisterClicked(ClickEvent event) {
		getUiHandlers().showRegisterDialog();
	}

	@UiHandler("rent")
	void onSellClicked(ClickEvent event) {
		getUiHandlers().handleBuyOrSell(null, null);
	}

	@UiHandler("signIn")
	void onSignInClicked(ClickEvent event) {
		getUiHandlers().showLoginDialog();
	}

	@UiHandler("signOut")
	void onSignOutClicked(ClickEvent event) {
		getUiHandlers().handleLogout();
	}

	private native void loadUrl(String key, String value) /*-{
															key = escape(key); value = escape(value);
															var url = "";
															var hrefTokens = top.location.href.split('#');
															var qpTokens = hrefTokens[0].split('?');
															var qpSize  = qpTokens.length;
															if(qpSize > 1) {
															var qps = qpTokens[1];
															var kvp = qps.split('&');
															
															var i=kvp.length; 
															
															var x;
															while(i--) 
															{
															x = kvp[i].split('=');
															
															if (x[0]==key)
															{
															x[1] = value;
															kvp[i] = x.join('=');
															break;
															}
															}
															
															if(i<0) {kvp[kvp.length] = [key,value].join('=');}
															var qpstr =  kvp.join('&');
															
															url = url +qpTokens[0] + "?" + qpstr;
															}
															else {
															url = url +qpTokens[0] + "?" + key + "=" + value;
															}
															
															if(typeof hrefTokens[1] != "undefined") {
															url = url + "#" + hrefTokens[1];
															} 
															top.location.href = url;
															}-*/;

	private void setMainContent(Widget content) {
		contentPanel.clear();
		if (content != null) {
			contentPanel.add(content, "contentWrapper");
		}
	}

	@Override
	public void hideBreadCrumbs() {
		breadcrumbs.clear();
		breadCrumbPanel.setVisible(false);

	}
}
