package org.jchlabs.gharonda.client.presenter;

import java.util.List;

import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.util.SearchFeaturedListing;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.FeaturedListingProfileIF;
import org.jchlabs.gharonda.shared.rpc.GetFeaturedProperties;
import org.jchlabs.gharonda.shared.rpc.GetFeaturedPropertiesResult;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * @author User
 * 
 * @param <V>
 * @param <P>
 */
public abstract class AbstractAppPresenter<V extends AbstractAppPresenter.MyView, P extends AbstractAppPresenter.MyProxy<?>>
		extends Presenter<V, P> implements HasBreadCrumbData {

	public interface MyProxy<Q extends AbstractAppPresenter<?, ?>> extends ProxyPlace<Q> {

	}

	public interface MyView extends View {
		void loadFeaturedListings(List<PropertiesDTO> featuredProperties, int size);
	}

	final DispatchAsync dispatcher;

	private List<PropertiesDTO> featuredProperties;

	public AbstractAppPresenter(DispatchAsync dispatcher, EventBus eventBus, V view, P proxy) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	public String getBreadCrumbHeading() {
		return "";
	}

	public boolean hasBreadCrumb() {
		return true;
	}

	protected abstract int getFeaturedListingsSize();

	protected void showFeaturedProperties() {
		showFeaturedProperties(getDefaultFeaturedPropertiesAction());
	}

	/**
	 * Get the featured listings in the following order 1. Fetch from the Cookie PropertyOptions.FEATURED_LISTING_COOKIE
	 * 2. Fetch from the VicinityFeaturedListing or Search Results based featured listings based on whether it is Home
	 * Page or Results Page
	 * 
	 */
	protected void showFeaturedProperties(GetFeaturedProperties action) {
		if (action != null) {
			dispatcher.execute(action, new AsyncCallback<GetFeaturedPropertiesResult>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Getting featured properties failed");
				}

				@Override
				public void onSuccess(GetFeaturedPropertiesResult result) {
					featuredProperties = result.getFeaturedProperties();
					getView().loadFeaturedListings(featuredProperties, getFeaturedListingsSize());
					PropertyOptions.setCookie(PropertyOptions.FEATURED_LISTING_COOKIE, featuredProperties);
				}
			});
		} else if (featuredProperties != null) {
			getView().loadFeaturedListings(featuredProperties, getFeaturedListingsSize());
		}

	}

	private GetFeaturedProperties getDefaultFeaturedPropertiesAction() {
		GetFeaturedProperties action = null;
		String ids = Cookies.getCookie(PropertyOptions.FEATURED_LISTING_COOKIE);
		if (ids != null) {
			String[] idsArr = ids.split(", ");
			FeaturedListingProfileIF flp = new SearchFeaturedListing(null,
					PropertyOptions.getPropertyIdSearchCriteriaForFeaturedListing(idsArr),
					PropertyOptions.DEFAULT_FEATURED_SEARCH_PROFILE);
			action = new GetFeaturedProperties(null, flp.getORSearchCriteria(), flp.getFeaturedListingType(),
					flp.getFetchProfile());

		}
		return action;
	}
}