package org.jchlabs.gharonda.shared.exceptions;

import com.gwtplatform.dispatch.shared.ActionException;

/**
 *
 */
public class InvalidSessionException extends ActionException {

	/**
     * 
     */
	private static final long serialVersionUID = 995112620968798947L;

	public InvalidSessionException(String msg) {
		super(msg);
	}
}
