package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.content.BaseWIPSvcHelper;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.shared.rpc.CancelProperty;
import org.jchlabs.gharonda.shared.rpc.CancelPropertyResult;
import org.jchlabs.gharonda.shared.rpc.Status;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class CancelPropertyHandler extends AbstractSessionHandler<CancelProperty, CancelPropertyResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;

	@Inject
	public CancelPropertyHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<ContentitemsDAO> itemDAOProvider) {
		super(logger, sessionProvider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
	}

	@Override
	protected CancelPropertyResult executeInternal(CancelProperty action, ExecutionContext context)
			throws ActionException {
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		ContentitemsDAO itemDAO = itemDAOProvider.get();
		if (logger.isDebugEnabled()) {
			logger.debug("The id = " + action.getPid());
		}
		Integer id = action.getPid();
		Contentholder holder = cDAO.get(id);
		BaseWIPSvcHelper helper = new BaseWIPSvcHelper(holder, cDAO, itemDAO);
		helper.undoCheckout();

		return new CancelPropertyResult(Status.SUCCESS);
	}

	@Override
	public Class<CancelProperty> getActionType() {
		return CancelProperty.class;
	}

	@Override
	public void undo(CancelProperty action, CancelPropertyResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}

}
