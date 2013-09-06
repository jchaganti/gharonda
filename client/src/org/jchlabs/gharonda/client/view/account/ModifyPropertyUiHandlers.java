package org.jchlabs.gharonda.client.view.account;

import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ModifyPropertyUiHandlers extends UiHandlers {
	void handleSaveProperty(PropertiesDTO property);

	void handleCancelProperty();

}
