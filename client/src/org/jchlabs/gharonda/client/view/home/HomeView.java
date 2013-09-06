package org.jchlabs.gharonda.client.view.home;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.home.HomePresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchProfile;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.GetSearchProperties;
import org.jchlabs.gharonda.shared.rpc.GetSearchPropertiesResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class HomeView extends AbstractAppView<HomeUiHandlers> implements MyView {
	interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
	}

	@UiField
	HTMLPanel contentPanel;
	@UiField
	HTMLPanel flashPanel;
	@UiField
	HTMLPanel mostCommentsLinks;

	@UiField
	HTMLPanel topNewsLinks;
	@UiField
	HTMLPanel bargains;

	@UiField
	HTMLPanel justListed;

	@UiField
	ListBox modeTypesList;

	@UiField
	ListBox priceTypesList;

	@UiField
	ListBox pTypesList;

	@UiField
	ListBox sftTypesList;

	@UiField
	Anchor basicSearch;

	@UiField
	TextBox searchText;
	@UiField
	HTMLPanel searchTextPanel;

	SuggestBox searchTextBox;

	@UiField
	Hyperlink advSearch1;
	@UiField
	HTMLPanel featuredListing;
	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

	private final Widget widget;
	private final PlaceManager placeManager;
	private final DispatchAsync dispatcher;
	private String html = "<object  width=\"380\" height=\"150\">	<param name=\"movie\" value=\"images/builders_slide/slideShow.swf\"/>			<param name=\"wmode\" value=\"transparent\"/>			<embed src=\"images/builders_slide/slideShow.swf\" width=\"380\"				height=\"150\" wmode=\"transparent\">			</embed>		</object>";
	private boolean flashAdded = false;

	@Inject
	public HomeView(final PlaceManager placeManager, final DispatchAsync dispatcher) {
		widget = uiBinder.createAndBindUi(this);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.contentPanel.getElement().setId("content");
		for (String type : PropertyOptions.MODE_TYPES) {
			modeTypesList.addItem(type);
		}

		pTypesList.addItem(PropertyOptions.any);
		for (String type : PropertyOptions.P_TYPES) {
			pTypesList.addItem(type);
		}

		priceTypesList.addItem(PropertyOptions.any);
		for (String priceType : PropertyOptions.PRICE_TYPES) {
			priceTypesList.addItem(priceType);
		}
		sftTypesList.addItem(PropertyOptions.any);
		for (String sftType : PropertyOptions.SQFT_TYPES) {
			sftTypesList.addItem(sftType);
		}

		searchText.setValue(PropertyOptions.INIT_STR);
		searchTextPanel.getElement().setId("searchText");
		PropertiesSuggestOracle propertiesOracle = new PropertiesSuggestOracle();
		searchTextBox = new SuggestBox(propertiesOracle, searchText);
		searchTextBox.showSuggestionList();
		searchTextBox.setAnimationEnabled(true);
		searchTextBox.setAutoSelectEnabled(false);
		searchTextBox.setStyleName("text_field");
		searchTextBox.setWidth("520px");
		searchTextPanel.add(searchTextBox, "searchText");

		bargains.getElement().setId("bargains");
		justListed.getElement().setId("justListed");
		mostCommentsLinks.setVisible(false);
		setFeaturedistingPanel(featuredListing);
		flashPanel.getElement().setId("flash");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiHandler("basicSearch")
	public void basicSearchClicked(ClickEvent event) {
		List<SearchCriteriaIFace> stList = getSearchTextCriteriaList();
		List<SearchCriteriaIFace> ssList = getSearchSelectionsCriteriaList();
		if (stList != null) {
			ssList.addAll(stList);
			SearchProfile listSearchProfile = new SearchProfile(PropertyOptions.DEFAULT_LIST_SEARCH_PROFILE, null,
					ssList);
			int mode = modeTypesList.getSelectedIndex() + 1;
			getUiHandlers().handleBasicSearch(listSearchProfile, null, mode);
		}

	}

	@UiHandler("searchText")
	public void searchTextClicked(ClickEvent event) {
		if (searchText.getValue().equals(PropertyOptions.INIT_STR)) {
			searchText.setValue("");
		}
	}

	@Override
	public void loadBargains(List<PropertiesDTO> plist) {
		if (!flashAdded) {
			flashPanel.add(new HTML(html), "flash");
			flashAdded = true;
		}

		setWindowTitle("Sadece Sahibinden Satılık ve Kiralık Emlak İlanları - Gharonda.com");
		String html = PropertyOptions.getJustListedAndBargainsHTML("graybox_inner4", 176, plist);
		HTMLPanel flPanel = new HTMLPanel(html);
		bargains.clear();
		bargains.add(flPanel, "bargains");

	}

	@Override
	public void loadJustListed(List<PropertiesDTO> plist) {
		searchText.setValue(PropertyOptions.INIT_STR);
		String html = PropertyOptions.getJustListedAndBargainsHTML("graybox_inner4", 176, plist);
		HTMLPanel flPanel = new HTMLPanel(html);
		justListed.clear();
		justListed.add(flPanel, "justListed");
	}

	@UiHandler({ "notifier1", "notifier2", "notifier3" })
	public void notifierClicked(ClickEvent event) {
		getUiHandlers().handleNotifier();
	}

	@UiHandler({ "woAgent1", "woAgent2" })
	public void woAgentClicked(ClickEvent event) {
		fireArticleURL("About Us/about_us_04_neden_sahibinden.html", 4);
	}

	@UiHandler({ "searchMap1", "searchMap2" })
	public void searchMapClicked(ClickEvent event) {
		PlaceRequest req = new PlaceRequest(NameTokens.resultsWMapPage);
		req = req.with("mode", "1");
		placeManager.revealRelativePlace(req, 1);
	}

	@UiHandler({ "aboutUs1", "aboutUs2" })
	public void aboutUsClicked(ClickEvent event) {
		fireArticleURL("About Us/about_us_02_neden_Gharonda.html", 3);
	}

	@UiHandler({ "compareSites1", "compareSites2" })
	public void compareSitesClicked(ClickEvent event) {
		fireArticleURL("Quick Links/Compare_our_services.html", 4);
	}

	@UiHandler("advSearch1")
	public void onAdvSearchClicked(ClickEvent event) {
		getUiHandlers().handleAdvSearch();
	}

	@UiHandler("mostComments")
	public void onMostCommentsClicked(ClickEvent event) {
		topNewsLinks.setVisible(false);
		mostCommentsLinks.setVisible(true);
	}

	@UiHandler("topNews")
	public void onTopNewsClicked(ClickEvent event) {
		topNewsLinks.setVisible(true);
		mostCommentsLinks.setVisible(false);
	}

	protected int getColumns() {
		return 2;
	}

	protected int getWidth() {
		return 374;
	}

	protected String getDivName() {
		return "featured_inner";
	}

	private Integer getPropertyID(String s) {
		Integer result = null;
		try {
			result = Integer.parseInt(s);
			if (result <= 0) {
				result = null;
			}
		} catch (NumberFormatException e) {
			result = null;
		}

		return result;
	}

	private List<SearchCriteriaIFace> getSearchSelectionsCriteriaList() {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();

		if (priceTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPriceSearchCriteria(priceTypesList.getSelectedIndex()));
		}

		if (sftTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedSftSearchCriteria(sftTypesList.getSelectedIndex()));
		}

		if (pTypesList.getSelectedIndex() > 0) {
			cList.add(PropertyOptions.getSelectedPropertyTypeSearchCriteria(pTypesList.getSelectedIndex()));
		}

		return cList;
	}

	private List<SearchCriteriaIFace> getSearchTextCriteriaList() {
		List<SearchCriteriaIFace> cList = null;
		String searchTextVal = searchText.getValue();
		if (searchTextVal == null || searchTextVal.equals(PropertyOptions.INIT_STR) || searchTextVal.trim().isEmpty()) {
			Window.alert(PropertyOptions.enterSearchText);
		} else {
			if (!searchTextVal.trim().equals(PropertyOptions.noPropertyFound)) {
				cList = getSearchTextCriteriaList(searchTextVal.split(", "));
			}
		}

		return cList;
	}

	private List<SearchCriteriaIFace> getSearchTextCriteriaList(String searchTextVal) {
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		Integer id = getPropertyID(searchTextVal);
		if (id == null) {
			cList.add(PropertyOptions.getCitySearchCriteria(searchTextVal));
			cList.add(PropertyOptions.getSuburbSearchCriteria(searchTextVal));
			cList.add(PropertyOptions.getStateSearchCriteria(searchTextVal));

		} else {
			cList.add(PropertyOptions.getPropertyIdSearchCriteria(id));
			cList.add(PropertyOptions.getPostCodeSearchCriteria(searchTextVal));
		}
		return cList;
	}

	private List<SearchCriteriaIFace> getSearchTextCriteriaList(String[] searchTokens) {
		int tokenslen = searchTokens != null ? searchTokens.length : 0;
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		if (tokenslen > 0 && !searchTokens[0].trim().isEmpty()) {
			cList.add(PropertyOptions.getSuburbSearchCriteria(searchTokens[0].trim()));
		} else {
			return null;
		}
		if (tokenslen > 1 && !searchTokens[1].trim().isEmpty()) {
			cList.add(PropertyOptions.getCitySearchCriteria(searchTokens[1].trim()));
		}
		if (tokenslen > 2 && !searchTokens[2].trim().isEmpty()) {
			cList.add(PropertyOptions.getStateSearchCriteria(searchTokens[2].trim()));
		}
		if (tokenslen > 3 && !searchTokens[3].trim().isEmpty()) {
			cList.add(PropertyOptions.getPostCodeSearchCriteria(searchTokens[3].trim()));
		}
		return cList;
	}

	private void fireArticleURL(String url, int num) {
		placeManager.revealRelativePlace(
				new PlaceRequest(NameTokens.articlePage).with("url", url).with("num", new Integer(num).toString()), 1);

	}

	private class PropertiesSuggestOracle extends SuggestOracle {

		@Override
		public void requestSuggestions(final Request request, final Callback callback) {
			String searchTextVal = request.getQuery();
			if (!searchTextVal.trim().isEmpty()) {
				List<SearchCriteriaIFace> stList = getSearchTextCriteriaList(searchTextVal);
				int mode = modeTypesList.getSelectedIndex() + 1;
				List<SearchCriteriaIFace> slCriteria = new ArrayList<SearchCriteriaIFace>();
				PropertyOptions.adjustForPModeSearchCriteria(slCriteria, mode);
				GetSearchProperties action = new GetSearchProperties(PropertyOptions.DEFAULT_SUGGESTBOX_SEARCH_PROFILE,
						slCriteria, stList, true, CommonConstants.SUGGESTBOX_RESULTS_SIZE);
				dispatcher.execute(action, new AsyncCallback<GetSearchPropertiesResult>() {
					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(GetSearchPropertiesResult result) {
						Set<PropertiesSuggestion> suggestions = new HashSet<PropertiesSuggestion>();
						if (result.getSearchProperties().size() == 0) {
							suggestions.add(new PropertiesSuggestion(PropertyOptions.noPropertyFound));
						} else {
							for (PropertiesDTO p : result.getSearchProperties()) {
								suggestions.add(new PropertiesSuggestion(p.getSuburb() + ", " + p.getCity() + ", "
										+ p.getState() + ", " + p.getPostCode()));
							}
						}

						SuggestOracle.Response response = new SuggestOracle.Response();
						response.setSuggestions(suggestions);
						callback.onSuggestionsReady(request, response);
					}
				});
			}

		}

	}

	private class PropertiesSuggestion implements Suggestion {

		private String display;

		public PropertiesSuggestion(String display) {
			this.display = display;
		}

		@Override
		public String getDisplayString() {
			return display;
		}

		@Override
		public String getReplacementString() {
			return display;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((display == null) ? 0 : display.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PropertiesSuggestion other = (PropertiesSuggestion) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (display == null) {
				if (other.display != null)
					return false;
			} else if (!display.equals(other.display))
				return false;
			return true;
		}

		private HomeView getOuterType() {
			return HomeView.this;
		}

	}
}
