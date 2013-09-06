package org.jchlabs.gharonda.server.servlet;

import org.apache.commons.logging.Log;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.Result;

@Singleton
public class GharondaDispatchServlet extends DispatchServiceServlet {
	private Log logger;

	@Inject
	public GharondaDispatchServlet(Dispatch dispatch, Log logger) {
		super(dispatch);
		this.logger = logger;
	}

	@Override
	public Result execute(Action<?> action) throws ActionException {
		try {
			logger.info("SahibidenevDispatchServlet: executing: "
					+ action.getClass().getName().replaceAll("^.*\\.", ""));
			Result res = super.execute(action);
			logger.info("SahibidenevDispatchServlet: finished: " + action.getClass().getName().replaceAll("^.*\\.", ""));
			return res;
		} catch (ActionException e) {
			logger.error("SahibidenevDispatchServlet returns an ActionException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SahibidenevDispatchServlet unexpected Exception:" + e.getMessage());
			return null;
		}
	}

	private static final long serialVersionUID = 1L;

}
