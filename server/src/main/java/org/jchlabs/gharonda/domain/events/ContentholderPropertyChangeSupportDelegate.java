package org.jchlabs.gharonda.domain.events;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.jchlabs.gharonda.domain.model.Contentholder;


public class ContentholderPropertyChangeSupportDelegate {

	private Contentholder contextContentHolder;
	private PropertyChangeSupport support;

	public ContentholderPropertyChangeSupportDelegate(Contentholder contextContentHolder) {
		this.contextContentHolder = contextContentHolder;
		this.support = new PropertyChangeSupport(this.contextContentHolder);
	}

	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		support.addPropertyChangeListener(propertyChangeListener);
	}

	protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (!newValue.equals(oldValue))
			support.firePropertyChange(property, oldValue, newValue);
	}

}
