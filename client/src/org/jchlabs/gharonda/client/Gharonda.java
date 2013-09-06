package org.jchlabs.gharonda.client;

import org.jchlabs.gharonda.client.gin.GharondaGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gharonda implements EntryPoint {
	public final GharondaGinjector ginjector = GWT.create(GharondaGinjector.class);

	public void onModuleLoad() {
		DelayedBindRegistry.bind(ginjector);
		ginjector.getPlaceManager().revealCurrentPlace();
	}
}
