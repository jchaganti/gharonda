package org.jchlabs.gharonda.client;

/**
 * The central location of all name tokens for the application. All {@link ProxyPlace} classes get their tokens from
 * here. This class also makes it easy to use name tokens as a resource within UIBinder xml files.
 * <p />
 * This class uses both static variables and static getters. The reason for this is that, if you want to use
 * {@code com.gwtplatform.mvp.client.annotations.NameTokens} in a UiBinder file, you can only access static methods of
 * the class. On the other hand, when you use the {@literal @}{@link com.gwtplatform.mvp.client.annotations.NameToken}
 * annotation, you can only refer to a static variable.
 * 
 */
public class NameTokens {

	public static final String homePage = "!home";
	public static final String resultsWOMapPage = "!results-wo-map";
	public static final String resultsWMapPage = "!results-w-map";
	public static final String myListingsPage = "!my-listings";
	public static final String myProfilePage = "!my-profile";
	public static final String myFavoritesPage = "!my-favorites";
	public static final String myNotifierProfilePage = "!my-notifier-profile";
	public static final String addPropertyPage = "!add-Property";
	public static final String propertyDetailsWMapPage = "!details-w-map";
	public static final String propertyDetailsWOMapPage = "!details-wo-map";
	public static final String propertyDetailsMortgagePage = "!details-mortgage";
	public static final String advSearchPage = "!adv-search";
	public static final String articlePage = "!article";
	public static final String contactUsPage = "!contactUs";
	public static final String buyPage = "!buy";
	public static final String compareListingPage = "!compareListing";

	public static String getHomePage() {
		return homePage;
	}

	public static String getResultsWOMapPage() {
		return resultsWOMapPage;
	}

	public static String getResultsWMapPage() {
		return resultsWMapPage;
	}

	public static String getMyListingsPage() {
		return myListingsPage;
	}

	public static String getMyProfilePage() {
		return myProfilePage;
	}

	public static String getMyFavoritesPage() {
		return myFavoritesPage;
	}

	public static String getMyNotifierProfilePage() {
		return myNotifierProfilePage;
	}

	public static String getAddPropertyPage() {
		return addPropertyPage;
	}

	public static String getPropertyDetailsWMapPage() {
		return propertyDetailsWMapPage;
	}

	public static String getPropertyDetailsWOMapPage() {
		return propertyDetailsWOMapPage;
	}

	public static String getPropertyDetailsMortgagePage() {
		return propertyDetailsMortgagePage;
	}

	public static String getAdvSearchPage() {
		return advSearchPage;
	}

	public static String getArticlePage() {
		return articlePage;
	}

	public static String getContactUsPage() {
		return contactUsPage;
	}

	public static String getBuyPage() {
		return buyPage;
	}

	public static String getCompareListingPage() {
		return compareListingPage;
	}

}
