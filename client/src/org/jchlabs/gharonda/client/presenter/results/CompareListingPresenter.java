package org.jchlabs.gharonda.client.presenter.results;

import java.util.LinkedList;
import java.util.List;

import org.jchlabs.gharonda.client.NameTokens;
import org.jchlabs.gharonda.client.presenter.AbstractAppPresenter;
import org.jchlabs.gharonda.client.presenter.app.AppPresenter;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.results.CompareListingUiHandlers;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;
import org.jchlabs.gharonda.shared.rpc.CompareProperties;
import org.jchlabs.gharonda.shared.rpc.ComparePropertiesResult;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class CompareListingPresenter extends
		AbstractAppPresenter<CompareListingPresenter.MyView, CompareListingPresenter.MyProxy> implements
		CompareListingUiHandlers {
	/**
	 * {@link CompareListingPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.compareListingPage)
	public interface MyProxy extends AbstractAppPresenter.MyProxy<CompareListingPresenter> {

	}

	/**
	 * {@link CompareListingPresenter}'s view.
	 */
	public interface MyView extends AbstractAppPresenter.MyView, HasUiHandlers<CompareListingUiHandlers> {
		void showCompareProperties(List<List<Integer>> compareProps, List<List<PropertiesDTO>> compareProperties,
				int size);

	}

	private final DispatchAsync dispatcher;

	@Inject
	public CompareListingPresenter(final EventBus eventBus, final PlaceManager placeManager, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(dispatcher, eventBus, view, proxy);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	public String getBreadCrumbHeading() {
		return PropertyOptions.compareListing;
	}

	@TitleFunction
	public static String getTitle(PlaceRequest request) {
		return PropertyOptions.compareListing;
	}

	@Override
	protected void onReset() {
		super.onReset();
		displayComparisonProperties();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_SetMainContent, this);
	}

	private void displayComparisonProperties() {

		String ids = Cookies.getCookie(PropertyOptions.COMPARE_LISTING_COOKIE);
		String[] idsArr = ids.split(", ");

		final List<List<Integer>> compareProps = new LinkedList<List<Integer>>();
		int totalChunks = idsArr.length / CommonConstants.COMPARE_PROPERTIES_SIZE;
		totalChunks = idsArr.length % CommonConstants.COMPARE_PROPERTIES_SIZE == 0 ? totalChunks : totalChunks + 1;

		for (int i = 0; i < totalChunks; i++) {
			int j = i * CommonConstants.COMPARE_PROPERTIES_SIZE;
			int offset = j + CommonConstants.COMPARE_PROPERTIES_SIZE;
			offset = (offset < idsArr.length) ? offset : idsArr.length;
			List<Integer> chunk = new LinkedList<Integer>();
			for (int k = j; k < offset; k++) {
				Integer id = Integer.parseInt(idsArr[k]);
				chunk.add(id);
			}
			compareProps.add(chunk);
		}
		CompareProperties action = new CompareProperties(compareProps, 1, 0);
		dispatcher.execute(action, new AsyncCallback<ComparePropertiesResult>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(ComparePropertiesResult result) {
				getView().showCompareProperties(compareProps, result.getCompareProperties(),
						result.getCompareProperties().size());
				showFeaturedProperties();

			}
		});
	}

	@Override
	protected int getFeaturedListingsSize() {
		return 5;
	}

}
