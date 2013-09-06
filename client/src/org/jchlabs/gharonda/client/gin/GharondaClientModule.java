package org.jchlabs.gharonda.client.gin;

import org.jchlabs.gharonda.client.FailureHandlerAlert;
import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.GharondaPlaceManager;
import org.jchlabs.gharonda.client.presenter.account.ModifyPropertyPresenter;
import org.jchlabs.gharonda.client.presenter.account.MyFavoritesPresenter;
import org.jchlabs.gharonda.client.presenter.account.MyListingsPresenter;
import org.jchlabs.gharonda.client.presenter.account.MyNotifierProfilePresenter;
import org.jchlabs.gharonda.client.presenter.account.MyProfilePresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.presenter.app.ArticlePresenter;
import org.jchlabs.gharonda.client.presenter.app.ContactUsPresenter;
import org.jchlabs.gharonda.client.presenter.app.LoginPresenterWidget;
import org.jchlabs.gharonda.client.presenter.app.RegisterPresenterWidget;
import org.jchlabs.gharonda.client.presenter.details.MortgagePresenter;
import org.jchlabs.gharonda.client.presenter.details.PropertyDetailsWithMapPresenter;
import org.jchlabs.gharonda.client.presenter.details.PropertyDetailsWithoutMapPresenter;
import org.jchlabs.gharonda.client.presenter.home.AdvancedSearchPresenter;
import org.jchlabs.gharonda.client.presenter.home.HomePresenter;
import org.jchlabs.gharonda.client.presenter.results.CompareListingPresenter;
import org.jchlabs.gharonda.client.presenter.results.ResultWithMapPresenter;
import org.jchlabs.gharonda.client.presenter.results.ResultWithoutMapPresenter;
import org.jchlabs.gharonda.client.view.account.ModifyPropertyView;
import org.jchlabs.gharonda.client.view.account.MyFavoritesView;
import org.jchlabs.gharonda.client.view.account.MyListingsView;
import org.jchlabs.gharonda.client.view.account.MyNotifierProfileView;
import org.jchlabs.gharonda.client.view.account.MyProfileView;
import org.jchlabs.gharonda.client.view.app.AppView;
import org.jchlabs.gharonda.client.view.app.ArticleView;
import org.jchlabs.gharonda.client.view.app.ContactUsView;
import org.jchlabs.gharonda.client.view.app.LoginView;
import org.jchlabs.gharonda.client.view.app.RegisterView;
import org.jchlabs.gharonda.client.view.details.MortgageView;
import org.jchlabs.gharonda.client.view.details.PropertyDetailsWithMapView;
import org.jchlabs.gharonda.client.view.details.PropertyDetailsWithoutMapView;
import org.jchlabs.gharonda.client.view.home.AdvancedSearchView;
import org.jchlabs.gharonda.client.view.home.HomeView;
import org.jchlabs.gharonda.client.view.results.CompareListingView;
import org.jchlabs.gharonda.client.view.results.ResultWithMapView;
import org.jchlabs.gharonda.client.view.results.ResultWithoutMapView;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.DefaultEventBus;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class GharondaClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		// NOTE: Commented lines are unused classes. They are commented out to
		// make sure
		// the GWT compiler does not include them.
		bindConstant().annotatedWith(SecurityCookie.class).to("JCH");

		// Singletons
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).to(GharondaPlaceManager.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(ProxyFailureHandler.class).to(FailureHandlerAlert.class).in(Singleton.class);

		// Non-singletons
		// //////////////////////////////////////////////////////////////////////
		// ////////////

		// Widgets

		// //////////////////////////////////////////////////////////////////////
		// ////////////

		// Presenter widget
		bindSingletonPresenterWidget(LoginPresenterWidget.class, LoginPresenterWidget.MyView.class, LoginView.class);
		bindSingletonPresenterWidget(RegisterPresenterWidget.class, RegisterPresenterWidget.MyView.class,
				RegisterView.class);
		// //////////////////////////////////////////////////////////////////////
		// ////////////

		// Presenter bundles

		// //////////////////////////////////////////////////////////////////////
		// ////////////

		// Constants
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.homePage);

		// //////////////////////////////////////////////////////////////////////
		// ////////////
		// Presenters
		bindPresenter(AppPresenter.class, AppPresenter.MyView.class, AppView.class, AppPresenter.MyProxy.class);
		bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
		bindPresenter(ArticlePresenter.class, ArticlePresenter.MyView.class, ArticleView.class,
				ArticlePresenter.MyProxy.class);
		bindPresenter(ResultWithoutMapPresenter.class, ResultWithoutMapPresenter.MyView.class,
				ResultWithoutMapView.class, ResultWithoutMapPresenter.MyProxy.class);
		bindPresenter(ResultWithMapPresenter.class, ResultWithMapPresenter.MyView.class, ResultWithMapView.class,
				ResultWithMapPresenter.MyProxy.class);
		bindPresenter(MyListingsPresenter.class, MyListingsPresenter.MyView.class, MyListingsView.class,
				MyListingsPresenter.MyProxy.class);
		bindPresenter(MyProfilePresenter.class, MyProfilePresenter.MyView.class, MyProfileView.class,
				MyProfilePresenter.MyProxy.class);
		bindPresenter(MyFavoritesPresenter.class, MyFavoritesPresenter.MyView.class, MyFavoritesView.class,
				MyFavoritesPresenter.MyProxy.class);
		bindPresenter(MyNotifierProfilePresenter.class, MyNotifierProfilePresenter.MyView.class,
				MyNotifierProfileView.class, MyNotifierProfilePresenter.MyProxy.class);
		bindPresenter(ModifyPropertyPresenter.class, ModifyPropertyPresenter.MyView.class, ModifyPropertyView.class,
				ModifyPropertyPresenter.MyProxy.class);
		bindPresenter(AdvancedSearchPresenter.class, AdvancedSearchPresenter.MyView.class, AdvancedSearchView.class,
				AdvancedSearchPresenter.MyProxy.class);
		bindPresenter(ContactUsPresenter.class, ContactUsPresenter.MyView.class, ContactUsView.class,
				ContactUsPresenter.MyProxy.class);
		bindPresenter(PropertyDetailsWithoutMapPresenter.class, PropertyDetailsWithoutMapPresenter.MyView.class,
				PropertyDetailsWithoutMapView.class, PropertyDetailsWithoutMapPresenter.MyProxy.class);
		bindPresenter(PropertyDetailsWithMapPresenter.class, PropertyDetailsWithMapPresenter.MyView.class,
				PropertyDetailsWithMapView.class, PropertyDetailsWithMapPresenter.MyProxy.class);
		bindPresenter(MortgagePresenter.class, MortgagePresenter.MyView.class, MortgageView.class,
				MortgagePresenter.MyProxy.class);
		bindPresenter(CompareListingPresenter.class, CompareListingPresenter.MyView.class, CompareListingView.class,
				CompareListingPresenter.MyProxy.class);
		// //////////////////////////////////////////////////////////////////////
		// ////////////
	}

}
