package org.jchlabs.gharonda.client.gin;

import org.jchlabs.gharonda.client.presenter.account.ModifyPropertyPresenter;
import org.jchlabs.gharonda.client.presenter.account.MyFavoritesPresenter;
import org.jchlabs.gharonda.client.presenter.account.MyListingsPresenter;
import org.jchlabs.gharonda.client.presenter.account.MyNotifierProfilePresenter;
import org.jchlabs.gharonda.client.presenter.account.MyProfilePresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.presenter.app.ArticlePresenter;
import org.jchlabs.gharonda.client.presenter.app.ContactUsPresenter;
import org.jchlabs.gharonda.client.presenter.details.MortgagePresenter;
import org.jchlabs.gharonda.client.presenter.details.PropertyDetailsWithMapPresenter;
import org.jchlabs.gharonda.client.presenter.details.PropertyDetailsWithoutMapPresenter;
import org.jchlabs.gharonda.client.presenter.home.AdvancedSearchPresenter;
import org.jchlabs.gharonda.client.presenter.home.HomePresenter;
import org.jchlabs.gharonda.client.presenter.results.CompareListingPresenter;
import org.jchlabs.gharonda.client.presenter.results.ResultWithMapPresenter;
import org.jchlabs.gharonda.client.presenter.results.ResultWithoutMapPresenter;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;

@GinModules({ DispatchAsyncModule.class, GharondaClientModule.class })
public interface GharondaGinjector extends Ginjector {
	PlaceManager getPlaceManager();

	EventBus getEventBus();

	DispatchAsync getDispatcher();

	ProxyFailureHandler getProxyFailureHandler();

	AsyncProvider<HomePresenter> getHomePresenter();

	AsyncProvider<AppPresenter> getAppPresenter();

	AsyncProvider<ArticlePresenter> getArticlePresenter();

	AsyncProvider<ResultWithoutMapPresenter> getResultWithoutMapPresenter();

	AsyncProvider<ResultWithMapPresenter> getResultWithMapPresenter();

	AsyncProvider<MyListingsPresenter> getMyListingsPresenter();

	AsyncProvider<MyProfilePresenter> getMyProfilePresenter();

	AsyncProvider<MyFavoritesPresenter> getMMyFavoritesPresenter();

	AsyncProvider<ModifyPropertyPresenter> getModifyPropertyPresenter();

	AsyncProvider<MyNotifierProfilePresenter> getMyNotifierProfilePresenter();

	AsyncProvider<AdvancedSearchPresenter> getAdvancedSearchPresenter();

	AsyncProvider<ContactUsPresenter> getContactUsPresenter();

	AsyncProvider<PropertyDetailsWithoutMapPresenter> getPropertyDetailsWithoutMapPresenter();

	AsyncProvider<PropertyDetailsWithMapPresenter> getPropertyDetailsWithMapPresenter();

	AsyncProvider<MortgagePresenter> getMortgagePresenter();

	AsyncProvider<CompareListingPresenter> getCompareListingPresenter();
}